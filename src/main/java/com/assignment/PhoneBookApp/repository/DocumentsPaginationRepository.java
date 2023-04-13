package com.assignment.PhoneBookApp.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.assignment.PhoneBookApp.model.Documents;

public interface DocumentsPaginationRepository extends PagingAndSortingRepository<Documents, Integer>{

	@Cacheable("/api/documents")
	@Query(value = "select * from documents where textdocument like LOWER(:textdocument)",nativeQuery = true )
	Page<Documents> searchTextDocument(@Param("textdocument") String textdocument, Pageable pageable);
}
