package com.mycompany.app.construct.aws.resource.eb;

import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_application.ElasticBeanstalkApplication;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_application_version.ElasticBeanstalkApplicationVersion;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_environment.ElasticBeanstalkEnvironment;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_environment.ElasticBeanstalkEnvironment.Builder;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_environment.ElasticBeanstalkEnvironmentSetting;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.construct.Provisonable;
import java.util.List;
import software.constructs.Construct;

public class EBEnv implements Provisonable<ElasticBeanstalkEnvironment> {

    private final String NAME = Constant.Version.PROJECT_NAME + "-eb-env";
    private String SOLUTION_STACK = Constant.Resource.ElasticBeanstalk.SOLUTION_STACK;

    private String appName;
    private String verLabel;
    private List<ElasticBeanstalkEnvironmentSetting> settings;

    public EBEnv(ElasticBeanstalkApplication ebApp,
        ElasticBeanstalkApplicationVersion ebAppVer,
        List<ElasticBeanstalkEnvironmentSetting> settings) {
        this.appName = ebApp.getName();
        this.verLabel = ebAppVer.getName();
        this.settings = settings;
    }

    @Override
    public ElasticBeanstalkEnvironment provision(Construct scope) {
        return Builder.create(scope, NAME)
            .name(NAME)
            .application(appName)
            .solutionStackName(SOLUTION_STACK)
            .setting(settings)
            .versionLabel(verLabel)
            .build();
    }
}
