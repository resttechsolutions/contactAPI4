package com.resttechsolutions.contactapi4.service.implementation;

import com.resttechsolutions.contactapi4.entity.Contact;
import com.resttechsolutions.contactapi4.exception.ContactException;
import com.resttechsolutions.contactapi4.repository.ContactRepository;
import com.resttechsolutions.contactapi4.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements IService<Contact, Long> {

    private ContactRepository cr;
    private Logger log = LoggerFactory.getLogger(ContactService.class);

    public ContactService(ContactRepository cr) {
        this.cr = cr;
    }

    @Override
    public Contact create(Contact contact) throws ContactException {
        log.info("Contact create init");

//        log.debug(contact.toString());
        try{
            return cr.save(contact);
        }  catch (Exception e){
            log.error(String.format("Contact %s can\'t be created", contact));
            e.printStackTrace();
            throw new ContactException(String.format("Contact %s can\'t be created", contact));
        }
    }

    @Override
    public Contact findById(Long aLong) throws ContactException {
        log.info("Contact findById init");

        try{
            return cr.findById(aLong).orElse(new Contact());
        }  catch (Exception e){
            log.error(String.format("Contact id %d can\'t be found", aLong));
            e.printStackTrace();
            throw new ContactException(String.format("Contact id %d can\'t be found", aLong));
        }
    }

    @Override
    public List<Contact> findAll() throws ContactException {
        log.info("Contact findAll init");

        try{
            return cr.findAll();
        }  catch (Exception e){
            log.error("Contact list can\'t be loaded");
            e.printStackTrace();
            throw new ContactException("Contact list can\'t be loaded");
        }
    }

    @Override
    public Contact update(Contact contact) throws ContactException {
        log.info("Contact update init");

        try{
            return cr.save(contact);
        }  catch (Exception e){
            log.error(String.format("Contact %s can\'t be updated", contact));
            e.printStackTrace();
            throw new ContactException(String.format("Contact %s can\'t be updated", contact));
        }
    }

    @Override
    public void delete(Long aLong) throws ContactException {
        log.info("Contact delete init");

        try{
            cr.delete(cr.findById(aLong).get());
        }  catch (Exception e){
            log.error(String.format("Contact id %d can\'t be deleted", aLong));
            e.printStackTrace();
            throw new ContactException(String.format("Contact id %d can\'t be deleted", aLong));
        }
    }
}
