package com.ecommerce.project.springbootecom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 4, message = "Street name must be atleast 4 characters")
    @Column(name="street")
    private String street;

    @NotBlank
    @Size(min = 5, message = "Building name must be atleast 5 characters")
    @Column(name="building_name")
    private String buildingName;

    @NotBlank
    @Size(min = 4 ,message = "city name must be atleast 4 characters")
    @Column(name="city")
    private String city;

    @NotBlank
    @Size(min = 5,  message = "state name must be atleast 5 characters")
    @Column(name="state")
    private String state;

    @NotBlank
    @Size(min = 6, message = "Pincode  must be atleast 6 characters")
    @Column(name="pincode")
    private String pincode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users=new ArrayList<>();

    public Address(String street, String buildingName, String city, String state, String pincode) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }
}