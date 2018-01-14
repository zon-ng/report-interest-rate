package sg.gov.mom.report.view;

import java.util.List;

import sg.gov.mom.report.result.IRateReportResult;

public abstract class RateComparisonView {
	public List<IRateReportResult> iRateReportResultList;

	public RateComparisonView(List<IRateReportResult> iRateReportResultList) {
		this.iRateReportResultList = iRateReportResultList;
	}

	abstract public void displayResult();
}
