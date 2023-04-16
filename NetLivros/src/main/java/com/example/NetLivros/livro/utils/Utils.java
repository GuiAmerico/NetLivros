package com.example.NetLivros.livro.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.example.NetLivros.livro.model.Exemplar;
import com.example.NetLivros.livro.model.enums.CondicaoDevolucao;

@Component
public class Utils {

	public static final int UMA_HORA = 60;
	public static final int VINTE_QUATRO_HORAS = 24 * UMA_HORA;
	public static final double VALOR_ADICIONAL_POR_HORA = 2.00;
	public static final double VALOR_ADICIONAL_MA_CONDICAO = 3.00;
	public static final double VALOR_DIA = 20.00;

	public BigDecimal verificarAluguel(Exemplar livro) {
		return verificarAluguel(livro.getDataAluguel(), livro.getDataDevolução(), livro.getCondicaoDevolucao());
	}

	private BigDecimal verificarAluguel(LocalDateTime dataAluguel, LocalDateTime dataDevolução,
			CondicaoDevolucao condicaoDevolucao) {

		long minutos = dataAluguel.until(dataDevolução, ChronoUnit.MINUTES);
		BigDecimal juros = BigDecimal.ZERO;
		if (minutos <= UMA_HORA) {
			return juros;
		}
		if (minutos <= VINTE_QUATRO_HORAS) {
			int horas = (int) (minutos / UMA_HORA);
			for (int i = 0; i < horas; i++) {
				juros.add(BigDecimal.valueOf(VALOR_ADICIONAL_POR_HORA));
			}
			return juros;
		}
		int dias = (int) (minutos / VINTE_QUATRO_HORAS);
		for (int i = 0; i < dias; i++) {
			juros.add(BigDecimal.valueOf(VALOR_DIA));
		}

		if (condicaoDevolucao.equals(CondicaoDevolucao.RUIM)) {
			juros.add(BigDecimal.valueOf(VALOR_ADICIONAL_MA_CONDICAO));
		}
		if (condicaoDevolucao.equals(CondicaoDevolucao.PESSIMO)) {
			juros.add(BigDecimal.valueOf(VALOR_ADICIONAL_MA_CONDICAO * 2));
		}
		

		return juros;
	}

}
