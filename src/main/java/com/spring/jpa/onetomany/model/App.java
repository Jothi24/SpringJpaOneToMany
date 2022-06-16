package com.spring.jpa.onetomany.model;

import javax.persistence.*;

@Entity
@Table(name = "apps")
public class App {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  
  private String appName;

  public Long getId() {
    return id;
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  @Override
  public String toString() {
    return "App [id=" + id + ", appname=" + appName + "]";
  }
}
