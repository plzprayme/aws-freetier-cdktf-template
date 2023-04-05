# CDKTF Template for AWS Freetier

## Goals
Deploy infrastructure just a command line.  
deployed infrastructure will be very powerful:
* Log Visualization
* Blue Green Deploy
* static file storage
* RDBMS
* DNS

## How to use

### .env.tmpl

```text
REGION="Your AWS Account Region"
AWS_ACCESS_KEY="Your AWS ACCESS KEY"
AWS_SECRET_KEY="Your AWS SECRET KEY"

DOMAIN_NAME="Your Domain Name"
NAME_SERVER_NAME1="Your Domain's NameServer1"
NAME_SERVER_NAME2="Your Domain's NameServer2"
NAME_SERVER_NAME3="Your Domain's NameServer3"
NAME_SERVER_NAME4="Your Domain's NameServer4"
DOMAIN_DURATION="Your Domain's DURATION"

PROJECT_NAME="Prefix all of the resource"
COMMIT_HASH="Your Git Commit's Hash"

EB_SOLUTION_STACK="EC2's Application Runtime"
SOURCE_BUNDLE_PATH="The local location of the JAR file running on EC2. (The reason why this is necessary is because when tf deploy is performed, the JAR file to be deployed to S3 is uploaded. So S3 needs to know where the JAR file is located.)"

TF_CLOUD_ORGANIZATION="Terraform Workapce name"
TF_WORKSPACE="Terraform Workspace ID"
TF_TOKEN="Your Terraform Cloud Token"
```


### Prerequisites
* [The Terraform CLI (1.1+).](https://developer.hashicorp.com/terraform/tutorials/aws-get-started/install-cli)
* [Node.js and npm v16+.](https://nodejs.org/en/)
* [Open JDK 17](https://adoptium.net/temurin/releases/) and [Maven 3.8.4+](https://maven.apache.org/download.cgi)
* [Terraform Cloud Account](https://cloud.hashicorp.com/products/terraform)

#### Configuration variables
you want run this proejct. you must export variables.   
Check `Configuration, Constant` classes under the `constant` package.  


### Deploy
Place your application (docker, jar , python, js file... whatever elastic beanstalk solution stack) under $SOURCE_BUNDLE_PATH  

and just type `cdktf deploy`

### Reference
* https://registry.terraform.io/providers/hashicorp/aws/latest/docs#argument-reference

