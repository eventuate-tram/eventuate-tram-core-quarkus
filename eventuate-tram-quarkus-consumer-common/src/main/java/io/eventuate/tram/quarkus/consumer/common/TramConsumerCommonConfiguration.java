package io.eventuate.tram.quarkus.consumer.common;

import io.eventuate.tram.consumer.common.DecoratedMessageHandlerFactory;
import io.eventuate.tram.consumer.common.MessageConsumerImpl;
import io.eventuate.tram.consumer.common.MessageConsumerImplementation;
import io.eventuate.tram.messaging.common.ChannelMapping;
import io.eventuate.tram.messaging.consumer.DefaultSubscriberMapping;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import io.eventuate.tram.messaging.consumer.SubscriberMapping;
import io.quarkus.arc.DefaultBean;

import javax.enterprise.inject.Instance;
import javax.inject.Singleton;

@Singleton
public class TramConsumerCommonConfiguration {

  @Singleton
  public MessageConsumer messageConsumer(Instance<MessageConsumerImplementation> messageConsumerImplementation,
                                         Instance<ChannelMapping> channelMapping,
                                         DecoratedMessageHandlerFactory decoratedMessageHandlerFactory, SubscriberMapping subscriberMapping) {
    return new MessageConsumerImpl(channelMapping.get(), messageConsumerImplementation.get(), decoratedMessageHandlerFactory, subscriberMapping);
  }

  @Singleton
  @DefaultBean
  public SubscriberMapping subscriberMapping() {
    return new DefaultSubscriberMapping();
  }

}
