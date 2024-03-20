package pl.zajavka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class BankService {

    private final List<BankAccount> accounts;
    private final List<BankTransaction> transactions;
    private long lastAccountId;
    private long lastTransactionId;

    public BankAccount openAccount(String owner, BigDecimal balance) {
        log.info("Opening new account for {} with balance {}...", owner, balance);
        long id = ++lastAccountId;
        var account = BankAccount.of(id, owner, balance);
        accounts.add(account);
        log.info("Created new account with id {} for {}", id, owner);
        return account;
    }

    public Optional<BankAccount> findAccountById(long id) {
        log.info("Searching for account with id: {}...", id);
        return accounts.stream()
                .filter(account -> account.getId() == id)
                .findFirst();
    }

    public void deposit(long accountId, BigDecimal ammount) throws BankException {
        log.info("Making a deposit {} on an account with id {}", ammount, accountId);
        var optionalAccount = findAccountById(accountId);
        if (optionalAccount.isEmpty()) {
            log.error("Account with id {} does not exist.", accountId);
            throw new BankException("Account with id %s does not exist.".formatted(accountId));
        }

        var account = optionalAccount.get();
        account.setBalance(account.getBalance().add(ammount));
        log.info("Deposit {} make on account with id {}.", ammount, accountId);
        long id = ++lastTransactionId;
        var transaction = BankTransaction.of(id, account, ammount, LocalDateTime.now());
        transactions.add(transaction);
        log.debug("Registered transaction {} for an accoutn with id {}...", id, accountId);
    }




}
