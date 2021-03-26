package com.sapo.qlsc.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapo.qlsc.converter.MaintenanceCardConverter;
import com.sapo.qlsc.dto.MaintenanceCardDTO;
import com.sapo.qlsc.entity.*;
import com.sapo.qlsc.exception.CodeExistedException;
import com.sapo.qlsc.exception.NotANumberException;
import com.sapo.qlsc.exception.commonException.NotFoundException;
import com.sapo.qlsc.exception.commonException.UnknownException;
import com.sapo.qlsc.exception.customerException.DupplicateFieldException;
import com.sapo.qlsc.exception.maintenanceCardException.NotEnoughProductException;
import com.sapo.qlsc.exception.maintenanceCardException.NotFoundRepairmanException;
import com.sapo.qlsc.exception.maintenanceCardException.NotUpdateException;
import com.sapo.qlsc.kafka.ProductModel;
import com.sapo.qlsc.model.MaintenanceCardCustomer;
import com.sapo.qlsc.model.MaintenanceCardFilter;
import com.sapo.qlsc.model.MessageModel;
import com.sapo.qlsc.repository.*;
import com.sapo.qlsc.service.MaintenanceCardService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.math.BigDecimal;
import java.util.*;

@Service
public class MaintenanceCardServiceImpl implements MaintenanceCardService {
    @Autowired
    private MaintenanceCardConverter maintenanceCardConverter;

    @Autowired
    private MaintenanceCardRepository maintenanceCardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MaintenanceCardDetailRepository maintenanceCardDetailRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    @Override
    public MaintenanceCardDTO insertMaintenanceCard(MaintenanceCardDTO maintenanceCardDTO) throws NotEnoughProductException, CodeExistedException, JsonProcessingException {
        MaintenanceCard maintenanceCard = maintenanceCardConverter.convertToEntity(maintenanceCardDTO);
        Date now = new Date();
        maintenanceCard.setCreatedDate(now);
        maintenanceCard.setModifiedDate(now);
        Long total = Long.valueOf(0);
        boolean check = true;
        for (MaintenanceCardDetail maintenanceCardDetail : maintenanceCard.getMaintenanceCardDetails()) {
            maintenanceCardDetail.setCreatedDate(now);
            maintenanceCardDetail.setModifiedDate(now);
            maintenanceCardDetail.setMaintenanceCard(maintenanceCard);
            maintenanceCardDetail.setMaintenanceCardDetailStatusHistories(new ArrayList<>());
            // Giam so luong trong kho: so luong con lai = so luong hien tai - so luong trong phieu sua chua
            Product product = productRepository.findById(maintenanceCardDetail.getProduct().getId()).orElse(null);
            if (product != null && product.getType() == 1) {
                int quantity = product.getQuantity() - maintenanceCardDetail.getQuantity();
                if (quantity >= 0 && maintenanceCardDetail.getQuantity() > 0) {
                    product.setQuantity(product.getQuantity() - maintenanceCardDetail.getQuantity());
                    ProductModel productModel = new ProductModel();
                    productModel.setAmountChargeInUnit(1);
                    productModel.setCode("MC001");
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonString = mapper.writeValueAsString(productModel);
                    ProducerRecord<String, String> record = new ProducerRecord<String, String>("qlsc_product", product.getId()+"", jsonString);
                    kafkaTemplate.send(record);
                } else {
                    throw new NotEnoughProductException(product.getId().toString());
                }
                total += maintenanceCardDetail.getPrice().longValue() * maintenanceCardDetail.getQuantity();
            } else if (product != null) {
                MaintenanceCardDetailStatusHistory maintenanceCardDetailStatusHistory = new MaintenanceCardDetailStatusHistory();
                maintenanceCardDetailStatusHistory.setCreatedDate(now);
                maintenanceCardDetailStatusHistory.setModifiedDate(now);
                maintenanceCardDetailStatusHistory.setMaintenanceCardDetail(maintenanceCardDetail);
                maintenanceCardDetailStatusHistory.setStatus((byte) 0);
                maintenanceCardDetail.getMaintenanceCardDetailStatusHistories().add(maintenanceCardDetailStatusHistory);
                total += maintenanceCardDetail.getPrice().longValue();
                check = false;
            }
            maintenanceCardDetail.setProduct(product);
        }
        maintenanceCard.setPrice(BigDecimal.valueOf(total));
        maintenanceCard.setPlatesNumber(maintenanceCard.getPlatesNumber().toLowerCase());
        if (maintenanceCard.getCode() == null || maintenanceCard.getCode().length() == 0) {
            try {
                maintenanceCard.setCode(createNewCode());
            } catch (NotANumberException notANumberExcepton) {
                notANumberExcepton.printStackTrace();
            }
        } else {

            int checkCode = maintenanceCardRepository.checkCode(maintenanceCard.getCode().toLowerCase(), Long.valueOf(0));
            if (checkCode != 0) throw new CodeExistedException("Code existed");
            maintenanceCard.setCode(maintenanceCard.getCode().toLowerCase());

        }
        if (check && maintenanceCard.getMaintenanceCardDetails().size() != 0) {
            maintenanceCard.setWorkStatus((byte) 2);
        } else {
            maintenanceCard.setWorkStatus((byte) 0);
        }
        MaintenanceCard maintenanceCard1 = maintenanceCardRepository.save(maintenanceCard);
        if (maintenanceCard1.getRepairman() != null) {
            MessageModel messageModel = new MessageModel();
            messageModel.setType(1);
            messageModel.setMessage(maintenanceCard.getId().toString());
            Message message = new Message();
            message.setStatus((byte) 1);
            message.setUrl("/admin/maintenanceCards/" + maintenanceCard.getId().toString());
            message.setTitle("Thêm mới phiếu sửa chữa");
            message.setContent("Một phiếu sửa chữa mới đã được điều phối cho bạn");
            message.setUser(maintenanceCard1.getRepairman());
            message.setCreatedDate(now);
            message.setModifiedDate(now);
            messageRepository.save(message);
            simpMessagingTemplate.convertAndSend("/topic/messages/" + maintenanceCard1.getRepairman().getId(), messageModel);
        }
        return maintenanceCardConverter.convertAllToDTO(maintenanceCard1);
    }

