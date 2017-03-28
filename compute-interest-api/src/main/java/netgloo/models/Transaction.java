package netgloo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

	double amount;
	
	double interestRate;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
}

