package sg.gov.mom.report.factory;

import sg.gov.mom.report.facade.RateReportFacade;
import sg.gov.mom.report.facade.RateReportFacadeImpl;

public class RateReportFactory extends ReportFactory {
	public RateReportFacade createReportFacade() {
		return new RateReportFacadeImpl();
	}
}
