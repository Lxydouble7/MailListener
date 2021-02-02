package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class WriteExcel {

    public static void toExcel(String FilePath,ArrayList<ArrayList<String>> ans)
            throws IOException, RowsExceededException, WriteException, BiffException {
        Workbook wb = Workbook.getWorkbook(new File(FilePath));
        WritableWorkbook workbook = Workbook.createWorkbook(new File(FilePath),wb);
        WritableSheet sheet = workbook.getSheet(0);
        //5:单元格
        Label label=null;
        int row = sheet.getRows();
        //System.out.println(row);
        int i = 0;
        for(ArrayList<String> temp : ans){
            int j = 0;
            for(String word: temp){
                label=new Label(j,i+row,word);
                sheet.addCell(label);
                j++;

            }
            i++;
        }
        workbook.write();
        workbook.close();
    }

}