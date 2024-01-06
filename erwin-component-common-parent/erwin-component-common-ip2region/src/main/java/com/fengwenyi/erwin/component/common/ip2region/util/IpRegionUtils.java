package com.fengwenyi.erwin.component.common.ip2region.util;

import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-22
 */
public class IpRegionUtils {

    public static String getRegion(String ip) {

        if (!StringUtils.hasText(ip)) {
            return null;
        }

        String dbPath;

        try {
            dbPath = Objects.requireNonNull(IpRegionUtils.class.getResource("/ip2region/ip2region.xdb")).getPath();
        } catch (Exception e) {
            return null;
        }

        if (!StringUtils.hasText(dbPath)) {
            return null;
        }

        Searcher searcher;
        try {
            searcher = Searcher.newWithFileOnly(dbPath);
        } catch (IOException e) {
            return null;
        }

        // 查询
        try {
            return searcher.search(ip);
        } catch (Exception e) {
            return null;
        } finally {
            try {
                searcher.close();
            } catch (IOException e) {

            }
        }

    }

    public static String getCity(String ip) {

        String region = getRegion(ip);

        if (!StringUtils.hasText(region)) {
            return "";
        }

        String[] positions = region.split("\\|");

        String country = positions[0];

        String unknown = "未知位置";

        if (blank(country)) {
            return unknown;
        }

        if (!"中国".equals(country)) {
            return country;
        }

        if (positions.length != 5) {
            return unknown;
        }

        String message = "";

        String province = positions[2];

        if (!blank(province)) {
            if (province.endsWith("省")) {
                message = province.substring(0, province.length() - 1);
            } else if (province.endsWith("区")) {
                message = province.substring(0, province.length() - 1);
            }
        }


        String city = positions[3];
        if (!blank(city)) {
            if (city.endsWith("市")) {
                message += city.substring(0, city.length() - 1);
            } else if (city.endsWith("州")) {
                message += city.substring(0, city.length() - 1);
            }
        }

        if (StringUtils.isEmpty(message)) {
            message = "未知位置";
        }
        return message;
    }

    private static boolean blank(String position) {
        String _0 = "0";
        return !StringUtils.hasText(position) || _0.equals(position);
    }

}
