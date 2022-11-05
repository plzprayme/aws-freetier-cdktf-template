package com.mycompany.app.construct;

import com.hashicorp.cdktf.TerraformStack;
import software.constructs.Construct;

public interface Provisonable<T> {
    T provision(Construct scope);
}
