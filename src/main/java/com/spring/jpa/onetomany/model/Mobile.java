package com.spring.jpa.onetomany.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "mobiles")
public class Mobile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "MobileCompany")
  private String mobileCompany;

  @Column(name = "MobileName")
  private String mobileName;

  

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "mobile_id")
  private Set<App> apps = new HashSet<>();

  public Mobile() {

  }

  public Mobile(String mobileCompany, String mobileName) {
    this.mobileCompany = mobileCompany;
    this.mobileName = mobileName;
   
  }

  public long getId() {
    return id;
  }

  public String getMobileCompany() {
    return mobileCompany;
  }

  public void setMobileCompany(String mobileCompany) {
    this.mobileCompany = mobileCompany;
  }

  public String getMobileName() {
    return mobileName;
  }

  public void setMobileName(String mobileName) {
    this.mobileName = mobileName;
  }

  
  
  public Set<App> getApps() {
    return apps;
  }

  public void setApps(Set<App> apps) {
    this.apps = apps;
  }
  
  public void removeApps() {
    this.apps.clear();
  }
  
  @Override
  public String toString() {
    return "Mobile [id=" + id + ", mobilecompany=" + mobileCompany + ", mobilename=" + mobileName + "]";
  }

}
