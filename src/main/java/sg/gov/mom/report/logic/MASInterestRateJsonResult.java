package sg.gov.mom.report.logic;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * MASInterestRateJsonResult will be part of the object that is converted from
 * the Interest Rate result(JSON) queried via MAS API.
 *
 */
public class MASInterestRateJsonResult {
	@SerializedName("records")
	private List<MASInterestRateJsonRecord> records;

	public List<MASInterestRateJsonRecord> getRecords() {
		return records;
	}

	@Override
	public String toString() {
		return "MASInterestRateJsonResult [records=" + records + "]";
	}

}
