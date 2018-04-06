package com.ryan.suns.gateway;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryan.suns.admin.SunsAdminAppliction;
import com.ryan.suns.common.model.enums.DeleteEnum;
import com.ryan.suns.common.model.enums.SexEnum;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author lr
 * @date 2018/2/12
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SunsAdminAppliction.class)
public class SunsAdminApplicationTest {
    
    @Autowired
    private StringEncryptor stringEncryptor;
    
    @Test
    public void testEnvironmentProperties() {
        System.out.println(stringEncryptor.encrypt("qdm162290194"));
        System.out.println(stringEncryptor.encrypt("ryan941026"));
    }
    
    
}
