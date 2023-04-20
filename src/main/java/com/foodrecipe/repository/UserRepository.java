package com.foodrecipe.repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.foodrecipe.model.Role;
import com.foodrecipe.model.User;

public class UserRepository extends RepositoryBase implements IRepository<User> {

	public UserRepository() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		try {
			open();
			String query = "SELECT * FROM user_tbl";
			prepareCreateStatement(query);
			prepareResultSet();
			
			while(resultSet.next()) {
				users.add(new User(resultSet.getLong("userId"),resultSet.getString("username"),"",resultSet.getString("firstname"),resultSet.getString("lastname"),resultSet.getString("midname"),getValue(resultSet.getString("role"))));
			}
			close();
		}catch(SQLException e) {
			Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
		}
		return users;
	}

	@Override
	public User findById(Long id) {
		try {
			open();
			String query = "SELECT * FROM user_tbl userId = ?";
			prepareCreateStatement(query);
			List<Object> params = new ArrayList<>();
			params.add(id);
			setParams(params);
			prepareResultSet();
			nextRow();
			User user = new User(resultSet.getLong("userId"),resultSet.getString("username"),"",resultSet.getString("firstname"),resultSet.getString("lastname"),resultSet.getString("midname"),getValue(resultSet.getString("role")));
			close();
			return user;
		}catch(SQLException e) {
			Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	
	}
	
	public User findByUsername(String username) {
		try {
			open();
			String query = "SELECT * FROM user_tbl WHERE username = ?";
			prepareCreateStatement(query);
			List<Object> params = new ArrayList<>();
			params.add(username);
			setParams(params);
			prepareResultSet();
			nextRow();
			User user = new User(resultSet.getLong("userId"),resultSet.getString("username"),resultSet.getString("password"),resultSet.getString("firstname"),resultSet.getString("lastname"),resultSet.getString("midname"),getValue(resultSet.getString("role")));
			close();
			return user;
		}catch(SQLException e) {
			Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	
	}

	@Override
	public int create(User model) {
		int flag = -1;
		try {
			open();
			String query = "INSERT INTO user_tbl(username,password,firstname,lastname,midname,role) values(?,?,?,?,?,?)";
			prepareCreateStatement(query);
			
			List<Object> params = new ArrayList<>();
			params.add(model.getUsername());
			params.add(model.getPassword());
			params.add(model.getFirstname());
			params.add(model.getLastname());
			params.add(model.getMidname());
			params.add(model.getRole().toString());
			
			setParams(params);
			flag = executeStatement();
			close();
			return flag;
		}catch(SQLException e) {
			Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
			return flag;
		}
	}

	@Override
	public int update(User model) {
		try {
			open();
			String call = "{call updateUser (?,?.?.?)}";
			createCallableStatement(call);
			
			List<Object> params = new ArrayList<>();
			params.add(model.getUserId());
			params.add(model.getFirstname());
			params.add(model.getLastname());
			params.add(model.getMidname());
			
			setCallableParams(params);
			callProcedure();
			if(resultSet != null) {
				close();
				return 1;
			}
			close();
		}catch(SQLException e) {
			Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
		}
		return -1;
	}

	@Override
	public void remove(Long id) {
		try {
			open();
			String query = "DELETE FROM user_tbl WHERE userId = ?";
			prepareCreateStatement(query);
			List<Object> params = new ArrayList<>();
			params.add(id);
			setParams(params);
			executeStatement();
		}catch(SQLException e) {
			Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
		}
		
	}

	@Override
	public DefaultTableModel populateTable() {
		String col[] = {"ID","Username","Firstname","Lastname","Middlename","Role"};
		DefaultTableModel tableModel = new DefaultTableModel(col,0);
		List<User> users = getAll();
		for(User user:users) {
			Object[] data = {user.getUserId(),user.getUsername(),user.getFirstname(),user.getLastname(),user.getMidname(),user.getRole().toString()};
			tableModel.addRow(data);
		}
		return tableModel;
	}
	
	private Role getValue(String value) {
		if(value.equals("ADMIN")) {
			return Role.ADMIN;
		}
		return Role.USER;
	}

}
