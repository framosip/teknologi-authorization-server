package br.com.teknologi.as.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import lombok.extern.slf4j.Slf4j;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableDynamoDBRepositories(basePackages = "br.com.teknologi.as.repository")
@Slf4j
public class DynamoDBConfig {

    @Value("${aws.endpoint}")
    private String awsEndpoint;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.access-key}")
    private String awsAccessKey;

    @Value("${aws.secret-key}")
    private String awsSecretKey;

    @Value("${aws.dynamodb.prefix}")
    private String prefix;

    private AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        log.info("[Configuration] ===== AWSCredentials bean configured =====");
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        log.info("[Configuration] ===== AmazonDynamoDB bean configured =====");
        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(amazonAWSCredentialsProvider())
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsEndpoint, awsRegion))
                .build();
    }

    @Bean
    public DynamoDBMapperConfig.TableNameOverride tableNameOverride() {
        log.info("[Configuration] ===== DynamoDBMapperConfig.TableNameOverride bean configured =====");
        return DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(prefix);
    }

    @Bean
    @Primary
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        log.info("[Configuration] ===== DynamoDBMapperConfig bean configured =====");
        return DynamoDBMapperConfig.builder()
                .withTableNameOverride(tableNameOverride())
                .build();
    }

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper() {
        log.info("[Configuration] ===== DynamoDBMapper bean configured =====");
        return new DynamoDBMapper(amazonDynamoDB(), dynamoDBMapperConfig());
    }

}