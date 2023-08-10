package com.mobiliz.dto.request;

import com.mobiliz.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserRequestDto {
    private String name;
    private String email;
    private String surname;
    private Long companyId;
    private List<Long> regions;
    private String companyName;
    private String role;
}
