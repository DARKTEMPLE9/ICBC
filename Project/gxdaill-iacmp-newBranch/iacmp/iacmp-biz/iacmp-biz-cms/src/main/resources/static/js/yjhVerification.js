//验证初始化
var theform = "";
var theform2 = "";
var theform3 = "";
var theform4 = "";
var theform5 = "";

function initializeValidator(formname) {
    theform = formname;
    changeName(theform);
}

function initializeValidator2(formname) {
    theform2 = formname;
    changeName(theform2);
}

function initializeValidator3(formname) {
    theform3 = formname;
    changeName(theform3);
}

function initializeValidator4(formname) {
    theform4 = formname;
    changeName(theform4);
}

function initializeValidator5(formname) {
    theform5 = formname;
    changeName(theform5);
}

//季报 日期转换汉字方法 
function changeSendDate1(myDate) {
    var m = myDate.substring(4, 6);
    var t = "";
    switch (m) {
        case "03":
            t = "第一季度";
            break;
        case "06":
            t = "第二季度";
            break;
        case "09":
            t = "第三季度";
            break;
        case "12":
            t = "第四季度";
            break;
    }
    return myDate.substring(0, 4) + t;
}

function quarterly(myDate) {
    var y = myDate.substring(0, 4);
    var m = myDate.substring(4, 6);
    var t = "";
    switch (m) {
        case "03":
            t = "1";
            break;
        case "06":
            t = "2";
            break;
        case "09":
            t = "3";
            break;
        case "12":
            t = "4";
            break;
    }
    return y + t;
}

function quarterlyFinal(myDate) {
    var m = myDate.substring(4, 6);
    var y = myDate.substring(0, 4);
    var t = "";
    switch (m) {
        case "01":
            t = "1";
            break;
        case "02":
            t = "1";
            break;
        case "03":
            t = "1";
            break;
        case "04":
            t = "2";
            break;
        case "05":
            t = "2";
            break;
        case "06":
            t = "2";
            break;
        case "07":
            t = "3";
            break;
        case "08":
            t = "3";
            break;
        case "09":
            t = "3";
            break;
        case "10":
            t = "4";
            break;
        case "11":
            t = "4";
            break;
        case "12":
            t = "4";
            break;
    }
    return y + t;

}

//基础设施调研表 日期转换汉字方法 tq1 tq2 tq3 tq4 tm1
function yjhChangeSendDate(myDate) {
    var y = myDate.substring(0, 4);
    var m = myDate.substring(4, 6);

    var t = "";
    switch (m) {
        case "01":
            t = "第一季度";
            break;
        case "02":
            t = "第一季度";
            break;
        case "03":
            t = "第一季度";
            break;
        case "04":
            t = "第二季度";
            break;
        case "05":
            t = "第二季度";
            break;
        case "06":
            t = "第二季度";
            break;
        case "07":
            t = "第三季度";
            break;
        case "08":
            t = "第三季度";
            break;
        case "09":
            t = "第三季度";
            break;
        case "10":
            t = "第四季度";
            break;
        case "11":
            t = "第四季度";
            break;
        case "12":
            t = "第四季度";
            break;
    }
    return y + t;

}


function changeName(theform) {
    $('.guanxi_Num').each(function () {
        var name = $(this).attr("name");
        $(theform).bootstrapValidator('addField', name, _betweenCheck);
    });
    $('.integer_Num').each(function () {
        var name = $(this).attr("name");
        $(theform).bootstrapValidator('addField', name, _numberCheck);
    });
    $('.integer_Num_Null').each(function () {
        var name = $(this).attr("name");
        $(theform).bootstrapValidator('addField', name, _numberCheckNull);
    });
    //xiaoshu
    $('.decimal_Num').each(function () {
        var name = $(this).attr("name");
        $(theform).bootstrapValidator('addField', name, _fractionalCheck);
    });
    $('.decimal_Num3').each(function () {
        var name = $(this).attr("name");
        $(theform).bootstrapValidator('addField', name, _fractionalCheck3);
    });
    $('.time_Num').each(function () {
        var name = $(this).attr("name");
        $(theform).bootstrapValidator('addField', name, _timeCheck);
    });

    $('.time_Num_Null').each(function () {
        var name = $(this).attr("name");
        $(theform).bootstrapValidator('addField', name, _timeCheckNull);
    });

    $('.phone_num').each(function () {
        var name = $(this).attr("name");
        $(theform).bootstrapValidator('addField', name, _phoneNumber);
    });
    //!null
    $('.nonEmpty').each(function () {
        var name = $(this).attr("name");
        $(theform).bootstrapValidator('addField', name, _nonEmpty);
    });


    $('.checkboxVer1').each(function () {
        var name = $(this).attr("name");
        $(theform).bootstrapValidator('addField', name, _checkboxVer);
    });

    //负小数
    $('.negative_num').each(function () {
        var name = $(this).attr("name");
        $(theform).bootstrapValidator('addField', name, _negativedecimalCheck);
    });

    $('.remark_check').each(function () {
        var name = $(this).attr("name");
        $(theform).bootstrapValidator('addField', name, _remarkCheck);
    });

}

