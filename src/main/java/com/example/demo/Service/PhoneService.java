package com.example.demo.Service;

import com.example.demo.Repository.PhoneRepository;
import com.example.demo.entity.Phone;
import com.example.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /*Delete Phone*/ /*new*/
    public String deletePhone(int phoneID) {
        phoneRepository.deleteById(phoneID);
        return String.format("Phone %d is deleted", phoneID);
    }


    /*Searching criteria /*new*/

    public List<Phone> filterPhonesBase(List<Phone> phones, String criteria) {
        if (criteria.equals("")) {
            return null;
        }

        Stream<Phone> phoneStream = phones.stream();

        String [] itemsArray = criteria.split("\\s*&\\s*");
        List<String> searchCriterias = Arrays.asList(itemsArray);


        for(String sc: searchCriterias) {
            String [] arr = sc.split("\\s*=\\s*");
            List<String> oneCriteria = Arrays.asList(arr);

            String searchAttribute = oneCriteria.get(0);
            String AttributeDetails = oneCriteria.get(1);



            if(searchAttribute.equals("brand")) { //ok
                phoneStream = phoneStream.filter(comp -> comp.getBrand().contains(AttributeDetails));
            }
            else if(searchAttribute.equals("model")) { //ok
                phoneStream = phoneStream.filter(comp -> comp.getModel().contains(AttributeDetails));
            }

            else if(searchAttribute.equals("phoneID")) {
                phoneStream = phoneStream.filter(comp -> comp.getPhoneID() == Integer.parseInt(AttributeDetails));
            }
            else if(searchAttribute.equals("screenSize")) { //ok
                float tmpScreenSize = Float.parseFloat(oneCriteria.get(2));

                if (AttributeDetails.equals("eq")) {
                    phoneStream = phoneStream.filter(comp -> comp.getScreenSize() == tmpScreenSize);
                }
                else if (AttributeDetails.equals("ne")) {
                    phoneStream = phoneStream.filter(comp -> comp.getScreenSize() != tmpScreenSize);
                }
                else if (AttributeDetails.equals("lt")) {
                    phoneStream = phoneStream.filter(comp -> comp.getScreenSize() < tmpScreenSize);
                }
                else if (AttributeDetails.equals("le")) {
                    phoneStream = phoneStream.filter(comp -> comp.getScreenSize() <= tmpScreenSize);
                }
                else if (AttributeDetails.equals("gt")) {
                    phoneStream = phoneStream.filter(comp -> comp.getScreenSize() > tmpScreenSize);
                }
                else if (AttributeDetails.equals("ge")) {
                    phoneStream = phoneStream.filter(comp -> comp.getScreenSize() >= tmpScreenSize);
                }
                else if (AttributeDetails.equals("bt")) {
                    phoneStream = phoneStream.filter(comp -> (comp.getScreenSize() >= tmpScreenSize && comp.getScreenSize() <= Float.parseFloat(oneCriteria.get(3))));
                }
                else {
                    return null;
                }
            }
            else if(searchAttribute.equals("Internalmemory")) { //ok
                int tmpMemory = Integer.parseInt(oneCriteria.get(2));

                if (AttributeDetails.equals("eq")) {
                    phoneStream = phoneStream.filter(comp -> comp.getInternalmemory() == tmpMemory);
                }
                else if (AttributeDetails.equals("ne")) {
                    phoneStream = phoneStream.filter(comp -> comp.getInternalmemory() != tmpMemory);
                }
                else if (AttributeDetails.equals("lt")) {
                    phoneStream = phoneStream.filter(comp -> comp.getInternalmemory() < tmpMemory);
                }
                else if (AttributeDetails.equals("le")) {
                    phoneStream = phoneStream.filter(comp -> comp.getInternalmemory() <= tmpMemory);
                }
                else if (AttributeDetails.equals("gt")) {
                    phoneStream = phoneStream.filter(comp -> comp.getInternalmemory() > tmpMemory);
                }
                else if (AttributeDetails.equals("ge")) {
                    phoneStream = phoneStream.filter(comp -> comp.getInternalmemory() >= tmpMemory);
                }
                else if (AttributeDetails.equals("bt")) {
                    phoneStream = phoneStream.filter(comp -> (comp.getInternalmemory() >= tmpMemory && comp.getInternalmemory() <= Integer.parseInt(oneCriteria.get(3))));
                }
                else {
                    return null;
                }
            }
            else if(searchAttribute.equals("price")) { //ok
                float tmpPrice = Float.parseFloat(oneCriteria.get(2));

                if (AttributeDetails.equals("eq")) {
                    phoneStream = phoneStream.filter(comp -> comp.getPrice() == tmpPrice);
                }
                else if (AttributeDetails.equals("ne")) {
                    phoneStream = phoneStream.filter(comp -> comp.getPrice() != tmpPrice);
                }
                else if (AttributeDetails.equals("lt")) {
                    phoneStream = phoneStream.filter(comp -> comp.getPrice() < tmpPrice);
                }
                else if (AttributeDetails.equals("le")) {
                    phoneStream = phoneStream.filter(comp -> comp.getPrice() <= tmpPrice);
                }
                else if (AttributeDetails.equals("gt")) {
                    phoneStream = phoneStream.filter(comp -> comp.getPrice() > tmpPrice);
                }
                else if (AttributeDetails.equals("ge")) {
                    phoneStream = phoneStream.filter(comp -> comp.getPrice() >= tmpPrice);
                }
                else if (AttributeDetails.equals("bt")) {
                    phoneStream = phoneStream.filter(comp -> (comp.getPrice() >= tmpPrice && comp.getPrice() <= Float.parseFloat(oneCriteria.get(3))));
                }
                else {
                    return null;
                }
            }

        }

        List<Phone> searchResults = phoneStream.collect(Collectors.toList());

        return searchResults;
    }


}
