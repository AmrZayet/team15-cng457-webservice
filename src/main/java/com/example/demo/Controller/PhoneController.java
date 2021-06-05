package com.example.demo.Controller;

import com.example.demo.Service.PhoneService;
import com.example.demo.entity.Phone;
import com.example.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @PostMapping("addPhone")
    public Phone addPhone(@RequestBody Phone c) {
        return phoneService.savePhone(c);
    }

    @PostMapping("/addPhones")
    public List<Phone> addFeatures(@RequestBody List<Phone> phones) {
        return phoneService.savePhones(phones);
    }

    @GetMapping("/getPhone/{id}")
    public Phone getPhone(@PathVariable int id) {
        return phoneService.getPhone(id);
    }

    @GetMapping("/getPhones")
    public List<Phone> getComputers() {
        return phoneService.getPhones();
    }

    @PostMapping("/addPhoneReview/{id}")
    public Phone getComputers(@PathVariable int id, @RequestBody Review r) {
        return phoneService.addReview(id, r);
    }
    /*ddd*/
    @GetMapping("/getPhonesByBrand/{brand}")
    public List<Phone> getPhoneByBrand(@PathVariable String brand){
        return phoneService.getPhonesByBrand(brand);
    }






}