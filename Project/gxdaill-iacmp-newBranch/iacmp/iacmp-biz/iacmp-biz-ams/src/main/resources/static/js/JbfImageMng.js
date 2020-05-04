////////////////////////////////////////////////////////////
//文件名称： JbfImageMng.js
//文件功能： 为IE封装京北方图像管理控件
//公司名称： 北京京北方科技
//建立时间： 2005-12-01
//建立作者： 郑邦东
//修改时间：
//修改人名： bd.zheng@northking.net,stdio_01@163.com
//使用方法：
//          在head域直接引入页面就可以使用CJbfImageMng类
//文件描述：
//
//
//----------------------------------------------------------------------------
//类名   CJbfImageMng
//功能   封装页面京北方图像管理控件的类,调用我们公司自己的图像处理控件
//参数
//说明   在页面中的使用例子<img src="<img src="<input type="image" src="<input type="image" src="">">">">
//      <html><head>
//		<SCRIPT LANGUAGE="JavaScript" src="js/JbfImageMng.js"></SCRIPT>
//		<SCRIPT LANGUAGE="JavaScript">
//		<!--
//			var g_oImageMng = new CJbfImageMng
//		-->
//		</SCRIPT>
//		</head><body>
//		<SCRIPT LANGUAGE="JavaScript">
//		<!--
//			InitLpk();
//			obj.InitImageMng("JBFImageMng_01",800,600);
//			obj.LoadListImage("c:\\1.jpg");
//			obj.ShowFirstPage();
//		-->
//		</SCRIPT>
//		</body></html>
//调用接口 方法类
//      void	InitImageMng(id,width,height)                 //初始化图像管理控件
//-----------------------------------------------------------------------------
//      boolean LoadBatchImage(strbatchpath)                  //加载一批图像
//
//  需要获得消息，实现 GetScanEvent_OnTwainScanOneOver 函数里面的内容
//
//////////////////////////////////////////////////////////////
//名称   InitLpk
//功能   初始化权限验证文件
function InitLpk() {
    document.write("<OBJECT CLASSID=\'CLSID:5220CB21-C88D-11CF-B347-00AA00A28331\'>");
    document.write("<PARAM NAME='LPKPath' VALUE='../../../soft/BJJbfImageMng.LPK'>");///**目录可根据项目情况！ */
    document.write("</OBJECT>");
}

//////////////////////////////////////////////////////////////
//名称   CJbfImageMng
//功能   CJbfImageMng封装类
function CJbfImageMng() {
    var m_szName = "JBFImageMng001";
    var m_nWidth = 800;
    var m_nHeight = 600;
    var m_bInitFinished = 0;                   //是否初始化完成
    var m_fZoomRate = 0.5;

    var m_nMaxRows = 2;
    var m_nMaxCols = 2;

    var m_szConstVersion = "1,0,4,90";
    var m_szConstID = "2077BC61-659B-4DF5-BA08-8996FBB78A6D";
    var m_szPackagePath = "../../../soft/JBFImgMng.CAB";
    /**目录可根据项目情况！"../../../../common/activex/scan/JBFImgMng.CAB" */
//扫描参数初始化，只用于控件第一次使用时初始化扫描参数，如果不设置，控件会采用默认值
    this.AutoFeed = "1";
    this.Brightness = "128";
    this.ColorMode = "2";
    this.Contrast = "128";
    this.Duplex = "1";
    this.ImageBits = "8";
    this.PageNum = "-1";
    this.XRes = "200";
    this.YRes = "200";
    this.InitCol = "2";
    this.InitRow = "2";
    this.SaveImgFileType = "1";
    this.ShowUi = "0";

    this.SetName = function (name) {
        Name = name;
    }
    this.GetName = function () {
        return Name;
    }

    this.SetWidth = function (width) {
        Width = width;
    }
    this.GetWidth = function () {
        return Width;
    }

    this.SetHeight = function (height) {
        Height = height;
    }
    this.GetHeight = function () {
        return Height;
    }

    this.SetInitFlag = function (flag) {
        m_bInitFinished = flag;
    }
    this.GetInitFlag = function () {
        return m_bInitFinished;
    }

    this.SetZoomRate = function (zoom) {
        m_fZoomRate = zoom;
    }
    this.GetZoomRate = function () {
        return m_fZoomRate;
    }

    this.GetVersion = function () {
        return m_szConstVersion;
    }

    this.GetClassID = function () {
        return m_szConstID;
    }

    this.SetPackagePath = function (packagepath) {
        m_szPackagePath = packagepath;
    }
    this.GetPackagePath = function () {
        return m_szPackagePath;
    }

    this.obj = function () {
        return document.getElementById(Name);
    }

    this.SetMaxRows = function (maxrows) {
        m_nMaxRows = maxrows;
    }
    this.GetMaxRows = function () {
        return m_nMaxRows;
    }

    this.SetMaxCols = function (maxcols) {
        m_nMaxCols = maxcols;
    }
    this.GetMaxCols = function () {
        return m_nMaxCols;
    }

    this.ShowMessage = "1主件";
    //this.GetErrDes = function() {return m_szErrDes;}

    this.m_szErrDes = new Array(
        0, "成功",
        1, "装载的目录不存在",
        2, "当前用户对目录没有访问权限"
    );
    this.TwainSetDuplexDes = new Array(
        0, "成功",
        1, "设置单双面扫描失败,未知原因",
        2, "设置单双面扫描失败,可能不支持双面扫描"
    );
    this.TwainSetContrast = new Array(
        0, "成功",
        1, "超出范围，不在[0-255]之间",
        2, "从设备获取范围失败！"
    );
}

//////////////////////////////////////////////////////////////
//名称   SetInitScanPara
//功能   设置初始化扫描参数，本参数只是第一次有效。以后参数是根据调用SetParameters之后设置的
//参数
//		pAutoFeed 是否自动进纸 0 手动，1自动
//		pBrightness 亮度 0-255
//		pColorMode  色彩模式 0 黑白 1 灰度 2 彩色
//		pContrast   对比度  0-255
//		pDuplex     单双面  0 单面 1 双面
//		pImageBits  色彩位数
//		pPageNum	进纸张数
//		pXRes,pYRes 分辨率 200dpi
//		pInitCol,pInitRow 初始化行 列
//		pSaveImgFileType 保存类型 0 bmp 1 jpg 2 tif
//		pShowUi 是否显示扫描控制台
CJbfImageMng.prototype.SetInitScanPara = function (pAutoFeed, pBrightness, pColorMode, pContrast, pDuplex, pImageBits, pPageNum, pXRes, pYRes, pInitCol, pInitRow, pSaveImgFileType, pShowUi) {
    this.AutoFeed = pAutoFeed;
    this.Brightness = pBrightness;
    this.ColorMode = pColorMode;
    this.Contrast = pContrast;
    this.Duplex = pDuplex;
    this.ImageBits = pImageBits;
    this.PageNum = pPageNum;
    this.XRes = pXRes;
    this.YRes = pYRes;
    this.InitCol = pInitCol;
    this.InitRow = pInitRow;
    this.SaveImgFileType = pSaveImgFileType;
    this.ShowUi = pShowUi;
}

