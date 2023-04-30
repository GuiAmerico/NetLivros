package com.example.NetLivros.livro.utils.chain.condicao;

import com.example.NetLivros.livro.model.enums.DevolutionCondition;

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
