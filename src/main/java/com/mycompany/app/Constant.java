package com.mycompany.app;

import java.time.LocalDateTime;
import java.util.Map;

public class Constant {

    private static Map<String, String> ENV = System.getenv();

    public static String AWS_ACCESS_KEY = ENV.get("AWS_ACCESS_KEY");
    public static String AWS_SECRET_KEY = ENV.get("AWS_SECRET_KEY");

    public static String REGION = ENV.get("region");

    public static String GITHUB_REPOSITORY = ENV.get("GITHUB_REPOSITORY");
    public static String GITHUB_SHA = ENV.get("GITHUB_SHA");
    public static LocalDateTime NOW = LocalDateTime.now();

    public static String EB_SOLUTION_STACK = ENV.get("EB_SOLUTION_STACK");

    public static String VERSION = GITHUB_REPOSITORY + GITHUB_SHA.substring(0, 5) + NOW;

    public static String SOURCE_BUNDLE_PATH = ENV.get("SOURCE_BUNDLE_PATH");

    public static String TF_CLOUD_ORGANIZATION = ENV.get("TF_CLOUD_ORGANIZATION");
    public static String TF_WORKSPACE = ENV.get("TF_WORKSPACE");
}
