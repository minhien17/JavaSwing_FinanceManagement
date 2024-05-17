/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.ConnectSQL;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import model.Expense;
import model.Income;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class DAO {
    public List <Expense> getAllExpense(int month, String type){
        List<Expense> expenses = new ArrayList<>();
        
        Connection connection = ConnectSQL.DBConnection();
        String sql = "";
        if(type.equals("All")){
            sql = "select * from expense where month(date) = ? order by date desc";
        }else{
            sql = "select * from expense where month(date) = ? and type = ? order by date desc";
        }
        System.out.println(type);
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, month);
            if(! type.equals("All"))preparedStatement.setString(2, type);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setType(rs.getString("type"));
                expense.setMoney(rs.getInt("money"));
                expense.setDate(rs.getString("date"));
                expense.setNote(rs.getString("note"));
                expenses.add(expense);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println(expenses);
        return expenses;
    }
    
    public List <Income> getAllIncome(int month){
        List<Income> incomes = new ArrayList<>();
        
        Connection connection = ConnectSQL.DBConnection();
        String sql = "select * from income where month(date) = ? order by date desc";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, month);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                Income income = new Income();
                income.setId(rs.getInt("id"));
                income.setMoney(rs.getInt("money"));
                income.setDate(rs.getDate("date"));
                income.setNote(rs.getString("note"));
                incomes.add(income);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println(incomes);
        return incomes;
    }
    
    public Expense getExpenseById(int id){
        Expense expense = new Expense();
        
        Connection connection = ConnectSQL.DBConnection();
        
        String sql = "select * from expense where id = " + id;
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                expense.setType(resultSet.getString("type"));
                expense.setMoney(resultSet.getInt("money"));
                expense.setDate(resultSet.getString("date"));
                expense.setNote(resultSet.getString("note"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return expense;
    }
    
    public Income getIncomeById(int id){
        Income income = new Income();
        
        Connection connection = ConnectSQL.DBConnection();
        
        String sql = "select * from income where id = " + id;
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                income.setMoney(resultSet.getInt("money"));
                income.setDate(resultSet.getDate("date"));
                income.setNote(resultSet.getString("note"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return income;
    }
    
    public int getTotalExpense(int month){
        Connection connection = ConnectSQL.DBConnection();
        String sql = "select SUM(money) as total from Expense where month(date) = " + month + " ";
        int total = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) total = resultSet.getInt("total");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public int getTotalIncome(int month){
        Connection connection = ConnectSQL.DBConnection();
        String sql = "select SUM(money) as total from Income where month(date) = " + month + "";
        int total = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) total = resultSet.getInt("total");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public int getTotalByType(String type, int month){
        Connection connection = ConnectSQL.DBConnection();
        String sql = "select SUM(money) as total from Expense where type = '" + type +"' and month(date) = "+ month + "" ;
        int total = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) total = resultSet.getInt("total");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    // xu ly date String --> Date
    
    public Date convert(String dateStr){
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
    
    public void addExpense(Expense expense){
        Connection connection = ConnectSQL.DBConnection();
        String sql = "insert into expense(type,money,date,note) values (?,?,?,?)";
        
        Date date = convert(expense.getDate());
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, expense.getType());
            preparedStatement.setInt(2, expense.getMoney());
            preparedStatement.setDate(3, new java.sql.Date(date.getTime()));
            preparedStatement.setString(4, expense.getNote());
            
            preparedStatement.executeUpdate();
            System.out.println("insert success");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void addIncome(Income income){
        Connection connection = ConnectSQL.DBConnection();
        String sql = "insert into income(money,date,note) values (?,?,?)";
        
//        Date date = convert(income.getDate());
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, income.getMoney());
            preparedStatement.setDate(2, new java.sql.Date(income.getDate().getTime()));
            preparedStatement.setString(3, income.getNote());
            
            preparedStatement.executeUpdate();
            System.out.println("insert success");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteExpense(int id){
        Connection connection = ConnectSQL.DBConnection();
        String sql = "delete from Expense where id = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteIncome(int id){
        Connection connection = ConnectSQL.DBConnection();
        String sql = "delete from Income where id = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateExpense(Expense expense){
        Connection connection = ConnectSQL.DBConnection();
        String sql = "update Expense set money = ?, type = ?, note =?, date = ?  where id = ?";
        
        Date date = convert(expense.getDate());
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, expense.getMoney());
            preparedStatement.setString(2, expense.getType());
            preparedStatement.setString(3, expense.getNote());
            preparedStatement.setDate(4, new java.sql.Date(date.getTime()));
            preparedStatement.setInt(5, expense.getId());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateIncome(Income income){
        Connection connection = ConnectSQL.DBConnection();
        String sql = "update Income set money = ?, note =?, date = ?  where id = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, income.getMoney());
            preparedStatement.setString(2, income.getNote());
            preparedStatement.setDate(3, new java.sql.Date(income.getDate().getTime()));
            preparedStatement.setInt(4, income.getId());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
