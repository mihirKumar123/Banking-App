package com.mkp.BankingManagment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkp.BankingManagment.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
