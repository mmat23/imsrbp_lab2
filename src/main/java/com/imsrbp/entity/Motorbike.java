package com.imsrbp.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "motorbikes")
public class Motorbike {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "motorbike_id")
    private Long motorbikeId;

    @Column
    private String model;

    @Column
    private String brand;

    @Column(name = "engine_type")
    private String engineType;

    @Column
    private Long cost;

    public Motorbike(){ }

    public Motorbike(String model, String brand, String engineType, Long cost){
        this.model = model;
        this.brand = brand;
        this.engineType = engineType;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Motorbike{" +
                "model=" + model +
                ", brand='" + brand + '\'' +
                ", cost='" + cost + '\'' +
                ", engineType='" + engineType + '\'' +
                '}';
    }
}

