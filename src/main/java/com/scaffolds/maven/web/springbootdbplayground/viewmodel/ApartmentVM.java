/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scaffolds.maven.web.springbootdbplayground.viewmodel;

import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 1
 */
public class ApartmentVM implements Serializable {
    
    private String title;
    private String price;
    private String description;
    private int roomNumber;
    private String propertyType;
    private AddressVM address;
    private MultipartFile[] images;
    private String[] imagePaths;

    public ApartmentVM() {}
    
    public ApartmentVM(String title, String description, int roomNumber, 
            String propertyType, AddressVM address, MultipartFile[] images, String price) {
        this.title = title;
        this.description = description;
        this.roomNumber = roomNumber;
        this.propertyType = propertyType;
        this.address = address;
        this.images = images;
        this.price = price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }
    
    public void setImagePaths(String[] imagePaths) {
        this.imagePaths = imagePaths;
    }

    public String[] getImagePaths() {
        return imagePaths;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setAddress(AddressVM address) {
        this.address = address;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public AddressVM getAddress() {
        return address;
    }

    public MultipartFile[] getImages() {
        return images;
    }
    
}
