package com.example.demo.Service;

import com.example.demo.Repository.PhoneRepository;
import com.example.demo.entity.Phone;
import com.example.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {
    @Autowired
    PhoneRepository phoneRepository;

    /*Save a Phone*/
    public Phone savePhone(Phone c) {
        return phoneRepository.save(c);
    }

    /*Save Phones*/
    public List<Phone> savePhones(List<Phone> phones) {
        return phoneRepository.saveAll(phones);
    }

    /*Get a phone by ID*/
    public Phone getPhone(int id) {
        return phoneRepository.findById(id).orElse(null);
    }

    /*Get all phones*/
    public List<Phone> getPhones() {
        return phoneRepository.findAll();
    }

    /*Get all phones from a particular brand along with their details*/
    public List<Phone> getPhonesByBrand(String brand){
        return phoneRepository.getPhonesByBrand(brand);
    }

    /*Add Review to Phone*/
    public Phone addReview(int id, Review r) {
        Phone tmp = phoneRepository.findById(id).orElse(null);
        if (tmp == null) {
            return null;
        }
        tmp.addReview(r);
        return phoneRepository.save(tmp);
    }


}
