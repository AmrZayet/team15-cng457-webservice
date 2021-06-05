package com.example.demo.Controller;

import com.example.demo.Service.ComputerService;
import com.example.demo.entity.Computer;
import com.example.demo.entity.ComputerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComputerController {

    @Autowired
    ComputerService computerService;

    @PostMapping("addComputer")
    public Computer addComputer(@RequestBody Computer c) {
//        List<ComputerFeature> computerFeatureList = c.getComputerFeatures();
//        for (ComputerFeature cf : computerFeatureList) {
//            System.out.println(String.format("computerID=%d, featureId=%d", cf.getId().getComputerID(), cf.getId().getFeatureID()));
//            System.out.println(String.format("featureId=%s",cf.toString()));
//        }
//        System.out.println(c.toString());
        c.setComputerFeatures(null);
        return computerService.saveComputer(c);
    }

    @PostMapping("/addComputers")
    public List<Computer> addFeatures(@RequestBody List<Computer> computers) {
        return computerService.saveComputers(computers);
    }

    @GetMapping("/getComputer/{id}")
    public Computer getComputer(@PathVariable int id) {
        return computerService.getComputer(id);
    }

    @GetMapping("/getComputer/brand={brand}")
    public List<Computer> getComputerByBrand(@PathVariable String brand) {
        return computerService.getComputerByBrand(brand);
    }

    @GetMapping("/getComputers")
    public List<Computer> getComputers() {
        return computerService.getComputers();
    }

    @DeleteMapping("/deleteComputer/{id}")
    public String deleteComputer(@PathVariable int id) {
        return computerService.deleteComputer(id);
    }

}
