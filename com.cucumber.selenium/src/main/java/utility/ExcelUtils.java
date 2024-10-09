package utility;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

public class ExcelUtils {

	public HashMap<String,  List<String>> readData(String path, String sheetName){ 

		HashMap<String, List<String>> excelDataMap;
		excelDataMap = new HashMap<String, List<String>>();
		try {
			FileInputStream file = new FileInputStream(path);
			XSSFWorkbook wb = new XSSFWorkbook(file); 
			XSSFSheet sh = wb.getSheet(sheetName);

			int numOfRows = sh.getLastRowNum();
			for(int i=0; i<=numOfRows; i++) {
				int cellLen = sh.getRow(i).getLastCellNum();
				List<String> rowData = new ArrayList<String>();
				for(int j=1; j<cellLen; j++) {
					rowData.add(sh.getRow(i).getCell(j).getStringCellValue());
				}
				excelDataMap.put(sh.getRow(i).getCell(0).getStringCellValue(), rowData);
			}
			wb.close(); 
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return excelDataMap;
	}
}
