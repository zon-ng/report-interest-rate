package sg.gov.mom.report.logic;

import com.google.gson.annotations.SerializedName;

/**
 * MASInterestRateJsonRecord will be part of the object that is converted from
 * the Interest Rate result(JSON) queried via MAS API.
 *
 */
public class MASInterestRateJsonRecord {
	@SerializedName("end_of_month")
	private String endOfMonth;
	@SerializedName("banks_fixed_deposits_3m")
	private float banksFixedDeposits3m;
	@SerializedName("banks_fixed_deposits_6m")
	private float banksFixedDeposits6m;
	@SerializedName("banks_fixed_deposits_12m")
	private float banksFixedDeposits12m;
	@SerializedName("banks_savings_deposits")
	private float banksSavingsDeposits;

	@SerializedName("fc_fixed_deposits_3m")
	private float fcFixedDeposits3m;
	@SerializedName("fc_fixed_deposits_6m")
	private float fcFixedDeposits6m;
	@SerializedName("fc_fixed_deposits_12m")
	private float fcFixedDeposits12m;
	@SerializedName("fc_savings_deposits")
	private float fcSavingsDeposits;

	public String getEndOfMonth() {
		return endOfMonth;
	}

	public void setEndOfMonth(String endOfMonth) {
		this.endOfMonth = endOfMonth;
	}

	public float getBanksFixedDeposits3m() {
		return banksFixedDeposits3m;
	}

	public void setBanksFixedDeposits3m(float banksFixedDeposits3m) {
		this.banksFixedDeposits3m = banksFixedDeposits3m;
	}

	public float getBanksFixedDeposits6m() {
		return banksFixedDeposits6m;
	}

	public void setBanksFixedDeposits6m(float banksFixedDeposits6m) {
		this.banksFixedDeposits6m = banksFixedDeposits6m;
	}

	public float getBanksFixedDeposits12m() {
		return banksFixedDeposits12m;
	}

	public void setBanksFixedDeposits12m(float banksFixedDeposits12m) {
		this.banksFixedDeposits12m = banksFixedDeposits12m;
	}

	public float getBanksSavingsDeposits() {
		return banksSavingsDeposits;
	}

	public void setBanksSavingsDeposits(float banksSavingsDeposits) {
		this.banksSavingsDeposits = banksSavingsDeposits;
	}

	public float getFcFixedDeposits3m() {
		return fcFixedDeposits3m;
	}

	public void setFcFixedDeposits3m(float fcFixedDeposits3m) {
		this.fcFixedDeposits3m = fcFixedDeposits3m;
	}

	public float getFcFixedDeposits6m() {
		return fcFixedDeposits6m;
	}

	public void setFcFixedDeposits6m(float fcFixedDeposits6m) {
		this.fcFixedDeposits6m = fcFixedDeposits6m;
	}

	public float getFcFixedDeposits12m() {
		return fcFixedDeposits12m;
	}

	public void setFcFixedDeposits12m(float fcFixedDeposits12m) {
		this.fcFixedDeposits12m = fcFixedDeposits12m;
	}

	public float getFcSavingsDeposits() {
		return fcSavingsDeposits;
	}

	public void setFcSavingsDeposits(float fcSavingsDeposits) {
		this.fcSavingsDeposits = fcSavingsDeposits;
	}

	@Override
	public String toString() {
		return "MASInterestRateJsonRecord [endOfMonth=" + endOfMonth + ", banksFixedDeposits3m=" + banksFixedDeposits3m
				+ ", banksFixedDeposits6m=" + banksFixedDeposits6m + ", banksFixedDeposits12m=" + banksFixedDeposits12m
				+ ", banksSavingsDeposits=" + banksSavingsDeposits + ", fcFixedDeposits3m=" + fcFixedDeposits3m
				+ ", fcFixedDeposits6m=" + fcFixedDeposits6m + ", fcFixedDeposits12m=" + fcFixedDeposits12m
				+ ", fcSavingsDeposits=" + fcSavingsDeposits + "]";
	}

}
