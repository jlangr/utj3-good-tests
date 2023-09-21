package iloveyouboss.functional;

import java.sql.SQLException;

@FunctionalInterface
public interface CheckedConsumer<T> {
   void accept(T t) throws SQLException;
}
