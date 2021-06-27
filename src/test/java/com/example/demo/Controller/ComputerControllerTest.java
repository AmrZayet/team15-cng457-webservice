package com.example.demo.Controller;

import org.junit.jupiter.api.Test;
import com.example.demo.Controller.ComputerController;
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
import com.example.demo.entity.Computer;
import com.example.demo.Service.ComputerService;
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

class ComputerControllerTest {

    @InjectMocks
    ComputerController computerController;

    @Mock
    ComputerService computerService;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void getComputer() {
        int id=5;
        Computer std1 = new Computer(id, "Test1Brand","TestModel",5,"Test","Test",5,5,5);

        when(computerService.getComputer(id)).thenReturn(std1);

        Computer computeritem=computerController.getComputer(id);

        Assertions.assertEquals("Test1Brand", computeritem.getBrand());
        Assertions.assertEquals("TestModel", computeritem.getModel());
        Assertions.assertEquals(5, computeritem.getScreenSize());
    }


    @Test
    void getComputers() {
        Computer std1 = new Computer(1, "Test1Brand","Test",5,"Test","Test",5,5,5);
        Computer std2 = new Computer(2, "Test2Brand","Test2Model",5,"Test2","Test2",5,5,5);
        List<Computer> computers = new ArrayList<>();
        computers.add(std1);
        computers.add(std2);

        when(computerService.getComputers()).thenReturn(new ArrayList(computers));

        List<Computer> computerlist = computerController.getComputers();

        Assertions.assertEquals(2, computerlist.size());
        Assertions.assertEquals("Test2Model", computerlist.get(computerlist.size()-1).getModel());
        Assertions.assertEquals("Test1Brand", computerlist.get(computerlist.size()-2).getBrand());
    }
}