//checkbox
var _checkboxVer = {
    message: '',
    validators: {
        choice: {
            min: 1,
            message: '不能为空'
        }
    }
};

//非空
var _nonEmpty = {
    message: '',
    validators: {
        notEmpty: {
            message: '不能为空'
        }
    }
};
// 整数
var _numberCheck = {
    message: '',
    validators: {
        stringLength: {
            max: 11,
            message: '不可超出11位长度'
        },
        notEmpty: {
            message: '不能为空'
        },
        regexp: {
            regexp: /^[0-9]*$/,
            message: '请填写整数'
        }
    }
};

//整数
var _numberCheckNull = {
    message: '',
    validators: {
        regexp: {
            regexp: /^[0-9]*$/,
            message: '请填写整数'
        }
    }
};
// 小数0~100之间
var _betweenCheck = {
    message: '',
    validators: {
        notEmpty: {
            message: '不能为空'
        },

        between: {
            min: 0,
            max: 100,
            message: '必须小于等于100'
        },
        regexp: {
            regexp: /^\d+(\.\d+)?$/,
            message: '请填写小数'
        }
    }
};

// 小数
var _fractionalCheck = {
    message: '',
    validators: {
        notEmpty: {
            message: '不能为空'
        },
        regexp: {
            regexp: /^\d+(\.\d+)?$/,
            message: '请填写小数'
        }
    }

};

// 小数后三位
var _fractionalCheck3 = {
    message: '',
    validators: {
        notEmpty: {
            message: '不能为空'
        },
        regexp: {
            regexp: /^\d+(\.\d{4})?$/,
            message: '请填写小数(小数点后最多有4位有效数字)'
        }
    }

};

//负小数
var _negativedecimalCheck = {
    message: '',
    validators: {
        notEmpty: {
            message: '不能为空'
        },
        regexp: {
            regexp: /^(\-|\+)?\d+(\.\d+)?$/,
            message: '请填写小数（可为负）'
        }
    }

};

// 备注
var _remarkCheck = {
    message: '',
    validators: {
        stringLength: {
            max: 100,
            message: '备注不能超过100个汉字'
        }
    }
};
// 单选
var _choiceCheck = {
    message: '',
    validators: {
        notEmpty: {
            message: '不能为空'
        }
    }
};
// 文字
var _choiceCheck = {
    message: '',
    validators: {
        notEmpty: {
            message: '不能为空'
        },
        stringLength: {
            max: 100,
            message: '备注不能超过100个汉字'
        }
    }
};

var _phoneNumber = {
    message: '',
    validators: {
        notEmpty: {
            message: '不能为空'
        },

        regexp: {
            regexp: /^1[34578]\d{9}$/,
            message: '请填写正确的电话格式如（13800138000）'
        },

    }
};

var _timeCheck = {
    message: '',
    validators: {
        notEmpty: {
            message: '不能为空'
        },
        regexp: {
            /*regexp:/^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/,		 */
            regexp: /^((((1[6-9]|[2-9]\d)\d{2})(\-)(0?[13578]|1[02])(\-)(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})(\-)(0?[13456789]|1[012])(\-)(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})(\-)0?2(\-)(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))(\-)0?2(\-)29(\-)))$/,
            message: '请正确选择日期'
        }
    }
};


var _timeCheckNull = {
    message: '',
    validators: {
        regexp: {
            //regexp:/^((((1[6-9]|[2-9]\d)\d{2})(\-|\/|\.)(0?[13578]|1[02])(\-|\/|\.)(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})(\-|\/|\.)(0?[13456789]|1[012])(\-|\/|\.)(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})(\-|\/|\.)0?2(\-|\/|\.)(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))(\-|\/|\.)0?2(\-|\/|\.)29(\-|\/|\.)))$/,
            regexp: /^((((1[6-9]|[2-9]\d)\d{2})(\-)(0?[13578]|1[02])(\-)(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})(\-)(0?[13456789]|1[012])(\-)(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})(\-)0?2(\-)(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))(\-)0?2(\-)29(\-)))$/,

            message: '请正确输入本季度日期,例如:1970-01-01'
        }
    }
};


function saveValidator() {
    $(theform).data('bootstrapValidator').validate();
    return $(theform).data('bootstrapValidator').isValid();
};

function saveValidator2() {
    $(theform2).data('bootstrapValidator').validate();
    return $(theform2).data('bootstrapValidator').isValid();
};

function saveValidator3() {
    $(theform3).data('bootstrapValidator').validate();
    return $(theform3).data('bootstrapValidator').isValid();
};

function saveValidator4() {
    $(theform4).data('bootstrapValidator').validate();
    return $(theform4).data('bootstrapValidator').isValid();
};

function saveValidator5() {
    $(theform5).data('bootstrapValidator').validate();
    return $(theform5).data('bootstrapValidator').isValid();
};

