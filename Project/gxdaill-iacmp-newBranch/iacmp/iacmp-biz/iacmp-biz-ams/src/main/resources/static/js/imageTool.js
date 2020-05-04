/*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓  全局变量定义区  ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/
var WebRoot = '/'
    + (window.location.pathname.indexOf('/') == 0 ? window.location.pathname
            .split('/')[1]
        : window.location.pathname.split('/')[0]) + '/';
// ecm返回id 格式为 1，2@3，4
var fileIdEcmPath = "";
// 影像集合
var imgList = [];
// 影像存储地址
var imageSavePath = "";
// trans存储地址
var httpFileTransPath = "";
// trans IP地址
var httpfiletransIp = "";
// trans 端口号
var httpfiletransPort = "";
// 当前地址
var url = window.location.href;
// 加密key值
var key = getQueryString("key");
// 业务编号1
var operationcode = "";
// 业务编号2
var regbillglideno = "";
// 员工编号
var tellerno = "";
// 当前批次ID
var batchId = "";
// 员工姓名
var createUser = "";
var createUserName = "";
// 当前分类类型ID
var billId = "";
// 当前分类类型编码
var billno = "";
// 参数链接串
var param = "";
// 原字体大小
var fontWeight;
// 原字体颜色
var fontColor;
var systemflag = "";
// iamgeui中的html
var imageUlHtml = "";
var fileUlHtml = "";
var billName = "";
var permissions = "";
var deleteFlag = "";
var isOperCode;
var product = "";
var delImageTypeId = "uip_delete";
var id = "";
var amsBatchId = "";
var orgCode = "";
var sizeMethod;
var maxSizeParam;
var amsbatchid = "";
var hadPath;
/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

//屏幕大小变化时
/*window.onresize = function(){

    //单独复制出来一份树的CSS。。 如果 下面的 imgTreeCss 这里面的方法要是变化了。。别忘了替换这里的。
    var h = $(window).height() - 150;
    var divWidth = $(window).width()/4;

    $("#treeDiv,#jbxcDiv").css({
        width : divWidth-30

    });
    $("#ywxxDiv").slimScroll({
        height : h,
        overflow : "auto"
    });
    $("#imgTree").css({
        /!* 'height' : h, *!/
        width : 1500

    });
    $("#imgTreeScroll").css({
        'height' : h,
        'overflow' : 'auto'

    });

    $("#imgTreeScroll").mCustomScrollbar({
        theme : "dark",
        axis : "yx"
    });
};*/

/* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 入口函数 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */

$(document).ready(function () {
    // 登记批次信息,并获取分类信息
    $("#jbxcDiv").show();
    //$("#treeDiv").hide();
    saveBatchMess();
    //  refushImgList();
    fileUlHtml = $("#newfileul").html();
    imageUlHtml = $("#imageul").html();
    // 初始化上传控件
    initUploadBtn(null, "noClassify", batchId, systemflag, maxSizeParam * 1024 * 1024);
    // 设置上传完成后回调
    setOnComplete(onCompleteForSweepImage);
    // 更新上传控件参数
    uploadPostvars(null, "noClassify", batchId, systemflag, createUserName, tellerno, key);
    //影像缺失选项卡切换
    $('#myTab a[href="#ios"]').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
        $("#ios").show();
        $("#home").css("display", "none");
    });
    $('#myTab a[href="#home"]').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
        $("#home").show();
        $("#ios").css("display", "none");
    });
    setpermissions();
});


/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
function setpermissions() {
    //上传权限
    //$("#scanImageLi").hide();
    //$("#i_select_files0").hide();
    $("#twoCodeLi").hide();

    //获取删除权限
    deleteFlag = permissions.charAt(2);
    //下载权限
    if (permissions.charAt(3) == 0) {
        $("#downImageLi").hide();
    }
}

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 初始化树的样式
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function imgTreeCss() {

    var h = $(window).height() - 170;
    var divWidth = $(window).width() / 4 + 15;

    $("#treeDiv,#jbxcDiv").css({
        width: divWidth - 50

    });
    $("#ywxxDiv").slimScroll({
        height: h,
        overflow: "auto"
    });
    $("#imgTree").css({
        /* 'height' : h, */
        width: 1500

    });
    $("#imgTreeScroll").css({
        'height': h,
        'overflow': 'auto'

    });

    $("#imgTreeScroll").mCustomScrollbar({
        theme: "dark",
        axis: "yx"
    });
    $("#imgTree").find(".tree-folder-header").each(function () {
        if ($(this).parent().css("display") == "block") {
            $(this).trigger("click");
        }
    });

    // 打开树时刷新右侧影像
    $('#imgTree').on('opened', function (evt, data) {

        var billIdTemp = "";
        id = data.name.split("id='")[1].split("'")[0];
        //获取影像类型id
        billId = data.name.split("billId='")[1].split("'")[0];
        billIdTemp = billId;
        //获取批次id
        batchId = data.name.split("batchId='")[1].split("'")[0];
        //获取操作号1
        operationcode = data.name.split("operationCode1='")[1].split("'")[0];
        //获取操作号2
        regbillglideno = data.name.split("operationCode2='")[1].split("'")[0];
        //获取类型（floder，item）
        var type = data.name.split("type='")[1].split("'")[0];
        //如果没找到节点则设置为空字符
        if (operationcode == null || operationcode == "undefinded") {
            operationcode = "";
        }
        if (regbillglideno == null || regbillglideno == "undefinded") {
            regbillglideno = "";
        }
        //设置上传权限（根据授权码进行判断）

        if (permissions.charAt(0) == 0) {
            //$("#scanImageLi").hide();
            //$("#i_select_files0").hide();
            $("#twoCodeLi").hide();
        } else {
            $("#scanImageLi").show();

            $("#twoCodeLi").show();
        }
        //受理号下隐藏分类按钮
        if (id == batchId) {
            isOperCode = true;
            billId = "";
            $("#classifyId").hide();
        } else {
            isOperCode = false;
            //展示分類
            $("#classifyId").show();
        }
        if (delImageTypeId == billId) {
            //$("#scanImageLi").hide();
            //$("#i_select_files0").hide();
            $("#twoCodeLi").hide();
            $("#classifyId").hide();
        }
        //  refushImgList();
        billName = $("#" + batchId + billId).text().split("(")[0];
        billId = billIdTemp;
        // 更新上传控件参数
        uploadPostvars(null, "noClassify", batchId, systemflag, createUserName, tellerno, key);
    });
}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

