package com.wx.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huangqing
 * @version 1.0
 * @date 2021/7/26 9:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private Long id;
    private String name;
    private Long teacherId;
    private String teacherName;
}
