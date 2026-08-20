package school.hei.examen_final_poo_l1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import school.hei.examen_final_poo_l1.dto.BalanceDto;
import school.hei.examen_final_poo_l1.dto.CashFlowResponseDto;
import school.hei.examen_final_poo_l1.dto.ExpenseCreateDto;
import school.hei.examen_final_poo_l1.service.CashFlowService;
import java.sql.SQLException;
import java.util.List;
@RestController
public class CashFlowController {
    private final CashFlowService cashFlowService;
    public CashFlowController(CashFlowService cashFlowService) {
        this.cashFlowService = cashFlowService;
    }
    @GetMapping("/cash-flows")
    public ResponseEntity<List<CashFlowResponseDto>> getCashFlows(
            @RequestParam(name = "type", required = false) String type) throws SQLException {
        List<CashFlowResponseDto> cashFlows = cashFlowService.getCashFlows(type);
        return ResponseEntity.ok(cashFlows);
    }
    @GetMapping("/users/{id}/cash-flows")
    public ResponseEntity<List<CashFlowResponseDto>> getUserCashFlows(
            @PathVariable("id") String id) throws SQLException {
        List<CashFlowResponseDto> cashFlows = cashFlowService.getUserCashFlows(id);
        return ResponseEntity.ok(cashFlows);
    }
    @PostMapping("/expenses")
    public ResponseEntity<CashFlowResponseDto> createExpense(
            @RequestBody ExpenseCreateDto expenseCreateDto) throws SQLException {
        CashFlowResponseDto createdExpense = cashFlowService.createExpense(expenseCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }
    @GetMapping("/balance")
    public ResponseEntity<BalanceDto> getBalance() throws SQLException {
        BalanceDto balance = cashFlowService.getBalance();
        return ResponseEntity.ok(balance);
    }
}