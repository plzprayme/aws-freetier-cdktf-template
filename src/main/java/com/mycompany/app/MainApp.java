package com.mycompany.app;

import com.hashicorp.cdktf.App;
import com.hashicorp.cdktf.CloudBackend;
import com.hashicorp.cdktf.CloudBackendProps;
import com.hashicorp.cdktf.NamedCloudWorkspace;
import com.hashicorp.cdktf.TerraformStack;
import com.mycompany.app.constant.Configuration;
import com.mycompany.app.constant.Constant;


public class MainApp {

    public static void main(String[] args) {
        final App app = new App();
        MainStack stack = new MainStack(app, Constant.Version.PROJECT_NAME + "-dev");
        provisionTFCloud(stack, Configuration.TFCloud.ORGANIZATION, Configuration.TFCloud.WORKSPACE, Configuration.TFCloud.TOKEN);
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