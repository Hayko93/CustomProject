package am.client.market.dao.sql;

import am.client.market.util.DataBaseConnectionFactory;

abstract class BaseSql {
    protected DataBaseConnectionFactory dataBaseConnectionFactory;

    public BaseSql() {
        this.dataBaseConnectionFactory = DataBaseConnectionFactory.getInstance();
    }
}
