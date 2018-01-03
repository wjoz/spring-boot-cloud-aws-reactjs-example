# springcloud-aws-example
The project contains an implementation of a sample Spring Boot application integrated with the AWS S3 storage thanks to the Spring Cloud AWS project.

# How to Build and Run

1. Generate your AWS credentials and create an S3 bucket in the AWS Web Console.
2. Set your generated AWS security credentials: the access and secret key in and the S3 bucket
in application.properties by using the following properties: *cloud.aws.credentials.accessKey*, *cloud.aws.credentials.accessKey* and *cloud.aws.s3.bucket* 
3. To build and run the process (application), use the following command: mvn **spring-boot:run
4. By default, the application will run on port 8080.
5. Visit the [http://localhost:8080](http://localhost:8080) to try the application.