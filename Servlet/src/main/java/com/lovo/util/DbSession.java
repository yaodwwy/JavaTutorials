package com.lovo.util;

import com.lovo.exception.DbSessionException;

import java.sql.*;

/**
 * ���ݿ�Ự
 * @author ���
 *
 */
public class DbSession {
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	/**
	 * �������ݿ�Ự
	 */
	public void open() {
		if(con == null) {
			try {
				con = DbResourceManager.getConnection();
			}
			catch (Exception e) {
				throw new DbSessionException("�����Ựʧ��", e);
			}
		}
	}
	
	/**
	 * ��������ݿ�Ự�󶨵�����
	 */
	public Connection getConnection() {
		return con;
	}
	
	/**
	 * �ر����ݿ�Ự
	 */
	public void close() {
		try {
			DbResourceManager.close(rs);
			rs = null;
			DbResourceManager.close(stmt);
			stmt = null;
			DbResourceManager.close(con);
			con = null;
		}
		catch (SQLException e) {
			throw new DbSessionException("�رջỰʧ��", e);
		}
	}
	
	/**
	 * ��������
	 * @throws �޷���������ʱ���׳��쳣
	 */
	public void beginTx() {
		try {
			if(con != null && !con.isClosed()) {
				con.setAutoCommit(false);
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("��������ʧ��", e);
		}
	}
	
	/**
	 * �ύ����
	 * @throws �޷��ύ����ʱ���׳��쳣
	 */
	public void commitTx() {
		try {
			if(con != null && !con.isClosed()) {
				con.commit();
			}
		}
		catch (SQLException e) {
			throw new DbSessionException("�ύ����ʧ��", e);
		}
	}
	
	/**
	 * �ع�����
	 * @throws �޷��ع�����ʱ���׳��쳣
	 */
	public void rollbackTx() {
		try {
			if(con != null && !con.isClosed()) {
				con.rollback();
			}
		}
		catch (SQLException e) {
			throw new DbSessionException("�ع�����ʧ��", e);
		}
	}
	
	/**
	 * ִ�и������
	 * @param sql SQL���
	 * @param params �滻SQL�����ռλ���Ĳ���
	 * @return ��������Ӱ��
	 */
	public DbResult executeUpdate(String sql, Object... params) {
		try {
			boolean isInsert = sql.trim().startsWith("insert");
			if(isInsert) {
				stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			else {
				stmt = con.prepareStatement(sql);
			}
			for(int i = 0; i < params.length; i++) {
				stmt.setObject(i + 1, params[i]);
			}
			int affectedRows = stmt.executeUpdate();
			long generatedKey = 0L;
			if(isInsert) {
				rs = stmt.getGeneratedKeys();
				generatedKey = rs.next()? rs.getLong(1) : generatedKey;
			}
			return new DbResult(affectedRows, generatedKey);
		}
		catch (SQLException e) {
			throw new DbSessionException(e);
		}
	}
	
	/**
	 * ִ�в�ѯ���
	 * @param sql SQL���
	 * @param params �滻SQL�����ռλ���Ĳ���
	 * @return �����(�α�)
	 */
	public ResultSet executeQuery(String sql, Object... params) {
		try {
			stmt = con.prepareStatement(sql);
			for(int i = 0; i < params.length; i++) {
				stmt.setObject(i + 1, params[i]);
			}
			rs = stmt.executeQuery();
		}
		catch (SQLException e) {
			throw new DbSessionException(e);
		}
		
		return rs;
	}
	
}
