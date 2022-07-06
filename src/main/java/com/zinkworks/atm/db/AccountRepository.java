package com.zinkworks.atm.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zinkworks.atm.db.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

	Account findByAccountNumberAndPin(long accountNumber, int pin);
}
