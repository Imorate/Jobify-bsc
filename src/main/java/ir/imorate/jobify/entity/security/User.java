package ir.imorate.jobify.entity.security;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 32, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 128)
    @ToString.Exclude
    private String password;

    @Column(name = "email", nullable = false, length = 32, unique = true)
    private String email;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired = Boolean.TRUE;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked = Boolean.TRUE;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired = Boolean.TRUE;

    @Column(name = "enabled")
    private Boolean enabled = Boolean.FALSE;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles;

}