    @Override
    public Map<String, Object> searchMaintenanceCard(MaintenanceCardFilter maintenanceCardFilter, String email, int role) {
        int page = maintenanceCardFilter.getPage();
        int size = maintenanceCardFilter.getSize();
        String search = maintenanceCardFilter.getSearch();
        String nameField = maintenanceCardFilter.getNameField();
        String order = maintenanceCardFilter.getOrder();
        byte[] workStatus = maintenanceCardFilter.getWorkStatus();
        byte[] payStatus = maintenanceCardFilter.getPayStatus();
        Pageable paging = PageRequest.of(page - 1, size, Sort.by("modifiedDate").descending());

        if (!nameField.equals("")) {
            paging = PageRequest.of(page - 1, size, Sort.by(nameField));
            if (order.equals("descend")) {
                paging = PageRequest.of(page - 1, size, Sort.by(nameField).descending());
            }
        }
        Page<MaintenanceCard> maintenanceCardPage = maintenanceCardRepository.search(paging, search, workStatus, payStatus, email, role);
        List<MaintenanceCardDTO> maintenanceCardDTOList = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        List<MaintenanceCard> maintenanceCards = maintenanceCardPage.getContent();
        for (MaintenanceCard maintenanceCard : maintenanceCards) {
            maintenanceCardDTOList.add(maintenanceCardConverter.convertToDTO(maintenanceCard));
        }
        map.put("maintenanceCards", maintenanceCardDTOList);
        map.put("currentPage", maintenanceCardPage.getNumber() + 1);
        map.put("totalItems", maintenanceCardPage.getTotalElements());
        map.put("totalPages", maintenanceCardPage.getTotalPages());
        return map;
    }

    @Override
    public MaintenanceCardDTO getMaintenanceCardById(Long id, String email, int role) throws NotFoundException {

        MaintenanceCard maintenanceCard = maintenanceCardRepository.getMaintenanceCardByIdAndEmail(id, email, role);
        if (maintenanceCard == null) {
            throw new NotFoundException("Not found maintenance card");
        }
        return maintenanceCardConverter.convertAllToDTO(maintenanceCard);

    }

