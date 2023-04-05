package com.mycompany.app;

import com.hashicorp.cdktf.App;
import com.hashicorp.cdktf.CloudBackend;
import com.hashicorp.cdktf.CloudBackendProps;
import com.hashicorp.cdktf.NamedCloudWorkspace;
import com.hashicorp.cdktf.TerraformStack;
import com.mycompany.app.constant.Configuration;
import com.mycompany.app.constant.Constant;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MainApp {

    public static void main(String[] args) {

        File file = new File(".env.tmpl");
        Map<String, String> env = new HashMap<>();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                String key = parts[0].replaceAll("\"", "");
                String value = parts[1].replaceAll("\"", "");
                env.put(key, value);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("region = " + env.get("REGION"));
        System.out.println("COMMIT_HASH = " + env.get("COMMIT_HASH"));

//        Map<String, String> ENV = System.getenv();
//        for (Entry<String, String> stringStringEntry : ENV.entrySet()) {
//            System.out.println("stringStringEntry = " + stringStringEntry);
//        }

        final App app = new App();
        MainStack stack = new MainStack(app, Constant.Version.PROJECT_NAME + "-dev");
        provisionTFCloud(
            stack,
            Configuration.TFCloud.ORGANIZATION,
            Configuration.TFCloud.WORKSPACE,
            Configuration.TFCloud.TOKEN
        );
        app.synth();
    }

    private static void provisionTFCloud(TerraformStack stack, String organization,
        String workspace, String token) {
        new CloudBackend(stack, CloudBackendProps.builder()
            .hostname("app.terraform.io")
            .organization(organization)
            .workspaces(new NamedCloudWorkspace(workspace))
            .token(token)
            .build());
    }
}