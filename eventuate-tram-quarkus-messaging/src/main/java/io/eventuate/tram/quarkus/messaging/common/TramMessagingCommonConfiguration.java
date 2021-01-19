package io.eventuate.tram.quarkus.messaging.common;

import io.eventuate.tram.messaging.common.ChannelMapping;
import io.eventuate.tram.messaging.common.DefaultChannelMapping;
import io.quarkus.arc.DefaultBean;

import javax.inject.Singleton;

@Singleton
public class TramMessagingCommonConfiguration {

  @Singleton
  @DefaultBean
  public ChannelMapping channelMapping() {
    return new DefaultChannelMapping.DefaultChannelMappingBuilder().build();
  }
}
