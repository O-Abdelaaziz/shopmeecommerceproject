package com.shopme.admin.service;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

import java.util.List;

/**
 * @Created 13/01/2022 - 18:24
 * @Package com.shopme.admin.service
 * @Project ShopmeProject
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
public interface IUserService {
    public List<User> listAll();

    public List<Role> listRoles();

    public User save(User user);

    public boolean isEmailUnique(String email);
}
