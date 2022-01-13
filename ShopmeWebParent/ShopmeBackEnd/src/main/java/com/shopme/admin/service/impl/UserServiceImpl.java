package com.shopme.admin.service.impl;

import com.shopme.admin.repository.UserRepository;
import com.shopme.admin.service.IUserService;
import com.shopme.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }
}
