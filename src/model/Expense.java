/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class Expense {
    private int id;
    private String type;
    private int money;
    private String date;
    private String note;

    public Expense(String type, int money, String date, String note) {
        this.type = type;
        this.money = money;
        this.date = date;
        this.note = note;
    }

    public Expense() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Expense{" + "id=" + id + ", type=" + type + ", money=" + money + ", date=" + date + ", note=" + note + '}';
    }

    
    
    
}
