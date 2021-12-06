package ir.imorate.jobify.entity.main;

import ir.imorate.jobify.entity.main.enums.ContactUsType;
import ir.imorate.jobify.entity.security.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "contact_us")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_us_type", nullable = false)
    private ContactUsType contactUsType;

    @Column(name = "body", nullable = false)
    private String body;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
