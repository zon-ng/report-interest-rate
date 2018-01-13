package sg.gov.mom.report.result;

import java.util.List;

public interface IRateReportResult {
	
	String getDepositType();
	
	List<IMonthlyRate> getMonthlyRateList();
	
	float getAvgBankRate();
	float getAvgFinancialCompanyRate();
}
