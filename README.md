# covapi

This is a sample to demonstrate how to leverage AWS Lambda Web adapter to integrate the web application container runtime inside lambda function.


## Walk you through the sample

<b>Prerequisite</b>

It is mandatory to have below services installed before executing the build, run, test steps: 

- Docker environment (v.24.0.2 or compatible)
- [CURL tool](https://curl.se/)

If you would like to review the sample code locally, the JDK 17 and Maven 3.8.6 Build tool is mandatory as well.


<b>Reference library & architecture</b>
[AWS Lambda Web Adapter](https://github.com/awslabs/aws-lambda-web-adapter)

### Build the sample

change the dictionary to the sample porject, e.g. ~/git/covapi, then : 
```shell
docker build -t covapi .
```

### Run the sample
```shell
$ docker run -d -p 8080:8080 covapi
```

### Test- hit the API locally
```shell
$ curl http://127.0.0.1:8080/covapi/kimDemo/testGet                                                                      ✔  at 11:12:02 

```
The result should be : {"result":"success with path covapi-kimDemo-testGet"}

```shell
$ curl http://127.0.0.1:8080/covapi/mobile/cov/testGet
```
The result should be : {"result":"success with path covapi-mobile-cov-testGet"}

### Deploy to AWS and integrate with API Gateway

Deploy to Lambda
Use the SAM CLI to build a container image

```shell
$ aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws
$ sam build
```

This command compiles the application and prepares a deployment package in the .aws-sam sub-directory.

To deploy the application in your AWS account, you can use the SAM CLI's guided deployment process and follow the instructions on the screen
```shell
$ sam deploy --guided
```

Please take note of the container image name. Once the deployment is completed, the SAM CLI will print out the stack's outputs, including the new application URL. You can use curl or a web browser to make a call to the URL

### Remove the base path
The application is deployed under /v1/{proxy+}. But the application does not know that. So in the SAM template file, we configured environment variable REMOVE_BASE_PATH=/v1. This configuration tells the Adapter to remove /v1 from http request path, so that the pet store application works without changing code.

