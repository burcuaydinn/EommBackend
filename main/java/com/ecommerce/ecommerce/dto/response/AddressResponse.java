package com.ecommerce.ecommerce.dto.response;

import com.ecommerce.ecommerce.enums.AddressType;

public record AddressResponse(Long id, AddressType addressType, String street, String city, String country, String postalCode) {}
