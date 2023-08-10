package com.mobiliz.repository.entity;

import com.mobiliz.repository.enums.ERole;
import com.mobiliz.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "tbl_userauthentication")
public class UserAuthentication extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @ElementCollection
    private List<Long> regions;
    @Email(message = "Email ")
    @Column(unique = true)
    private String email;
    private String name;
    private String surname;
    private Long companyId;
    private String companyName;
    @Enumerated(EnumType.STRING)
    private ERole role;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status=EStatus.ACTIVE;
}
