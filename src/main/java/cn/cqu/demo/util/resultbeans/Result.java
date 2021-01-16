package cn.cqu.demo.util.resultbeans;

public class Result<T> {
    public int rescode;
    public String msg;
    public T data;

    public Result<T> setCode(int code) {
        this.rescode = code;
        return this;
    }
    public Result<T> setCode(RestCode result) {
        this.rescode = result.rescode;
        return this;
    }
    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
    public int getCode() {
        return rescode;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
