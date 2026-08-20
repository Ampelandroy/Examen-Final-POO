package school.hei.examen_final_poo_l1.dto;
import school.hei.examen_final_poo_l1.model.ExpenseFrequency;
import java.math.BigDecimal;
public class ExpenseCreateDto {
    private String userId;
    private BigDecimal amount;
    private String reason;
    private ExpenseFrequency frequency;
    public ExpenseCreateDto() {
    }
    public ExpenseCreateDto(String userId, BigDecimal amount, String reason, ExpenseFrequency frequency) {
        this.userId = userId;
        this.amount = amount;
        this.reason = reason;
        this.frequency = frequency;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public ExpenseFrequency getFrequency() {
        return frequency;
    }
    public void setFrequency(ExpenseFrequency frequency) {
        this.frequency = frequency;
    }
}