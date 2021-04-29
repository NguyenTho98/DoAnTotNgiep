package com.doan.product.service.impl;

import com.doan.product.entity.ProductHistory;
import com.doan.product.exception.NotANumberException;
import com.doan.product.exception.commonException.UnknownException;
import com.doan.product.exception.productException.InvalidImageTypeException;
import com.doan.product.exception.productException.ProductNotFoundException;
import com.doan.product.model.ProductRequest;
import com.doan.product.repository.ProductHistoryRepository;
import com.doan.product.repository.ProductRepository;
import com.doan.product.service.ImageService;
import com.doan.product.service.ProductService;
import com.doan.product.converter.ProductConverter;
import com.doan.product.dto.ProductDTO;
import com.doan.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        String maxCode = fetchedCode.get(0);
        long codeNumber = Long.parseLong(maxCode);
        if (maxCode == null) {
            codeNumber = 1;
        } else {
            String codeNumberString = ""; // ""
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
    public ProductDTO save(ProductRequest productRequest) throws Exception {
        MultipartFile fileOptional = productRequest.getImage();
        String codeOptional = productRequest.getCode();
        String name = productRequest.getName();
        Integer quantityOptional = productRequest.getQuantity();
        String unitOptional = productRequest.getUnit();
        String pricePerUnitOptional = productRequest.getPricePerUnit();
        String description = productRequest.getDescription();
        String type = productRequest.getType();
        Product product = new Product();
        // Upload Image
        if (fileOptional != null) {
            MultipartFile file = fileOptional;
            String imageName = imageService.insertImage(file);
            if (imageName == null) {
                throw new UnknownException();
            }
            product.setImage(imageName);
        }

        // Generate code
        String code;
        if (codeOptional == null || codeOptional.isEmpty()) {
            code = createNewCode();
        } else {
            code = codeOptional;
            if (isCodeExist(code)) {
                throw new Exception("This code has already existed");
            }
        }
        product.setCode(code);
        // Get Current date
        Date date = new Date();
        // Get product data
        if (isNameExist(name)) {
            throw new Exception("This name has already existed");
        }
        product.setName(name);
        if (quantityOptional != null) {
            product.setQuantity(quantityOptional);
        }
        if (unitOptional != null) {
            product.setUnit(unitOptional);
        }
        if (pricePerUnitOptional != null) {
            product.setPricePerUnit(new BigDecimal(pricePerUnitOptional));
        }
        product.setDescription(description);
        product.setCreatedDate(date);
        product.setModifiedDate(date);
        product.setStatus((byte) 1);
        if (!StringUtils.isNumeric(type)) {
            throw new NotANumberException("Type is invalid");
        }
        product.setType((byte) (Integer.parseInt(type)));

        Product savedProduct = productRepository.save(product);
        if (product.getType() == 1) {
            Date now = new Date();
            ProductHistory productHistory = new ProductHistory();
            productHistory.setAmountChargeInUnit(savedProduct.getQuantity());
            productHistory.setName(savedProduct.getName());
            productHistory.setNote("Thêm sản phẩm");
            productHistory.setProductId(savedProduct.getId());
            productHistory.setStockRemain(savedProduct.getQuantity());
            productHistory.setCreatedDate(now);
            productHistory.setModifiedDate(now);
            productHistoryRepository.save(productHistory);
        }
        return productConverter.convertToDTO(savedProduct);

    }

    @Override
    public ProductDTO update(ProductRequest productRequest, Long id) throws Exception {
        Product product = productRepository.getOne(id);
        MultipartFile fileOptional = productRequest.getImage();
        String codeOptional = productRequest.getCode();
        String name = productRequest.getName();
        Integer quantityOptional = productRequest.getQuantity();
        String unitOptional = productRequest.getUnit();
        String pricePerUnitOptional = productRequest.getPricePerUnit();
        Byte statusOptional = productRequest.getStatus();
        String description = productRequest.getDescription();
        String type = productRequest.getType();
        int quantityNum = product.getQuantity();
        // Upload new Image (OPTIONAL)
        if (fileOptional != null) {
            MultipartFile file = fileOptional;
            String[] types = {"image/png", "image/jpg", "image/jpeg"};
            // Upload image and update product image name
            if (checkImage(file.getContentType())) {
                String imageName = imageService.insertImage(file);
                if (imageName == null) {
                    throw new UnknownException();
                }
                product.setImage(imageName);
            } else {
                throw new InvalidImageTypeException("Invalid image");
            }
        }

        if (codeOptional != null) {
            String code = codeOptional;
            if (isCodeExistToUpdate(code, id)) {
                throw new Exception("This code has already existed");
            }
            product.setCode(code);
        }

        // Update product info
        if (quantityOptional != null) {
            Integer quantity = quantityOptional;
            if (quantity > 0) {
                product.setQuantity(quantity);
            } else {
                throw new NotANumberException("The entered quantity is not a number");
            }
        }
        product.setId(id);
        if (isNameExistToUpdate(name, id)) {
            throw new Exception("This name has already existed");
        }
        product.setName(name);
        if (unitOptional != null) {
            String unit = unitOptional;
            product.setUnit(unit);
        }
        if (pricePerUnitOptional != null) {
            String pricePerUnit = pricePerUnitOptional;
            product.setPricePerUnit(new BigDecimal((Integer.parseInt(pricePerUnit))));
        }
        product.setDescription(description);
        if (statusOptional != null) {
            product.setStatus((byte) statusOptional);
        }
        if (!StringUtils.isNumeric(type)) {
            throw new NotANumberException("Type is invalid");
        }
        product.setType((byte) (Integer.parseInt(type)));
        try {
            Product savedProduct = productRepository.save(product);
            if (product.getType() == 1 && quantityNum != savedProduct.getQuantity()) {
                Date now = new Date();
                ProductHistory productHistory = new ProductHistory();
                productHistory.setAmountChargeInUnit(savedProduct.getQuantity() - quantityNum);
                productHistory.setName(savedProduct.getName());
                productHistory.setNote("Cập nhật số lượng");
                productHistory.setProductId(savedProduct.getId());
                productHistory.setStockRemain(savedProduct.getQuantity());
                productHistory.setCreatedDate(now);
                productHistory.setModifiedDate(now);
                productHistoryRepository.save(productHistory);
            }
            return productConverter.convertToDTO(savedProduct);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean checkImage(String value) {
        String[] arr = {
            "image/png",
            "image/jpeg",
            "image/jpg"
        };
        for (String ele : arr) {
            if (value.equals(ele)) {
                return true;
            }
        }
        return false;
    }
}
