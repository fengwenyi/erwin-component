package com.fengwenyi.erwin.component.common.mybatis_plus.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fengwenyi.api.result.PageTemplate;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
public class PageUtils {

    public static <R, T> PageTemplate<R> convert(IPage<T> page) {
        PageTemplate<R> pageTemplate = new PageTemplate<>();
        pageTemplate.setCurrent(page.getCurrent());
        pageTemplate.setPageSize((int) page.getSize());
        pageTemplate.setTotalPage(page.getPages());
        pageTemplate.setTotalRow(page.getTotal());
        return pageTemplate;
    }

    public static <R> PageTemplate<R> empty() {
        PageTemplate<R> pageTemplate = new PageTemplate<>();
        pageTemplate.setCurrent(1);
        pageTemplate.setPageSize(10);
        pageTemplate.setTotalPage(0);
        pageTemplate.setTotalRow(0);
        return pageTemplate;
    }

}
