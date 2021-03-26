package com.doan.product.service;

import com.doan.product.dto.ProductHistoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ProductHistoryService {

    public Page<ProductHistoryDTO> getAll(int page,int size);

}
