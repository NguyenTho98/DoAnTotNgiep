package com.sapo.qlsc;

import com.sapo.qlsc.dto.UserDTO;
import com.sapo.qlsc.entity.MaintenanceCard;
import com.sapo.qlsc.entity.User;
import com.sapo.qlsc.repository.MaintenanceCardRepository;
import com.sapo.qlsc.repository.UserRepository;
import com.sapo.qlsc.repository.UserRepositoryCustom;
import com.sapo.qlsc.repository.impl.UserRepositoryCustomImpl;
import com.sapo.qlsc.service.impl.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private MaintenanceCardRepository userRepositoryCustom;
    @Autowired UserServiceImpl userService;

@Test
    public void CheckLogin(){
//    System.out.println(userRepositoryCustom.getMaintenanceCardByPlatesNumber(Long.valueOf(41)));
}
}
