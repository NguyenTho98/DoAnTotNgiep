package com.doan.product.service.impl;

import com.doan.product.entity.ProductHistory;
import com.doan.product.exception.NotANumberException;
import com.doan.product.exception.commonException.UnknownException;
import com.doan.product.exception.productException.InvalidImageTypeException;
import com.doan.product.exception.productException.ProductNotFoundException;
import com.doan.product.model.ProductRequest;
import com.doan.product.model.ProductResponse;
import com.doan.product.repository.ProductHistoryRepository;
import com.doan.product.repository.ProductRepository;
import com.doan.product.service.ImageService;
import com.doan.product.service.ProductService;
import com.doan.product.converter.ProductConverter;
import com.doan.product.dto.ProductDTO;
import com.doan.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final ImageService imageService;
    private final ProductHistoryRepository productHistoryRepository;

    @Override
    public Page<ProductDTO> getAll(String search, Pageable pageable) {
        Page<Product> products = productRepository.findAllByStatusNotAndNameContainingIgnoreCaseOrStatusNotAndCodeContainingIgnoreCaseOrderByModifiedDateDesc((byte) 0, search, (byte) 0, search, pageable);
        return products.map(product -> productConverter.convertToDTO(product));
    }

    @Override
    public Page<ProductDTO> getAllAccessories(String search, Pageable pageable) {
        Page<Product> products = productRepository.findAllByTypeAndStatusNotAndNameContainingIgnoreCaseOrTypeAndStatusNotAndCodeContainingIgnoreCaseOrderByModifiedDateDesc((byte) 1, (byte) 0, search, (byte) 1, (byte) 0, search, pageable);
        return products.map(productConverter::convertToDTO);
    }

    @Override
    public Page<ProductDTO> getAllServices(String search, Pageable pageable) {
        Page<Product> products = productRepository.findAllByTypeAndStatusNotAndNameContainingIgnoreCaseOrTypeAndStatusNotAndCodeContainingIgnoreCaseOrderByModifiedDateDesc((byte) 2, (byte) 0, search, (byte) 2, (byte) 0, search, pageable);
        return products.map(productConverter::convertToDTO);
    }

    @Override
    public String createNewCode() {
        StringBuilder newCode = new StringBuilder("sp00");
        List<String> fetchedCode = productRepository.getMaxCode();
        long codeNumber;
        if (StringUtils.isBlank(fetchedCode.get(0))) {
            return newCode.append(1).toString();
        } else {
            codeNumber = Long.parseLong(fetchedCode.get(0));
            String codeNumberString;
            do {
                newCode = new StringBuilder("sp00");
                codeNumber++;
                codeNumberString = Long.toString(codeNumber);
                newCode.append(codeNumberString);
            }
            while (this.isCodeExist(newCode.toString()));
        }
        return newCode.toString();
    }

    @Override
    public byte[] getImageByte(HttpServletResponse response, String imageName) throws IOException {
        Optional<Product> productOptional = productRepository.findByImage(imageName);
        if (productOptional.isEmpty()) {
            throw new IOException("Image not found");
        }
        Product product = productOptional.get();
        String directory = "product-image\\";
        File file = new File(directory + product.getImage());
        byte[] imageBytes = new byte[(int) file.length()];
        if (file.exists()) {
            String contentType = "image/png";
            response.setContentType(contentType);
            OutputStream out = response.getOutputStream();
            FileInputStream in = new FileInputStream(file);
            IOUtils.copy(in, out);
            out.close();
            in.close();
        }
        return imageBytes;
    }

    @Override
    public ProductDTO getOneById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found with id " + id);
        }
        Product product = productOptional.get();
        if (product.getStatus() == 0) {
            throw new ProductNotFoundException("Product not found with id " + id);
        }
        return productConverter.convertToDTO(product);
    }

    @Override
    public void deleteById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found with id " + id);
        }
        Product product = productOptional.get();
        product.setStatus((byte) 0);
        productRepository.save(product);
        if (product.getType() == 1) {
            Date now = new Date();
            ProductHistory productHistory = new ProductHistory();
            productHistory.setAmountChargeInUnit(-product.getQuantity());
            productHistory.setName(product.getName());
            productHistory.setNote("Xóa sản phẩm");
            productHistory.setProductId(product.getId());
            productHistory.setStockRemain(0);
            productHistory.setCreatedDate(now);
            productHistory.setModifiedDate(now);
            productHistoryRepository.save(productHistory);
        }
    }

    @Override
    public boolean isCodeExist(String code) {
        Optional<String> codeOptional = productRepository.findByCode(code);
        return codeOptional.isPresent();
    }

    @Override
    public boolean isCodeExistToUpdate(String code, Long id) {
        Optional<String> codeOptional = productRepository.findCodeByCodeAndIdNot(code, id);
        return codeOptional.isPresent();
    }

    @Override
    public ProductDTO getOneByIdAndType(Long id, Byte type) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findByIdAndType(id, type);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found!");
        }
        Product product = productOptional.get();
        return productConverter.convertToDTO(product);
    }

    @Override
    public boolean isNameExist(String name) {
        Optional<String> nameOptional = productRepository.findNameByName(name);
        return nameOptional.isPresent();
    }

    @Override
    public boolean isNameExistToUpdate(String name, Long id) {
        Optional<String> nameOptional = productRepository.findNameByNameAndIdNot(name, id);
        return nameOptional.isPresent();
    }

    @Override
    public void multiDelete(Long[] idArray) {
        productRepository.multipleDelete(idArray);
    }

    @Override
    public ProductResponse save(ProductRequest productReq) {
        Product product = new Product();
        if (StringUtils.isNotBlank(productReq.getCode())
            && isCodeExist(productReq.getCode())) {
            return new ProductResponse(Boolean.FALSE, "Mã sp đã tồn tại");
        }
        if (StringUtils.isNotBlank(productReq.getCode())) {
            product.setCode(productReq.getCode());
        } else {
            product.setCode(createNewCode());
        }
        product.setImage(productReq.getImage());
        product.setName(productReq.getName());
        product.setQuantity(productReq.getQuantity());
        product.setUnit(productReq.getUnit());
        product.setPricePerUnit(new BigDecimal(productReq.getPricePerUnit()));
        product.setDescription(productReq.getDescription());
        product.setType(productReq.getType());
        product.setCreatedDate(new Date());
        product.setModifiedDate(new Date());
        product.setStatus((byte) 1);
        try {
            Product savedProduct = productRepository.save(product);
            if (product.getType() == 1) {
                ProductHistory productHistory = new ProductHistory();
                productHistory.setAmountChargeInUnit(savedProduct.getQuantity());
                productHistory.setName(savedProduct.getName());
                productHistory.setNote("Thêm sản phẩm");
                productHistory.setProductId(savedProduct.getId());
                productHistory.setStockRemain(savedProduct.getQuantity());
                productHistory.setCreatedDate(new Date());
                productHistory.setModifiedDate(new Date());
                productHistoryRepository.save(productHistory);
            }
        } catch (Exception e) {
            return new ProductResponse(Boolean.FALSE, "false");
        }
        return new ProductResponse(Boolean.TRUE, "success");

    }

    @Override
    public ProductResponse update(ProductRequest productReq, Long id) throws Exception {
        Product product = productRepository.getOne(id);
        if (ObjectUtils.isEmpty(product)) {
            new ProductResponse(Boolean.FALSE, "Sản phẩm không tồn tại");
        }
        if (StringUtils.isNotBlank(productReq.getCode())
            && isCodeExist(productReq.getCode())) {
            return new ProductResponse(Boolean.FALSE, "Mã sp đã tồn tại");
        }
        if (StringUtils.isNotBlank(productReq.getCode())) {
            product.setCode(productReq.getCode());
        }
        product.setImage(productReq.getImage());
        product.setName(productReq.getName());
        product.setQuantity(productReq.getQuantity());
        product.setUnit(productReq.getUnit());
        product.setPricePerUnit(new BigDecimal(productReq.getPricePerUnit()));
        product.setStatus(productReq.getStatus());
        product.setDescription(productReq.getDescription());
        product.setType(productReq.getType());
        try {
            Product savedProduct = productRepository.save(product);
            if (product.getType() == 1 && product.getQuantity() != savedProduct.getQuantity()) {
                Date now = new Date();
                ProductHistory productHistory = new ProductHistory();
                productHistory.setAmountChargeInUnit(savedProduct.getQuantity() - product.getQuantity());
                productHistory.setName(savedProduct.getName());
                productHistory.setNote("Cập nhật số lượng");
                productHistory.setProductId(savedProduct.getId());
                productHistory.setStockRemain(savedProduct.getQuantity());
                productHistory.setCreatedDate(now);
                productHistory.setModifiedDate(now);
                productHistoryRepository.save(productHistory);
            }
            return new ProductResponse(Boolean.TRUE, "success");
        } catch (Exception e) {
            return new ProductResponse(Boolean.FALSE, "false");
        }
    }

}
