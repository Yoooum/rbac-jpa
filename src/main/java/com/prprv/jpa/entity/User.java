package com.prprv.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Yoooum
 */
@Data
@Entity
@Schema(description = "用户信息")
public class User {
    @Id
    @Schema(description = "用户序号")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Schema(description = "用户生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthday;

    @Schema(description = "用户签名")
    private String description;

    @Schema(description = "用户角色")
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "relation_user_role", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Set<Role> role;
}
