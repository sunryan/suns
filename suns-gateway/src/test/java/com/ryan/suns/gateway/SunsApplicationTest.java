package com.ryan.suns.gateway;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryan.suns.common.model.enums.DeleteEnum;
import com.ryan.suns.common.model.enums.SexEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author lr
 * @date 2018/2/12
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SunsGatewayApplication.class)
public class SunsApplicationTest {
    
    @Test
    public void testJsonSerialize() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
    
        String jsonStr1 = objectMapper.writeValueAsString(DeleteEnum.DELETE);
        String jsonStr2 = objectMapper.writeValueAsString(SexEnum.WOMAN);
        System.out.println(jsonStr1);
        System.out.println(objectMapper.readValue(jsonStr1, DeleteEnum.class));
        System.out.println(jsonStr2);
        System.out.println(objectMapper.readValue(jsonStr2, SexEnum.class));
    }
    
}
