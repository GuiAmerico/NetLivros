package com.example.NetLivros.book.utils.chain.condicao;

import com.example.NetLivros.book.model.enums.DevolutionCondition;

public interface ConditionChain {

	DevolutionCondition avaliation(double random, DevolutionCondition devolutionCondition);
}
