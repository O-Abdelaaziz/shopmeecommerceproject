package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Created 12/01/2022 - 20:43
 * @Package com.shopme.admin.user
 * @Project ShopmeProject
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest {

    private RoleRepository roleRepository;

    @Autowired
    public RoleRepositoryTest(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Test
    public void createFirstRole() {
        Role roleAdmin = new Role("Admin", "manage everything");
        Role savedRole = roleRepository.save(roleAdmin);
        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void createRestRoles() {
        Role roleSalesperson = new Role("Salesperson", "manage product price, "
                + "customers, shipping, orders and sales report");

        Role roleEditor = new Role("Editor", "manage categories, brands, "
                + "products, articles and menus");

        Role roleShipper = new Role("Shipper", "view products, view orders "
                + "and update order status");

        Role roleAssistant = new Role("Assistant", "manage questions and reviews");

        roleRepository.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));
    }
}
