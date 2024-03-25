package com.testejava.domain.user;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.usertype.UserType;

import java.math.BigDecimal;

@Entity(name="companies")
@Table(name="companies")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    @Column(unique=true)
    private String document;
    @Column(unique=true)
    private String email;
    private BigDecimal balance;

    @Column
    private BigDecimal tax;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public Company(Long id,
                   String companyName,
                   String document,
                   String email,
                   BigDecimal balance,
                   UserType userType,
                   BigDecimal tax) {
        this.id = id;
        this.companyName = companyName;
        this.document = isDocumentValid(document) ? document : "";
        this.email = email;
        this.balance = balance;
        this.userType = userType;
        this.tax = tax;
    }
    public boolean isDocumentValid(String document){
        try {
            CNPJValidator cnpjValidator = new CNPJValidator();
            cnpjValidator.assertValid(document);
            return true;
        } catch(Exception e) {
            System.out.println("O CNPJ" + document + "é inválido " + "exception: " + e);
            return false;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
