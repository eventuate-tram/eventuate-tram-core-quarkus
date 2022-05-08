package io.eventuate.tram.quarkus.commands.common;


import io.eventuate.tram.commands.common.CommandNameMapping;
import io.eventuate.tram.commands.common.DefaultCommandNameMapping;
import io.quarkus.arc.DefaultBean;

import javax.inject.Singleton;

@Singleton
public class TramCommandCommonConfiguration {

    @Singleton
    @DefaultBean
    public CommandNameMapping defaultCommandNameMapping() {
        return new DefaultCommandNameMapping();
    }
}
