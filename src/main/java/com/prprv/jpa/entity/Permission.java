package com.prprv.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yoooum
 */
@Data
@Entity
@NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "权限标识不能为空")
    @Column(unique = true, nullable = false)
    private String permission;
    private String description;

    public Permission(String permission) {
        this.permission = permission;
    }
}
