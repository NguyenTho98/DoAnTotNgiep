package com.sapo.qlsc.repository;

import com.sapo.qlsc.entity.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, Integer> {

    @Query(value = "FROM Ward w, District d WHERE w.district.code = d.code AND w.district.code = ?1")
    List<Ward> getWardByDistrict(String district);

}
