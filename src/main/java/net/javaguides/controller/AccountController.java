package net.javaguides.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.dto.AccountDto;
import net.javaguides.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	//Create New Account API
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		
		
		return ResponseEntity.ok(accountService.createAccount(accountDto));
	}
	
	//Find Account by ID API
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountByID(@PathVariable Long id){
		
		AccountDto accountDto= accountService.findAccount(id);
		return ResponseEntity.ok(accountDto);
	}

	//Deposit amount API
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, 
										@RequestBody Map<String, Double> request){
		
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.deposit(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	//Withdraw amount API
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, 
								@RequestBody Map<String, Double> request){
		
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	//Get all accounts API
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccount(){

		List<AccountDto> accountDtos=accountService.showAllAccounts();
		return ResponseEntity.ok(accountDtos);
	}
	
	//Delete account API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Successfully!");
	}
}
