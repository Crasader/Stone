package com.stone.core.db.service.cassandra;

import com.datastax.driver.core.ResultSet;
import com.stone.core.db.service.IDBService;

public interface ICassandraDBService extends IDBService {
	/**
	 * Execute the cassandra query language;
	 * 
	 * @param cql
	 */
	public ResultSet executeCQL(String cql);
}
