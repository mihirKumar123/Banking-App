package com.mkp.BankingManagment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkp.BankingManagment.AccountRepository;
import com.mkp.BankingManagment.entity.Account;

@Service
public class AccountServiceImpl implements AccountService{

    

	@Autowired
	AccountRepository accountRepository;


  
	
	
	@Override
	public Account createAccount(Account account) {
		Account account_saved = accountRepository.save(account);
		return account_saved;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
		Optional<Account> account_fetched = accountRepository.findById(accountNumber);
		if(account_fetched.isEmpty()) {
			throw new RuntimeException("Account is NOT present");
		}
		return account_fetched.get();
	}

	@Override
	public List<Account> getAllAccountDetails() {
        List<Account> all_accounts = accountRepository.findAll();
		return all_accounts;
	}

	@Override
	public Account depositAmmount(Long accountNumber, Double ammount) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if(account.isEmpty()) {
        	throw new RuntimeException("Account number is Invalid");
        }
        Account deposit_account = account.get();
        Double totalBalance = deposit_account.getAccount_balance()+ammount;
        deposit_account.setAccount_balance(totalBalance);
        
        accountRepository.save(deposit_account);
        return deposit_account;
	}

	@Override
	public Account withdrawAmmount(Long accountNumber, Double ammount) {
		Optional<Account> account = accountRepository.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account Number is invalid");
		}
		Account witdrawl_account = account.get();
		if(witdrawl_account.getAccount_balance()<ammount) {
			throw new RuntimeException("Insufficient Account Balance");
		}
		
		Double totalBalance = witdrawl_account.getAccount_balance() - ammount;
		witdrawl_account.setAccount_balance(totalBalance);
		accountRepository.save(witdrawl_account);
		return witdrawl_account;
	}

	@Override
	public void closeAccount(Long accountNumber) {
		getAccountDetailsByAccountNumber(accountNumber);
		
		accountRepository.deleteById(accountNumber);
		
	}

}