/*function replaceJBXX() {

	$("#jbxcDiv").show();
	$("#treeDiv").hide();

}

function turnJBXX() {

	$("#jbxcDiv").show();
	$("#treeDiv").hide();

}
function turnTree() {
	$("#jbxcDiv	").hide();
	resetLeftTreeScroll();
	$("#treeDiv").show();


}*/

function replaceJBXX() {

    $("#jbxcDiv").show();
    $("#treeDiv").hide();

}

function turnJBXX() {

    $("#jbxcDiv").show();
    $("#treeDiv").hide();

}

function turnTree() {
    $("#jbxcDiv	").show();
    resetLeftTreeScroll();
    $("#treeDiv").hide();

}

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 登记批次信息并获取业务数据
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function saveBatchMess() {
    //var met = window.location.search.split('&')[0].split('=')[1];
    var urlStr = window.location.search.substring(1);
    var paramArray = urlStr.split('&');
    var paramMap = {};
    $.each(paramArray, function (index, param) {
        var key = param.split('=')[0];
        var value = param.split('=')[1];
        paramMap[key] = encodeURI(value);
    });


    if (paramMap["method"] === "dangan") {
        var url = WebRoot + "amsBatchAction!queryScanMess.do";
        $.ajax({
            url: url,
            method: 'post',
            data: {
                params: JSON.stringify(paramMap),
            },
            async: false,
            success: function (data) {
                sizeMethod = WebRoot + "amsBatchAction!queryScanMess.do";
                if (data.errorMessage) {
                    alert(data.errorMessage);
                } else {
                    if (data.errorMessage) {
                        alert(data.errorMessage);
                    } else {
                        // 获取业务数据
                        hadPath = data.hadoopPath;
                        httpFileTransPath = data.paramMap.httpfiletransPath;
                        httpfiletransIp = data.paramMap.httpfiletransIp;
                        httpfiletransPort = data.paramMap.httpfiletransPort;
                        tellerno = data.createUser;
                        createUser = data.createUser;
                        createUserName = data.createUserName;
                        systemflag = "AMS"
                        batchId = data.amsBatchId;
                        billId = data.billId;
                        amsBatchId = data.amsBatchId;
                        orgCode = data.orgCode;
                        permissions = data.permissions;
                        product = data.product;
                        regbillglideno = data.pojo.batchNo;
                        regbillglideno = data.pojo.batchNo;
                        param = "tellerno=" + tellerno + "&createUser=" + createUser + "&createUserName=" + createUserName
                            + "&operationcode=" + amsBatchId + "&orgCode=" + orgCode
                            + "&regbillglideno=" + regbillglideno + "&billno="
                            + batchId + "&batchId=" + batchId + "&systemflag=" + systemflag + "&hadPath=" + hadPath;
                        // 设置影像类型
                        setBaseData(data);
                        //setBillType(data);
                        // 设置树
                        imgTree(data.lstTree);
                    }
                }
            },
            error: function () {
                alert("请求异常");
            }
        });
    } else if (paramMap["method"] === "danganQuery") {
        var url = WebRoot + "amsBatchAction!queryScanMess.do";
        $.ajax({
            url: url,
            method: 'post',
            data: {
                params: JSON.stringify(paramMap),
            },
            async: false,
            success: function (data) {
                sizeMethod = WebRoot + "amsBatchAction!queryScanMess.do";
                if (data.errorMessage) {
                    alert(data.errorMessage);
                } else {
                    if (data.errorMessage) {
                        alert(data.errorMessage);
                    } else {
                        // 获取业务数据
                        hadPath = data.hadoopPath;
                        httpFileTransPath = data.paramMap.httpfiletransPath;
                        httpfiletransIp = data.paramMap.httpfiletransIp;
                        httpfiletransPort = data.paramMap.httpfiletransPort;
                        tellerno = data.createUser;
                        createUser = data.createUser;
                        createUserName = data.createUserName;
                        systemflag = "AMS";
                        batchId = data.amsBatchId;
                        billId = data.billId;
                        amsBatchId = data.amsBatchId;
                        orgCode = data.orgCode;
                        permissions = data.permissions;
                        product = data.product;
                        regbillglideno = data.pojo.batchNo;
                        regbillglideno = data.pojo.batchNo;
                        param = "tellerno=" + tellerno + "&createUser=" + createUser + "&createUserName=" + createUserName
                            + "&operationcode=" + amsBatchId + "&orgCode=" + orgCode
                            + "&regbillglideno=" + regbillglideno + "&billno="
                            + batchId + "&batchId=" + batchId + "&systemflag=" + systemflag + "&hadPath=" + hadPath;
                        // 设置影像类型
                        setBaseData(data);
                        //setBillType(data);
                        // 设置树
                        imgTree(data.lstTree);
                    }
                }
            },
            error: function () {
                alert("请求异常");
            }
        });
    }
}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

var imageMess = "";

function setBaseData(data) {
    // 设置基本信息
    var ulHtml = "";
    /*for ( var i = 0; i < data.baseLst.length; i++) {
        var _class= "";
        if(i%2==0){*/
    ulHtml = '<li class="list-group-item list-group-item-success" style="white-space: nowrap;">档案名称：'
        + data.arcname + '</li>' +
        '<li class="list-group-item list-group-item-success" style="white-space: nowrap;">著录编号：'
        + data.batchno + '</li>';


    imageMess =
        "<div style='display:none;position:absolute;z-index:100;width:19%; height: 100%;word-wrap:break-word;font-size:12px; margin-top: 20px; margin-left: 1px;'>" +
        "<div class='widget-box'>" +
        "<div class='widget-header'>" +
        "<h4 class='lighter smaller'>" +
        "<span class='glyphicon glyphicon-comment'>业务信息</span></h4></div>" +
        "<div class='widget-body'>" +
        "<div class='widget-main'>" +
        "<div class='dialogs' id='ywxxDiv' style='height: 100%;word-wrap:break-word; overflow: auto;'><ul class='list-group' id='baseDataUl' style='margin-left: 0px;'>" + ulHtml +
        "</ul></div></div></div></div>";
    $('#baseDataUl').append(ulHtml);
}

