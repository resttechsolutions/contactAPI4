package com.resttechsolutions.contactapi4.config;

import com.resttechsolutions.contactapi4.entity.Address;
import com.resttechsolutions.contactapi4.entity.Contact;
import com.resttechsolutions.contactapi4.entity.Phone;
import com.resttechsolutions.contactapi4.service.IService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestData implements ApplicationRunner {

    private IService<Contact, Long> cs;
    private IService<Address, Long> as;
    private IService<Phone, Long> ps;

    public TestData(IService<Contact, Long> cs, IService<Address, Long> as, IService<Phone, Long> ps) {
        this.cs = cs;
        this.as = as;
        this.ps = ps;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Contact contact = new Contact();


        contact.setName("Toad");
        contact.setEmail("toad@gmail.com");

        Phone phone = new Phone("Toad", "809-995-5888", contact);
        Address address = new Address("Alma Rosa 1", "Santo Domingo Este", contact);

        cs.create(contact);
        as.create(address);
        ps.create(phone);
    }
}
