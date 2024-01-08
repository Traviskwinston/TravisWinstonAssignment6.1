package Assignment6;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class TeslaSalesData {
	
	private YearMonth date;
	private Integer sales;
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM-yy");
	
	public TeslaSalesData(String date, String sales) {
		this.date = YearMonth.parse(date,dateFormatter);
		this.sales = Integer.parseInt(sales);
	}
	
	public YearMonth getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = YearMonth.parse(date,dateFormatter);
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}
}
