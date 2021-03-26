package com.sapo.qlsc.converter;

import com.sapo.qlsc.dto.*;
import com.sapo.qlsc.entity.*;
import com.sapo.qlsc.repository.MaintenanceCardDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class MaintenanceCardConverter {

    @Autowired
    UserConverter userConverter;
    @Autowired
    CustomerConverter customerConverter;
    @Autowired
    MaintenanceCardDetailRepository maintenanceCardDetailRepository;

    public MaintenanceCardDTO convertToDTO(MaintenanceCard maintenanceCard) {
        MaintenanceCardDTO maintenanceCardDTO = new MaintenanceCardDTO();
        maintenanceCardDTO.setCode(maintenanceCard.getCode());
        maintenanceCardDTO.setPlatesNumber(maintenanceCard.getPlatesNumber());
        if(maintenanceCard.getCustomer() != null) {
            maintenanceCardDTO.setCustomer(customerConverter.convertToDTO(maintenanceCard.getCustomer(),"get"));
        }
        if(maintenanceCard.getRepairman() != null) {
            maintenanceCardDTO.setRepairman(userConverter.convertToDTO(maintenanceCard.getRepairman()));
        }
        maintenanceCardDTO.setDescription(maintenanceCard.getDescription());
        maintenanceCard.setReturnDate(maintenanceCard.getReturnDate());//
        maintenanceCardDTO.setPrice(maintenanceCard.getPrice());
        maintenanceCardDTO.setReturnDate(maintenanceCard.getReturnDate());
        maintenanceCardDTO.setWorkStatus(maintenanceCard.getWorkStatus());
        maintenanceCardDTO.setPayStatus(maintenanceCard.getPayStatus());
        maintenanceCardDTO.setModel(maintenanceCard.getModel());
        maintenanceCardDTO.setColor(maintenanceCard.getColor());
        maintenanceCardDTO.setCoordinator(userConverter.convertToDTO(maintenanceCard.getCoordinator()));
        maintenanceCardDTO.setId(maintenanceCard.getId());
        maintenanceCardDTO.setCreatedDate(maintenanceCard.getCreatedDate());
        return maintenanceCardDTO;
    }
    public MaintenanceCard convertToEntity(MaintenanceCardDTO maintenanceCardDTO) {
        ModelMapper modelMapper = new ModelMapper();
        MaintenanceCard maintenance= modelMapper.map(maintenanceCardDTO,MaintenanceCard.class);
        return maintenance;
    }
    public List<MaintenanceCardDTO> convertToMaintenanceCardDTOList(List<MaintenanceCard> maintenanceCardList){
        List<MaintenanceCardDTO> list = new ArrayList<>();
        for (MaintenanceCard maintenanceCard: maintenanceCardList
        ) {
            list.add(convertToDTO(maintenanceCard));
        }

        return list;
    }
    public List<MaintenanceCardDTO> convertToCoordinatorWarrantyCardsDTO(List<MaintenanceCard> coordinatorWarrantyCardsList){
        List<MaintenanceCardDTO> list = new ArrayList<>();
        coordinatorWarrantyCardsList.forEach(maintenanceCard -> {
            System.out.println(maintenanceCard);
            list.add(convertToDTO(maintenanceCard));

        });
        return list;
    }

    public List<MaintenanceCard> convertToMaintenanceCardDTOListEntity(List<MaintenanceCardDTO> maintenanceCardList){
        List<MaintenanceCard> list = new ArrayList<>();
        maintenanceCardList.forEach(maintenanceCard ->{
            System.out.println(maintenanceCard);
            list.add(convertToEntity(maintenanceCard));
        });
        System.out.println("convertToMaintenanceCardDTOListEntity Size: "+list.size());
        return list;
    }
    public List<MaintenanceCard> convertToCoordinatorWarrantyCardsDTOEntity(List<MaintenanceCardDTO> coordinatorWarrantyCardsList){
        List<MaintenanceCard> list = new ArrayList<>();
        coordinatorWarrantyCardsList.forEach(maintenanceCard -> list.add(convertToEntity(maintenanceCard)));
        return list;
    }
    public MaintenanceCardDTO convertAllToDTO(MaintenanceCard maintenanceCard) {
        MaintenanceCardDTO maintenanceCardDTO = convertToDTO(maintenanceCard);
        List<MaintenanceCardDetailDTO> maintenanceCardDetailDTOS = new ArrayList<>();
        List<MaintenanceCardDetailStatusHistoryDTO> maintenanceCardDetailStatusHistoryDTOS = new ArrayList<>();
        System.out.println(maintenanceCard.getMaintenanceCardDetails().size());
        for (MaintenanceCardDetail maintenanceCardDetail: maintenanceCard.getMaintenanceCardDetails()) {
            if(maintenanceCardDetail.getIsDelete() == 0) {
                MaintenanceCardDetailDTO maintenanceCardDetailDTO = new MaintenanceCardDetailDTO();
                maintenanceCardDetailDTO.setPrice(maintenanceCardDetail.getPrice());
                ProductDTO productDTO = new ProductDTO();
                Product product = maintenanceCardDetail.getProduct();
                productDTO.setCode(product.getCode());
                productDTO.setName(product.getName());
                productDTO.setPricePerUnit(product.getPricePerUnit());
                productDTO.setType(product.getType());
                if(product.getType() == 1){
                    productDTO.setQuantity(product.getQuantity());
                }
                productDTO.setId(product.getId());
                maintenanceCardDetailDTO.setProduct(productDTO);
                maintenanceCardDetailDTO.setQuantity(maintenanceCardDetail.getQuantity());
                maintenanceCardDetailDTO.setStatus(maintenanceCardDetail.getStatus());
                maintenanceCardDetailDTO.setCreatedDate(maintenanceCardDetail.getCreatedDate());
                maintenanceCardDetailDTO.setId(maintenanceCardDetail.getId());
                maintenanceCardDetailDTO.setModifiedDate(maintenanceCardDetail.getModifiedDate());
                maintenanceCardDetailDTOS.add(maintenanceCardDetailDTO);
            }
            List<MaintenanceCardDetailStatusHistory> maintenanceCardDetailStatusHistories = maintenanceCardDetail.getMaintenanceCardDetailStatusHistories();
            if(maintenanceCardDetailStatusHistories != null){
                for(MaintenanceCardDetailStatusHistory maintenanceCardDetailStatusHistory: maintenanceCardDetailStatusHistories){
                    MaintenanceCardDetailStatusHistoryDTO maintenanceCardDetailStatusHistoryDTO = new MaintenanceCardDetailStatusHistoryDTO();
                    maintenanceCardDetailStatusHistoryDTO.setName(maintenanceCardDetailStatusHistory.getMaintenanceCardDetail().getProduct().getName());
                    maintenanceCardDetailStatusHistoryDTO.setStatus(maintenanceCardDetailStatusHistory.getStatus());
                    maintenanceCardDetailStatusHistoryDTO.setCreatedDate(maintenanceCardDetailStatusHistory.getCreatedDate());
                    maintenanceCardDetailStatusHistoryDTO.setId(maintenanceCardDetailStatusHistory.getId());
                    maintenanceCardDetailStatusHistoryDTO.setModifiedDate(maintenanceCardDetailStatusHistory.getModifiedDate());
                    maintenanceCardDetailStatusHistoryDTOS.add(maintenanceCardDetailStatusHistoryDTO);
                }
            }
        }
        maintenanceCardDTO.setMaintenanceCardDetailStatusHistories(maintenanceCardDetailStatusHistoryDTOS);
        List<PaymentHistoryDTO> paymentHistoryDTOS = new ArrayList<>();
        if(maintenanceCard.getPaymentHistories() != null){
            for(PaymentHistory paymentHistory : maintenanceCard.getPaymentHistories()){
                PaymentHistoryDTO paymentHistoryDTO = new PaymentHistoryDTO();
                paymentHistoryDTO.setMoney(paymentHistory.getMoney());
                PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
                PaymentMethod paymentMethod = paymentHistory.getPaymentMethod();
                paymentMethodDTO.setName(paymentMethod.getName());
                paymentHistoryDTO.setPaymentMethod(paymentMethodDTO);
                paymentHistoryDTO.setCreatedDate(paymentHistory.getCreatedDate());
                paymentHistoryDTO.setModifiedDate(paymentHistory.getModifiedDate());
                paymentHistoryDTO.setId(paymentHistory.getId());
                paymentHistoryDTOS.add(paymentHistoryDTO);
            }
            maintenanceCardDTO.setPaymentHistories(paymentHistoryDTOS);
        }
        maintenanceCardDTO.setMaintenanceCardDetails(maintenanceCardDetailDTOS);
        return maintenanceCardDTO;
    }

}
