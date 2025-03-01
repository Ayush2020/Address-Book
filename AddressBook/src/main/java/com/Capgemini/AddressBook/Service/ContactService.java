package com.Capgemini.AddressBook.Service;

import com.Capgemini.AddressBook.DTO.ContactDTO;
import com.Capgemini.AddressBook.Entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private List<Contact> contactList;
    private final AtomicLong counter = new AtomicLong(1); // To generate unique IDs

    public ContactService() {
        this.contactList = new ArrayList<>();
    }

    // Convert Entity to DTO
    private ContactDTO convertToDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setName(contact.getName());
        dto.setPhone(contact.getPhone());
        dto.setEmail(contact.getEmail());
        dto.setAddress(contact.getAddress());
        return dto;
    }

    // Get all contacts (returns DTOs)
    public List<ContactDTO> getAllContacts() {
        return contactList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Add a new contact
    public Contact saveContact(Contact contact) {
        contact.setId(counter.getAndIncrement()); // Assigning unique ID
        contactList.add(contact);
        return contact;
    }

    // Get contact by ID
    public ContactDTO getContactById(Long id) {
        Optional<Contact> contact = contactList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
        return contact.map(this::convertToDTO).orElse(null);
    }

    // Update contact by ID
    public Contact updateContact(Long id, Contact updatedContact) {
        for (Contact contact : contactList) {
            if (contact.getId().equals(id)) {
                contact.setName(updatedContact.getName());
                contact.setPhone(updatedContact.getPhone());
                contact.setEmail(updatedContact.getEmail());
                contact.setAddress(updatedContact.getAddress());
                return contact;
            }
        }
        return null;
    }

    // Delete contact by ID
    public boolean deleteContact(Long id) {
        return contactList.removeIf(contact -> contact.getId().equals(id));
    }
}
