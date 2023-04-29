package com.mycompany.app.construct;

import com.hashicorp.cdktf.TerraformOutput;
import com.hashicorp.cdktf.TerraformOutputConfig;
import software.constructs.Construct;

public class Output {

    public TerraformOutput provision(Construct scope, String id, String value) {
        return new TerraformOutput(scope, id, TerraformOutputConfig.builder()
                .value(value)
                .build());
    }

}
