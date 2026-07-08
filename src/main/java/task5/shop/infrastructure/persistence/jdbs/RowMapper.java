package ru.itis.shop.infrastructure.persistence.jdbs;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    public T mapRow(ResultSet row) throws SQLException;
}
