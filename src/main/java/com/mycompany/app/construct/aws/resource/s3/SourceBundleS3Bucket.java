package com.mycompany.app.construct.aws.resource.s3;

import com.hashicorp.cdktf.providers.aws.s3_bucket.S3Bucket;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.construct.Provisonable;
import software.constructs.Construct;

public class SourceBundleS3Bucket implements Provisonable<S3Bucket> {

    private final String NAME = Constant.Version.PROJECT_NAME + "-source-bundle";

    @Override
    public S3Bucket provision(Construct scope) {
        return S3Bucket.Builder.create(scope, NAME)
                .bucket(NAME)
                .acl("private")
                .build();
    }
}
