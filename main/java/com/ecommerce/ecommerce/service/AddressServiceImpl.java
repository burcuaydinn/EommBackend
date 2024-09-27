package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.AddressRequest;
import com.ecommerce.ecommerce.dto.response.AddressResponse;
import com.ecommerce.ecommerce.entity.Address;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repository.AddressRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private Long userId;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AddressResponse addAddress(Long userId, AddressRequest request) {
        User user = userRepository.findById(this.userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = new Address();
        address.setUser(user);
        address.setAddressType(request.addressType());  // AddressType enum kullanımı
        address.setStreet(request.street());
        address.setCity(request.city());
        address.setCountry(request.country());
        address.setPostalCode(request.postalCode());

        addressRepository.save(address);

        return new AddressResponse(address.getId(), address.getAddressType(), address.getStreet(), address.getCity(), address.getCountry(), address.getPostalCode());
    }

    @Override
    public AddressResponse updateAddress(Long addressId, AddressRequest request) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        address.setAddressType(request.addressType());
        address.setStreet(request.street());
        address.setCity(request.city());
        address.setCountry(request.country());
        address.setPostalCode(request.postalCode());

        addressRepository.save(address);

        return new AddressResponse(address.getId(), address.getAddressType(), address.getStreet(), address.getCity(), address.getCountry(), address.getPostalCode());
    }

    @Override
    public void deleteAddress(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        addressRepository.delete(address);
    }

    @Override
    public List<AddressResponse> getUserAddresses(Long userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        return addresses.stream()
                .map(address -> new AddressResponse(
                        address.getId(),
                        address.getAddressType(),
                        address.getStreet(),
                        address.getCity(),
                        address.getCountry(),
                        address.getPostalCode()))
                .collect(Collectors.toList());
    }
}
