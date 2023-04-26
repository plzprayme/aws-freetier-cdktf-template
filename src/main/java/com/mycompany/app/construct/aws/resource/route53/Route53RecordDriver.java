package com.mycompany.app.construct.aws.resource.route53;

import static com.mycompany.app.constant.Configuration.Aws.HOSTED_ZONE_ID;
import static com.mycompany.app.constant.Constant.Version.PROJECT_NAME;

import com.hashicorp.cdktf.providers.aws.route53_record.Route53Record;
import com.mycompany.app.constant.Configuration.Aws;
import com.mycompany.app.construct.Provisonable;
import com.mycompany.app.construct.aws.resource.eb.EBApp;
import java.util.Collections;
import software.constructs.Construct;

/*

[ 도메인 등록 요청을 위한 필수 필드 ]

.records(Collections.singletonList(""))
.name("dorogba")
.zoneId(HOSTED_ZONE_ID)
.type("A")
.ttl(300)

--------------------------------------------------------------------------

- 사용자가 Domain 을 가지고 있다고 가정한다.
- 배포 후 생성되는 ALB의 Endpoint로 라우팅이 되어야한다.
- Route53 에 사용자의 Domain이 등록되어있고, Route53에서 DNS RECORD에 ALB를 등록한다.

 */

public class Route53RecordDriver implements Provisonable<Route53Record> {

    private final EBApp ebApp = new EBApp();

    private final String DOMAIN_REGISTER_COMPONENT = PROJECT_NAME + "-domains-registers";

    // 등록할 도메인 이름
    private final String SUB_DOMAIN_NAME = Aws.SUB_DOMAIN_NAME;

    private final String EB_ENDPOINT;

    public Route53RecordDriver(String EB_ENDPOINT) {
        this.EB_ENDPOINT = EB_ENDPOINT;
    }

    @Override
    public Route53Record provision(Construct scope) {
        return Route53Record.Builder
            .create(scope, DOMAIN_REGISTER_COMPONENT)
            .records(Collections.singletonList(EB_ENDPOINT))
            .name(SUB_DOMAIN_NAME)
            .zoneId(HOSTED_ZONE_ID)
            .type("A")
            .ttl(300)
            .build();
    }
}
