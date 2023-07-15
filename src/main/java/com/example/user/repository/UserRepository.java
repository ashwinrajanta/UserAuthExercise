package com.example.user.repository;

import com.example.user.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    Users findByEmail(String email);


//    @Modifying
//    @Query(value = "DELETE FROM user_details WHERE email = :email",nativeQuery = true) //
    int deleteByEmail(String email);


}
