package com.Capgemini.AddressBook.Controller;


import com.Capgemini.AddressBook.Entity.Contact;
import com.Capgemini.AddressBook.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import  java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService service;

    @PostMapping("/post")
    public Contact addContact(@RequestBody Contact contact){
        return service.addContact(contact);
    }

    @GetMapping("/get/All")
    public List<Contact> getAllContacts() {
        return service.getAllContacts();
    }

    @GetMapping("/get/{id}")
    public Contact getContactById(@PathVariable Long id) {
        return service.getContactById(id);
    }

    @PutMapping("/put/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        return service.updateContact(id, contact);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id){
        service.deleteContact(id);
        return "Contact Delete";
    }
}
