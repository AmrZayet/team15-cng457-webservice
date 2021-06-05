package com.example.demo.Service;

import com.example.demo.Repository.ComputerFeatureRepository;
import com.example.demo.Repository.ComputerRepository;
import com.example.demo.Repository.FeatureRepository;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {
    @Autowired
    ComputerRepository computerRepository;
    @Autowired
    FeatureRepository featureRepository;
    @Autowired
    ComputerFeatureRepository computerFeatureRepository;


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

    public Computer addReview(int id, Review r) {
        Computer tmp = computerRepository.findById(id).orElse(null);
        if (tmp == null) {
            return null;
        }
        tmp.addReview(r);
        return computerRepository.save(tmp);
    }

    public Computer updateComputerFeature(int id, String name) {

        Computer computer = computerRepository.findById(id).orElse(null);
        List<Feature> featureList = featureRepository.findByfeatureNameContains(name);
        Feature feature;
        int featureId;
        if(featureList.size() == 0) {
            feature = null;
        }
        else
        {
            feature = featureList.get(0);
        }
        if (feature == null) {
            feature = new Feature();
            feature.setFeatureName(name);
            featureRepository.save(feature);
            featureId = featureRepository.findByfeatureNameContains(name).get(0).getFeatureID();
        }
        else {
            featureId = feature.getFeatureID();
        }

        ComputerFeature tmp = new ComputerFeature();
        tmp.setId(new ComputerFeatureID(id, featureId));
        computerFeatureRepository.save(tmp);
        return computerRepository.findById(id).orElse(null);
    }
}
