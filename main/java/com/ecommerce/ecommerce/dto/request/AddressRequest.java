package com.ecommerce.ecommerce.dto.request;

import com.ecommerce.ecommerce.enums.AddressType;

public record AddressRequest(AddressType addressType, String street, String city, String country, String postalCode) {

}
