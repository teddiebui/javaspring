package com.example.demo10092021.user.service;

import com.example.demo10092021.user.model.EndUser;
import com.example.demo10092021.user.dto.UserDTO;
import com.example.demo10092021.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserRepository getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        EndUser user = userRepo.findByUsername(username);
        if (user != null) {
            UserDTO userDTO = new UserDTO(user);
            return userDTO;
        }
        return null;
    }


    public UserDTO update(EndUser currentUser, EndUser newUser) {
        EndUser temp = userRepo.findById(currentUser.getId()).get();

        try {
            temp.setEmail(newUser.getEmail());
            userRepo.save(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UserDTO(temp);

    }


    public UserDTO updatePassword(EndUser currentUser, String newPassword) {
        EndUser temp = userRepo.findById(currentUser.getId()).get();

        try {
            temp.setPassword(passwordEncoder.encode(newPassword));
            userRepo.save(temp);
            System.out.println("------------ UserService: password updated");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new UserDTO(temp);
    }


    public EndUser save(EndUser user) {
        if (!existed(user)) {
            try {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepo.save(user);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
        return user;
    }


    public boolean existed(EndUser user) {
        EndUser checkUser = userRepo.findByUsername(user.getUsername());
        System.out.println("existed ? : " + checkUser);
        if (checkUser!=null) {
            System.out.println("------user already exist: ");

            return true;
        }
        return false;
    }

    public List<EndUser> findAll() {
        return userRepo.findAll();
    }
}
