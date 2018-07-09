/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scaffolds.maven.web.springbootdbplayground.service;

import com.scaffolds.maven.web.springbootdbplayground.model.Apartments;
import com.scaffolds.maven.web.springbootdbplayground.viewmodel.AddressVM;
import com.scaffolds.maven.web.springbootdbplayground.viewmodel.ApartmentVM;
import java.util.List;

/**
 *
 * @author 1
 */
public interface ApartmentService {
    
    void createApartment(ApartmentVM apartmentVM);
    void deleteApartment(ApartmentVM apartmentVM);
    List<ApartmentVM> findAll();
    List<ApartmentVM> findApartmentByAddress(AddressVM addressVM, int page, int count);
    
}
