package com.example.NetLivros.book.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.NetLivros.book.model.Copy;

public interface CopyRepository extends JpaRepository<Copy, UUID> {

	@Query(value="SELECT * FROM copy c  WHERE c.book_title =:title AND c.date_rent IS null LIMIT 1", nativeQuery=true)
	Optional<Copy> findCopyByTitle(@Param("title") String title);

	boolean existsByBookTitle(String titulo);
	
	@Query(value="SELECT COUNT(*) FROM copy c WHERE c.book_title =:title AND c.date_rent IS null", nativeQuery = true)
	Integer getAvailableQuantity(@Param("title") String title);


	void deleteAllByBookTitle(String title);
	

}
