package sg.gov.mom.report;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sg.gov.mom.report.facade.RateReportFacade;
import sg.gov.mom.report.facade.RateReportFacadeImpl;
import sg.gov.mom.report.factory.RateReportFactory;
import sg.gov.mom.report.factory.ReportFactory;
import sg.gov.mom.report.logic.MASInterestRateJson;
import sg.gov.mom.report.result.IRateReportResult;
import sg.gov.mom.report.view.BankAndFcRateComparisonView;
import sg.gov.mom.report.view.RateComparisonView;

/**
 * Generate Bank and Financial Company fixed and saving deposit rates report for comparison
 *
 */
public class GenerateRateReport 
{
    public static void main( String[] args )
    {
    	// Required 1 date to be specified in argument
    	if(args==null || args.length!=1) {
    		System.out.println("Please run the program again indicating the period(s) that you wish the report to be generated. (Period is in the format mmm-yyyy e.g \"Jan-2017,Feb-2017\").");
    		System.out.println("Below is an example of how you could run it:");
    		System.out.println("java sg.gov.mom.report.GenerateRateReport Jan-2017,Feb-2017");
    		System.exit(-1);
    	}
    	
    	// Query MAS and generate fixed and saving deposit rate report for bank and financial company.
    	ReportFactory reportFactory = new RateReportFactory();
        RateReportFacade rateReportFacade = reportFactory.createReportFacade();
        List<IRateReportResult> iRateReportResult = rateReportFacade.generateRateReport(args[0]);
        
        // Send result into view for display
        if(iRateReportResult != null) {
	        RateComparisonView rateComparisonView = new BankAndFcRateComparisonView(iRateReportResult);
	        rateComparisonView.displayResult();
        } else {
        	System.out.println("Report Generation Failed. Please try again.");
        }
    }
}
