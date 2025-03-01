package com.Capgemini.AddressBook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Capgemini.AddressBook.Entity.Contact;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Contact, Long> {

}
