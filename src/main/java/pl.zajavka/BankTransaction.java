package pl.zajavka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor(staticName = "of")
public class BankTransaction {
    private long id;
    private BankAccount account;
    private BigDecimal amount;
    private LocalDateTime date;
}
