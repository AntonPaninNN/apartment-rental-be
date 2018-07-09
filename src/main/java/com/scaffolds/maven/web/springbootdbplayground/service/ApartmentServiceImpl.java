/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scaffolds.maven.web.springbootdbplayground.service;

import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.scaffolds.maven.web.springbootdbplayground.model.Address;
import com.scaffolds.maven.web.springbootdbplayground.model.Apartments;
import com.scaffolds.maven.web.springbootdbplayground.model.PropertyType;
import com.scaffolds.maven.web.springbootdbplayground.repository.AddressRepository;
import com.scaffolds.maven.web.springbootdbplayground.repository.ApartmentsRepository;
import com.scaffolds.maven.web.springbootdbplayground.viewmodel.AddressVM;
import com.scaffolds.maven.web.springbootdbplayground.viewmodel.ApartmentVM;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 1
 */
@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {

    private AddressRepository addressRepository;
    private ApartmentsRepository apartmentsRepository;
    private static String UPLOAD_DIRECTORY = "//images//"; 
    
    @Autowired
    public ApartmentServiceImpl(AddressRepository addressRepository, ApartmentsRepository apartmentsRepository) {
        this.addressRepository = addressRepository;
        this.apartmentsRepository = apartmentsRepository;
    }
    
    @Override
    public void createApartment(ApartmentVM apartmentVM) {
        Address addr = toAddress(apartmentVM.getAddress());
        addressRepository.save(addr);
        List<String> imgPaths = saveImages(apartmentVM.getImages());
        apartmentsRepository.save(toApartments(addr, apartmentVM, imgPaths));
    }

    @Override
    public void deleteApartment(ApartmentVM apartmentVM) {
        Iterable<Apartments> apartments = apartmentsRepository.findAll();
        apartments = findCertainApartmentsByAddress(apartmentVM.getAddress(), apartments); 
        if (Iterables.size(apartments) > 1) {
            // TODO: ERROR CONDITION
        }
        addressRepository.delete(Iterables.get(apartments, 0).getAddress());
        apartmentsRepository.delete(Iterables.get(apartments, 0));
    }

    @Override
    public List<ApartmentVM> findAll() {
        Iterable<Apartments> apartments = apartmentsRepository.findAll(); 
        return modelToVM(apartments);
    }

    @Override
    public List<ApartmentVM> findApartmentByAddress(AddressVM addressVM, int page, int count) {
        Iterable<Apartments> apartments = apartmentsRepository.findAll();
        apartments = findCertainApartmentsByAddress(addressVM, apartments);
        apartments = Iterables.skip(apartments, page*count);
        apartments = Iterables.limit(apartments, count);
        return modelToVM(apartments);
    }
    
    private List<ApartmentVM> modelToVM(Iterable<Apartments> apartments) {
        List<ApartmentVM> apartmentsList = new ArrayList<ApartmentVM>();
        for(Apartments apartment : apartments) {
            apartmentsList.add(toApartmentsVM(apartment));
        }
        return apartmentsList;
    }
    
    private Iterable<Apartments> findCertainApartmentsByAddress(AddressVM addressVM, Iterable<Apartments> apartments) {
        List<Apartments> outputApartments = new ArrayList<Apartments>();
        Address address = toAddress(addressVM);
        Address apAddress = null; 
        for(Apartments apartment : apartments) {
            apAddress = apartment.getAddress(); 
            if ((Strings.isNullOrEmpty(address.getApartmentNumber()) || 
                    address.getApartmentNumber().equals(apAddress.getApartmentNumber())) &&
                (Strings.isNullOrEmpty(address.getCity()) || 
                    address.getCity().equals(apAddress.getCity())) &&
                (Strings.isNullOrEmpty(address.getCountry()) || 
                    address.getCountry().equals(apAddress.getCountry())) &&
                (Strings.isNullOrEmpty(address.getStreet()) ||
                    address.getStreet().equals(apAddress.getStreet())) &&
                (Strings.isNullOrEmpty(address.getZip()) ||
                    address.getZip().equals(apAddress.getZip())))
                outputApartments.add(apartment);
        }
        return outputApartments;
    }
    
    private Address toAddress(AddressVM addressVM) {
        Address address = new Address();
        address.setApartmentNumber(addressVM.getApartmentNumber());
        address.setCity(addressVM.getCity());
        address.setCountry(addressVM.getCountry());
        address.setStreet(addressVM.getStreet());
        address.setZip(addressVM.getZipCode());
        return address;
    }
    
    private AddressVM toAddressVM(Address address) {
        AddressVM addressVM = new AddressVM();
        addressVM.setApartmentNumber(address.getApartmentNumber());
        addressVM.setCity(address.getCity());
        addressVM.setCountry(address.getCountry());
        addressVM.setStreet(address.getCity());
        addressVM.setZipCode(address.getZip());
        return addressVM;
    }
    
    private Apartments toApartments(Address addr, ApartmentVM apartmentsVM, List<String> imgPaths) {
        Apartments apartments = new Apartments();
        apartments.setAddress(addr);
        apartments.setDescription(apartmentsVM.getDescription());
        apartments.setPrice(apartmentsVM.getPrice());
        apartments.setImgLinks(imgPaths);
        apartments.setRoomNumber(apartmentsVM.getRoomNumber());
        apartments.setTitle(apartmentsVM.getTitle());
        apartments.setType(PropertyType.valueOf(apartmentsVM.getPropertyType()));
        return apartments;
    }
    
    private ApartmentVM toApartmentsVM(Apartments apartments) {
        ApartmentVM apartmentVM = new ApartmentVM();
        apartmentVM.setAddress(toAddressVM(apartments.getAddress()));
        apartmentVM.setDescription(apartments.getDescription());
        apartmentVM.setImagePaths(Iterables.toArray(apartments.getImgLinks(), String.class));
        apartmentVM.setPropertyType(apartments.getType().toString());
        apartmentVM.setRoomNumber(apartments.getRoomNumber());
        apartmentVM.setPrice(apartments.getPrice());
        apartmentVM.setTitle(apartments.getTitle());
        return apartmentVM;
        
    }
    
    private List<String> saveImages(MultipartFile[] files) {
        List<String> imgPaths = new ArrayList<String>();
        if (files == null || files.length <= 0)
            return imgPaths;
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_DIRECTORY + file.getOriginalFilename());
                Files.write(path, bytes);
                imgPaths.add(path.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imgPaths;
    }
    
}
