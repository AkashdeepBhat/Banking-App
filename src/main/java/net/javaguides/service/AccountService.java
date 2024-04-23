package net.javaguides.service;

import java.util.List;

import net.javaguides.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto findAccount(Long id);

	AccountDto deposit(Long id, double amount);
	
	AccountDto withdraw(Long id, double amount);
	
	List<AccountDto> showAllAccounts();
	
	void deleteAccount(Long id);
}
