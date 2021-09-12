package com.resttechsolutions.contactapi4.resource;

import com.resttechsolutions.contactapi4.entity.Address;
import com.resttechsolutions.contactapi4.entity.Contact;
import com.resttechsolutions.contactapi4.entity.Phone;
import com.resttechsolutions.contactapi4.exception.ContactException;
import com.resttechsolutions.contactapi4.resource.dto.Response;
import com.resttechsolutions.contactapi4.resource.dto.rerquest.AddressRequestDto;
import com.resttechsolutions.contactapi4.resource.dto.rerquest.ContactRequestDto;
import com.resttechsolutions.contactapi4.resource.dto.rerquest.PhoneRequestDto;
import com.resttechsolutions.contactapi4.resource.dto.response.ContactResponseDTO;
import com.resttechsolutions.contactapi4.service.IService;
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

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactResource extends AbstractResource<ContactRequestDto, Long>{

    private Logger log = LoggerFactory.getLogger(ContactResource.class);

    private ModelMapper modelMapper;
    private IService<Contact, Long> contactServie;
    private IService<Phone, Long> phoneService;
    private IService<Address, Long> addressService;

  public ContactResource(ModelMapper modelMapper, IService<Contact, Long> contactServie, IService<Phone, Long> phoneService, IService<Address, Long> addressService) {
    this.modelMapper = modelMapper;
    this.contactServie = contactServie;
    this.phoneService = phoneService;
    this.addressService = addressService;
  }

  @Override
    public ResponseEntity<Response> create(@RequestBody ContactRequestDto request, Response response) {
        log.info("ContactResource create init");
        try {

            Contact contact = GenericMapper.map(request, Contact.class, modelMapper);
            contact.setPhones(GenericMapper.mapCollection(request.getPhones(), Phone.class, modelMapper));
            contact.setAddresses(GenericMapper.mapCollection(request.getAddresses(), Address.class, modelMapper));

            response.response.put("Data", GenericMapper.map(this.contactServie.create(contact), ContactResponseDTO.class, modelMapper));
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
            response.response.put("Data", GenericMapper.map(this.contactServie.findById(id), ContactResponseDTO.class, this.modelMapper));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ContactException e) {

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();


        } catch (Exception e) {
          e.printStackTrace();
        }
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response> findAll(Response response) {
        log.info("ContactResource findAll init");

        try{
            response.response.put("Data", GenericMapper.mapCollection(this.contactServie.findAll(), ContactResponseDTO.class, this.modelMapper));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ContactException e) {

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

        } catch (Exception e) {
          e.printStackTrace();
        }
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response> update(@RequestBody ContactRequestDto request, Response response) {
        log.info("ContactResource update init");

        try{
            Contact contact = GenericMapper.map(request, Contact.class, this.modelMapper);

            response.response.put("Data", GenericMapper.map(this.contactServie.update(contact), ContactResponseDTO.class,this.modelMapper));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (ContactException e) {
            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

        } catch (Exception e) {
          e.printStackTrace();
        }
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Response> delete(@PathVariable  Long id, Response response) {
        log.info("ContactResource delete init");

        try {
            this.contactServie.delete(id);

            response.response.put("Data", null);
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ContactException e) {

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();


        } catch (Exception e) {
          e.printStackTrace();
        }

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
