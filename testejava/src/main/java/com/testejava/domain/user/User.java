package com.testejava.domain.user;

import br.com.caelum.stella.validation.CPFValidator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.usertype.UserType;

import java.math.BigDecimal;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique=true)
    private String document;
    @Column(unique=true)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column
    private Company company;

    public User(Long id,
                String firstName,
                String lastName,
                String document,
                String email,
                UserType userType) throws Exception {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = isDocumentValid(document) ? document : "";
        this.email = email;
        this.userType = userType;
    }
    public boolean isDocumentValid(String document){
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(document);
            return true;
        } catch(Exception e) {
            System.out.println("O CPF " + document + "é inválido " + "exception: " + e);
            return false;
        }
    }
}
