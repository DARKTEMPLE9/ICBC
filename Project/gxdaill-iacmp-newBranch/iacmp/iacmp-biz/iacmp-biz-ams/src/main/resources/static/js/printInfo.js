/*--------------------------------------------------|
页面打印js
2018-03-05
rjz第一版
|--------------------------------------------------*/
var initFieldHeight;
var htm;


$(function () {
    var excelInitHtml = "";
    // var paramsNew =$.urlParam("id");
    var paramsNew = '30';

    if (paramsNew == '20') {


    } else {

        excelInitHtml = "<button type='button' name=\"export\" class=\"excelExport arcRecPrint\" id=\"exportExcelID\" value='导出' title=''><span class='glyphicon glyphicon-export' ></span>&nbsp;导出excel</button>";
        $("#excelExportAndPrint").append(excelInitHtml);
        $(".excelExport").addClass("btn btn-primary");
        $(".excelExport").mouseover(function () {
            $(".excelExport").css("background-color", "#286090");
        });
        $(".excelExport").mouseleave(function () {
            $(".excelExport").css("background-color", "#337AB7");
        });

        //export excel
        $(document).ready(function () {
            $(".excelExport").click(function () {
                checkVals = $("input:checkbox:checked").map(function (index, elem) {
                    return $(elem).val();
                }).get().join(',');
                if (checkVals == '') {
                    alert("请选择列表数据");

                } else {
                    produceExcel();


                }


            });

        });
    }


    var initHtml = "<span class='glyphicon glyphicon-print' ></span>&nbsp;打印";
    $(".printInfoButton").append(initHtml);
    $(".printInfoButton").addClass("btn btn-primary");
    $(".printInfoButton").mouseover(function () {
        $(".printInfoButton").css("background-color", "#286090");
    });

    $(".printInfoButton").mouseleave(function () {
        $(".printInfoButton").css("background-color", "#337AB7");
    });

    var pathName = window.document.location.pathname;
    //pathName.subString(0,);
    var index = pathName.indexOf("/", 1);
    var G_WebRoot = pathName.substring(0, index);

    var btnClass = $(".printInfoButton").attr("class");

    $(".printInfoButton").click(function () {
        var objs;
        var ids;
        if (btnClass.indexOf("arcRecPrint") > 0) {
            objs = $("input[name='selectedName']:checkbox:checked");
            $.each(objs, function (i, obj) {
                if (i === 0) {
                    ids = $(obj).val();
                } else {
                    ids += '/' + $(obj).val();
                }
            });

            // 以前的代码，先写定值，后期修改
            // window.open(G_WebRoot+"/pages/printLook/printLook.html?type=arcRecPrint&id="+ids);
            var G_WebRoot = '/'
                + (window.location.pathname.indexOf('/') == 0 ? window.location.pathname.split('/')[1]
                    : window.location.pathname.split('/')[0]) + '/';

            window.open("/amsArcReportcontroller/amsProcessHistory?type=arcRecPrint&id=" + ids);


        } else if (btnClass.indexOf("arcDesPrint") > 0) {
            objs = $("input[name='selectedName']:checkbox:checked");
            $.each(objs, function (i, obj) {
                if (i === 0) {
                    ids = $(obj).val();
                } else {
                    ids += '/' + $(obj).val();
                }
                console.log(ids);
            });
            /*	jQuery("#myPrint").click(function(){})*/

            checkVals = $("input:checkbox:checked").map(function (index, elem) {
                return $(elem).val();
            }).get().join(',');
            if (checkVals == '') {
                alert("请选择列表数据");

            } else {
                window.open(G_WebRoot + "/pages/printLook/printLook.html?type=arcDesPrint&id=" + ids);
            }

            //window.open(G_WebRoot+"/pages/printLook/printLook.html?type=arcDesPrint&id="+ids);
        } else if (btnClass.indexOf("arcHandlePrint") > 0) {
            objs = $("input[name='selectedName']:checkbox:checked");
            $.each(objs, function (i, obj) {
                if (i === 0) {
                    ids = $(obj).val();
                } else {
                    ids += '/' + $(obj).val();
                }
                console.log(ids);
            });

            window.open(G_WebRoot + "/pages/printLook/printLook.html?type=arcHandlePrint&id=" + ids);
            //objs = $("input:radio:checked");
            //window.open(G_WebRoot+"/pages/printLook/printLook.html?type=arcHandlePrint&id="+$(objs).val());
        } else if (btnClass.indexOf("boxInfoPrint") > 0) {
            objs = $("input[name='selectedName']:checkbox:checked");
            $.each(objs, function (i, obj) {
                if (i === 0) {
                    ids = $(obj).val();
                } else {
                    ids += '/' + $(obj).val();
                }
                console.log(ids);
            });

            window.open(G_WebRoot + "/pages/printLook/printLook.html?type=boxInfoPrint&id=" + ids);
            //objs = $("input:radio:checked");
            //window.open(G_WebRoot+"/pages/printLook/printLook.html?type=arcHandlePrint&id="+$(objs).val());
        }
        ;

    });

});


function calDataAjaxOne(formId, checkValid) {
    var data = {};
    var ips = $(".submitInput", formId ? ("#" + formId) : null);
    if (ips) {
        for (var i = 0; i < ips.length; i++) {
            var key = ips[i].id || ips[i].name;
            var es = NorthkingCRUD.getCheckedRadioValue(key, formId);
            var e;
            if (es && es.length) {

                e = es[0];
                data[key] = e.value;
                if (e.id) {
                    e = $("#" + e.id);
                }
            } else {
                if (!$("input[name='" + key + "'][type='checkbox']").length) {
                    e = $("#" + key);
                    data[key] = e.val();
                    if (!data[key]) {
                        data[key] = ips[i].value;
                    }
                }

            }
            if (checkValid) {
                if (e.attr("notNull") == "true") {
                    if (isNull(data[key])) {
                        if (!e.parent().parent().hasClass("error")) {
                            e
                                .focus()
                                .after(
                                    "<span style='color:#F00' id='err_" + key
                                    + "'>不能为空</span>")
                                .parent().parent().addClass("error");
                        } else {
                            e.focus();
                        }
                        ;
                        return false;
                    }
                }
            }
            if (e.parent().parent().hasClass("error")) {
                e.focus().parent().parent().removeClass("error");
                $("#err_" + key).remove();
            }
        }
    }
    ;
    return data;
};

function produceExcel() {

    checkValsOne = $("input:checkbox:checked").map(function (index, elem) {
        return $(elem).val();
    }).get().join(',');


    NorthkingCRUD.ajax({
        type: "POST",
        url: "amsArcRecExportExcelAction!exportExcelOne.do?checkVals=" + checkValsOne,
        beforeSend: function () {
        },
        error: function (jqXHR, textStatus, errorThrown) {
            NorthkingCRUD
                .commonErrorHandler(jqXHR, textStatus, errorThrown);
        },
        success: function (json) {
            window.open("amsArcRecExportExcelAction!exportExcelOne.do?checkVals=" + checkValsOne);
        }
    });
}




