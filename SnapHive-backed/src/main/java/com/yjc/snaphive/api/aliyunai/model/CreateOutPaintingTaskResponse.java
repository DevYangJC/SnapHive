package com.yjc.snaphive.api.aliyunai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建扩图任务响应�?
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOutPaintingTaskResponse {

    private Output output;

    /**
     * 表示任务的输出信�?
     */
    @Data
    public static class Output {

        /**
         * 任务 ID
         */
        private String taskId;

        /**
         * 任务状�?
         * <ul>
         *     <li>PENDING：排队中</li>
         *     <li>RUNNING：处理中</li>
         *     <li>SUSPENDED：挂�?/li>
         *     <li>SUCCEEDED：执行成�?/li>
         *     <li>FAILED：执行失�?/li>
         *     <li>UNKNOWN：任务不存在或状态未�?/li>
         * </ul>
         */
        private String taskStatus;
    }

    /**
     * 接口错误码�?
     * <p>接口成功请求不会返回该参数�?/p>
     */
    private String code;

    /**
     * 接口错误信息�?
     * <p>接口成功请求不会返回该参数�?/p>
     */
    private String message;

    /**
     * 请求唯一标识�?
     * <p>可用于请求明细溯源和问题排查�?/p>
     */
    private String requestId;

}
