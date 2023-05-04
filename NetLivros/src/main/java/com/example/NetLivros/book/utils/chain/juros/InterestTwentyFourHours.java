package com.example.NetLivros.book.utils.chain.juros;

import java.math.BigDecimal;

import com.example.NetLivros.book.model.enums.DevolutionCondition;

public class InterestTwentyFourHours implements InterestChain {
	private InterestChain next;
	private double interestDouble;
	
	public static final int ONE_HOUR = 60;
	public static final int TWENTY_FOUR_HOURS = 24 * ONE_HOUR;
	public static final double ADDITIONAL_VALUE_PER_HOUR = 2.00;


	public InterestTwentyFourHours(InterestChain next) {
		this.next = next;
	}

	@Override
	public BigDecimal calculateInterest(long minutes, DevolutionCondition devolutionCondition, BigDecimal interest) {
		if (minutes <= TWENTY_FOUR_HOURS) {
			int hours = (int) (minutes / ONE_HOUR);

			for (int i = 0; i < hours; i++) {
				interestDouble += ADDITIONAL_VALUE_PER_HOUR;
				
			}
			return BigDecimal.valueOf(interestDouble);
		}
		return next.calculateInterest(minutes, devolutionCondition, interest);
	}

	
}