//////////////////////////////////////////////////////////////
//名称   InitImageMng
//功能   在IE页面中初始化图像控件
//参数
//       name    在页面中的ID，要唯一区别于其他页面中的元素ID或者名称
//       width   控件的宽度，像素
//       height  控件的高度，像素
//说明   这个方法一定要在其他方法使用之前调用,不指属性设置函数 如：SetPackagePath()。
CJbfImageMng.prototype.InitImageMng = function (name, width, height) {
    //初始化图像处理控件
    if (this.GetInitFlag() == 1) return;

    this.SetName(name);
    this.SetWidth(width);
    this.SetHeight(height);
    document.write("<CENTER><OBJECT ");//this.GetHeight()
    document.write("ID='" + this.GetName() + "' WIDTH='" + this.GetWidth() + "' HEIGHT='" + 560 + "' ");
    document.write("CLASSID='CLSID:" + this.GetClassID() + "' CODEBASE='" + this.GetPackagePath() + "#version=" + this.GetVersion() + "'>");
    //document.write ("CLASSID='CLSID:" + this.GetClassID() + "'>");
    document.write("<PARAM NAME=\"Vender\" VALUE=\"京北方信息技术股份有限公司\">");
    //document.write ("<PARAM NAME=\"Vender\" VALUE=\"北京京北方科技股份有限公司\">");
    document.write("<PARAM NAME=\"AutoFeed\" VALUE=\"" + this.AutoFeed + "\">");
    document.write("<PARAM NAME=\"Brightness\" VALUE=\"" + this.Brightness + "\">");
    document.write("<PARAM NAME=\"ColorMode\" VALUE=\"" + this.ColorMode + "\">");
    document.write("<PARAM NAME=\"Contrast\" VALUE=\"" + this.Contrast + "\">");
    document.write("<PARAM NAME=\"Duplex\" VALUE=\"" + this.Duplex + "\">");
    document.write("<PARAM NAME=\"ImageBits\" VALUE=\"" + this.ImageBits + "\">");
    document.write("<PARAM NAME=\"PageNum\" VALUE=\"" + this.PageNum + "\">");
    document.write("<PARAM NAME=\"XRes\" VALUE=\"" + this.XRes + "\">");
    document.write("<PARAM NAME=\"YRes\" VALUE=\"" + this.YRes + "\">");
    document.write("<PARAM NAME=\"InitCol\" VALUE=\"" + this.InitCol + "\">");
    document.write("<PARAM NAME=\"InitRow\" VALUE=\"" + this.InitRow + "\">");
    document.write("<PARAM NAME=\"SaveImgFileType\" VALUE=\"" + this.SaveImgFileType + "\">");
    document.write("<PARAM NAME=\"ShowUi\" VALUE=\"" + this.ShowUi + "\">");
    document.write("<PARAM NAME=\"ShowMessage\" VALUE=\"" + this.ShowMessage + "\">");
    document.write("</OBJECT></CENTER>");

    //绑定事件 --  扫描完成一张
    document.write("<SCRIPT FOR='" + this.GetName() + "' EVENT='TwainScanOneOver(strFileName,nSize,bObversed)'>");
    document.write("GetScanEvent_OnTwainScanOneOver(strFileName,nSize,bObversed);");
    document.write("</SCRIPT>");
    //绑定事件 --  扫描结束
    document.write("<SCRIPT FOR='" + this.GetName() + "' EVENT='TwainScanOver(flag,szDes)'>");
    document.write("GetScanEvent_OnTwainScanOver(flag,szDes);");
    document.write("</SCRIPT>");
    //绑定事件 --  装载图像完成
    document.write("<SCRIPT FOR='" + this.GetName() + "' EVENT='LoadOk(count)'>");
    document.write("GetImgEvent_OnLoadOk(count);");
    document.write("</SCRIPT>");
    //绑定事件 --  传输完成一张
    document.write("<SCRIPT FOR='" + this.GetName() + "' EVENT='UploadOnefile(filepath,filesize,nflag)'>");
    document.write("GetScanEvent_OnUploadOnefile(filepath,filesize,nflag);");
    document.write("</SCRIPT>");
    //绑定事件 --  显示的当前页数以及总页数
    document.write("<SCRIPT FOR='" + this.GetName() + "' EVENT='SelectImage(nIndex,nCount)'>");
    document.write("GetScanEvent_OnSelectImage(nIndex,nCount);");
    document.write("</SCRIPT>");
    //绑定事件 --  控件加载完成
    document.write("<SCRIPT FOR='" + this.GetName() + "' EVENT='InitFinish()'>");
    document.write("GetScanEvent_OnInitFinish();");
    document.write("</SCRIPT>");

    this.SetInitFlag(1);
}


//////////////////////////////////////////////////////////////
//名称   GetScanEvent_OnTwainScanOneOver
//功能   扫描完成一张图像接收的事件
//参数
//			 strFileName	文件名称
//			 nSize        文件大小
//			 bObversed    正反面
//返回
function GetScanEvent_OnTwainScanOneOver(strFileName, nSize, bObversed) {
    if (nSize == 0) {
        alert("保存文件：" + strFileName + " 失败，原因是色彩模式不匹配！");
    } else {
        try {
            OnTwainScanOneOver(strFileName, nSize, bObversed);
        } catch (e) {
            alert("Please implement OnTwainScanOneOver(strFileName,nSize,bObversed)! 消息信息为： 文件名称：　" + strFileName + "尺寸：　" + nSize + " 正反面：" + bObversed);
        }
    }
}

//////////////////////////////////////////////////////////////
//名称   GetImgEvent_OnLoadOk
//功能   装载一批图像完成
//参数
//			  count   数量
//返回
function GetImgEvent_OnLoadOk(count) {
    try {
        OnImageLoadOk(count);
    } catch (e) {
        alert("Please implement OnImageLoadOk(count)! 消息信息为：数量 " + count);
    }
}

//////////////////////////////////////////////////////////////
//名称   GetScanEvent_OnTwainScanOver
//功能   扫描完成接收的事件
//参数
//			 flag  标志信息 0 是 扫描一次完成指自动进纸的时候，放的纸已经扫描完成 1 是扫描结束
//			 szDes 描述信息
//返回
function GetScanEvent_OnTwainScanOver(flag, szDes) {
    try {
        OnTwainScanOver(flag, szDes);
    } catch (e) {
        alert("Please implement OnTwainScanOver(flag,szDes)! 消息信息为：标志 " + flag + "描述 " + szDes);
    }
}

//////////////////////////////////////////////////////////////
//名称   GetScanEvent_OnInitFinish()
//功能   控件加载完成,实现方式有问题，暂时不支持，仍然使用Settimeout初始化
//参数
//返回

function GetScanEvent_OnInitFinish() {
    try {
        OnInitFinish();
    } catch (e) {
        alert("Please implement OnInitFinish()! 消息信息为控件已经加载完成");
    }
}

//////////////////////////////////////////////////////////////
//名称   GetScanEvent_OnSelectImage(nIndex,nCount)
//功能   扫描完成接收的事件
//参数
//			 nIndex  当前页序号，此页序号是指某个文件在整个扫描文件中的序号，和显示的页不属于同一概念
//			 nCount  总页数
//返回
function GetScanEvent_OnSelectImage(nIndex, nCount) {
    try {
        //alert("in es");
        OnSelectImage(nIndex, nCount);
    } catch (e) {
        alert("Please implement OnSelectImage(nIndex,nCount)! 消息信息为：第 " + nIndex + " 页/共 " + nCount + " 页");
    }
}


//////////////////////////////////////////////////////////////
//名称   GetScanEvent_OnUploadOnefile
//功能   上传一张图像完成接收的事件
//参数
//           filepath -  文件名称
//           filesize - 文件大小
//			 nflag -   标志信息
//                 -1 出现异常，异常信息有filepath带回
//                0 失败
//                1 成功
//                2 打开本地文件失败
function GetScanEvent_OnUploadOnefile(filepath, filesize, nflag) {
    try {
        OnUploadOnefile(filepath, filesize, nflag);
    } catch (e) {
        alert("Please implement OnUploadOnefile(filepath,filesize,nflag)! 消息信息为：文件 " + filepath + " 长度 " + filesize + " 结果标志 " + nflag);
    }
}


//////////////////////////////////////////////////////////////
//名称   GetLastErrorNo
//功能   获取错误代码
//参数
//返回
CJbfImageMng.prototype.GetLastErrorNo = function () {
    return this.obj().GetLastErrorNo();
}

