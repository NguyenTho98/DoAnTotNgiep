package com.doan.product.service.impl;

import com.doan.product.entity.ProductHistory;
import com.doan.product.repository.ProductHistoryRepository;
import com.doan.product.service.ProductHistoryService;
import com.doan.product.converter.ProductHistoryConverter;
import com.doan.product.dto.ProductHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductHistoryServiceImpl implements ProductHistoryService {

    @Autowired
    private ProductHistoryRepository productHistoryRepository;

    @Autowired
    private ProductHistoryConverter productHistoryConverter;

    @Override
    public Page<ProductHistoryDTO> getAll(int page,int size) {
        Pageable paging = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<ProductHistory> productHistories = productHistoryRepository.findAll(paging);
        return productHistories.map(productHistory -> productHistoryConverter.convertToDTO(productHistory));
    }
}
