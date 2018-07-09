/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scaffolds.maven.web.springbootdbplayground.commandline;

import com.scaffolds.maven.web.springbootdbplayground.model.Address;
import com.scaffolds.maven.web.springbootdbplayground.model.Apartments;
import com.scaffolds.maven.web.springbootdbplayground.model.PropertyType;
import com.scaffolds.maven.web.springbootdbplayground.model.Rental;
import com.scaffolds.maven.web.springbootdbplayground.model.Role;
import com.scaffolds.maven.web.springbootdbplayground.model.User;
import com.scaffolds.maven.web.springbootdbplayground.repository.AddressRepository;
import com.scaffolds.maven.web.springbootdbplayground.repository.ApartmentsRepository;
import com.scaffolds.maven.web.springbootdbplayground.repository.UserRepository;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author 1
 */

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    ApartmentsRepository appRepository;
    
    @Autowired
    AddressRepository addrRepository;
    
    @Override
    public void run(String... strings) throws Exception {
        userRepository.save(generateUser("one_own", Role.ROLE_ADMIN));
        userRepository.save(generateUser("two_own", Role.ROLE_CUSTOMER));
        userRepository.save(generateUser("three_own", Role.ROLE_PROPERTY_OWNER));
        
        userRepository.save(generateUser("one_cust", Role.ROLE_ADMIN));
        userRepository.save(generateUser("two_cust", Role.ROLE_CUSTOMER));
        userRepository.save(generateUser("three_cust", Role.ROLE_PROPERTY_OWNER));
        
        Address addr1 = generateAddress();
        Address addr2 = generateAddress();
        Address addr3 = generateAddress();
        Address addr4 = generateAddress();
        Address addr5 = generateAddress();
        Address addr6 = generateAddress();
        Address addr7 = generateAddress();
        Address addr8 = generateAddress();
        Address addr9 = generateAddress();
        Address addr10 = generateAddress();
        Address addr11 = generateAddress();
        Address addr12 = generateAddress();
        addrRepository.save(addr1);
        addrRepository.save(addr2);
        addrRepository.save(addr3);
        addrRepository.save(addr4);
        addrRepository.save(addr5);
        addrRepository.save(addr6);
        addrRepository.save(addr7);
        addrRepository.save(addr8);
        addrRepository.save(addr9);
        addrRepository.save(addr10);
        addrRepository.save(addr11);
        addrRepository.save(addr12);
        /*
        addressRepository.save(toAddress(apartmentVM.getAddress()));
        List<String> imgPaths = saveImages(apartmentVM.getImages());
        apartmentsRepository.save(toApartments(apartmentVM, imgPaths));
        */
        appRepository.save(generateApartment(addr1, "title1", "desc1", "10$"));
        appRepository.save(generateApartment(addr2, "title2", "desc2", "20$"));
        appRepository.save(generateApartment(addr3, "title3", "desc3", "30$"));
        appRepository.save(generateApartment(addr4, "title4", "desc4", "40$"));
        appRepository.save(generateApartment(addr5, "title5", "desc5", "50$"));
        appRepository.save(generateApartment(addr6, "title6", "desc6", "60$"));
        appRepository.save(generateApartment(addr7, "title7", "desc7", "70$"));
        appRepository.save(generateApartment(addr8, "title8", "desc8", "80$"));
        appRepository.save(generateApartment(addr9, "title9", "desc9", "90$"));
        appRepository.save(generateApartment(addr10, "title10", "desc10", "100$"));
        appRepository.save(generateApartment(addr11, "title11", "desc11", "110$"));
        appRepository.save(generateApartment(addr12, "title12", "desc12", "120$"));
        
        
        System.out.println("Count: " + userRepository.count());
        Iterable<User> users = userRepository.findAll();
        users.forEach(user -> System.out.println(user.getId() + " " + user.getUsername() + " " + user.getPassword()));
    
        System.out.println("============================================================");
        
        System.out.println("Count: " + appRepository.count());
        Iterable<Apartments> apps = appRepository.findAll();
        apps.forEach(app -> System.out.println(app.getId() + " " + app.getTitle() + " " + app.getDescription() + " " + app.getType()));
    }
    
    private User generateUser(String name, Role role) {
        User user = new User();
        user.setUsername(name);
        //user.setRegistrationDate(new Date(1999, 12, 21));
        user.setPassword(new BCryptPasswordEncoder().encode("password123"));
        user.setRole(role);
        user.setEmail(name + "@gmail.com");
        return user;
    }
    
    private Rental generateRental(User customer, User owner, Apartments app) {
        Rental ren = new Rental();
        ren.setApartmentId(app.getId());
        ren.setApartmentOwnerId(owner.getId());
        ren.setCustomerId(customer.getId());
        return ren;
    }
    
    private Address generateAddress() {
        Address addr = new Address();
        addr.setApartmentNumber("4a");
        addr.setCity("city1");
        addr.setCountry("ctr1");
        addr.setStreet("str1");
        addr.setZip("zip1");
        return addr;
    }
    
    private Apartments generateApartment(Address addr, String title, String desc, String price) {
        Apartments app = new Apartments();
        app.setAddress(addr);
        app.setPrice(price);
        app.setDescription(desc);
        app.setRoomNumber(4);
        app.setTitle(title);
        app.setType(PropertyType.FLAT);
        app.setImgLinks(Arrays.asList("assets/img.png", "assets/img.png", "assets/img.png"));
        return app;
    }
}
