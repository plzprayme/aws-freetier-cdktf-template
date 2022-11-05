package com.mycompany.app.construct.aws.resource.eb;

import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_application.ElasticBeanstalkApplication;
import com.hashicorp.cdktf.providers.aws.elastic_beanstalk_application_version.ElasticBeanstalkApplicationVersion;
import com.hashicorp.cdktf.providers.aws.s3_bucket.S3Bucket;
import com.hashicorp.cdktf.providers.aws.s3_object.S3Object;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.construct.Provisonable;
import software.constructs.Construct;

public class EBAppVer implements Provisonable<ElasticBeanstalkApplicationVersion> {

    private final String ID = Constant.Version.PROJECT_NAME + "-eb-app-ver";
    private final String NAME = Constant.Version.VERSION;

    private final String appName;
    private final String bucket;
    private final String key;

    public EBAppVer(ElasticBeanstalkApplication ebApp, S3Bucket s3Bucket, S3Object s3Object) {
        this.appName = ebApp.getName();
        this.bucket = s3Bucket.getBucket();
        this.key = s3Object.getKey();
    }

    @Override
    public ElasticBeanstalkApplicationVersion provision(Construct scope) {
        return ElasticBeanstalkApplicationVersion.Builder.create(scope, ID)
                .name(NAME)
                .application(appName)
                .bucket(bucket)
                .key(key)
                .build();
    }
}
