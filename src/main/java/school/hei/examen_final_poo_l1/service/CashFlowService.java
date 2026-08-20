package school.hei.examen_final_poo_l1.service;

import org.springframework.stereotype.Service;
import school.hei.examen_final_poo_l1.dto.BalanceDto;
import school.hei.examen_final_poo_l1.dto.CashFlowResponseDto;
import school.hei.examen_final_poo_l1.dto.ExpenseCreateDto;
import school.hei.examen_final_poo_l1.model.CashFlow;
import school.hei.examen_final_poo_l1.model.Donation;
import school.hei.examen_final_poo_l1.model.Expense;
import school.hei.examen_final_poo_l1.model.User;
import school.hei.examen_final_poo_l1.repository.CashFlowRepository;
import school.hei.examen_final_poo_l1.repository.UserRepository;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CashFlowService {
    private final CashFlowRepository cashFlowRepository;
    private final UserRepository userRepository;
    public CashFlowService() {
        this.cashFlowRepository = new CashFlowRepository();
        this.userRepository = new UserRepository();
    }
    public List<CashFlowResponseDto> getCashFlows(String type) throws SQLException {
        List<CashFlow> cashFlows = cashFlowRepository.findByType(type);
        return cashFlows.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }
    public List<CashFlowResponseDto> getUserCashFlows(String userId) throws SQLException {
        List<CashFlow> cashFlows = cashFlowRepository.findByUserId(userId);
        return cashFlows.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }
    public CashFlowResponseDto createExpense(ExpenseCreateDto dto) throws SQLException {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + dto.getUserId()));
        Expense expense = new Expense(
                UUID.randomUUID().toString(),
                Instant.now(),
                dto.getAmount(),
                user,
                dto.getReason(),
                dto.getFrequency()
        );
        Expense savedExpense = cashFlowRepository.saveExpense(expense);
        return mapToResponseDto(savedExpense);
    }
    public BalanceDto getBalance() throws SQLException {
        BigDecimal balanceAmount = cashFlowRepository.calculateBalance();
        return new BalanceDto(balanceAmount, Instant.now());
    }
    private CashFlowResponseDto mapToResponseDto(CashFlow cashFlow) {
        String type = (cashFlow instanceof Expense) ? "EXPENSE" : "DONATION";
        String comment = (cashFlow instanceof Donation) ? ((Donation) cashFlow).getComment() : null;
        String reason = (cashFlow instanceof Expense) ? ((Expense) cashFlow).getReason() : null;
        var frequency = (cashFlow instanceof Expense) ? ((Expense) cashFlow).getFrequency() : null;
        return new CashFlowResponseDto(
                cashFlow.getId(),
                cashFlow.getCreatedAt(),
                cashFlow.getAmount(),
                type,
                cashFlow.getUser().getId(),
                comment,
                reason,
                frequency
        );
    }
}