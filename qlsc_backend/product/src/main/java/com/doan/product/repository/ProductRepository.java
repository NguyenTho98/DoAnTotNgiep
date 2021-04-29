package com.doan.product.repository;

import com.doan.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByStatusNotAndNameContainingIgnoreCaseOrStatusNotAndCodeContainingIgnoreCaseOrderByModifiedDateDesc(byte status1, String searchByName, byte status2, String searchByCode, Pageable pageable);
    Page<Product> findAllByTypeAndStatusNotAndNameContainingIgnoreCaseOrTypeAndStatusNotAndCodeContainingIgnoreCaseOrderByModifiedDateDesc(byte type, byte status, String search, byte type2, byte status2, String search2, Pageable pageable);
    @Query(value = "SELECT MAX(CONVERT(SUBSTRING(code, 4), UNSIGNED INTEGER)) as maxcode FROM products WHERE code LIKE 'sp%' LIMIT 1", nativeQuery = true)
    List<String> getMaxCode();
    Optional<Product> findByImage(String imageName);
    Optional<String> findByCode(String code);
    Optional<Product> findByIdAndType(Long id, Byte type);
    Optional<String> findNameByName(String name);
    Optional<String> findNameByNameAndIdNot(String name, Long id);
    Optional<String> findCodeByCodeAndIdNot(String code, Long id);
    @Modifying
    @Transactional
    @Query("UPDATE Product SET status = 0 WHERE id IN :idArray")
    void multipleDelete(@Param("idArray") Long[] idArray);
}
