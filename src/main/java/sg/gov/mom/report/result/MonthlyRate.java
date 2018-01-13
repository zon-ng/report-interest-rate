package sg.gov.mom.report.result;

/**
 * MonthlyRate is the model that holds the each month's bank and financial
 * company rates
 *
 */
public class MonthlyRate implements IMonthlyRate {
	private int month;
	private int year;
	private float bankRate;
	private float financialCompanyRate;

	public MonthlyRate(int month, int year, float bankRate, float financialCompanyRate) {
		super();
		this.month = month;
		this.year = year;
		this.bankRate = bankRate;
		this.financialCompanyRate = financialCompanyRate;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public float getBankRate() {
		return bankRate;
	}

	public void setBankRate(float bankRate) {
		this.bankRate = bankRate;
	}

	public float getFinancialCompanyRate() {
		return financialCompanyRate;
	}

	public void setFinancialCompanyRate(float financialCompanyRate) {
		this.financialCompanyRate = financialCompanyRate;
	}

}
