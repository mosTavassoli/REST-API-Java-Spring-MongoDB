package com.illimity.mostafa.services;

import com.illimity.mostafa.movements.AccountMovements;

import java.net.URI;
import java.util.List;


public interface AccountMovementsService {
     List<AccountMovements> getTenLastMovementsOrderByDateService(String customerId) throws Exception;
     URI createAccountMovementsService(AccountMovements accountMovements) throws Exception;

}
