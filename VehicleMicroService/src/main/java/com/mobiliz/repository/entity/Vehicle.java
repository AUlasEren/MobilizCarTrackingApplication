package com.mobiliz.repository.entity;

import com.mobiliz.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_vehicle")
public class Vehicle extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private Long companyId;
    private Long regionId;
    @Column(nullable = false, unique = true)
    private String plate;
    private String chasisNumber;
    private String label;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String modelYear;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status=EStatus.ACTIVE;





}
