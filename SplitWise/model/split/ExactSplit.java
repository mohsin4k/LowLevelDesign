package model.split;

import model.User;

public class ExactSplit extends Split {
	double amount; 
	
    public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public ExactSplit(User user, double amount) {
        super(user);
        this.amount = amount;
    }
}