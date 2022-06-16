package com.spring.jpa.onetomany.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.jpa.onetomany.exception.ResourceNotFoundException;
import com.spring.jpa.onetomany.model.App;
import com.spring.jpa.onetomany.model.Mobile;
import com.spring.jpa.onetomany.repository.AppRepository;
import com.spring.jpa.onetomany.repository.MobileRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AppController {

  @Autowired
  private MobileRepository mobileRepository;

  @Autowired
  private AppRepository appRepository;

  @GetMapping("/mobiles/{mobileId}/apps")
  public ResponseEntity<List<App>> getAllAppsByMobileId(@PathVariable(value = "mobileId") Long mobileId) {    
    Mobile mobile = mobileRepository.findById(mobileId)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Mobile with id = " + mobileId));

    List<App> apps = new ArrayList<App>();
    apps.addAll(mobile.getApps());
    
    return new ResponseEntity<>(apps, HttpStatus.OK);
  }

  @GetMapping("/apps/{id}")
  public ResponseEntity<App> getAppsByMobileId(@PathVariable(value = "id") Long id) {
    App app = appRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found App with id = " + id));

    return new ResponseEntity<>(app, HttpStatus.OK);
  }

  @PostMapping("/mobiles/{mobileId}/apps")
  public ResponseEntity<App> createApp(@PathVariable(value = "mobileId") Long mobileId,
      @RequestBody App appRequest) {
    App app = mobileRepository.findById(mobileId).map(mobile -> {
    	mobile.getApps().add(appRequest);
      return appRepository.save(appRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found Mobile with id = " + mobileId));

    return new ResponseEntity<>(app, HttpStatus.CREATED);
  }

  @PutMapping("/apps/{id}")
  public ResponseEntity<App> updateApp(@PathVariable("id") long id, @RequestBody App appRequest) {
    App app = appRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("AppId " + id + "not found"));

    app.setAppName(appRequest.getAppName());

    return new ResponseEntity<>(appRepository.save(app), HttpStatus.OK);
  }

  @DeleteMapping("/apps/{id}")
  public ResponseEntity<HttpStatus> deleteApp(@PathVariable("id") long id) {
    appRepository.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @DeleteMapping("/mobiles/{mobileId}/apps")
  public ResponseEntity<List<App>> deleteAllAppsOfMobile(@PathVariable(value = "mobileId") Long mobileId) {
    Mobile mobile = mobileRepository.findById(mobileId)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Mobile with id = " + mobileId));
    
    mobile.removeApps();
    mobileRepository.save(mobile);
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
