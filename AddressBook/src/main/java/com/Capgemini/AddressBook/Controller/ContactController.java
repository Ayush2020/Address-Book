package com.Capgemini.AddressBook.Controller;

import com.Capgemini.AddressBook.DTO.ContactDTO;
import com.Capgemini.AddressBook.Entity.Contact;
import com.Capgemini.AddressBook.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Add a new contact
    @PostMapping("/post")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.saveContact(contact));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }
}
