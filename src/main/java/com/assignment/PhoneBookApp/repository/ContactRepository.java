package com.assignment.PhoneBookApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.PhoneBookApp.model.Contacts;

public interface ContactRepository extends JpaRepository<Contacts, Integer> {

}
