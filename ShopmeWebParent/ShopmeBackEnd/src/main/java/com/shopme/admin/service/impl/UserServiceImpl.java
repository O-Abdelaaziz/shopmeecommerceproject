package com.shopme.admin.service.impl;

import com.shopme.admin.exception.UserNotFoundException;
import com.shopme.admin.repository.RoleRepository;
import com.shopme.admin.repository.UserRepository;
import com.shopme.admin.service.IUserService;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Created 13/01/2022 - 18:29
 * @Package com.shopme.admin.service.impl
 * @Project ShopmeProject
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public User save(User user) {
        boolean isUpdatingUser = (user.getId() != null);
        if(isUpdatingUser){
            User exitingUser =userRepository.findById(user.getId()).get();

            if(user.getPassword().isEmpty()){
                user.setPassword(exitingUser.getPassword());
            }else{
                encodePassword(user);
            }
        }else{
            encodePassword(user);
        }
        return userRepository.save(user);
    }

    @Override
    public boolean isEmailUnique(Long id, String email) {
        User userByEmail = userRepository.findUserByEmail(email);
        if (userByEmail == null) return true;

        boolean isCreatingNew = (id == null);
        if (isCreatingNew) {
            if (userByEmail != null) return false;
        } else {
            if (userByEmail.getId() != id) {
                return false;
            }
        }
        return true;
    }

    @Override
    public User get(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("could not found user with id: " + id)
        );
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}
