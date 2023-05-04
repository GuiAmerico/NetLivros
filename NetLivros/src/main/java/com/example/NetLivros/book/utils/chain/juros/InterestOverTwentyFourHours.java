package com.example.NetLivros.book.utils.chain.juros;

import java.math.BigDecimal;

import com.example.NetLivros.book.model.enums.DevolutionCondition;

public class InterestOverTwentyFourHours implements InterestChain {
	private InterestChain next;
	private double interestDouble;

	public static final int ONE_HOUR = 60;
	public static final int TWENTY_FOUR_HOURS = 24 * ONE_HOUR;
	public static final double VALUE_DAY = 10.00;

	public InterestOverTwentyFourHours(InterestChain next) {
		this.next = next;
	}

	@Override
	public BigDecimal calculateInterest(long minutes, DevolutionCondition devolutionCondition, BigDecimal interest) {
		int days = (int) (minutes / TWENTY_FOUR_HOURS);
		for (int i = 0; i < days; i++) {
			interestDouble += VALUE_DAY;
		}

		return BigDecimal.valueOf(interestDouble);
	}

}