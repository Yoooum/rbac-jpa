package com.prprv.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @author Yoooum
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Schema(description = "角色信息")
public class Role extends AbstractEntity {
    @Schema(description = "角色标识")
    @NotBlank(message = "角色标识不能为空")
    @Column(unique = true, nullable = false)
    private String role;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色描述")
    private String description;

    // 角色拥有的权限
    @Schema(description = "角色拥有的权限")
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "relation_role_permission", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Set<Permission> permission;

    // 角色拥有的用户
    @Schema(description = "拥有该角色的用户")
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "relation_role_user", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Set<User> user;
}
