package com.boozedb.api.model.bottle;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DomesticWhiskeyBottleRowMapper implements RowMapper<Bottle> {
    @Override
    public Bottle map(ResultSet rs, StatementContext ctx) throws SQLException {

        return new Bottle.Builder()
                .age(rs.getInt("age"))
                .id(rs.getInt("id"))
                .item(rs.getString("item"))
                .category(rs.getString("category"))
                .subCategory(rs.getString("subCategory"))
                .proof(rs.getDouble("proof"))
                .description(rs.getString("description"))
                .size(rs.getDouble("size"))
                .price(rs.getDouble("price")).build();

    }
}
