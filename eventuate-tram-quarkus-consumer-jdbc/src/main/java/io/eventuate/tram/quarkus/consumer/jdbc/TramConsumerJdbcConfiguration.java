package io.eventuate.tram.quarkus.consumer.jdbc;

import io.eventuate.common.jdbc.EventuateJdbcStatementExecutor;
import io.eventuate.common.jdbc.EventuateSchema;
import io.eventuate.common.jdbc.EventuateTransactionTemplate;
import io.eventuate.common.jdbc.sqldialect.SqlDialectSelector;
import io.eventuate.tram.consumer.common.DuplicateMessageDetector;
import io.eventuate.tram.consumer.jdbc.SqlTableBasedDuplicateMessageDetector;
import io.quarkus.arc.properties.UnlessBuildProperty;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class TramConsumerJdbcConfiguration {

  @Singleton
  @UnlessBuildProperty(name = "transactional.noop.duplicate.message.detector.factory.enabled", stringValue = "true", enableIfMissing = true)
  public DuplicateMessageDetector duplicateMessageDetector(@ConfigProperty(name = "eventuateDatabase") String dbName,
                                                           EventuateSchema eventuateSchema,
                                                           SqlDialectSelector sqlDialectSelector,
                                                           EventuateJdbcStatementExecutor eventuateJdbcStatementExecutor,
                                                           EventuateTransactionTemplate eventuateTransactionTemplate) {

    return new SqlTableBasedDuplicateMessageDetector(eventuateSchema,
            sqlDialectSelector.getDialect(dbName, Optional.empty()).getCurrentTimeInMillisecondsExpression(),
            eventuateJdbcStatementExecutor,
            eventuateTransactionTemplate);
  }
}
