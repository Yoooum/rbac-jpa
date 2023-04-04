package com.prprv.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Yoooum
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Schema(description = "用户信息")
public class User extends AbstractEntity {
    @Schema(description = "用户姓名")
    @Column(nullable = false, unique = true)
    private String username;

    @Schema(description = "用户邮箱")
    @Column(nullable = false, unique = true)
    private String email;

    @Schema(description = "用户密码")
    @Column(nullable = false)
    private String password;

    @Schema(description = "用户电话")
    private Long phone;

    @Schema(description = "用户性别")
    private String gender;

    @Schema(description = "用户头像")
    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "用户生日")
    private LocalDate birthday;

    @Schema(description = "用户签名")
    private String description;

    @Schema(description = "用户角色")
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "relation_user_role", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Set<Role> role;
}
