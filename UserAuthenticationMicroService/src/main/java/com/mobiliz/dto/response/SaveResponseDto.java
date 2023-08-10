package com.mobiliz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveResponseDto {
    private Long companyId;
    private Long userId;
    private String role;
    private String name;
    private String surname;
}
