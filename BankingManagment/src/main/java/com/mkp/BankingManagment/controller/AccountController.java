package com.mkp.BankingManagment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mkp.BankingManagment.entity.Account;
import com.mkp.BankingManagment.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	public AccountService accountService;
	
	//created Account
	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody Account accounts) {
		Account createAccount = accountService.createAccount(accounts);
		return new ResponseEntity<Account>(createAccount, HttpStatus.CREATED);
	}
	
	@GetMapping("/findById/{account_number}")
	public ResponseEntity<Account> getAccountDetailsByAccountNumber(@PathVariable Long account_number){
		Account accountDetails = accountService.getAccountDetailsByAccountNumber(account_number);
		return new ResponseEntity<Account>(accountDetails, HttpStatus.FOUND);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Account>> getAllAccountDetails(){
        List<Account> account_list = accountService.getAllAccountDetails();
		return new ResponseEntity<List<Account>>(account_list, HttpStatus.FOUND);
	}
	
	@PutMapping("/deposit")
	public ResponseEntity<Account> depositAccount(@RequestParam Long accountNumber,@RequestParam Double ammount){
		Account accountDetails = accountService.depositAmmount(accountNumber, ammount);
		return new ResponseEntity<Account>(accountDetails, HttpStatus.OK);
	}
	
	@PutMapping("/withdraw")
	public ResponseEntity<Account> withdrawAccount(@RequestParam Long accountNumber, Double ammount){
		Account accountDetails = accountService.withdrawAmmount(accountNumber, ammount);
		return new ResponseEntity<Account>(accountDetails, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{accountNumber}")
	public ResponseEntity<String> closeAccount(@PathVariable Long accountNumber) {
		accountService.closeAccount(accountNumber);
		
		return new ResponseEntity<String>("Account Closed", HttpStatus.OK);
	}
}
