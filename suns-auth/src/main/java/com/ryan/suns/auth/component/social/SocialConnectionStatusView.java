package com.ryan.suns.auth.component.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 访问/connect会获取所有绑定社交的信息(显示是否绑定第三方社交信息)
 * Created on 2018/1/11 0011.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@Component("connect/status")
public class SocialConnectionStatusView  extends AbstractView {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, List<Connection<?>>> connections = (Map<String, List<Connection<?>>>) model.get("connectionMap");

        Map<String, Boolean> result = new HashMap<>(8);
        for (String key : connections.keySet()) {
//            result.put(key, CollectionUtils.isNotEmpty(connections.get(key)));
            result.put(key, true);
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
