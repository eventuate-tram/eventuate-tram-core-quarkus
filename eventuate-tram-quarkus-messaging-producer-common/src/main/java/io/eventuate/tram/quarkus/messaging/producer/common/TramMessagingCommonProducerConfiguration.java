package io.eventuate.tram.quarkus.messaging.producer.common;

import io.eventuate.tram.messaging.common.ChannelMapping;
import io.eventuate.tram.messaging.common.MessageInterceptor;
import io.eventuate.tram.messaging.producer.MessageProducer;
import io.eventuate.tram.messaging.producer.common.MessageProducerImpl;
import io.eventuate.tram.messaging.producer.common.MessageProducerImplementation;

import javax.enterprise.inject.Instance;
import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class TramMessagingCommonProducerConfiguration {
  @Singleton
  public MessageProducer messageProducer(Instance<MessageInterceptor> messageInterceptors,
                                         Instance<ChannelMapping> channelMapping,
                                         Instance<MessageProducerImplementation> implementation) {
    return new MessageProducerImpl(messageInterceptors.stream().collect(Collectors.toList()).toArray(new MessageInterceptor[] {}),
            channelMapping.get(), implementation.get());
  }
}
