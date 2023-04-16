package com.mycompany.app.construct.aws.resource.route53;

import static com.mycompany.app.constant.Constant.Version.PROJECT_NAME;

import com.hashicorp.cdktf.providers.aws.route53_record.Route53Record;
import com.mycompany.app.constant.Configuration.Aws;
import com.mycompany.app.construct.Provisonable;
import java.util.List;
import software.constructs.Construct;

public class Route53AttachEB implements Provisonable<Route53Record> {

    private final String EB_ENDPOINT;
    private final String ZONE_ID = Aws.HOSTED_ZONE_ID;
    private final String DOMAIN_NAME = Aws.DOMAIN_NAME;

    private final String DOMAIN_ATTACH_COMPONENT = PROJECT_NAME + "-domains-attach";

    public Route53AttachEB(String ebEndpoint) {
        this.EB_ENDPOINT = ebEndpoint;
    }

    @Override
    public Route53Record provision(Construct scope) {
        return Route53Record.Builder
            .create(scope, DOMAIN_ATTACH_COMPONENT)
            .zoneId(ZONE_ID)
            .name(DOMAIN_NAME)
            .type("A")
            .records(List.of(EB_ENDPOINT))
            .build();
    }
}
