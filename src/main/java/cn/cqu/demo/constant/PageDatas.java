package cn.cqu.demo.constant;

import java.util.ArrayList;
import java.util.HashMap;

public class PageDatas {
    private ArrayList<byte[]> arrayLists;
    public PageDatas(){
        this.arrayLists=new ArrayList<>();
    }
    public ArrayList<byte[]> getArrayLists() {
        return this.arrayLists;
    }
    public void setArrayLists(ArrayList<byte[]> arrayLists) {
        this.arrayLists = arrayLists;
    }
    public void addtoArrayLists(byte[] arrayList){
        this.arrayLists.add(arrayList);
    }
}