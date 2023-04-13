package com.assignment.PhoneBookApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Documents {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String textdocument;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTextdocument() {
		return textdocument;
	}
	public void setTextdocument(String textdocument) {
		this.textdocument = textdocument;
	}
    
	
      
}
