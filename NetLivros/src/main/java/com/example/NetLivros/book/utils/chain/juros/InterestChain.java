package com.example.NetLivros.book.utils.chain.juros;

import java.math.BigDecimal;

import com.example.NetLivros.book.model.enums.DevolutionCondition;

public interface InterestChain {

	BigDecimal calculateInterest(long minutes, DevolutionCondition devolutionCondition, BigDecimal interest);

}
