package com.nk.product.service;

import com.nk.product.dto.ProductDTO;
import com.nk.product.exception.NotANumberException;
import com.nk.product.exception.productException.ProductNotFoundException;
import com.nk.product.model.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ProductService {
    public Page<ProductDTO> getAll(String search, Pageable pageable);

    public Page<ProductDTO> getAllAccessories(String search, Pageable pageable);

    public Page<ProductDTO> getAllServices(String search, Pageable pageable);

    public ProductDTO save(ProductRequest productRequest) throws Exception;

    public ProductDTO update(ProductRequest productRequest, Long id) throws Exception;

    public String createNewCode() throws NotANumberException;

    public byte[] getImageByte(HttpServletResponse response, String imageName) throws IOException;

    public ProductDTO getOneById(Long id) throws ProductNotFoundException;

    public void deleteById(Long id) throws ProductNotFoundException;

    public boolean isCodeExist(String code);

    public boolean isCodeExistToUpdate(String code, Long id);

    public ProductDTO getOneByIdAndType(Long id, Byte type) throws ProductNotFoundException;

    public boolean isNameExist(String name);

    public boolean isNameExistToUpdate(String name, Long id);

    public void multiDelete(Long[] idArray);
}
