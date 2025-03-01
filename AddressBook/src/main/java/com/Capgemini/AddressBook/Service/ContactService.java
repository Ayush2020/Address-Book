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

    // Get all contacts as DTOs
    public List<ContactDTO> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Save contact
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
}