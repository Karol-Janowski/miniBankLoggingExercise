package pl.zajavka;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
@Slf4j
public class BankLoggingRunner {

    public static void main(String[] args) {

        BankService bankService = new BankService(new ArrayList<>(), new ArrayList<>(), 0L, 0L);
        BankAccount account1 = bankService.openAccount("Karol Janowski", BigDecimal.valueOf(1000));
        BankAccount account2 = bankService.openAccount("Marcel Kawalec", BigDecimal.valueOf(500));

        try {
            bankService.transfer(account1.getId(), account2.getId(), BigDecimal.valueOf(200));
        } catch (BankException e) {
            log.error("Error: {}", e.getMessage());
        }

        try {
            bankService.transfer(account1.getId(), account2.getId(), BigDecimal.valueOf(1000));
        } catch (BankException e) {
            log.error("Error: {}", e.getMessage());
        }

        try {
            bankService.withdraw(account2.getId(), BigDecimal.valueOf(300));
        } catch (BankException e) {
            log.error("Error: {}", e.getMessage());
        }

        try {
            bankService.withdraw(account1.getId(), BigDecimal.valueOf(300));
        } catch (BankException e) {
            log.error("Error: {}", e.getMessage());
        }



        try {
            bankService.getTransactionsHistory(account1.getId())
                    .forEach(transaction -> log.info("Bank transactions for account {}, {}", account1.getId(), transaction));
            bankService.getTransactionsHistory(account2.getId())
                    .forEach(transaction -> log.info("Bank transactions for account {}, {}", account2.getId(), transaction));

        } catch (BankException e) {
            log.error("Error: {}", e.getMessage());
        }

        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());


    }

}
