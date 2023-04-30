package com.example.NetLivros.livro.utils.chain.juros;

import java.math.BigDecimal;

import com.example.NetLivros.livro.model.enums.CondicaoDevolucao;

public interface JurosChain {

    BigDecimal calcularJuros(long minutos, CondicaoDevolucao condicaoDevolucao, BigDecimal juros);

}
