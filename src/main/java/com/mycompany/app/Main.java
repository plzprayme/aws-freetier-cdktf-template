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
        new CloudBackend(stack, CloudBackendProps.builder()
                .hostname("app.terraform.io")
                .organization("2022summit")
                .workspaces(new NamedCloudWorkspace("aws-freetier-cdktf-template"))
                .build());
        app.synth();
    }
}