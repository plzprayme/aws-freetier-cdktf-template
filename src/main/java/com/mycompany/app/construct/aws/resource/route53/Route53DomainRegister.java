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
import com.mycompany.app.constant.Configuration.Aws;
import java.util.List;

/**
 * 도메인 등록 요청을 위한 필수 필드 Argument: domain_name - (Required) The name of the registered domain. The
 * name_server Name_Server object supports the following: name - (Required) The fully qualified host
 * name of the name server.
 */

public class Route53DomainRegister {

    // 등록할 도메인 이름
    private final String DOMAIN_NAME = Aws.DOMAIN_NAME;
    // 네임서버 이름
    private final List<String> NAME_SERVER_NAME = List.of(
        NAME_SERVER_NAME1,
        NAME_SERVER_NAME2,
        NAME_SERVER_NAME3,
        NAME_SERVER_NAME4
    );
    private final Integer DOMAIN_DURATION = Aws.DOMAIN_DURATION;

    public Route53DomainRegister() {

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
            for (String nameServerName : NAME_SERVER_NAME) {
                System.out.println("nameServerName = " + nameServerName);
            }
        } catch (DomainLimitExceededException e) {
            System.err.println("Domain limit exceeded: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }
}
