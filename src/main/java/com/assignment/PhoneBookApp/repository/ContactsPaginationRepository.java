package com.assignment.PhoneBookApp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.assignment.PhoneBookApp.model.Contacts;

public interface ContactsPaginationRepository extends PagingAndSortingRepository<Contacts, Integer>{

}
