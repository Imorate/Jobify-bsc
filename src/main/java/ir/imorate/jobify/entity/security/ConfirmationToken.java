package ir.imorate.jobify.entity.security;

import ir.imorate.jobify.entity.security.enums.ConfirmationTokenType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "confirmation_token")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotNull(message = "Confirmation Token Type may not be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "confirmation_token_type", nullable = false, updatable = false)
    private ConfirmationTokenType confirmationTokenType;

    @Pattern(message = "Token must be match with the regex",
            regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")
    @NotBlank(message = "Token may not be blank")
    @Size(max = 36)
    @Column(name = "token", nullable = false, updatable = false, length = 36)
    private String token;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "confirm_date")
    private LocalDateTime confirmedAt;

    @Column(name = "expires", nullable = false)
    private LocalDateTime expiresAt;

    @NotNull(message = "User may not be null")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

}