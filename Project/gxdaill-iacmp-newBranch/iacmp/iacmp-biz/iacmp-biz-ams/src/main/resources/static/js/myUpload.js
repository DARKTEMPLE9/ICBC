/**
 * 配置文件（如果没有默认字样，说明默认值就是注释下的值）
 * 但是，on*（onSelect， onMaxSizeExceed...）等函数的默认行为
 * 是在ID为i_stream_message_container的页面元素中写日志
 */
// define(function(require,exports,module) {
//     var $ = require("jquery");

var WebRoot = '/'
    + (window.location.pathname.indexOf('/') == 0 ? window.location.pathname
            .split('/')[1]
        : window.location.pathname.split('/')[0]) + '/';
var aa = 529;
var _t0;
var uploadfun;
var onqueuefun = function () {
};
var delImageTypeId = "uip_delete";
var selSize;

//上传初始化函数
//$(function() {
function initUploadBtn(billCode, billId, batchId, sysCode, Msize, $) {
    //exports.initUploadBtn=function(billCode, billId, batchId, sysCode, Msize) {
    var config0 = {
        browseFileId: "i_select_files0",
        /** 选择文件的ID, 默认: i_select_files */
        multipleFiles: true,
        /** 多个文件一起上传, 默认: false */
        autoUploading: true,
        /** 选择文件后是否自动上传, 默认: true */
        autoRemoveCompleted: true,
        /** 是否自动删除容器中已上传完毕的文件, 默认: false */
        minSize: 0,
        maxSize: Msize,
        postVarsPerFile: {
            /** 上传文件时传入的参数，默认: {} */
            param1: billCode,//单据类型code
            param2: billId,//单据类型id
            param3: batchId,//批次id
            param4: sysCode,//系统code
            param5: "",	//上传人姓名
            prarm6: "",	//上传人编号
            param7: ""//后续要改，这是key无值
        },
        swfURL: "static/swf/FlashUploader.swf",
        /** SWF文件的位置 */
        tokenURL: "/uip/tk",
        /** 根据文件名、大小等信息获取Token的URI（用于生成断点续传、跨域的令牌） */
        frmUploadURL: "/uip/fd;",
        /** Flash上传的URI */
        uploadURL: "/uip/upload",
        /** HTML5上传的URI */
        simLimit: 200, /** 单次最大上传文件个数 */
        extFilters: [".jpg", ".png", ".doc", ".docx", ".pdf", ".rar", ".zip", ".*"], /** 允许的文件扩展名, 默认: [] */
        /** 文件大小超出的响应事件 */
        onMaxSizeExceed: function (size, limited, name) {
            alert('单个文件超过' + (Msize / 1024) / 1024 + 'M无法上传');
            var fade = document.getElementById('i_stream_files_queue0');
            if (selSize == 1) {
                fade.style.display = 'none';
            }
            //fade.style.display='none';
        },
        onFileCountExceed: function (selected, limit) {
            alert('onFileCountExceed');
        },
        /** 文件数量超出的响应事件 */
        onExtNameMismatch: function (name, filters) {
            alert('onExtNameMismatch');
        },
        /** 文件的扩展名不匹配的响应事件 */
        onComplete: function (file) {//上传完成后
            myuploadOnComplete(file.msg);
        },
        /** 单个文件上传完毕的响应事件 */
        onUploadError: function (status, msg) {
            myuploadOnComplete(msg);
        },
        /** 文件上传出错的响应事件 */

        onSelect: function (selected) {
            var fade = document.getElementById('i_stream_files_queue0');
            if (selected.length > aa) {
                fade.style.display = 'none';
            } else {
                fade.style.display = 'block';
            }
            selSize = selected.length;
        },
        //上传完成相应事件
        onQueueComplete: function (msg) {
            alert(msg);
//			var uploadfilediv=document.getElementById('uploadfilediv').show();
//			$(uploadfilediv).show();
            //$(uploadfilediv).hide();
            onQueueComplete();
        },

        onCancel: function () {
            var l = $(".stream-files-scroll li").length;
            if (l == 1) {
                var fade = document.getElementById('i_stream_files_queue0');
                fade.style.display = 'none';
            }
            /**/
        }
    }


    //隐藏无用控件 初始化上传控件按钮大小
    _t0 = new Stream(config0);
    alert(1);
    var H_height = $(window).height();
    var W_width = $(window).width();
    /*     $('#i_stream_files_queue0').css({
             "left": W_width / 2 - 300,
             "top": H_height / 2 - 150
         });*/
};

