package com.fengwenyi.erwin.component.sample.redis.service.impl;

import com.fengwenyi.erwin.component.sample.redis.service.IUserService;
import com.fengwenyi.erwin.component.sample.redis.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-26
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Override
    @Cacheable(value = "sample-redis", key = "'user-'+#id",  unless = "#result == null")
    public UserVo getUserById(Long id) {

        log.info("userVo from db query");

        UserVo userVo = new UserVo();
        userVo.setId(1L);
        userVo.setName("Zhangsan");
        userVo.setCreateDateTime(LocalDateTime.now());

        return userVo;
    }

    @Override
    @CacheEvict(value = "sample-redis", key = "'user-'+#id")
    public void clearUserCacheById(Long id) {

    }
}
