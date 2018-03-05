package ru.frtk.das.configuration;

import com.petersamokhin.bots.sdk.clients.Group;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(VkConfig.class)
public class ApplicationConfig {

    @Bean
    public Group vkBotGroup(VkConfig config) {
        return new Group(config.getGroupId(), config.getAccessToken());
    }
}
