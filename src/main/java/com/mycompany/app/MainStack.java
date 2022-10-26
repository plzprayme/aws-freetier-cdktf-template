package com.mycompany.app;

import com.hashicorp.cdktf.TerraformProvider;
import com.hashicorp.cdktf.providers.aws.provider.AwsProvider;
import software.constructs.Construct;

import com.hashicorp.cdktf.TerraformStack;

public class MainStack extends TerraformStack
{
    public MainStack(final Construct scope, final String id) {
        super(scope, id);
        TerraformProvider provider = provisionProvider();
    }

    private TerraformProvider provisionProvider() {
        final String accessKey = System.getenv().get("AWS_ACCESS_KEY");
        final String secretKey = System.getenv().get("AWS_SECRET_KEY");
        return AwsProvider.Builder.create(this, "aws-provider")
                .region("ap-northeast-2")
                .accessKey(accessKey)
                .secretKey(secretKey)
                .build();
    }


}