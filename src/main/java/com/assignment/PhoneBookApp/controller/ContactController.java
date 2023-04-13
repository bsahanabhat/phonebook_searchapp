package com.assignment.PhoneBookApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.assignment.PhoneBookApp.repository.ContactsPaginationRepository;
import com.assignment.PhoneBookApp.repository.ContactRepository;

@Controller
public class ContactController {

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	ContactsPaginationRepository contactsPaginationRepository;

	// home page
	@GetMapping("/")
	public String allcontacts() {

		return "redirect:/api/contacts?page=1";
	}

	// get all contacts defalut first page loading
	@GetMapping("/api/contacts")
	public String contacts(Model model, Pageable pageable,
			@RequestParam(name = "page", required = false, defaultValue = "1") String selected_page) {
		
		Page<Contacts> pages = contactsPaginationRepository.findAll(pageable);

		model.addAttribute("number", pages.getNumber());
		model.addAttribute("totalPages", pages.getTotalPages());
		model.addAttribute("totalElements", pages.getTotalElements());
		model.addAttribute("size", pages.getSize());
		model.addAttribute("list_contacts", pages.getContent());

		model.addAttribute("selected_page", selected_page);

		return "contacts";
	}

	// Add new contact
	@PostMapping(value = "/api/contacts")
	public String createContact(@ModelAttribute("contact") Contacts contact, BindingResult result, Model model) {

		try {
			contactRepository.save(contact);
			return "redirect:/api/contacts";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	// edit contact with id, cannot use PUT /api/contacts/{id}, because jsp/html
	// wont support form submit method other than POST and GET, so used REST API
	// design as POST /api/contacts/{id}/edit
	@PostMapping(value = "/api/contacts/{id}/edit")
	public String editContact(@ModelAttribute("contact") Contacts contact, @PathVariable Integer id) {

		try {
			contact.setId(id);
			contactRepository.save(contact);
			return "redirect:/api/contacts";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	// delete contact, cannot use DELETE /api/contacts/{id}, because jsp/html
	// wont support form submit method other than POST and GET, so used REST API
	// design as POST /api/contacts/{id}/delete
	@PostMapping(value = "/api/contacts/{id}/delete")
	public String deleteContact(@PathVariable Integer id) {

		try {

			contactRepository.deleteById(id);
			return "redirect:/api/contacts";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

}
