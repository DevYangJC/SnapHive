package com.yjc.snaphive.model.dto.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserExportRequest {
    /**
     * 导出类型�?-�?2-�?3-�?4-�?5-自定�?
     */
    private Integer type;

    /**
     * 自定义开始时�?
     */
    private Date startTime;

    /**
     * 自定义结束时�?
     */
    private Date endTime;
}
