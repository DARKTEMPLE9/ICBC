package net.northking.iacmp.utils;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

public class MyExcelUtil {
    protected static final Logger logger = LoggerFactory.getLogger(MyExcelUtil.class);

    /**
     * 档案总量，移交报表
     */
    public HSSFWorkbook getHSSFWorkbook(List<List> rowsList, String title) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet");
        //设置样式
        CellStyle blackStyle = workbook.createCellStyle();
        //自动换行*重要*
        blackStyle.setWrapText(true);
        //居中
        blackStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        //字体大小
        font.setFontHeightInPoints((short) 22);
        //列宽
        sheet.setDefaultColumnWidth(15);

        HSSFRow row;
        HSSFCell cell;
        //共计列数
        int culomnCount = rowsList.get(0).size() - 1;
        //标题行
        HSSFRow row0 = sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, culomnCount));
        HSSFCell cell0 = row0.createCell(0);
        cell0.setCellValue(title);
        row0.setHeightInPoints(50);
        cell0.setCellStyle(blackStyle);
        cell0.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        cell0.getCellStyle().setFont(font);
        //垂直居中
        cell0.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
        //单位
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell1 = row1.createCell(0);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, culomnCount));
        cell1.setCellValue("统计单位：件/卷");
        row1.setHeightInPoints(20);
        cell1.getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
        //垂直居中
        cell1.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
        //表
        for (int i = 0; i < rowsList.size(); i++) {
            //创建行
            row = sheet.createRow(i + 2);
            row.setHeightInPoints(20);
            List rows = rowsList.get(i);
            for (int j = 0; j < rows.size(); j++) {
                //创建行内单元格
                cell = row.createCell(j);
                cell.setCellValue((String) rows.get(j));
                cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                //垂直居中
                cell.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
            }
        }

        return workbook;
    }

    /**
     * 档案利用报表
     */
    public HSSFWorkbook getHSSFWorkbookGroupByBorType(List<List> rowsList, String title) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet");
        //设置样式
        CellStyle blackStyle = workbook.createCellStyle();
        //自动换行*重要*
        blackStyle.setWrapText(true);
        //居中
        blackStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        //字体大小
        font.setFontHeightInPoints((short) 22);
        //列宽
        sheet.setDefaultColumnWidth(10);
        HSSFRow row;
        HSSFCell cell;
        //共计列数
        int culomnCount = rowsList.get(2).size() - 1;
        //标题行
        HSSFRow row0 = sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, culomnCount));
        HSSFCell cell0 = row0.createCell(0);
        cell0.setCellValue(title);
        row0.setHeightInPoints(50);
        cell0.setCellStyle(blackStyle);
        cell0.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        cell0.getCellStyle().setFont(font);
        //垂直居中
        cell0.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
        //单位
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell1 = row1.createCell(0);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, culomnCount));
        cell1.setCellValue("统计单位：件/卷");
        row1.setHeightInPoints(20);
        cell1.getCellStyle().setAlignment(HorizontalAlignment.RIGHT);
        //垂直居中
        cell1.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
        //第三行档案类型
        HSSFRow row2 = sheet.createRow(2);
//        cell = row2.createCell(0);
        row2.setHeightInPoints(20);
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 0, 0));
        List arcType = rowsList.get(0);

        for (int i = 0; i <= culomnCount; i = i + 2) {
            sheet.addMergedRegion(new CellRangeAddress(2, 2, i + 1, i + 2));
        }
        int k = 1;
        for (int i = 1; i <= culomnCount; i = i + 2) {
//            sheet.addMergedRegion(new CellRangeAddress(2, 2, i+1, i+2));
            cell = row2.createCell(i);
            cell.setCellValue((String) arcType.get(k));
            k++;
            cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
            //垂直居中
            cell.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
        }
        //第四行利用类型
        HSSFRow row3 = sheet.createRow(3);
//        cell=row3.createCell(0);
        row3.setHeightInPoints(20);
        List borType = rowsList.get(1);
        for (int i = 0; i < borType.size(); i++) {
            cell = row3.createCell(i + 1);
            cell.setCellValue((String) borType.get(i));
            cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
            //垂直居中
            cell.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
        }
        //表
        for (int i = 2; i < rowsList.size(); i++) {
            //创建行
            row = sheet.createRow(i + 2);
            row.setHeightInPoints(20);
            List rows = rowsList.get(i);
            for (int j = 0; j < rows.size(); j++) {
                //创建行内单元格
                cell = row.createCell(j);
                cell.setCellValue((String) rows.get(j));
                cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                //垂直居中
                cell.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
            }
        }

//        workbook.write(file);
        return workbook;
    }

    public void exportExc(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
//        HSSFWorkbook workbook=getHSSFWorkbook( rowsList);
        OutputStream out = null;
        try {
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            String downloadFileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFileName);
            out = response.getOutputStream();
            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

}
