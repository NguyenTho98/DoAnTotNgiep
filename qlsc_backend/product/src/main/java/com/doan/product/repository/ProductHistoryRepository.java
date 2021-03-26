package com.doan.product.repository;

import com.doan.product.entity.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHistoryRepository  extends JpaRepository<ProductHistory, Long> {
}
