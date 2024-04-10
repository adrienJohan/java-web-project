package com;

import java.time.LocalDate;


public class Expense {
	private int id; 
	private String description; 
	private double amount;
	private String category; 
	private LocalDate date;
	
	public Expense() {}
	
	public void setId(int id) { this.id = id;}
	public void setDescription(String description) { this.description = description; }
	public void setAmount(double amount) {this.amount = amount;}
	public void setCategory(String category) {this.category = category;}
	public void setDate(LocalDate date) {this.date = date;}
	
	public int getId() {return this.id;}
	public String getDescription() {return this.description;}
	public double getAmount() { return this.amount;}
	public String getCategory() {return this.category;}
	public LocalDate getDate() {return this.date;}
	
}

