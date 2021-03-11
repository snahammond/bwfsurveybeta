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

/** This is an auto generated class representing the VolunteerMonthlySummary type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "VolunteerMonthlySummaries", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class VolunteerMonthlySummary implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAMEBWE = field("Namebwe");
  public static final QueryField NAMEVOL = field("Namevol");
  public static final QueryField DATE = field("date");
  public static final QueryField NO_WATER_SAMPLE_TAKEN = field("NoWaterSampleTaken");
  public static final QueryField NO_SURVEYS_COMPLETED = field("NoSurveysCompleted");
  public static final QueryField NO_HEALTH_CHECK = field("NoHealthCheck");
  public static final QueryField NO_LSN1_TAUGHT = field("NoLsn1Taught");
  public static final QueryField NO_PERSONS_TAUGHT_LESSON1 = field("NoPersonsTaughtLesson1");
  public static final QueryField NO_LSN2_TAUGHT = field("NoLsn2Taught");
  public static final QueryField NO_PERSONS_TAUGHT_LESSON2 = field("NoPersonsTaughtLesson2");
  public static final QueryField NO_LSN3_TAUGHT = field("NoLsn3Taught");
  public static final QueryField NO_PERSONS_TAUGHT_LESSON3 = field("NoPersonsTaughtLesson3");
  public static final QueryField NO_LSN4_TAUGHT = field("NoLsn4Taught");
  public static final QueryField NO_PERSONS_TAUGHT_LESSON4 = field("NoPersonsTaughtLesson4");
  public static final QueryField NO_PERSONS_TAUGHT = field("NoPersonsTaught");
  public static final QueryField NO_HOUSEHOLD_RECEIVING_CHLORINE_SUPPLIES = field("NoHouseholdReceivingChlorineSupplies");
  public static final QueryField NO_LIQUID_CHLORINE_DISTRIBUTED = field("NoLiquidChlorineDistributed");
  public static final QueryField NO_CHLORINE_TABLETS_DISTRIBUTED = field("NoChlorineTabletsDistributed");
  public static final QueryField NO_WATER_STORAGE_CONTAINERS_DISTRIBUTED = field("NoWaterStorageContainersDistributed");
  public static final QueryField NO_SCHOOL_VISITS = field("NoSchoolVisits");
  public static final QueryField NO_PUBLIC_SERVICE_MESSAGES_AIRED = field("NoPublicServiceMessagesAired");
  public static final QueryField COMPLETED = field("Completed");
  public static final QueryField LAT = field("Lat");
  public static final QueryField LNG = field("Lng");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Namebwe;
  private final @ModelField(targetType="String", isRequired = true) String Namevol;
  private final @ModelField(targetType="AWSDate") Temporal.Date date;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoWaterSampleTaken;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoSurveysCompleted;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHealthCheck;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn1Taught;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoPersonsTaughtLesson1;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn2Taught;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoPersonsTaughtLesson2;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn3Taught;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoPersonsTaughtLesson3;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn4Taught;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoPersonsTaughtLesson4;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoPersonsTaught;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHouseholdReceivingChlorineSupplies;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLiquidChlorineDistributed;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoChlorineTabletsDistributed;
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
  
  public String getNamevol() {
      return Namevol;
  }
  
  public Temporal.Date getDate() {
      return date;
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
  
  public Integer getNoLsn1Taught() {
      return NoLsn1Taught;
  }
  
  public Integer getNoPersonsTaughtLesson1() {
      return NoPersonsTaughtLesson1;
  }
  
  public Integer getNoLsn2Taught() {
      return NoLsn2Taught;
  }
  
  public Integer getNoPersonsTaughtLesson2() {
      return NoPersonsTaughtLesson2;
  }
  
  public Integer getNoLsn3Taught() {
      return NoLsn3Taught;
  }
  
  public Integer getNoPersonsTaughtLesson3() {
      return NoPersonsTaughtLesson3;
  }
  
  public Integer getNoLsn4Taught() {
      return NoLsn4Taught;
  }
  
  public Integer getNoPersonsTaughtLesson4() {
      return NoPersonsTaughtLesson4;
  }
  
  public Integer getNoPersonsTaught() {
      return NoPersonsTaught;
  }
  
  public Integer getNoHouseholdReceivingChlorineSupplies() {
      return NoHouseholdReceivingChlorineSupplies;
  }
  
  public Integer getNoLiquidChlorineDistributed() {
      return NoLiquidChlorineDistributed;
  }
  
  public Integer getNoChlorineTabletsDistributed() {
      return NoChlorineTabletsDistributed;
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
  
  private VolunteerMonthlySummary(String id, String Namebwe, String Namevol, Temporal.Date date, Integer NoWaterSampleTaken, Integer NoSurveysCompleted, Integer NoHealthCheck, Integer NoLsn1Taught, Integer NoPersonsTaughtLesson1, Integer NoLsn2Taught, Integer NoPersonsTaughtLesson2, Integer NoLsn3Taught, Integer NoPersonsTaughtLesson3, Integer NoLsn4Taught, Integer NoPersonsTaughtLesson4, Integer NoPersonsTaught, Integer NoHouseholdReceivingChlorineSupplies, Integer NoLiquidChlorineDistributed, Integer NoChlorineTabletsDistributed, Integer NoWaterStorageContainersDistributed, Integer NoSchoolVisits, Integer NoPublicServiceMessagesAired, Integer Completed, String Lat, String Lng) {
    this.id = id;
    this.Namebwe = Namebwe;
    this.Namevol = Namevol;
    this.date = date;
    this.NoWaterSampleTaken = NoWaterSampleTaken;
    this.NoSurveysCompleted = NoSurveysCompleted;
    this.NoHealthCheck = NoHealthCheck;
    this.NoLsn1Taught = NoLsn1Taught;
    this.NoPersonsTaughtLesson1 = NoPersonsTaughtLesson1;
    this.NoLsn2Taught = NoLsn2Taught;
    this.NoPersonsTaughtLesson2 = NoPersonsTaughtLesson2;
    this.NoLsn3Taught = NoLsn3Taught;
    this.NoPersonsTaughtLesson3 = NoPersonsTaughtLesson3;
    this.NoLsn4Taught = NoLsn4Taught;
    this.NoPersonsTaughtLesson4 = NoPersonsTaughtLesson4;
    this.NoPersonsTaught = NoPersonsTaught;
    this.NoHouseholdReceivingChlorineSupplies = NoHouseholdReceivingChlorineSupplies;
    this.NoLiquidChlorineDistributed = NoLiquidChlorineDistributed;
    this.NoChlorineTabletsDistributed = NoChlorineTabletsDistributed;
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
      VolunteerMonthlySummary volunteerMonthlySummary = (VolunteerMonthlySummary) obj;
      return ObjectsCompat.equals(getId(), volunteerMonthlySummary.getId()) &&
              ObjectsCompat.equals(getNamebwe(), volunteerMonthlySummary.getNamebwe()) &&
              ObjectsCompat.equals(getNamevol(), volunteerMonthlySummary.getNamevol()) &&
              ObjectsCompat.equals(getDate(), volunteerMonthlySummary.getDate()) &&
              ObjectsCompat.equals(getNoWaterSampleTaken(), volunteerMonthlySummary.getNoWaterSampleTaken()) &&
              ObjectsCompat.equals(getNoSurveysCompleted(), volunteerMonthlySummary.getNoSurveysCompleted()) &&
              ObjectsCompat.equals(getNoHealthCheck(), volunteerMonthlySummary.getNoHealthCheck()) &&
              ObjectsCompat.equals(getNoLsn1Taught(), volunteerMonthlySummary.getNoLsn1Taught()) &&
              ObjectsCompat.equals(getNoPersonsTaughtLesson1(), volunteerMonthlySummary.getNoPersonsTaughtLesson1()) &&
              ObjectsCompat.equals(getNoLsn2Taught(), volunteerMonthlySummary.getNoLsn2Taught()) &&
              ObjectsCompat.equals(getNoPersonsTaughtLesson2(), volunteerMonthlySummary.getNoPersonsTaughtLesson2()) &&
              ObjectsCompat.equals(getNoLsn3Taught(), volunteerMonthlySummary.getNoLsn3Taught()) &&
              ObjectsCompat.equals(getNoPersonsTaughtLesson3(), volunteerMonthlySummary.getNoPersonsTaughtLesson3()) &&
              ObjectsCompat.equals(getNoLsn4Taught(), volunteerMonthlySummary.getNoLsn4Taught()) &&
              ObjectsCompat.equals(getNoPersonsTaughtLesson4(), volunteerMonthlySummary.getNoPersonsTaughtLesson4()) &&
              ObjectsCompat.equals(getNoPersonsTaught(), volunteerMonthlySummary.getNoPersonsTaught()) &&
              ObjectsCompat.equals(getNoHouseholdReceivingChlorineSupplies(), volunteerMonthlySummary.getNoHouseholdReceivingChlorineSupplies()) &&
              ObjectsCompat.equals(getNoLiquidChlorineDistributed(), volunteerMonthlySummary.getNoLiquidChlorineDistributed()) &&
              ObjectsCompat.equals(getNoChlorineTabletsDistributed(), volunteerMonthlySummary.getNoChlorineTabletsDistributed()) &&
              ObjectsCompat.equals(getNoWaterStorageContainersDistributed(), volunteerMonthlySummary.getNoWaterStorageContainersDistributed()) &&
              ObjectsCompat.equals(getNoSchoolVisits(), volunteerMonthlySummary.getNoSchoolVisits()) &&
              ObjectsCompat.equals(getNoPublicServiceMessagesAired(), volunteerMonthlySummary.getNoPublicServiceMessagesAired()) &&
              ObjectsCompat.equals(getCompleted(), volunteerMonthlySummary.getCompleted()) &&
              ObjectsCompat.equals(getLat(), volunteerMonthlySummary.getLat()) &&
              ObjectsCompat.equals(getLng(), volunteerMonthlySummary.getLng());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getNamebwe())
      .append(getNamevol())
      .append(getDate())
      .append(getNoWaterSampleTaken())
      .append(getNoSurveysCompleted())
      .append(getNoHealthCheck())
      .append(getNoLsn1Taught())
      .append(getNoPersonsTaughtLesson1())
      .append(getNoLsn2Taught())
      .append(getNoPersonsTaughtLesson2())
      .append(getNoLsn3Taught())
      .append(getNoPersonsTaughtLesson3())
      .append(getNoLsn4Taught())
      .append(getNoPersonsTaughtLesson4())
      .append(getNoPersonsTaught())
      .append(getNoHouseholdReceivingChlorineSupplies())
      .append(getNoLiquidChlorineDistributed())
      .append(getNoChlorineTabletsDistributed())
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
      .append("VolunteerMonthlySummary {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Namebwe=" + String.valueOf(getNamebwe()) + ", ")
      .append("Namevol=" + String.valueOf(getNamevol()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("NoWaterSampleTaken=" + String.valueOf(getNoWaterSampleTaken()) + ", ")
      .append("NoSurveysCompleted=" + String.valueOf(getNoSurveysCompleted()) + ", ")
      .append("NoHealthCheck=" + String.valueOf(getNoHealthCheck()) + ", ")
      .append("NoLsn1Taught=" + String.valueOf(getNoLsn1Taught()) + ", ")
      .append("NoPersonsTaughtLesson1=" + String.valueOf(getNoPersonsTaughtLesson1()) + ", ")
      .append("NoLsn2Taught=" + String.valueOf(getNoLsn2Taught()) + ", ")
      .append("NoPersonsTaughtLesson2=" + String.valueOf(getNoPersonsTaughtLesson2()) + ", ")
      .append("NoLsn3Taught=" + String.valueOf(getNoLsn3Taught()) + ", ")
      .append("NoPersonsTaughtLesson3=" + String.valueOf(getNoPersonsTaughtLesson3()) + ", ")
      .append("NoLsn4Taught=" + String.valueOf(getNoLsn4Taught()) + ", ")
      .append("NoPersonsTaughtLesson4=" + String.valueOf(getNoPersonsTaughtLesson4()) + ", ")
      .append("NoPersonsTaught=" + String.valueOf(getNoPersonsTaught()) + ", ")
      .append("NoHouseholdReceivingChlorineSupplies=" + String.valueOf(getNoHouseholdReceivingChlorineSupplies()) + ", ")
      .append("NoLiquidChlorineDistributed=" + String.valueOf(getNoLiquidChlorineDistributed()) + ", ")
      .append("NoChlorineTabletsDistributed=" + String.valueOf(getNoChlorineTabletsDistributed()) + ", ")
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
  public static VolunteerMonthlySummary justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new VolunteerMonthlySummary(
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
      Namevol,
      date,
      NoWaterSampleTaken,
      NoSurveysCompleted,
      NoHealthCheck,
      NoLsn1Taught,
      NoPersonsTaughtLesson1,
      NoLsn2Taught,
      NoPersonsTaughtLesson2,
      NoLsn3Taught,
      NoPersonsTaughtLesson3,
      NoLsn4Taught,
      NoPersonsTaughtLesson4,
      NoPersonsTaught,
      NoHouseholdReceivingChlorineSupplies,
      NoLiquidChlorineDistributed,
      NoChlorineTabletsDistributed,
      NoWaterStorageContainersDistributed,
      NoSchoolVisits,
      NoPublicServiceMessagesAired,
      Completed,
      Lat,
      Lng);
  }
  public interface NamebweStep {
    NamevolStep namebwe(String namebwe);
  }
  

  public interface NamevolStep {
    NoWaterSampleTakenStep namevol(String namevol);
  }
  

  public interface NoWaterSampleTakenStep {
    NoSurveysCompletedStep noWaterSampleTaken(Integer noWaterSampleTaken);
  }
  

  public interface NoSurveysCompletedStep {
    NoHealthCheckStep noSurveysCompleted(Integer noSurveysCompleted);
  }
  

  public interface NoHealthCheckStep {
    NoLsn1TaughtStep noHealthCheck(Integer noHealthCheck);
  }
  

  public interface NoLsn1TaughtStep {
    NoPersonsTaughtLesson1Step noLsn1Taught(Integer noLsn1Taught);
  }
  

  public interface NoPersonsTaughtLesson1Step {
    NoLsn2TaughtStep noPersonsTaughtLesson1(Integer noPersonsTaughtLesson1);
  }
  

  public interface NoLsn2TaughtStep {
    NoPersonsTaughtLesson2Step noLsn2Taught(Integer noLsn2Taught);
  }
  

  public interface NoPersonsTaughtLesson2Step {
    NoLsn3TaughtStep noPersonsTaughtLesson2(Integer noPersonsTaughtLesson2);
  }
  

  public interface NoLsn3TaughtStep {
    NoPersonsTaughtLesson3Step noLsn3Taught(Integer noLsn3Taught);
  }
  

  public interface NoPersonsTaughtLesson3Step {
    NoLsn4TaughtStep noPersonsTaughtLesson3(Integer noPersonsTaughtLesson3);
  }
  

  public interface NoLsn4TaughtStep {
    NoPersonsTaughtLesson4Step noLsn4Taught(Integer noLsn4Taught);
  }
  

  public interface NoPersonsTaughtLesson4Step {
    NoPersonsTaughtStep noPersonsTaughtLesson4(Integer noPersonsTaughtLesson4);
  }
  

  public interface NoPersonsTaughtStep {
    NoHouseholdReceivingChlorineSuppliesStep noPersonsTaught(Integer noPersonsTaught);
  }
  

  public interface NoHouseholdReceivingChlorineSuppliesStep {
    NoLiquidChlorineDistributedStep noHouseholdReceivingChlorineSupplies(Integer noHouseholdReceivingChlorineSupplies);
  }
  

  public interface NoLiquidChlorineDistributedStep {
    NoChlorineTabletsDistributedStep noLiquidChlorineDistributed(Integer noLiquidChlorineDistributed);
  }
  

  public interface NoChlorineTabletsDistributedStep {
    NoWaterStorageContainersDistributedStep noChlorineTabletsDistributed(Integer noChlorineTabletsDistributed);
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
    VolunteerMonthlySummary build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep date(Temporal.Date date);
  }
  

  public static class Builder implements NamebweStep, NamevolStep, NoWaterSampleTakenStep, NoSurveysCompletedStep, NoHealthCheckStep, NoLsn1TaughtStep, NoPersonsTaughtLesson1Step, NoLsn2TaughtStep, NoPersonsTaughtLesson2Step, NoLsn3TaughtStep, NoPersonsTaughtLesson3Step, NoLsn4TaughtStep, NoPersonsTaughtLesson4Step, NoPersonsTaughtStep, NoHouseholdReceivingChlorineSuppliesStep, NoLiquidChlorineDistributedStep, NoChlorineTabletsDistributedStep, NoWaterStorageContainersDistributedStep, NoSchoolVisitsStep, NoPublicServiceMessagesAiredStep, CompletedStep, LatStep, LngStep, BuildStep {
    private String id;
    private String Namebwe;
    private String Namevol;
    private Integer NoWaterSampleTaken;
    private Integer NoSurveysCompleted;
    private Integer NoHealthCheck;
    private Integer NoLsn1Taught;
    private Integer NoPersonsTaughtLesson1;
    private Integer NoLsn2Taught;
    private Integer NoPersonsTaughtLesson2;
    private Integer NoLsn3Taught;
    private Integer NoPersonsTaughtLesson3;
    private Integer NoLsn4Taught;
    private Integer NoPersonsTaughtLesson4;
    private Integer NoPersonsTaught;
    private Integer NoHouseholdReceivingChlorineSupplies;
    private Integer NoLiquidChlorineDistributed;
    private Integer NoChlorineTabletsDistributed;
    private Integer NoWaterStorageContainersDistributed;
    private Integer NoSchoolVisits;
    private Integer NoPublicServiceMessagesAired;
    private Integer Completed;
    private String Lat;
    private String Lng;
    private Temporal.Date date;
    @Override
     public VolunteerMonthlySummary build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new VolunteerMonthlySummary(
          id,
          Namebwe,
          Namevol,
          date,
          NoWaterSampleTaken,
          NoSurveysCompleted,
          NoHealthCheck,
          NoLsn1Taught,
          NoPersonsTaughtLesson1,
          NoLsn2Taught,
          NoPersonsTaughtLesson2,
          NoLsn3Taught,
          NoPersonsTaughtLesson3,
          NoLsn4Taught,
          NoPersonsTaughtLesson4,
          NoPersonsTaught,
          NoHouseholdReceivingChlorineSupplies,
          NoLiquidChlorineDistributed,
          NoChlorineTabletsDistributed,
          NoWaterStorageContainersDistributed,
          NoSchoolVisits,
          NoPublicServiceMessagesAired,
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
     public NoWaterSampleTakenStep namevol(String namevol) {
        Objects.requireNonNull(namevol);
        this.Namevol = namevol;
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
     public NoLsn1TaughtStep noHealthCheck(Integer noHealthCheck) {
        Objects.requireNonNull(noHealthCheck);
        this.NoHealthCheck = noHealthCheck;
        return this;
    }
    
    @Override
     public NoPersonsTaughtLesson1Step noLsn1Taught(Integer noLsn1Taught) {
        Objects.requireNonNull(noLsn1Taught);
        this.NoLsn1Taught = noLsn1Taught;
        return this;
    }
    
    @Override
     public NoLsn2TaughtStep noPersonsTaughtLesson1(Integer noPersonsTaughtLesson1) {
        Objects.requireNonNull(noPersonsTaughtLesson1);
        this.NoPersonsTaughtLesson1 = noPersonsTaughtLesson1;
        return this;
    }
    
    @Override
     public NoPersonsTaughtLesson2Step noLsn2Taught(Integer noLsn2Taught) {
        Objects.requireNonNull(noLsn2Taught);
        this.NoLsn2Taught = noLsn2Taught;
        return this;
    }
    
    @Override
     public NoLsn3TaughtStep noPersonsTaughtLesson2(Integer noPersonsTaughtLesson2) {
        Objects.requireNonNull(noPersonsTaughtLesson2);
        this.NoPersonsTaughtLesson2 = noPersonsTaughtLesson2;
        return this;
    }
    
    @Override
     public NoPersonsTaughtLesson3Step noLsn3Taught(Integer noLsn3Taught) {
        Objects.requireNonNull(noLsn3Taught);
        this.NoLsn3Taught = noLsn3Taught;
        return this;
    }
    
    @Override
     public NoLsn4TaughtStep noPersonsTaughtLesson3(Integer noPersonsTaughtLesson3) {
        Objects.requireNonNull(noPersonsTaughtLesson3);
        this.NoPersonsTaughtLesson3 = noPersonsTaughtLesson3;
        return this;
    }
    
    @Override
     public NoPersonsTaughtLesson4Step noLsn4Taught(Integer noLsn4Taught) {
        Objects.requireNonNull(noLsn4Taught);
        this.NoLsn4Taught = noLsn4Taught;
        return this;
    }
    
    @Override
     public NoPersonsTaughtStep noPersonsTaughtLesson4(Integer noPersonsTaughtLesson4) {
        Objects.requireNonNull(noPersonsTaughtLesson4);
        this.NoPersonsTaughtLesson4 = noPersonsTaughtLesson4;
        return this;
    }
    
    @Override
     public NoHouseholdReceivingChlorineSuppliesStep noPersonsTaught(Integer noPersonsTaught) {
        Objects.requireNonNull(noPersonsTaught);
        this.NoPersonsTaught = noPersonsTaught;
        return this;
    }
    
    @Override
     public NoLiquidChlorineDistributedStep noHouseholdReceivingChlorineSupplies(Integer noHouseholdReceivingChlorineSupplies) {
        Objects.requireNonNull(noHouseholdReceivingChlorineSupplies);
        this.NoHouseholdReceivingChlorineSupplies = noHouseholdReceivingChlorineSupplies;
        return this;
    }
    
    @Override
     public NoChlorineTabletsDistributedStep noLiquidChlorineDistributed(Integer noLiquidChlorineDistributed) {
        Objects.requireNonNull(noLiquidChlorineDistributed);
        this.NoLiquidChlorineDistributed = noLiquidChlorineDistributed;
        return this;
    }
    
    @Override
     public NoWaterStorageContainersDistributedStep noChlorineTabletsDistributed(Integer noChlorineTabletsDistributed) {
        Objects.requireNonNull(noChlorineTabletsDistributed);
        this.NoChlorineTabletsDistributed = noChlorineTabletsDistributed;
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
    private CopyOfBuilder(String id, String namebwe, String namevol, Temporal.Date date, Integer noWaterSampleTaken, Integer noSurveysCompleted, Integer noHealthCheck, Integer noLsn1Taught, Integer noPersonsTaughtLesson1, Integer noLsn2Taught, Integer noPersonsTaughtLesson2, Integer noLsn3Taught, Integer noPersonsTaughtLesson3, Integer noLsn4Taught, Integer noPersonsTaughtLesson4, Integer noPersonsTaught, Integer noHouseholdReceivingChlorineSupplies, Integer noLiquidChlorineDistributed, Integer noChlorineTabletsDistributed, Integer noWaterStorageContainersDistributed, Integer noSchoolVisits, Integer noPublicServiceMessagesAired, Integer completed, String lat, String lng) {
      super.id(id);
      super.namebwe(namebwe)
        .namevol(namevol)
        .noWaterSampleTaken(noWaterSampleTaken)
        .noSurveysCompleted(noSurveysCompleted)
        .noHealthCheck(noHealthCheck)
        .noLsn1Taught(noLsn1Taught)
        .noPersonsTaughtLesson1(noPersonsTaughtLesson1)
        .noLsn2Taught(noLsn2Taught)
        .noPersonsTaughtLesson2(noPersonsTaughtLesson2)
        .noLsn3Taught(noLsn3Taught)
        .noPersonsTaughtLesson3(noPersonsTaughtLesson3)
        .noLsn4Taught(noLsn4Taught)
        .noPersonsTaughtLesson4(noPersonsTaughtLesson4)
        .noPersonsTaught(noPersonsTaught)
        .noHouseholdReceivingChlorineSupplies(noHouseholdReceivingChlorineSupplies)
        .noLiquidChlorineDistributed(noLiquidChlorineDistributed)
        .noChlorineTabletsDistributed(noChlorineTabletsDistributed)
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
     public CopyOfBuilder namevol(String namevol) {
      return (CopyOfBuilder) super.namevol(namevol);
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
     public CopyOfBuilder noLsn1Taught(Integer noLsn1Taught) {
      return (CopyOfBuilder) super.noLsn1Taught(noLsn1Taught);
    }
    
    @Override
     public CopyOfBuilder noPersonsTaughtLesson1(Integer noPersonsTaughtLesson1) {
      return (CopyOfBuilder) super.noPersonsTaughtLesson1(noPersonsTaughtLesson1);
    }
    
    @Override
     public CopyOfBuilder noLsn2Taught(Integer noLsn2Taught) {
      return (CopyOfBuilder) super.noLsn2Taught(noLsn2Taught);
    }
    
    @Override
     public CopyOfBuilder noPersonsTaughtLesson2(Integer noPersonsTaughtLesson2) {
      return (CopyOfBuilder) super.noPersonsTaughtLesson2(noPersonsTaughtLesson2);
    }
    
    @Override
     public CopyOfBuilder noLsn3Taught(Integer noLsn3Taught) {
      return (CopyOfBuilder) super.noLsn3Taught(noLsn3Taught);
    }
    
    @Override
     public CopyOfBuilder noPersonsTaughtLesson3(Integer noPersonsTaughtLesson3) {
      return (CopyOfBuilder) super.noPersonsTaughtLesson3(noPersonsTaughtLesson3);
    }
    
    @Override
     public CopyOfBuilder noLsn4Taught(Integer noLsn4Taught) {
      return (CopyOfBuilder) super.noLsn4Taught(noLsn4Taught);
    }
    
    @Override
     public CopyOfBuilder noPersonsTaughtLesson4(Integer noPersonsTaughtLesson4) {
      return (CopyOfBuilder) super.noPersonsTaughtLesson4(noPersonsTaughtLesson4);
    }
    
    @Override
     public CopyOfBuilder noPersonsTaught(Integer noPersonsTaught) {
      return (CopyOfBuilder) super.noPersonsTaught(noPersonsTaught);
    }
    
    @Override
     public CopyOfBuilder noHouseholdReceivingChlorineSupplies(Integer noHouseholdReceivingChlorineSupplies) {
      return (CopyOfBuilder) super.noHouseholdReceivingChlorineSupplies(noHouseholdReceivingChlorineSupplies);
    }
    
    @Override
     public CopyOfBuilder noLiquidChlorineDistributed(Integer noLiquidChlorineDistributed) {
      return (CopyOfBuilder) super.noLiquidChlorineDistributed(noLiquidChlorineDistributed);
    }
    
    @Override
     public CopyOfBuilder noChlorineTabletsDistributed(Integer noChlorineTabletsDistributed) {
      return (CopyOfBuilder) super.noChlorineTabletsDistributed(noChlorineTabletsDistributed);
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
