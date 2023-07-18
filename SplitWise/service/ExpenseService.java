package service;

import java.util.List;

import model.ExpenseType;
import model.User;
import model.expense.EqualExpense;
import model.expense.ExactExpense;
import model.expense.Expense;
import model.expense.ExpenseData;
import model.expense.PercentageExpense;
import model.split.PercentSplit;
import model.split.Split;

public class ExpenseService {

    public static Expense createExpense(ExpenseType expenseType, double amount,
                                        User expensePaidBy, List<Split> splits, ExpenseData expenseData) {
        switch (expenseType) {
            case EXACT:
                return new ExactExpense(amount, expensePaidBy, splits, expenseData);
            case PERCENT:
                for (Split split : splits) {
                    PercentSplit percentSplit = (PercentSplit) split;
                    split.setAmount((amount*percentSplit.getPercent())/100.0);
                }
                return new PercentageExpense(amount, expensePaidBy, splits, expenseData);
            case EQUAL:
                int totalSplits = splits.size();
                double splitAmount = ((double) Math.round(amount*100/totalSplits))/100.0;
                for (Split split : splits) {
                    split.setAmount(splitAmount);
                }
                return new EqualExpense(amount, expensePaidBy, splits, expenseData);
            default:
                return null;
        }
    }
}