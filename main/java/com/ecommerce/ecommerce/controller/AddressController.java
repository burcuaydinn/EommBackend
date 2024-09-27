package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.AddressRequest;
import com.ecommerce.ecommerce.dto.response.AddressResponse;
import com.ecommerce.ecommerce.enums.AddressType;
import com.ecommerce.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")


public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/add")
    public AddressResponse addAddress(@RequestParam Long userId,
                                      @RequestParam AddressType addressType,
                                      @RequestParam String street,
                                      @RequestParam String city,
                                      @RequestParam String country,
                                      @RequestParam String postalCode) {

        AddressRequest request = new AddressRequest(addressType, street, city, country, postalCode);
        return addressService.addAddress(userId, request);
    }



    @GetMapping("/list/{userId}")
    public List<AddressResponse> getUserAddresses(@PathVariable Long userId) {
        return addressService.getUserAddresses(userId);
    }
}
