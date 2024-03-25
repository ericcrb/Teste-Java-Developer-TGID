package com.testejava.domain.transaction;

import com.testejava.domain.user.Company;
import com.testejava.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name="sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name="receiver_id")
    private Company receiver;

    private LocalDateTime timeStamp;

    private void withdraw(Company receiver, BigDecimal amount) {
        BigDecimal amountWithTax = amount.add(amount.multiply(receiver.getTax()));
        if (amount.compareTo(receiver.getBalance()) == 1) {
            System.out.println("Saldo para saque é insuficiente ");
        } else {
            receiver.setBalance(receiver.getBalance().subtract(amountWithTax));
        }
    }
}
