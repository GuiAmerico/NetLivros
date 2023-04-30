package com.example.NetLivros.livro.utils.chain.juros;

import java.math.BigDecimal;

import com.example.NetLivros.livro.model.enums.CondicaoDevolucao;

public class JurosMaisDeVinteQuatroHoras implements JurosChain {
	private JurosChain proximo;
	private double jurosDouble;
	
	public static final int UMA_HORA = 60;
	public static final int VINTE_QUATRO_HORAS = 24 * UMA_HORA;
	public static final double VALOR_DIA = 20.00;

	public JurosMaisDeVinteQuatroHoras(JurosChain proximo) {
		this.proximo = proximo;
	}

	@Override
	public BigDecimal calcularJuros(long minutos, CondicaoDevolucao condicaoDevolucao, BigDecimal juros) {
		int dias = (int) (minutos / VINTE_QUATRO_HORAS);
		for (int i = 0; i < dias; i++) {
			jurosDouble += VALOR_DIA;
		}

		return BigDecimal.valueOf(jurosDouble);
	}
}