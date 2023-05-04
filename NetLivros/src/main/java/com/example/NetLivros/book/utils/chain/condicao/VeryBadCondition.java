package com.example.NetLivros.book.utils.chain.condicao;

import com.example.NetLivros.book.model.enums.DevolutionCondition;

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
