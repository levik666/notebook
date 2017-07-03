package com.notebook.configuration;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class ProjectInfoContributor implements InfoContributor{

    private String version;

    public ProjectInfoContributor() {
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("project", version);
    }
}
