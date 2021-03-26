package com.sapo.qlsc.repository;

import com.sapo.qlsc.entity.MaintenanceCardDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MaintenanceCardDetailRepository extends JpaRepository< MaintenanceCardDetail,Long> {

    @Modifying
    @Query(value = "UPDATE maintenance_card_details SET is_delete = 1 WHERE (id = :id );\n", nativeQuery = true)
    public void deleteById(@Param("id") Long id);
}
