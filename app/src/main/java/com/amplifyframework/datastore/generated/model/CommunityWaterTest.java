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

/** This is an auto generated class representing the CommunityWaterTest type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "CommunityWaterTests", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class CommunityWaterTest implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAMEBWE = field("Namebwe");
  public static final QueryField DATE = field("date");
  public static final QueryField COUNTRY = field("Country");
  public static final QueryField COMMUNITY = field("Community");
  public static final QueryField COLILERT_DATE_TESTED = field("ColilertDateTested");
  public static final QueryField COLILERT_DATE_READ = field("ColilertDateRead");
  public static final QueryField COLILERT_TEST_RESULT = field("ColilertTestResult");
  public static final QueryField PETRIFILM_DATE_TESTED = field("PetrifilmDateTested");
  public static final QueryField PETRIFILM_DATE_READ = field("PetrifilmDateRead");
  public static final QueryField PETRIFILM_TEST_RESULT = field("PetrifilmTestResult");
  public static final QueryField COMPLETED = field("Completed");
  public static final QueryField LAT = field("Lat");
  public static final QueryField LNG = field("Lng");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Namebwe;
  private final @ModelField(targetType="AWSDate") Temporal.Date date;
  private final @ModelField(targetType="String", isRequired = true) String Country;
  private final @ModelField(targetType="String", isRequired = true) String Community;
  private final @ModelField(targetType="AWSDate", isRequired = true) Temporal.Date ColilertDateTested;
  private final @ModelField(targetType="AWSDate", isRequired = true) Temporal.Date ColilertDateRead;
  private final @ModelField(targetType="String", isRequired = true) String ColilertTestResult;
  private final @ModelField(targetType="AWSDate", isRequired = true) Temporal.Date PetrifilmDateTested;
  private final @ModelField(targetType="AWSDate", isRequired = true) Temporal.Date PetrifilmDateRead;
  private final @ModelField(targetType="String", isRequired = true) String PetrifilmTestResult;
  private final @ModelField(targetType="Int", isRequired = true) Integer Completed;
  private final @ModelField(targetType="String", isRequired = true) String Lat;
  private final @ModelField(targetType="String", isRequired = true) String Lng;
  public String getId() {
      return id;
  }
  
  public String getNamebwe() {
      return Namebwe;
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
  
  public Temporal.Date getColilertDateTested() {
      return ColilertDateTested;
  }
  
  public Temporal.Date getColilertDateRead() {
      return ColilertDateRead;
  }
  
  public String getColilertTestResult() {
      return ColilertTestResult;
  }
  
  public Temporal.Date getPetrifilmDateTested() {
      return PetrifilmDateTested;
  }
  
  public Temporal.Date getPetrifilmDateRead() {
      return PetrifilmDateRead;
  }
  
  public String getPetrifilmTestResult() {
      return PetrifilmTestResult;
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
  
  private CommunityWaterTest(String id, String Namebwe, Temporal.Date date, String Country, String Community, Temporal.Date ColilertDateTested, Temporal.Date ColilertDateRead, String ColilertTestResult, Temporal.Date PetrifilmDateTested, Temporal.Date PetrifilmDateRead, String PetrifilmTestResult, Integer Completed, String Lat, String Lng) {
    this.id = id;
    this.Namebwe = Namebwe;
    this.date = date;
    this.Country = Country;
    this.Community = Community;
    this.ColilertDateTested = ColilertDateTested;
    this.ColilertDateRead = ColilertDateRead;
    this.ColilertTestResult = ColilertTestResult;
    this.PetrifilmDateTested = PetrifilmDateTested;
    this.PetrifilmDateRead = PetrifilmDateRead;
    this.PetrifilmTestResult = PetrifilmTestResult;
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
      CommunityWaterTest communityWaterTest = (CommunityWaterTest) obj;
      return ObjectsCompat.equals(getId(), communityWaterTest.getId()) &&
              ObjectsCompat.equals(getNamebwe(), communityWaterTest.getNamebwe()) &&
              ObjectsCompat.equals(getDate(), communityWaterTest.getDate()) &&
              ObjectsCompat.equals(getCountry(), communityWaterTest.getCountry()) &&
              ObjectsCompat.equals(getCommunity(), communityWaterTest.getCommunity()) &&
              ObjectsCompat.equals(getColilertDateTested(), communityWaterTest.getColilertDateTested()) &&
              ObjectsCompat.equals(getColilertDateRead(), communityWaterTest.getColilertDateRead()) &&
              ObjectsCompat.equals(getColilertTestResult(), communityWaterTest.getColilertTestResult()) &&
              ObjectsCompat.equals(getPetrifilmDateTested(), communityWaterTest.getPetrifilmDateTested()) &&
              ObjectsCompat.equals(getPetrifilmDateRead(), communityWaterTest.getPetrifilmDateRead()) &&
              ObjectsCompat.equals(getPetrifilmTestResult(), communityWaterTest.getPetrifilmTestResult()) &&
              ObjectsCompat.equals(getCompleted(), communityWaterTest.getCompleted()) &&
              ObjectsCompat.equals(getLat(), communityWaterTest.getLat()) &&
              ObjectsCompat.equals(getLng(), communityWaterTest.getLng());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getNamebwe())
      .append(getDate())
      .append(getCountry())
      .append(getCommunity())
      .append(getColilertDateTested())
      .append(getColilertDateRead())
      .append(getColilertTestResult())
      .append(getPetrifilmDateTested())
      .append(getPetrifilmDateRead())
      .append(getPetrifilmTestResult())
      .append(getCompleted())
      .append(getLat())
      .append(getLng())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("CommunityWaterTest {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Namebwe=" + String.valueOf(getNamebwe()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("Country=" + String.valueOf(getCountry()) + ", ")
      .append("Community=" + String.valueOf(getCommunity()) + ", ")
      .append("ColilertDateTested=" + String.valueOf(getColilertDateTested()) + ", ")
      .append("ColilertDateRead=" + String.valueOf(getColilertDateRead()) + ", ")
      .append("ColilertTestResult=" + String.valueOf(getColilertTestResult()) + ", ")
      .append("PetrifilmDateTested=" + String.valueOf(getPetrifilmDateTested()) + ", ")
      .append("PetrifilmDateRead=" + String.valueOf(getPetrifilmDateRead()) + ", ")
      .append("PetrifilmTestResult=" + String.valueOf(getPetrifilmTestResult()) + ", ")
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
  public static CommunityWaterTest justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new CommunityWaterTest(
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
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      Namebwe,
      date,
      Country,
      Community,
      ColilertDateTested,
      ColilertDateRead,
      ColilertTestResult,
      PetrifilmDateTested,
      PetrifilmDateRead,
      PetrifilmTestResult,
      Completed,
      Lat,
      Lng);
  }
  public interface NamebweStep {
    CountryStep namebwe(String namebwe);
  }
  

  public interface CountryStep {
    CommunityStep country(String country);
  }
  

  public interface CommunityStep {
    ColilertDateTestedStep community(String community);
  }
  

  public interface ColilertDateTestedStep {
    ColilertDateReadStep colilertDateTested(Temporal.Date colilertDateTested);
  }
  

  public interface ColilertDateReadStep {
    ColilertTestResultStep colilertDateRead(Temporal.Date colilertDateRead);
  }
  

  public interface ColilertTestResultStep {
    PetrifilmDateTestedStep colilertTestResult(String colilertTestResult);
  }
  

  public interface PetrifilmDateTestedStep {
    PetrifilmDateReadStep petrifilmDateTested(Temporal.Date petrifilmDateTested);
  }
  

  public interface PetrifilmDateReadStep {
    PetrifilmTestResultStep petrifilmDateRead(Temporal.Date petrifilmDateRead);
  }
  

  public interface PetrifilmTestResultStep {
    CompletedStep petrifilmTestResult(String petrifilmTestResult);
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
    CommunityWaterTest build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep date(Temporal.Date date);
  }
  

  public static class Builder implements NamebweStep, CountryStep, CommunityStep, ColilertDateTestedStep, ColilertDateReadStep, ColilertTestResultStep, PetrifilmDateTestedStep, PetrifilmDateReadStep, PetrifilmTestResultStep, CompletedStep, LatStep, LngStep, BuildStep {
    private String id;
    private String Namebwe;
    private String Country;
    private String Community;
    private Temporal.Date ColilertDateTested;
    private Temporal.Date ColilertDateRead;
    private String ColilertTestResult;
    private Temporal.Date PetrifilmDateTested;
    private Temporal.Date PetrifilmDateRead;
    private String PetrifilmTestResult;
    private Integer Completed;
    private String Lat;
    private String Lng;
    private Temporal.Date date;
    @Override
     public CommunityWaterTest build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new CommunityWaterTest(
          id,
          Namebwe,
          date,
          Country,
          Community,
          ColilertDateTested,
          ColilertDateRead,
          ColilertTestResult,
          PetrifilmDateTested,
          PetrifilmDateRead,
          PetrifilmTestResult,
          Completed,
          Lat,
          Lng);
    }
    
    @Override
     public CountryStep namebwe(String namebwe) {
        Objects.requireNonNull(namebwe);
        this.Namebwe = namebwe;
        return this;
    }
    
    @Override
     public CommunityStep country(String country) {
        Objects.requireNonNull(country);
        this.Country = country;
        return this;
    }
    
    @Override
     public ColilertDateTestedStep community(String community) {
        Objects.requireNonNull(community);
        this.Community = community;
        return this;
    }
    
    @Override
     public ColilertDateReadStep colilertDateTested(Temporal.Date colilertDateTested) {
        Objects.requireNonNull(colilertDateTested);
        this.ColilertDateTested = colilertDateTested;
        return this;
    }
    
    @Override
     public ColilertTestResultStep colilertDateRead(Temporal.Date colilertDateRead) {
        Objects.requireNonNull(colilertDateRead);
        this.ColilertDateRead = colilertDateRead;
        return this;
    }
    
    @Override
     public PetrifilmDateTestedStep colilertTestResult(String colilertTestResult) {
        Objects.requireNonNull(colilertTestResult);
        this.ColilertTestResult = colilertTestResult;
        return this;
    }
    
    @Override
     public PetrifilmDateReadStep petrifilmDateTested(Temporal.Date petrifilmDateTested) {
        Objects.requireNonNull(petrifilmDateTested);
        this.PetrifilmDateTested = petrifilmDateTested;
        return this;
    }
    
    @Override
     public PetrifilmTestResultStep petrifilmDateRead(Temporal.Date petrifilmDateRead) {
        Objects.requireNonNull(petrifilmDateRead);
        this.PetrifilmDateRead = petrifilmDateRead;
        return this;
    }
    
    @Override
     public CompletedStep petrifilmTestResult(String petrifilmTestResult) {
        Objects.requireNonNull(petrifilmTestResult);
        this.PetrifilmTestResult = petrifilmTestResult;
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
    private CopyOfBuilder(String id, String namebwe, Temporal.Date date, String country, String community, Temporal.Date colilertDateTested, Temporal.Date colilertDateRead, String colilertTestResult, Temporal.Date petrifilmDateTested, Temporal.Date petrifilmDateRead, String petrifilmTestResult, Integer completed, String lat, String lng) {
      super.id(id);
      super.namebwe(namebwe)
        .country(country)
        .community(community)
        .colilertDateTested(colilertDateTested)
        .colilertDateRead(colilertDateRead)
        .colilertTestResult(colilertTestResult)
        .petrifilmDateTested(petrifilmDateTested)
        .petrifilmDateRead(petrifilmDateRead)
        .petrifilmTestResult(petrifilmTestResult)
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
     public CopyOfBuilder country(String country) {
      return (CopyOfBuilder) super.country(country);
    }
    
    @Override
     public CopyOfBuilder community(String community) {
      return (CopyOfBuilder) super.community(community);
    }
    
    @Override
     public CopyOfBuilder colilertDateTested(Temporal.Date colilertDateTested) {
      return (CopyOfBuilder) super.colilertDateTested(colilertDateTested);
    }
    
    @Override
     public CopyOfBuilder colilertDateRead(Temporal.Date colilertDateRead) {
      return (CopyOfBuilder) super.colilertDateRead(colilertDateRead);
    }
    
    @Override
     public CopyOfBuilder colilertTestResult(String colilertTestResult) {
      return (CopyOfBuilder) super.colilertTestResult(colilertTestResult);
    }
    
    @Override
     public CopyOfBuilder petrifilmDateTested(Temporal.Date petrifilmDateTested) {
      return (CopyOfBuilder) super.petrifilmDateTested(petrifilmDateTested);
    }
    
    @Override
     public CopyOfBuilder petrifilmDateRead(Temporal.Date petrifilmDateRead) {
      return (CopyOfBuilder) super.petrifilmDateRead(petrifilmDateRead);
    }
    
    @Override
     public CopyOfBuilder petrifilmTestResult(String petrifilmTestResult) {
      return (CopyOfBuilder) super.petrifilmTestResult(petrifilmTestResult);
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
