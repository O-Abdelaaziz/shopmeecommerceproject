package com.shopme.admin.service;

import com.shopme.admin.exception.UserNotFoundException;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.springframework.data.domain.Page;

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

    public boolean isEmailUnique(Long id, String email);

    public User get(Long id) throws UserNotFoundException;

    public void delete(Long id) throws UserNotFoundException;

    public void updateUserEnabledStatus(Long id, boolean enabled);

    public Page<User> listByPage(int pageNumber, String sortField, String sortDirection);

}
