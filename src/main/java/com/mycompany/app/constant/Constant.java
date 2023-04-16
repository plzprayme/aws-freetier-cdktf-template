package com.mycompany.app.constant;

import java.time.LocalDateTime;
import java.util.Map;

public class Constant {

    private static Map<String, String> ENV = System.getenv();

    public static class Version {
        public static String PROJECT_NAME = ENV.get("PROJECT_NAME");
        public static String COMMIT_HASH = ENV.get("COMMIT_HASH");
        public static LocalDateTime TIMESTAMP = LocalDateTime.now();

        public static String VERSION =
                String.join("-", new String[]{PROJECT_NAME, COMMIT_HASH.substring(0, 5), TIMESTAMP.toString()});
    }

    public static class Resource {

        public static class ElasticBeanstalk {
            public static String SOLUTION_STACK = ENV.get("EB_SOLUTION_STACK");
        }

        public static class S3 {
            public static String SOURCE_BUNDLE_PATH = ENV.get("SOURCE_BUNDLE_PATH");
        }

        public static class RDS {
            public static String RDS_USER_NAME = ENV.get("RDS_USER_NAME");
            public static String RDS_PASSWORD = ENV.get("RDS_PASSWORD");
        }
    }
}
