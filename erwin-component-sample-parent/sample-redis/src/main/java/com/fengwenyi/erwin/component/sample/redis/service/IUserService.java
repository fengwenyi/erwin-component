package com.fengwenyi.erwin.component.sample.redis.service;

import com.fengwenyi.erwin.component.sample.redis.vo.UserVo;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-26
 */
public interface IUserService {

    UserVo getUserById(Long id);

    void clearUserCacheById(Long id);

}
