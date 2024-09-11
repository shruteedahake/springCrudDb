package com.hexaware.webex.ActRepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.webex.Entities.Account;

@Repository
public interface ActRepository extends CrudRepository<Account, Integer>{

}
