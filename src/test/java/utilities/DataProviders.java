package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//Data provider for login data
	
	@DataProvider(name = "LoginData")
	public static String[][] getLoginData() throws Exception {
	    String path = System.getProperty("user.dir") + "\\TestData\\Opencart_LoginData.xlsx";
	    ExcelUtility xlutil = new ExcelUtility(path);

	    int totalrowCount = xlutil.getRowCount("Sheet1");
	    int totalcellCount = xlutil.getCellCount("Sheet1", 1);

	    String loginData[][] = new String[totalrowCount][totalcellCount];

	    for (int i = 1; i <= totalrowCount; i++) {      // ✅ <= not 
	        for (int j = 0; j < totalcellCount; j++) {
	            loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j);
	        }
	    }

	    return loginData;
	}
	
	
	//Data provider 2
	
	//Data provider 3
	

}
