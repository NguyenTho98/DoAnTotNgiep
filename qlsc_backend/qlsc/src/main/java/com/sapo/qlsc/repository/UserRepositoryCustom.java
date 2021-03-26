package com.sapo.qlsc.repository;

import com.sapo.qlsc.dto.UserDTO;
import com.sapo.qlsc.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface UserRepositoryCustom {

    public List<Map<String,Object>> getTotalMaintenanceCardByRepairman(int page, int size,String key);
    public int countTotalElements(String key);
    public Map<String,Object> getListUser(int page,int size,String key);
    public  List<Map<String, Object>> getTotalMaintenanceCardUser(int page,int size,String sortBy,String descending,String search);
    public int countTotalElementsUser(String key);
}
