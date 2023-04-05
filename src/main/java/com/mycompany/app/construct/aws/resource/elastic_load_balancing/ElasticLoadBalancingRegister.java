package com.mycompany.app.construct.aws.resource.elastic_load_balancing;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.elasticloadbalancingv2.AmazonElasticLoadBalancing;
import com.amazonaws.services.elasticloadbalancingv2.AmazonElasticLoadBalancingClientBuilder;
import com.amazonaws.services.elasticloadbalancingv2.model.CreateLoadBalancerRequest;
import com.amazonaws.services.elasticloadbalancingv2.model.CreateLoadBalancerResult;
import com.hashicorp.cdktf.IResolvable;
import com.hashicorp.cdktf.providers.aws.route53_domains_registered_domain.Route53DomainsRegisteredDomain;
import com.mycompany.app.constant.Constant.Version;
import com.mycompany.app.construct.Provisonable;
import software.constructs.Construct;

/*
[ access_logs ]
    bucket - (Required)
        The S3 bucket name to store the logs in.

[ subnet_mapping ]
    subnet_id (Required)
        ID of the subnet of which to attach to the load balancer.
        You can specify only one subnet per Availability Zone.

 */

public class ElasticLoadBalancingRegister implements Provisonable<AmazonElasticLoadBalancing> {

    private final String ALB_NAME = Version.PROJECT_NAME + "-alb";
    private final AWSCredentialsProvider credentialsProvider =
        new DefaultAWSCredentialsProviderChain();

    private static final String RESOURCE_TYPE = "aws_lb";
    private static final String RESOURCE_NAME = "my-load-balancer";
    private static final String LOAD_BALANCER_NAME = "my-load-balancer";
    private static final boolean INTERNAL = false;
    private static final String LOAD_BALANCER_TYPE = "application";
    private static final String SUBNET_ID_1 = "subnet-0123456789abcdef0";
    private static final String SUBNET_ID_2 = "subnet-0123456789abcdef1";
    private static final String SECURITY_GROUP_ID = "sg-0123456789abcdef";

    @Override
    public AmazonElasticLoadBalancing provision(Construct scope) {
        createELBObjectByCollaboratingAwsSdk();
        return null;
    }

    public void createELBObjectByCollaboratingAwsSdk() {

        // ALB 클라이언트 생성
        AmazonElasticLoadBalancing loadBalancingClient =
            AmazonElasticLoadBalancingClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();

        // 로드 밸런서 생성 요청 생성
        CreateLoadBalancerRequest request = new CreateLoadBalancerRequest()
            .withName(ALB_NAME)
            .withType(LOAD_BALANCER_TYPE)
            .withSubnets()
            .withSecurityGroups()
            .withSubnetMappings();


        // 로드 밸런서 생성
        CreateLoadBalancerResult result = loadBalancingClient.createLoadBalancer(request);

        System.out.println("로드 밸런서 생성 완료");
        System.out.println(
            "LoadBalancerName = " + result.getLoadBalancers().get(0).getLoadBalancerName() +
            "LoadBalancerArn = " + result.getLoadBalancers().get(0).getLoadBalancerArn() +
            "LoadBalancerDNSName = " + result.getLoadBalancers().get(0).getDNSName() +
            "LoadBalancerVpcId = " + result.getLoadBalancers().get(0).getVpcId() +
            "LoadBalancerCreatedTime = " + result.getLoadBalancers().get(0).getCreatedTime()
        );
    }
}
