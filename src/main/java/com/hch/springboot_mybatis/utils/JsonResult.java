package com.hch.springboot_mybatis.utils;

public class JsonResult<T> {

    private T data;
    private String code;
    private String msg;
    private Integer totalPage;

    public JsonResult() {
        this.data = null;
        this.code = "1";
    }

    public JsonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(T data, String code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(T data, String code, String msg, Integer totalPage) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.totalPage = totalPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "data=" + data +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
