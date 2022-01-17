package com.shopme.admin.user;

import com.shopme.admin.repository.UserRepository;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Created 13/01/2022 - 09:46
 * @Package com.shopme.admin.user
 * @Project ShopmeProject
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    private UserRepository userRepository;

    private TestEntityManager testEntityManager;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository, TestEntityManager testEntityManager) {
        this.userRepository = userRepository;
        this.testEntityManager = testEntityManager;
    }

    @Test
    public void testCreateUserWithOneRole() {
        Role role = testEntityManager.find(Role.class, 1L);
        User user = new User("ouakala", "abdelaaziz", "a@a.com", "01234560");
        user.addRole(role);
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testCreateUserWithTwoRoles() {
        User user = new User("ravi2020", "ravi", "ravi2020@a.com", "01234560");
        Role roleEditor = new Role(3L);
        Role roleAssistant = new Role(5L);
        user.addRole(roleEditor);
        user.addRole(roleAssistant);
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAllUsers() {
        Iterable<User> users = userRepository.findAll();
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void testGetUserById() {
        User user = userRepository.findById(1L).get();
        System.out.println(user);
        assertThat(user).isNotNull();
    }

    @Test
    public void testUpdateUserDetails() {
        User userNam = userRepository.findById(1L).get();
        userNam.setEnabled(true);
        userNam.setEmail("namjavaprogrammer@gmail.com");

        userRepository.save(userNam);
    }

    @Test
    public void testUpdateUserRoles() {
        User userRavi = userRepository.findById(2L).get();
        Role roleEditor = new Role(3L);
        Role roleSalesperson = new Role(2L);

        userRavi.getRoles().remove(roleEditor);
        userRavi.addRole(roleSalesperson);

        userRepository.save(userRavi);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 2L;
        userRepository.deleteById(userId);
    }

    @Test
    public void testFindUserByEmail() {
        String email = "namjavaprogrammer@gmail.com";
        User user = userRepository.findUserByEmail(email);
        assertThat(user).isNotNull();
    }

    @Test
    public void testCountById() {
        Long id = 1L;
        Long countById = userRepository.countById(id);
        assertThat(countById).isNotNull().isGreaterThan(0);
    }

    @Test
    public void testDisableUser(){
        Long id=2L;
        userRepository.updateEnabledStatus(id,false);
    }

    @Test
    public void testListFirstPage() {
        int pageNumber = 0;
        int pageSize = 4;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> page = userRepository.findAll(pageable);

        List<User> listUsers = page.getContent();

        listUsers.forEach(user -> System.out.println(user));

        assertThat(listUsers.size()).isEqualTo(pageSize);
    }
}
