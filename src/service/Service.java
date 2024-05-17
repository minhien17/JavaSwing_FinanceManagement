/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.DAO;
import java.util.List;
import model.Expense;
import model.Income;

/**
 *
 * @author DELL
 */
public class Service {
    private DAO itemDao;

    public Service() {
        itemDao = new DAO();
    }
    
    public List getAllExpense(int month, String type){
        return itemDao.getAllExpense(month,type);
    }
    
    public List getAllIncome(int month){
        return itemDao.getAllIncome(month);
    }
    
    public Expense getExpenseById(int id){
        return itemDao.getExpenseById(id);
    }
    
    public Income getIncomeById(int id){
        return itemDao.getIncomeById(id);
    }
    
    public int getTotalExpense(int month){
        return itemDao.getTotalExpense(month);
    }
    
    public int getTotalIncome(int month){
        return itemDao.getTotalIncome(month);
    }
    
    public int getTotalByType(String type, int month){
        return itemDao.getTotalByType(type, month);
    }
    
    public void addExpense(Expense expense){
        itemDao.addExpense(expense);
    }
    
    public void addIncome(Income income){
        itemDao.addIncome(income);
    }
    
    public void deleteExpensebyId(int id){
        itemDao.deleteExpense(id);
    }
    
    public void updateExpense(Expense expense){
        itemDao.updateExpense(expense);
    }
    public void updateIncome(Income income){
        itemDao.updateIncome(income);
    }
}
