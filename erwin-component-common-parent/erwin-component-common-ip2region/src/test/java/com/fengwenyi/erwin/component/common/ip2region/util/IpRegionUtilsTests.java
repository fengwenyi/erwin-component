package com.fengwenyi.erwin.component.common.ip2region.util;

import org.junit.Test;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-27
 */
public class IpRegionUtilsTests {

    @Test
    public void testGetCity() {
        String ip = "117.139.192.58";
        System.out.println(IpRegionUtils.getRegion(ip));
        System.out.println(IpRegionUtils.getCity(ip));
    }

}
