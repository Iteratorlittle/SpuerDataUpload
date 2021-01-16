package cn.cqu.demo.model;

import java.util.ArrayList;
import java.util.List;

public class DataBean {
    private List<String> msglist=new ArrayList<>();
    private List<String> headlist=new ArrayList<>();
    public List<String> getMsglist() {
        return msglist;
    }

    public void setHeadlist(List<String> headlist) {
        this.headlist = headlist;
    }

    public void setMsglist(List<String> msglist) {
        this.msglist = msglist;
    }

    public List<String> getHeadlist() {
        return headlist;
    }

    public Integer getListLenght(){
        return headlist.size();
    }
}