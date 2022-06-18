package com.BikkadIT.PhoneBookApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.PhoneBookApplication.model.Contact;
import com.BikkadIT.PhoneBookApplication.service.ContactServiceI;

@RestController
public class ContactController {

	@Autowired
	private ContactServiceI contactServiceI;
	
	@PostMapping(value = "/saveContact", consumes = "Application/json")
	public ResponseEntity<String> saveContact(@RequestBody Contact contact){
		
		boolean save = contactServiceI.saveContact(contact);
		if(save == true) {
			String msg="Contact Saved Successfully";
			return new ResponseEntity<String>(msg,HttpStatus.CREATED); 
		}else {
			String msg="Contact Not Saved Successfully";
			return new ResponseEntity<String>(msg,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(value = "/getAllContact", produces = "Application/json")
	public ResponseEntity<List<Contact>> getAllContact(){
		
		List<Contact> list = contactServiceI.getAllContact();
		if(list != null) {
			return new ResponseEntity<List<Contact>> (list,HttpStatus.OK);
		}else {
			String msg="Data Not Found";
		return new ResponseEntity (msg,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/edit")
	public ResponseEntity<Contact> getContactById(@PathVariable Integer cid){
		Contact contact = contactServiceI.getContactById(cid);
		if(contact != null) {
			return new ResponseEntity<Contact> (contact,HttpStatus.OK);
		}else {
			String s="Record Not Found";
		return new ResponseEntity (s,HttpStatus.BAD_REQUEST);
		}
	}
}
