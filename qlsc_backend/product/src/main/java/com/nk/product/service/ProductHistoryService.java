package com.nk.product.service;

import com.nk.product.dto.ProductDTO;
import com.nk.product.dto.ProductHistoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductHistoryService {

    public Page<ProductHistoryDTO> getAll(int page,int size);

}
