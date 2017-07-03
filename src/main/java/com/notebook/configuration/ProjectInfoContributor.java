package com.notebook.configuration;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProjectInfoContributor implements InfoContributor {

    private static final String TIME = "Current local time with time zone";

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> project = new HashMap<>();
        project.put(TIME, ZonedDateTime.now().toString());
        builder.withDetail("project", project);
    }
}
