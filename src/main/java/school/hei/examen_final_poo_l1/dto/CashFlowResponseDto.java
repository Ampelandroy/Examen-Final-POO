package school.hei.examen_final_poo_l1.dto;
import school.hei.examen_final_poo_l1.model.ExpenseFrequency;
import java.math.BigDecimal;
import java.time.Instant;
public class CashFlowResponseDto {
    private String id;
    private Instant createdAt;
    private BigDecimal amount;
    private String type;
    private String userId;
    private String comment;
    private String reason;
    private ExpenseFrequency frequency;
    public CashFlowResponseDto() {
    }
    public CashFlowResponseDto(String id, Instant createdAt, BigDecimal amount, String type, String userId, String comment, String reason, ExpenseFrequency frequency) {
        this.id = id;
        this.createdAt = createdAt;
        this.amount = amount;
        this.type = type;
        this.userId = userId;
        this.comment = comment;
        this.reason = reason;
        this.frequency = frequency;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
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