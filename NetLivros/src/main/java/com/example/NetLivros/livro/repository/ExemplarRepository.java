package com.example.NetLivros.livro.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.NetLivros.livro.model.Exemplar;

public interface ExemplarRepository extends JpaRepository<Exemplar, UUID> {

	@Query(value="SELECT * FROM exemplar e  WHERE e.titulo_livro =:titulo AND e.data_aluguel IS null LIMIT 1", nativeQuery=true)
	Optional<Exemplar> findExemplarByTitulo(@Param("titulo") String titulo);

	boolean existsByTituloLivro(String titulo);
	
	@Query(value="SELECT COUNT(*) FROM exemplar e WHERE e.titulo_livro =:titulo AND e.data_aluguel IS null", nativeQuery = true)
	Integer getQuantidadeDisponivel(@Param("titulo") String titulo);


	void deleteAllByTituloLivro(String titulo);
	

}
