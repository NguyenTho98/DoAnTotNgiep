package com.nk.product.controller;

import com.nk.product.dto.ProductHistoryDTO;
import com.nk.product.service.ProductHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class ProductHistoryController {

    @Autowired
    private ProductHistoryService productHistoryService;


    @GetMapping("/history")
    public Page<ProductHistoryDTO> getAll(int page,int size){
        return productHistoryService.getAll(page,size);
    }

}