/*
    $('#i_select_files0').css({
        "height": "50px",
        "width": "110px"
    });
    $('#tzsc').css({
        "display": "none"
    });
*/

//隐藏文档文件标题栏
function hidetitle() {
    $("#uploadfilediv").css({
        "display": "none"
    });
}


/*打开文件方法*/
function openFile(id, type) {
    //exports.openFile=function (id, type) {

    if (type != null) {
        if (type == "pdfFileImage.png") {
            var popup = window.open("pages/imageTool/viewfile/filepdf.html?id=" + id);
        } else if (type == "wordFileImage.png") {
            var popup = window.open("pages/imageTool/viewfile/filedoc.html?id=" + id);
            //popup.location.href = "pages/imageTool/viewfile/filedoc.html?id=" + id;
        } else if (type == "excelFileImage.jpg") {
            var popup = window.open("pages/imageTool/viewfile/fileexcel.html?id=" + id);
            //popup.location.href = "pages/imageTool/viewfile/filedoc.html?id=" + id;
        } else if (type == "txtFileImage.png") {
            var popup = window.open("pages/imageTool/viewfile/filetxt.html?id=" + id);
            //popup.location.href = "pages/imageTool/viewfile/filedoc.html?id=" + id;
        } else {
            if (confirm('是否下载文件?')) {
                makedownloadZip("id", null, null, null, null, id, null);
            } else {
            }
        }
    }
}

function deleteFileUl(id) {
    //exports.deleteFileUl=function (id) {

    var liId = '#' + id + '_imageLi';
    $(liId).remove();
    updateIndex();
}

//删除文件方法
//
function delFile(id) {
    alert(id)
    //exports.delFile=function (id) {
    if (!confirm("确定删除该文件？")) {
        return;
    }
    $.ajax({
        url: WebRoot + "imBatch/deleteFile?idsIndex=" + id,
        type: "post",
        data: {},
        asycn: false,
        success: function (data) {
            var liId = '#' + id + '_imageLi';
            $(liId).remove();
            updateIndex();
            alert("删除成功");
            setImageNumToTree(billId, -1);
            setImageNumToTree(delImageTypeId, 1);
        },
        dataType: "json"
    });
}


//提取选中影像文件 下载
function downloadZip() {
    //exports.downloadZip=function (){
    // 双选框选择的影像id
    var imagechangeid = "";
    var filechangeid = "";
    $('input:checkbox[name=types]:checked').each(function (i) {
        if (0 == i) {
            imagechangeid = $(this).val();
        } else {
            imagechangeid += ("," + $(this).val());
        }
    });
    // 双选框选择的影像id
    $('input:checkbox[name=file]:checked').each(function (i) {
        if (0 == i) {
            filechangeid = $(this).val();
        } else {
            filechangeid += ("," + $(this).val());
        }
    });
    if (!imagechangeid && !filechangeid) {
        alert("请选择需要下载的影像");
        return false;
    }
    //发送下载请求
    makedownloadZip("id", null, null, null, null, filechangeid, imagechangeid);

}

/** 下载方法
 * id下载 fileid或imageid必填
 * batch下载 batchid必填
 * bill下载 batchid批次 billcodefir或billcodesec必填 sysflag必填
 * @param downloadtype    id或batch或bill
 * @param batchid    批次id
 * @param billcodefir    一级分类
 * @param billcodesec    二级分类
 * @param sysflag    系统标示
 * @param fileid    文件id
 * @param imageid    影像id
 */
