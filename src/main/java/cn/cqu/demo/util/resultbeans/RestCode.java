package cn.cqu.demo.util.resultbeans;

public enum  RestCode {
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    SERVICE_ERR(500);
    public int rescode;
    RestCode(int code){
        this.rescode=code;
    }
}
