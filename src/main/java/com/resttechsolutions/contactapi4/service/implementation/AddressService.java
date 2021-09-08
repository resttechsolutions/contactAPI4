package com.resttechsolutions.contactapi4.service.implementation;

import com.resttechsolutions.contactapi4.entity.Address;
import com.resttechsolutions.contactapi4.exception.AddressException;
import com.resttechsolutions.contactapi4.repository.AddressRepository;
import com.resttechsolutions.contactapi4.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IService<Address, Long> {

    private AddressRepository ar;
    private Logger log = LoggerFactory.getLogger(AddressService.class);

    public AddressService(AddressRepository ar) {
        this.ar = ar;
    }

    @Override
    public Address create(Address address) throws AddressException {
        log.info("Address create init");

        try{
            return ar.save(address);
        }  catch (Exception e){
            log.error(String.format("Address %s can\'t be created", address));
            e.printStackTrace();
            throw new AddressException(String.format("Address %s can\'t be created", address));
        }
    }

    @Override
    public Address findById(Long aLong) throws AddressException {
        log.info("Address findById init");

        try{
            return ar.findById(aLong).orElse(new Address());
        }  catch (Exception e){
            log.error(String.format("Address id %d can\'t be found", aLong));
            e.printStackTrace();
            throw new AddressException(String.format("Address id %d can\'t be found", aLong));
        }
    }

    @Override
    public List<Address> findAll() throws AddressException {
        log.info("Address findAll init");

        try{
            return ar.findAll();
        }  catch (Exception e){
            log.error("Address list can\'t be loaded");
            e.printStackTrace();
            throw new AddressException("Address list can\'t be loaded");
        }
    }

    @Override
    public Address update(Address address) throws AddressException {
        log.info("Address update init");

        try{
            return ar.save(address);
        }  catch (Exception e){
            log.error(String.format("Address %s can\'t be updated", address));
            e.printStackTrace();
            throw new AddressException(String.format("Address %s can\'t be updated", address));
        }
    }

    @Override
    public void delete(Long aLong) throws AddressException {
        log.info("Address delete init");

        try{
            ar.delete(ar.findById(aLong).get());
        }  catch (Exception e){
            log.error(String.format("Address id %d can\'t be deleted", aLong));
            e.printStackTrace();
            throw new AddressException(String.format("Address id %d can\'t be deleted", aLong));
        }
    }
}
