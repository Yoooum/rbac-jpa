package com.prprv.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Yoooum
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Schema(description = "权限信息")
public class Permission extends AbstractEntity {
    @Schema(description = "权限标识")
    @NotBlank(message = "权限标识不能为空")
    @Column(unique = true, nullable = false)
    private String permission;

    @Schema(description = "权限名称")
    private String description;
}
