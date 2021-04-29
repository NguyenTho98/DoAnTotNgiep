package com.doan.product.service;

import com.doan.product.exception.NotANumberException;
import com.doan.product.exception.productException.ProductNotFoundException;
import com.doan.product.model.ProductRequest;
import com.doan.product.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ProductService {

    Page<ProductDTO> getAll(String search, Pageable pageable);
    Page<ProductDTO> getAllAccessories(String search, Pageable pageable);
    Page<ProductDTO> getAllServices(String search, Pageable pageable);
    ProductDTO save(ProductRequest productRequest) throws Exception;
    ProductDTO update(ProductRequest productRequest, Long id) throws Exception;
    String createNewCode() throws NotANumberException;
    byte[] getImageByte(HttpServletResponse response, String imageName) throws IOException;
    ProductDTO getOneById(Long id) throws ProductNotFoundException;
    void deleteById(Long id) throws ProductNotFoundException;
    boolean isCodeExist(String code);
    boolean isCodeExistToUpdate(String code, Long id);
    ProductDTO getOneByIdAndType(Long id, Byte type) throws ProductNotFoundException;
    boolean isNameExist(String name);
    boolean isNameExistToUpdate(String name, Long id);
    void multiDelete(Long[] idArray);
}
