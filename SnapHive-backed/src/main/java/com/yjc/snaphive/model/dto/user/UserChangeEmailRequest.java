package com.yjc.snaphive.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改邮箱请求
 */
@Data
public class UserChangeEmailRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 新邮�?
     */
    private String newEmail;

    /**
     * 验证�?
     */
    private String code;
}
