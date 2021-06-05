package com.example.demo.Controller;

import com.example.demo.Service.ComputerFeatureService;
import com.example.demo.Service.ComputerService;
import com.example.demo.Service.FeatureService;
import com.example.demo.entity.Computer;
import com.example.demo.entity.ComputerFeature;
import com.example.demo.entity.Feature;
import com.example.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComputerController {

    @Autowired
    ComputerService computerService;

    @Autowired
    FeatureService featureService;

    @Autowired
    ComputerFeatureService computerFeatureService;

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

    @PostMapping("/addComputerReview/{id}")
    public Computer addComputerReview(@PathVariable int id, @RequestBody Review r) {
        return computerService.addReview(id, r);
    }
    
    // if name contains spaces it should be represented as %20
    @GetMapping("/updateComputer/{id}/feature={name}")
    public Computer updateComputerFeature(@PathVariable int id, @PathVariable String name) {
        Computer computer = computerService.getComputer(id);
        Feature feature = featureService.getFeatureByfeatureName(name); // get the first one (it should be one
        int featureId;
        if (feature == null) {
            feature = new Feature();
            feature.setFeatureName(name);
            featureService.saveFeature(feature);
            featureId = featureService.getFeatureByfeatureName(name).getFeatureID();
        }
        else {
            featureId = feature.getFeatureID();
        }
        computerFeatureService.saveComputerFeature(id, featureId);
        return computerService.getComputer(id);
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
