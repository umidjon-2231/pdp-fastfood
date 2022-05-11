package com.example.adminservice;

import com.example.adminservice.service.FilialService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminServiceApplicationTests {
    @Autowired
    FilialService filialService;
    @Test
    void contextLoads() {
    }

}
