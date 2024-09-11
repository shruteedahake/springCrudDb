package com.hexaware.webex;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.hexaware.webex.ActRepo.ActRepository;
import com.hexaware.webex.Entities.Account;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(Application.class, args);
		ActRepository repo = context.getBean(ActRepository.class);
		
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			
		System.out.println(" ");
		System.out.println("1. Create a new account");
		System.out.println("2. Deposit amount");
		System.out.println("3. Withdraw amount");
		System.out.println("4. Search account by account number");
		System.out.println("5. Transfer amount");
		System.out.println("6. Close the account");
		System.out.println("7. Exit");
		System.out.println("Enter your choice");
		int choice=sc.nextInt();
		
		switch(choice) {
		case 1:
			Account act1= new Account();
			System.out.println("Enter the account number");
			int actNo=sc.nextInt();
			
			Optional<Account> act=repo.findById(actNo);
			if(act.isPresent()) {
				System.out.println("Account already exists.");
			}
			else{
				sc.nextLine();
				System.out.println("Enter the name");
				String name=sc.nextLine();
				System.out.println("Enter the balance");
				Double bal=sc.nextDouble();
				sc.nextLine();
				System.out.println("Enter the email");
				String email=sc.nextLine();
				
				if(bal>1000) {
					act1.setActNo(actNo);
					act1.setName(name);
					act1.setBalance(bal);
					act1.setEmail(email);
					repo.save(act1);
					System.out.println("Account created successfully!");
				}
				else {
					System.out.println("Insufficient Balance! Balance should be more than 1000.");
				}
			}
			
			
			break;
			
		case 2:
			System.out.println("Enter the account number");
			int actNo2=sc.nextInt();
			
			Optional<Account> act2=repo.findById(actNo2);
			if(act2.isPresent()) {
				System.out.println("Enter the amount to deposit");
				Double deposit=sc.nextDouble();
				Account temp=act2.get();
				temp.setBalance(temp.getBalance()+deposit);
				repo.save(temp);
				System.out.println("Amount deposited successfully!");
			}else {
				System.out.println("Account not found");
			}
			
			break;
			
		case 3:
			System.out.println("Enter the account number");
			int actNo3=sc.nextInt();
			
			Optional<Account> act3=repo.findById(actNo3);
			if(act3.isPresent()) {
				System.out.println("Enter the amount to withdraw");
				Double withdraw=sc.nextDouble();
				Account temp=act3.get();
				if(withdraw<temp.getBalance()) {
					temp.setBalance(temp.getBalance()-withdraw);
					repo.save(temp);
					System.out.println("Amount withdrawn successfully!");
				}else {
					System.out.println("Insufficient Balance");
				}
			}else {
				System.out.println("Account not found");
			}
			
			break;
			
			
		case 4:
			System.out.println("Enter the account number");
			int actNo4=sc.nextInt();
			Optional<Account> act4 = repo.findById(actNo4);
			if(act4.isPresent()) {
				System.out.println(act4);
			}else {
				System.out.println("Account not found.");
			}
			
			break;
			
		case 5:
			System.out.println("Enter the account number to transfer amount from");
			int account1=sc.nextInt();
			Optional<Account> act51=repo.findById(account1);
			if(act51.isPresent()) {
				Account temp1=act51.get();
				System.out.println("Enter the account number to transfer amount to");
				int account2=sc.nextInt();
				Optional<Account> act52=repo.findById(account2);
				
				if(act52.isPresent()) {
					Account temp2=act52.get();
					System.out.println("Enter the amount");
					Double amount=sc.nextDouble();
					if(temp1.getBalance()>amount) {
						temp1.setBalance(temp1.getBalance()-amount);
						temp2.setBalance(temp2.getBalance()+amount);
						repo.save(temp1);
						repo.save(temp2);
						System.out.println("Transfer successful!");
					}else {
						System.out.println("Insufficient balance!");
					}
				}else {
					System.out.println("Second account not found");
				}
			}else {
				System.out.println("Account not found.");
			}
			
			break;
			
			
		case 6:
			System.out.println("Enter the account number to delete");
			int actNo6=sc.nextInt();
			Optional<Account> act6=repo.findById(actNo6);
			if(act6.isPresent()) {
				repo.deleteById(actNo6);
				System.out.println("Account closed successfully!");
			}else {
				System.out.println("Account not found.");
			}
			
			break;
			
		case 7:
			break;
			
		default:
			System.out.println("Invalid choice!");
		}
		}
	}

}
