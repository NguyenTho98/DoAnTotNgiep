package com.nk.product.service.impl;

import com.nk.product.converter.ProductConverter;
import com.nk.product.dto.ProductDTO;
import com.nk.product.entity.Product;
import com.nk.product.entity.ProductHistory;
import com.nk.product.exception.NotANumberException;
import com.nk.product.exception.commonException.UnknownException;
import com.nk.product.exception.productException.InvalidImageTypeException;
import com.nk.product.exception.productException.ProductNotFoundException;
import com.nk.product.model.ProductRequest;
import com.nk.product.repository.ProductHistoryRepository;
import com.nk.product.repository.ProductRepository;
import com.nk.product.service.ImageService;
import com.nk.product.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductHistoryRepository productHistoryRepository;

    @Override
    public Page<ProductDTO> getAll(String search, Pageable pageable) {
        Page<Product> products = productRepository.findAllByStatusNotAndNameContainingIgnoreCaseOrStatusNotAndCodeContainingIgnoreCaseOrderByModifiedDateDesc((byte) 0, search, (byte) 0, search, pageable);
        return products.map(product -> productConverter.convertToDTO(product));
    }

    @Override
    public Page<ProductDTO> getAllAccessories(String search, Pageable pageable) {
//        Page<Product> products = productRepository.getProductWithType((byte)1, (byte)0, search, search, pageable);
        Page<Product> products = productRepository.findAllByTypeAndStatusNotAndNameContainingIgnoreCaseOrTypeAndStatusNotAndCodeContainingIgnoreCaseOrderByModifiedDateDesc((byte) 1, (byte) 0, search, (byte) 1, (byte) 0, search, pageable);
        return products.map(product -> productConverter.convertToDTO(product));
    }

    @Override
    public Page<ProductDTO> getAllServices(String search, Pageable pageable) {
//        Page<Product> products = productRepository.getProductWithType((byte)2, (byte)0, search, search, pageable);
        Page<Product> products = productRepository.findAllByTypeAndStatusNotAndNameContainingIgnoreCaseOrTypeAndStatusNotAndCodeContainingIgnoreCaseOrderByModifiedDateDesc((byte) 2, (byte) 0, search, (byte) 2, (byte) 0, search, pageable);
        return products.map(product -> productConverter.convertToDTO(product));
    }

    @Override
    public String createNewCode() throws NotANumberException {
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
//        StringBuilder code = new StringBuilder("sp00");
//        List<String> fetchedCode = productRepository.getMaxCode();
//        if (fetchedCode.size() == 0) {
//            return "sp001";
//        }
//        String maxCode = fetchedCode.get(0);
//        System.out.println(maxCode);
//        if (!StringUtils.isNumeric(maxCode)) {
//            throw new NotANumberException("Code is not a number");
//        }
//        long maxCodeNumber = Long.parseLong(maxCode);
//        long codeNumber = maxCodeNumber;
//        if (maxCode == null) {
//            maxCodeNumber = 1;
//        } else {
//            String codeNumberString = "";
//            do {
//                codeNumber++;
//                codeNumberString = Long.toString(codeNumber);
//                code.append(codeNumberString);
//            }
//            while(this.isCodeExist(code.toString()));
//        }
//        System.out.println(code.toString());
//        return code.toString();
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
            // copy from in to out
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
        if(product.getType() == 1){
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
        if (codeOptional.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isCodeExistToUpdate(String code, Long id) {
        Optional<String> codeOptional = productRepository.findCodeByCodeAndIdNot(code, id);
        if (codeOptional.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public ProductDTO getOneByIdAndType(Long id, Byte type) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findByIdAndType(id, type);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found!");
        }
        Product product = productOptional.get();
        ProductDTO productDTO = productConverter.convertToDTO(product);
        return productDTO;
    }

    @Override
    public boolean isNameExist(String name) {
        Optional<String> nameOptional = productRepository.findNameByName(name);
        if (nameOptional.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isNameExistToUpdate(String name, Long id) {
        Optional<String> nameOptional = productRepository.findNameByNameAndIdNot(name, id);
        if (nameOptional.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public void multiDelete(Long[] idArray) {
        productRepository.multipleDelete(idArray);
    }

    @Override
    public ProductDTO save(ProductRequest productRequest) throws Exception {
        Optional<MultipartFile> fileOptional = productRequest.getImage();
        Optional<String> codeOptional = productRequest.getCode();
        String name = productRequest.getName();
        Optional<String> quantityOptional = productRequest.getQuantity();
        Optional<String> unitOptional = productRequest.getUnit();
        Optional<String> pricePerUnitOptional = productRequest.getPricePerUnit();
        String description = productRequest.getDescription();
        String type = productRequest.getType();
        Product product = new Product();
        // Upload Image
        if (fileOptional != null && fileOptional.isPresent()) {
            MultipartFile file = fileOptional.get();
//            Image image = new Image(file);
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
            code = codeOptional.get();
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
        if (quantityOptional != null && quantityOptional.isPresent()) {
            product.setQuantity(Integer.parseInt(quantityOptional.get()));
        }
        if (unitOptional != null && unitOptional.isPresent()) {
            product.setUnit(unitOptional.get());
        }
        if (pricePerUnitOptional != null && pricePerUnitOptional.isPresent()) {
            product.setPricePerUnit(new BigDecimal(pricePerUnitOptional.get()));
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
        if(product.getType() == 1){
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
        Optional<MultipartFile> fileOptional = productRequest.getImage();
        Optional<String> codeOptional = productRequest.getCode();
        String name = productRequest.getName();
        Optional<String> quantityOptional = productRequest.getQuantity();
        Optional<String> unitOptional = productRequest.getUnit();
        Optional<String> pricePerUnitOptional = productRequest.getPricePerUnit();
        Optional<String> statusOptional = productRequest.getStatus();
        String description = productRequest.getDescription();
        String type = productRequest.getType();
        int quantityNum = product.getQuantity();
        // Upload new Image (OPTIONAL)
        if (fileOptional != null && fileOptional.isPresent()) {
            MultipartFile file = fileOptional.get();
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

        if (codeOptional != null && codeOptional.isPresent() ) {
            String code = codeOptional.get();
            if (isCodeExistToUpdate(code, id)) {
                throw new Exception("This code has already existed");
            }
            product.setCode(code);
        }

        // Update product info
        if (quantityOptional!=null && quantityOptional.isPresent()) {
            String quantity = quantityOptional.get();
            if (!StringUtils.isNumeric(quantity)) {
                throw new NotANumberException("The entered quantity is not a number");
            }
            if(Integer.parseInt(quantity) > 0 ){
                product.setQuantity((Integer.parseInt(quantity)));
            }
            else{
                throw new NotANumberException("The entered quantity is not a number");
            }
        }
        if (pricePerUnitOptional!=null && pricePerUnitOptional.isPresent()) {
            if (!StringUtils.isNumeric(pricePerUnitOptional.get())) {
                throw new NotANumberException("The entered price is not a number");
            }
        }
        product.setId(id);
        if (isNameExistToUpdate(name, id)) {
            throw new Exception("This name has already existed");
        }
        product.setName(name);
        if (unitOptional!=null && unitOptional.isPresent()) {
            String unit = unitOptional.get();
            product.setUnit(unit);
        }
        if (pricePerUnitOptional!= null && pricePerUnitOptional.isPresent()) {
            String pricePerUnit = pricePerUnitOptional.get();
            product.setPricePerUnit(new BigDecimal((Integer.parseInt(pricePerUnit))));
        }
        product.setDescription(description);
        if (statusOptional != null && statusOptional.isPresent()) {
            product.setStatus((byte) Integer.parseInt(statusOptional.get()));
        }
        if (!StringUtils.isNumeric(type)) {
            throw new NotANumberException("Type is invalid");
        }
        product.setType((byte) (Integer.parseInt(type)));
        try {
            Product savedProduct = productRepository.save(product);
            if(product.getType() == 1 && quantityNum != savedProduct.getQuantity()){
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
