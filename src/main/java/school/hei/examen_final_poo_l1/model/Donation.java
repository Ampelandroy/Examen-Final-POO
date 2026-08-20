package school.hei.examen_final_poo_l1.model;
import java.math.BigDecimal;
import java.time.Instant;
public class Donation extends CashFlow {
    private String comment;
    public Donation() {
    }
    public Donation(String id, Instant createdAt, BigDecimal amount, User user, String comment) {
        super(id, createdAt, amount, user);
        this.comment = comment;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}