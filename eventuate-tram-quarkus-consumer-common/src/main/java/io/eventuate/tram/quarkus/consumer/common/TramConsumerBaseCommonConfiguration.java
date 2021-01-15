package io.eventuate.tram.quarkus.consumer.common;

import io.eventuate.tram.consumer.common.*;
import io.eventuate.tram.messaging.common.MessageInterceptor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import java.util.stream.Collectors;

@ApplicationScoped
public class TramConsumerBaseCommonConfiguration {

  @Produces
  public DecoratedMessageHandlerFactory subscribedMessageHandlerChainFactory(Instance<MessageHandlerDecorator> decorators) {
    return new DecoratedMessageHandlerFactory(decorators.stream().collect(Collectors.toList()));
  }

  @Produces
  public PrePostReceiveMessageHandlerDecorator prePostReceiveMessageHandlerDecoratorDecorator(Instance<MessageInterceptor> messageInterceptors) {
    return new PrePostReceiveMessageHandlerDecorator(messageInterceptorInstanceToArray(messageInterceptors));
  }

  @Produces
  public DuplicateDetectingMessageHandlerDecorator duplicateDetectingMessageHandlerDecorator(DuplicateMessageDetector duplicateMessageDetector) {
    return new DuplicateDetectingMessageHandlerDecorator(duplicateMessageDetector);
  }

  @Produces
  public PrePostHandlerMessageHandlerDecorator prePostHandlerMessageHandlerDecorator(Instance<MessageInterceptor> messageInterceptors) {
    return new PrePostHandlerMessageHandlerDecorator(messageInterceptorInstanceToArray(messageInterceptors));
  }

  private MessageInterceptor[] messageInterceptorInstanceToArray(Instance<MessageInterceptor> messageInterceptors) {
    return messageInterceptors.stream().collect(Collectors.toList()).toArray(new MessageInterceptor[] {});
  }
}
