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

/** This is an auto generated class representing the SWEMonthlyTotalSummary type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "SWEMonthlyTotalSummaries", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class SWEMonthlyTotalSummary implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAMEBWE = field("Namebwe");
  public static final QueryField DATE = field("date");
  public static final QueryField SWE_POSITION = field("SWEPosition");
  public static final QueryField NO_WATER_SAMPLE_TAKEN = field("NoWaterSampleTaken");
  public static final QueryField NO_SURVEYS_COMPLETED = field("NoSurveysCompleted");
  public static final QueryField NO_HEALTH_CHECK = field("NoHealthCheck");
  public static final QueryField NO_WATER_STORAGE_CONTAINERS_DISTRIBUTED = field("NoWaterStorageContainersDistributed");
  public static final QueryField NO_SCHOOL_VISITS = field("NoSchoolVisits");
  public static final QueryField NO_PUBLIC_SERVICE_MESSAGES_AIRED = field("NoPublicServiceMessagesAired");
  public static final QueryField COMPLETED = field("Completed");
  public static final QueryField LAT = field("Lat");
  public static final QueryField LNG = field("Lng");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Namebwe;
  private final @ModelField(targetType="AWSDate") Temporal.Date date;
  private final @ModelField(targetType="String", isRequired = true) String SWEPosition;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoWaterSampleTaken;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoSurveysCompleted;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHealthCheck;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoWaterStorageContainersDistributed;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoSchoolVisits;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoPublicServiceMessagesAired;
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
  
  public Integer getNoWaterSampleTaken() {
      return NoWaterSampleTaken;
  }
  
  public Integer getNoSurveysCompleted() {
      return NoSurveysCompleted;
  }
  
  public Integer getNoHealthCheck() {
      return NoHealthCheck;
  }
  
  public Integer getNoWaterStorageContainersDistributed() {
      return NoWaterStorageContainersDistributed;
  }
  
  public Integer getNoSchoolVisits() {
      return NoSchoolVisits;
  }
  
  public Integer getNoPublicServiceMessagesAired() {
      return NoPublicServiceMessagesAired;
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
  
  private SWEMonthlyTotalSummary(String id, String Namebwe, Temporal.Date date, String SWEPosition, Integer NoWaterSampleTaken, Integer NoSurveysCompleted, Integer NoHealthCheck, Integer NoWaterStorageContainersDistributed, Integer NoSchoolVisits, Integer NoPublicServiceMessagesAired, Integer Completed, String Lat, String Lng) {
    this.id = id;
    this.Namebwe = Namebwe;
    this.date = date;
    this.SWEPosition = SWEPosition;
    this.NoWaterSampleTaken = NoWaterSampleTaken;
    this.NoSurveysCompleted = NoSurveysCompleted;
    this.NoHealthCheck = NoHealthCheck;
    this.NoWaterStorageContainersDistributed = NoWaterStorageContainersDistributed;
    this.NoSchoolVisits = NoSchoolVisits;
    this.NoPublicServiceMessagesAired = NoPublicServiceMessagesAired;
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
      SWEMonthlyTotalSummary sweMonthlyTotalSummary = (SWEMonthlyTotalSummary) obj;
      return ObjectsCompat.equals(getId(), sweMonthlyTotalSummary.getId()) &&
              ObjectsCompat.equals(getNamebwe(), sweMonthlyTotalSummary.getNamebwe()) &&
              ObjectsCompat.equals(getDate(), sweMonthlyTotalSummary.getDate()) &&
              ObjectsCompat.equals(getSwePosition(), sweMonthlyTotalSummary.getSwePosition()) &&
              ObjectsCompat.equals(getNoWaterSampleTaken(), sweMonthlyTotalSummary.getNoWaterSampleTaken()) &&
              ObjectsCompat.equals(getNoSurveysCompleted(), sweMonthlyTotalSummary.getNoSurveysCompleted()) &&
              ObjectsCompat.equals(getNoHealthCheck(), sweMonthlyTotalSummary.getNoHealthCheck()) &&
              ObjectsCompat.equals(getNoWaterStorageContainersDistributed(), sweMonthlyTotalSummary.getNoWaterStorageContainersDistributed()) &&
              ObjectsCompat.equals(getNoSchoolVisits(), sweMonthlyTotalSummary.getNoSchoolVisits()) &&
              ObjectsCompat.equals(getNoPublicServiceMessagesAired(), sweMonthlyTotalSummary.getNoPublicServiceMessagesAired()) &&
              ObjectsCompat.equals(getCompleted(), sweMonthlyTotalSummary.getCompleted()) &&
              ObjectsCompat.equals(getLat(), sweMonthlyTotalSummary.getLat()) &&
              ObjectsCompat.equals(getLng(), sweMonthlyTotalSummary.getLng());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getNamebwe())
      .append(getDate())
      .append(getSwePosition())
      .append(getNoWaterSampleTaken())
      .append(getNoSurveysCompleted())
      .append(getNoHealthCheck())
      .append(getNoWaterStorageContainersDistributed())
      .append(getNoSchoolVisits())
      .append(getNoPublicServiceMessagesAired())
      .append(getCompleted())
      .append(getLat())
      .append(getLng())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("SWEMonthlyTotalSummary {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Namebwe=" + String.valueOf(getNamebwe()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("SWEPosition=" + String.valueOf(getSwePosition()) + ", ")
      .append("NoWaterSampleTaken=" + String.valueOf(getNoWaterSampleTaken()) + ", ")
      .append("NoSurveysCompleted=" + String.valueOf(getNoSurveysCompleted()) + ", ")
      .append("NoHealthCheck=" + String.valueOf(getNoHealthCheck()) + ", ")
      .append("NoWaterStorageContainersDistributed=" + String.valueOf(getNoWaterStorageContainersDistributed()) + ", ")
      .append("NoSchoolVisits=" + String.valueOf(getNoSchoolVisits()) + ", ")
      .append("NoPublicServiceMessagesAired=" + String.valueOf(getNoPublicServiceMessagesAired()) + ", ")
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
  public static SWEMonthlyTotalSummary justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new SWEMonthlyTotalSummary(
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
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      Namebwe,
      date,
      SWEPosition,
      NoWaterSampleTaken,
      NoSurveysCompleted,
      NoHealthCheck,
      NoWaterStorageContainersDistributed,
      NoSchoolVisits,
      NoPublicServiceMessagesAired,
      Completed,
      Lat,
      Lng);
  }
  public interface NamebweStep {
    SwePositionStep namebwe(String namebwe);
  }
  

  public interface SwePositionStep {
    NoWaterSampleTakenStep swePosition(String swePosition);
  }
  

  public interface NoWaterSampleTakenStep {
    NoSurveysCompletedStep noWaterSampleTaken(Integer noWaterSampleTaken);
  }
  

  public interface NoSurveysCompletedStep {
    NoHealthCheckStep noSurveysCompleted(Integer noSurveysCompleted);
  }
  

  public interface NoHealthCheckStep {
    NoWaterStorageContainersDistributedStep noHealthCheck(Integer noHealthCheck);
  }
  

  public interface NoWaterStorageContainersDistributedStep {
    NoSchoolVisitsStep noWaterStorageContainersDistributed(Integer noWaterStorageContainersDistributed);
  }
  

  public interface NoSchoolVisitsStep {
    NoPublicServiceMessagesAiredStep noSchoolVisits(Integer noSchoolVisits);
  }
  

  public interface NoPublicServiceMessagesAiredStep {
    CompletedStep noPublicServiceMessagesAired(Integer noPublicServiceMessagesAired);
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
    SWEMonthlyTotalSummary build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep date(Temporal.Date date);
  }
  

  public static class Builder implements NamebweStep, SwePositionStep, NoWaterSampleTakenStep, NoSurveysCompletedStep, NoHealthCheckStep, NoWaterStorageContainersDistributedStep, NoSchoolVisitsStep, NoPublicServiceMessagesAiredStep, CompletedStep, LatStep, LngStep, BuildStep {
    private String id;
    private String Namebwe;
    private String SWEPosition;
    private Integer NoWaterSampleTaken;
    private Integer NoSurveysCompleted;
    private Integer NoHealthCheck;
    private Integer NoWaterStorageContainersDistributed;
    private Integer NoSchoolVisits;
    private Integer NoPublicServiceMessagesAired;
    private Integer Completed;
    private String Lat;
    private String Lng;
    private Temporal.Date date;
    @Override
     public SWEMonthlyTotalSummary build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new SWEMonthlyTotalSummary(
          id,
          Namebwe,
          date,
          SWEPosition,
          NoWaterSampleTaken,
          NoSurveysCompleted,
          NoHealthCheck,
          NoWaterStorageContainersDistributed,
          NoSchoolVisits,
          NoPublicServiceMessagesAired,
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
     public NoWaterSampleTakenStep swePosition(String swePosition) {
        Objects.requireNonNull(swePosition);
        this.SWEPosition = swePosition;
        return this;
    }
    
    @Override
     public NoSurveysCompletedStep noWaterSampleTaken(Integer noWaterSampleTaken) {
        Objects.requireNonNull(noWaterSampleTaken);
        this.NoWaterSampleTaken = noWaterSampleTaken;
        return this;
    }
    
    @Override
     public NoHealthCheckStep noSurveysCompleted(Integer noSurveysCompleted) {
        Objects.requireNonNull(noSurveysCompleted);
        this.NoSurveysCompleted = noSurveysCompleted;
        return this;
    }
    
    @Override
     public NoWaterStorageContainersDistributedStep noHealthCheck(Integer noHealthCheck) {
        Objects.requireNonNull(noHealthCheck);
        this.NoHealthCheck = noHealthCheck;
        return this;
    }
    
    @Override
     public NoSchoolVisitsStep noWaterStorageContainersDistributed(Integer noWaterStorageContainersDistributed) {
        Objects.requireNonNull(noWaterStorageContainersDistributed);
        this.NoWaterStorageContainersDistributed = noWaterStorageContainersDistributed;
        return this;
    }
    
    @Override
     public NoPublicServiceMessagesAiredStep noSchoolVisits(Integer noSchoolVisits) {
        Objects.requireNonNull(noSchoolVisits);
        this.NoSchoolVisits = noSchoolVisits;
        return this;
    }
    
    @Override
     public CompletedStep noPublicServiceMessagesAired(Integer noPublicServiceMessagesAired) {
        Objects.requireNonNull(noPublicServiceMessagesAired);
        this.NoPublicServiceMessagesAired = noPublicServiceMessagesAired;
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
    private CopyOfBuilder(String id, String namebwe, Temporal.Date date, String swePosition, Integer noWaterSampleTaken, Integer noSurveysCompleted, Integer noHealthCheck, Integer noWaterStorageContainersDistributed, Integer noSchoolVisits, Integer noPublicServiceMessagesAired, Integer completed, String lat, String lng) {
      super.id(id);
      super.namebwe(namebwe)
        .swePosition(swePosition)
        .noWaterSampleTaken(noWaterSampleTaken)
        .noSurveysCompleted(noSurveysCompleted)
        .noHealthCheck(noHealthCheck)
        .noWaterStorageContainersDistributed(noWaterStorageContainersDistributed)
        .noSchoolVisits(noSchoolVisits)
        .noPublicServiceMessagesAired(noPublicServiceMessagesAired)
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
     public CopyOfBuilder noWaterSampleTaken(Integer noWaterSampleTaken) {
      return (CopyOfBuilder) super.noWaterSampleTaken(noWaterSampleTaken);
    }
    
    @Override
     public CopyOfBuilder noSurveysCompleted(Integer noSurveysCompleted) {
      return (CopyOfBuilder) super.noSurveysCompleted(noSurveysCompleted);
    }
    
    @Override
     public CopyOfBuilder noHealthCheck(Integer noHealthCheck) {
      return (CopyOfBuilder) super.noHealthCheck(noHealthCheck);
    }
    
    @Override
     public CopyOfBuilder noWaterStorageContainersDistributed(Integer noWaterStorageContainersDistributed) {
      return (CopyOfBuilder) super.noWaterStorageContainersDistributed(noWaterStorageContainersDistributed);
    }
    
    @Override
     public CopyOfBuilder noSchoolVisits(Integer noSchoolVisits) {
      return (CopyOfBuilder) super.noSchoolVisits(noSchoolVisits);
    }
    
    @Override
     public CopyOfBuilder noPublicServiceMessagesAired(Integer noPublicServiceMessagesAired) {
      return (CopyOfBuilder) super.noPublicServiceMessagesAired(noPublicServiceMessagesAired);
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
