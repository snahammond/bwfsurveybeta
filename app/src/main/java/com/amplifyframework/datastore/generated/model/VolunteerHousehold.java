package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the VolunteerHousehold type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "VolunteerHouseholds", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class VolunteerHousehold implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAMEBWE = field("Namebwe");
  public static final QueryField NAMEVOL = field("Namevol");
  public static final QueryField DATE = field("date");
  public static final QueryField COUNTRY = field("Country");
  public static final QueryField COMMUNITY = field("Community");
  public static final QueryField HEAD_HOUSEHOLD_NAME = field("HeadHouseholdName");
  public static final QueryField HOUSEHOLD_LOCATION = field("HouseholdLocation");
  public static final QueryField COMPLETED = field("Completed");
  public static final QueryField LAT = field("Lat");
  public static final QueryField LNG = field("Lng");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Namebwe;
  private final @ModelField(targetType="String", isRequired = true) String Namevol;
  private final @ModelField(targetType="AWSDate") Temporal.Date date;
  private final @ModelField(targetType="String", isRequired = true) String Country;
  private final @ModelField(targetType="String", isRequired = true) String Community;
  private final @ModelField(targetType="String", isRequired = true) String HeadHouseholdName;
  private final @ModelField(targetType="String", isRequired = true) String HouseholdLocation;
  private final @ModelField(targetType="Int", isRequired = true) Integer Completed;
  private final @ModelField(targetType="String", isRequired = true) String Lat;
  private final @ModelField(targetType="String", isRequired = true) String Lng;
  public String getId() {
      return id;
  }
  
  public String getNamebwe() {
      return Namebwe;
  }
  
  public String getNamevol() {
      return Namevol;
  }
  
  public Temporal.Date getDate() {
      return date;
  }
  
  public String getCountry() {
      return Country;
  }
  
  public String getCommunity() {
      return Community;
  }
  
  public String getHeadHouseholdName() {
      return HeadHouseholdName;
  }
  
  public String getHouseholdLocation() {
      return HouseholdLocation;
  }
  
  public Integer getCompleted() {
      return Completed;
  }
  
  public String getLat() {
      return Lat;
  }
  
  public String getLng() {
      return Lng;
  }
  
  private VolunteerHousehold(String id, String Namebwe, String Namevol, Temporal.Date date, String Country, String Community, String HeadHouseholdName, String HouseholdLocation, Integer Completed, String Lat, String Lng) {
    this.id = id;
    this.Namebwe = Namebwe;
    this.Namevol = Namevol;
    this.date = date;
    this.Country = Country;
    this.Community = Community;
    this.HeadHouseholdName = HeadHouseholdName;
    this.HouseholdLocation = HouseholdLocation;
    this.Completed = Completed;
    this.Lat = Lat;
    this.Lng = Lng;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      VolunteerHousehold volunteerHousehold = (VolunteerHousehold) obj;
      return ObjectsCompat.equals(getId(), volunteerHousehold.getId()) &&
              ObjectsCompat.equals(getNamebwe(), volunteerHousehold.getNamebwe()) &&
              ObjectsCompat.equals(getNamevol(), volunteerHousehold.getNamevol()) &&
              ObjectsCompat.equals(getDate(), volunteerHousehold.getDate()) &&
              ObjectsCompat.equals(getCountry(), volunteerHousehold.getCountry()) &&
              ObjectsCompat.equals(getCommunity(), volunteerHousehold.getCommunity()) &&
              ObjectsCompat.equals(getHeadHouseholdName(), volunteerHousehold.getHeadHouseholdName()) &&
              ObjectsCompat.equals(getHouseholdLocation(), volunteerHousehold.getHouseholdLocation()) &&
              ObjectsCompat.equals(getCompleted(), volunteerHousehold.getCompleted()) &&
              ObjectsCompat.equals(getLat(), volunteerHousehold.getLat()) &&
              ObjectsCompat.equals(getLng(), volunteerHousehold.getLng());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getNamebwe())
      .append(getNamevol())
      .append(getDate())
      .append(getCountry())
      .append(getCommunity())
      .append(getHeadHouseholdName())
      .append(getHouseholdLocation())
      .append(getCompleted())
      .append(getLat())
      .append(getLng())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("VolunteerHousehold {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Namebwe=" + String.valueOf(getNamebwe()) + ", ")
      .append("Namevol=" + String.valueOf(getNamevol()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("Country=" + String.valueOf(getCountry()) + ", ")
      .append("Community=" + String.valueOf(getCommunity()) + ", ")
      .append("HeadHouseholdName=" + String.valueOf(getHeadHouseholdName()) + ", ")
      .append("HouseholdLocation=" + String.valueOf(getHouseholdLocation()) + ", ")
      .append("Completed=" + String.valueOf(getCompleted()) + ", ")
      .append("Lat=" + String.valueOf(getLat()) + ", ")
      .append("Lng=" + String.valueOf(getLng()))
      .append("}")
      .toString();
  }
  
  public static NamebweStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static VolunteerHousehold justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new VolunteerHousehold(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      Namebwe,
      Namevol,
      date,
      Country,
      Community,
      HeadHouseholdName,
      HouseholdLocation,
      Completed,
      Lat,
      Lng);
  }
  public interface NamebweStep {
    NamevolStep namebwe(String namebwe);
  }
  

  public interface NamevolStep {
    CountryStep namevol(String namevol);
  }
  

  public interface CountryStep {
    CommunityStep country(String country);
  }
  

  public interface CommunityStep {
    HeadHouseholdNameStep community(String community);
  }
  

  public interface HeadHouseholdNameStep {
    HouseholdLocationStep headHouseholdName(String headHouseholdName);
  }
  

  public interface HouseholdLocationStep {
    CompletedStep householdLocation(String householdLocation);
  }
  

  public interface CompletedStep {
    LatStep completed(Integer completed);
  }
  

  public interface LatStep {
    LngStep lat(String lat);
  }
  

  public interface LngStep {
    BuildStep lng(String lng);
  }
  

  public interface BuildStep {
    VolunteerHousehold build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep date(Temporal.Date date);
  }
  

  public static class Builder implements NamebweStep, NamevolStep, CountryStep, CommunityStep, HeadHouseholdNameStep, HouseholdLocationStep, CompletedStep, LatStep, LngStep, BuildStep {
    private String id;
    private String Namebwe;
    private String Namevol;
    private String Country;
    private String Community;
    private String HeadHouseholdName;
    private String HouseholdLocation;
    private Integer Completed;
    private String Lat;
    private String Lng;
    private Temporal.Date date;
    @Override
     public VolunteerHousehold build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new VolunteerHousehold(
          id,
          Namebwe,
          Namevol,
          date,
          Country,
          Community,
          HeadHouseholdName,
          HouseholdLocation,
          Completed,
          Lat,
          Lng);
    }
    
    @Override
     public NamevolStep namebwe(String namebwe) {
        Objects.requireNonNull(namebwe);
        this.Namebwe = namebwe;
        return this;
    }
    
    @Override
     public CountryStep namevol(String namevol) {
        Objects.requireNonNull(namevol);
        this.Namevol = namevol;
        return this;
    }
    
    @Override
     public CommunityStep country(String country) {
        Objects.requireNonNull(country);
        this.Country = country;
        return this;
    }
    
    @Override
     public HeadHouseholdNameStep community(String community) {
        Objects.requireNonNull(community);
        this.Community = community;
        return this;
    }
    
    @Override
     public HouseholdLocationStep headHouseholdName(String headHouseholdName) {
        Objects.requireNonNull(headHouseholdName);
        this.HeadHouseholdName = headHouseholdName;
        return this;
    }
    
    @Override
     public CompletedStep householdLocation(String householdLocation) {
        Objects.requireNonNull(householdLocation);
        this.HouseholdLocation = householdLocation;
        return this;
    }
    
    @Override
     public LatStep completed(Integer completed) {
        Objects.requireNonNull(completed);
        this.Completed = completed;
        return this;
    }
    
    @Override
     public LngStep lat(String lat) {
        Objects.requireNonNull(lat);
        this.Lat = lat;
        return this;
    }
    
    @Override
     public BuildStep lng(String lng) {
        Objects.requireNonNull(lng);
        this.Lng = lng;
        return this;
    }
    
    @Override
     public BuildStep date(Temporal.Date date) {
        this.date = date;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String namebwe, String namevol, Temporal.Date date, String country, String community, String headHouseholdName, String householdLocation, Integer completed, String lat, String lng) {
      super.id(id);
      super.namebwe(namebwe)
        .namevol(namevol)
        .country(country)
        .community(community)
        .headHouseholdName(headHouseholdName)
        .householdLocation(householdLocation)
        .completed(completed)
        .lat(lat)
        .lng(lng)
        .date(date);
    }
    
    @Override
     public CopyOfBuilder namebwe(String namebwe) {
      return (CopyOfBuilder) super.namebwe(namebwe);
    }
    
    @Override
     public CopyOfBuilder namevol(String namevol) {
      return (CopyOfBuilder) super.namevol(namevol);
    }
    
    @Override
     public CopyOfBuilder country(String country) {
      return (CopyOfBuilder) super.country(country);
    }
    
    @Override
     public CopyOfBuilder community(String community) {
      return (CopyOfBuilder) super.community(community);
    }
    
    @Override
     public CopyOfBuilder headHouseholdName(String headHouseholdName) {
      return (CopyOfBuilder) super.headHouseholdName(headHouseholdName);
    }
    
    @Override
     public CopyOfBuilder householdLocation(String householdLocation) {
      return (CopyOfBuilder) super.householdLocation(householdLocation);
    }
    
    @Override
     public CopyOfBuilder completed(Integer completed) {
      return (CopyOfBuilder) super.completed(completed);
    }
    
    @Override
     public CopyOfBuilder lat(String lat) {
      return (CopyOfBuilder) super.lat(lat);
    }
    
    @Override
     public CopyOfBuilder lng(String lng) {
      return (CopyOfBuilder) super.lng(lng);
    }
    
    @Override
     public CopyOfBuilder date(Temporal.Date date) {
      return (CopyOfBuilder) super.date(date);
    }
  }
  
}
