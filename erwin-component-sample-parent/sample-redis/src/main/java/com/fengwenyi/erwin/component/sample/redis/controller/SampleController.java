package com.fengwenyi.erwin.component.sample.redis.controller;

import com.fengwenyi.erwin.component.common.util.JacksonUtils;
import com.fengwenyi.erwin.component.sample.redis.service.IUserService;
import com.fengwenyi.erwin.component.sample.redis.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-26
 */
@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
@Slf4j
public class SampleController {

    private final IUserService userService;

    @GetMapping("/user/{id}")
    public UserVo getUserById(@PathVariable Long id) {

        UserVo vo = userService.getUserById(id);

        log.info("vo: {}", JacksonUtils.json(vo));

        return vo;
    }

    @DeleteMapping("/user-cache/{id}")
    public void clearUserCacheById(@PathVariable Long id) {
        userService.clearUserCacheById(id);
    }

}