//////////////////////////////////////////////////////////////
//名称   GetLastErrorDes
//功能   获取错误描述
//参数   errno 错误代码，为空，返回最后错误描述
//返回
CJbfImageMng.prototype.GetLastErrorDes = function (errno) {
    var ErrNo = (errno == null ? this.GetLastErrorNo() : errno);
    for (var i = 0; i < this.m_szErrDes.length; i = i + 2) {
        if (this.m_szErrDes[i] == ErrNo) {
            return this.m_szErrDes[i + 1];
        }
    }
    return "没有指明的错误信息，请联系程序开发商！";
}

//////////////////////////////////////////////////////////////
//名称   SetCells
//功能   设置单元格的个数
//参数   rows: 行数
//       cols: 列数
//返回   成功的 0 成功 其他失败
//说明   注意，目前支持 8*8
CJbfImageMng.prototype.SetImageCells = function (rows, cols) {
    var nRet = this.obj().ImgSetImageShowCells(rows, cols);
    if (nRet != 0) {
        this.SetMaxRows(rows);
        this.SetMaxCols(cols);
    }

    return nRet;
}

//////////////////////////////////////////////////////////////
//名称   LoadBatchImage
//功能   加载一个目录的图像到图像处理控件里，但是不会显示，要显示需要用显示影像的函数
//参数   batchpath 批量图像所在的目录，全路径名称
//返回   true 成功 false 失败
//说明   注意，这里没有校验目录，即使你传一个非法的，控件也认为是正确的
CJbfImageMng.prototype.LoadBatchImage = function (batchpath) {
    return this.obj().ImgLoadBatchImage(batchpath);
}

//////////////////////////////////////////////////////////////
//名称   LoadListImage
//功能   加载一批图像到图像处理控件里，但是不会显示，要显示需要用显示影像的函数
//参数   filelist 格式： filepathname|filepathname|filepathname
//返回   true 成功 false 失败
//说明   注意，这里没有校验目录，即使你传一个非法的，控件也认为是正确的
CJbfImageMng.prototype.LoadListImage = function (filelist) {
    return this.obj().ImgLoadListImage(filelist);
}

//////////////////////////////////////////////////////////////
//名称   AppendImage
//功能   添加一张指定的图像到控件的列表中
//参数   strfilename 文件名称全路径
//返回   true 成功 false 失败
//说明   需要先调用加载图像的函数
CJbfImageMng.prototype.AppendImage = function (strfilename) {
    return this.obj().ImgAppendImage(strfilename);
}

//////////////////////////////////////////////////////////////
//名称   ShowFirstPage
//功能   显示列表中的第一张影像
//参数
//返回   显示成功的图像个数
CJbfImageMng.prototype.ShowFirstPage = function () {
    return this.obj().ImgShowFirstPage();
}

//////////////////////////////////////////////////////////////
//名称   ShowPreviousPage
//功能   显示列表中 当前显示图像的上一张影像
//参数
//返回   显示成功的图像个数
CJbfImageMng.prototype.ShowPreviousPage = function () {
    return this.obj().ImgShowPreviousPage();
}

//////////////////////////////////////////////////////////////
//名称   ShowNextPage
//功能   显示列表中 当前显示图像的下一张影像
//参数
//返回   显示成功的图像个数
CJbfImageMng.prototype.ShowNextPage = function () {
    return this.obj().ImgShowNextPage();
}

//////////////////////////////////////////////////////////////
//名称   ShowLastPage
//功能   显示列表中的最后一张影像
//参数
//返回   显示成功的图像个数
CJbfImageMng.prototype.ShowLastPage = function () {
    return this.obj().ImgShowLastPage();
}

//////////////////////////////////////////////////////////////
//名称   ZoomImage
//功能   调整图像大小
//参数
//       fzoomrate 浮点数
//       范围 r = (0.03,16) 表示显示的倍数 1 表示原图大小显示
//返回
CJbfImageMng.prototype.ZoomImage = function (fzoomrate) {
    fzoomrate = fzoomrate == null ? this.GetZoomRate() : fzoomrate;
    this.SetZoomRate(fzoomrate);
    this.obj().ImgZoomImage(fzoomrate);
}

//////////////////////////////////////////////////////////////
//名称   ZoomRate
//功能   放大图像
//参数
//       fzoomrate 浮点数
//       范围 r = (0,10) 表示显示的倍数 1 表示原图大小显示
//返回
//说明   如果没有传递参数 就是用默认设置,默认设置是0.5
CJbfImageMng.prototype.ZoomRate = function (fzoomrate) {
    if (fzoomrate == null) {
        fzoomrate = 0.5;
    }
    this.obj().ImgZoomImage(fzoomrate);
}

//////////////////////////////////////////////////////////////
//名称   ZoomOut
//功能   放大图像
//参数
//       fzoomrate 浮点数
//       范围 r = (0,10) 表示显示的倍数 1 表示原图大小显示
//返回
//说明   如果没有传递参数 就是用默认设置,默认设置是再放大0.2的参数
CJbfImageMng.prototype.ZoomOut = function (fzoomrate) {
    if (fzoomrate == null) {
        this.SetZoomRate(this.GetZoomRate() + this.GetZoomRate() * 0.2);
    } else {
        this.SetZoomRate(fzoomrate);
    }
    this.obj().ImgZoomImage(this.GetZoomRate());
}

//////////////////////////////////////////////////////////////
//名称   ZoomIn
//功能   放大图像
//参数
//       fzoomrate 浮点数
//       范围 r = (0,10) 表示显示的倍数 1 表示原图大小显示
//返回
//说明   如果没有传递参数 就是用默认设置,默认设置是再 缩小0.2的参数
CJbfImageMng.prototype.ZoomIn = function (fzoomrate) {
    if (fzoomrate == null) {
        this.SetZoomRate(this.GetZoomRate() - this.GetZoomRate() * 0.2);
    } else {
        this.SetZoomRate(fzoomrate);
    }
    this.obj().ImgZoomImage(this.GetZoomRate());
}

//////////////////////////////////////////////////////////////
//名称   ShowPageByIndex
//功能   根据索引顺序显示图像
//参数   iindex 索引数
//返回   true 成功 false 失败
//说明   参数为空，显示当前图像
CJbfImageMng.prototype.ShowPageByIndex = function (lindex) {
    var index = parseInt(lindex, 10);
    if (lindex == null) {
        return true;
    }
    if (isNaN(index) || index < 0) {
        index = 0;
    }
    if (index >= this.GetImagesCount()) {
        flag = this.GetImagesCount() - 1;
    }
    return this.obj().ImgShowFileByIndex(index);
}

//////////////////////////////////////////////////////////////
//名称   GetImagesCount
//功能   得到列表中可显示的图像个数
//参数
//返回
CJbfImageMng.prototype.GetImagesCount = function () {
    return this.obj().ImgGetImageCount();
}

//////////////////////////////////////////////////////////////
//名称   GetImageFileName
//功能   得到指定位置的影像名称
//参数   iindex 文件序号
//返回
CJbfImageMng.prototype.GetImageFileName = function (index) {
    return this.obj().ImgGetFileNameByIndex(index);
}

//////////////////////////////////////////////////////////////
//名称   PrintImage
//功能   打印图像
//参数
//返回
CJbfImageMng.prototype.PrintImage = function () {
    return this.obj().ImgPrint();
}

//////////////////////////////////////////////////////////////
//名称   Rotate
//功能   旋转图像
//参数
//返回
CJbfImageMng.prototype.Rotate = function (fangle) {
    //alert("按照９０度旋转，可以自己设定角度");
    if (fangle == null) {
        return this.obj().ImgRotate(90);
    } else {
        return this.obj().ImgRotate(fangle);
    }

}

