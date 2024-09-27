package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.AddressRequest;
import com.ecommerce.ecommerce.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {

    // Adres ekle
    AddressResponse addAddress(Long userId, AddressRequest request);

    // Adres güncelle
    AddressResponse updateAddress(Long addressId, AddressRequest request);

    // Adres sil
    void deleteAddress(Long addressId);

    // Kullanıcının tüm adreslerini listele
    List<AddressResponse> getUserAddresses(Long userId);
}
