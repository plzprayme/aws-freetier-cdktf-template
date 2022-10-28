package com.mycompany.app;

import software.constructs.Construct;

import com.hashicorp.cdktf.App;
import com.hashicorp.cdktf.NamedCloudWorkspace;
import com.hashicorp.cdktf.CloudBackend;
import com.hashicorp.cdktf.CloudBackendProps;
import com.hashicorp.cdktf.TerraformStack;


public class Main
{
    public static void main(String[] args) {
        final App app = new App();
        MainStack stack = new MainStack(app, "aws-freetier-cdktf-template");
        provisionTFCloud(stack, Constant.TF_CLOUD_ORGANIZATION, Constant.TF_WORKSPACE);
        app.synth();
    }

    private static void provisionTFCloud(TerraformStack stack, final String organization, final String workspace) {
        new CloudBackend(stack, CloudBackendProps.builder()
                .hostname("app.terraform.io")
                .organization(organization)
                .workspaces(new NamedCloudWorkspace(workspace))
                .build());
    }
}