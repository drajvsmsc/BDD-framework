



import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class de {
    //apache poi excel read method
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("C:\\Users\\HP\\Desktop\\TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int rowcount = sheet.getLastRowNum();
        int colcount = sheet.getRow(0).getLastCellNum();
        System.out.println(rowcount);
        System.out.println(colcount);
        for (int i = 0; i < rowcount; i++) {
            for (int j = 0; j < colcount; j++) {
                String data = sheet.getRow(i).getCell(j).getStringCellValue();
                System.out.println(data);
            }
        }
    
    





}
