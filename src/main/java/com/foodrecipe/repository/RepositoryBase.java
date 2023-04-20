package com.foodrecipe.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.foodrecipe.config.Config;

public abstract class RepositoryBase {
	private Connection connection;
	private PreparedStatement prepStatement;
	private CallableStatement callStatement;
	private Statement statement;
	protected ResultSet resultSet;

	@SuppressWarnings("deprecation")
	public RepositoryBase() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class.forName(Config.DRIVER).newInstance();
	}
	
	public void open() throws SQLException {
		connection = (Connection) DriverManager.getConnection(Config.URL,Config.MYSQL_USER,Config.MYSQL_PASSWORD);
	}
	
	public void close() throws SQLException {
		connection.close();
	}
	
	public void createStatement(String query) throws SQLException {
		statement = connection.createStatement();
	}
	
	public void closeStatement() throws SQLException {
		statement.close();
	}
	
	public void prepareCreateStatement(String query) throws SQLException {
		prepStatement = (PreparedStatement) connection.prepareStatement(query);
	}
	
	public void createCallableStatement(String procedure) throws SQLException {
		callStatement = (CallableStatement) connection.prepareCall(procedure);
	}
	
	public void closeCallableStatement() throws SQLException {
		callStatement.close();
	}
	
	public void setParams(List<Object> params) throws SQLException {
		for(int index=0; index<params.size(); index++) {
			Object param = params.get(index);
			int paramIndex = index + 1;
			if(param.getClass() == String.class) {
				prepStatement.setString(paramIndex, param.toString());
			}else if(param.getClass() == Integer.class) {
				prepStatement.setInt(paramIndex, (int)param);
			}else if(param.getClass() == Float.class) {
				prepStatement.setFloat(paramIndex, (float)param);
			}else if(param.getClass() == Double.class) {
				prepStatement.setDouble(paramIndex, (double)param);
			}else if(param.getClass() == Boolean.class) {
				prepStatement.setBoolean(paramIndex, (boolean)param);
			}else if(param.getClass() == Date.class) {
				prepStatement.setDate(paramIndex, (Date)param);
			}
		}
	}
	
	public void setCallableParams(List<Object> params) throws SQLException {
		for(int index=0; index<params.size(); index++) {
			Object param = params.get(index);
			int paramIndex = index + 1;
			if(param.getClass() == String.class) {
				callStatement.setString(paramIndex, param.toString());
			}else if(param.getClass() == Integer.class) {
				callStatement.setInt(paramIndex, (int)param);
			}else if(param.getClass() == Float.class) {
				callStatement.setFloat(paramIndex, (float)param);
			}else if(param.getClass() == Double.class) {
				callStatement.setDouble(paramIndex, (double)param);
			}else if(param.getClass() == Boolean.class) {
				callStatement.setBoolean(paramIndex, (boolean)param);
			}else if(param.getClass() == Date.class) {
				callStatement.setDate(paramIndex, (Date)param);
			}
		}
	}
	
	protected int executeStatement() throws SQLException {
		return prepStatement.executeUpdate();
	}
	
	protected void prepareResultSet() throws SQLException {
		resultSet = (ResultSet) prepStatement.executeQuery();
	}
	
	protected void callProcedure() throws SQLException {
		boolean hasResult = callStatement.execute();
		if(hasResult) {
			resultSet = (ResultSet) callStatement.getResultSet();
		}
	}
	
	protected void nextRow() throws SQLException {
		resultSet.next();
	}
}
