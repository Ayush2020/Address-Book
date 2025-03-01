package com.Capgemini.AddressBook.Controller;

import com.Capgemini.AddressBook.Entity.Contact;
import com.Capgemini.AddressBook.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(contactService.addContact(contact), HttpStatus.CREATED);
    }

    // Get all contacts
    @GetMapping("/get/All")
    public ResponseEntity<List<Contact>> getAllContacts() {
        return new ResponseEntity<>(contactService.getAllContacts(), HttpStatus.OK);
    }

    // Get paginated contacts
    @GetMapping("/get/paginated")
    public ResponseEntity<Page<Contact>> getContactsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(contactService.getContactsPaginated(page, size), HttpStatus.OK);
    }

    // Get contact by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactService.getContactById(id);
        return contact != null ? new ResponseEntity<>(contact, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Search contacts by name
    @GetMapping("/search")
    public ResponseEntity<List<Contact>> searchContacts(@RequestParam String name) {
        return new ResponseEntity<>(contactService.searchContactsByName(name), HttpStatus.OK);
    }

    // Update contact by ID
    @PutMapping("/put/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        Contact updatedContact = contactService.updateContact(id, contact);
        return updatedContact != null ? new ResponseEntity<>(updatedContact, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete contact by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>("Contact deleted successfully", HttpStatus.OK);
    }
}
