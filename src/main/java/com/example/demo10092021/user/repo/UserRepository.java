package com.example.demo10092021.user.repo;

import com.example.demo10092021.user.model.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<EndUser, Object> {

    @Query(name = "SELECT * FROM end_user e WHERE e.username = :string",nativeQuery = true)
    EndUser findByUsername(String string);

}
