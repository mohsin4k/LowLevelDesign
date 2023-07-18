package model.expense;

import java.util.List;

import model.User;
import model.split.PercentSplit;
import model.split.Split;

public class PercentageExpense extends Expense {

    public PercentageExpense(double amount, User expensePaidBy, List<Split> splits, ExpenseData expenseData) {
        super(amount, expensePaidBy, splits, expenseData);
    }

    @Override
    public boolean validate() {
        double totalSplitPercent = 0;
        for(Split split: getSplits()){
            if(!(split instanceof PercentSplit)) return false;
            PercentSplit percentSplit = (PercentSplit) split;
            totalSplitPercent+=percentSplit.getPercent();
        }
        return 100 == totalSplitPercent;
    }
}