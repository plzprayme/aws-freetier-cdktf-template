package com.mycompany.app;

import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_application.ElasticBeanstalkApplication;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_application_version.ElasticBeanstalkApplicationVersion;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_environment.ElasticBeanstalkEnvironment;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_environment.ElasticBeanstalkEnvironmentSetting;
import com.hashicorp.cdktf.providers.aws.iam_instance_profile.IamInstanceProfile;
import com.hashicorp.cdktf.providers.aws.iam_role.IamRole;
import com.hashicorp.cdktf.providers.aws.s3_bucket.S3Bucket;
import com.hashicorp.cdktf.providers.aws.s3_object.S3Object;
import com.mycompany.app.constant.Configuration;
import com.mycompany.app.construct.aws.iam.profile.EBIamInstanceProfile;
import com.mycompany.app.construct.aws.iam.role.EBIamRole;
import com.mycompany.app.construct.aws.provider.AwsProvider;
import com.mycompany.app.construct.aws.resource.eb.*;
import com.mycompany.app.construct.aws.resource.s3.SourceBundleS3Bucket;
import com.mycompany.app.construct.aws.resource.s3.SourceBundleS3Object;
import software.constructs.Construct;

import com.hashicorp.cdktf.TerraformStack;

import java.util.List;

// aws provider argument docs: https://registry.terraform.io/providers/hashicorp/aws/latest/docs#argument-reference
public class MainStack extends TerraformStack
{
    public MainStack(final Construct scope, final String id) {
        super(scope, id);

        provisionProvider(this);
        provisionElasticBeanstalk(this);
    }

    private void provisionProvider(Construct scope) {
        new AwsProvider(Configuration.Aws.REGION, Configuration.Aws.AWS_ACCESS_KEY, Configuration.Aws.AWS_SECRET_KEY)
                .provision(scope);
    }

    private ElasticBeanstalkApplication provisionElasticBeanstalk(Construct scope) {
        ElasticBeanstalkApplication ebApp = new EBApp().provision(scope);

        S3Bucket s3Bucket = new SourceBundleS3Bucket().provision(scope);
        S3Object s3Object = new SourceBundleS3Object(s3Bucket).provision(scope);
        ElasticBeanstalkApplicationVersion ebAppVer = new EBAppVer(ebApp, s3Bucket, s3Object).provision(scope);

        IamRole role = new EBIamRole().provision(scope);
        IamInstanceProfile profile = new EBIamInstanceProfile(role).provision(scope);
        List<ElasticBeanstalkEnvironmentSetting> settings = new EBEnvSetting(profile).provision(scope);
        ElasticBeanstalkEnvironment ebEnv = new EBEnv(ebApp, ebAppVer, settings).provision(scope);

        return ebApp;
    }
}