/*var imageMess="";
function setBaseData(data) {
	// 设置基本信息
	var ulHtml = "";
	for ( var i = 0; i < data.baseLst.length; i++) {
		var _class= "";
		if(i%2==0){
			ulHtml += '<li class="list-group-item" style=" background-color:#F5F5F5;white-space: nowrap;color : #f00;">'
				+ data.baseLst[i] + '</li>';
		}else{
			ulHtml += '<li class="list-group-item list-group-item-success" style="white-space: nowrap;">'
				+ data.baseLst[i] + '</li>';
		}


	}
	if(data.baseLst.length>0){
		imageMess=
			"<div style='position:absolute;z-index:100;width:25%; height: 100%;word-wrap:break-word;font-size:12px; margin-top: 20px; margin-left: 1px;'>" +
			"<div class='widget-box'>"+
			"<div class='widget-header'>"+
			"<h4 class='lighter smaller'>"+
			"<span class='glyphicon glyphicon-comment'>业务信息</span></h4></div>"+
			"<div class='widget-body'>" +
			"<div class='widget-main'>" +
			"<div class='dialogs' id='ywxxDiv' style='height: 100%;word-wrap:break-word; overflow: auto;'><ul class='list-group' id='baseDataUl' style='margin-left: 0px;'>"+ulHtml+
			"</ul></div></div></div></div>";
	}
	$('#baseDataUl').append(ulHtml);
}*/

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 设置影像类型
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function setBillType(data) {
    var bills = data.bills;
    var imgTypeHmtl = "";

    var imgTypeHmtl1 = "";
    for (var i = 0; i < bills.length; i++) {
        if ("0" == bills[i].isLostType) {
            imgTypeHmtl += '<option value="' + bills[i].code + '">' + bills[i].name + '</option>';
        }
    }
    for (var i = 0; i < bills.length; i++) {
        if ("1" == bills[i].isLostType) {
            imgTypeHmtl1 += '<input name="billType" style="width:20px;" type="checkbox" value="'
                + bills[i].code + '">&nbsp;&nbsp;<span>' + bills[i].name + '</span>&nbsp;&nbsp;';
            if ((i + 1) % 1 == 0) {
                imgTypeHmtl1 += '<br>';
            }
        }

    }
    $("#imgTypeModalImgTypeSelect").html(imgTypeHmtl);

    $("#imgTypeModalImgTypeSelect1").html(imgTypeHmtl1);


    $(".chosen-select").chosen({
        width: "300px",
        no_results_text: "没有找到"
    });
}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 设置树
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function imgTree(data) {
    $('#imgTree')
        .ace_tree(
            {
                dataSource: new DataSourceTree({
                    data: data
                }),
                loadingHTML: '<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
                'open-icon': 'icon-folder-open',
                'close-icon': 'icon-folder-close',
                'selectable': false,
                'selected-icon': null,
                'unselected-icon': null

            });
    imgTreeCss();
}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 点击树上的影像类型刷新影像文件列表
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function refushImgList() {
    var currentBillId = billId;
    if (id == batchId) {
        currentBillId = "";
    }
    $.post(WebRoot + "imImageAction!findImageByTree.do", {
        batchId: batchId,
        billId: currentBillId,
        systemFlag: systemflag,
        createUser: tellerno,
        tellerno: tellerno
    }, function (data) {
        $("#newfileul").html("");
        $("#imageul").html("");
        getimageAndFiles(data);

    }, "json");

}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
/*function queryBusiNo(){
	alert("httpFileTransPath"+httpFileTransPath);
	alert("httpfiletransIp"+httpfiletransIp);
	alert("httpfiletransPort"+httpfiletransPort);
	alert("tellerno"+tellerno);
	alert("tellerno"+tellerno);
	alert("batchId : "+batchId);
	alert("billId : "+billId);
	batchId = data.pojo.id;
	billId = data.billId;
	permissions =data.permissions;
	product  = data.product;
	// 设置影像类型
	setBaseData(data);
	setBillType(data);
	// 设置树
	imgTree(data.lstTree);
}*/

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 获取影像文件
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function getimageAndFiles(data) {
    var sltHtml = "";
    var lst = data.lst;
    // 保存批次信息参数

    // 填充影像集合
    imgList = lst;
    for (var i = 0; i < lst.length; i++) {
        // 传入影像存储地址和影像id
        sltHtml += getImageLiHTML(lst[i].imagePath, lst[i].id, lst[i].createUser, false, lst[i].pzNum, lst[i].imageName);
    }
    var h = $("#imageul").html();

    $("#imageul").html(h + sltHtml);
    viewerInit();
    /*
     * if (lst.length == 0) { $("#uploadimagediv").css({ "display" : "none" }); }
     */

    // 填充文档集合
    var lstfile = data.fileLst;
    var fHtml = "";
    for (var i = 0; i < lstfile.length; i++) {
        // 传入影像存储地址和影像id\
        if (lstfile[i].fileType.toLowerCase() == "mp4".toLowerCase()
            || lstfile[i].fileType.toLowerCase() == "ogg".toLowerCase()) {
            fHtml += getVideoLiHTML(lstfile[i].filePath, lstfile[i].id,
                "mp4.png", lstfile[i].fileName, createUser);
        } else if (lstfile[i].fileType.toLowerCase() == "mp3".toLowerCase()) {
            fHtml += getVideoLiHTML(lstfile[i].filePath, lstfile[i].id,
                "mp3.png", lstfile[i].fileName, createUser);
        } else if (lstfile[i].fileType.toLowerCase() == "pdf".toLowerCase()) {
            fHtml += getFileLiHTML(lstfile[i].filePath, lstfile[i].id,
                "pdfFileImage.png", lstfile[i].fileName, createUser);
        } else if (lstfile[i].fileType.toLowerCase() == "doc".toLowerCase()
            || lstfile[i].fileType.toLowerCase() == "docx".toLowerCase()) {
            fHtml += getFileLiHTML(lstfile[i].filePath, lstfile[i].id,
                "wordFileImage.png", lstfile[i].fileName, createUser);
        } else if (lstfile[i].fileType.toLowerCase() == "rar".toLowerCase()
            || lstfile[i].fileType.toLowerCase() == "zip".toLowerCase()) {
            fHtml += getFileLiHTML(lstfile[i].filePath, lstfile[i].id,
                "rarFileImage.jpg", lstfile[i].fileName, createUser);
        } else if (lstfile[i].fileType.toLowerCase() == "xls".toLowerCase()
            || lstfile[i].fileType.toLowerCase() == "xlsx".toLowerCase()) {
            fHtml += getFileLiHTML(lstfile[i].filePath, lstfile[i].id,
                "excelFileImage.jpg", lstfile[i].fileName, createUser);
        } else if (lstfile[i].fileType.toLowerCase() == "txt".toLowerCase()) {
            fHtml += getFileLiHTML(lstfile[i].filePath, lstfile[i].id,
                "txtFileImage.png", lstfile[i].fileName, createUser);
        } else {
            fHtml += getFileLiHTML(lstfile[i].filePath, lstfile[i].id,
                "otherFile.jpg", lstfile[i].fileName, createUser);
        }
    }
    var h = $("#newfileul").html();
    $("#newfileul").html(h + fHtml);
    initImageTool();

    /*
     * if (lstfile.length == 0) { hidetitle(); }
     */
}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 初始化影像控件
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function initImageTool() {
    var colorbox_params = {
        reposition: true,
        scalePhotos: true,
        scrolling: false,
        previous: '<i class="icon-arrow-left"></i>',
        next: '<i class="icon-arrow-right"></i>',
        close: '&times;',
        current: '{current} of {total}',
        maxWidth: '100%',
        maxHeight: '100%',
        onOpen: function () {
            document.body.style.overflow = 'hidden';
        },
        onClosed: function () {
            document.body.style.overflow = 'auto';
        },
        onComplete: function () {
            $.colorbox.resize();
        }
    };
    $('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
    $("#cboxLoadingGraphic").append("<i class='icon-spinner orange'></i>");// let's
    // add
    // a
    // custom
    // loading
    // icon

    $('.row-fluid').sortable({

        items: 'li',
        placeholder: "li",
        containment: 'parent'
    });
    $(".row-fluid").disableSelection();

}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 影像扫描
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
var ids = "";
var scanFileName = "";

//扫描影像
function showScanWindow() {
    if (window.showModalDialog) {
        ChildWidth = 1024;
        ChildHeight = 700;
        window
            .showModalDialog(
                "scan_trunk/scan.html?" + encodeURI(param) + "&billId="
                + billId + "&httpfiletransIp="
                + httpfiletransIp + "&httpfiletransPort="
                + httpfiletransPort + "&product=" + product,
                window,
                'dialogWidth:'
                + ChildWidth
                + 'px;dialogHeight:'
                + ChildHeight
                + 'px;center:yes;help:yes;resizable:no;status:no;scroll:no;');
        //window.open("scan_trunk/scan.html?"+param+"&httpfiletransIp="+httpfiletransIp+"&httpfiletransPort="+httpfiletransPort);
        if (imageSavePath != "") {
            var html = getFileLiHTML(imageSavePath, ids, "pdfFileImage.png", "scan.pdf", "", true);
            // 设置上传完成后回调
            //var sltHtml = getFileLiHTML("UIP/CCB/2018/9/17/yxcsfffffghr/scan.pdf", "4028538165e73c080165e741bd270000","wordFileImage.png","scan.pdf","",true);
            var h = $("#newfileul").html();
            $("#newfileul").html(h + html);
            $("#uploadfilediv").show();
            updateIndex();
            initImageTool();
            setImageNumToTree(billId, 1);
        }

    } else {
        alert("不能打开扫描页面,请使用IE6以上版本.");
    }
}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
function setImageNumToTree(billId, num) {
    try {
        var content = $("#" + batchId + billId).text();
        var pa = /.*\((.*)\)/;
        var total = parseInt(content.match(pa)[1]) + parseInt(num);
        var newContent = content.substring(0, content.lastIndexOf("("
            + content.match(pa)[1] + ")"))
            + "(" + total + ")"; // 获取到(num)前面的字符串。
        $("#" + batchId + billId).text(newContent);

        var name = $("#" + batchId + billId).attr("name");
        var fatherId = name.replace(batchId, '');
        if ('' != fatherId && 'uip_delete' != fatherId) {
            setImageNumToTree(fatherId, num)
        }
    } catch (e) {
        finishUpload();
    }

}

function setFatherNumToTree(billId, num) {
    if (billId == batchId) {
        return;
    }
    var content = obj.text();
    var pa = /.*\((.*)\)/;
    var total = parseInt(content.match(pa)[1]) + parseInt(num);
    var newContent = content.substring(0, content.lastIndexOf("("
        + content.match(pa)[1] + ")"))
        + "(" + total + ")"; // 获取到(num)前面的字符串。
    $("#" + batchId + billId).text(newContent);
    var fathreBillId = obj.parent().attr("billId");
    setFatherNumToTree(fathreBillId, num)
}

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 获取页面所有影像的id
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function getIds() {
    var lst = $('#imageul img');
    var s = "";
    for (var i = 0; i < lst.length; i++) {
        s += lst[i].id.replace("_img", "") + ",";
    }
    return s;
}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 删除影像
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function delImage(id, flag) {
    if (flag) {
        if (!confirm("确定删除该影像？")) {
            return;
        }
    }
    $.ajax({
        url: WebRoot + "imBatchAction!deleteImage.do",
        type: "post",
        data: {
            idsIndex: id,
            systemflag: systemflag,
            createUser: createUser,
            createUserName: createUserName
        },
        asycn: false,
        success: function (data) {
            var liId = '#' + id + '_imageLi';
            $(liId).remove();
            updateIndex();
            if (flag) {
                alert("删除成功");
                setImageNumToTree(billId, -1);
                setImageNumToTree(delImageTypeId, 1);
                viewerInit();
            }
        },
        dataType: "json"
    });
}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 替换当前影像
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function replaceImage(id) {
    if (window.showModalDialog) {
        ChildWidth = 1024;
        ChildHeight = 700;
        window.showModalDialog("scan_trunk_guokai/scan.html?" + param
            + "&httpfiletransIp=" + httpfiletransIp + "&httpfiletransPort="
            + httpfiletransPort, window, 'dialogWidth:' + ChildWidth
            + 'px;dialogHeight:' + ChildHeight
            + 'px;center:yes;help:yes;resizable:no;status:yes;scroll:no;');
        if (imageSavePath != "") {
            var html = "";
            var imagePaths = imageSavePath.split("@");
            for (var i = 0; i < imagePaths.length - 1; i++) {
                var imagePath = imagePaths[i];
                var idTemp = ids.split("@")[i];
                html += getImageLiHTML(imagePath, idTemp, createUser);
            }
            var liId = '#' + id + '_imageLi';
            $(liId).before(html);
            delImage(id, false);
            initImageTool();
            fileIdEcmPath = "";
        }

    } else {
        alert("不能打开扫描页面,请使用IE6以上版本.");
    }
}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 插入影像
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function insertImage(id) {
    if (window.showModalDialog) {
        ChildWidth = 1024;
        ChildHeight = 700;
        window.showModalDialog("scan_trunk_guokai/scan.html?" + param
            + "&httpfiletransIp=" + httpfiletransIp + "&httpfiletransPort="
            + httpfiletransPort, window, 'dialogWidth:' + ChildWidth
            + 'px;dialogHeight:' + ChildHeight
            + 'px;center:yes;help:yes;resizable:no;status:yes;scroll:no;');
        if (imageSavePath != "") {
            var html = "";
            var imagePaths = imageSavePath.split("@");
            for (var i = 0; i < imagePaths.length - 1; i++) {
                var imagePath = imagePaths[i];
                var idTemp = ids.split("@")[i];
                html += getImageLiHTML(imagePath, idTemp, createUser, true, 0);
            }
            var liId = '#' + id + '_imageLi';
            $(liId).before(html);
            updateIndex();
            initImageTool();
            imageSavePath = "";

        }

    } else {
        alert("不能打开扫描页面,请使用IE6以上版本.");
    }
}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 拼接图片带li元素
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function getImageLiHTML(imagePath, id, ib_tellerno, deleteTrue, pz_num, imageName) {

    return '<li id="' + id + '_imageLi">' + getImageHTML(imagePath, id, ib_tellerno, deleteTrue, pz_num, imageName)
        + '</li>';
}

// 拼接图片不带li元素
function getImageHTML(imagePath, id, ib_tellerno, deleteTrue, pz_num, imageName) {

    // httpFileTransPath =
    // http://127.0.0.1:8080/httpfiletrans/HttpFileTrans?method=download&BufferSize=65536&FlushSize=65536&IsCreateDir=TRUE&FilePath=
    // 原影像存储路径
    var imagePathSrc = httpFileTransPath + imagePath;
    // 缩略图存储路径
    // var s = imagePathSrc.lastIndexOf("/");
    var e = imagePathSrc.lastIndexOf(".");
    var simagePathSrc = imagePathSrc.substring(0, e) + "_small.jpg";
    var returnHtml = '<a href= "' + simagePathSrc + '" id="' + id
        + '" data-rel="colorbox" >' + '<img id="' + id
        + '_img" data-original="' + imagePathSrc
        + '"   style="width:150px;height:150px;" alt="150x150" src= "'
        + simagePathSrc + '"/></a><div class="tags">';
    returnHtml += '<span class="label-holder">	<span class="label label-info" title="' + imageName.substring(0, imageName.lastIndexOf('.')) + '" >'
        + imageName.substring(0, imageName.lastIndexOf('.')) + '</span></span>';
//		if(pz_num!=0&&pz_num!=undefined){
//		returnHtml+='<span class="label-holder"><span class="label label-danger">'+ pz_num + '</span></span>' ;
//		}


    returnHtml += ' </div>'
        + '<div class="tools tools-bottom">'
        + '<input type="checkbox" name="types" value="' + id
        + '"  style="float:left;"/>';
    if (!isOperCode) {
        if ((deleteTrue || deleteFlag == 2) && !isOperCode && delImageTypeId != billId) {
            returnHtml += '<a href="#" id="' + id + '_remove" title="删除图片"  onclick="delImage(\'' + id + '\',true)"> <i class="icon-remove red" ></i>' + '</a>'
        } else if (deleteFlag == 1 && ib_tellerno == tellerno && !isOperCode && delImageTypeId != billId) {
            returnHtml += '<a href="#" id="' + id + '_remove" title="删除图片"  onclick="delImage(\'' + id + '\',true)"> <i class="icon-remove red" ></i>' + '</a>'
        }
    }
    returnHtml += '</div>';

    return returnHtml;
}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 拼接文件带li元素
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function getFileLiHTML(filePath, id, type, filen, ib_tellerno, deleteTrue) {
    return '<li id="'
        + id
        + '_imageLi">'
        + getFileHTML(filePath, id, type, filen.substring(0, filen
            .lastIndexOf('.')), ib_tellerno, deleteTrue) + '</li>';
}

function getVideoLiHTML(filePath, id, type, filen, ib_tellerno, deleteTrue) {
    return '<li id="'
        + id
        + '_imageLi">'
        + getVideoHTML(filePath, id, type, filen.substring(0, filen
            .lastIndexOf('.')), ib_tellerno, deleteTrue) + '</li>';
}

function closeModal() {
    $("#video").removeAttr("src"); //移除视频链接
    $("#myModal").modal('hide');
}

function getVideoHTML(imagePath, id, type, filen, ib_tellerno, deleteTrue) {

    var simagePathSrc = "pages/imageTool/upload/css/img/" + type;
    var imagePathSrc = httpFileTransPath + imagePath;

    var returnHtml = '<a onclick="openVideoFile(\''
        + imagePathSrc
        + '\')" id="'
        + id
        + '">'
        + '<img id="'
        + id
        + '_img"  style="width:150px;height:150px;" alt="150x150" src= "'
        + simagePathSrc
        + '"/></a><div class="tags"><span class="label-holder">	<span class="label label-info">'
        + filen + '</span></span></div>'
        + '<div class="tools tools-bottom">'
        + '<i class="icon-pencil"></i>' + '</a>'
        + '<input type="checkbox" name="file" value="' + id
        + '"  style="float:left;"/>';


    if ((deleteTrue || deleteFlag == 2) && !isOperCode && delImageTypeId != billId) {
        returnHtml += '<a href="#" id="' + id
            + '_remove" title="删除文件"  onclick="delFile(\'' + id + '\')"></a>';
    } else if (deleteFlag == 1 && ib_tellerno == tellerno && !isOperCode && delImageTypeId != billId) {
        returnHtml += '<a href="#" id="' + id
            + '_remove" title="删除文件"  onclick="delFile(\'' + id + '\')"></a>';
    }
    returnHtml += '</div>';

    return returnHtml;
}

function openVideoFile(videoSrc) {
    $("#video").attr("src", videoSrc);
    $('#myModal').modal('show');
}

function getFileHTML(imagePath, id, type, filen, ib_tellerno, deleteTrue) {

    // httpFileTransPath =
    // http://127.0.0.1:8080/httpfiletrans/HttpFileTrans?method=download&BufferSize=65536&FlushSize=65536&IsCreateDir=TRUE&FilePath=
    // 原影像存储路径
    var imagePathSrc = httpFileTransPath + imagePath;
    // 缩略图存储路径
    var simagePathSrc = "js/" + type;


    var returnHtml = '<a onclick="openFile(\''
        + id
        + '\',\''
        + type
        + '\')" id="'
        + id
        + '">'
        + '<img id="'
        + id
        + '_img"  style="width:150px;height:150px;" alt="150x150" src= "'
        + simagePathSrc
        + '"/></a><div class="tags"><span class="label-holder">	<span class="label label-info">'
        + filen + '</span></span></div>'
        + '<div class="tools tools-bottom">'
        + '<i class="icon-pencil"></i>' + '</a>'
        + '<input type="checkbox" name="file" value="' + id
        + '"  style="float:left;"/>';

    if ((deleteTrue || deleteFlag == 2) && !isOperCode && delImageTypeId != billId) {
        returnHtml += '<a href="#" id="' + id
            + '_remove" title="删除文件"  onclick="delFile(\'' + id + '\')">'
            + '<i class="icon-remove red" ></i>' + '</a>';
    } else if (deleteFlag == 1 && ib_tellerno == tellerno && !isOperCode && delImageTypeId != billId) {
        returnHtml += '<a href="#" id="' + id
            + '_remove" title="删除文件"  onclick="delFile(\'' + id + '\')">'
            + '<i class="icon-remove red" ></i>' + '</a>';
    }
    returnHtml += '</div>';

    return returnHtml;
}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */


/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 保存影像順序
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function updateIndex() {

    $.ajax({

        url: WebRoot + "amsBatchAction!updateImageIndex.do?idsIndex="
            + getIds() + "&batchId=" + batchId + "&billId=" + billId + "&systemflag=" + systemflag,
        type: "post",
        data: {},
        asycn: false,
        success: function (data) {

        },
        dataType: "json"
    });
}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 調閱影像查看頁面
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
function openImgView(/*obj*/) {
    /*var sltId = obj.id;*/
    var method = getQueryString("method");
    // window.open(WebRoot+"ImageServlet?method=queryImage&currentdate="+currentdate+"&branchno="+branchno+"&comresult="+comresult+"&tellerno="+tellerno+"&tellername="+tellername+"&branchname="+branchname+"&systemflag="+systemflag+"&regbillglideno="+regbillglideno+"&annotationflag=no&remarkflag=no&remarkshowflag=hide&sltId="+eid+"&queryallflag="+queryallflag);
    if (method == "saveImage") {
        // 扫描单击查看影像
        //http://127.0.0.1:8080/uip_hhbx/ImageServlet?method=queryImage&key=1&operationcode=0100101000103301120160001094&
        //billId=8a82905456ba9a530156ba9b127e0000&billName=单据名称&
        //sltId=402881ec56f8f9b20156f8ff80640005&batchId=402881ec56f8f9b20156f8f9f9fc0000&regbillglideno=12
        var w = screen.availWidth;
        var h = screen.availHeight;
        sltId = "";

        if ($('input:checkbox[name=types]:checked').length > 0) {
            sltId = $('input:checkbox[name=types]:checked')[0].value;
        }
        var openUrl = encodeURI(WebRoot + "ImageServlet?method=queryImage&key=" + key + "&billId=" + billId + "&billName=" + billName + "&sltId=" + sltId + "&operationcode=" + operationcode + "&regbillglideno=" + regbillglideno + "&batchId=" + batchId + "&isOperCode=" + isOperCode + "&createUser=" + createUser + "&createUserName=" + createUserName);
        window.open(openUrl, '', 'width=' + w + ',height=' + h + ',top=0,left=0, scrollbars=yes,status=yes');
    } else if (method == "upload" || method == "query") {
        var w = screen.availWidth;
        var h = screen.availHeight;
        sltId = "";
        if ($('input:checkbox[name=types]:checked').length > 0) {
            sltId = $('input:checkbox[name=types]:checked')[0].value;
        }
        var openUrl = encodeURI(WebRoot + "ImageServlet?method=queryImage&key=VMSBUSI&billId=" + billId + "&sysid=" + systemflag + "&billName=" + billName + "&sltId=" + sltId + "&operationcode=" + operationcode + "&regbillglideno=" + regbillglideno + "&batchId=" + batchId + "&isOperCode=" + isOperCode + "&createUser=" + createUser + "&createUserName=" + createUserName);
        window.open(openUrl, '', 'width=' + w + ',height=' + h + ',top=0,left=0, scrollbars=yes,status=yes');
    }
}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

/*
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 影像分類
 * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 */
var spCodesTemp = "";
var spCodesFile = "";

function classifyImage() {
    // 双选框选择的影像id
    spCodesTemp = "";
    spCodesFile = "";
    $('input:checkbox[name=types]:checked').each(function (i) {
        if (0 == i) {
            spCodesTemp = $(this).val();
        } else {
            spCodesTemp += ("," + $(this).val());
        }
    });
    // 双选框选择的影像id
    $('input:checkbox[name=file]:checked').each(function (i) {
        if (0 == i) {
            spCodesFile = $(this).val();
        } else {
            spCodesFile += ("," + $(this).val());
        }
    });
    if (!spCodesTemp && !spCodesFile) {
        alert("请选择需要分类的影像");
        return false;
    }

    $("#classifyImage").modal('show');
    $("#classifyImage").children(".modal-dialog").children(".modal-content").css({
        top: '150px'
    });

}

// 影像缺失
function lostImage() {
    $("#lostImage").modal('show');
    $("#imgTypeModalImgTypeSelect1_chosen").css("width", "400px");
    $("#lostImage").children(".modal-dialog").children(".modal-content").css({
        top: '150px'
    });
    var url = WebRoot + "imImageLostLogAction!getImImageLostLogList.do?";

    var para = {batchId: batchId};
    $.post(url, para, function (data) {
        var imageCodeArray = new Array();
        if (data.defectType) {
            imageCodeArray = data.defectType.split(",");
        }

        $("input[name=billType]:checkbox").each(function () {
            var isChecked = false;
            for (var j = 0; j < imageCodeArray.length; j++) {
                if ($(this).val() == imageCodeArray[j]) {

                    $(this).prop('checked', true);
                    isChecked = true;
                    break;
                }
            }
            if (!isChecked) {
                $(this).prop('checked', false);
            }
        });


        var imageLostLog = "<thead><tr><th>操作人工号</th><th>操作时间</th><th>影像缺失记录</th></tr></thead><tbody>";
        var list = data.list;
        if (list.length > 0) {

            for (var i = 0; i < list.length; i++) {
                var operTime = (list[i].operTime + "").replace("T", " ");
                var losts = "无缺失,";
                if (list[i].lostBilltype) {
                    losts = list[i].lostBilltype;
                }
                if (losts.length > 1) {
                    losts = losts.substring(0, losts.length - 1);
                }

                imageLostLog += '<tr><td width="80px">' + list[i].operNo + '</td>' + '<td width="160px">' + operTime + '</td>'
                    + '<td width="300px">' + losts + '</td></tr>';
            }
            imageLostLog += "</tbody>";
            $("#imageLostLog").html(imageLostLog);
        }

    }, 'json');

}

/* ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */

// 获取URL参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

// 弹出影像分类界面

// 执行影像分类
function executeClassifyImage() {

    // 选中的业务
    var spSelectTemp = $("#imgTypeModalImgTypeSelect").val();
    if (!spSelectTemp) {
        alert("请选择影像类型");
        return false;
    }
    var url = WebRoot + "imImageAction!updateForType.do";
    var param = {
        id: spCodesTemp + "@@" + spCodesFile,
        imgType: spSelectTemp,
        systemFlag: systemflag,
        product: product
    };
    $.post(url, param, function (data) {
        if (!data.errorMessage) {
            if (data.billId !== billId) {
                var imgIds = spCodesTemp.split(",");
                var fileIds = spCodesFile.split(",");
                var imgNum = 0;
                if (spCodesTemp !== "" && spCodesTemp != null) {
                    imgNum += parseInt(imgIds.length);
                }
                if (spCodesFile !== "" && spCodesFile != null) {
                    imgNum += fileIds.length;
                }
                setImageNumToTree(data.billId, imgNum);
                var numTemp = 0 - parseInt(imgNum);
                setImageNumToTree(billId, numTemp);

                for (var i = 0; i < imgIds.length; i++) {
                    deleteImageUl(imgIds[i]);
                }
                for (var i = 0; i < fileIds.length; i++) {

                    deleteFileUl(fileIds[i]);
                }
            }
            alert("分类成功！");

        } else {
            alert("分类失败！");
        }

    }, 'json');

    $('#classifyImage').modal('hide');

}

// 执行影像缺失
function executeLostImage() {

    // 选中的业务
    var spSelectTemp = "";
    var billCodeAndName = "";

    $("input[name=billType]:checkbox").each(function () {
        if ($(this).is(":checked")) {
            spSelectTemp += $(this).val() + ",";
            billCodeAndName += $(this).next().text() + ",";
        }

    });


    if (spSelectTemp == null || spSelectTemp == "") {
        /*alert("请选择影像缺失类型");
        return false;*/
    }
    var defectType = spSelectTemp.toString();

    var url = WebRoot + "imBatchAction!saveImageLost.do";
    var param = {
        id: batchId,
        defectType: defectType,
        operNo: tellerno,
        lostImageType: billCodeAndName
    };
    $.post(url, param, function (data) {
        if (data.errorMessage) {
            alert(data.errorMessage);
        } else {
            alert("类型缺失登记成功！");
        }

    }, 'json');

    $('#lostImage').modal('hide');

}

/* 变更单据分类时候影像的地址 */
function imgSrcForImgType(imagePath) {

    // 原影像存储路径
    var imagePathSrc = httpFileTransPath + imagePath;

    $("#imgTypeModalImg").attr("src", imagePathSrc);

}

// 拼装影像列表
function showImgTable() {

    var imgTableHmtl = "";
    for (var i = 0; i < imgList.length; i++) {

        imgTableHmtl += '<tr onclick="changeImg(\'' + imgList[i].id + '\',\''
            + imgList[i].imagePath + '\')" id="tr' + imgList[i].id
            + '" imgId="' + imgList[i].id + '"><td> ' + (i + 1) + ' </td>'
            + '<td>' + imgList[i].imageName
            + '</td><td class="hidden-480">'
            + '<span class="label label-sm label-warning">'
            + imgList[i].imBillByBillId.name + '</span></td></tr>';
    }
    $("#imgTypeModalImgTableBody").html(imgTableHmtl);
}

function findAllImgType() {

}

/* 变更选择影像 */
function changeImg(index, path) {

    if ($("tr[select='select']")) {
        $("tr[select='select']").attr("class", "");
    }
    $("#tr" + index).attr("select", "select");
    $("#tr" + index).attr("class", "info");
    imgSrcForImgType(path);
}

function bindKeyCode() {

    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];

        var activeElement = document.activeElement;

        var autocomplete = $(activeElement).attr("autocomplete");

        if (e && e.keyCode == 27) { // 按 Esc

        }

        if (e && e.keyCode == 13) {// enter 键

            $("#imgTypeOKBtn").click();

        }
        // 当焦点在下拉框上时
        if (autocomplete) {
            return;
        }
        if (e && e.keyCode == 40) {// 下 键
            nextImg();
        }
        if (e && e.keyCode == 38) {// 上 键
            prevImg();
        }
    };
}

