package io.eventuate.tram.quarkus.consumer.common;

import io.eventuate.tram.consumer.common.*;
import io.eventuate.tram.messaging.common.MessageInterceptor;

import javax.enterprise.inject.Instance;
import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class TramConsumerBaseCommonConfiguration {

  @Singleton
  public DecoratedMessageHandlerFactory subscribedMessageHandlerChainFactory(Instance<MessageHandlerDecorator> decorators) {
    return new DecoratedMessageHandlerFactory(decorators.stream().collect(Collectors.toList()));
  }

  @Singleton
  public PrePostReceiveMessageHandlerDecorator prePostReceiveMessageHandlerDecoratorDecorator(Instance<MessageInterceptor> messageInterceptors) {
    return new PrePostReceiveMessageHandlerDecorator(messageInterceptorInstanceToArray(messageInterceptors));
  }

  @Singleton
  public DuplicateDetectingMessageHandlerDecorator duplicateDetectingMessageHandlerDecorator(DuplicateMessageDetector duplicateMessageDetector) {
    return new DuplicateDetectingMessageHandlerDecorator(duplicateMessageDetector);
  }

  @Singleton
  public PrePostHandlerMessageHandlerDecorator prePostHandlerMessageHandlerDecorator(Instance<MessageInterceptor> messageInterceptors) {
    return new PrePostHandlerMessageHandlerDecorator(messageInterceptorInstanceToArray(messageInterceptors));
  }

  private MessageInterceptor[] messageInterceptorInstanceToArray(Instance<MessageInterceptor> messageInterceptors) {
    return messageInterceptors.stream().collect(Collectors.toList()).toArray(new MessageInterceptor[] {});
  }
}
