package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


    List<Address> findByUserId(Long userId);

    // Kullanıcının belirli adres tipini (ev veya iş) bul
    @Query("SELECT a FROM Address a WHERE a.user.id = :userId AND a.addressType = :addressType")
    List<Address> findUserAddressesByType(Long userId, String addressType);
}
