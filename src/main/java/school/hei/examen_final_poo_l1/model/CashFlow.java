package school.hei.examen_final_poo_l1.model;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
public class CashFlow {
    private String id;
    private Instant createdAt;
    private BigDecimal amount;
    private User user;
    public CashFlow() {
    }
    public CashFlow(String id, Instant createdAt, BigDecimal amount, User user) {
        this.id = id;
        this.createdAt = createdAt;
        this.amount = amount;
        this.user = user;
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashFlow cashFlow = (CashFlow) o;
        return Objects.equals(id, cashFlow.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}