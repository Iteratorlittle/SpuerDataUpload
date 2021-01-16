var nowpage=0;
window.onload=function() {
    //初始化一页数据
    getmoredata();
    nowpage++;
}
function getmoredata(){
    var tokens=localStorage.getItem("excel_md5token");
    var publicKeyExponent=getCookie("publicexpen");
    var publicKeyModulus=getCookie("publicmodel");
    var data=JSON.stringify({"nowpage":nowpage,"tokens":tokens,"tablenum":'0'});
    RSAUtils.setMaxDigits(200);
    var key = RSAUtils.getKeyPair(publicKeyExponent, "", publicKeyModulus);
    var encryptdata=RSAUtils.encryptedString(key,data.split("").reverse().join(""));
    if(getCookie('publicmodel')==''||getCookie('publicexpen')==''){
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/GetRsa",
            contentType: "application/json",
            dataType: "json",
            data: '',
            success: function (data) {
                setCookie("publicmodel",data.data.publicmodel);
                setCookie("publicexpen",data.data.publicexpen);
                login();
            }
        });
    }else{
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/GetPageData",
            contentType: "application/json",
            dataType: "json",
            data: encryptdata,
            success: function (data) {
                nowpage++;
                $.each(data.data,function(index,item){
                    var ul=document.createElement("ul");
                    var num=0;
                    $.each(item,function (index,item) {
                        num+=1;
                        var li=document.createElement("li");
                        li.innerText=item;
                        li.style.fontSize=(item.length/300)*10+'px';
                        ul.appendChild(li);
                    });
                    setTimeout(function () {
                        document.getElementById('datashower').appendChild(ul);
                    },100);
                });
            }
        });
    }
}
function setelm() {

}
function getCookie(name){
    var strCookies = document.cookie;
    var cookiesindex=strCookies.indexOf(name);
    var value='';
    if(cookiesindex!=-1){
        cookiesindex=cookiesindex+name.length+1;
        var cookies_end=strCookies.indexOf(";",cookiesindex);
        if(cookies_end==-1){
            cookies_end=strCookies.length;
        }
        value=unescape(strCookies.substring(cookiesindex,cookies_end));
    }
    return value;
}
function setCookie(key,value){
    var oDate=new Date();
    oDate.setDate(oDate.getDate()+1800);
    document.cookie=key+"="+value+"; expires="+oDate.toDateString();
}