package com.spotlight.platform.userprofile.api.web.modules;

import com.google.inject.AbstractModule;
import com.spotlight.platform.userprofile.api.core.logging.ILogger;
import com.spotlight.platform.userprofile.api.core.logging.PhoenixLogger;
import com.spotlight.platform.userprofile.api.core.profile.UserProfileService;
import com.spotlight.platform.userprofile.api.core.profile.CommandProcessors.CommandProcessFactory;
import com.spotlight.platform.userprofile.api.core.profile.CommandProcessors.ICommandProcessFactory;
import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDao;
import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDaoInMemory;

import javax.inject.Singleton;

public class ProfileModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ILogger.class).to(PhoenixLogger.class)
        .in(Singleton.class);


        bind(UserProfileDao.class).to(UserProfileDaoInMemory.class).in(Singleton.class);
        bind(ICommandProcessFactory.class).to(CommandProcessFactory.class);
        bind(UserProfileService.class).in(Singleton.class);
    }
}
