package com.mkp.BankingManagment.service;

import java.util.List;

import com.mkp.BankingManagment.entity.Account;

public interface AccountService {

	public Account createAccount(Account account);
	public Account getAccountDetailsByAccountNumber(Long accountNumber);
	public List<Account> getAllAccountDetails();
	public Account depositAmmount(Long accountNumber, Double ammount);
	public Account withdrawAmmount(Long accountNumber, Double ammount);
	public void closeAccount(Long accountNumber);
	
}
