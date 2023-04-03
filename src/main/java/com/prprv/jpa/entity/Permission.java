package com.prprv.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Yoooum
 */
@Data
@Entity
@Schema(description = "权限信息")
public class Permission {
    @Id
    @Schema(description = "权限序号")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "权限标识")
    @NotBlank(message = "权限标识不能为空")
    @Column(unique = true, nullable = false)
    private String permission;

    @Schema(description = "权限名称")
    private String description;
}
