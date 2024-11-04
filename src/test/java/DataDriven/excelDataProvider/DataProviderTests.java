package DataDriven.excelDataProvider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class DataProviderTests {

	@Test
	public void getExcel() throws IOException {
		// Multiple sets of data to our tests
		// array public
		// 5 sets of data as 5 arrays from data provider to your tests
		// then your test will run 5 times with 5 separate sets of data (arrays)

		// Object[][] data = { { "Hello", "Test", "1" }, { "Bye", "message", "2" }, {
		// "Solo", "Call", "3" } };

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
				System.out.println(sheetRow.getCell(j));
			}

		}
	}

}
