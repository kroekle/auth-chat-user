package com.kurtspace.chat.user.guice;

import com.google.inject.AbstractModule;
import com.kurtspace.chat.user.resource.ApiResource;

import org.gwizard.web.WebConfig;

public class UserModule extends AbstractModule {
    @Override
    protected void configure() {
        // All resources must be bound so they will be picked up by resteasy
        bind(ApiResource.class);
        bind(WebConfig.class).to(MyWebConfig.class);
    }

    private static final class MyWebConfig extends WebConfig {
        @Override
        public int getPort() {
            return 80;
        }
    }

}
