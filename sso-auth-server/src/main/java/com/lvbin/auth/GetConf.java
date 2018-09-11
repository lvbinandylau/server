package com.lvbin.auth;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix="spring")
public class GetConf {
    private List<Map<String, String>> datasource = new ArrayList<>();

    public List<Map<String, String>> getDatasource() {
        return datasource;
    }
}
