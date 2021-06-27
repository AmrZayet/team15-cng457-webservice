package com.example.demo.Controller;

import com.example.demo.Service.ComputerService;
import com.example.demo.entity.Computer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.demo.Controller.PhoneController;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.demo.DemoApplication;
import com.example.demo.entity.Phone;
import com.example.demo.Service.PhoneService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PhoneControllerTest {

    @InjectMocks
    PhoneController phoneController;

    @Mock
    PhoneService phoneService;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    @Test
    void getPhone() {
        int id=20;
        Phone std1 = new Phone(id, "Test1Brand","Test1Model",5,5,5);

        when(phoneService.getPhone(id)).thenReturn(std1);

        Phone phoneitem=phoneController.getPhone(id);

        Assertions.assertEquals("Test1Brand", phoneitem.getBrand());
        Assertions.assertEquals("Test1Model", phoneitem.getModel());
        Assertions.assertEquals(5, phoneitem.getScreenSize());
    }


    @Test
    void getPhones() {
        Phone std1 = new Phone(1, "Test1Brand","Test1Model",5,5,5);
        Phone std2 = new Phone(2, "Test2Brand","Test2Model",5,5,5);
        List<Phone> phones = new ArrayList<>();
        phones.add(std1);
        phones.add(std2);

        when(phoneService.getPhones()).thenReturn(new ArrayList(phones));

        List<Phone> phonelist = phoneController.getPhones();

        Assertions.assertEquals(2, phonelist.size());
        Assertions.assertEquals("Test2Model", phonelist.get(phonelist.size()-1).getModel());
        Assertions.assertEquals("Test1Brand", phonelist.get(phonelist.size()-2).getBrand());
    }
}


