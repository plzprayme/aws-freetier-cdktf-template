package com.mycompany.app.construct.aws.resource.eb;

import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_application.ElasticBeanstalkApplication;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_application_version.ElasticBeanstalkApplicationVersion;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.construct.Provisonable;
import lombok.RequiredArgsConstructor;
import software.constructs.Construct;

@RequiredArgsConstructor
public class EBApp implements Provisonable<ElasticBeanstalkApplication> {

    private final String NAME = Constant.Version.PROJECT_NAME + "-eb-app";

    @Override
    public ElasticBeanstalkApplication provision(Construct scope) {
        return ElasticBeanstalkApplication.Builder.create(scope, NAME)
                .name(NAME)
                .build();
    }
}
