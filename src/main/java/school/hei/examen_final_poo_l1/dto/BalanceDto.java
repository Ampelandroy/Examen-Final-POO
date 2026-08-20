package school.hei.examen_final_poo_l1.dto;

import java.math.BigDecimal;
import java.time.Instant;
public class BalanceDto {
    private BigDecimal amount;
    private Instant dateTime;
    public BalanceDto() {
    }
    public BalanceDto(BigDecimal amount, Instant dateTime) {
        this.amount = amount;
        this.dateTime = dateTime;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Instant getDateTime() {
        return dateTime;
    }
    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }
}