package com.wx.common.commonresult;

import com.wx.common.tool.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 通用返回对象
 */
@Data
@Builder
@AllArgsConstructor
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;
    private Integer count;

    protected CommonResult() {
    }

    private CommonResult(long code,Integer count ,String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.count = count;
    }
    /**
     * 成功返回结果
     * 无数据
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),1, ResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),1, ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     *成功返回结果
     *
     * @param data 获取的数据
     * @param count 总条数
     */

    public static <T> CommonResult<T> success(T data,Integer count) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),count, ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> CommonResult<T> success(T data,Integer count,String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),count, message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(),0, errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(),0, message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(),0, message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(),0, ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(),0, ResultCode.FORBIDDEN.getMessage(), data);
    }
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
