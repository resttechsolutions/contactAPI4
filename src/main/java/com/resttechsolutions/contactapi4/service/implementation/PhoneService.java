package com.resttechsolutions.contactapi4.service.implementation;

import com.resttechsolutions.contactapi4.entity.Phone;
import com.resttechsolutions.contactapi4.exception.PhoneException;
import com.resttechsolutions.contactapi4.repository.PhoneRepository;
import com.resttechsolutions.contactapi4.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService implements IService<Phone,Long> {

    private PhoneRepository pr;
    private Logger log = LoggerFactory.getLogger(PhoneService.class);

    public PhoneService(PhoneRepository pr) {
        this.pr = pr;
    }

    @Override
    public Phone create(Phone phone) throws PhoneException {
        log.info("Contact create init");

        try{
            return pr.save(phone);
        } catch (Exception e){
            log.error(String.format("The phone %s can\'t be created"));
            e.printStackTrace();
            throw new PhoneException(String.format("The phone %s can\'t be created", phone));
        }
    }

    @Override
    public Phone findById(Long aLong)  throws PhoneException{
        log.info("Contact findById init");

        try{
            return pr.findById(aLong).orElse(new Phone());
        } catch (Exception e){
            log.error(String.format("The phone id %d can\'t be found", aLong));
            e.printStackTrace();
            throw new PhoneException(String.format("The phone id %d can\'t be found", aLong));
        }
    }

    @Override
    public List<Phone> findAll()  throws PhoneException{
        log.info("Contact findAll init");

        try{
            return pr.findAll();
        } catch (Exception e){
            log.error("The phone list can\'t be loaded");
            e.printStackTrace();
            throw new PhoneException("The phone list can\'t be loaded");
        }
    }

    @Override
    public Phone update(Phone phone)  throws PhoneException {
        log.info("Phone update init");

        try{
            return pr.save(phone);
        } catch (Exception e){
            log.error(String.format("The phone %s can\'t be updated", phone));
            e.printStackTrace();
            throw new PhoneException(String.format("The phone %s can\'t be updated", phone));
        }
    }

    @Override
    public void delete(Long aLong)  throws PhoneException {
        log.info("Phone delete init");

        try{
            pr.delete(pr.findById(aLong).get());
        }catch (Exception e){
            log.error(String.format("The phone id %d cant\'t be created", aLong));
            e.printStackTrace();
            throw new PhoneException(String.format("The phone id %d cant\'t be created", aLong));
        }
    }
}
