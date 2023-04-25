FROM alpine:3.17.3

COPY ./application application
COPY ./infrastructure infrastructure

RUN apk update && \
    apk add curl unzip openjdk17 maven nodejs npm && \
    npm install --global cdktf-cli@latest && \
    curl -LO https://releases.hashicorp.com/terraform/1.1.0/terraform_1.1.0_linux_amd64.zip && \
    unzip terraform_1.1.0_linux_amd64.zip && \
    mv terraform /usr/local/bin/ && \
    cd /infrastructure && \
    cdktf provider add "aws@~>4.0"

ENTRYPOINT ["tail", "-f", "/dev/null"]
