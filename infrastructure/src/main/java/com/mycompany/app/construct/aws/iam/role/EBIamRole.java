package com.mycompany.app.construct.aws.iam.role;

import com.hashicorp.cdktf.providers.aws.iam_role.IamRole;
import com.mycompany.app.construct.Provisonable;
import software.constructs.Construct;

import java.util.List;

public class EBIamRole implements Provisonable<IamRole> {

    private final String NAME = "eb-iam-role";

    private final List<String> policyArn = List.of(
            "arn:aws:iam::aws:policy/AWSElasticBeanstalkWebTier",
            "arn:aws:iam::aws:policy/AWSElasticBeanstalkMulticontainerDocker",
            "arn:aws:iam::aws:policy/AWSElasticBeanstalkWorkerTier");

    private final String rolePolicy = "{\"Version\": \"2012-10-17\", \"Statement\": [{\"Action\": \"sts:AssumeRole\", \"Principal\": {\"Service\": \"ec2.amazonaws.com\"}, \"Effect\": \"Allow\", \"Sid\": \"\"}]}";

    @Override
    public IamRole provision(Construct scope) {
        return IamRole.Builder.create(scope, NAME)
                .name(NAME)
                .managedPolicyArns(policyArn)
                .assumeRolePolicy(rolePolicy)
                .build();
    }
}
