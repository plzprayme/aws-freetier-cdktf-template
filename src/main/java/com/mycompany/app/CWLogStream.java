package com.mycompany.app;

import com.hashicorp.cdktf.providers.aws.cloudwatch_log_group.CloudwatchLogGroup;
import com.hashicorp.cdktf.providers.aws.cloudwatch_log_stream.CloudwatchLogStream;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.construct.Provisonable;
import lombok.RequiredArgsConstructor;
import software.constructs.Construct;

@RequiredArgsConstructor
public class CWLogStream implements Provisonable<CloudwatchLogStream> {

    private final CloudwatchLogGroup logGroup;
    private final String id = Constant.Version.PROJECT_NAME + "_log_stream";
    private final String name = Constant.Version.TIMESTAMP.toString().replaceAll(":", "-") +
            Constant.Version.COMMIT_HASH;

    @Override
    public CloudwatchLogStream provision(Construct scope) {
        return CloudwatchLogStream.Builder.create(scope, id)
                .name(name)
                .logGroupName(logGroup.getName())
                .build();
    }
}
