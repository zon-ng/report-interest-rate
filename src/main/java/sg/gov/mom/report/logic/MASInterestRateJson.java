package sg.gov.mom.report.logic;

import java.util.HashMap;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * MASInterestRateJson will be the object that is converted from the Interest
 * Rate result(JSON) queried via MAS API.
 *
 */

public class MASInterestRateJson {
	@SerializedName("result")
	private MASInterestRateJsonResult result;

	public MASInterestRateJsonResult getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "MASInterestRateJson [result=" + result + "]";
	}
}
