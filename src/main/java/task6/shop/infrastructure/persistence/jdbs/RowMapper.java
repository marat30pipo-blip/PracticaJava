package task6.shop.infrastructure.persistence.jdbs;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T mapRow(ResultSet row) throws SQLException;
}
