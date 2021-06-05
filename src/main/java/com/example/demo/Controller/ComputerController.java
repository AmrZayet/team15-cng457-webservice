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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

//    /getComputers/search/brand=Apple&screenSize=eq=17.0
    @GetMapping("/searchComputers/base/{criteria}")
    public List<Computer> SearchComputers(@PathVariable String criteria) {
        if (criteria.equals("")) {
            return null;
        }

        Stream<Computer> computerList = computerService.getComputers().stream();

        String [] itemsArray = criteria.split("\\s*&\\s*");
        List<String> searchCriterias = Arrays.asList(itemsArray);


        for(String sc: searchCriterias) {
            String [] arr = sc.split("\\s*=\\s*");
            List<String> oneCrieteria = Arrays.asList(arr);
//            System.out.println("\n\n");
//            for(String part: oneCrieteria) {
//                System.out.print(String.format("%s  -->", part));
//            }
//            System.out.println("\n\n");
            String searchAttribute = oneCrieteria.get(0);
            String AttributeDetails = oneCrieteria.get(1);


            if(searchAttribute.equals("brand")) {
                computerList = computerList.filter(comp -> comp.getBrand().contains(AttributeDetails));
            }
            else if(searchAttribute.equals("model")) {
                computerList = computerList.filter(comp -> comp.getModel().contains(AttributeDetails));
            }
            else if(searchAttribute.equals("screenResolution")) {
                computerList = computerList.filter(comp -> comp.getScreenResolution().contains(AttributeDetails));
            }
            else if(searchAttribute.equals("processor")) {
                computerList = computerList.filter(comp -> comp.getProcessor().contains(AttributeDetails));
            }
            else if(searchAttribute.equals("computerID")) {
                computerList = computerList.filter(comp -> comp.getComputerID() == Integer.parseInt(AttributeDetails));
            }
            else if(searchAttribute.equals("screenSize")) {
                float tmpScreenSize = Float.parseFloat(oneCrieteria.get(2));

                if (AttributeDetails.equals("eq")) {
                    computerList = computerList.filter(comp -> comp.getScreenSize() == tmpScreenSize);
                }
                else if (AttributeDetails.equals("ne")) {
                    computerList = computerList.filter(comp -> comp.getScreenSize() != tmpScreenSize);
                }
                else if (AttributeDetails.equals("lt")) {
                    computerList = computerList.filter(comp -> comp.getScreenSize() < tmpScreenSize);
                }
                else if (AttributeDetails.equals("le")) {
                    computerList = computerList.filter(comp -> comp.getScreenSize() <= tmpScreenSize);
                }
                else if (AttributeDetails.equals("gt")) {
                    computerList = computerList.filter(comp -> comp.getScreenSize() > tmpScreenSize);
                }
                else if (AttributeDetails.equals("ge")) {
                    computerList = computerList.filter(comp -> comp.getScreenSize() >= tmpScreenSize);
                }
                else if (AttributeDetails.equals("bt")) {
                    computerList = computerList.filter(comp -> (comp.getScreenSize() >= tmpScreenSize && comp.getScreenSize() <= Float.parseFloat(oneCrieteria.get(3))));
                }
                else {
                    return null;
                }
            }
            else if(searchAttribute.equals("memory")) {
                int tmpMemory = Integer.parseInt(oneCrieteria.get(2));

                if (AttributeDetails.equals("eq")) {
                    computerList = computerList.filter(comp -> comp.getMemory() == tmpMemory);
                }
                else if (AttributeDetails.equals("ne")) {
                    computerList = computerList.filter(comp -> comp.getMemory() != tmpMemory);
                }
                else if (AttributeDetails.equals("lt")) {
                    computerList = computerList.filter(comp -> comp.getMemory() < tmpMemory);
                }
                else if (AttributeDetails.equals("le")) {
                    computerList = computerList.filter(comp -> comp.getMemory() <= tmpMemory);
                }
                else if (AttributeDetails.equals("gt")) {
                    computerList = computerList.filter(comp -> comp.getMemory() > tmpMemory);
                }
                else if (AttributeDetails.equals("ge")) {
                    computerList = computerList.filter(comp -> comp.getMemory() >= tmpMemory);
                }
                else if (AttributeDetails.equals("bt")) {
                    computerList = computerList.filter(comp -> (comp.getMemory() >= tmpMemory && comp.getMemory() <= Integer.parseInt(oneCrieteria.get(3))));
                }
                else {
                    return null;
                }
            }
            else if(searchAttribute.equals("storageCapacity")) {
                float tmpStorageCapacity = Float.parseFloat(oneCrieteria.get(2));

                if (AttributeDetails.equals("eq")) {
                    computerList = computerList.filter(comp -> comp.getStorageCapacity() == tmpStorageCapacity);
                }
                else if (AttributeDetails.equals("ne")) {
                    computerList = computerList.filter(comp -> comp.getStorageCapacity() != tmpStorageCapacity);
                }
                else if (AttributeDetails.equals("lt")) {
                    computerList = computerList.filter(comp -> comp.getStorageCapacity() < tmpStorageCapacity);
                }
                else if (AttributeDetails.equals("le")) {
                    computerList = computerList.filter(comp -> comp.getStorageCapacity() <= tmpStorageCapacity);
                }
                else if (AttributeDetails.equals("gt")) {
                    computerList = computerList.filter(comp -> comp.getStorageCapacity() > tmpStorageCapacity);
                }
                else if (AttributeDetails.equals("ge")) {
                    computerList = computerList.filter(comp -> comp.getStorageCapacity() >= tmpStorageCapacity);
                }
                else if (AttributeDetails.equals("bt")) {
                    computerList = computerList.filter(comp -> (comp.getStorageCapacity() >= tmpStorageCapacity && comp.getStorageCapacity() <= Float.parseFloat(oneCrieteria.get(3))));
                }
                else {
                    return null;
                }
            }
            else if(searchAttribute.equals("price")) {
                float tmpPrice = Float.parseFloat(oneCrieteria.get(2));

                if (AttributeDetails.equals("eq")) {
                    computerList = computerList.filter(comp -> comp.getPrice() == tmpPrice);
                }
                else if (AttributeDetails.equals("ne")) {
                    computerList = computerList.filter(comp -> comp.getPrice() != tmpPrice);
                }
                else if (AttributeDetails.equals("lt")) {
                    computerList = computerList.filter(comp -> comp.getPrice() < tmpPrice);
                }
                else if (AttributeDetails.equals("le")) {
                    computerList = computerList.filter(comp -> comp.getPrice() <= tmpPrice);
                }
                else if (AttributeDetails.equals("gt")) {
                    computerList = computerList.filter(comp -> comp.getPrice() > tmpPrice);
                }
                else if (AttributeDetails.equals("ge")) {
                    computerList = computerList.filter(comp -> comp.getPrice() >= tmpPrice);
                }
                else if (AttributeDetails.equals("bt")) {
                    computerList = computerList.filter(comp -> (comp.getPrice() >= tmpPrice && comp.getPrice() <= Float.parseFloat(oneCrieteria.get(3))));
                }
                else {
                    return null;
                }
            }

//            System.out.println(String.format("\n\n\n %s  --> %s\n\n\n", searchAttribute, AttributeDetails));
        }

        List<Computer> searchResults = computerList.collect(Collectors.toList());

        return searchResults;
    }

    @DeleteMapping("/deleteComputer/{id}")
    public String deleteComputer(@PathVariable int id) {
        return computerService.deleteComputer(id);
    }

}
