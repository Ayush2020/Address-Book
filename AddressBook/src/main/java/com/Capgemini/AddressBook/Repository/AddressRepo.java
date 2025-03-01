package com.Capgemini.AddressBook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Capgemini.AddressBook.Entity.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Contact, Long> {
    List<Contact> findByNameContainingIgnoreCase(String name);

}
