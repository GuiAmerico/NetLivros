package com.example.NetLivros.livro.utils.chain.condicao;

import com.example.NetLivros.livro.model.enums.CondicaoDevolucao;

public class CondicaoBom implements CondicaoChain{
	private CondicaoDevolucao condicaoDevolucao;
	
	
	public CondicaoBom(CondicaoChain proximo) {
	}

	
	@Override
	public CondicaoDevolucao avaliacao(double random,CondicaoDevolucao condicaoDevolucao) {
		if (random > 0.50) {
			condicaoDevolucao = CondicaoDevolucao.BOM;
			this.condicaoDevolucao = condicaoDevolucao;
		}
		
		return this.condicaoDevolucao;
	}

}
