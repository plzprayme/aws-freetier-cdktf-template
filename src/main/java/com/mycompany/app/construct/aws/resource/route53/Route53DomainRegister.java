package com.mycompany.app.construct.aws.resource.route53;

import static com.mycompany.app.constant.Configuration.Aws.NAME_SERVER_NAME1;
import static com.mycompany.app.constant.Configuration.Aws.NAME_SERVER_NAME2;
import static com.mycompany.app.constant.Configuration.Aws.NAME_SERVER_NAME3;
import static com.mycompany.app.constant.Configuration.Aws.NAME_SERVER_NAME4;

import com.amazonaws.services.route53domains.AmazonRoute53Domains;
import com.amazonaws.services.route53domains.AmazonRoute53DomainsClientBuilder;
import com.amazonaws.services.route53domains.model.ContactDetail;
import com.amazonaws.services.route53domains.model.DomainLimitExceededException;
import com.amazonaws.services.route53domains.model.InvalidInputException;
import com.amazonaws.services.route53domains.model.RegisterDomainRequest;
import com.hashicorp.cdktf.IResolvable;
import com.hashicorp.cdktf.providers.aws.route53_domains_registered_domain.Route53DomainsRegisteredDomain;
import com.mycompany.app.constant.Configuration.Aws;
import com.mycompany.app.construct.Provisonable;
import java.util.List;
import software.constructs.Construct;

/*
[ 도메인 등록 요청을 위한 필수 필드 ]

Argument:
    domain_name - (Required) The name of the registered domain.

The name_server Name_Server object supports the following:
    name - (Required) The fully qualified host name of the name server.

--------------------------------------------------------------------------

- 사용자가 Domain 을 가지고 있다고 가정한다.
- 배포 후 생성되는 ALB의 Endpoint로 라우팅이 되어야한다.
- Route53 에 사용자의 Domain이 등록되어있고, Route53에서 DNS RECORD에 ALB를 등록한다.
 */

public class Route53DomainRegister implements Provisonable<Route53DomainsRegisteredDomain> {

    // 등록할 도메인 이름
    private final String DOMAIN_NAME = Aws.DOMAIN_NAME;
    // 네임서버 이름
    private final List<String> NAME_SERVER_NAME = List.of(
        NAME_SERVER_NAME1,
        NAME_SERVER_NAME2,
        NAME_SERVER_NAME3,
        NAME_SERVER_NAME4
    );
    // 도메인 등록 기간
    private final Integer DOMAIN_DURATION = Aws.DOMAIN_DURATION;

    @Override
    public Route53DomainsRegisteredDomain provision(Construct scope) {
        enrollRoute53DomainByCollaboratingAwsSdk();

        return Route53DomainsRegisteredDomain.Builder
            .create(scope, "test")
            .domainName(DOMAIN_NAME)
            .nameServer((IResolvable) NAME_SERVER_NAME)
            .build();
    }

    // AWS에 도메인 등록하기
    private void enrollRoute53DomainByCollaboratingAwsSdk() {
        // 클라이언트 받아오기
        AmazonRoute53Domains client = AmazonRoute53DomainsClientBuilder.standard().build();

        // 도메인 등록자의 정보
        ContactDetail adminContactDetail = new ContactDetail();

        // 도메인 등록 요청 객체 생성
        RegisterDomainRequest request = new RegisterDomainRequest()
            .withDomainName(DOMAIN_NAME)
            .withDurationInYears(DOMAIN_DURATION)
            .withAdminContact(adminContactDetail);

        try {
            // 도메인 등록 요청을 보낸다.
            client.registerDomain(request);
            System.out.println(DOMAIN_NAME + " <---- 도메인 등록 완료\n");
            for (String nameServerName : NAME_SERVER_NAME) {
                System.out.println("nameServerName = " + nameServerName + "\n");
            }
        } catch (DomainLimitExceededException e) {
            System.err.println("Domain limit exceeded: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }
}
