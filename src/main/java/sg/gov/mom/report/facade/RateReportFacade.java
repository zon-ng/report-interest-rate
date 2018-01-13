package sg.gov.mom.report.facade;

import java.util.List;

import sg.gov.mom.report.result.IRateReportResult;

public interface RateReportFacade {
	List<IRateReportResult> generateRateReport(String dateCriteria);
}