// 更改影像类型请求
function updateImgType(imgId, imgType) {
    var url = WebRoot + "imImageAction!updateForType.do";
    var param = {
        id: imgId,
        imgType: imgType,
        systemFlag: systemflag,
        product: product
    };
    $.post(url, param, function (data) {
        if (!data.errorMessage) {
            if (data.billId !== billId) {
                var imgIds = imgId.split(",");
                setImageNumToTree(data.billId, imgIds.length);
                var numTemp = 0 - parseInt(imgIds.length);
                setImageNumToTree(billId, numTemp);
                for (var i = 0; i < imgIds.length; i++) {
                    deleteImageUl(imgIds[i]);
                }
            }
            alert("分类成功！");

        } else {
            alert("分类失败！");
        }

    }, 'json');

}

function deleteImageUl(id) {
    var liId = '#' + id + '_imageLi';
    $(liId).remove();
    updateIndex();
}

// 更改文件类型请求
function updateFileType(fileId, fileType) {

    var url = WebRoot + "imFileAction!updateForType.do";
    var param = {
        id: fileId,
        fileBill: fileType,
        product: product
    };
    $.post(url, param, function (data) {
        if (!data.errorMessage) {
            if (data.billId !== billId) {
                var imgIds = imgId.split(",");
                setImageNumToTree(data.billId, imgIds.length);
                var numTemp = 0 - parseInt(imgIds.length);
                setImageNumToTree(billId, numTemp);
                for (var i = 0; i < imgIds.length; i++) {
                    deleteFileUl(imgIds[i]);
                }
            }
            alert("分类成功！");
        } else {
            alert("分类失败！");
        }

    }, 'json');

}

