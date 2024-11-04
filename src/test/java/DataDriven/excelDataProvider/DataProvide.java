package DataDriven.excelDataProvider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvide {

	DataFormatter dataFormatter = new DataFormatter();

	@Test(dataProvider = "DriveTest")
	public void testCaseData(String greetingString, String communicationString, String id) {
		System.out.println(greetingString + " " + communicationString + " " + id);
	}

	@DataProvider(name = "DriveTest")
	public Object[][] getData() throws IOException {

		FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "/src/resources/ExcelDataProvider.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

		XSSFSheet sheet = workbook.getSheetAt(0);

		int rowCount = sheet.getPhysicalNumberOfRows();

		XSSFRow row = sheet.getRow(0);

		int columnCount = row.getLastCellNum();

		Object dataObjects[][] = new Object[rowCount - 1][columnCount];

		for (int i = 0; i < rowCount - 1; i++) {

			XSSFRow sheetRow = sheet.getRow(i + 1);

			for (int j = 0; j < columnCount; j++) {
				XSSFCell cell = sheetRow.getCell(j);
				dataObjects[i][j] = dataFormatter.formatCellValue(cell);
			}

		}

		return dataObjects;
	}
}