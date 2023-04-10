package com.mycompany.app.construct.aws.resource.rds;

import com.hashicorp.cdktf.providers.aws.db_instance.DbInstance;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.construct.Provisonable;
import software.constructs.Construct;

// ref : https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/USER_CreateDBInstance.html
// preprocess : create default vpc
public class RDSInstance implements Provisonable<DbInstance> {
    private final String instanceClass = "db.t3.micro";
    private final String engine = "mysql";
    private final String engineVersion = "8.0";
    // DBName must begin with a letter and contain only alphanumeric characters.
    private final String dbName = Constant.Version.PROJECT_NAME + "DB";
    // only lowercase alphanumeric characters and hyphens allowed
    private final String identifier = Constant.Version.PROJECT_NAME + "-db";
    private final String initUserName = Constant.Resource.RDS.RDS_USER_NAME;
    private final String initPassword = Constant.Resource.RDS.RDS_PASSWORD;

    @Override
    public DbInstance provision(Construct scope) {
        return DbInstance.Builder.create(scope, dbName)
                .identifier(identifier)
                .engine(engine)
                .engineVersion(engineVersion)
                .allocatedStorage(10)
                .instanceClass(instanceClass)
                .dbName(dbName)
                .username(initUserName)
                .password(initPassword)
                .skipFinalSnapshot(true)
                .build();

        // skipFinalSnapshot (default : false) :
        // the option is "false" : if you run "cdktf destroy" command,
        // Unable to delete rds in terraform resource with error "Error: final_snapshot_identifier is required when skip_final_snapshot is false"
    }
}