// 接触监听键盘绑定事件
function unBindKeyCode() {
    document.onkeydown = function (event) {
    };
}

// 影像分类确定按钮事件
$("#imgTypeOKBtn").click(function () {
    var imgType = $("#imgTypeModalImgTypeSelect").val();

    var imgId = $("tr[class='info']").attr("imgId");
    if (confirm("是否将该影像分配至【" + imgType + "】类型下?")) {

        // 请求后台更改单据类型
        updateImgType(imgId, imgType);

    }

});

$('#imgTypeModal').on('hidden.bs.modal', function (e) {
    unBindKeyCode();
});

function prevImg() {

    $("tr[class='info']").prev("tr").click();
    moveScroll(-17);
}

function nextImg() {
    $("tr[class='info']").next("tr").click();
    moveScroll(17);
}

// 偏移滚动条
function moveScroll(s) {

    $('#imgTypeModalImgTable').slimScroll({
        scrollBy: s
    });

}

// 二维码完成上传刷新左侧的树与图片
function finishUpload() {
    //  refushTree();
    //  refushImgList();
}

// 刷新树
function refushTree() {

    $.ajax({
        url: WebRoot + "amsBatchAction!queryScanMess.do",
        type: "post",
        data: {
            key: key
        },
        async: true,
        success: function (data) {
            imgAndFlieTree = data.lstTree;
            imgTree1(imgAndFlieTree);
        },
        dataType: "json"
    });
}