//////////////////////////////////////////////////////////////
//名称   ImgAutoTrim
//功能   去边
// 参  数: [in] short nEdge - 边缘标志 0-黑边 1-白边
//       : [in] short nThreshold - 前景与背景的最小亮度差值
//       : [in] long nPixelNum - 边缘上的最大象素个数
//       : [in] float fratio - 边缘上象素所占的最小比例
//返回
CJbfImageMng.prototype.AutoTrim = function (nEdge, nThreshold, nPixelNum, fratio) {
    //alert("去黑边\r\n 前景与背景的最小亮度差值：20 \r\n 边缘上的最大象素个数：0 \r\n 边缘上象素所占的最小比例:1%");
    return this.obj().ImgAutoTrim(0, 20, 0, 0.01);
}


///////////////////////////////////////////////////////////////
//
//					扫描仪的设置
//
//////////////////////////////////////////////////////////////
//名称   SelectSource
//功能   选择扫描仪
//参数
//返回
CJbfImageMng.prototype.SelectSource = function () {
    return this.obj().TwainSelectSource();
}

//选择扫描仪
CJbfImageMng.prototype.ChoiceScanner = function () {
    return this.SelectSource();
}

//扫描时，是否显示界面
CJbfImageMng.prototype.SetShowUI = function (flag) {
    return this.obj().TwainSetShowUI(flag);
}

//扫描时，是否自动进纸
CJbfImageMng.prototype.SetAutoFeed = function (flag) {
    return this.obj().TwainSetAutoFeed(flag);
}


//////////////////////////////////////////////////////////////
//名称   SetScanPath
//功能   设置路径
//参数
//返回
CJbfImageMng.prototype.SetScanPath = function (pathfile) {
    return this.obj().ImgSetPathNameforSave(pathfile);
}

//////////////////////////////////////////////////////////////
//名称   Scan
//功能   扫描
//参数
//返回
CJbfImageMng.prototype.Scan = function () {
    if (arguments.length != 1)
        return this.obj().TwainAcquire();
    else {
        return this.obj().TwainAcquire(arguments[0]);
    }
}
//////////////////////////////////////////////////////////////
//名称   GetDeviceType
//功能   获取设备类型
//参数
//返回   1：摄像头 0：扫描仪
//说明：
CJbfImageMng.prototype.GetDeviceType = function () {
    return this.obj().GetDeviceType();
}
//////////////////////////////////////////////////////////////
//名称   CutImage
//功能   切分图像
//参数
//返回  0：成功，其它失败
//说明：
CJbfImageMng.prototype.CutImage = function (srcPath, dstPath, left, top, width, height) {
    return this.obj().CutImage(srcPath, dstPath, left, top, width, height);
}
//////////////////////////////////////////////////////////////
//名称   EnableAutoCrop
//功能   启用自动裁切
//参数   isEnable 1:启用 0:关闭
//返回   1：启用成功 0：启用失败
//说明：
CJbfImageMng.prototype.EnableAutoCrop = function (isEnable) {
    return this.obj().EnableAutoCrop(isEnable);
}
//////////////////////////////////////////////////////////////
//名称   Rescan
//功能   重新扫描
//参数
//返回
//说明：应该先插入，然后再删除。以后有时间在修改
CJbfImageMng.prototype.Rescan = function () {
    return this.obj().TwainReplace(0);
}

//////////////////////////////////////////////////////////////
//名称   InsertScan
//功能   插入
//参数
//返回
CJbfImageMng.prototype.InsertScan = function () {
    this.obj().TwainInsertFile();
}

//////////////////////////////////////////////////////////////
//名称   DeleteFile
//功能   删除扫描的文件
//参数   -1 删除当前选中的，其他的，以后补充 -----
//       -1 delete current choiced file
//       -2 delete all file
//		   -3 delete all file after current choiced file
//       -4 delete all file befor current choiced file
//       >=0 && < count, delete file in pagelist's nindex
//返回
CJbfImageMng.prototype.DeleteFile = function (flag) {
    this.obj().TwainDeleteFile(flag);
}

CJbfImageMng.prototype.DeleteCurrentFile = function () {
    /*alert("into2");*/
    //this.obj().SetParaInfo("bSinglePageOperate", "1");
    this.obj().TwainDeleteFile(-1);
}

CJbfImageMng.prototype.DeleteAllFiles = function () {
    /*alert("into1");*/
    this.obj().TwainDeleteFile(-2);
}


CJbfImageMng.prototype.SetParaInfo = function () {
    this.obj().SetParaInfo("bSinglePageOperate", "1");
}

CJbfImageMng.prototype.GetParaValFromKey = function () {
    var strkey = "bSinglePageOperate";
    var strVal;

    var ret = this.obj().GetParaValFromKey(strkey);
    /*alert(ret);*/
}

CJbfImageMng.prototype.DeleteCurAfterFiles = function () {
    this.obj().TwainDeleteFile(-3);
}
CJbfImageMng.prototype.Delete = function (entityName) {
    alert("into3");
    this.obj().DeleteFolder(entityName);
}

//////////////////////////////////////////////////////////////
//名称   SetScanPath
//功能   设置扫描路径
//参数
//返回
CJbfImageMng.prototype.SetScanPath = function (filepath) {
    this.obj().ImgSetPathNameforSave(filepath);
}


//////////////////////////////////////////////////////////////
//名称   SetMaxPages
//功能   设置最大扫描页数
//参数
//返回
CJbfImageMng.prototype.SetMaxPages = function (maxpages) {
    this.obj().TwainSetMaxPageNum(maxpages);
}

//////////////////////////////////////////////////////////////
//名称   TwainSetEnableDuplex
//功能   设置扫描单双面
//参数   flag  0 单面
//			   1 双面
//返回
CJbfImageMng.prototype.SetEnableDuplex = function (flag) {
    return this.obj().TwainSetEnableDuplex(flag);
}

//////////////////////////////////////////////////////////////
//名称   SetImgResolution
//功能   设置扫描分辨率X
//参数
//返回
CJbfImageMng.prototype.SetImgResolution = function (xRes, yRes) {
    this.SetImgXResolution(xRes);
    this.SetImgYResolution(yRes);
}


//////////////////////////////////////////////////////////////
//名称   SetImgXResolution
//功能   设置扫描分辨率X
//参数
//返回
CJbfImageMng.prototype.SetImgXResolution = function (xRes) {
    this.obj().TwainSetImgXResolution(xRes);
}


//////////////////////////////////////////////////////////////
//名称   SetImgYResolution
//功能   设置扫描分辨率Y
//参数
//返回
CJbfImageMng.prototype.SetImgYResolution = function (yRes) {
    this.obj().TwainSetImgYResolution(yRes);
}

//////////////////////////////////////////////////////////////
//名称   SetColorMode
//功能   设置扫描颜色深度
//参数
//返回
CJbfImageMng.prototype.SetColorMode = function (nCurSel) {

    var bits;
    if (nCurSel == 0) {
        bits = 1;
    } else if (nCurSel == 1) {
        bits = 4;
    } else if (nCurSel == 2) {
        bits = 24;
    }
    try {
        this.obj().TwainSetImgPixelBits(bits);
        this.obj().TwainSetColorMode(nCurSel);
    } catch (e) {
        alert("出现错误：" + e);
    }

}

//////////////////////////////////////////////////////////////
//名称   SetImgContrast
//功能   设置扫描图像对比度
//参数
//返回  0 成功 1 不在 (0-255) 2 获取信息失败
CJbfImageMng.prototype.SetImgContrast = function (nContrast) {
    return this.obj().TwainSetImgContrast(nContrast);
}

//////////////////////////////////////////////////////////////
//名称   SetImgBrightness
//功能   设置扫描图像亮度
//参数
//返回    0 成功 1 不在 (0-255) 2 获取信息失败
CJbfImageMng.prototype.SetImgBrightness = function (nBright) {
    return this.obj().TwainSetImgBrightness(nBright);
}