    @Override
    public MaintenanceCardDTO updateMaintenanceCard(MaintenanceCardDTO maintenanceCardDTO, String email, int role) throws NotEnoughProductException, NotFoundException, CodeExistedException, NotUpdateException, UnknownException {

        MaintenanceCard maintenanceCardUpdate = maintenanceCardRepository.getMaintenanceCardByIdAndCoordinatorEmail(maintenanceCardDTO.getId(), email, role);
        if (maintenanceCardUpdate == null) {
            throw new NotFoundException("Not found maintenance card");
        }
        if (maintenanceCardUpdate.getReturnDate() != null) {
            throw new NotUpdateException();
        }
        byte status = 0;
        boolean check = true;
        boolean checkNull = true;

        MaintenanceCard maintenanceCard = maintenanceCardConverter.convertToEntity(maintenanceCardDTO);
        if (maintenanceCard.getMaintenanceCardDetails().size() == 0) {
            checkNull = false;
        }
        Date now = new Date();
        maintenanceCard.setCreatedDate(maintenanceCardUpdate.getCreatedDate());
        maintenanceCard.setModifiedDate(now);
        Long total = Long.valueOf(0);
        Long[] maintenanceCardDetailId = new Long[10000];
        int dem = 0;
        for (MaintenanceCardDetail maintenanceCardDetail : maintenanceCard.getMaintenanceCardDetails()) {
            maintenanceCardDetailId[dem] = maintenanceCardDetail.getId();
            dem++;
            MaintenanceCardDetail maintenanceCardDetail1Update = null;
            if (maintenanceCardDetail.getId() != null) {
                maintenanceCardDetail1Update = maintenanceCardDetailRepository.findById(maintenanceCardDetail.getId()).orElse(null);
            }
            // neu them moi
            if (maintenanceCardDetail1Update == null) {
                maintenanceCardDetail.setCreatedDate(now);
                maintenanceCardDetail.setModifiedDate(now);
                maintenanceCardDetail.setMaintenanceCard(maintenanceCard);
                Product product = productRepository.findById(maintenanceCardDetail.getProduct().getId()).orElse(null);
                if (product != null && product.getType() == 1) {
                    int quantity = product.getQuantity() - maintenanceCardDetail.getQuantity();
                    if (quantity >= 0 && maintenanceCardDetail.getQuantity() > 0) {
                        product.setQuantity(product.getQuantity() - maintenanceCardDetail.getQuantity());
                    } else {
                        throw new NotEnoughProductException(product.getId().toString());
                    }
                    total += maintenanceCardDetail.getPrice().longValue() * maintenanceCardDetail.getQuantity();
                } else if (product != null) {
                    MaintenanceCardDetailStatusHistory maintenanceCardDetailStatusHistory = new MaintenanceCardDetailStatusHistory();
                    maintenanceCardDetailStatusHistory.setCreatedDate(now);
                    maintenanceCardDetailStatusHistory.setModifiedDate(now);
                    maintenanceCardDetailStatusHistory.setMaintenanceCardDetail(maintenanceCardDetail);
                    maintenanceCardDetailStatusHistory.setStatus((byte) 0);
                    List<MaintenanceCardDetailStatusHistory> maintenanceCardDetailStatusHistories = new ArrayList<>();
                    maintenanceCardDetailStatusHistories.add(maintenanceCardDetailStatusHistory);
                    maintenanceCardDetail.setMaintenanceCardDetailStatusHistories(maintenanceCardDetailStatusHistories);
                    total += maintenanceCardDetail.getPrice().longValue();
                    check = false;
                }

                maintenanceCardDetail.setProduct(product);
            }
            // neu da ton tai
            else {
                maintenanceCardDetail.setCreatedDate(maintenanceCardDetail1Update.getCreatedDate());
                maintenanceCardDetail.setMaintenanceCard(maintenanceCard);
                maintenanceCardDetail.setStatus(maintenanceCardDetail1Update.getStatus());

                Product product = productRepository.findById(maintenanceCardDetail.getProduct().getId()).orElse(null);
                // so luong con lai = so luong trong kho - chenh lech giua phieu sua chua truoc va sau
                if (product != null && product.getType() == 1) {
                    int quantity = product.getQuantity() - (maintenanceCardDetail.getQuantity() - maintenanceCardDetail1Update.getQuantity());
                    if (quantity >= 0 && maintenanceCardDetail.getQuantity() > 0) {
                        maintenanceCardDetail.setModifiedDate(now);
                        product.setQuantity(quantity);
                    } else {
                        throw new NotEnoughProductException(product.getId().toString());
                    }
                    total += maintenanceCardDetail.getPrice().longValue() * maintenanceCardDetail.getQuantity();
                } else if (product != null) {
                    total += maintenanceCardDetail.getPrice().longValue();
                    if (maintenanceCardDetail1Update.getStatus() != 0) {
                        status = 1;
                    }
                    if (maintenanceCardDetail1Update.getStatus() != 2) {
                        check = false;
                    }
                }
                maintenanceCardDetail.setMaintenanceCardDetailStatusHistories(maintenanceCardDetail1Update.getMaintenanceCardDetailStatusHistories());
            }
        }
        maintenanceCard.setPrice(BigDecimal.valueOf(total));
        for (MaintenanceCardDetail maintenanceCardDetail : maintenanceCardUpdate.getMaintenanceCardDetails()) {
            if (!ArrayUtils.contains(maintenanceCardDetailId, maintenanceCardDetail.getId())) {
                if (maintenanceCardDetail.getIsDelete() == 0) {
                    if (!(maintenanceCardDetail.getProduct().getType() == 2 && maintenanceCardDetail.getStatus() != 0)) {
                        Product product = productRepository.findById(maintenanceCardDetail.getProduct().getId()).orElse(null);
                        if (product != null && product.getType() == 1) {
                            if (maintenanceCardDetail.getQuantity() > 0) {
                                product.setQuantity(product.getQuantity() + maintenanceCardDetail.getQuantity());
                            } else {
                                throw new NotEnoughProductException(product.getId().toString());
                            }
                        } else {
                            MaintenanceCardDetailStatusHistory maintenanceCardDetailStatusHistory = new MaintenanceCardDetailStatusHistory();
                            maintenanceCardDetailStatusHistory.setCreatedDate(now);
                            maintenanceCardDetailStatusHistory.setModifiedDate(now);
                            maintenanceCardDetailStatusHistory.setMaintenanceCardDetail(maintenanceCardDetail);
                            maintenanceCardDetailStatusHistory.setStatus((byte) (-1));
                            maintenanceCardDetail.getMaintenanceCardDetailStatusHistories().add(maintenanceCardDetailStatusHistory);
                        }
                        maintenanceCardDetail.setIsDelete((byte) 1);

                    }
                }
                maintenanceCard.getMaintenanceCardDetails().add(maintenanceCardDetail);
            }
        }
        maintenanceCard.setCode(maintenanceCard.getCode().toLowerCase());
        if (maintenanceCard.getCode() == null || maintenanceCard.getCode().length() == 0) {
            maintenanceCard.setCode(maintenanceCardUpdate.getCode());
        } else {
            if (maintenanceCardRepository.checkCode(maintenanceCard.getCode(), maintenanceCard.getId()) > 0) {
                throw new CodeExistedException("Code existed");
            }
        }
        if (check) {
            maintenanceCard.setWorkStatus((byte) 2);

        } else {
            maintenanceCard.setWorkStatus(status);
        }
        if (!checkNull) {
            maintenanceCard.setWorkStatus((byte) 0);
        }
        Long temp = Long.valueOf(0);
        for (PaymentHistory paymentHistory : maintenanceCardUpdate.getPaymentHistories()) {
            temp += paymentHistory.getMoney().longValue();
        }
        if (temp < total || !checkNull) {
            maintenanceCard.setPayStatus((byte) 0);
        } else {
            maintenanceCard.setPayStatus((byte) 1);
        }
        maintenanceCard.setCoordinator(maintenanceCardUpdate.getCoordinator());
        maintenanceCard.setCustomer(maintenanceCardUpdate.getCustomer());
        maintenanceCard.setPaymentHistories(maintenanceCardUpdate.getPaymentHistories());
        if (maintenanceCard.getWorkStatus() != 2 || maintenanceCard.getPayStatus() != 1) {
            maintenanceCard.setReturnDate(null);
        } else {
            if (maintenanceCard.getReturnDate() != null) {
                Date returnDate = maintenanceCard.getReturnDate();
                if (returnDate.compareTo(now) > 0) {
                    throw new UnknownException();
                }
            }
        }
        maintenanceCard.setPlatesNumber(maintenanceCardUpdate.getPlatesNumber().toLowerCase());
        if (maintenanceCardUpdate.getRepairman() != null && maintenanceCardUpdate.getRepairman().getId() != null) {
            maintenanceCard.setRepairman(maintenanceCardUpdate.getRepairman());
        }

        try {
            MaintenanceCard maintenanceCard1 = maintenanceCardRepository.save(maintenanceCard);
            MessageModel messageModel = new MessageModel();
            if (maintenanceCard1.getWorkStatus() == 2 && maintenanceCard1.getPayStatus() == 0) {
                messageModel.setType(2);
                messageModel.setMessage(maintenanceCard.getId().toString());
                for (User user : userRepository.getAllManager()) {
                    Message message = new Message();
                    message.setStatus((byte) 1);
                    message.setUrl("/admin/maintenanceCards/" + maintenanceCard.getId().toString());
                    message.setTitle("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() + " đang chờ thanh toán");
                    message.setContent("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() + " đã được sửa chữa xong và chờ được thanh toán");
                    message.setUser(user);
                    message.setCreatedDate(now);
                    message.setModifiedDate(now);
                    messageRepository.save(message);
                    simpMessagingTemplate.convertAndSend("/topic/messages/" + user.getId(), messageModel);
                }
            } else {
                messageModel.setType(3);
                messageModel.setMessage(maintenanceCard.getId().toString());
                messageModel.setCode(maintenanceCard.getCode().toString());
                for (User user : userRepository.getAllManager()) {
                    if (user.getId() != maintenanceCard.getCoordinator().getId()) {
                        Message message = new Message();
                        message.setStatus((byte) 1);
                        message.setUrl("/admin/maintenanceCards/" + maintenanceCard.getId().toString());
                        message.setTitle("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() + " đã được cập nhật");
                        message.setContent("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() + " đã được cập nhật.");
                        message.setUser(user);
                        message.setCreatedDate(now);
                        message.setModifiedDate(now);
                        messageRepository.save(message);
                        simpMessagingTemplate.convertAndSend("/topic/messages/" + user.getId(), messageModel);
                    }

                }
            }
            messageModel.setType(3);
            messageModel.setMessage(maintenanceCard.getId().toString());
            messageModel.setCode(maintenanceCard.getCode().toString());
            if (maintenanceCard1.getRepairman() != null) {
                Message message = new Message();
                message.setStatus((byte) 1);
                message.setUrl("/admin/maintenanceCards/" + maintenanceCard.getId().toString());
                message.setTitle("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() + " đã được cập nhật");
                message.setContent("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() + " đã được cập nhật.");
                message.setUser(maintenanceCard1.getRepairman());
                message.setCreatedDate(now);
                message.setModifiedDate(now);
                messageRepository.save(message);
                simpMessagingTemplate.convertAndSend("/topic/messages/" + maintenanceCard1.getRepairman().getId(), messageModel);
            }
            return maintenanceCardConverter.convertAllToDTO(maintenanceCard1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknownException();
        }
    }

    @Override
    public MaintenanceCardDTO updateAllStatusMaintenanceCard(Long id, String email, int role) throws NotFoundException, NotFoundRepairmanException {
        Date now = new Date();
        MaintenanceCard maintenanceCard = maintenanceCardRepository.getMaintenanceCardByIdAndRepairmanEmail(id, email, role);
        if (maintenanceCard == null) {
            throw new NotFoundException("Not found maintenance card");
        }
        if (maintenanceCard.getRepairman() != null) {
            byte workStatus = 2;
            maintenanceCard.setWorkStatus(workStatus);
            for (MaintenanceCardDetail maintenanceCardDetail : maintenanceCard.getMaintenanceCardDetails()) {
                if (maintenanceCardDetail.getProduct().getType() == 2) {
                    byte dis = 1;
                    for (byte i = (byte) (maintenanceCardDetail.getStatus() + dis); i <= 2; i++) {
                        MaintenanceCardDetailStatusHistory maintenanceCardDetailStatusHistory = new MaintenanceCardDetailStatusHistory();
                        maintenanceCardDetailStatusHistory.setCreatedDate(now);
                        maintenanceCardDetailStatusHistory.setModifiedDate(now);
                        maintenanceCardDetailStatusHistory.setMaintenanceCardDetail(maintenanceCardDetail);
                        maintenanceCardDetailStatusHistory.setStatus((byte) (i));
                        maintenanceCardDetail.getMaintenanceCardDetailStatusHistories().add(maintenanceCardDetailStatusHistory);
                    }
                    maintenanceCardDetail.setStatus(workStatus);

                }
            }
            MaintenanceCard maintenanceCard1 = maintenanceCardRepository.save(maintenanceCard);
            MessageModel messageModel = new MessageModel();
            if (maintenanceCard1.getWorkStatus() == 2 && maintenanceCard1.getPayStatus() == 0) {
                messageModel.setType(2);
                messageModel.setMessage(maintenanceCard.getId().toString());
                for (User user : userRepository.getAllManager()) {
                    Message message = new Message();
                    message.setStatus((byte) 1);
                    message.setUrl("/admin/maintenanceCards/" + maintenanceCard.getId().toString());
                    message.setTitle("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() + " chờ được thanh toán");
                    message.setContent("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() + " đã được sửa chữa xong và chờ thanh toán ");
                    message.setUser(user);
                    message.setCreatedDate(now);
                    message.setModifiedDate(now);
                    messageRepository.save(message);
                    simpMessagingTemplate.convertAndSend("/topic/messages/" + user.getId(), messageModel);
                }
            } else {
                messageModel.setType(3);
                messageModel.setMessage(maintenanceCard.getId().toString());
                messageModel.setCode(maintenanceCard.getCode().toString());
                for (User user : userRepository.getAllManager()) {
                    Message message = new Message();
                    message.setStatus((byte) 1);
                    message.setUrl("/admin/maintenanceCards/" + maintenanceCard.getId().toString());
                    message.setTitle("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() + " đã được cập nhật");
                    message.setContent("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() + " đã được cập nhật");
                    message.setUser(user);
                    message.setCreatedDate(now);
                    message.setModifiedDate(now);
                    messageRepository.save(message);
                    simpMessagingTemplate.convertAndSend("/topic/messages/" + user.getId(), messageModel);
                }
            }
            messageModel.setType(3);
            messageModel.setMessage(maintenanceCard.getId().toString());
            messageModel.setCode(maintenanceCard.getCode().toString());
//            if(maintenanceCard1.getRepairman() != null){
//                simpMessagingTemplate.convertAndSend("/topic/messages/" + maintenanceCard1.getRepairman().getId(), messageModel);
//            }
            if (maintenanceCard1.getCoordinator() != null) {
                Message message = new Message();
                message.setStatus((byte) 1);
                message.setUrl("/admin/maintenanceCards/" + maintenanceCard.getId().toString());
                message.setTitle("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() + " đã được cập nhật");
                message.setContent("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() + " đã được cập nhật");
                message.setUser(maintenanceCard1.getCoordinator());
                message.setCreatedDate(now);
                message.setModifiedDate(now);
                messageRepository.save(message);
                simpMessagingTemplate.convertAndSend("/topic/messages/" + maintenanceCard1.getCoordinator().getId(), messageModel);
            }
            return maintenanceCardConverter.convertAllToDTO(maintenanceCard1);
        } else {
            return maintenanceCardConverter.convertAllToDTO(maintenanceCard);
        }
    }

    @Override
    public boolean deleteMaintenanceCard(Long id) throws NotFoundException, NotFoundRepairmanException, NotEnoughProductException, UnknownException {
        MaintenanceCard maintenanceCard = maintenanceCardRepository.findById(id).orElse(null);
        if (maintenanceCard == null) {
            throw new NotFoundException("Not found maintenance card");
        }
        if (maintenanceCard.getWorkStatus() == 0 && maintenanceCard.getPayStatus() == 0 && maintenanceCard.getPaymentHistories().size() == 0) {

            for (MaintenanceCardDetail maintenanceCardDetail : maintenanceCard.getMaintenanceCardDetails()) {
                if (maintenanceCardDetail.getProduct().getType() == 2) {
                    Product product = productRepository.findById(maintenanceCardDetail.getProduct().getId()).orElse(null);
                    if (product != null && product.getType() == 1) {
                        if (maintenanceCardDetail.getQuantity() > 0) {
                            product.setQuantity(product.getQuantity() + maintenanceCardDetail.getQuantity());
                        } else {
                            throw new NotEnoughProductException(product.getId().toString());
                        }
                    }
                }
                maintenanceCardDetail.setIsDelete((byte) 1);
            }
            maintenanceCardRepository.delete(maintenanceCard);
            return true;
        } else {
            throw new UnknownException();
        }

    }

    @Override
    public Map<String, Object> getMaintenanceCardByRepairMan(int PageNum, int PageSize, String sortBy, boolean descending, Long userId, String code, byte[] payStatus, byte[] workStatus) {
        Page<MaintenanceCard> page = null;


        System.out.println("workStatus :" + Arrays.toString(workStatus));
        System.out.println("payStatus: " + Arrays.toString(payStatus));
        Pageable pageable = null;
        if (descending) {
            pageable = PageRequest.of(PageNum - 1, PageSize, Sort.by(sortBy.length() == 0 ? "id" : sortBy).descending());
        } else {
            pageable = PageRequest.of(PageNum - 1, PageSize, Sort.by(sortBy.length() == 0 ? "id" : sortBy).ascending());
        }
        if ((payStatus != null && payStatus.length > 0) || (workStatus != null && workStatus.length > 0)) {
            System.out.println("filter");
            page = maintenanceCardRepository.filterByWsandPs(pageable, userId, workStatus, payStatus, code);
        } else {
            System.out.println("Not filter");
            page = maintenanceCardRepository.getMaintenanceCardByRepairMan(pageable, userId, code);
        }
        List<MaintenanceCardDTO> maintenanceCardDTO = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        page.toList().forEach(maintenanceCard -> {
            maintenanceCardDTO.add(maintenanceCardConverter.convertToDTO(maintenanceCard));
        });
        map.put("maintenanceCard", maintenanceCardDTO);
        map.put("totalPage", page.getTotalPages());
        map.put("totalElement", page.getTotalElements());
        map.put("currentPage", PageNum);
        return map;

    }

    @Override
    public List<String> getPlatesNumberByCustomerId(Long id) {
        List<String> listPlates = maintenanceCardRepository.getPlatesNumberByCustomerId(id);
        return listPlates;
    }

    public String createNewCode() throws NotANumberException {
        Long codeNumber = 0L;
        String newCodeString;
        int index = 0;
        String getMaxCode = null;
        getMaxCode = maintenanceCardRepository.getMaxCode(index);
        System.out.println(getMaxCode);
        do {
            getMaxCode = maintenanceCardRepository.getMaxCode(index);
            if (getMaxCode == null) {
                getMaxCode = "0";
            } else {
                boolean result = StringUtils.isNumeric(getMaxCode);
                if (!result) {
                    getMaxCode = null;
                    index++;
                } else {
                    getMaxCode = getMaxCode;
                }
            }
        } while (getMaxCode == null);
        codeNumber = Long.parseLong(getMaxCode) + 1;
        newCodeString = "mc00" + codeNumber.toString();
        return newCodeString;
    }

    @Override
    public Map<String, Object> getMaintenanceCardByIdCustomer(MaintenanceCardCustomer maintenanceCardCustomer) {

        int page = maintenanceCardCustomer.getPage();
        int size = maintenanceCardCustomer.getSize();
        String search = maintenanceCardCustomer.getSearch();
        Long id = maintenanceCardCustomer.getId();
        String nameField = maintenanceCardCustomer.getNameField();
        String order = maintenanceCardCustomer.getOrder();
        byte[] payStatus = maintenanceCardCustomer.getPayStatus();
        byte[] workStatus = maintenanceCardCustomer.getWorkStatus();
        Pageable paging = PageRequest.of(page - 1, size, Sort.by("modifiedDate").descending());

        if (!nameField.equals("")) {
            paging = PageRequest.of(page - 1, size, Sort.by(nameField));
            if (order.equals("descend")) {
                paging = PageRequest.of(page - 1, size, Sort.by(nameField).descending());
            }
        }

        Page<MaintenanceCard> maintenanceCardPage = maintenanceCardRepository.getMaintenanceCardByIdCustomer(paging, id, search, payStatus, workStatus);

        List<MaintenanceCardDTO> maintenanceCardDTOS = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        List<MaintenanceCard> maintenanceCards = maintenanceCardPage.getContent();

        for (MaintenanceCard maintenanceCard : maintenanceCards) {
            maintenanceCardDTOS.add(maintenanceCardConverter.convertToDTO(maintenanceCard));
        }

        map.put("customers", maintenanceCardDTOS);
        map.put("currentPage", maintenanceCardPage.getNumber() + 1);
        map.put("totalItems", maintenanceCardPage.getTotalElements());
        map.put("totalPages", maintenanceCardPage.getTotalPages());
        return map;
    }


}
