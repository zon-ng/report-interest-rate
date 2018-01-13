package sg.gov.mom.report.factory;

import sg.gov.mom.report.facade.RateReportFacade;

public abstract class ReportFactory {
	public abstract RateReportFacade createReportFacade();
}