//////////////////////////////////////////////////////////////
//名称   SaveAs
//功能   图像另存为
//参数
//返回
CJbfImageMng.prototype.SaveAs = function (srcfilename) {
    return this.obj().ImgSaveAs(srcfilename);
}

//////////////////////////////////////////////////////////////
//名称   SaveAs
//功能   图像另存为
//参数
//返回
CJbfImageMng.prototype.GetCurrentImageName = function () {
    return this.obj().ImgGetFileNameByIndex(this.obj().ImgGetCurrentImageIndex());
}

//////////////////////////////////////////////////////////////
//名称   FilterWhite
//功能   图像另存为
//参数
//返回
CJbfImageMng.prototype.FilterWhite = function (flag) {
    //alert("参数为２，只滤背面");
    this.obj().ImgRemoveWhitePage(flag);
}

//////////////////////////////////////////////////////////////
//名称   FilterWhite
//功能   图像另存为
//参数
//返回
CJbfImageMng.prototype.SetShowType = function (flag) {
    //alert("参数为0，显示全部１显示正面　２显示背面");
    this.obj().ImgSetShowway(flag);
}

//////////////////////////////////////////////////////////////
//名称   ShowUrlFile
//功能   显示http或者file://的文件，也能显示本地的文件
//参数
//返回
CJbfImageMng.prototype.ShowUrlFile = function (filename) {
    //alert("参数为0，显示全部１显示正面　２显示背面");
    this.obj().ImgShowFile(filename);
}

//////////////////////////////////////////////////////////////
//名称   SetImageType
//功能   设置文件的格式类型
//参数   0 BMP 1 JPG 2 TIF
//返回
CJbfImageMng.prototype.SetImageType = function (imagetype) {
    this.obj().ImgSetType(imagetype);//maoyubo 20120327
}

//////////////////////////////////////////////////////////////
//名称   SetImageType
//功能   设置文件的格式类型
//参数   0 BMP 1 JPG 2 TIF
//返回
CJbfImageMng.prototype.TwainGetImgBrightnessInfo = function (VMin, VMax, VStep, VDefault) {
    this.obj().TwainGetImgBrightnessInfo(VMin, VMax, VStep, VDefault);
}

//////////////////////////////////////////////////////////////
//名称   SetParameters
//功能   弹出参数设置对话框，设置部分参数
//参数
//返回
CJbfImageMng.prototype.SetParameters = function () {
    this.obj().SetBrightEditable(1);
    /*alert('11111');*/
    this.obj().TwainSetParameter();
}

//////////////////////////////////////////////////////////////
//名称   Close
//功能   关闭扫描设备,在页面的unload里面调用
//参数
//返回
CJbfImageMng.prototype.Close = function () {
    this.obj().Close();
}


//////////////////////////////////////////////////////////////
//名称   GetBase64StringFrom
//功能   得到图像的base64的字符
//参数   文件名称
//返回
CJbfImageMng.prototype.GetBase64StringFrom = function (FilePath, lReserved) {
    return this.obj().GetBase64StringFrom(FilePath, lReserved);
}


//////////////////////////////////////////////////////////////
//名称   GetBase64StringFrom
//功能   得到图像的base64的字符
//参数   文件名称
//返回
CJbfImageMng.prototype.TwainSetDeletefileFlag = function (isRealDelete) {
    return this.obj().TwainSetDeletefileFlag(isRealDelete);
}


//////////////////////////////////////////////////////////////////////
// 函数名: SetDemoImgInfo
// 功  能: 为扫描仪提供虚拟的扫描结果，预先准备好图像，依照顺序模拟扫描，出现预先定义的图像结果
// 返  回: void  -
// 参  数: [in] LPCTSTR FilenameTemplate - 预定义图像的路径名称,路径和文件名直接加起来成为文件的绝对路径。
//       : [in] long DemoImgMaxCount - 最多图像张数，当为0时候取消DEMO,按照实际上的情况扫描
//       : [in] long DemoGrade - 模拟级别
//              0 调用扫描接口就给图像，不需要扫描仪,暂时不支持
//              非零 正常调用扫描仪，接收到扫描图像替换为预先准备的图像
//说明： 只是支持两种格式HTTP以及本地
//    SetDemoImgInfo("c:\\DEMO\\",3,1);
//    SetDemoImgInfo("http://127.0.0.1/DEMO/",3,1);
//    表示c:\\DEMO　或者　http://127.0.0.1/DEMO/　目录下有FILE0001.jpg,FILE0002.jpg,FILE0003.jpg文件。
CJbfImageMng.prototype.SetDemoImgInfo = function (FilenameTemplate, DemoImgMaxCount, DemoGrade) {
    this.obj().SetDemoImgInfo(FilenameTemplate, DemoImgMaxCount, DemoGrade);
}


//////////////////////////////////////////////////////////////
//名称   SetJPEGQuality
//功能   设置图像的压缩质量
//参数   Quality 取值 0 - 100
//返回
CJbfImageMng.prototype.SetJPEGQuality = function (Quality) {
    this.obj().ImgSetJPEGQuality(Quality);
}


//////////////////////////////////////////////////////////////
//名称   ImgDeskew
//功能   图像自动纠偏，不好使用
//参数
//返回
CJbfImageMng.prototype.ImgDeskew = function () {
    this.obj().ImgDeskew();
}

//////////////////////////////////////////////////////////////
//名称   SetMaxCashCount
//功能   设置最大缓存页面数
//参数   页面数，整数，注意此参数一定要大于 单页显示的最多文件数，如 8×8 maxcount 必须大于64
//返回
CJbfImageMng.prototype.SetMaxCashCount = function (maxcount) {
    this.obj().ImgSetMaxCashCount(maxcount);
}

//////////////////////////////////////////////////////////////
//名称   SubmitBatch
//功能   提交批量
//参数   在这里起到的作用仅为，把文件名称整理一下。整理成为顺序的号码。
//返回
CJbfImageMng.prototype.SubmitBatch = function () {
    this.obj().SubmitBatch();
}

