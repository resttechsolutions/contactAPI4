package com.resttechsolutions.contactapi4.resource;

import com.resttechsolutions.contactapi4.entity.Phone;
import com.resttechsolutions.contactapi4.exception.PhoneException;
import com.resttechsolutions.contactapi4.resource.dto.PhoneDTO;
import com.resttechsolutions.contactapi4.resource.dto.Response;
import com.resttechsolutions.contactapi4.service.implementation.PhoneService;
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
@RequestMapping("/phone")
public class PhoneResource extends AbstractResource<PhoneDTO, Long> {

    private Logger log = LoggerFactory.getLogger(PhoneResource.class);
    private PhoneService ps;
    private ModelMapper mm;

    public PhoneResource(PhoneService ps, ModelMapper mm) {
        this.ps = ps;
        this.mm = mm;
    }

    @Override
    public ResponseEntity<Response> create(@RequestBody PhoneDTO phoneDTO, Response response) {
        log.info("PhoneResource create init");

        try{
            Phone phone = GenericMapper.map(phoneDTO, Phone.class, mm);

            response.response.put("Data", GenericMapper.map(ps.create(phone), PhoneDTO.class, mm));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (PhoneException e) {
            log.error(String.format("There was an error creating the phone %s", phoneDTO));

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> findById(@PathVariable Long id, Response response) {
        log.info("PhoneResource findById init");

        try{
            response.response.put("Data", GenericMapper.map(ps.findById(id), PhoneDTO.class, mm));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (PhoneException e) {
            log.error(String.format("There was an error looking for the phone id %d", id));

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> findAll(Response response) {
        log.info("PhoneResource findAll init");

        try{
            response.response.put("Data", GenericMapper.map(ps.findAll(), PhoneDTO.class, mm));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (PhoneException e) {
            log.error("There was an error loading the phone list");

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> update(@RequestBody PhoneDTO phoneDTO, Response response) {
        log.info("PhoneResource update init");

        try{
            Phone phone = GenericMapper.map(phoneDTO, Phone.class, mm);

            response.response.put("Data", GenericMapper.map(ps.update(phone), PhoneDTO.class, mm));
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (PhoneException e) {
            log.error(String.format("There was an error updating the phone %s", phoneDTO));

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Response> delete(@PathVariable Long id, Response response) {
        log.info("PhoneResource delete init");

        try{
            ps.delete(id);

            response.response.put("Data", null);
            response.response.put("Code", "01");

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (PhoneException e) {
            log.error(String.format("There was an error deleting the phone id %d", id));

            response.response.put("Code", "99");
            response.response.put("Error", true);

            e.printStackTrace();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
