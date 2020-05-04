var count = 1;// 行数ID后缀 ,序号
var delid = "";// 删除的ID
var addid = "";// 添加ID的前缀
var regArray = {};

function newAddOnload() {
    $("#addReg").find("tbody").empty();
    var html = trHtml;
    $("#addReg").find("tbody").append(html);
}

/*选择人员 */
function selectUser(id) {
    var options = {
        title: '选择用户',
        /* width: width,
         height: height,*/
        id: id,
        url: ctx + "system/user/selectUser?id=" + id,
        btn: ['确定', '关闭'],
        shadeClose: true,
        yes: function (index, layero) {
            var body = layer.getChildFrame('body', index);
            $('#' + id).val(body.find('#thisRowName').val());
            layer.close(index);
        },
        cancel: function (index) {
            return true;
        }
    };
    $.modal.openOptions(options);
}


// var a= str.split(",");
function addTr2() {

    var row = $("#addReg").find("tr").length;
    trHtml = '<tr ><td><div style="width:100%;padding:5px 5px 5px 5px;"><input type="checkbox" id ="regcheck'
        + count
        + '" name="regcheck" value='
        + count
        + '/></div></td><td><div style="width:100%;padding:5px 5px 5px 5px;">'
        + '<font style="font-family:cursive">' + count + '</font>'
        + '</div></td><td>'
        + '<div  style="width:100%;padding:5px 5px 5px 5px;"><input class="submitInput form-control "  notNull="true" type="text" dataType="text"style="width:100%;"'
        + ' id="name'
        + count
        + '" name="name" value="" >'
        + '</div></td><td>'
        + '<div style="width:100%;padding:5px 5px 5px 5px;"><input class="submitInput form-control "'
        + '	notNull="true" type="text" dataType="number"'
        // + '	onkeyup="(this.value=function(){this.value=this.value.replace(/[^0-9-]+/,\'\');}).call(this)"'
        + ' oninput="value=value.replace(/[^\\d]/g,\'\')"'
        + ' onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,\'\')}else{this.value=this.value.replace(/\\D/g,\'\');if(this.value.substring(0,1)==\'0\'){this.value=this.value.replace(this.value,\'\')}}"'
        + ' onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,\'\')}else{this.value=this.value.replace(/\\D/g,\'\')}"'
        + '	'
        + '	'
        + '	id="regArcNum'
        + count
        + '" name="regArcNum" value=""  maxlength="10">'
        + '</div></td><td>'
        + '<div style="width:100%;padding:5px 5px 5px 5px;"><input class="submitInput form-control "'
        + '	notNull="true" type="text" name="recPageNum"'
        // + '	onkeyup="(this.value=function(){this.value=this.value.replace(/[^0-9-]+/,\'\');}).call(this)"'
        + ' oninput="value=value.replace(/[^\\d]/g,\'\')"'
        + ' onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,\'\')}else{this.value=this.value.replace(/\\D/g,\'\');if(this.value.substring(0,1)==\'0\'){this.value=this.value.replace(this.value,\'\')}}"'
        + ' onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,\'\')}else{this.value=this.value.replace(/\\D/g,\'\')}"'
        + '	'
        + '	'
        + 'id="recPageNum'
        + count
        + '" value="" maxlength="10">'
        + '</div></td><td>'
        + '<div  style="width:100%;padding:5px 5px 5px 5px;"><input class="submitInput form-control "  notNull="true" type="text" dataType="text"style="width:100%;"'
        + ' id="arcCode'
        + count
        + '" name="arcCode" value="" maxlength="15"'
        + 'oninput="this.value=this.value.replace(/[^\a-\z\A-\Z0-9\-\_]/g,\'\')" >'
        + '</div></td><td>'
        + '<div  style="width:100%;padding:5px 5px 5px 5px;"><input class="submitInput form-control "  notNull="true" type="text" dataType="text"style="width:100%;"'
        + ' id="respOpName'
        + count
        + '" name="respOpName" AUTOCOMPLETE="off"  readonly onclick="respOpName.blur;selectUser(\'respOpName' + count + ' \');return false" value="" >'
        + '</div></td><td>'
        + '<div  style="width:100%;padding:5px 5px 5px 5px;">' +
        '<input class="submitInput form-control "  notNull="true" type="text" dataType="text"style="width:100%;"'
        + ' id="arcCreTime'
        + count
        + '" name="arcCreTime" value="" AUTOCOMPLETE="off" placeholder="yyyy-MM-dd" >'
        + '</div></td><td>'

        + '<div  style="width:100%;padding:5px 5px 5px 5px;">'
        + '<i id="hasMoveBankI' + count + '" style="padding: 6px 13px" class="fa fa-toggle-on text-info fa-2x" onclick="tag(this)"></i>'
        + '<input name="hasMoveBank" id="hasMoveBank'
        + count
        + '" value="1" type="hidden">'
        + '</div></td><td>'

        + '<div  style="width:100%;padding:5px 5px 5px 5px;">'
        + '	<input class="submitInput form-control "'
        + '	notNull="true" type="text" name="regRemark"'
        + '	maxlength="11" id="regRemark'
        + count
        + '" value=""  >'
        + '</div></td></tr>';

    addTr(row, trHtml);
// 文件编号 只能输入 数字和英文
//var input = document.querySelector('input[name="forwardNo"]');
    $('input[name="arcCode"]').oninput = function updateValue(e) {
        var mvalue = e.target.value;
        console.log(mvalue)
        // ((?=[\x21-\x7e]+)[^A-Za-z0-9])x21-\x7e]+)[^A-Za-z0-9])
        $('input[name="arcCode"]').val(mvalue.replace(/^[^\a-zA-Z][^\a-\z\A-\Z0-9\-\_]/g, ''));
    }
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#arcCreTime' + count,
            type: 'date',
            theme: 'molv',
            trigger: 'click',
        });


    });
    addid += count + ",";

    count++;
}

