package com.mycompany.app.construct.aws.resource.rds;

import com.hashicorp.cdktf.TerraformOutput;
import com.hashicorp.cdktf.TerraformOutputConfig;
import com.hashicorp.cdktf.providers.aws.db_instance.DbInstance;
import com.mycompany.app.constant.Constant;
import com.mycompany.app.construct.Output;
import com.mycompany.app.construct.Provisonable;
import software.constructs.Construct;

// ref : https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/USER_CreateDBInstance.html
// preprocess : create default vpc
public class RDSInstance implements Provisonable<DbInstance> {
    private final String instanceClass = "db.t4g.micro";
    private final String engine = Constant.Resource.RDS.ENGINE;
    private final String engineVersion = Constant.Resource.RDS.ENGINE_VERSION;
    // DBName must begin with a letter and contain only alphanumeric characters.
    private final String dbName = Constant.Resource.RDS.DB_NAME;
    private final String initUserName = Constant.Resource.RDS.USER_NAME;
    private final String initPassword = Constant.Resource.RDS.PASSWORD;

    @Override
    public DbInstance provision(Construct scope) {
        DbInstance db = DbInstance.Builder.create(scope, dbName)
                .engine(engine)
                .engineVersion(engineVersion)
                .allocatedStorage(10)
                .instanceClass(instanceClass)
                .dbName(dbName)
                .username(initUserName)
                .password(initPassword)
                .skipFinalSnapshot(true)
                .build();

        new Output().provision(scope, "DB-INSTANCE-ENDPOINT", db.getEndpoint());

        return db;

        // skipFinalSnapshot (default : false) :
        // the option is "false" : if you run "cdktf destroy" command,
        // Unable to delete rds in terraform resource with error "Error: final_snapshot_identifier is required when skip_final_snapshot is false"
    }
}
