package com.nk.product.repository;

import com.nk.product.entity.Product;
import com.nk.product.entity.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHistoryRepository  extends JpaRepository<ProductHistory, Long> {
}
