package com.mvp.arm.commonmodule.base.bean;

/**
 * 响应基类
 *
 * @author Gjm
 * @date 2018/5/23
 */
public class Repo<T> {
    private String code;
    private String description;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isOk(){
        return "000000".equals(code);
    }

    @Override
    public String toString() {
        return "Repo{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", data=" + data +
                '}';
    }
}
