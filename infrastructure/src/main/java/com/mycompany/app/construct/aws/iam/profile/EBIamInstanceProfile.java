package com.mycompany.app.construct.aws.iam.profile;

import com.hashicorp.cdktf.providers.aws.iam_instance_profile.IamInstanceProfile;
import com.hashicorp.cdktf.providers.aws.iam_role.IamRole;
import com.mycompany.app.construct.Provisonable;
import software.constructs.Construct;

public class EBIamInstanceProfile implements Provisonable<IamInstanceProfile> {

    private final String NAME = "eb-iam-instance-profile";
    private final String roleName;

    public EBIamInstanceProfile(IamRole role) {
        this.roleName = role.getName();
    }

    @Override
    public IamInstanceProfile provision(Construct scope) {
        return IamInstanceProfile.Builder.create(scope, NAME)
                .name(NAME)
                .role(roleName)
                .build();
    }
}

