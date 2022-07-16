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

/** This is an auto generated class representing the SWEMonthlyClinicSummary type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "SWEMonthlyClinicSummaries", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class SWEMonthlyClinicSummary implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAMEBWE = field("Namebwe");
  public static final QueryField DATE = field("date");
  public static final QueryField SWE_POSITION = field("SWEPosition");
  public static final QueryField NO_TABLET_USED_AT_DRINKING_STATION1 = field("NoTabletUsedAtDrinkingStation1");
  public static final QueryField NO_TABLET_USED_AT_DRINKING_STATION2 = field("NoTabletUsedAtDrinkingStation2");
  public static final QueryField NO_TABLET_USED_AT_DRINKING_STATION3 = field("NoTabletUsedAtDrinkingStation3");
  public static final QueryField COMPLETED = field("Completed");
  public static final QueryField LAT = field("Lat");
  public static final QueryField LNG = field("Lng");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Namebwe;
  private final @ModelField(targetType="AWSDate") Temporal.Date date;
  private final @ModelField(targetType="String", isRequired = true) String SWEPosition;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoTabletUsedAtDrinkingStation1;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoTabletUsedAtDrinkingStation2;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoTabletUsedAtDrinkingStation3;
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
  
  public String getSwePosition() {
      return SWEPosition;
  }
  
  public Integer getNoTabletUsedAtDrinkingStation1() {
      return NoTabletUsedAtDrinkingStation1;
  }
  
  public Integer getNoTabletUsedAtDrinkingStation2() {
      return NoTabletUsedAtDrinkingStation2;
  }
  
  public Integer getNoTabletUsedAtDrinkingStation3() {
      return NoTabletUsedAtDrinkingStation3;
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
  
  private SWEMonthlyClinicSummary(String id, String Namebwe, Temporal.Date date, String SWEPosition, Integer NoTabletUsedAtDrinkingStation1, Integer NoTabletUsedAtDrinkingStation2, Integer NoTabletUsedAtDrinkingStation3, Integer Completed, String Lat, String Lng) {
    this.id = id;
    this.Namebwe = Namebwe;
    this.date = date;
    this.SWEPosition = SWEPosition;
    this.NoTabletUsedAtDrinkingStation1 = NoTabletUsedAtDrinkingStation1;
    this.NoTabletUsedAtDrinkingStation2 = NoTabletUsedAtDrinkingStation2;
    this.NoTabletUsedAtDrinkingStation3 = NoTabletUsedAtDrinkingStation3;
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
      SWEMonthlyClinicSummary sweMonthlyClinicSummary = (SWEMonthlyClinicSummary) obj;
      return ObjectsCompat.equals(getId(), sweMonthlyClinicSummary.getId()) &&
              ObjectsCompat.equals(getNamebwe(), sweMonthlyClinicSummary.getNamebwe()) &&
              ObjectsCompat.equals(getDate(), sweMonthlyClinicSummary.getDate()) &&
              ObjectsCompat.equals(getSwePosition(), sweMonthlyClinicSummary.getSwePosition()) &&
              ObjectsCompat.equals(getNoTabletUsedAtDrinkingStation1(), sweMonthlyClinicSummary.getNoTabletUsedAtDrinkingStation1()) &&
              ObjectsCompat.equals(getNoTabletUsedAtDrinkingStation2(), sweMonthlyClinicSummary.getNoTabletUsedAtDrinkingStation2()) &&
              ObjectsCompat.equals(getNoTabletUsedAtDrinkingStation3(), sweMonthlyClinicSummary.getNoTabletUsedAtDrinkingStation3()) &&
              ObjectsCompat.equals(getCompleted(), sweMonthlyClinicSummary.getCompleted()) &&
              ObjectsCompat.equals(getLat(), sweMonthlyClinicSummary.getLat()) &&
              ObjectsCompat.equals(getLng(), sweMonthlyClinicSummary.getLng());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getNamebwe())
      .append(getDate())
      .append(getSwePosition())
      .append(getNoTabletUsedAtDrinkingStation1())
      .append(getNoTabletUsedAtDrinkingStation2())
      .append(getNoTabletUsedAtDrinkingStation3())
      .append(getCompleted())
      .append(getLat())
      .append(getLng())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("SWEMonthlyClinicSummary {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Namebwe=" + String.valueOf(getNamebwe()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("SWEPosition=" + String.valueOf(getSwePosition()) + ", ")
      .append("NoTabletUsedAtDrinkingStation1=" + String.valueOf(getNoTabletUsedAtDrinkingStation1()) + ", ")
      .append("NoTabletUsedAtDrinkingStation2=" + String.valueOf(getNoTabletUsedAtDrinkingStation2()) + ", ")
      .append("NoTabletUsedAtDrinkingStation3=" + String.valueOf(getNoTabletUsedAtDrinkingStation3()) + ", ")
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
  public static SWEMonthlyClinicSummary justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new SWEMonthlyClinicSummary(
      id,
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
      SWEPosition,
      NoTabletUsedAtDrinkingStation1,
      NoTabletUsedAtDrinkingStation2,
      NoTabletUsedAtDrinkingStation3,
      Completed,
      Lat,
      Lng);
  }
  public interface NamebweStep {
    SwePositionStep namebwe(String namebwe);
  }
  

  public interface SwePositionStep {
    NoTabletUsedAtDrinkingStation1Step swePosition(String swePosition);
  }
  

  public interface NoTabletUsedAtDrinkingStation1Step {
    NoTabletUsedAtDrinkingStation2Step noTabletUsedAtDrinkingStation1(Integer noTabletUsedAtDrinkingStation1);
  }
  

  public interface NoTabletUsedAtDrinkingStation2Step {
    NoTabletUsedAtDrinkingStation3Step noTabletUsedAtDrinkingStation2(Integer noTabletUsedAtDrinkingStation2);
  }
  

  public interface NoTabletUsedAtDrinkingStation3Step {
    CompletedStep noTabletUsedAtDrinkingStation3(Integer noTabletUsedAtDrinkingStation3);
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
    SWEMonthlyClinicSummary build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep date(Temporal.Date date);
  }
  

  public static class Builder implements NamebweStep, SwePositionStep, NoTabletUsedAtDrinkingStation1Step, NoTabletUsedAtDrinkingStation2Step, NoTabletUsedAtDrinkingStation3Step, CompletedStep, LatStep, LngStep, BuildStep {
    private String id;
    private String Namebwe;
    private String SWEPosition;
    private Integer NoTabletUsedAtDrinkingStation1;
    private Integer NoTabletUsedAtDrinkingStation2;
    private Integer NoTabletUsedAtDrinkingStation3;
    private Integer Completed;
    private String Lat;
    private String Lng;
    private Temporal.Date date;
    @Override
     public SWEMonthlyClinicSummary build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new SWEMonthlyClinicSummary(
          id,
          Namebwe,
          date,
          SWEPosition,
          NoTabletUsedAtDrinkingStation1,
          NoTabletUsedAtDrinkingStation2,
          NoTabletUsedAtDrinkingStation3,
          Completed,
          Lat,
          Lng);
    }
    
    @Override
     public SwePositionStep namebwe(String namebwe) {
        Objects.requireNonNull(namebwe);
        this.Namebwe = namebwe;
        return this;
    }
    
    @Override
     public NoTabletUsedAtDrinkingStation1Step swePosition(String swePosition) {
        Objects.requireNonNull(swePosition);
        this.SWEPosition = swePosition;
        return this;
    }
    
    @Override
     public NoTabletUsedAtDrinkingStation2Step noTabletUsedAtDrinkingStation1(Integer noTabletUsedAtDrinkingStation1) {
        Objects.requireNonNull(noTabletUsedAtDrinkingStation1);
        this.NoTabletUsedAtDrinkingStation1 = noTabletUsedAtDrinkingStation1;
        return this;
    }
    
    @Override
     public NoTabletUsedAtDrinkingStation3Step noTabletUsedAtDrinkingStation2(Integer noTabletUsedAtDrinkingStation2) {
        Objects.requireNonNull(noTabletUsedAtDrinkingStation2);
        this.NoTabletUsedAtDrinkingStation2 = noTabletUsedAtDrinkingStation2;
        return this;
    }
    
    @Override
     public CompletedStep noTabletUsedAtDrinkingStation3(Integer noTabletUsedAtDrinkingStation3) {
        Objects.requireNonNull(noTabletUsedAtDrinkingStation3);
        this.NoTabletUsedAtDrinkingStation3 = noTabletUsedAtDrinkingStation3;
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
    private CopyOfBuilder(String id, String namebwe, Temporal.Date date, String swePosition, Integer noTabletUsedAtDrinkingStation1, Integer noTabletUsedAtDrinkingStation2, Integer noTabletUsedAtDrinkingStation3, Integer completed, String lat, String lng) {
      super.id(id);
      super.namebwe(namebwe)
        .swePosition(swePosition)
        .noTabletUsedAtDrinkingStation1(noTabletUsedAtDrinkingStation1)
        .noTabletUsedAtDrinkingStation2(noTabletUsedAtDrinkingStation2)
        .noTabletUsedAtDrinkingStation3(noTabletUsedAtDrinkingStation3)
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
     public CopyOfBuilder swePosition(String swePosition) {
      return (CopyOfBuilder) super.swePosition(swePosition);
    }
    
    @Override
     public CopyOfBuilder noTabletUsedAtDrinkingStation1(Integer noTabletUsedAtDrinkingStation1) {
      return (CopyOfBuilder) super.noTabletUsedAtDrinkingStation1(noTabletUsedAtDrinkingStation1);
    }
    
    @Override
     public CopyOfBuilder noTabletUsedAtDrinkingStation2(Integer noTabletUsedAtDrinkingStation2) {
      return (CopyOfBuilder) super.noTabletUsedAtDrinkingStation2(noTabletUsedAtDrinkingStation2);
    }
    
    @Override
     public CopyOfBuilder noTabletUsedAtDrinkingStation3(Integer noTabletUsedAtDrinkingStation3) {
      return (CopyOfBuilder) super.noTabletUsedAtDrinkingStation3(noTabletUsedAtDrinkingStation3);
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