function makedownloadZip(downloadtype, batchid, billcodefir, billcodesec, sysflag, fileid, imageid) {
    //exports.makedownloadZip=function (downloadtype, batchid, billcodefir, billcodesec, sysflag, fileid, imageid){
    //这里证明只选择了一个文件
    if (imageid === "" && fileid.indexOf(",") < 0) {
        var actionUrl = "downloadAction!downloadOne.do?fileid=" + fileid;
        var form = $("<form>");//定义一个form表单
        form.attr("style", "display:none");
        form.attr("target", "");
        form.attr("method", "post");
        form.attr("action", actionUrl);
        $("body").append(form);//将表单放置在web中
        form.submit();
    } else if (fileid === "" && imageid.indexOf(",") < 0) {
        var actionUrl = "downloadAction!downloadOne.do?imageid=" + imageid;
        var form = $("<form>");//定义一个form表单
        form.attr("style", "display:none");
        form.attr("target", "");
        form.attr("method", "post");
        form.attr("action", actionUrl);
        $("body").append(form);//将表单放置在web中
        form.submit();
    } else {
        $.ajax({
            url: WebRoot + "downloadAction!makeZip.do",
            type: "post",
            data: {
                downloadtype: downloadtype,
                batchid: batchid,
                billcodefir: billcodefir,
                billcodesec: billcodesec,
                sysflag: sysflag,
                fileid: fileid,
                imageid: imageid
            },
            asycn: false,
            success: function (data) {
                if ("success" == data.makeState) {
                    downloadpost(data.makePath, data.fileName);
                    //alert("下载完成");
                } else {
                    downloadpost(data.makePath, data.fileName);
                    alert("未下载成功的影像:" + data.makeState);
                }

            },
            dataType: "json"
        });

    }
}

//发生下载请求
function downloadpost(makePath, name) {
    //exports.downloadpost=function (makePath, name){
    var actionUrl = "downloadAction!downloadZip.do?makePath=" + makePath + "&fileName=" + name;
    var form = $("<form>");//定义一个form表单
    form.attr("style", "display:none");
    form.attr("target", "");
    form.attr("method", "post");
    form.attr("action", actionUrl);
    $("body").append(form);//将表单放置在web中
    form.submit();

}

//修改上传参数方法
function uploadPostvars(billCode, billId, batchId, sysCode, createusername, createuser, key) {
    //exports.uploadPostvars=function (billCode, billId, batchId, sysCode, createusername, createuser, key){
    _t0.config.postVarsPerFile.param2 = billId;
    _t0.config.postVarsPerFile.param3 = batchId;
    _t0.config.postVarsPerFile.param1 = billCode;
    if (sysCode != null && sysCode != "") {
        _t0.config.postVarsPerFile.param4 = sysCode;
    } else {
        _t0.config.postVarsPerFile.param4 = "";
    }
    if (createusername != null && createusername != "") {
        _t0.config.postVarsPerFile.param5 = createusername;
    } else {
        _t0.config.postVarsPerFile.param5 = "";
    }
    if (createuser != null && createuser != "") {
        _t0.config.postVarsPerFile.param6 = createuser;
    } else {
        _t0.config.postVarsPerFile.param6 = "";
    }
    if (key != null && key != "") {
        _t0.config.postVarsPerFile.param7 = key;
    } else {
        _t0.config.postVarsPerFile.param7 = "";
    }
    /*_t0.config.postVarsPerFile.param4=sysCode;
    _t0.config.postVarsPerFile.param5=createusername;
    _t0.config.postVarsPerFile.param6=createuser;
    _t0.config.postVarsPerFile.param7=key;*/
}

//修改上传回调函数方法
function setOnComplete(fun) {
    //exports.setOnComplete=function (fun){
    uploadfun = fun;
}