//////////////////////////////////////////////////////////////
//名称   SetVender
//功能   设置生产厂商
//参数   厂商名称：固定输入 "北京京北方科技股份有限公司"
//返回
CJbfImageMng.prototype.SetVender = function (vender) {
    this.obj().SetVender(vender);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::Upload2Ftp
// 功  能: 把扫描文件传输到FTP服务器上去
// 返  回: long  - 传输成功的文件个数
// 参  数: [] LPCTSTR sServerName -  ftp Server 形如："10.10.208.38" 或者 "www.northking.net"
//									 如果本参数内容为 http:// 开头，函数就会去下载 sServerName,
//									 从文件中读取 sServerName，sUserName，sPassword, nPort, 加密密码
//       : [] LPCTSTR sUserName -    ftp user name
//       : [] LPCTSTR sPassword -    ftp user password 明码
//       : [] short nPort -          ftp port
//       : [] short bPassive -       ftp passive
//       : [] LPCTSTR sRDir -    必须以 / 结尾 ,只能创建一级目录  ftpdir/a/b/c/
//								 当前FTP用户的用户主目录下的目录
//       : [] LPCTSTR sLFileList - 传输的本地列表，名称由"|"隔开   形如："c:\\JBFScan\\0000001.JPG|c:\\JBFScan\\0000003.JPG|c:\\JBFScan\\0000003.JPG"
//									当本参数不为 "" ,传输的所有文件就来源于此列表,为 "" 时，传输目前扫描组件内装载的所有文件,必须是可加载的图像文件。

CJbfImageMng.prototype.Upload2Ftp = function (server, user, pwd, port, passive, rpath, filelist) {
    return this.obj().Upload2Ftp(server, user, pwd, port, passive, rpath, filelist);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::MakeFtpConnectInfo
// 功  能: 生成FTP传输参数，保存到文件中
// 返  回: void  -
// 参  数: [] LPCTSTR sServerName - 服务器地址
//       : [] short nPort - 服务器端口号
//       : [] LPCTSTR sUserName - FTP用户名称
//       : [] LPCTSTR sPassword - FTP用户密码
//       : [] short nPassive -    FTP主动模式或者被动模式
//       : [] LPCTSTR sFilePath -  存储加密的FTP参数的本地文件全路径
//说   明: 生成的文件大小504字节，注意，V1.0.3.0只支持从HTTP下载加密参数，不支持从本地加载参数。

CJbfImageMng.prototype.MakeFtpConnectInfo = function (sServerName, nPort, sUserName, sPassword, nPassive, sFilePath) {
    return this.obj().MakeFtpConnectInfo(sServerName, nPort, sUserName, sPassword, nPassive, sFilePath);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::Upload2FtpByHttp
// 功  能: 使用Http代理FTP,完成FTP传输
// 返  回: long  - 成功传输的文件数
// 参  数: [] LPCTSTR sHttpServerName - http Server 形如："10.10.208.38" 或者 "www.northking.net"
//       : [] short nHttpPort -   http端口, 形如： 80 或者 8080
//       : [] LPCTSTR sHttpUrl -  实现FTP代理的HttpUrl, 形如："/icc/servlet/HttpAgentFileStoreServlet"
//       : [] LPCTSTR sFtpRootPath - FTP服务器上的路径,形如："ftp://192.168.1.133////0001/abc/" 或者 "httpagentftpServer////0001/abc/"
//									 注意，ftp://192.168.1.133 只是在服务器上配置的一个FTP的名称。
//									 0001/abc/ 当前FTP用户的用户主目录下的目录
//       : [] LPCTSTR sLocalFilelist - 传输的本地列表，名称由"|"隔开   形如："c:\\JBFScan\\0000001.JPG|c:\\JBFScan\\0000003.JPG|c:\\JBFScan\\0000003.JPG"
//									当本参数不为 "" ,传输的所有文件就来源于此列表,为 "" 时，传输目前扫描组件内装载的所有文件,必须是可加载的图像文件。

CJbfImageMng.prototype.Upload2FtpByHttp = function (server, port, url, ftproot, filelist) {
    return this.obj().Upload2FtpByHttp(server, port, url, ftproot, filelist);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::Init
// 功  能:
// 返  回: short  -
// 参  数: [] short nFlag - 1 扫描初始化   - 2 图像浏览初始化
// 说  明: 此函数必须在扫描动作或者设置扫描参数之前设置。一般在控件初始化完成时候加载

CJbfImageMng.prototype.InitControlType = function (nFlag) {
    return this.obj().Init(nFlag);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::SetShowFrontBackIdentity
// 功  能: 设置，显示正反面以及序号标志
// 返  回: void  -
// 参  数: [] short nFlag
//  HIDE_MESSAGE		0
//  SHOW_MESSAGE_FRONT	1
//  SHOW_MESSAGE_NO		2
//  SHOW_MESSAGE_ATTACH	4

//         默认不显示
CJbfImageMng.prototype.SetShowFrontBackIdentity = function (nFlag) {
    this.obj().SetShowFrontBackIdentity(nFlag);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::SetShowFrontBackIdentity
// 功  能: 获得，显示正反面以及序号标志
// 返  回: void  -
// 参  数: [] short nFlag - TRUE 显示 FALSE 不显示
//         默认不显示
CJbfImageMng.prototype.IsShowFrontBackIdentity = function () {
    return this.obj().IsShowFrontBackIdentity();
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::ImgRateAll
// 功  能: 按照指定的角度旋转所有的图像
// 返  回: long  -
// 参  数: [] float fRate - 旋转角度
//         90  - left
//		   180
//         270 - right
CJbfImageMng.prototype.RotateAll = function (fRate) {
    this.obj().ImgRateAll(fRate);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::TwainSetDefaultRate
// 功  能: 按照指定的角度旋转所有的图像
// 返  回: long  -
// 参  数: [] float fRate - 旋转角度
//         90  - left
//		   180
//         270 - right
CJbfImageMng.prototype.SetScanDefaultRate = function (fRate) {
    this.obj().TwainSetDefaultRate(fRate);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::ImgImport
// 功  能: 模拟导入
// 返  回: short  -
// 参  数: [] LPCTSTR FilePath -
//       : [] short nFlag - 0 导入目录 1 导入 FilePath的文件 格式形如LoadListImageA
//CJbfImageMng.prototype.ImgImport = function(filePath,nFlag)
//{
//	return this.obj().ImgImport(filePath,nFlag);
//}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::ImgImport
// 功  能: 模拟导入
// 返  回: short  -
// 参  数: [] LPCTSTR FilePath -
//       : [] short nFlag - 导入标志
//			0 导入目录
//			1 导入 FilePath的文件 格式形如LoadListImageA
//			-1 弹出导入对话框，向导方式导入，支持高级导入方式
CJbfImageMng.prototype.ImgImport = function (filePath, nFlag) {
    //return this.obj().ImgImport(filePath,nFlag);
    return this.obj().importImg(filePath, nFlag);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::ImgImportByFileList
// 功  能:
// 创  建: 2008-12-18 12:22:42 by bd.zheng@northking.net stdio_01@163.com
// 修  改:
// 返回值: long   -
// 参  数: [in] LPCTSTR szFileList - 文件列表，全路经 filepath1|filepath2|...|filepathn|
//       : [in] short nSortByName -  通过文件名称按照字母排序 1 升序 0 降序,受nNeesSort参数的影响
//		 : [in] short nFrontIdentityType - 正反面确定方式
//				-3 全部正面
//				-2 奇偶 第一个 正面 第二个 反面 依次类推,szFrontIdentity参数失效
//				-1 文件名称，奇偶，奇数正面 偶数反面,szFrontIdentity参数失效
//				> 0 在filename (短文件名，不包括路径以及扩展名)内
//					nIdentityStartPos 到 nIdentityEndPos 之间能够查询到 szFrontIdentity 计为正面，否则反面
//       : [in] LPCTSTR szFrontIdentity -
//				正反面标志字符串,通过在文件名中查找标志字符串，
//				找到就是正面否则就是反面，受nIdentityStartPos参数影响
//       : [in] short nIdentityStartPos - 正反面标志字符串的开始查找位置
//       : [in] short nIdentityEndPos -   正反面标志字符串的结束查找位置
//       : [in] short nNeedSort - 是否需要排序，
//				1 需要，排序规则根据参数nSortByName
//				0 不需要，nSortByName失效,按照文件名称传入的顺序存放
// 说  明:
CJbfImageMng.prototype.ImgImportByFileList = function (szFileList, nSortByName, nFrontIdentityType, szFrontIdentity, nIdentityStartPos, nIdentityEndPos, nNeedSort) {
    return this.obj().ImgImportByFileList(szFileList, nSortByName, nFrontIdentityType, szFrontIdentity, nIdentityStartPos, nIdentityEndPos, nNeedSort);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::ImgImportByPath
// 功  能: 导入指定目录下的影像
// 创  建: 2008-12-18 10:52:51 by bd.zheng@northking.net stdio_01@163.com
// 修  改:
// 返回值: long   - -1 导入失败 否则返回导入的文件个数，有可能是0
// 参  数: [in] LPCTSTR szFilePath - 磁盘目录，全路径
//       : [in] short nSortByName -  通过文件名称按照字母排序 1 升序 0 降序
//		 : [in] short nFrontIdentityType -参见 CJBFImgMngCtrl::ImgImportByFileList
//       : [in] LPCTSTR szFrontIdentity - 参见 CJBFImgMngCtrl::ImgImportByFileList
//       : [in] short nIdentityStartPos - 参见 CJBFImgMngCtrl::ImgImportByFileList
//       : [in] short nIdentityEndPos -   参见 CJBFImgMngCtrl::ImgImportByFileList
// 说  明:
CJbfImageMng.prototype.ImgImportByPath = function (szFilePath, nSortByName, nFrontIdentityType, szFrontIdentity, nIdentityStartPos, nIdentityEndPos) {
    return this.obj().ImgImportByPath(szFilePath, nSortByName, nFrontIdentityType, szFrontIdentity, nIdentityStartPos, nIdentityEndPos);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::ChoosePath
// 功  能: 选择路径
// 返  回: BSTR  - 选择的目录
// 参  数: [] LPCTSTR szCaption - 标题
//       : [] LPCTSTR szTitle - 提示信息
//       : [] LPCTSTR szInitialPath - 初始路径
CJbfImageMng.prototype.ChoosePath = function (szCaption, szTitle, szInitialPath) {
    return this.obj().ChoosePath(szCaption, szTitle, szInitialPath);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::ImgSwapByIndex
// 功  能: 交换
// 返  回: short  -
// 参  数: [] long nIndex1 -
//       : [] long nIndex2 -
//       : [] short nFlag - 是否刷新
// 1.0.4.12 以后的版本不提供了
CJbfImageMng.prototype.ImgSwapByIndex = function (nIndex1, nIndex2, nFlag) {
    return this.obj().ImgSwapByIndex(nIndex1, nIndex2, nFlag);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::TwainSwapFrontBack
// 功  能: 交换
// 返  回:
// 参  数:
//
CJbfImageMng.prototype.TwainSwapFrontBack = function () {
    return this.obj().TwainSwapFrontBack();
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJbfImageMng::SetScanFrontAndBackJPEGQuality
// 功  能: 设置正反面的压缩质量
// 返  回: void  -
// 参  数: [in] short frontQuality - 正面的 10 - 100
//      : [in] short backQuality -  反面的 10 - 100
CJbfImageMng.prototype.SetScanFrontAndBackJPEGQuality = function (frontQuality, backQuality) {
    this.obj().SetScanFrontAndBackJPEGQuality(frontQuality, backQuality);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJbfImageMng::EnableAccelerateKey
// 功  能: 允许使用键盘快捷键
// 返回值: void   -
// 参  数: 无
// 说  明:
CJbfImageMng.prototype.EnableAccelerateKey = function () {
    this.obj().EnableAK();
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJbfImageMng::DisableAccelerateKey
// 功  能: 禁用键盘快捷键
// 返回值: void   -
// 参  数: 无
// 说  明: 注意，在有扫描控件的页面中，使用其他的弹出页面进行录入信息时，
// 		  要调用此函数，否则快捷键仍然生效.
CJbfImageMng.prototype.DisableAccelerateKey = function () {
    this.obj().DisableAK();
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJbfImageMng::GetFileSizeByIndex
// 功  能: 通过索引顺序获得文件实际大小，不是需要的存储空间
// 返回值: long   -
// 参  数: [in] long nIndexInPagelist - 在列表中的序号
// 说  明:
CJbfImageMng.prototype.GetFileSizeByIndex = function (nIndexInPagelist) {
    return this.obj().GetFileSizeByIndex(nIndexInPagelist);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJbfImageMng::GetFileSizeByFilepath
// 功  能: 通过文件名称获得文件实际大小，不是需要的存储空间
// 返回值: long   -
// 参  数: [in] LPCTSTR szFilePath - 文件全路径
// 说  明:
CJbfImageMng.prototype.GetFileSizeByFilepath = function (szFilePath) {
    return this.obj().GetFileSizeByFilepath(szFilePath);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJbfImageMng::GetImageWidthByIndex
// 功  能: 通过索引顺序获得图像的宽度，以像素点返回
// 返回值: long   -
// 参  数: [in] long nIndexInPagelist - 在列表中的序号
// 说  明:
CJbfImageMng.prototype.GetImageWidthByIndex = function (nIndexInPagelist) {
    return this.obj().GetImageWidthByIndex(nIndexInPagelist);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJbfImageMng::GetImageHeightByIndex
// 功  能: 通过索引顺序获得图像的高度，以像素点返回
// 返回值: long   -
// 参  数: [in] long nIndexInPagelist - 在列表中的序号
// 说  明:
CJbfImageMng.prototype.GetImageHeightByIndex = function (nIndexInPagelist) {
    return this.obj().GetImageHeightByIndex(nIndexInPagelist);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJbfImageMng::GetImageWidthByFilepath
// 功  能: 通过文件名称获得图像的宽度，以像素点返回
// 返回值: long   -
// 参  数: [in] LPCTSTR szFilePath - 文件全路径
// 说  明:
CJbfImageMng.prototype.GetImageWidthByFilepath = function (szFilepath) {
    return this.obj().GetImageWidthByFilepath(szFilepath);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJbfImageMng::GetImageHeightByFilepath
// 功  能: 通过文件名称获得图像的高度，以像素点返回
// 返回值: long   -
// 参  数: [in] LPCTSTR szFilePath - 文件全路径
// 说  明:
CJbfImageMng.prototype.GetImageHeightByFilepath = function (szFilepath) {
    return this.obj().GetImageHeightByFilepath(szFilepath);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::SetAccelerate
// 功  能: 设置快捷键
// 创  建: 2009-2-13 18:55:21 by bd.zheng@northking.net stdio_01@163.com
// 修  改:
// 返回值: void   -
// 参  数: [in] long commandId -  工具条的ID值  为0，弹出系统对话框
//       : [in] LPCTSTR accelerateKey - 快捷键， 形如"Ctrl+Alt+Shift+A"
// 说  明:
CJbfImageMng.prototype.SetAccelerateKey = function (id, accelerateKey) {
    this.obj().SetAccelerate(id, accelerateKey);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::SetAcceleratePaneFlag
// 功  能: 设置显示工具条标志
// 创  建: 2009-2-13 19:04:59 by bd.zheng@northking.net stdio_01@163.com
// 修  改:
// 返回值: void   -
// 参  数: [] long nFlag -  0  默认，自动确定，1 永远显示  2 永远不显示
// 说  明:
CJbfImageMng.prototype.SetAcceleratePaneFlag = function (nFlag) {
    this.obj().SetAcceleratePaneFlag(nFlag);
}
//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::ImgPageZoomOut
// 功  能: 页放大
// 创  建: 2009-1-19 11:09:59 by bd.zheng@northking.net stdio_01@163.com
// 修  改:
// 返回值: long  - 详细见ImgPageZoom
// 参  数: [in] short row - 未使用
//       : [in] short col - 未使用
// 说  明:
CJbfImageMng.prototype.ImgPageZoomOut = function (row, col) {
    return this.obj().ImgPageZoomOut(row, col);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::ImgPageZoomIn
// 功  能: 页缩小
// 创  建: 2009-1-19 11:09:59 by bd.zheng@northking.net stdio_01@163.com
// 修  改:
// 返回值: long   -  详细见ImgPageZoom
// 参  数: [in] short row - 未使用
//       : [in] short col - 未使用
// 说  明:
CJbfImageMng.prototype.ImgPageZoomIn = function (row, col) {
    return this.obj().ImgPageZoomIn(row, col);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::ImgPageZoom
// 功  能: 页放大缩小
// 创  建: 2009-01-17 10:24:56 by bd.zheng@northking.net stdio_01@163.com
// 修  改:
// 返回值: long   - 高16位返回rows , 低16位返回cols
// 参  数: [in] short row - 行数增加，如果为负就减少
//       : [in] short col - 列数增加，如果为负就减少
// 说  明:
CJbfImageMng.prototype.ImgPageZoom = function (row, col) {
    return this.obj().ImgPageZoom(row, col);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::GetScanPath
// 功  能: 获取扫描缓存文件夹路径

// 创  建: 2009-01-17 10:24:56 by bd.zheng@northking.net stdio_01@163.com
// 修  改:
// 返回值: String   路径
// 说  明:
CJbfImageMng.prototype.GetScanPath = function () {
    return this.obj().ImgGetPathNameForSave();
}
//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::SetAttachShowMsg
// 功  能: 设置附加显示信息
// 创  建: 2009-6-26 11:34:11 by bd.zheng@northking.net stdio_01@163.com
// 修  改:
// 返回值: void   -
// 参  数: [] LPCTSTR szMessage - 显示信息，最长10个字，第一个字节是颜色值，RGB 0B00000111 代表RGB(255,255,255)  0B00000101 代表RGB(255,0,255)。//       : [] long nIndex - 索引号，基于0
//       : [] short bReDraw - 是否立即重画
// 说  明: SetAttachShowMsg("7主件", 1, true);
CJbfImageMng.prototype.SetAttachShowMsg = function (szMessage, nIndex, bReDraw) {
    this.obj().SetAttachShowMsg(szMessage, nIndex, bReDraw);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::SetCommFlag
// 功  能: 设置删除影像前是否询问

// 修  改:
// 返回值: void
// 参  数: [] long value - 值
//       : [] short flag -  标志位
// 说  明:
CJbfImageMng.prototype.SetCommFlag = function (value, nFlag) {
    return this.obj().SetCommFlag(value, nFlag);
}

//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::GetAttachShowMsg
// 功  能: 返回附加信息
// 创  建: 2009-6-26 11:29:18 by bd.zheng@northking.net stdio_01@163.com
// 修  改:
// 返回值: BSTR   -  OK开头成功的，否则失败的
// 参  数: [] long lIndex - 索引号，基于0
//       : [] short flag -  参考SetAttachShowMsg
// 说  明:
CJbfImageMng.prototype.GetAttachShowMsg = function (lIndex, flag) {
    return this.obj().GetAttachShowMsg(lIndex, flag);
}


CJbfImageMng.prototype.PutHttpFiles = function (host, port, url, lpath, nFlag) {
    return this.obj().PutHttpFiles(host, port, url, lpath, nFlag);
}


//////////////////////////////////////////////////////////////
//名称   ImgSave
//功能   保存图像

CJbfImageMng.prototype.ImgSave = function () {
    this.obj().ImgSave2("11111", "22222222", 1);
}


//////////////////////////////////////////////////////////////////////
// 函数名: CJBFImgMngCtrl::RenameSelectedImg
// 功  能: 对选中的图像重命名
// 创  建: 2014-11-25 9:32:20 by xb.zhang@northking.net
// 修  改:
// 返回值: true  - 重命名成功
//         false - 重命名失败
// 参  数: [] newName - 新的命名
// 说  明:
CJbfImageMng.prototype.RenameSelectedImg = function (newName) {
    if ((newName == null) || (newName == '')) {
        alert("请输入有效的图像名称！");
        return false;
    }

    // 获取路径
    var pathName;
    pathName = this.obj().ImgGetPathNameForSave();
    // 获取图像序号
    var index;
    index = this.obj().ImgGetCurrentImageIndex();

    // 获取图像名称（包含路径）
    var name;
    name = this.obj().ImgGetFileNameByIndex(index);
    if (name == '') {
        alert("没有选择的图像！");
        return false;
    }

    // 获取图像类型
    var arr = new Array;
    arr = name.split(".");
    var type;
    type = arr[arr.length - 1];

    // 拼接图像全名称（包含路径）
    var fullName;
    fullName = pathName + newName + '.' + type;

    // 重命名
    try {
        var ret = this.obj().ImgRenameEx(index, fullName);
        if (ret == 1) {
            return true;
        } else {
            return false;
        }
    } catch (e) {
        alert("重命名错误：" + e);
        return false;
    }

}
CJbfImageMng.prototype.RenameAllImg = function () {

    var count = parseInt(this.GetImagesCount());
    var timestamp = parseInt((new Date()).valueOf());
    // 获取路径
    var pathName = this.obj().ImgGetPathNameForSave();
    for (var i = 0; i <= count; i++) {
        var newName = timestamp + i;
        // 获取图像序号
        var index;
        index = i;
        // 获取图像名称（包含路径）
        var name;
        name = this.obj().ImgGetFileNameByIndex(index);
        if (name == '') {
            alert("没有选择的图像！");
            return false;
        }
        // 获取图像类型
        var arr = new Array;
        arr = name.split(".");
        var type;
        type = arr[arr.length - 1];

        // 拼接图像全名称（包含路径）
        var fullName;
        fullName = pathName + newName + '.' + type;

        // 重命名
        try {
            var ret = this.obj().ImgRenameEx(index, fullName);

        } catch (e) {
            alert("重命名错误：" + e);
            return false;
        }


    }


}


//////////////////////////////////////////////////////////////
//名称   SetVisualWMString
//功能   设置可视水印内容
CJbfImageMng.prototype.SetVisualWMString = function (strWM) {
    //alert(strWM);
    this.obj().SetVisualWMString(strWM);
}


//////////////////////////////////////////////////////////////
//名称   EnableInsertWM
//功能   设置可插入水印
CJbfImageMng.prototype.EnableInsertWM = function (bEnable) {
    this.obj().EnableInsertWM(bEnable);
}

//////////////////////////////////////////////////////////////
//名称   SetVisualWMLocation
//功能   设置水印位置 0代表左上角 1代表右上角 2代表左下角 3代表右下角
CJbfImageMng.prototype.SetVisualWMLocation = function (nWMLocation) {
    this.obj().SetVisualWMLocation(nWMLocation);
}

//////////////////////////////////////////////////////////////
//名称   GetVisualWMString
//功能   获取可视水印内容
CJbfImageMng.prototype.GetVisualWMString = function (strWM) {

    var ret = this.obj().GetVisualWMString(strWM);
    //alert(strWM);
    alert(ret);
}


//////////////////////////////////////////////////////////////
//名称   IsEnableInsertWM
//功能   获取是否设置插入水印
CJbfImageMng.prototype.IsEnableInsertWM = function (bEnable) {
    this.obj().IsEnableInsertWM(bEnable);
}

//////////////////////////////////////////////////////////////
//名称   GetVisualWMLocation
//功能   获取水印位置 0代表左上角 1代表右上角 2代表左下角 3代表右下角
CJbfImageMng.prototype.GetVisualWMLocation = function (nWMLocation) {
    this.obj().GetVisualWMLocation(nWMLocation);
}


//////////////////////////////////////////////////////////////
//名称   SetWMTextColor
//功能   设置水印文字的颜色（DWORD）
CJbfImageMng.prototype.SetWMTextColor = function (wmTexColor) {
    this.obj().SetWMTextColor(wmTexColor);
}

//////////////////////////////////////////////////////////////
//名称   GetWMTextColor
//功能   获取水印文字的颜色（DWORD）（BGR格式，eg:0xff0000 为蓝色）
CJbfImageMng.prototype.GetWMTextColor = function (wmTexColor) {
    this.obj().GetWMTextColor(wmTexColor);
}

//////////////////////////////////////////////////////////////
//名称   generateImgSmall
//功能   生成缩略图（在原有的图像后加_small,eg:00000001.jpg--->00000001_small.jpg）
//国开行中提交传输影像时调用生成对应的缩略图
CJbfImageMng.prototype.generateImgSmall = function () {
    this.obj().generateImgSmall();
}
//////////////////////////////////////////////////////////////
//名称   getFileWidthHeight
//功能   获取扫描影像的宽高，以字符串的格式返回,每张影像以‘|’分割（影像名称，宽，高）（eg:c:\sdi\00000001.jpg,1653,2356|c:\sdi\00000002.jpg,1653,2356|）
//针对国开行添加的接口
CJbfImageMng.prototype.getFileWidthHeight = function () {
    return this.obj().getFileWidthHeight();
}