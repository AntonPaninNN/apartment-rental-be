/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scaffolds.maven.web.springbootdbplayground.controller;

import com.scaffolds.maven.web.springbootdbplayground.service.ApartmentService;
import com.scaffolds.maven.web.springbootdbplayground.viewmodel.AddressVM;
import com.scaffolds.maven.web.springbootdbplayground.viewmodel.ApartmentVM;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1
 */

@RestController
@RequestMapping("/api/apartments")
public class ApartmentsController {
    
    private ApartmentService apartmentsService;
    
    @Autowired
    public ApartmentsController(ApartmentService apartmentsService) {
        this.apartmentsService = apartmentsService;
    }
    
    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<?> getAllApartments() {
        List<ApartmentVM> apartments = apartmentsService.findAll();
        return new ResponseEntity<>(apartments, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getapartments", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getApartments(@RequestParam("page") int page,
                                           @RequestParam("count") int count,
                                           @RequestBody AddressVM address) {
        List<ApartmentVM> apartments = apartmentsService.findApartmentByAddress(address, page, count);
        return new ResponseEntity<>(apartments, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/createapartment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createApartment(@RequestBody ApartmentVM apartment) {
        apartmentsService.createApartment(apartment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/deleteapartment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE) 
    public ResponseEntity<?> deleteApartment(@RequestBody ApartmentVM apartment) {
        apartmentsService.deleteApartment(apartment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
