package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the Fullname type in your schema. */
public final class Fullname {
  private final String surname;
  private final String givenname;
  public String getSurname() {
      return surname;
  }
  
  public String getGivenname() {
      return givenname;
  }
  
  private Fullname(String surname, String givenname) {
    this.surname = surname;
    this.givenname = givenname;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Fullname fullname = (Fullname) obj;
      return ObjectsCompat.equals(getSurname(), fullname.getSurname()) &&
              ObjectsCompat.equals(getGivenname(), fullname.getGivenname());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getSurname())
      .append(getGivenname())
      .toString()
      .hashCode();
  }
  
  public static SurnameStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(surname,
      givenname);
  }
  public interface SurnameStep {
    GivennameStep surname(String surname);
  }
  

  public interface GivennameStep {
    BuildStep givenname(String givenname);
  }
  

  public interface BuildStep {
    Fullname build();
  }
  

  public static class Builder implements SurnameStep, GivennameStep, BuildStep {
    private String surname;
    private String givenname;
    @Override
     public Fullname build() {
        
        return new Fullname(
          surname,
          givenname);
    }
    
    @Override
     public GivennameStep surname(String surname) {
        Objects.requireNonNull(surname);
        this.surname = surname;
        return this;
    }
    
    @Override
     public BuildStep givenname(String givenname) {
        Objects.requireNonNull(givenname);
        this.givenname = givenname;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String surname, String givenname) {
      super.surname(surname)
        .givenname(givenname);
    }
    
    @Override
     public CopyOfBuilder surname(String surname) {
      return (CopyOfBuilder) super.surname(surname);
    }
    
    @Override
     public CopyOfBuilder givenname(String givenname) {
      return (CopyOfBuilder) super.givenname(givenname);
    }
  }
  
}
