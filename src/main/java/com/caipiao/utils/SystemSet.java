//    SystemSet.java

package com.caipiao.utils;

import com.sysbcjzh.utils.ConfigurationFactory;

import java.util.Properties;

public class SystemSet {

    public static Properties crawler = ConfigurationFactory.getConfigDir("crawler.properties");
    public static Properties money = ConfigurationFactory.getConfigDir("money.properties");
    public static Properties paytype = ConfigurationFactory.getConfigDir("paytype.properties");

    public SystemSet() {
    }

    public static void main(String args1[]) {
    }

}
