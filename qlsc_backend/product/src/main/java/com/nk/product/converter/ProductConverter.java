package com.nk.product.converter;


import com.nk.product.dto.ProductDTO;
import com.nk.product.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    private final ModelMapper modelmapper = new ModelMapper();
    public ProductDTO convertToDTO(Product product) {
//        return modelmapper.map(product, ProductDTO.class);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCode(product.getCode());
        productDTO.setUnit(product.getUnit());
        productDTO.setPricePerUnit(product.getPricePerUnit());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());
        productDTO.setCreatedDate(product.getCreatedDate());
        productDTO.setModifiedDate(product.getModifiedDate());
        productDTO.setStatus(product.getStatus());
        productDTO.setType(product.getType());
        return productDTO;
    }

    public Product convertToEntity(ProductDTO productDTO) {
        return modelmapper.map(productDTO, Product.class);
    }
}
