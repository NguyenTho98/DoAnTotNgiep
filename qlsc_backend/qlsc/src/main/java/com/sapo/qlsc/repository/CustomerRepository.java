package com.sapo.qlsc.repository;

import com.sapo.qlsc.entity.Customer;
import com.sapo.qlsc.entity.MaintenanceCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long> {

    Customer findOneByCode(String code);

    @Query("SELECT c FROM Customer c")
    Page<Customer> findAllCustomer(Pageable pageable);

    @Query(value = "SELECT CONVERT(SUBSTRING(code, 4), UNSIGNED INTEGER ) AS newcode FROM customers WHERE code LIKE 'kh00%' ORDER BY newcode DESC LIMIT 1 offset :index", nativeQuery = true)
    String getMaxCode(@Param("index") int index);

    @Query("SELECT c FROM Customer c WHERE c.status = 1 AND (c.name LIKE %?1%"
            + " OR c.code LIKE %?1%"
            + " OR c.address LIKE %?1%"
            + " OR c.email LIKE %?1%"
            + " OR c.phoneNumber LIKE %?1%)")
//            + " OR CONCAT( c.createdDate,'') LIKE %?1%"
//            + " OR CONCAT( c.modifiedDate,'') LIKE %?1%")
    Page<Customer> search(Pageable pageable, String keyWork);

    @Query(value = "SELECT DISTINCT c FROM Customer c, MaintenanceCard m WHERE c.id = m.customer.id AND m.payStatus IN ?1")
    Page<Customer> filterPayStatusOfCustomer(Pageable pageable,byte[] payStatus);

    @Query(value = "SELECT c FROM Customer c WHERE c.phoneNumber = ?1 ")
    Customer checkPhoneNumber(String phoneNumber);
}
