package com.example.NetLivros.livro.utils.chain.condicao;

import com.example.NetLivros.livro.model.enums.DevolutionCondition;

public class VeryBadCondition implements ConditionChain{
	private DevolutionCondition devolutionCondition;
	private ConditionChain next;
	
	
	public VeryBadCondition(ConditionChain next) {
		this.next = next;
	}

	
	@Override
	public DevolutionCondition avaliation(double random,DevolutionCondition devolutionCondition) {
		
		if (random > 0.20 && random <= 0.50) {
			devolutionCondition = DevolutionCondition.VERY_BAD;
			this.devolutionCondition = devolutionCondition;
		}
		
		return next.avaliation(random,this.devolutionCondition);
	}

}
