package com.example.NetLivros.book.utils.chain.juros;

import java.math.BigDecimal;

import com.example.NetLivros.book.model.enums.DevolutionCondition;

public class InterestOneHour implements InterestChain {
	private InterestChain next;
	private double interestDouble;

	public static final int ONE_HOUR= 60;
	public static final double ADDITIONAL_VALUE_BAD_CONDITION = 3.00;

	public InterestOneHour(InterestChain next) {
		this.next = next;
	}

	@Override
	public BigDecimal calculateInterest(long minutes, DevolutionCondition devolutionCondition, BigDecimal interest) {
		if (minutes <= ONE_HOUR) {
			return interest;
		}
		if (DevolutionCondition.BAD.equals(devolutionCondition)) {
			this.interestDouble += ADDITIONAL_VALUE_BAD_CONDITION;
		}
		if (DevolutionCondition.VERY_BAD.equals(devolutionCondition)) {
			this.interestDouble += ADDITIONAL_VALUE_BAD_CONDITION * 2;
		}

		return next.calculateInterest(minutes, devolutionCondition, BigDecimal.valueOf(interestDouble));
	}

}
