package pl.zajavka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor(staticName = "of")
public class BankAccount {
    private long id;
    private String owner;
    private BigDecimal balance;
}
