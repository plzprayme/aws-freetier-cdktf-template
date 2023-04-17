package com.mycompany.app.construct.aws.resource.cloud_watch;

import com.hashicorp.cdktf.providers.aws.cloudwatch_log_group.CloudwatchLogGroup;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.construct.Provisonable;
import software.constructs.Construct;

public class CWLogGroup implements Provisonable<CloudwatchLogGroup> {

    private final String id = Constant.Version.PROJECT_NAME + "_log_group_id";
    private final String name = Constant.Version.PROJECT_NAME + "/var/log/application";

    @Override
    public CloudwatchLogGroup provision(Construct scope) {
        return CloudwatchLogGroup.Builder.create(scope, id)
                .name(name)
                .build();
    }
}
