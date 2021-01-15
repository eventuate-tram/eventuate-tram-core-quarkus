package io.eventuate.tram.quarkus.messaging.common;

import io.eventuate.tram.messaging.common.ChannelMapping;
import io.eventuate.tram.messaging.common.DefaultChannelMapping;
import io.quarkus.arc.DefaultBean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class TramMessagingCommonConfiguration {

  @Produces
  @DefaultBean
  public ChannelMapping channelMapping() {
    return new DefaultChannelMapping.DefaultChannelMappingBuilder().build();
  }
}
