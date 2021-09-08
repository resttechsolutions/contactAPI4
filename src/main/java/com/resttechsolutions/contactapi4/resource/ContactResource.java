package com.resttechsolutions.contactapi4.resource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resttechsolutions.contactapi4.entity.Contact;
import com.resttechsolutions.contactapi4.exception.ContactException;
import com.resttechsolutions.contactapi4.resource.dto.ContactDTO;
import com.resttechsolutions.contactapi4.resource.dto.Response;
import com.resttechsolutions.contactapi4.service.implementation.ContactService;
import com.resttechsolutions.contactapi4.util.GenericMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/contact")
public class ContactResource extends AbstractResource<ContactDTO, Long>{

    private Logger log = LoggerFactory.getLogger(ContactResource.class);
    private ContactService cs;
    private ModelMapper mm;

    public ContactResource(ContactService cs, ModelMapper mm) {
        this.cs = cs;
        this.mm = mm;
    }

    @Override
    public ResponseEntity<Response> create(@RequestBody ContactDTO contactDTO, Response response) {
        log.info("ContactResource create init");

        ObjectMapper mapper = new ObjectMapper();


//        log.debug(contactDTO.toString());
        log.info(contactDTO.getPhones().size() + " ");
        try {
            ContactDTO dto = mapper.readValue(contactDTO.toString(), ContactDTO.class);
            Contact contact = GenericMapper.map(dto, Contact.class, mm);

            response.response.put("Data", GenericMapper.map(cs.create(contact), ContactDTO.class, mm));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> findById(@PathVariable Long id, Response response) {
        log.info("ContactResourse findById init");

        try{
            response.response.put("Data", GenericMapper.map(cs.findById(id), ContactDTO.class, mm));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ContactException e) {

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> findAll(Response response) {
        log.info("ContactResource findAll init");

        try{
            response.response.put("Data", GenericMapper.mapCollection(cs.findAll(), ContactDTO.class, mm));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ContactException e) {

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> update(@RequestBody ContactDTO contactDTO, Response response) {
        log.info("ContactResource update init");

        try{
            Contact contact = GenericMapper.map(contactDTO, Contact.class, mm);

            response.response.put("Data", GenericMapper.map(cs.update(contact), ContactDTO.class, mm));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (ContactException e) {
            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> delete(@PathVariable  Long id, Response response) {
        log.info("ContactResource delete init");

        try {
            cs.delete(id);

            response.response.put("Data", null);
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ContactException e) {

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
