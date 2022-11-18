package config;

public class Sql {
	// book
	public static final String INSERT_BOOK = "INSERT INTO `book` VALUES (?,?,?,?)";
	
	public static final String SELECT_BOOKS = "SELECT * FROM `book`";
	public static final String SELECT_BOOK = "SELECT * FROM `book` WHERE `bookId`=?";
	
	public static final String UPDATE_BOOK = "UPDATE `book` SET `bookName`=?, `publisher`=?, `price`=? WHERE `bookId`=?";
	
	public static final String DELETE_BOOK = "DELETE FROM `book` WHERE `bookId`=?";
	// customer
	public static final String INSERT_CUSTOMER = "INSERT INTO `customer`(`name`,`address`,`phone`) values (?,?,?)";
	
	public static final String SELECT_CUSTOMERS = "SELECT * FROM `customer`";
	public static final String SELECT_CUSTOMER = "SELECT * FROM `customer` WHERE `custId`=?";
	
	public static final String UPDATE_CUSTOMER = "UPDATE `customer` SET `name`=?, `address`=?, `phone`=? WHERE `custId`=?";
	
	public static final String DELETE_CUSTOMER = "DELETE FROM `customer` WHERE `custId`=?";
}
