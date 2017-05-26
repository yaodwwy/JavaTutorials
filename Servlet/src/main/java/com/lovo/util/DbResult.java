package com.lovo.util;

public class DbResult {
	private int affectedRows;
	private long generatedKey;

	public DbResult(int affectedRows, long generatedKey) {
		this.affectedRows = affectedRows;
		this.generatedKey = generatedKey;
	}

	public int getAffectedRows() {
		return affectedRows;
	}

	public long getGeneratedKey() {
		return generatedKey;
	}

}
