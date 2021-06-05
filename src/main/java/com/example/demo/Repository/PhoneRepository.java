package com.example.demo.Repository;

import com.example.demo.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    @Query("SELECT c from Phone c where c.brand LIKE %?1%")
    public List<Phone> getPhonesByBrand(String brand);



}
