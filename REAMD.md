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

