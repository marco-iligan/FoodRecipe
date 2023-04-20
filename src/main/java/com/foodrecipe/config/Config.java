package com.foodrecipe.config;

public class Config {
	public static String MYSQL_HOST = "localhost";
	public static String MYSQL_PORT = "3306";
	public static String MYSQL_DATABASE = "FoodRecipe";
	public static String URL = "jdbc:mysql://"+MYSQL_HOST+":"+MYSQL_PORT+"/"+MYSQL_DATABASE;
	public static String MYSQL_USER = "root";
	public static String MYSQL_PASSWORD = "admin";
	public static String DRIVER = "com.mysql.cj.jdbc.Driver";
}
