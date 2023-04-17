package com.mycompany.app.construct.aws.resource.eb;

import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_environment.ElasticBeanstalkEnvironmentSetting;
import com.hashicorp.cdktf.providers.aws.iam_instance_profile.IamInstanceProfile;
import com.mycompany.app.construct.Provisonable;
import software.constructs.Construct;

import java.util.List;

// Env Setting Docs: https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/command-options-general.html
public class EBEnvSetting implements Provisonable<List<ElasticBeanstalkEnvironmentSetting>> {

    private final String iamInstanceProfileName;

    public EBEnvSetting(IamInstanceProfile instanceProfile) {
        this.iamInstanceProfileName = instanceProfile.getName();
    }

    @Override
    public List<ElasticBeanstalkEnvironmentSetting> provision(Construct scope) {
        return List.of(
                provisionIamInstanceProfile(iamInstanceProfileName),
                provisionKeyPair(), provisionInstanceType(), provisionEBCWLogHealth(),
                provisionEBCWStreamLogs()
        );
    }

    private ElasticBeanstalkEnvironmentSetting provisionIamInstanceProfile(String iamInstanceProfileName) {
        return ElasticBeanstalkEnvironmentSetting.builder()
                .namespace("aws:autoscaling:launchconfiguration")
                .name("IamInstanceProfile")
                .value(iamInstanceProfileName)
                .build();
    }

    private ElasticBeanstalkEnvironmentSetting provisionEBCWLogHealth() {
        return ElasticBeanstalkEnvironmentSetting.builder()
                .namespace("aws:elasticbeanstalk:cloudwatch:logs:health")
                .name("HealthStreamingEnabled")
                .value("true")
                .build();
    }

    private ElasticBeanstalkEnvironmentSetting provisionKeyPair() {
        return ElasticBeanstalkEnvironmentSetting.builder()
                .namespace("aws:autoscaling:launchconfiguration")
                .name("EC2KeyName")
                .value("freetier-template")
                .build();
    }

    private ElasticBeanstalkEnvironmentSetting provisionInstanceType() {
        return ElasticBeanstalkEnvironmentSetting.builder()
                .namespace("aws:autoscaling:launchconfiguration")
                .name("InstanceType")
                .value("t3.micro")
                .build();
    }

    private ElasticBeanstalkEnvironmentSetting provisionEBCWStreamLogs() {
        return ElasticBeanstalkEnvironmentSetting.builder()
                .namespace("aws:elasticbeanstalk:cloudwatch:logs")
                .name("StreamLogs")
                .value("true")
                .build();
    }
}
