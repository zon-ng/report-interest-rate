package sg.gov.mom.report.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sg.gov.mom.report.logic.MASInterestRateJson;
import sg.gov.mom.report.logic.MASInterestRateJsonRecord;
import sg.gov.mom.report.result.IRateReportResult;
import sg.gov.mom.report.result.MonthlyRate;
import sg.gov.mom.report.result.RateReportResult;

public class RateReportFacadeImpl implements RateReportFacade {
	private final String MAS_API_URL_WITH_PARAM = "https://eservices.mas.gov.sg/api/action/datastore/search.json?resource_id=5f2b18a8-0883-4769-a635-879c63d3caac&filters[end_of_month]=";
	public static final String VALID_MULTI_DATE_REGEX = "^(((JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)\\-\\d{4})[,]??){1,12}(?<!,)$"; 
	private final SimpleDateFormat sdfMMMYYYY = new SimpleDateFormat("MMM-yyyy");
	private final SimpleDateFormat sdfYYYYMM = new SimpleDateFormat("yyyy-MM");

	public List<IRateReportResult> generateRateReport(String dateCriteria) {
		List<IRateReportResult> iRateReportResultList = null;
		
		// Remove all spaces and make upper case for regex checking
		String processedDateCriteria = dateCriteria.replaceAll(" ", "").toUpperCase();
		// Date validation (Allow 1 to many dates in the format MMM-YYYY delimited by "," and up to at most 12 dates)
		if (processedDateCriteria.matches(VALID_MULTI_DATE_REGEX)) {
			String[] dateCriteriaArr = processedDateCriteria.split(",");
			
			// if exceeded 12 dates
			if(dateCriteriaArr.length>=12) {
				System.out.println("Please specify a maximum of 12 dates.");
			}
			
			processedDateCriteria = "";
			for(int i=0; i<dateCriteriaArr.length; i++) {
				try {
					processedDateCriteria += sdfYYYYMM.format(sdfMMMYYYY.parse(dateCriteriaArr[i]));

					// Only append "," if there are more dates specified
					if(i < dateCriteriaArr.length-1) {
						processedDateCriteria += ",";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("Invalid date specified. (Allow 1 to many dates in the format MMM-YYYY delimited by \",\" and up to at most 12 dates)");
			return iRateReportResultList;
		}

		iRateReportResultList = new ArrayList();
		// Call MAS API to query interest rates
		String jsonResult = null;
		try {
			jsonResult = queryForInterestRate(processedDateCriteria);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (jsonResult == null) {
			System.out.println("Unable to query MAS API at the moment. Please try again later.");
			return iRateReportResultList;
		}

		Gson gson = new GsonBuilder().create();
		MASInterestRateJson masInterestRateJson = gson.fromJson(jsonResult, MASInterestRateJson.class);

		// Convert MASInterestRateJson object to IRateReportResult (4 types of deposit rates)
		// IRateReportResult will then be sent to presentation layer, aka View, for display
		RateReportResult resultForFixedDeposits3m = new RateReportResult();
		resultForFixedDeposits3m.setDepositType("Fixed Deposits 3-month");
		resultForFixedDeposits3m.setMonthlyRateList(new ArrayList());
		RateReportResult resultForFixedDeposits6m = new RateReportResult();
		resultForFixedDeposits6m.setDepositType("Fixed Deposits 6-month");
		resultForFixedDeposits6m.setMonthlyRateList(new ArrayList());
		RateReportResult resultForFixedDeposits12m = new RateReportResult();
		resultForFixedDeposits12m.setDepositType("Fixed Deposits 12-month");
		resultForFixedDeposits12m.setMonthlyRateList(new ArrayList());
		RateReportResult resultForSavingsDeposits = new RateReportResult();
		resultForSavingsDeposits.setDepositType("Savings Deposits");
		resultForSavingsDeposits.setMonthlyRateList(new ArrayList());
		for (MASInterestRateJsonRecord masInterestRateJsonRecord : masInterestRateJson.getResult().getRecords()) {
			if (masInterestRateJsonRecord == null) {
				continue;
			}

			int computedYear = Integer.parseInt((masInterestRateJsonRecord.getEndOfMonth().substring(0,4)));
			int computedMonth = Integer.parseInt((masInterestRateJsonRecord.getEndOfMonth().substring(5,7)));

			MonthlyRate monthlyRate = new MonthlyRate(computedMonth, computedYear,
					masInterestRateJsonRecord.getBanksFixedDeposits3m(),
					masInterestRateJsonRecord.getFcFixedDeposits3m());
			resultForFixedDeposits3m.addMonthlyRateList(monthlyRate);
			
			monthlyRate = new MonthlyRate(computedMonth, computedYear,
					masInterestRateJsonRecord.getBanksFixedDeposits6m(),
					masInterestRateJsonRecord.getFcFixedDeposits6m());
			resultForFixedDeposits6m.addMonthlyRateList(monthlyRate);
			
			monthlyRate = new MonthlyRate(computedMonth, computedYear,
					masInterestRateJsonRecord.getBanksFixedDeposits12m(),
					masInterestRateJsonRecord.getFcFixedDeposits12m());
			resultForFixedDeposits12m.addMonthlyRateList(monthlyRate);
			
			monthlyRate = new MonthlyRate(computedMonth, computedYear,
					masInterestRateJsonRecord.getBanksSavingsDeposits(),
					masInterestRateJsonRecord.getFcSavingsDeposits());
			resultForSavingsDeposits.addMonthlyRateList(monthlyRate);
		}
		iRateReportResultList.add(resultForFixedDeposits3m);
		iRateReportResultList.add(resultForFixedDeposits6m);
		iRateReportResultList.add(resultForFixedDeposits12m);
		iRateReportResultList.add(resultForSavingsDeposits);

		return iRateReportResultList;
	}

	// Call MAS API to query interest rates
	public String queryForInterestRate(String date) throws ClientProtocolException, IOException {
		String jsonResult = null;

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(MAS_API_URL_WITH_PARAM + date);
		CloseableHttpResponse response = client.execute(httpGet);

		if (response.getStatusLine().getStatusCode() == 200) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			jsonResult = "";
			String line = "";
			while ((line = rd.readLine()) != null) {
				jsonResult += line;
			}
		} else {
			System.out.println("HTTP POST Status=[" + response.getStatusLine().getStatusCode() + "]");
		}
		client.close();

		return jsonResult;
	}

}
