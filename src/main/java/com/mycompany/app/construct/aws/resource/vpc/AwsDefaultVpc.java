package com.mycompany.app.construct.aws.resource.vpc;

import com.hashicorp.cdktf.providers.aws.default_vpc.DefaultVpc;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.construct.Provisonable;
import software.constructs.Construct;

public class AwsDefaultVpc implements Provisonable<DefaultVpc> {

    private final String NAME = Constant.Version.PROJECT_NAME + "-default-vpc";

    @Override
    public DefaultVpc provision(Construct scope) {
        return DefaultVpc.Builder.create(scope, NAME).build();
    }
}
