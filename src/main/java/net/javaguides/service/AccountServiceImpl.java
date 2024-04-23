package net.javaguides.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.dto.AccountDto;
import net.javaguides.entity.Account;
import net.javaguides.mapper.AccountMapper;
import net.javaguides.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto findAccount(Long id) {

		Account acccount=accountRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("No account by this ID"));
		AccountDto foundAccount=AccountMapper.mapToAccountDto(acccount);
		return foundAccount;
	}

	@Override
	public AccountDto deposit(Long id, double amount) {

		Account account=accountRepository.findById(id).
				orElseThrow(()-> new RuntimeException("No account by this ID"));
		double total=account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account=accountRepository.findById(id).
				orElseThrow(()-> new RuntimeException("No account by this ID"));
		double total=account.getBalance()-amount;
		account.setBalance(total);
		Account updatedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(updatedAccount);
	}

	@Override
	public List<AccountDto> showAllAccounts() {
 
		List<Account> accounts=accountRepository.findAll();
		
		return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account)).
				collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
 
		accountRepository.findById(id).
				orElseThrow(()-> new RuntimeException("No account by this ID"));
		accountRepository.deleteById(id);
		
	}

	

}
