package service;

import java.util.List;
import java.util.Optional;

import controller.ExpenseController;
import model.User;

public class UserService {
	ExpenseController expenseController;

    public UserService(ExpenseController expenseRepository){
        this.expenseController = expenseRepository;
    }

    public void addUser(User user){
    	expenseController.addUser(user);
    }
    public User getUser(String userName){
        return expenseController.getUser(userName);
    }
}