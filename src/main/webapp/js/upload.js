function showselectfile(text){
    document.getElementById('showfilename').innerText=text;
}
function upload(obj){
    if(obj=='file'){
        UpladFile();
    }else{
        Uploaddata();
    }
}
var handler_num=-1;
function uploadComplete(e) {
}
function uploadFailed(e) {
    alert("上传失败");
}
function progressFunction(e){
    var progress_bar = document.getElementsByClassName("progress")[handler_num];
    var pro_shower = document.getElementsByClassName("pro_shower")[handler_num];
    var loading = Math.round(e.loaded / e.total * 100);
    console.log("loading::", loading);
    if(loading === 100){
        pro_shower.innerText = '上传完成';
    }else{
        pro_shower.innerText = loading+"%";
    }
    progress_bar.style.width = String(loading) + "%";
    progress_bar.style.borderRadius='30px';
}
function UpladFile() {
    var file_obj = document.getElementById("xFile").files[0]
    if(file_obj){
        var divbox=document.createElement("div");
        divbox.className='progressbox';
        var divchid=document.createElement('div');
        divchid.className='progress';
        var a=document.createElement('a');
        a.innerText='0%';
        a.className='pro_shower';
        divbox.appendChild(divchid);
        divbox.appendChild(a);
        var forms=document.getElementById('forms');
        forms.appendChild(divbox);
        var url = "http://localhost:8080/UploadFile";
        var form = new FormData();
        var filebox_obj=document.getElementById("xFile")
        if(filebox_obj.value.substring(filebox_obj.value.lastIndexOf(".")+1)=='xls'){
            form.append("xlsfile", file_obj);
        }else{
            form.append("xlsxfile", file_obj);
        }
        var xhr = new XMLHttpRequest();
        xhr.onload = uploadComplete;
        xhr.onerror =  uploadFailed;
        xhr.upload.onprogress = progressFunction;
        xhr.open("POST", url, true);
        xhr.send(form);
        handler_num++;
    }else{
        alert("请先选择文件后再上传");
    }
}
var data_number=0;
var uploaddatas=new Array();
var pagenum=0;
function Uploaddata(){
    //分页异步上传算法，1000条/页

}
function areachange(obj){
    let cutnumber=obj.value.split('~');
    var number=pagenum*1000+cutnumber.length-1;
    data_number=cutnumber.length-1;
    $('#updatanum').text('数据条数'+number);
    if(data_number>=1000){
        uploaddatas[pagenum]=obj.value.substring(0,findindex(obj.value,'~',999)+1);
        obj.value=obj.value.substring(findindex(obj.value,'~',999)+1);
        pagenum++;
    }
}
function findindex(str,cha,num){
    var x=str.indexOf(cha);
    for(var i=0;i<num;i++){
        x=str.indexOf(cha,x+1);
    }
    return x;
}