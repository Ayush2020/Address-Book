package com.Capgemini.AddressBook.Service;

import com.Capgemini.AddressBook.DTO.ContactDTO;
import com.Capgemini.AddressBook.Entity.Contact;
import com.Capgemini.AddressBook.Repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private AddressRepo contactRepository;

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
        return contactRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Add a new contact
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    // Get contact by ID
    public ContactDTO getContactById(Long id) {
        Contact contact = contactRepository.findById(id).orElse(null);
        return (contact != null) ? convertToDTO(contact) : null;
    }

    // Update contact by ID
    public Contact updateContact(Long id, Contact updatedContact) {
        Contact existingContact = contactRepository.findById(id).orElse(null);
        if (existingContact != null) {
            existingContact.setName(updatedContact.getName());
            existingContact.setPhone(updatedContact.getPhone());
            existingContact.setEmail(updatedContact.getEmail());
            existingContact.setAddress(updatedContact.getAddress());
            return contactRepository.save(existingContact);
        }
        return null;
    }

    // Delete contact by ID
    public boolean deleteContact(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
