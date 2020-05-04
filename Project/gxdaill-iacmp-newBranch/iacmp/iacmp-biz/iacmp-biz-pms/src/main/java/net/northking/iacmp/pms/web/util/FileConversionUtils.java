//package net.northking.iacmp.pms.web.util;
//
//import com.aspose.cells.License;
//import com.aspose.cells.PdfSaveOptions;
//import com.aspose.cells.Workbook;
////import com.aspose.slides.Presentation;
////import com.aspose.words.Document;
////import com.aspose.words.SaveFormat;
//import net.northking.iacmp.constant.CmsConstants;
//import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
//import net.northking.iacmp.framework.util.UploadUtil;
//import net.northking.iacmp.utils.NumConstants;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//
//public class FileConversionUtils {
//
//    private static Logger logger = LoggerFactory.getLogger(FileConversionUtils.class);
////    private static int SAVE_OPTION = SaveFormat.PDF;
//    private static InputStream license;
//
//    /**
//     * 文件转化pdf并输出流
//     *
//     * @param response
//     * @param filePath 文件的数据库存放地址
//     * @param fileType 文件类型
//     * @param fileName 文件名
//     */
//    public static void fileToPdf(HttpServletResponse response, String filePath,
//                                 String fileType, String fileName, String hadoopType) {
//        response.setStatus(HttpServletResponse.SC_OK);
//        response.setContentType("application/pdf;charset=UTF-8");
//        ServletOutputStream outputStream = null;
//        BufferedInputStream in = null;
//        String dbPath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
//        // 本地服务器下载地址
//        File savePath = new File(getServerLocalPath() + dbPath);
//        if (!savePath.exists()) {
//            savePath.mkdirs();
//        }
//        try {
//            outputStream = response.getOutputStream();
//            File pdfFile = null;
//            if (fileType != null && "pdf".equals(fileType.toLowerCase())) {
//                pdfFile = new File(getServerPdfLocalPath() + filePath);
//                // 如果pdf文件不存在，则下载到本地
//                if (!pdfFile.exists()) {
//                    boolean b = UploadUtil.downloadTrans(getServerPdfLocalPath() + dbPath,
//                            filePath.substring(filePath.lastIndexOf("/") + 1), filePath + "&hadoopType=1");
//                    if (!b) {
//                        throw new Exception("HDFS下载失败，请联系服务器管理员");
//                    }
//                }
//            } else {
//                File localFile = new File(getServerLocalPath() + filePath);
//                if (!localFile.exists()) {
//                    String[] split = filePath.split("/");
//                    boolean b = UploadUtil.downloadTrans(getServerLocalPath() + dbPath, split[split.length - 1], filePath + "&hadoopType=1");
//                    if (!b) {
//                        throw new Exception("HDFS下载失败，请联系服务器管理员");
//                    }
//                }
//                // 服务器本地的转化的PDF存放路径
//                String pdfPath = getServerPdfLocalPath() + dbPath;
//                File newFile = new File(pdfPath); // 转换之后文件生成的地址
//                if (!newFile.exists()) {
//                    newFile.mkdirs();
//                }
//                String pdfFilePath = pdfPath + fileName.substring(0, fileName.lastIndexOf(".")) + ".pdf";
//                // 要转化的文件
//                pdfFile = new File(pdfFilePath);
//                // 如果已存在，则无需再转化
//                if (!pdfFile.exists()) {
//                    String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
//                    switch (extension) {
//                        case "docx":
//                        case "doc":
////                            docToPdf(localFile.getPath(), pdfFile.getPath());
//                            break;
//                        case "xlsx":
//                        case "xls":
//                            excelToPdf(localFile.getPath(), pdfFile.getPath());
//                            break;
//                        case "pptx":
//                        case "ppt":
////                            pptToPdf(localFile.getPath(), pdfFile.getPath());
//                            break;
//                    }
//                }
//            }
//            //使用response,将pdf文件以流的方式发送的前段
//            in = new BufferedInputStream(new FileInputStream(pdfFile));// 读取文件
//            // 读取生成的PDF文件
//            byte[] b = new byte[NumConstants.NUM_1024];
//            int i = 0;
//            while ((i = in.read(b)) != -1) {
//                outputStream.write(b, 0, b.length);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        } finally {
//            try {
//                in.close();
//                outputStream.flush();
//                outputStream.close();
//            } catch (IOException e) {
//                logger.error(e.getMessage());
//            }
//        }
//    }
//
//    /**
//     * word转pdf
//     *
//     * @param inPath
//     * @param outPath
//     */
////    public static void docToPdf(String inPath, String outPath) {
////        boolean flag = false;
////        long old = System.currentTimeMillis();
////        // 新建一个空白文档
////        File file = new File(outPath);
////        try (FileOutputStream os = new FileOutputStream(file)) {
////            // InPath是将要被转化的文档
////            Document doc = new Document(inPath);
////            /*
////             * 全面支持DOC,DOCX进行OOXML,RTF,HTML,OpenDocument,PDF,EPUB,XPS,SWF间转换
////             */
////            doc.save(os, SAVE_OPTION);
////            flag = true;
////            long now = System.currentTimeMillis();
////            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            if (!flag) file.deleteOnExit();
////        }
////    }
//
//    /**
//     * 获取license
//     *
//     * @return
//     */
//    public static boolean getLicense() {
//        boolean result = false;
//        try {
//            license = new FileInputStream("C:/Users/yinrui/Desktop/license-excel.xml");// 凭证文件
//            License aposeLic = new License();
//            aposeLic.setLicense(license);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    // excel转pdfhttp:
//    public static void excelToPdf(String inPath, String outPath) {
//        boolean flag = false;
//        long old = System.currentTimeMillis();
//        // 新建一个空白文档
//        File file = new File(outPath);
//
//        try (FileOutputStream os = new FileOutputStream(file)) {
//            // 验证License
////            getLicense();
//            // InPath是将要被转化的文档
//            Workbook doc = new Workbook(inPath);
//            PdfSaveOptions option = new PdfSaveOptions();
//            option.setOnePagePerSheet(true);
//            doc.save(os, option);
//            flag = true;
//            long now = System.currentTimeMillis();
//            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (!flag) file.deleteOnExit();
//        }
//    }
//
//    // ppt转pdf
////    public static void pptToPdf(String inPath, String outPath) {
////        boolean flag = false;
////        // 新建一个空白文档
////        File file = new File(outPath);
//////        getLicense();
////        try (FileOutputStream fileOS = new FileOutputStream(file)) {
////            long old = System.currentTimeMillis();
////            Presentation pres = new Presentation(inPath);
////            pres.save(fileOS, com.aspose.slides.SaveFormat.Pdf);
////            flag = true;
////            long now = System.currentTimeMillis();
////            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒\n\n" + "文件保存在:" + file.getPath());
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            if (!flag) file.deleteOnExit();
////        }
////    }
//
//    // 获取本地上传或下载文件的路径 add by yinrui 2019-10-31
//    private static String getServerLocalPath() {
//        return System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0 ? SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_SERVER_PATH) : SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH);
//    }
//
//    // 获取本地存放pdf文件的路径 add by yinrui 2019-10-3
//    private static String getServerPdfLocalPath() {
//        return System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0 ? SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_SERVER_PDF_PATH) : SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PDF_PATH);
//    }
//
//    // 获取系统环境的ip
//    private static String getServerIp() {
//        return System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0 ? SysConfigInitParamsUtils.getConfig(CmsConstants.WINDOWS_FILETRANSIP) : SysConfigInitParamsUtils.getConfig(CmsConstants.FILETRANSIP);
//    }
//
//    public static void main(String[] args) {
////        docToPdf("D:\\pdf\\test.docx","D:\\pdf\\test.pdf");
//        excelToPdf("C:\\Users\\yinrui\\Desktop\\这是一个ppt.pptx", "C:\\Users\\yinrui\\Desktop\\test.pdf");
//    }
//}
