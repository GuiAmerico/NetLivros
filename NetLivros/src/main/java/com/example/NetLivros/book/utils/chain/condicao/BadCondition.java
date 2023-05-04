package com.example.NetLivros.book.utils.chain.condicao;

import com.example.NetLivros.book.model.enums.DevolutionCondition;

public class BadCondition implements ConditionChain{
	private double random;
	private DevolutionCondition devolutionCondition;
	private ConditionChain next;
	
	
	public BadCondition(ConditionChain next) {
		this.next = next;
	}

	
	@Override
	public DevolutionCondition avaliation(double random,DevolutionCondition devolutionCondition) {
		this.random = random;
		if (this.random <= 0.20) {
			devolutionCondition = DevolutionCondition.BAD;
			this.devolutionCondition = devolutionCondition;
		}
		
		return next.avaliation(this.random,this.devolutionCondition);
	}

}
