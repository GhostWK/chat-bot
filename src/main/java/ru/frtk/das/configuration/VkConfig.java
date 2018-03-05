package ru.frtk.das.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("vk")
public class VkConfig {
    private Integer groupId;
    private String accessToken;

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

}
