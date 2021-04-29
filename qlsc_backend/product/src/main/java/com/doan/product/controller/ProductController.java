package com.doan.product.controller;


import com.doan.product.exception.NotANumberException;
import com.doan.product.exception.productException.ProductNotFoundException;
import com.doan.product.model.ProductRequest;
import com.doan.product.service.ProductService;
import com.doan.product.converter.ProductConverter;
import com.doan.product.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    @GetMapping("products")
    public ResponseEntity<Page<ProductDTO>> getAll(@RequestParam(value = "search", required = false) String search, Pageable pageable) {
        Page<ProductDTO> productDTOs = productService.getAll("", pageable);
        if (StringUtils.isNotBlank(search)) {
            productDTOs = productService.getAll(search, pageable);
        }
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    // Type 1
    @GetMapping("accessories")
    public ResponseEntity<Page<ProductDTO>> getAllAccessories(@RequestParam(value = "search", required = false) String search, Pageable pageable) {
        Page<ProductDTO> productDTOs = productService.getAllAccessories("", pageable);
        if (StringUtils.isNotBlank(search)) {
            productDTOs = productService.getAllAccessories(search, pageable);
        }
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    // Type 2
    @GetMapping("services")
    public ResponseEntity<Page<ProductDTO>> getAllServices(@RequestParam(value = "search", required = false) String search, Pageable pageable) {
        Page<ProductDTO> productDTOs = productService.getAllServices("", pageable);
        if (StringUtils.isNotBlank(search)) {
            productDTOs = productService.getAllServices(search, pageable);
        }
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductDTO> getOne(@PathVariable("id") String pathId, @RequestParam(value = "type", required = false) String type) throws ProductNotFoundException {
        Long id = Long.parseLong(pathId);
        ProductDTO productDTO = productService.getOneById(id);
        if (StringUtils.isNotBlank(type)) {
            Byte newType = Byte.parseByte(type);
            productDTO = productService.getOneByIdAndType(id, newType);
        }
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping("products")
    public ResponseEntity<ProductDTO> create(ProductRequest productRequest) {
        ProductDTO productDTO = null;
        try {
            productDTO = productService.save(productRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "products/image/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody
    ResponseEntity<byte[]> getImage(HttpServletResponse response, @PathVariable("imageName") String imageName) throws IOException {
        byte[] imageBytes = productService.getImageByte(response, imageName);
        return new ResponseEntity<>(imageBytes, HttpStatus.OK);
    }

    @SneakyThrows
    @PutMapping("products/{id}")
    public ResponseEntity<ProductDTO> update(ProductRequest productRequest,
                                             @PathVariable("id") String pathId) {
        // check if path id is numeric and check its existence
        if (!StringUtils.isNumeric(pathId)) {
            try {
                throw new NotANumberException("Invalid product id: the id is not a number");
            } catch (NotANumberException e) {
                e.printStackTrace();
            }
        }
        Long id = Long.parseLong(pathId);
        // Save product info
        ProductDTO returnedProductDTO = productService.update(productRequest, id);
        return new ResponseEntity<>(returnedProductDTO, HttpStatus.OK);
    }

    @SneakyThrows
    @DeleteMapping("products/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String pathId) {
        // check if path id is numeric and check its existence
        if (!StringUtils.isNumeric(pathId)) {
            throw new NotANumberException("Invalid product id: the id is not a number");
        }
        Long id = Long.parseLong(pathId);
        productService.deleteById(id);
        return new ResponseEntity<>("Deleted product with id " + pathId, HttpStatus.OK);
    }

    // multiple delete
    @PutMapping("products")
    public ResponseEntity<String> multipleDelete(@RequestBody Long[] idArray) {
        productService.multiDelete(idArray);
        return new ResponseEntity<>("Products are deleted", HttpStatus.OK);
    }

    @GetMapping("test")
    public String testApi() {
        return "Success";
    }
}
