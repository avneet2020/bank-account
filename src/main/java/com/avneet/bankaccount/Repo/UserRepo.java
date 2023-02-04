package com.avneet.bankaccount.Repo;

import com.avneet.bankaccount.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