//检验上传是否成功
function myuploadOnComplete(file) {
    //exports.myuploadOnComplete=function (file){
    var jsonobj = JSON.parse(file);
    console.log(jsonobj);
    if (jsonobj.success) {
        if (jsonobj.start > 0) {
            uploadfun(file);
        } else {
            alert("上传失败: 文件内容为空");
            return false;
        }
    } else {
        alert("上传失败:" + jsonobj.message);
        return false;
    }
}

//扫描页单个文件上传完成回调函数
function onCompleteForSweepImage(file) {
    //exports.onCompleteForSweepImage=function (file){
    //每次上传的时候 都把滚动条的位置放到开始
    $(".stream-files-scroll").scrollTop(0);
    var jsonobj = JSON.parse(file);
    //id
    var id = jsonobj.id;
    //路径
    var path = jsonobj.path;
    //类型 图片为image 文件为 PDF doc docx 大小写不一定
    var type = jsonobj.type;
    //备注
    var remark = jsonobj.remark;
    //文件名
    var name = jsonobj.name;
    //图片文件
    if ("image" == type) {
        var sltHtml = getImageLiHTML(path, id, "", true, 0);
        var h = $("#imageul").html();
        $("#imageul").html(h + sltHtml);
        $("#uploadimagediv").show();
        initImageTool();
        viewerInit();
    }
    //word文件
    else if ("doc" == type || "docx" == type || "DOC" == type || "DOCX" == type) {
        var sltHtml = getFileLiHTML(path, id, "wordFileImage.png", name, "", true);
        var h = $("#newfileul").html();
        $("#newfileul").html(h + sltHtml);
        $("#uploadfilediv").show();
        initImageTool();
    }
    //pdf文件
    else if ("pdf" == type || "PDF" == type) {
        var sltHtml = getFileLiHTML(path, id, "pdfFileImage.png", name, "", true);
        var h = $("#newfileul").html();
        $("#newfileul").html(h + sltHtml);
        $("#uploadfilediv").show();
        initImageTool();
    }
    //rar文件
    else if ("rar" == type || "zip" == type) {
        var sltHtml = getFileLiHTML(path, id, "rarFileImage.jpg", name, "", true);
        var h = $("#newfileul").html();
        $("#newfileul").html(h + sltHtml);
        $("#uploadfilediv").show();
        initImageTool();
    }
    //excel文件
    else if ("xls" == type || "xlsx" == type) {
        var sltHtml = getFileLiHTML(path, id, "excelFileImage.jpg", name, "", true);
        var h = $("#newfileul").html();
        $("#newfileul").html(h + sltHtml);
        $("#uploadfilediv").show();
        initImageTool();
    }

    //excel文件
    else if ("txt" == type || "TXT" == type) {
        var sltHtml = getFileLiHTML(path, id, "txtFileImage.png", name, "", true);
        var h = $("#newfileul").html();
        $("#newfileul").html(h + sltHtml);
        $("#uploadfilediv").show();
        initImageTool();
    } else {
        var sltHtml = getFileLiHTML(path, id, "otherFile.jpg", name, "", true);
        var h = $("#newfileul").html();
        $("#newfileul").html(h + sltHtml);
        $("#uploadfilediv").show();
        initImageTool();
    }
    if (billId != null && billId != "") {
        setImageNumToTree(billId, 1);
    }
}

//全部取消按钮
function allCancel() {
    //exports.allCancel=function(){
    _t0.cancel();
}

//修改全部上传完成回调函数方法
function setonQueue(fun) {
    //exports.setonQueue=function(fun){
    onqueuefun = fun;

}

//上传完成调用方法
function onQueueComplete() {
    //exports.onQueueComplete=function(){
    onqueuefun();
    alert('上传完成')
    /* var fade = document.getElementById('i_stream_files_queue0');
     fade.style.display = 'none';*/
}

// })