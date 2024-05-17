/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package financemanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import model.Expense;
import service.Service;
import view.MainJFrame;

/**
 *
 * @author DELL
 */
public class FinanceManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        new MainJFrame().setVisible(true);
//        Service item = new Service();
//        System.out.println(item.getAllExpense());
//          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//          Date date = formatter.parse("26/04/2024");
//        
//          Expense expense = new Expense("Food&Drink", 20000, date, "");
//          Service service = new Service();
//          service.addExpense(expense);
//        System.out.println(date);
    }
    
}
