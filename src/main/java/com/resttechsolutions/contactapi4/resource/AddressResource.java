package com.resttechsolutions.contactapi4.resource;

import com.resttechsolutions.contactapi4.entity.Address;
import com.resttechsolutions.contactapi4.exception.AddressException;
import com.resttechsolutions.contactapi4.resource.dto.Response;
import com.resttechsolutions.contactapi4.resource.dto.response.AddressResponseDTO;
import com.resttechsolutions.contactapi4.service.implementation.AddressService;
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

@RestController
@RequestMapping("/address")
public class AddressResource extends AbstractResource<AddressResponseDTO,Long>{

    private AddressService as;
    private Logger log = LoggerFactory.getLogger(AddressResource.class);
    private ModelMapper mm;

    public AddressResource(AddressService as, ModelMapper mm) {
        this.as = as;
        this.mm = mm;
    }

    @Override
    public ResponseEntity<Response> create(@RequestBody AddressResponseDTO addressResponseDTO, Response response) {
        log.info("AddressResource create init");

        try {
            Address address = GenericMapper.map(addressResponseDTO, Address.class, mm);

            response.response.put("Data", GenericMapper.map(as.create(address), AddressResponseDTO.class, mm));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (AddressException e) {
            log.error(String.format("Error creating the address %s", addressResponseDTO));

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> findById(@PathVariable Long id, Response response) {
        log.info("AddressResource findById init");

        try{
            response.response.put("Data", GenericMapper.map(as.findById(id), AddressResponseDTO.class, mm));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AddressException e) {
            log.error(String.format("The Address with id %d can\'t be found", id));

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> findAll(Response response) {
        log.info("AddressResource findAll init");

        try{
            response.response.put("Data", GenericMapper.mapCollection(as.findAll(), AddressResponseDTO.class, mm));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AddressException e) {
            log.error("The address list can\'t be loaded");

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> update(@RequestBody AddressResponseDTO addressResponseDTO, Response response) {
        log.info("AddressResource update init");

        try{
            Address address = GenericMapper.map(addressResponseDTO, Address.class, mm);

            response.response.put("Data", GenericMapper.map(as.update(address), AddressResponseDTO.class, mm));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (AddressException e) {
            log.error(String.format("Error creating the address %s", addressResponseDTO));

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> delete(@PathVariable Long id, Response response) {
        log.info("Address delete init");

        try{
            as.delete(id);
            response.response.put("Data", null);
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (AddressException e) {
            log.error(String.format("There was an error deleting the address with id %d", id));

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
