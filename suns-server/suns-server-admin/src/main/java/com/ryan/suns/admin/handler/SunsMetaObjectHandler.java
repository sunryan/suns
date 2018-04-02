package com.ryan.suns.admin.handler;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author lr
 * @date 2018/3/27.
 */
@Slf4j
@Component
public class SunsMetaObjectHandler extends MetaObjectHandler {
    
    
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("新增的时候干点不可描述的事情");
    }
    
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新的时候干点不可描述的事情");
    }
}
