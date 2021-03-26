package com.nk.product.converter;

import com.nk.product.dto.ProductHistoryDTO;
import com.nk.product.entity.ProductHistory;
import org.springframework.stereotype.Component;

@Component
public class ProductHistoryConverter {

    public ProductHistoryDTO convertToDTO(ProductHistory productHistory){
        ProductHistoryDTO productHistoryDTO = new ProductHistoryDTO();
        productHistoryDTO.setAmountChargeInUnit(productHistory.getAmountChargeInUnit());
        productHistoryDTO.setName(productHistory.getName());
        productHistoryDTO.setNote(productHistory.getNote());
        productHistoryDTO.setProductId(productHistory.getProductId());
        productHistoryDTO.setStockRemain(productHistory.getStockRemain());
        productHistoryDTO.setCreatedDate(productHistory.getCreatedDate());
        productHistoryDTO.setId(productHistory.getId());
        productHistoryDTO.setModifiedDate(productHistory.getModifiedDate());
        return productHistoryDTO;
    }

}
