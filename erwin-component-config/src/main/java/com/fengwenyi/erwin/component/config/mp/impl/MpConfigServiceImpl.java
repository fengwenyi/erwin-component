package com.fengwenyi.erwin.component.config.mp.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengwenyi.erwin.component.config.entity.ConfigEntity;
import com.fengwenyi.erwin.component.config.mapper.IConfigMapper;
import com.fengwenyi.erwin.component.config.mp.IMpConfigService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-19
 */
@Service
public class MpConfigServiceImpl
        extends ServiceImpl<IConfigMapper, ConfigEntity>
        implements IMpConfigService {
}
