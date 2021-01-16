package cn.cqu.demo.model;

public class SingleMsg<T> {
    private T msg;
    private T msg2;

    public T getMsg2() {
        return msg2;
    }

    public void setMsg2(T msg2) {
        this.msg2 = msg2;
    }

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }
}
