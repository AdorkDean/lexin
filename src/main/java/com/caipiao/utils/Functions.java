package com.caipiao.utils;

import com.caipiao.admin.service.AdminCaiwuService;

import java.util.List;

/**
 * Created by liujie on 2017/8/21.
 */
public class Functions {

    public Functions() {
    }

    public static List<String> getAllAgent() {
        AdminCaiwuService service = new AdminCaiwuService();
        return service.findAllAgent();
    }
}
