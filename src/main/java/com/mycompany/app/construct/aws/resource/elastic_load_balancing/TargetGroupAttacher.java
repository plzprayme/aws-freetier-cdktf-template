package com.mycompany.app.construct.aws.resource.elastic_load_balancing;

import com.hashicorp.cdktf.providers.aws.alb_target_group.AlbTargetGroup;
import com.hashicorp.cdktf.providers.aws.alb_target_group.AlbTargetGroup.Builder;
import com.mycompany.app.construct.Provisonable;
import software.constructs.Construct;

/*
[ 도메인 등록 요청을 위한 필수 필드 ]

resource "aws_lb_target_group" "alb-example" {
  name        = "tf-example-lb-alb-tg"
  target_type = "alb"
  port        = 80
  protocol    = "TCP"
  vpc_id      = aws_vpc.main.id
}


port:
    Port on which targets receive traffic, unless overridden when registering a specific target.
    Required when target_type is instance, ip or alb. Does not apply when target_type is lambda.

protocol:
    Protocol to use for routing traffic to the targets.
    Should be one of GENEVE, HTTP, HTTPS, TCP, TCP_UDP, TLS, or UDP.
    Required when target_type is instance, ip or alb. Does not apply when target_type is lambda.

target_type:
    Type of target that you must specify when registering targets with this target group.
    See doc for supported values. The default is instance.

vpc_id:
    Identifier of the VPC in which to create the target group.
    Required when target_type is instance, ip or alb. Does not apply when target_type is lambda.

 */

public class TargetGroupAttacher implements Provisonable<AlbTargetGroup> {

    @Override
    public AlbTargetGroup provision(Construct scope) {
        AlbTargetGroup albTargetGroup = Builder
            .create(scope, "test")
            .targetType("alb")
            .protocol("TCP")
            .port(80)
            .vpcId("vpcId")
            .build();

        printResult(albTargetGroup);

        return albTargetGroup;
    }

    private void printResult(AlbTargetGroup albTargetGroup) {
        System.out.println("[Success] ALB Target Group is Created");
        System.out.println("targetGroup Id = " + albTargetGroup.getId());
        System.out.println("targetGroup Arn = " + albTargetGroup.getArn());
        System.out.println("targetGroup TargetType = " + albTargetGroup.getTargetType());
        System.out.println("targetGroup Protocol = " + albTargetGroup.getProtocol());
        System.out.println("targetGroup Port = " + albTargetGroup.getPort());
        System.out.println("targetGroup VpcId = " + albTargetGroup.getVpcId());
        System.out.println("------------------------------------");
    }
}
