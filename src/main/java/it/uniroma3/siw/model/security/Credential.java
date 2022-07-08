package it.uniroma3.siw.model.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "credentials")
@Data
@NoArgsConstructor
@SequenceGenerator(name = "CRED_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "CRED_SEQ")
public class Credential {

    public static final String DEFAULT_ROLE = "DEFAULT";
    public static final String ADMIN_ROLE = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CRED_SEQUENCE_GENERATOR")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

}
