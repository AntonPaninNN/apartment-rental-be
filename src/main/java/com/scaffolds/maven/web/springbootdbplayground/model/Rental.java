/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scaffolds.maven.web.springbootdbplayground.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author 1
 */
@Entity
@Table(name="ApartmentRental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="CustomerId")
    private Long customerId;

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public void setApartmentOwnerId(Long apartmentOwnerId) {
        this.apartmentOwnerId = apartmentOwnerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public Long getApartmentOwnerId() {
        return apartmentOwnerId;
    }
    
    @Column(name="ApartmentId")
    private Long apartmentId;
    
    @Column(name="OwnerId")
    private Long apartmentOwnerId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
