package com.DigiMarket.AgriHive.repo;

import com.DigiMarket.AgriHive.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    //Optional<User> fin(String phone);
}
