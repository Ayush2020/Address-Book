package com.Capgemini.AddressBook.Service;

import com.Capgemini.AddressBook.Entity.Contact;
import com.Capgemini.AddressBook.Repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private AddressRepo repository;

    public Contact addContact(Contact contact) {
        return repository.save(contact);
    }

    public List<Contact> getAllContacts() {
        return repository.findAll();
    }

    public Page<Contact> getContactsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public Contact getContactById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Contact> searchContactsByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Contact updateContact(Long id, Contact contact) {
        return repository.findById(id).map(existingContact -> {
            existingContact.setName(contact.getName());
            existingContact.setPhone(contact.getPhone());
            existingContact.setEmail(contact.getEmail());
            existingContact.setAddress(contact.getAddress());
            return repository.save(existingContact);
        }).orElse(null);
    }

    public void deleteContact(Long id) {
        repository.deleteById(id);
    }
}
