package sg.gov.mom.report.result;

public interface IMonthlyRate {
	public int getMonth();
	public int getYear();
	public float getBankRate();
	public float getFinancialCompanyRate();
}
