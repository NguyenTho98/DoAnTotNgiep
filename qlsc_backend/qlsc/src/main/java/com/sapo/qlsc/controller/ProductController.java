package com.sapo.qlsc.controller;

import com.sapo.qlsc.converter.ProductConverter;
import com.sapo.qlsc.dto.ProductDTO;
import com.sapo.qlsc.entity.Product;
import com.sapo.qlsc.exception.NotANumberException;
import com.sapo.qlsc.exception.productException.InvalidImageTypeException;
import com.sapo.qlsc.exception.productException.ProductNotFoundException;
import com.sapo.qlsc.model.ProductRequest;
import com.sapo.qlsc.service.ProductService;
import com.sapo.qlsc.upload.Image;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("admin")
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductConverter productConverter;

    @GetMapping("products")
    public ResponseEntity<Page<ProductDTO>> getAll(@RequestParam(value = "search", required = false) Optional<String> search, Pageable pageable) {
        Page<ProductDTO> productDTOs = productService.getAll("", pageable);
        if (search.isPresent()) {
            productDTOs = productService.getAll(search.get(), pageable);
        }
        return new ResponseEntity<Page<ProductDTO>>(productDTOs, HttpStatus.OK);
    }

    // Type 1
    @GetMapping("accessories")
    public ResponseEntity<Page<ProductDTO>> getAllAccessories(@RequestParam(value = "search", required = false) Optional<String> search, Pageable pageable) {
        Page<ProductDTO> productDTOs = productService.getAllAccessories("", pageable);
        if (search.isPresent()) {
            productDTOs = productService.getAllAccessories(search.get(), pageable);
        }
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    // Type 2
    @GetMapping("services")
    public ResponseEntity<Page<ProductDTO>> getAllServices(@RequestParam(value = "search", required = false) Optional<String> search, Pageable pageable) {
        Page<ProductDTO> productDTOs = productService.getAllServices("", pageable);
        if (search.isPresent()) {
            productDTOs = productService.getAllServices(search.get(), pageable);
        }
        return new ResponseEntity<Page<ProductDTO>>(productDTOs, HttpStatus.OK);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductDTO> getOne(@PathVariable("id") String pathId, @RequestParam(value = "type", required = false) Optional<String> typeOptional) throws ProductNotFoundException {
        Long id = Long.parseLong(pathId);
        ProductDTO productDTO = productService.getOneById(id);
        if (typeOptional.isPresent()) {
            Byte type = Byte.parseByte(typeOptional.get());
            productDTO = productService.getOneByIdAndType(id, type);
        }
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

    @PostMapping("products")
    public ResponseEntity<ProductDTO> create(ProductRequest productRequest) throws Exception {
        // Create the product
        ProductDTO productDTO = productService.save(productRequest);
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "products/image/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody
    ResponseEntity<byte[]> getImage(HttpServletResponse response, @PathVariable("imageName") String imageName) throws IOException {
        byte[] imageBytes = productService.getImageByte(response, imageName);
        return new ResponseEntity<byte[]>(imageBytes, HttpStatus.OK);
    }

    @PutMapping("products/{id}")
    public ResponseEntity<ProductDTO> update(ProductRequest productRequest,
            @PathVariable("id") String pathId) throws Exception {

        // check if path id is numeric and check its existence
        if (!StringUtils.isNumeric(pathId)) {
            throw new NotANumberException("Invalid product id: the id is not a number");
        }
        Long id = Long.parseLong(pathId);
        // Save product info
        ProductDTO returnedProductDTO = productService.update(productRequest,id);
        return new ResponseEntity<ProductDTO>(returnedProductDTO, HttpStatus.OK);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String pathId) throws Exception {
        // check if path id is numeric and check its existence
        if (!StringUtils.isNumeric(pathId)) {
            throw new NotANumberException("Invalid product id: the id is not a number");
        }
        Long id = Long.parseLong(pathId);
        productService.deleteById(id);
        return new ResponseEntity<String>("Deleted product with id " + pathId, HttpStatus.OK);
    }

    // multiple delete
    @PutMapping("products")
    public ResponseEntity<String> multipleDelete(@RequestBody Long[] idArray) {
        System.out.println(idArray);
        productService.multiDelete(idArray);
        return new ResponseEntity<String>("Products are deleted", HttpStatus.OK);
    }
}
