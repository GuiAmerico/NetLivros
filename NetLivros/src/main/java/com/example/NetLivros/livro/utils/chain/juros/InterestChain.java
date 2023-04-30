package com.example.NetLivros.livro.utils.chain.juros;

import java.math.BigDecimal;

import com.example.NetLivros.livro.model.enums.DevolutionCondition;

public interface InterestChain {

	BigDecimal calculateInterest(long minutes, DevolutionCondition devolutionCondition, BigDecimal interest);

}
