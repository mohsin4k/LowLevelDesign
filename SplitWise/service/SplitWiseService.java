package service;

import java.util.List;

import controller.ExpenseController;
import model.ExpenseType;
import model.expense.ExpenseData;
import model.split.Split;

public class SplitWiseService {
	ExpenseController expenseController;

    public SplitWiseService(ExpenseController expenseController){
        this.expenseController = expenseController;
    }

    public void addExpense(ExpenseType expenseType, double amount,
                           String expensePaidBy, List<Split > splits, ExpenseData expenseData) {
    	expenseController.addExpense(expenseType,amount,expensePaidBy,splits,expenseData);
    }

    public void showBalance(String userName) {
        List<String> balances = expenseController.getBalance(userName);
        if (balances.isEmpty()) {
            System.out.println("No balances");
        } else {
            for(String balance: balances){
                System.out.println(balance);
            }
        }
    }

    public void showBalances(){
        List<String> balances = expenseController.getBalances();
        if (balances.isEmpty()) {
            System.out.println("No balances");
        } else {
            for(String balance: balances){
                System.out.println(balance);
            }
        }
    }
}