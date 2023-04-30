package com.example.NetLivros.livro.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.example.NetLivros.livro.model.Exemplar;
import com.example.NetLivros.livro.model.enums.CondicaoDevolucao;
import com.example.NetLivros.livro.utils.chain.condicao.CondicaoBom;
import com.example.NetLivros.livro.utils.chain.condicao.CondicaoChain;
import com.example.NetLivros.livro.utils.chain.condicao.CondicaoPessimo;
import com.example.NetLivros.livro.utils.chain.condicao.CondicaoRuim;
import com.example.NetLivros.livro.utils.chain.juros.JurosChain;
import com.example.NetLivros.livro.utils.chain.juros.JurosMaisDeVinteQuatroHoras;
import com.example.NetLivros.livro.utils.chain.juros.JurosUmaHora;
import com.example.NetLivros.livro.utils.chain.juros.JurosVinteQuatroHoras;

@Component
public class Utils {

	public BigDecimal verificarAluguel(Exemplar livro) {
		return verificarAluguel(livro.getDataAluguel(), livro.getDataDevolucao(), livro.getDataQueDeveriaSerDevolvido(),
				livro.getCondicaoDevolucao());
	}

	private BigDecimal verificarAluguel(LocalDateTime dataAluguel, LocalDateTime dataDevolução,
			LocalDateTime dataQueDeveriaSerDevolvido, CondicaoDevolucao condicaoDevolucao) {

		long minutos = dataQueDeveriaSerDevolvido.until(dataDevolução, ChronoUnit.MINUTES);
		condicaoDevolucao = avaliacao(condicaoDevolucao);

		JurosChain chain = new JurosUmaHora(new JurosVinteQuatroHoras(new JurosMaisDeVinteQuatroHoras(null)));

		return chain.calcularJuros(minutos, condicaoDevolucao, BigDecimal.ZERO);

	}

	private CondicaoDevolucao avaliacao(CondicaoDevolucao condicaoDevolucao) {
		double random = Math.random();
		CondicaoChain chain = new CondicaoRuim(condicaoDevolucao, new CondicaoPessimo(new CondicaoBom(null)));
		return chain.avaliacao(random, condicaoDevolucao);
	}

}
