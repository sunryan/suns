package com.ryan.suns.api.auth;


import com.ryan.suns.common.model.auth.User;

/**
 * @author lr
 * @date 2018/1/23
 */
public interface UserService {

    User selectByUsername(String username);


}
