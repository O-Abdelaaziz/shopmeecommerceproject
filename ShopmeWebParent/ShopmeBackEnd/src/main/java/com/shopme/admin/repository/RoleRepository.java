package com.shopme.admin.repository;

import com.shopme.common.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Created 12/01/2022 - 20:08
 * @Package com.shopme.admin.repository
 * @Project ShopmeProject
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
