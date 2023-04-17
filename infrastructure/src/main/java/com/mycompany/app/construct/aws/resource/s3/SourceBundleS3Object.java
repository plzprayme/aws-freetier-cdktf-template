package com.mycompany.app.construct.aws.resource.s3;

import com.hashicorp.cdktf.providers.aws.s3_bucket.S3Bucket;
import com.hashicorp.cdktf.providers.aws.s3_object.S3Object;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.construct.Provisonable;
import software.constructs.Construct;

public class SourceBundleS3Object implements Provisonable<S3Object> {

    private final String ID = Constant.Version.PROJECT_NAME + "-source-bundle-s3-object";
    private final String OBJECT_KEY = Constant.Version.VERSION + ".jar";
    private final String SOURCE_PATH = Constant.Resource.S3.SOURCE_BUNDLE_PATH;

    private final String bucketName;

    public SourceBundleS3Object(S3Bucket bucket) {
        this.bucketName = bucket.getBucket();
    }

    @Override
    public S3Object provision(Construct scope) {
        final String objectKey = generateObjectKey();
        return S3Object.Builder.create(scope, ID)
                .bucket(bucketName)
                .key(objectKey)
                .source(SOURCE_PATH)
                .acl("private")
                .build();
    }

    private String generateObjectKey() {
        String[] splited = SOURCE_PATH.split("/");
        final String sourceFileName = splited[splited.length - 1];
        return Constant.Version.VERSION + sourceFileName;
    }

}
