package com.shopme.admin.repository;

import com.shopme.common.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Created 13/01/2022 - 09:45
 * @Package com.shopme.admin.repository
 * @Project ShopmeProject
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
