package com.mycompany.app.construct;

import com.hashicorp.cdktf.TerraformStack;

public interface Provisonable<T> {
    T provision(TerraformStack scope);
}
