var PUBLIC_KEY;
function login(){
    var inputuid=document.getElementById('uid').value;
    var password=document.getElementById('password').value;
    if(inputuid==''||password==''){
        alert('账号密码为空');
    }else{
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
            $("#toast").show(0);
        }else{
            var publicKeyExponent=getCookie("publicexpen");
            var publicKeyModulus=getCookie("publicmodel");
            var data=JSON.stringify({"uid":inputuid,"passwords":password,"tokens":''});
            RSAUtils.setMaxDigits(200);
            var key = RSAUtils.getKeyPair(publicKeyExponent, "", publicKeyModulus);
            var encryptdata=RSAUtils.encryptedString(key,data.split("").reverse().join(""));
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/login",
                contentType: "application/json",
                dataType: "json",
                data: encryptdata,
                success: function (data) {
                    $('#toast').hide(1000);
                    if(data.data.msg==1){
                        localStorage.setItem("excel_md5token",data.data.msg2);
                        window.location.href='./mainpage.jsp'
                    }
                }
            });
        }
    }
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
function req_succes(data){

}
function req_error(data) {

}