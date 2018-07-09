/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scaffolds.maven.web.springbootdbplayground.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author 1
 */
@Entity
@Table(name="Apartments")
public class Apartments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="apartment_ids")
    private Long id;

    @OneToOne/*(cascade = CascadeType.ALL, fetch = FetchType.LAZY)*/
    @JoinColumn(name="address_id")
    private Address address;

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setImgLinks(List<String> imgLinks) {
        this.imgLinks = imgLinks;
    }

    public Address getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public List<String> getImgLinks() {
        return imgLinks;
    }
    
    @Column(name="Price")
    private String price;

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }
    
    @Column(name="Title")
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    
    @Column(name="PropertyType")
    @Enumerated(EnumType.STRING)
    private PropertyType type;

    public void setType(PropertyType type) {
        this.type = type;
    }

    public PropertyType getType() {
        return type;
    }
    
    @Column(name="Description")
    private String description;
    
    @Column(name="RoomNum")
    private int roomNumber;
    
    @ElementCollection
    @CollectionTable(name="image_links", joinColumns = @JoinColumn(name="apartment_ids"))
    @Column(name="image_links")
    private List<String> imgLinks;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