function imgTree1(data) {
    $("#imgTree").removeData("tree");
    $("#imgTree").unbind('click');
    $('#imgTree')
        .ace_tree(
            {
                dataSource: new DataSourceTree({
                    data: data
                }),
                multiSelect: true,
                loadingHTML: '<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
                'open-icon': 'icon-folder-open',
                'close-icon': 'icon-folder-close',
                'selectable': false,
                'selected-icon': null,
                'unselected-icon': null,
                cacheItems: false,
                folderSelect: false
            });
    /*
     * $("#imgTree").find(".tree-folder").each(function(){
     * $(this).css("display","block"); });
     */

}

// 将页面key拼接上 需要修改
$("#twoCode")
    .attr(
        "data-template",
        '<div class="popover" role="tooltip"><div class="arrow"></div><h1 class="popover-title" style="font-size:9px"></h1><div class="popover-content"></div><image src="imFileAction!twoCode.do?key='
        + key
        + '"></image><div align="center"><button style="text-align:center;" id="finishUpload" onclick="finishUpload()">上传完成</button></div></div>');

// 初始化二维码弹出框控件
// $('[data-toggle="popover"]').popover();

//还原左侧树的滚动条位置
function resetLeftTreeScroll() {
    $("#imgTreeScroll").mCustomScrollbar("scrollTo", "left");
}

//图片查看器 初始化
function viewerInit() {
    $('#uploadimagediv').viewer('destroy');
    $('#uploadimagediv').viewer({
        url: 'data-original',
        title: false
    });
}
