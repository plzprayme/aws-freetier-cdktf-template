package com.mycompany.app.construct.aws.resource.eb;

import com.hashicorp.cdktf.TerraformOutput;
import com.hashicorp.cdktf.TerraformOutputConfig;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_application.ElasticBeanstalkApplication;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_application_version.ElasticBeanstalkApplicationVersion;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_environment.ElasticBeanstalkEnvironment;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_environment.ElasticBeanstalkEnvironmentSetting;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.construct.Output;
import com.mycompany.app.construct.Provisonable;
import software.constructs.Construct;

import java.util.List;

public class EBEnv implements Provisonable<ElasticBeanstalkEnvironment> {

    private final String NAME = Constant.Version.PROJECT_NAME + "-eb-env";
    private String SOLUTION_STACK = Constant.Resource.ElasticBeanstalk.SOLUTION_STACK;

    private String appName;

    private List<ElasticBeanstalkEnvironmentSetting> settings;

    public EBEnv(ElasticBeanstalkApplication ebApp,
                 List<ElasticBeanstalkEnvironmentSetting> settings) {
        this.appName = ebApp.getName();
        this.settings = settings;
    }

    @Override
    public ElasticBeanstalkEnvironment provision(Construct scope) {
        ElasticBeanstalkEnvironment ebEnv = ElasticBeanstalkEnvironment.Builder.create(scope, NAME)
                .name(NAME)
                .application(appName)
                .solutionStackName(SOLUTION_STACK)
                .setting(settings)
                .build();

        new Output().provision(scope, "EB-ENDPOINT", ebEnv.getEndpointUrl());

        return ebEnv;
    }
}
