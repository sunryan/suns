package com.ryan.suns.auth.component;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryan.suns.api.feign.admin.MenuClient;
import com.ryan.suns.common.constant.CommonConstant;
import com.ryan.suns.common.constant.RedisConstants;
import com.ryan.suns.common.model.admin.SysMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 登录成功，调用oauth token
 * @author lr
 * @date 2018/3/26
 */
public abstract class SunsAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    
    public static final String BASIC_ = "Basic ";
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private MenuClient menuClient;
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Autowired
    private AuthorizationServerTokenServices defaultAuthorizationServerTokenServices;
    
    
    
    /**
     * Called when a admin has been successfully authenticated.
     * 调用spring security oauth API 生成 oAuth2AccessToken
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String header = request.getHeader("Authorization");
        
        if (header == null || !header.startsWith(BASIC_)) {
            throw new UnapprovedClientAuthenticationException("请求头中client信息为空");
        }
        
        try {
            String[] tokens = extractAndDecodeHeader(header);
            assert tokens.length == 2;
            String clientId = tokens[0];
            String clientSecret = tokens[1];
            
            JSONObject params = new JSONObject();
            params.put("clientId", clientId);
            params.put("clientSecret", clientSecret);
            params.put("authentication", authentication);
            
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            TokenRequest tokenRequest = new TokenRequest(MapUtil.newHashMap(), clientId, clientDetails.getScope(), getGrantType());
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken oAuth2AccessToken = defaultAuthorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            logger.info("获取token 成功：{}", oAuth2AccessToken.getValue());
    
            // 保存用户权限
            saveUserPermission(authentication);
            
            response.setCharacterEncoding(CommonConstant.UTF8);
            response.setContentType(CommonConstant.CONTENT_TYPE);
            PrintWriter printWriter = response.getWriter();
            printWriter.append(objectMapper.writeValueAsString(oAuth2AccessToken));
        } catch (IOException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }
    }
    
    protected  void saveUserPermission(Authentication authentication){
       
        List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        Set<SysMenu> urls = new HashSet<>();
        for (SimpleGrantedAuthority authority : grantedAuthorityList) {
            urls.addAll(menuClient.findMenuByRoleCode(authority.getAuthority()));
        }
    
        redisTemplate.opsForHash().put(RedisConstants.USER_PERMISSION_KEY ,authentication.getName() , urls);
        logger.info("保存用户权限");
    }
    
    protected String getGrantType(){
        return "suns";
    }
    
    /**
     * Decodes the header into a username and password.
     *
     * @throws BadCredentialsException if the Basic header is not present or is not valid
     *                                 Base64
     */
    private String[] extractAndDecodeHeader(String header)
            throws IOException {
        
        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }
        
        String token = new String(decoded, CommonConstant.UTF8);
        
        int delim = token.indexOf(":");
        
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }
}
