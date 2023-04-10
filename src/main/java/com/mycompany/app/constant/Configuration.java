package com.mycompany.app.constant;

import java.util.Map;

public class Configuration {

    private static Map<String, String> ENV = System.getenv();

    public static class Aws {

        public static String AWS_ACCESS_KEY = ENV.get("AWS_ACCESS_KEY");
        public static String AWS_SECRET_KEY = ENV.get("AWS_SECRET_KEY");
        public static String REGION = ENV.get("REGION");

        public static String DOMAIN_NAME = ENV.get("DOMAIN_NAME");
        public static String NAME_SERVER_NAME1 = ENV.get("NAME_SERVER_NAME1");
        public static String NAME_SERVER_NAME2 = ENV.get("NAME_SERVER_NAME2");
        public static String NAME_SERVER_NAME3 = ENV.get("NAME_SERVER_NAME3");
        public static String NAME_SERVER_NAME4 = ENV.get("NAME_SERVER_NAME4");
    }

    public static class TFCloud {

        public static String ORGANIZATION = ENV.get("TF_CLOUD_ORGANIZATION");
        public static String WORKSPACE = ENV.get("TF_WORKSPACE");
        public static String TOKEN = ENV.get("TF_TOKEN");
    }
}
