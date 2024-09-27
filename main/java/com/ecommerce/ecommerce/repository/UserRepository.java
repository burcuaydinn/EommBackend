package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);


    Optional<User> findByEmail(String email);



    // Özelleştirilmiş query örneği: Kullanıcıları rolüne göre getir
    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findByRole(String role);
}
