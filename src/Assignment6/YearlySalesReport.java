package Assignment6;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;

public class YearlySalesReport {

	public static void main(String[] args) throws ParseException {

		runReport("Model 3", "model3.csv");

		runReport("Model S", "modelS.csv");

		runReport("Model X", "modelX.csv");
		
	}
	
	public static void runReport(String model, String fileName) {
		FileService fileService = new FileService();
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		ArrayList<TeslaSalesData> fileData = fileService.addInfoFromFile(fileName);
		//I could just do a for Loop for 2016 to 2020, but I like the practice making
		//the list more dynamic in case it covers a wider or narrower span of years.
		int minYear = fileData.stream()
	            			  .mapToInt(data -> data.getDate()
	            					  				.getYear())
	            			  .min()
	            			  .orElse(0);

	    int maxYear = fileData.stream()
	            			  .mapToInt(data -> data.getDate()
	            					  				.getYear())
	            			  .max()
	            			  .orElse(0);
		
		System.out.println(model +" Yearly Sales Report");
		System.out.println("---------------------------");
	    for (int i = minYear; i <= maxYear; i++) {
	    	int year = i;
	        int sales = fileData.stream()
					.filter(e -> e.getDate()
								  .getYear() == year)//Getting error when I use "i" here?
					.mapToInt(TeslaSalesData::getSales)
					.sum();
			if (sales != 0) {
				System.out.println(year + " -> " + currencyFormatter.format(sales));
			}
		}
		//Sort the list from Big to Small.
		Collections.sort(fileData, new SalesComparator());
		
		try {
			YearMonth bestDate = fileData.get(0).getDate();
			YearMonth worstDate = fileData.get(fileData.size() -1).getDate();
			
			System.out.println("\nThe best month for " + model + " was: " + bestDate);
			System.out.println("The worst month for " + model + " was: " + worstDate + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
