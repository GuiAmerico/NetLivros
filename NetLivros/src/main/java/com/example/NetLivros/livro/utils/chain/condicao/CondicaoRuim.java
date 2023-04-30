package com.example.NetLivros.livro.utils.chain.condicao;

import com.example.NetLivros.livro.model.enums.CondicaoDevolucao;

public class CondicaoRuim implements CondicaoChain{
	private double random;
	private CondicaoDevolucao condicaoDevolucao;
	private CondicaoChain proximo;
	
	
	public CondicaoRuim(CondicaoDevolucao condicaoDevolucao, CondicaoChain proximo) {
		this.condicaoDevolucao = condicaoDevolucao;
		this.proximo = proximo;
	}

	
	@Override
	public CondicaoDevolucao avaliacao(double random,CondicaoDevolucao condicaoDevolucao) {
		this.random = random;
		if (this.random <= 0.20) {
			condicaoDevolucao = CondicaoDevolucao.RUIM;
			this.condicaoDevolucao = condicaoDevolucao;
		}
		
		return proximo.avaliacao(this.random,this.condicaoDevolucao);
	}

}
