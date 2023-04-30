package com.example.NetLivros.livro.utils.chain.condicao;

import com.example.NetLivros.livro.model.enums.DevolutionCondition;

public interface ConditionChain {

	DevolutionCondition avaliation(double random, DevolutionCondition devolutionCondition);
}
