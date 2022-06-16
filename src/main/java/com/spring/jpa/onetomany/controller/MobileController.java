package com.spring.jpa.onetomany.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.jpa.onetomany.exception.ResourceNotFoundException;
import com.spring.jpa.onetomany.model.Mobile;
import com.spring.jpa.onetomany.repository.MobileRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MobileController {

  @Autowired
  MobileRepository mobileRepository;

  @GetMapping("/mobiles")
  public ResponseEntity<List<Mobile>> getAllMobiles(@RequestParam(required = false) String mobileCompany) {
    List<Mobile> mobiles = new ArrayList<Mobile>();

    if (mobileCompany == null)
      mobileRepository.findAll().forEach(mobiles::add);
    else
      mobileRepository.findByMobileCompanyContaining(mobileCompany).forEach(mobiles::add);

    if (mobiles.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(mobiles, HttpStatus.OK);
  }

  @GetMapping("/mobiles/{id}")
  public ResponseEntity<Mobile> getMobileById(@PathVariable("id") long id) {
    Mobile mobile = mobileRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Mobile with id = " + id));

    return new ResponseEntity<>(mobile, HttpStatus.OK);
  }

  @PostMapping("/mobiles")
  public ResponseEntity<Mobile> createMobile(@RequestBody Mobile mobile) {
    Mobile _mobile = mobileRepository.save(new Mobile(mobile.getMobileCompany(), mobile.getMobileName()));
    return new ResponseEntity<>(_mobile, HttpStatus.CREATED);
  }

  @PutMapping("/mobiles/{id}")
  public ResponseEntity<Mobile> updateMobile(@PathVariable("id") long id, @RequestBody Mobile mobile) {
    Mobile _mobile = mobileRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Mobile with id = " + id));

    _mobile.setMobileCompany(mobile.getMobileCompany());
    _mobile.setMobileName(mobile.getMobileName());
    
    
    return new ResponseEntity<>(mobileRepository.save(_mobile), HttpStatus.OK);
  }

  @DeleteMapping("/mobiles/{id}")
  public ResponseEntity<HttpStatus> deleteMobile(@PathVariable("id") long id) {
    mobileRepository.deleteById(id);
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/mobiles")
  public ResponseEntity<HttpStatus> deleteAllMobiles() {
    mobileRepository.deleteAll();
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  
}
