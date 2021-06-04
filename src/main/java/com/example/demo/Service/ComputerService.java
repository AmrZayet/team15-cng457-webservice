package com.example.demo.Service;

import com.example.demo.Repository.ComputerRepository;
import com.example.demo.entity.Computer;
import com.example.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {
    @Autowired
    ComputerRepository computerRepository;

    public Computer saveComputer(Computer c) {
        return computerRepository.save(c);
    }

    public List<Computer> saveComputers(List<Computer> computers) {
        return computerRepository.saveAll(computers);
    }

    public Computer getComputer(int id) {
        return computerRepository.findById(id).orElse(null);
    }

    public List<Computer> getComputerByBrand(String brand) {
//        return courseRepository.getCourseByName(name);
//        return courseRepository.findBycourseNameContains(name);
        return computerRepository.findBybrandContains(brand);
    }

    public List<Computer> getComputers() {
        return computerRepository.findAll();
    }

    public String deleteComputer(int computerID) {
        computerRepository.deleteById(computerID);
        return String.format("Computer %d is deleted", computerID);
    }



}
