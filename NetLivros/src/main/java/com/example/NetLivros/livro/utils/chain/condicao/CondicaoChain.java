package com.example.NetLivros.livro.utils.chain.condicao;

import com.example.NetLivros.livro.model.enums.CondicaoDevolucao;

public interface CondicaoChain {

	CondicaoDevolucao avaliacao(double random, CondicaoDevolucao condicaoDevolucao);
}
