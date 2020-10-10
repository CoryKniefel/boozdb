package com.boozdb.db.dao;

import com.boozdb.api.model.whiskey.Bottle;
import com.boozdb.api.model.whiskey.DomesticWhiskeyBottleRowMapper;
import com.boozdb.core.constants.QueryParamToColumnMap;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BoozDbDao {

    private final Jdbi jdbi;

    public BoozDbDao(Jdbi jdbi)
    {
        this.jdbi = jdbi;
    }

    public Optional<Bottle> getBottleById(String id)
    {
        Optional<Bottle> result;
        String temp = String.format("select * from bottles where id = \"%s\"", id);
        try
        {
            result = jdbi.withHandle(handle -> handle.createQuery(temp)
                    .setMaxRows(1)
                    .map(new DomesticWhiskeyBottleRowMapper())
                    .findFirst());
        }
        catch (RuntimeException e) {
            result = Optional.empty();
        }

        return result;
    }

    public List<Bottle> search(Map<QueryParamToColumnMap, String> paramMap) {

        StringBuilder queryBuilder = new StringBuilder("select * from bottles");
        String wereParams = joinParams(paramMap);
        String limitParams = pagination(paramMap);

        if(! wereParams.isEmpty())
        {
            queryBuilder.append(" where ").append(wereParams);
        }

        String fullQuery = queryBuilder.append(" ").append(limitParams).toString();

        List<Bottle> result = jdbi.withHandle(handle ->
                handle.createQuery(fullQuery)
                        .map(new DomesticWhiskeyBottleRowMapper())
                        .list());

        return result;

    }

    /**
     * Takes a map of pagination query parameters and returns a formatted string of sql like <br>
     *     limit 0,25
     *
     * @param paramMap
     * @return
     */
    private String pagination(Map<QueryParamToColumnMap, String> paramMap)
    {
        StringBuilder result = new StringBuilder();

        String offset = paramMap.get(QueryParamToColumnMap.PAGE_OFFSET);
        String pageSize = paramMap.get(QueryParamToColumnMap.PAGE_SIZE);

        String temp = String.format(QueryParamToColumnMap.PAGE_SIZE.getSqlFormat(), offset, pageSize);
        result.append(temp);

        return result.toString();
    }

    private String joinParams(Map<QueryParamToColumnMap, String> paramMap)
    {
        StringBuilder result = new StringBuilder();

        int count = 0;
        for(QueryParamToColumnMap q : paramMap.keySet())
        {
            if(q.equals(QueryParamToColumnMap.PAGE_OFFSET) | q.equals(QueryParamToColumnMap.PAGE_SIZE))
            {
                continue; // page params don't go in were clause
            }

            if(count > 0)
            {
                result.append(" and ");
                String temp = String.format(q.getSqlFormat(), paramMap.get(q));
                result.append(temp);
            }else
            {
                String temp = String.format(q.getSqlFormat(), paramMap.get(q));
                result.append(temp);
            }
            count++;

        }

        return result.toString();

    }
}
