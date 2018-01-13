package sg.gov.mom.report.result;

import java.util.List;

/**
 * RateReportResult is the model that stores the data for display on the
 * presentation layer
 *
 */
public class RateReportResult implements IRateReportResult {
	private String depositType;
	List<IMonthlyRate> monthlyRateList;

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public List<IMonthlyRate> getMonthlyRateList() {
		return monthlyRateList;
	}

	public void setMonthlyRateList(List<IMonthlyRate> monthlyRateList) {
		this.monthlyRateList = monthlyRateList;
	}

	public void addMonthlyRateList(MonthlyRate monthlyRate) {
		monthlyRateList.add(monthlyRate);
	}

	public float getAvgBankRate() {
		float avgBankRate = 0.0f;
		float totalBankRate = 0.0f;

		if (monthlyRateList != null) {
			for (IMonthlyRate monthlyRate : monthlyRateList) {
				totalBankRate += monthlyRate.getBankRate();
			}
			avgBankRate = totalBankRate / monthlyRateList.size();
		}

		return avgBankRate;
	}

	public float getAvgFinancialCompanyRate() {
		float avgFinancialCompanyRate = 0.0f;
		float totalFinancialCompanyRate = 0.0f;

		if (monthlyRateList != null) {
			for (IMonthlyRate monthlyRate : monthlyRateList) {
				totalFinancialCompanyRate += monthlyRate.getFinancialCompanyRate();
			}
			avgFinancialCompanyRate = totalFinancialCompanyRate / monthlyRateList.size();
		}

		return avgFinancialCompanyRate;
	}
}
