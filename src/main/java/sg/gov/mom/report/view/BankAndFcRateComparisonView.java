package sg.gov.mom.report.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import sg.gov.mom.report.result.IMonthlyRate;
import sg.gov.mom.report.result.IRateReportResult;

/**
 * BankAndFcRateComparisonView prints out in table format on console for ease of
 * comparing bank and financial company rates. It also prints out the overall
 * average on the last column of the report.
 *
 */
public class BankAndFcRateComparisonView extends RateComparisonView {
	private final SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");

	public BankAndFcRateComparisonView(List<IRateReportResult> iRateReportResultList) {
		super(iRateReportResultList);
	}

	public void displayResult() {
		System.out.println("");
		System.out.println(
				"Bank and Financial Company Rate Comparison Report is generated as follows (Overall Average displayed on the last column):\n");
		for (IRateReportResult iRateReportResult : iRateReportResultList) {
			System.out.println("[" + iRateReportResult.getDepositType() + "]");
			String monthHeader = "\t\t\t";
			String bankRates = "Bank Rates\t\t";
			String fcRates = "Financial Company Rates\t";
			for (IMonthlyRate iMonthlyRate : iRateReportResult.getMonthlyRateList()) {
				Calendar calendar = new GregorianCalendar(iMonthlyRate.getYear(), iMonthlyRate.getMonth() - 1, 1);

				monthHeader += sdf.format(calendar.getTime()) + "\t\t";
				bankRates += iMonthlyRate.getBankRate()
						+ (iMonthlyRate.getBankRate() > iMonthlyRate.getFinancialCompanyRate() ? "(Higher)" : "\t")
						+ "\t\t";
				fcRates += iMonthlyRate.getFinancialCompanyRate()
						+ (iMonthlyRate.getFinancialCompanyRate() > iMonthlyRate.getBankRate() ? "(Higher)" : "\t")
						+ "\t\t";
			}
			monthHeader += "Overal Average Rates";
			bankRates += String.format("%,.2f", iRateReportResult.getAvgBankRate());
			fcRates += String.format("%,.2f", iRateReportResult.getAvgFinancialCompanyRate());

			System.out.println(monthHeader);
			System.out.println(bankRates);
			System.out.println(fcRates);
			System.out.println("\n");
		}
	}
}
