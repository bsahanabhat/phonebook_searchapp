package com.assignment.PhoneBookApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.PhoneBookApp.model.Contacts;
import com.assignment.PhoneBookApp.model.Documents;
import com.assignment.PhoneBookApp.repository.DocumentsPaginationRepository;
import com.assignment.PhoneBookApp.repository.DocumentRepository;

@Controller
public class DocumentController {

	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	DocumentsPaginationRepository documentsPaginationRepository;

	@GetMapping("/api/documents")
	public String contacts(Model model, Pageable pageable,
			@RequestParam(name = "page", required = false, defaultValue = "1") String selected_page,
			@RequestParam("search") Optional<String> search) {
		Page<Documents> pages;
		String searchtext="";
		if(!search.isPresent()) {
			 pages = documentsPaginationRepository.findAll(pageable);
		}
		else {
			searchtext=search.get();
			 pages = documentsPaginationRepository.searchTextDocument("%"+searchtext+"%",pageable);
		}

		model.addAttribute("number", pages.getNumber());
		model.addAttribute("totalPages", pages.getTotalPages());
		model.addAttribute("totalElements", pages.getTotalElements());
		model.addAttribute("size", pages.getSize());
		model.addAttribute("list_search_result", pages.getContent());

		model.addAttribute("selected_page", selected_page);
		model.addAttribute("searchtext", searchtext);

		return "documents";
	}

	// Add new Document
	@Caching(evict = {
            @CacheEvict(value="/api/documents", allEntries=true)})
	@PostMapping(value = "/api/documents")
	public String createContact(@ModelAttribute("document") Documents document, BindingResult result, Model model) {

		try {
			documentRepository.save(document);
			return "redirect:/api/documents";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}


}
