package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ComputerFeature {

    @EmbeddedId
    ComputerFeatureID id;

    @ManyToOne
    @JoinColumn(name = "computerID", insertable = false, updatable = false)
    private Computer computer;

    @ManyToOne@JoinColumn(name = "featureID", insertable = false, updatable = false)
    private Feature feature;

}
