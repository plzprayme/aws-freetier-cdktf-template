package com.mycompany.app;

import com.hashicorp.cdktf.TerraformProvider;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_application.ElasticBeanstalkApplication;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_application_version.ElasticBeanstalkApplicationVersion;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_environment.ElasticBeanstalkEnvironment;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_environment.ElasticBeanstalkEnvironmentSetting;
import com.hashicorp.cdktf.providers.aws.iam_instance_profile.IamInstanceProfile;
import com.hashicorp.cdktf.providers.aws.iam_role.IamRole;
import com.hashicorp.cdktf.providers.aws.s3_bucket.S3Bucket;
import com.hashicorp.cdktf.providers.aws.s3_object.S3Object;
import com.mycompany.app.constant.Configuration;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.construct.provider.AwsProvider;
import org.jetbrains.annotations.NotNull;
import software.constructs.Construct;

import com.hashicorp.cdktf.TerraformStack;

import java.util.List;

public class MainStack extends TerraformStack
{
    public MainStack(final Construct scope, final String id) {
        super(scope, id);

        provisionProvider(scope);

        IamInstanceProfile iamInstanceProfile = provisionIamInstanceProfile();
        ElasticBeanstalkApplication ebApp = provisionEbApp(Constant.Version.PROJECT_NAME, Constant.Resource.S3.SOURCE_BUNDLE_PATH);
    }

    private void provisionProvider(Construct scope) {
        new AwsProvider(Configuration.Aws.REGION, Configuration.Aws.AWS_ACCESS_KEY, Configuration.Aws.AWS_SECRET_KEY)
                .provision(scope);
    }

    private ElasticBeanstalkApplication provisionEbApp(final String ebAppPrefix, final String sourcePath) {
        ElasticBeanstalkApplication ebApp = provisionEbApp(ebAppPrefix);
        ElasticBeanstalkApplicationVersion ebAppVersion = provisionEbAppVersion(ebApp, sourcePath);
        provisionEbEnv(ebApp, ebAppVersion);
        return ebApp;
    }

    private ElasticBeanstalkApplication provisionEbApp(String ebAppPrefix) {
        return ElasticBeanstalkApplication.Builder.create(this, "eb-application")
                .name(ebAppPrefix + "application")
                .build();
    }

    private ElasticBeanstalkApplicationVersion provisionEbAppVersion(ElasticBeanstalkApplication ebApp, final String sourcePath) {
        S3Bucket s3Bucket = provisionS3Bucket();
        S3Object s3Object = provisionS3Object(s3Bucket, sourcePath);
        return provisionEbAppVersion(ebApp, s3Bucket, s3Object);
    }

    private S3Object provisionS3Object(S3Bucket s3Bucket, String sourcePath) {
        final String objectKey = generateObjectKey(sourcePath);
        return S3Object.Builder.create(this, "eb-source-s3-object")
                .bucket(s3Bucket.getBucket())
                .key(objectKey)
                .source(sourcePath)
                .acl("private")
                .build();
    }

    private String generateObjectKey(String sourcePath) {
        String[] splited = sourcePath.split("/");
        final String sourceFileName = splited[splited.length - 1];
        return Constant.Version.VERSION + sourceFileName;
    }

    private S3Bucket provisionS3Bucket() {
        return S3Bucket.Builder.create(this, "eb-source-bucket")
                .bucket(Constant.Version.PROJECT_NAME + "_bucket")
                .acl("private")
                .build();
    }

    private ElasticBeanstalkApplicationVersion provisionEbAppVersion(
            ElasticBeanstalkApplication ebApp,
            S3Bucket s3Bucket, S3Object s3Object) {
        return ElasticBeanstalkApplicationVersion.Builder.create(this, "eb-app-version")
                .name(ebApp.getName() + "_version")
                .application(ebApp.getName())
                .bucket(s3Bucket.getBucket())
                .key(s3Object.getKey())
                .build();
    }

    private IamRole provisionIamRole() {
        List<String> policyArns = List.of("arn:aws:iam::aws:policy/AWSElasticBeanstalkWebTier",
                "arn:aws:iam::aws:policy/AWSElasticBeanstalkMulticontainerDocker",
                "arn:aws:iam::aws:policy/AWSElasticBeanstalkWorkerTier");

        String rolePolicy = "{\"Version\": \"2012-10-17\", \"Statement\": [{\"Action\": \"sts:AssumeRole\", \"Principal\": {\"Service\": \"ec2.amazonaws.com\"}, \"Effect\": \"Allow\", \"Sid\": \"\"}]}";

        return IamRole.Builder.create(this, "iamRole")
                .name("eb-role")
                .managedPolicyArns(policyArns)
                .assumeRolePolicy(rolePolicy)
                .build();
    }

    private IamInstanceProfile provisionIamInstanceProfile() {
        IamRole role = provisionIamRole();
        return IamInstanceProfile.Builder.create(this, "instanceProfile")
                .name("aws-elasticbeanstalk-ec2-role")
                .role(role.getName())
                .build();
    }

    private List<ElasticBeanstalkEnvironmentSetting> provisionEbEnvSettings() {
        ElasticBeanstalkEnvironmentSetting createEbEnvSetting = provisionInstanceProfileEbEnvSetting();
        return List.of(createEbEnvSetting);
    }

    @NotNull
    private ElasticBeanstalkEnvironmentSetting provisionInstanceProfileEbEnvSetting() {
        // solve Environment must have instance profile associated with it
        ElasticBeanstalkEnvironmentSetting createEbEnvSetting = ElasticBeanstalkEnvironmentSetting.builder()
                .namespace("aws:autoscaling:launchconfiguration")
                .name("IamInstanceProfile")
                .value("aws-elasticbeanstalk-ec2-role")
                .build();
        return createEbEnvSetting;
    }

    private ElasticBeanstalkEnvironment provisionEbEnv(
            ElasticBeanstalkApplication application,
            ElasticBeanstalkApplicationVersion ebAppVersion) {

        List<ElasticBeanstalkEnvironmentSetting> ebEnvSettings = provisionEbEnvSettings();
        return ElasticBeanstalkEnvironment.Builder.create(this, "eb-env")
                .name(Constant.Version.PROJECT_NAME + "env")
                .application(application.getName())
                .solutionStackName(Constant.Resource.ElasticBeanstalk.SOLUTION_STACK)
                .setting(ebEnvSettings)
                .versionLabel(ebAppVersion.getName())
                .build();
    }
}
