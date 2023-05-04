package com.example.NetLivros.book.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.example.NetLivros.book.model.Copy;
import com.example.NetLivros.book.model.enums.DevolutionCondition;
import com.example.NetLivros.book.utils.chain.condicao.BadCondition;
import com.example.NetLivros.book.utils.chain.condicao.ConditionChain;
import com.example.NetLivros.book.utils.chain.condicao.GoodCondition;
import com.example.NetLivros.book.utils.chain.condicao.VeryBadCondition;
import com.example.NetLivros.book.utils.chain.juros.InterestChain;
import com.example.NetLivros.book.utils.chain.juros.InterestOneHour;
import com.example.NetLivros.book.utils.chain.juros.InterestOverTwentyFourHours;
import com.example.NetLivros.book.utils.chain.juros.InterestTwentyFourHours;

@Component
public class Utils {

	public BigDecimal checkRent(Copy copy) {
		return checkRent(copy.getDateRent(), copy.getDevolutionDate(), copy.getDateThatShouldBeReturned(),
				copy.getDevolutionCondition());
	}

	private BigDecimal checkRent(LocalDateTime dateRent, LocalDateTime devolutionDate,
			LocalDateTime dateThatShouldBeReturned, DevolutionCondition devolutionCondition) {
		long minutes = dateThatShouldBeReturned.until(dateRent, ChronoUnit.MINUTES);
		devolutionCondition = avaliation(devolutionCondition);

		InterestChain chain = new InterestOneHour(new InterestTwentyFourHours(new InterestOverTwentyFourHours(null)));

		return chain.calculateInterest(minutes, devolutionCondition, BigDecimal.ZERO);

	}

	private DevolutionCondition avaliation(DevolutionCondition devolutionCondition) {
		double random = Math.random();
		ConditionChain chain = new BadCondition(new VeryBadCondition(new GoodCondition(null)));
		return chain.avaliation(random, devolutionCondition);
	}

}
