package com.example.demo.Repository;

import com.example.demo.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComputerRepository extends JpaRepository<Computer, Integer> {
    public List<Computer> findBybrandContains(String brand);
}
