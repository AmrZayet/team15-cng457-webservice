package com.example.demo.Service;

import com.example.demo.Repository.PhoneRepository;
import com.example.demo.entity.Phone;
import com.example.demo.entity.PhoneFeature;
import com.example.demo.entity.Feature;
import com.example.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PhoneService {
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    FeatureService featureService;
    @Autowired
    PhoneFeatureService phoneFeatureService;

    private static Lock lock = new ReentrantLock(true);

    public Phone savePhone(Phone c) {
        lock.lock();
        Phone temp = phoneRepository.save(c);
        lock.unlock();
        return temp;
    }

    public List<Phone> savePhones(List<Phone> phones) {
        lock.lock();
        List<Phone> tempList = phoneRepository.saveAll(phones);
        lock.unlock();
        return tempList;
    }

    public Phone getPhone(int id) {
        lock.lock();
        Phone temp = phoneRepository.findById(id).orElse(null);
        lock.unlock();
        return temp;
    }

    public List<Phone> getPhoneByBrand(String brand) {
        lock.lock();
        List<Phone> tempList = phoneRepository.findBybrandContains(brand);
        lock.unlock();
        return tempList;
    }

    public List<Phone> getPhones() {
        lock.lock();
        List<Phone> tempList = phoneRepository.findAll();
        lock.unlock();
        return tempList;
    }

    public String deletePhone(int phoneID) {
        lock.lock();
        phoneRepository.deleteById(phoneID);
        lock.unlock();
        return String.format("Phone %d is deleted", phoneID);
    }

    public Phone addReview(int id, Review r) {
        Phone tmp = phoneRepository.findById(id).orElse(null);
        if (tmp == null) {
            return null;
        }
        tmp.addReview(r);
        return phoneRepository.save(tmp);
    }



    public List<Phone> filterPhonesBase(List<Phone> phones, String criteria) {
        if (criteria.equals("")) {
            return null;
        }

        Stream<Phone> phoneStream = phones.stream();

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
                float tmpScreenSize = Float.parseFloat(oneCrieteria.get(2));

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
                    phoneStream = phoneStream.filter(comp -> (comp.getScreenSize() >= tmpScreenSize && comp.getScreenSize() <= Float.parseFloat(oneCrieteria.get(3))));
                }
                else {
                    return null;
                }
            }
            else if(searchAttribute.equals("Internalmemory")) { //ok
                float tmpMemory = Float.parseFloat(oneCrieteria.get(2));

                if (AttributeDetails.equals("eq")) {
                    phoneStream = phoneStream.filter(comp -> comp.getInternalMemory() == tmpMemory);
                }
                else if (AttributeDetails.equals("ne")) {
                    phoneStream = phoneStream.filter(comp -> comp.getInternalMemory() != tmpMemory);
                }
                else if (AttributeDetails.equals("lt")) {
                    phoneStream = phoneStream.filter(comp -> comp.getInternalMemory() < tmpMemory);
                }
                else if (AttributeDetails.equals("le")) {
                    phoneStream = phoneStream.filter(comp -> comp.getInternalMemory() <= tmpMemory);
                }
                else if (AttributeDetails.equals("gt")) {
                    phoneStream = phoneStream.filter(comp -> comp.getInternalMemory() > tmpMemory);
                }
                else if (AttributeDetails.equals("ge")) {
                    phoneStream = phoneStream.filter(comp -> comp.getInternalMemory() >= tmpMemory);
                }
                else if (AttributeDetails.equals("bt")) {
                    phoneStream = phoneStream.filter(comp -> (comp.getInternalMemory() >= tmpMemory && comp.getInternalMemory() <= Float.parseFloat(oneCrieteria.get(3))));
                }
                else {
                    return null;
                }
            }

            else if(searchAttribute.equals("price")) { //ok
                float tmpPrice = Float.parseFloat(oneCrieteria.get(2));

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
                    phoneStream = phoneStream.filter(comp -> (comp.getPrice() >= tmpPrice && comp.getPrice() <= Float.parseFloat(oneCrieteria.get(3))));
                }
                else {
                    return null;
                }
            }


        }

        List<Phone> searchResults = phoneStream.collect(Collectors.toList());

        return searchResults;
    }

    public List<Phone> searchPhonesAdditional(String featureNamePart) {

        featureNamePart = featureNamePart.replace("-", " ");

        List<Feature> featureList = featureService.getFeatureWithNameContains(featureNamePart);
        System.out.println(String.format("\n\nfeature list count = %d\n\n", featureList.size()));

        List<Integer> featureIds = featureList.stream().map(Feature::getFeatureID).collect(Collectors.toList());
        System.out.println(String.format("\n\nfeature ids list count = %d\n\n", featureIds.size()));

        List<PhoneFeature> phoneFeatureList = phoneFeatureService.getPhoneFeatures();
        System.out.println(String.format("\n\n phone feature list count = %d\n\n", phoneFeatureList.size()));

        List<Integer> phoneIds = phoneFeatureList.stream().filter(feat -> featureIds.contains(feat.getId().getFeatureID())).map(PhoneFeature::getIdPhoneId).collect(Collectors.toList());
        System.out.println(String.format("\n\n phone list count = %d\n\n", phoneIds.size()));

        List<Phone> phoneList = getPhones();
        System.out.println(String.format("\n\n phone list count = %d\n\n", phoneList.size()));


        List<Phone> searchResult = phoneList.stream().filter(com -> phoneIds.contains(com.getPhoneID())).collect(Collectors.toList());
        System.out.println(String.format("\n\n search list count = %d\n\n", searchResult.size()));
        return searchResult;
    }}



