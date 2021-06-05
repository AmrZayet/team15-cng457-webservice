package com.example.demo.Service;

import com.example.demo.Repository.FeatureRepository;
import com.example.demo.entity.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureService {
    @Autowired
    FeatureRepository featureRepository;

    public Feature saveFeature(Feature f) {
        return featureRepository.save(f);
    }

    public List<Feature> saveFeatures(List<Feature> features) {
        return featureRepository.saveAll(features);
    }

    public Feature getFeature(int id) {
        return featureRepository.findById(id).orElse(null);
    }

    public List<Feature> getFeatures() {
        return featureRepository.findAll();
    }

}
