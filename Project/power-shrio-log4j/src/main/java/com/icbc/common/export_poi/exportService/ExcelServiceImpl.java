package com.icbc.common.export_poi.exportService;

import com.icbc.common.export_poi.entity.Export_a;
import com.icbc.common.export_poi.entity.Export_a_1;
import com.icbc.common.export_poi.entity.Export_a_1_1;
import com.icbc.common.export_poi.exportMapper.ExcelMapper;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ExcelMapper excelMapper;

    @Override
    public void excel() {
        String[] headers = {"主键", "姓名", "性别", "身高",
                "EXPORT_A_1_主键", "EXPORT_A_1_关联EXPORT_A的主键", "EXPORT_A_1_备注", "EXPORT_A_1_数量",
                "EXPORT_A_1_1_主键", "EXPORT_A_1_关联EXPORT_A的主键", "EXPORT_A_1_1_的主键", "EXPORT_A_1_备注", "EXPORT_A_1_数量"
        };
        String title = "测试";

// 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(20);
        // 生成一个样式
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(new XSSFColor(Color.gray));
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        XSSFFont font = workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setColor(new XSSFColor(Color.BLACK));
        font.setFontHeightInPoints((short) 11);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        XSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(new XSSFColor(Color.WHITE));
        style2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        XSSFFont font2 = workbook.createFont();
        font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        XSSFCell cellHeader;
        for (int i = 0; i < headers.length; i++) {
            cellHeader = row.createCell(i);
            cellHeader.setCellStyle(style);
            cellHeader.setCellValue(new XSSFRichTextString(headers[i]));
        }

        /*
         * 生成表格内容
         * */

        //获取数据
        //产生数据行
        int row_index = 1; //行下标
        int lie_index = 0; //列下标
        int count = 0;
        int count_2 = 0;
        List<Export_a> list_a = excelMapper.queryExcel();
        /*一级数据*/
        for (Export_a export_a : list_a) {
            String row_id = export_a.getRow_id();
            List<Export_a_1> export_a_1List = excelMapper.queryExcel_a_1(row_id);
            count = 0;
            /*二级数据*/
            for (Export_a_1 export_a_1 : export_a_1List) {
                String row_id1 = export_a_1.getRow_id();
                List<Export_a_1_1> export_a_1_1List = excelMapper.queryExcel_a_1_1(row_id1);
                count_2 = 0;
                /*三级数据*/
                for (Export_a_1_1 export_a_1_1 : export_a_1_1List) {

                    XSSFRow row1 = sheet.createRow(row_index);//创建行
                    XSSFCell cell = row1.createCell(0);//创建单元格,从零开始 可用于合并行

                    if (count == 0) {
                        /*一级菜单数据*/
                        cell.setCellValue(export_a.getRow_id());//设置内容
                        row1.createCell(1).setCellValue(export_a.getName());
                        row1.createCell(2).setCellValue(export_a.getSex());
                        row1.createCell(3).setCellValue(export_a.getHeight());
                        CellRangeAddress region = new CellRangeAddress(1, 4, 1, 4);
                        sheet.addMergedRegion(region);
                        count = 1;
                    }
                    if (count_2 == 0) {
                        /*二级菜单数据*/
                        row1.createCell(4).setCellValue(export_a_1.getRow_id());
                        row1.createCell(5).setCellValue(export_a_1.getExport_a_row_id());
                        row1.createCell(6).setCellValue(export_a_1.getRemark());
                        row1.createCell(7).setCellValue(export_a_1.getNumber());
                        count_2 = 1;
                    }




                    /*三级菜单数量*/
                    row1.createCell(8).setCellValue(export_a_1_1.getRow_id());
                    row1.createCell(9).setCellValue(export_a_1_1.getExport_a_row_id());
                    row1.createCell(10).setCellValue(export_a_1_1.getExport_a_1_row_id());
                    row1.createCell(11).setCellValue(export_a_1_1.getRemark());
                    row1.createCell(12).setCellValue(export_a_1_1.getNumber());
                    row_index++;
                }
            }
        }


        //产生数据行
        /*int row_index = 1; //行下标
        int lie_index = 0; //列下标

        for (int i = 0; i < list_a.size(); i++) {
            XSSFRow row1 = sheet.createRow(row_index);// 创建行
            XSSFCell cell = row1.createCell(lie_index);// 创建行的单元格,也是从0开始
            cell.setCellValue(list_a.get(i).getRow_id());// 设置单元格内容
            row1.createCell(1).setCellValue(list_a.get(i).getName());
            row1.createCell(2).setCellValue(list_a.get(i).getSex());
            row1.createCell(3).setCellValue(list_a.get(i).getHeight());
            row_index++;
            //lie_index++;
        }*/

        try {
            //输出到磁盘中
            FileOutputStream fos = new FileOutputStream(new File("E:\\root\\sheet\\11.xlsx"));
            workbook.write(fos);
            workbook.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
