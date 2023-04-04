package com.mycompany.app.construct.aws.resource.route53;

import com.amazonaws.services.route53domains.AmazonRoute53Domains;
import com.amazonaws.services.route53domains.AmazonRoute53DomainsClientBuilder;
import com.amazonaws.services.route53domains.model.ContactDetail;
import com.amazonaws.services.route53domains.model.ContactType;
import com.amazonaws.services.route53domains.model.DomainLimitExceededException;
import com.amazonaws.services.route53domains.model.InvalidInputException;
import com.amazonaws.services.route53domains.model.Nameserver;
import com.amazonaws.services.route53domains.model.RegisterDomainRequest;
import com.amazonaws.services.route53domains.model.RegisterDomainResult;

public class Route53DomainRegister {

    public static void main(String[] args) {

        String domainName = "example.com"; // 등록할 도메인 이름
        String adminContactFirstName = "John"; // 도메인 관리자의 이름
        String adminContactLastName = "Doe"; // 도메인 관리자의 성
        String adminContactEmail = "john.doe@example.com"; // 도메인 관리자의 이메일
        String adminContactPhoneNumber = "+1.5555555555"; // 도메인 관리자의 전화번호
        String adminContactAddressLine1 = "123 Main St"; // 도메인 관리자의 주소
        String adminContactCity = "Anytown"; // 도메인 관리자의 도시
        String adminContactState = "NY"; // 도메인 관리자의 주
        String adminContactCountryCode = "US"; // 도메인 관리자의 국가 코드
        String adminContactZipCode = "12345"; // 도메인 관리자의 우편번호

        AmazonRoute53Domains client = AmazonRoute53DomainsClientBuilder.standard().build();
        ContactDetail adminContactDetail = new ContactDetail()
            .withFirstName(adminContactFirstName)
            .withLastName(adminContactLastName)
            .withEmail(adminContactEmail)
            .withPhoneNumber(adminContactPhoneNumber)
            .withAddressLine1(adminContactAddressLine1)
            .withCity(adminContactCity)
            .withState(adminContactState)
            .withCountryCode(adminContactCountryCode)
            .withZipCode(adminContactZipCode)
            .withContactType(ContactType.ADMINISTRATIVE);

        RegisterDomainRequest request = new RegisterDomainRequest()
            .withDomainName(domainName)
            .withDurationInYears(1)
            .withAdminContact(adminContactDetail);

        try {
            RegisterDomainResult result = client.registerDomain(request);
            System.out.println("Registered domain: " + result.getDomainName());
            for (Nameserver nameserver : result.getNameservers()) {
                System.out.println("Nameserver: " + nameserver.getName());
            }
        } catch (DomainLimitExceededException e) {
            System.err.println("Domain limit exceeded: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }
}
}
