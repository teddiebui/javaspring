package com.example.demo10092021.user.repo;

import com.example.demo10092021.user.model.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<UserAuthority, String> {

    UserAuthority findByName(String name);

    List<UserAuthority> findAll();
}