function initAddTr() {
    var i = 0;
    for (; i < 1; i++)
        addTr2();
}

var addFlag = false;

function tag(e) {
    var thisNum = e.id.slice(12)
    var iName = "hasMoveBankI" + thisNum;
    var inputName = "hasMoveBank" + thisNum;
    if (addFlag == false) {
        $("#" + iName).removeClass("fa-toggle-on");
        $("#" + iName).addClass("fa-toggle-off");
        $("#" + inputName).val("0")
    } else {
        $("#" + iName).removeClass("fa-toggle-off");
        $("#" + iName).addClass("fa-toggle-on");
        $("#" + inputName).val("1")
    }
    addFlag = !addFlag
}

function addTr(row, trHtml) {
    // 获取table最后一行 $("#tab tr:last")
    // 获取table第一行 $("#tab tr").eq(0)
    // 获取table倒数第二行 $("#tab tr").eq(-2)
    row = row - 1;
    var $tr = $("#addReg tr").eq(row);
    if ($tr.size() == 0) {
        alert("指定的table id或行数不存在！");
        return;
    }

    $tr.after(trHtml);

    //var table=document.getElementById("resultTable");
    var table = document.getElementById("addReg");
    colResizables(table);//调用拖拽表格方法

    // var table=document.getElementsByTagName("table")[0];
    makeSortable(table, 2, 1);//调用表格列排序

}

// 全选功能
// name是你点击出发chexbox的name itemname 是你要操作cheeckbox的name
function qcheckbox(name, itemname) {
    if ($("input[name='" + name + "']:checkbox").attr("checked"))
        $("input[name='" + itemname + "']:checkbox").attr("checked", true);
    else
        $("input[name='" + itemname + "']:checkbox").attr("checked", false);
}

// chname你选择的checbox的name
function delTr() {
    // var chobj= $("input[name='"+chname+"']:checkbox");
    if ($("input:checkbox:checked").val() == null) {
        alert('请选中某条记录！');
        return;
    }
    if (confirm("是否删除选中项？")) {
        alert(1)
        var chobj = $("input[name=regcheck]:checkbox");
        chobj.each(function () {

            if ($(this).attr("checked")) {
                delid += $(this).val() + ",";
                $(this).parent().parent().parent().remove();
                count--;
            }

        });
        var num = 0;
        var editRow = 0;
        $("#addReg").find('tr').each(function () {
            if (editRow != 0) {
                num++;
                $(this).find("td:eq(1)").html("<div style=\"width:100%;padding:5px 5px 5px 5px;\"><font style=\"font-family:cursive\">" + num + "</font></div>");

            }
            editRow++;

        });
        // count--;

    }
}


