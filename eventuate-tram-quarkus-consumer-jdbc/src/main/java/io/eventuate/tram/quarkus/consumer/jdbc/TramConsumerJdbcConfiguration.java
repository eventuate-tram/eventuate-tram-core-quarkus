package io.eventuate.tram.quarkus.consumer.jdbc;

import io.eventuate.common.jdbc.EventuateJdbcStatementExecutor;
import io.eventuate.common.jdbc.EventuateSchema;
import io.eventuate.common.jdbc.EventuateTransactionTemplate;
import io.eventuate.common.jdbc.sqldialect.SqlDialectSelector;
import io.eventuate.tram.consumer.common.DuplicateMessageDetector;
import io.eventuate.tram.consumer.jdbc.SqlTableBasedDuplicateMessageDetector;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Optional;

@ApplicationScoped
public class TramConsumerJdbcConfiguration {

  @Produces
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
