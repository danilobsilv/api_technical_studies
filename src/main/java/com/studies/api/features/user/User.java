package com.studies.api.features.user;

import com.studies.api.features.user.dto.CreateUserType;
import com.studies.api.features.user.dto.UpdateUserModType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(
    name = "user",
    indexes = {
        @Index(name = "idx_user_name", columnList = "user_name"),
        @Index(name = "idx_user_role", columnList = "user_role")
    }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "user_id", updatable = false, nullable = false, columnDefinition = "char(36)")
    private String userId;

    @Column(name = "user_name", length = 150, nullable = false)
    private String userName;

    @Column(name = "user_email", length = 150, nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_password", length = 255, nullable = false)
    private String userPasswordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    @Column(name = "user_is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "user_created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    public User(CreateUserType data){
        this.userName = data.userName();
        this.userEmail = data.userEmail();
        this.userPasswordHash = data.userPassword();
        this.userRole = data.userRole();
    }

    public void updateUser(UpdateUserModType data) {
        if (data.userName() != null && !data.userName().isBlank()) {this.userName = data.userName();}
        if (data.userEmail() != null && !data.userEmail().isBlank()) {this.userEmail = data.userEmail();}
        if (data.userPassword() != null && !data.userPassword().isBlank()) {this.userPasswordHash = data.userPassword();}
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return this.userPasswordHash;
    }

    @Override
    public String getUsername() {
        return this.userEmail;
    }
}
