package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "featureID")
@Table(name="Feature")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="featureID")
    private int featureID;
    private String featureName;



    @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL)
    private List<ComputerFeature> computers;

    @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL)
    private List<PhoneFeature> phones;


}
