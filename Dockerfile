FROM maven:3.8.4-eclipse-temurin-17-alpine

RUN apk update && apk add curl unzip
RUN curl -LO https://releases.hashicorp.com/terraform/1.1.0/terraform_1.1.0_linux_amd64.zip && unzip terraform_1.1.0_linux_amd64.zip
RUN mv terraform /usr/local/bin/ && terraform version

RUN apk add nodejs
RUN npm install --global cdktf-cli@latest

ENTRYPOINT ["tail", "-f", "/dev/null"]