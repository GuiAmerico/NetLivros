package com.example.NetLivros.livro.utils.chain.condicao;

import com.example.NetLivros.livro.model.enums.CondicaoDevolucao;

public class CondicaoPessimo implements CondicaoChain{
	private CondicaoDevolucao condicaoDevolucao;
	private CondicaoChain proximo;
	
	
	public CondicaoPessimo(CondicaoChain proximo) {
		this.proximo = proximo;
	}

	
	@Override
	public CondicaoDevolucao avaliacao(double random,CondicaoDevolucao condicaoDevolucao) {
		
		if (random > 0.20 && random <= 0.50) {
			condicaoDevolucao = CondicaoDevolucao.PESSIMO;
			this.condicaoDevolucao = condicaoDevolucao;
		}
		
		return proximo.avaliacao(random,this.condicaoDevolucao);
	}

}
