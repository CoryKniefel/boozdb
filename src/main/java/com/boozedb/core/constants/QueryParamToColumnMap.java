package com.boozedb.core.constants;

public enum QueryParamToColumnMap {

    MIN_PRICE("price", "price >= %s"),
    MAX_PRICE("price", "price <= %s"),
    MIN_AGE("age", "age >= %s"),
    MAX_AGE("age", "age <= %s"),
    MIN_PROOF("proof", "proof >= %s"),
    MAX_PROOF("proof", "proof <= %s"),
    MIN_SIZE("size", "size >= %s"),
    MAX_SIZE("size", "size <= %s"),
    CATEGORY("category", "category = \"%s\""),
    SUB_CATEGORY("subCategory", "subCategory = \"%s\""),
    DESCRIPTION("description", "description = \"%s\""),
    NAME("name", "name = \"%s\""),

    /**
     * sqlFormat: single param LIMIT expression, like 'LIMIT 80'
     */
    PAGE_OFFSET("page", "limit %s"),
    /**
     * sqlFormat: two param LIMIT expression, like 'LIMIT 150, 10'
     */
    PAGE_SIZE("pageSize", "limit %s, %s"),

    ;

    public final String column;
    public final String sqlFormat;

    QueryParamToColumnMap(String column, String sqlFormat)
    {
        this.column = column;
        this.sqlFormat = sqlFormat;

    }

    public String getColumn()
    {
        return this.column;
    }

    public String getSqlFormat()
    {
        return this.sqlFormat;
    }

}
