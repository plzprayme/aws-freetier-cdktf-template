package com.mycompany.app.construct.provider;

import com.mycompany.app.construct.Provisonable;
import lombok.AllArgsConstructor;
import software.constructs.Construct;

@AllArgsConstructor
public class AwsProvider implements Provisonable<com.hashicorp.cdktf.providers.aws.provider.AwsProvider> {

    private final String region;
    private final String accessKey;
    private final String secretKey;

    @Override
    public com.hashicorp.cdktf.providers.aws.provider.AwsProvider provision(Construct scope) {

        return com.hashicorp.cdktf.providers.aws.provider.AwsProvider.Builder.create(scope, "aws-provider")
                .region(region)
                .accessKey(accessKey)
                .secretKey(secretKey)
                .build();
    }
}
