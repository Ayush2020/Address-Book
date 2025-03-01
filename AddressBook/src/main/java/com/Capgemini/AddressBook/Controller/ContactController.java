package com.Capgemini.AddressBook.Controller;

import com.Capgemini.AddressBook.DTO.ContactDTO;
import com.Capgemini.AddressBook.Entity.Contact;
import com.Capgemini.AddressBook.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Get all contacts
    @GetMapping("/get/All")
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    // Get contact by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable Long id) {
        ContactDTO contactDTO = contactService.getContactById(id);
        return (contactDTO != null) ? ResponseEntity.ok(contactDTO) : ResponseEntity.notFound().build();
    }

    // Add a new contact
    @PostMapping("/post")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.saveContact(contact));
    }

    // Update contact by ID
    @PutMapping("/put/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        Contact updatedContact = contactService.updateContact(id, contact);
        return (updatedContact != null) ? ResponseEntity.ok(updatedContact) : ResponseEntity.notFound().build();
    }

    // Delete contact by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        return (contactService.deleteContact(id)) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
