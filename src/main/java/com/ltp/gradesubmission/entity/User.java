package com.ltp.gradesubmission.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "users")
/*
 * 'user' is a reserved keyword in SQL, so we name our table users. If you name it user, you will get a org.h2.jdbc.JdbcSQLSyntaxErrorException.
 *  See https://docs.microsoft.com/en-us/sql/t-sql/language-elements/reserved-keywords-transact-sql?view=sql-server-ver16 for a list of reserved keywords.
 */
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "username cannot be blank")
    @NonNull
    @Column(nullable = false,unique = true)
    private String username;
    @NotBlank(message = "password cannot be blank")
    @NonNull
    @Column(nullable = false)
    private String password;
}
