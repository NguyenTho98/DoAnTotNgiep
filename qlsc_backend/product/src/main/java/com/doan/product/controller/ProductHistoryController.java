package com.doan.product.controller;

import com.doan.product.service.ProductHistoryService;
import com.doan.product.dto.ProductHistoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class ProductHistoryController {

    private final ProductHistoryService productHistoryService;

    @GetMapping("/history")
    public Page<ProductHistoryDTO> getAll(int page,int size){
        return productHistoryService.getAll(page,size);
    }

}
