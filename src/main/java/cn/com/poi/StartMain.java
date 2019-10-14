package cn.com.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StartMain {

    public static void main(String[] args ) throws Exception{
        File file = new File("C:\\Users\\Administrator\\Desktop\\日前负荷段划分test.xls");
        InputStream inputStream = new FileInputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = null;
        sheet = workbook.getSheetAt(0);
        int row = sheet.getLastRowNum();
        HSSFRow rowObject = null;
        List<Date> timeList = new ArrayList();
        List<String> electricList = new ArrayList();
        for(int i=1;i<=row;i++){
            rowObject =  sheet.getRow(i);

            int lastCellNum = rowObject.getLastCellNum();
            if(i == 1){
                System.out.print("时刻:");
            }else if(i == 2){
                System.out.print("计划负荷:");
            }
            for(int j = 0;j<lastCellNum;j++){
                HSSFCell cell = rowObject.getCell(j);
                if(cell.getCellType() == 0){
                    String dataFormatString = cell.getCellStyle().getDataFormatString();
                    if("General".equals(dataFormatString)){
                        electricList.add(String.valueOf(cell.getNumericCellValue()));
                        System.out.print(" "+cell.getNumericCellValue());
                    }else{
                        timeList.add(cell.getDateCellValue());
                        System.out.print(" "+cell.getDateCellValue().getHours()+":"+cell.getDateCellValue().getMinutes());
                    }
                }
                System.out.print("");
            }
            System.out.println("");
        }

        System.out.println(timeList);
        System.out.println(electricList);
    }
}
