package cn.cqu.demo.model;

public class UserBaseInfo {
    private String uid;
    private String passwords;
    private String tokens;

    public String getTokens() {
        return tokens;
    }

    public String getUid() {
        return uid;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }
}
