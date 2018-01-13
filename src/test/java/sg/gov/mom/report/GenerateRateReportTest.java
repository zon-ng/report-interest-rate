package sg.gov.mom.report;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sg.gov.mom.report.facade.RateReportFacade;
import sg.gov.mom.report.facade.RateReportFacadeImpl;
import sg.gov.mom.report.factory.RateReportFactory;
import sg.gov.mom.report.factory.ReportFactory;
import sg.gov.mom.report.logic.MASInterestRateJson;
import sg.gov.mom.report.result.IMonthlyRate;
import sg.gov.mom.report.result.IRateReportResult;

/**
 * Unit test for simple GenerateRateReport.
 */
public class GenerateRateReportTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GenerateRateReportTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( GenerateRateReportTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        // Test Codes on Conversion of json to object
    	testJsonToObject();
        
        // test date regex used for validation (Allow max 12 dates delimited with comma)
        testSingleDate();
        testSingleDateWithSpecialCharacter();
        testSingleDateWithTrailingComma();
        testSingleDateWithStartingComma();
        testTwelveDate();
        testThirteenDate();
        
        // test creation of report with invalid date, expect no result
        testInvalidDateGenerateReport();
        // test creation of single date report
        testSingleDateGenerateReport();
        // test creation of single date report with space
        testSingleDateWithSpaceGenerateReport();
        // test creation of twelve date report
        testTwelveDateGenerateReport();
        // test creation of twelve date report, check on the overall average rate is it computed correctly
        testTwelveDateGenerateReportCheckOverallAverage();
        // test creation of thirteen date report, expected no result as maximum allowed 12 dates
        testThirteenDateGenerateReport();
    }
    
    public void testJsonToObject() {
    	Gson gson = new GsonBuilder().create();
        MASInterestRateJson masInterestRateJson = gson.fromJson("{\"help\":\"Search a datastore table. :param resource_id: id or alias of the data that is going to be selected.\",\"success\":true,\"result\":{\"fields\":[{\"id\":\"end_of_month\",\"type\":\"text\"},{\"id\":\"preliminary\",\"type\":\"text\"},{\"id\":\"prime_lending_rate\",\"type\":\"float\"},{\"id\":\"banks_fixed_deposits_3m\",\"type\":\"float\"},{\"id\":\"banks_fixed_deposits_6m\",\"type\":\"float\"},{\"id\":\"banks_fixed_deposits_12m\",\"type\":\"float\"},{\"id\":\"banks_savings_deposits\",\"type\":\"float\"},{\"id\":\"fc_hire_purchase_motor_3y\",\"type\":\"float\"},{\"id\":\"fc_housing_loans_15y\",\"type\":\"float\"},{\"id\":\"fc_fixed_deposits_3m\",\"type\":\"float\"},{\"id\":\"fc_fixed_deposits_6m\",\"type\":\"float\"},{\"id\":\"fc_fixed_deposits_12m\",\"type\":\"float\"},{\"id\":\"fc_savings_deposits\",\"type\":\"float\"}],\"resource_id\":[\"5f2b18a8-0883-4769-a635-879c63d3caac\"],\"limit\":5,\"total\":\"420\",\"records\":[{\"end_of_month\":\"1983-01\",\"preliminary\":null,\"prime_lending_rate\":\"9.53\",\"banks_fixed_deposits_3m\":\"6.75\",\"banks_fixed_deposits_6m\":\"6.80\",\"banks_fixed_deposits_12m\":\"7.13\",\"banks_savings_deposits\":\"6.50\",\"fc_hire_purchase_motor_3y\":\"12.67\",\"fc_housing_loans_15y\":\"12.42\",\"fc_fixed_deposits_3m\":\"7.15\",\"fc_fixed_deposits_6m\":\"7.30\",\"fc_fixed_deposits_12m\":\"7.70\",\"fc_savings_deposits\":\"7.21\",\"feeds_flatstore_entry_id\":\"50628\",\"timestamp\":\"1514945713\",\"feeds_entity_id\":null},{\"end_of_month\":\"1983-02\",\"preliminary\":null,\"prime_lending_rate\":\"9.25\",\"banks_fixed_deposits_3m\":\"6.40\",\"banks_fixed_deposits_6m\":\"6.70\",\"banks_fixed_deposits_12m\":\"6.93\",\"banks_savings_deposits\":\"6.40\",\"fc_hire_purchase_motor_3y\":\"12.58\",\"fc_housing_loans_15y\":\"12.21\",\"fc_fixed_deposits_3m\":\"6.70\",\"fc_fixed_deposits_6m\":\"7.03\",\"fc_fixed_deposits_12m\":\"7.33\",\"fc_savings_deposits\":\"7.08\",\"feeds_flatstore_entry_id\":\"50629\",\"timestamp\":\"1514945713\",\"feeds_entity_id\":null},{\"end_of_month\":\"1983-03\",\"preliminary\":null,\"prime_lending_rate\":\"9.10\",\"banks_fixed_deposits_3m\":\"6.18\",\"banks_fixed_deposits_6m\":\"6.48\",\"banks_fixed_deposits_12m\":\"6.83\",\"banks_savings_deposits\":\"6.30\",\"fc_hire_purchase_motor_3y\":\"12.36\",\"fc_housing_loans_15y\":\"11.97\",\"fc_fixed_deposits_3m\":\"6.48\",\"fc_fixed_deposits_6m\":\"6.78\",\"fc_fixed_deposits_12m\":\"7.18\",\"fc_savings_deposits\":\"7.00\",\"feeds_flatstore_entry_id\":\"50630\",\"timestamp\":\"1514945713\",\"feeds_entity_id\":null},{\"end_of_month\":\"1983-04\",\"preliminary\":null,\"prime_lending_rate\":\"9.03\",\"banks_fixed_deposits_3m\":\"6.10\",\"banks_fixed_deposits_6m\":\"6.35\",\"banks_fixed_deposits_12m\":\"6.73\",\"banks_savings_deposits\":\"6.15\",\"fc_hire_purchase_motor_3y\":\"12.19\",\"fc_housing_loans_15y\":\"11.92\",\"fc_fixed_deposits_3m\":\"6.38\",\"fc_fixed_deposits_6m\":\"6.65\",\"fc_fixed_deposits_12m\":\"7.13\",\"fc_savings_deposits\":\"7.00\",\"feeds_flatstore_entry_id\":\"50631\",\"timestamp\":\"1514945713\",\"feeds_entity_id\":null},{\"end_of_month\":\"1983-05\",\"preliminary\":null,\"prime_lending_rate\":\"9.03\",\"banks_fixed_deposits_3m\":\"6.10\",\"banks_fixed_deposits_6m\":\"6.35\",\"banks_fixed_deposits_12m\":\"6.73\",\"banks_savings_deposits\":\"6.13\",\"fc_hire_purchase_motor_3y\":\"12.14\",\"fc_housing_loans_15y\":\"11.84\",\"fc_fixed_deposits_3m\":\"6.50\",\"fc_fixed_deposits_6m\":\"6.75\",\"fc_fixed_deposits_12m\":\"7.18\",\"fc_savings_deposits\":\"7.00\",\"feeds_flatstore_entry_id\":\"50632\",\"timestamp\":\"1514945713\",\"feeds_entity_id\":null}]}}", MASInterestRateJson.class);
        
        Assert.assertEquals("MASInterestRateJson [result=MASInterestRateJsonResult [records=[MASInterestRateJsonRecord [endOfMonth=1983-01, banksFixedDeposits3m=6.75, banksFixedDeposits6m=6.8, banksFixedDeposits12m=7.13, banksSavingsDeposits=6.5, fcFixedDeposits3m=7.15, fcFixedDeposits6m=7.3, fcFixedDeposits12m=7.7, fcSavingsDeposits=7.21], MASInterestRateJsonRecord [endOfMonth=1983-02, banksFixedDeposits3m=6.4, banksFixedDeposits6m=6.7, banksFixedDeposits12m=6.93, banksSavingsDeposits=6.4, fcFixedDeposits3m=6.7, fcFixedDeposits6m=7.03, fcFixedDeposits12m=7.33, fcSavingsDeposits=7.08], MASInterestRateJsonRecord [endOfMonth=1983-03, banksFixedDeposits3m=6.18, banksFixedDeposits6m=6.48, banksFixedDeposits12m=6.83, banksSavingsDeposits=6.3, fcFixedDeposits3m=6.48, fcFixedDeposits6m=6.78, fcFixedDeposits12m=7.18, fcSavingsDeposits=7.0], MASInterestRateJsonRecord [endOfMonth=1983-04, banksFixedDeposits3m=6.1, banksFixedDeposits6m=6.35, banksFixedDeposits12m=6.73, banksSavingsDeposits=6.15, fcFixedDeposits3m=6.38, fcFixedDeposits6m=6.65, fcFixedDeposits12m=7.13, fcSavingsDeposits=7.0], MASInterestRateJsonRecord [endOfMonth=1983-05, banksFixedDeposits3m=6.1, banksFixedDeposits6m=6.35, banksFixedDeposits12m=6.73, banksSavingsDeposits=6.13, fcFixedDeposits3m=6.5, fcFixedDeposits6m=6.75, fcFixedDeposits12m=7.18, fcSavingsDeposits=7.0]]]]",
        		masInterestRateJson.toString());
    }
    
    public void testSingleDate() {
	    String dateCriteria = "JAN-2017";
	    Assert.assertTrue(dateCriteria.matches(RateReportFacadeImpl.VALID_MULTI_DATE_REGEX));
	}
    
    public void testSingleDateWithSpecialCharacter() {
	    String dateCriteria = "JAN-2017*";
	    Assert.assertFalse(dateCriteria.matches(RateReportFacadeImpl.VALID_MULTI_DATE_REGEX));
	}
    
    public void testSingleDateWithTrailingComma() {
	    String dateCriteria = "JAN-2017,";
	    Assert.assertFalse(dateCriteria.matches(RateReportFacadeImpl.VALID_MULTI_DATE_REGEX));
	}
    public void testSingleDateWithStartingComma() {
	    String dateCriteria = ",JAN-2017";
	    Assert.assertFalse(dateCriteria.matches(RateReportFacadeImpl.VALID_MULTI_DATE_REGEX));
	}
    
    public void testTwelveDate() {
    	String dateCriteria = "JAN-2017,FEB-2017,MAR-2017,APR-2017,MAY-2017,JUN-2017,JUL-2017,AUG-2017,SEP-2017,OCT-2017,NOV-2017,DEC-2017";
	    Assert.assertTrue(dateCriteria.matches(RateReportFacadeImpl.VALID_MULTI_DATE_REGEX));
	}
    
    public void testThirteenDate() {
	    String dateCriteria = "JAN-2017,FEB-2017,MAR-2017,APR-2017,MAY-2017,JUN-2017,JUL-2017,AUG-2017,SEP-2017,OCT-2017,NOV-2017,DEC-2017,JAN-2018";
	    Assert.assertFalse(dateCriteria.matches(RateReportFacadeImpl.VALID_MULTI_DATE_REGEX));
	}
    
    
    
    public void testInvalidDateGenerateReport() {
    	ReportFactory reportFactory = new RateReportFactory();
        RateReportFacade rateReportFacade = reportFactory.createReportFacade();
        Assert.assertEquals(null, rateReportFacade.generateRateReport("JAN2017"));
    }
    
    public void testSingleDateGenerateReport() {
    	ReportFactory reportFactory = new RateReportFactory();
        RateReportFacade rateReportFacade = reportFactory.createReportFacade();
        Assert.assertEquals(1, rateReportFacade.generateRateReport("JAN-2017").get(0).getMonthlyRateList().size());
    }
    
    public void testSingleDateWithSpaceGenerateReport() {
    	ReportFactory reportFactory = new RateReportFactory();
        RateReportFacade rateReportFacade = reportFactory.createReportFacade();
        Assert.assertEquals(1, rateReportFacade.generateRateReport(" J A N - 2 0 1 7 ").get(0).getMonthlyRateList().size());
    }
    
    public void testTwelveDateGenerateReport() {
    	ReportFactory reportFactory = new RateReportFactory();
        RateReportFacade rateReportFacade = reportFactory.createReportFacade();
        Assert.assertEquals(12, rateReportFacade.generateRateReport("JAN-2017,FEB-2017,MAR-2017,APR-2017,MAY-2017,JUN-2017,JUL-2017,AUG-2017,SEP-2017,OCT-2017,NOV-2017,DEC-2017").get(0).getMonthlyRateList().size());
    }
    
    public void testTwelveDateGenerateReportCheckOverallAverage() {
    	ReportFactory reportFactory = new RateReportFactory();
        RateReportFacade rateReportFacade = reportFactory.createReportFacade();
        List<IRateReportResult> iRateReportResultList = rateReportFacade.generateRateReport("JAN-2017,FEB-2017,MAR-2017,APR-2017,MAY-2017,JUN-2017,JUL-2017,AUG-2017,SEP-2017,OCT-2017,NOV-2017,DEC-2017");
        for(IRateReportResult iRateReportResult : iRateReportResultList) {
        	float overallAvgBankRate = 0.0f;
        	float overallAvgFinancialCompanyRate = 0.0f;
        	for(IMonthlyRate monthlyRate : iRateReportResult.getMonthlyRateList()) {
        		overallAvgBankRate += monthlyRate.getBankRate();
        		overallAvgFinancialCompanyRate += monthlyRate.getFinancialCompanyRate();
        	}
        	Assert.assertEquals(overallAvgBankRate/iRateReportResult.getMonthlyRateList().size(), iRateReportResult.getAvgBankRate());
        	Assert.assertEquals(overallAvgFinancialCompanyRate/iRateReportResult.getMonthlyRateList().size(), iRateReportResult.getAvgFinancialCompanyRate());
        }
    }
    
    public void testThirteenDateGenerateReport() {
    	ReportFactory reportFactory = new RateReportFactory();
        RateReportFacade rateReportFacade = reportFactory.createReportFacade();
        Assert.assertEquals(null, rateReportFacade.generateRateReport("JAN-2017,FEB-2017,MAR-2017,APR-2017,MAY-2017,JUN-2017,JUL-2017,AUG-2017,SEP-2017,OCT-2017,NOV-2017,DEC-2017,JAN-2018"));
    }
}