// NorthkingCRUD.Add.toSave = function() {
// 	if ($("input:checkbox:checked").val() == null) {
// 		alert('请选中某条记录！');
// 		return;
// 	}
// 	var regList = new Array();
// 	var retFlag =true ;
// 	var chobj = $("input[name=regcheck]:checkbox");
// 	var chobj1 = $("input[name=regArcNum]");
// 	var chobj2 = $("input[name=recPageNum]");
// 	var chobj3 = $("input[name=regRemark]");
// 	var chobj4 = $("input[name=name]");
// 	NorthkingCRUD.Manage.bnSetDisabled('mySubmitForm','disabled');
// 	// 数组 List<Object[]>
// 	var i=0,j=0;
// 	var a="",b="",c="";
// 	chobj.each(function() {
// 		if($(this).attr('checked')){
// 				var regCode = {};
// 				if($(chobj1[i]).val()==null||$(chobj1[i]).val()==""){
// 					alert("有必填项未输入，请将信息补充完整");
// 					retFlag = false;
// 					return retFlag;
// 				}
// 				if($(chobj2[i]).val()==null||$(chobj2[i]).val()==""){
// 					alert("有必填项未输入，请将信息补充完整");
// 					retFlag = false;
// 					return retFlag;
// 				}
// 				if($(chobj4[i]).val()==null||$(chobj4[i]).val()==""){
// 					alert("有必填项未输入，请将信息补充完整");
// 					retFlag = false;
// 					return retFlag;
// 				}
// 				if(i == 0){
// 					a = $(chobj1[i]).val();b = $(chobj2[i]).val(); c = $(chobj4[i]).val();
// 					regCode["regArcNum"] = $(chobj1[i]).val();
// 					regCode["recPageNum"] = $(chobj2[i]).val();
// 					regCode["regRemark"] = $(chobj3[i]).val();
// 					regCode["name"] = $(chobj4[i]).val();
// 					regList.push(regCode);
// 					i++;
// 				}else{
// 					if((a==$(chobj1[i]).val())&&(b==$(chobj2[i]).val())&&(c==$(chobj4[i]).val())){
// 						if(confirm("数据重复，是否继续操作?")){
// 							regCode["regArcNum"] = $(chobj1[i]).val();
// 							regCode["recPageNum"] = $(chobj2[i]).val();
// 							regCode["regRemark"] = $(chobj3[i]).val();
// 							regCode["name"] = $(chobj4[i]).val();
// 							regList.push(regCode);
// 						}else{
// 							regCode["regArcNum"] = $(chobj1[i]).val();
// 							regCode["recPageNum"] = $(chobj2[i]).val();
// 							regCode["regRemark"] = $(chobj3[i]).val();
// 							regCode["name"] = $(chobj4[i]).val();
// 							regArray[j] = regCode;
// 							j++;
// 						}
// 					}else{
// 						regCode["regArcNum"] = $(chobj1[i]).val();
// 						regCode["recPageNum"] = $(chobj2[i]).val();
// 						regCode["regRemark"] = $(chobj3[i]).val();
// 						regCode["name"] = $(chobj4[i]).val();
// 						regList.push(regCode);
// 					}
// 					i++;
// 				}
// 		}else{
// 			var regCode = {};
// 			regCode["regArcNum"] = $(chobj1[i]).val();
// 			regCode["recPageNum"] = $(chobj2[i]).val();
// 			regCode["regRemark"] = $(chobj3[i]).val();
// 			regCode["name"] = $(chobj4[i]).val();
// 			regArray[j] = regCode;
// 			i++;
// 			j++;
// 		}
// 	});
// 	/*$.each(chobj1, function(i, list) {
// 		if($(this).val()!=null && $(this).val()!=""){
// 			var regCode = {};
// 			if($(chobj1[i]).val()==null||$(chobj1[i]).val()==""){
// 				alert("有必填项未输入，请将信息补充完整");
// 				retFlag = false;
// 				return retFlag;
// 			}
// 			if($(chobj2[i]).val()==null||$(chobj2[i]).val()==""){
// 				alert("有必填项未输入，请将信息补充完整");
// 				retFlag = false;
// 				return retFlag;
// 			}
// 			if($(chobj4[i]).val()==null||$(chobj4[i]).val()==""){
// 				alert("有必填项未输入，请将信息补充完整");
// 				retFlag = false;
// 				return retFlag;
// 			}
// 			regCode["regArcNum"] = $(chobj1[i]).val();
// 			regCode["recPageNum"] = $(chobj2[i]).val();
// 			regCode["regRemark"] = $(chobj3[i]).val();
// 			regCode["name"] = $(chobj4[i]).val();
// 			regList.push(regCode);
// 		}
// 	});*/
// 	if(!retFlag){
// 		return;
// 	}
// 	var addUrl = "amsArcReg!toSave.do";
// 	$.ajax({
// 		type : "POST",
// 		url : G_WebRoot + addUrl,
// 		dataType : "json",
// 		traditional :true,
// 		data : {
// 			regList : JSON.stringify(regList),
// 		},
// 		error : function(jqXHR, textStatus, errorThrown) {
// 			NorthkingCRUD.commonErrorHandler(jqXHR, textStatus, errorThrown);
// 			NorthkingCRUD.Manage.bnSetDisabled('mySubmitForm', '');
// 		},
// 		success : function(data) {
// 			alert('保存成功');
// 			NorthkingCRUD.Manage.bnSetDisabled('mySubmitForm', '');
// 			var j=1;
// 			$("input[name=regcheck]:checkbox").attr("checked",false);
// 			$("input[name=regArcNum]").val("");
// 			$("input[name=recPageNum]").val("");
// 			$("input[name=regRemark]").val("");
// 			$("input[name=name]").val("");
// 			Object.keys(regArray).forEach(function(key){
// 				$("#name"+j).val(regArray[key]["name"]);
// 				$("#regArcNum"+j).val(regArray[key]["regArcNum"]);
// 				$("#recPageNum"+j).val(regArray[key]["recPageNum"]);
// 				$("#regRemark"+j).val(regArray[key]["regRemark"]);
// 				j++;
// 			});
// 		}
// 	});
// };
