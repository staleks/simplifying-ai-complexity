package com.jatheon.ergo.ai.assistant.config.langchain4j;

import dev.langchain4j.model.bedrock.BedrockAnthropicMessageChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;

@Configuration
public class BedrockAnothropicClaudeV2LanguageModelConfig {

    //~ Credentials
    @Value("${aws.bedrock.accessKey}")
    private String accessKey;

    @Value("${aws.bedrock.secretKey}")
    private String secretKey;

    @Value("${aws.bedrock.region}")
    private String regionName;

    //~ Creativity
    @Value("${aws.bedrock.temp:0.5}")
    private double temp;

    //~ Filter out cumulative probability is less than a specified threshold
    @Value("${aws.bedrock.topP:0.3f}")
    private float topP;

    //~ Limits the model output to the top-k most probable tokens
    @Value("${aws.bedrock.topK:50}")
    private int topK;

    @Value("${aws.bedrock.maxRetries:1}")
    private int maxRetries;

    private AwsCredentialsProvider credentialsProvider() {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey));
    }

    @Bean(name = "bedrockAnthropicClaude2ChatLanguageModel")
    public ChatLanguageModel bedrockAnthropicClaude2ChatLanguageModel() {
        return BedrockAnthropicMessageChatModel.builder()
                .region(Region.of(regionName))
                .model(BedrockAnthropicMessageChatModel.Types.AnthropicClaudeV2.getValue())
                .temperature(temp)
                .topP(topP)
                .topK(topK)
                .maxRetries(maxRetries)
                .credentialsProvider(credentialsProvider())
                .build();
    }

}
