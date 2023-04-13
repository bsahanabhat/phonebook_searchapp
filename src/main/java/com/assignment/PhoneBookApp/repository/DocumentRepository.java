package com.assignment.PhoneBookApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.PhoneBookApp.model.Documents;

public interface DocumentRepository extends JpaRepository<Documents, Integer> {

}
