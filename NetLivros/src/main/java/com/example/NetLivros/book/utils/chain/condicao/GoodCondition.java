package com.example.NetLivros.book.utils.chain.condicao;

import com.example.NetLivros.book.model.enums.DevolutionCondition;

public class GoodCondition implements ConditionChain{
	private DevolutionCondition devolutionCondition;
	
	
	public GoodCondition(ConditionChain next) {
	}

	
	@Override
	public DevolutionCondition avaliation(double random,DevolutionCondition devolutionCondition) {
		if (random > 0.50) {
			devolutionCondition = DevolutionCondition.GOOD;
			this.devolutionCondition= devolutionCondition;
		}
		
		return this.devolutionCondition;
	}

}
