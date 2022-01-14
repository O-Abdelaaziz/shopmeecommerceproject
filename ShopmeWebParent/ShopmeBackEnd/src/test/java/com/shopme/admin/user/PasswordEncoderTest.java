package com.shopme.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @Created 14/01/2022 - 09:53
 * @Package com.shopme.admin.user
 * @Project ShopmeProject
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
public class PasswordEncoderTest {

    @Test
    public void testEncoderPassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "01234560";
        String encoderPassword = bCryptPasswordEncoder.encode(rawPassword);
        boolean matches = bCryptPasswordEncoder.matches(rawPassword, encoderPassword);
        assertThat(matches).isTrue();
    }
}
