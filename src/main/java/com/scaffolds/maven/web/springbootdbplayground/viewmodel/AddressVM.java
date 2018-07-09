/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scaffolds.maven.web.springbootdbplayground.viewmodel;

import java.io.Serializable;

/**
 *
 * @author 1
 */
public class AddressVM implements Serializable {

    private String country;
    private String city;
    private String street;
    private String apartmentNumber;
    private String zipCode;

    public AddressVM() {}
    public AddressVM(String country, String city, String street, String apartmentNumber, String zipCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.apartmentNumber = apartmentNumber;
        this.zipCode = zipCode;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public String getZipCode() {
        return zipCode;
    }
    
}
