package com.ryan.suns.gateway;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * @author lr
 * @date 2018/2/12
 */
public class test {
    
    public static void main(String[] args) {
        PasswordEncoder encoder = new StandardPasswordEncoder("suns");
        System.out.println(encoder.encode("admin"));
        System.out.println(encoder.matches("admin", "53ad6c38dd1f58b6967602cce83ddf4cf7a74bee53b37fb420bb4b489dee13c7cdeff38ee8a483a8"));
    }
}
