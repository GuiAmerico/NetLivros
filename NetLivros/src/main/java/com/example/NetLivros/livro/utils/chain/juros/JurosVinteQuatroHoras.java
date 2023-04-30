package com.example.NetLivros.livro.utils.chain.juros;

import java.math.BigDecimal;

import com.example.NetLivros.livro.model.enums.CondicaoDevolucao;

public class JurosVinteQuatroHoras implements JurosChain {
	private JurosChain proximo;
	private double jurosDouble;
	
	public static final int UMA_HORA = 60;
	public static final int VINTE_QUATRO_HORAS = 24 * UMA_HORA;
	public static final double VALOR_ADICIONAL_POR_HORA = 2.00;


	public JurosVinteQuatroHoras(JurosChain proximo) {
		this.proximo = proximo;
	}

	@Override
	public BigDecimal calcularJuros(long minutos, CondicaoDevolucao condicaoDevolucao, BigDecimal juros) {
		if (minutos <= VINTE_QUATRO_HORAS) {
			int horas = (int) (minutos / UMA_HORA);

			for (int i = 0; i < horas; i++) {
				jurosDouble += VALOR_ADICIONAL_POR_HORA;
				
			}
			return BigDecimal.valueOf(jurosDouble);
		}
		return proximo.calcularJuros(minutos, condicaoDevolucao, juros);
	}
}