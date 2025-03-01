package com.Capgemini.AddressBook.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.Capgemini.AddressBook.Entity.Contact;
import com.Capgemini.AddressBook.Repository.AddressRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private AddressRepo repository;

    public Contact addContact (Contact contact){
        return repository.save(contact);
    }
    public List<Contact> getAllContacts(){
        return repository.findAll();
    }
    public Contact getContactById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Contact updateContact(Long id, Contact contact) {
        Contact existingContact = repository.findById(id).orElse(null);
        if (existingContact != null) {
            existingContact.setName(contact.getName());
            existingContact.setPhone(contact.getPhone());
            existingContact.setEmail(contact.getEmail());
            existingContact.setAddress(contact.getAddress());
            return repository.save(existingContact);
        }
        return null;
    }
    public void deleteContact(Long id){
        repository.deleteById(id);
    }
}
