package ir.imorate.jobify.entity.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persistent_logins")
@Getter
@Setter
public class PersistentLogins {

    @Id
    @Column(name = "series", length = 64)
    private String series;

    @Column(name = "username", nullable = false, length = 32)
    private String username;

    @Column(name = "token", nullable = false, length = 64)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_used", nullable = false)
    private Date lastUsed;

}