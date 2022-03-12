package com.wx.auth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;

@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class WxPermission  implements GrantedAuthority {
    private Integer id;
    private Integer pid;
    private String name;
    private String value;
    private String icon;
    private Integer type;
    private String uri;
    private Integer status;
    private Date createTime;
    private String sort;

    @Override
    public String getAuthority() {
        return this.uri;
    }
}