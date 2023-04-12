//package com.mycompany.app.construct.aws.resource.elastic_load_balancing;
//
//import com.hashicorp.cdktf.providers.aws.alb.Alb;
//import com.hashicorp.cdktf.providers.aws.alb.Alb.Builder;
//import com.hashicorp.cdktf.providers.aws.alb.AlbAccessLogs;
//import com.hashicorp.cdktf.providers.aws.subnet.Subnet;
//import com.mycompany.app.constant.Constant.Version;
//import com.mycompany.app.construct.Provisonable;
//import software.constructs.Construct;
//
///*
//[ access_logs ]
//    bucket - (Required)
//        The S3 bucket name to store the logs in.
//
//[ subnet_mapping ]
//    subnet_id (Required)
//        ID of the subnet of which to attach to the load balancer.
//        You can specify only one subnet per Availability Zone.
//
//resource "aws_lb" "test" {
//  name               = "test-lb-tf"
//  internal           = false
//  load_balancer_type = "application"
//  security_groups    = [aws_security_group.lb_sg.id]
//  subnets            = [for subnet in aws_subnet.public : subnet.id]
//
//  enable_deletion_protection = true
//
//  access_logs {
//    bucket  = aws_s3_bucket.lb_logs.id
//    prefix  = "test-lb"
//    enabled = true
//  }
//
//  tags = {
//    Environment = "production"
//  }
//}
//
// */
//
//public class ElasticLoadBalancerRegister implements Provisonable<Alb> {
//
//    private final String ALB_NAME = Version.PROJECT_NAME + "-alb";
//
//    @Override
//    public Alb provision(Construct scope) {
//        Alb alb = Builder
//            .create(scope, "test")
//            .accessLogs(AlbAccessLogs.builder()
//                .bucket("S3 Bucket Name For AccessLog logging")
//                .build())
//            .subnetMapping(
//                Subnet.Builder
//                    .create(scope, "testSubnetId")
//                    .build())
//            .build();
//        return alb;
//    }
//}
