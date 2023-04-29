package com.mycompany.app.construct;

import software.constructs.Construct;

public interface Provisonable<T> {

    T provision(Construct scope);
}
