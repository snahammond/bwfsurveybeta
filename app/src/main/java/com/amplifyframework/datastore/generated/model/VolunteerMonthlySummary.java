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
  public static final QueryField NO_LSN1_TAUGHT_AS_PRIMARY_INSTR = field("NoLsn1TaughtAsPrimaryInstr");
  public static final QueryField NO_LSN1_TAUGHT_ASSISTING_SWE = field("NoLsn1TaughtAssistingSWE");
  public static final QueryField NO_PERSONS_TAUGHT_LESSON1_BY_VOL = field("NoPersonsTaughtLesson1ByVol");
  public static final QueryField NO_LSN2_TAUGHT_AS_PRIMARY_INSTR = field("NoLsn2TaughtAsPrimaryInstr");
  public static final QueryField NO_LSN2_TAUGHT_ASSISTING_SWE = field("NoLsn2TaughtAssistingSWE");
  public static final QueryField NO_PERSONS_TAUGHT_LESSON2_BY_VOL = field("NoPersonsTaughtLesson2ByVol");
  public static final QueryField NO_LSN3_TAUGHT_AS_PRIMARY_INSTR = field("NoLsn3TaughtAsPrimaryInstr");
  public static final QueryField NO_LSN3_TAUGHT_ASSISTING_SWE = field("NoLsn3TaughtAssistingSWE");
  public static final QueryField NO_PERSONS_TAUGHT_LESSON3_BY_VOL = field("NoPersonsTaughtLesson3ByVol");
  public static final QueryField NO_LSN4_TAUGHT_AS_PRIMARY_INSTR = field("NoLsn4TaughtAsPrimaryInstr");
  public static final QueryField NO_LSN4_TAUGHT_ASSISTING_SWE = field("NoLsn4TaughtAssistingSWE");
  public static final QueryField NO_PERSONS_TAUGHT_LESSON4_BY_VOL = field("NoPersonsTaughtLesson4ByVol");
  public static final QueryField NO_PERSONS_TAUGHT_BY_VOL = field("NoPersonsTaughtByVol");
  public static final QueryField COMPLETED = field("Completed");
  public static final QueryField LAT = field("Lat");
  public static final QueryField LNG = field("Lng");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Namebwe;
  private final @ModelField(targetType="String", isRequired = true) String Namevol;
  private final @ModelField(targetType="AWSDate") Temporal.Date date;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoWaterSampleTaken;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn1TaughtAsPrimaryInstr;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn1TaughtAssistingSWE;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoPersonsTaughtLesson1ByVol;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn2TaughtAsPrimaryInstr;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn2TaughtAssistingSWE;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoPersonsTaughtLesson2ByVol;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn3TaughtAsPrimaryInstr;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn3TaughtAssistingSWE;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoPersonsTaughtLesson3ByVol;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn4TaughtAsPrimaryInstr;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn4TaughtAssistingSWE;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoPersonsTaughtLesson4ByVol;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoPersonsTaughtByVol;
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
  
  public Integer getNoLsn1TaughtAsPrimaryInstr() {
      return NoLsn1TaughtAsPrimaryInstr;
  }
  
  public Integer getNoLsn1TaughtAssistingSWE() {
      return NoLsn1TaughtAssistingSWE;
  }
  
  public Integer getNoPersonsTaughtLesson1ByVol() {
      return NoPersonsTaughtLesson1ByVol;
  }
  
  public Integer getNoLsn2TaughtAsPrimaryInstr() {
      return NoLsn2TaughtAsPrimaryInstr;
  }
  
  public Integer getNoLsn2TaughtAssistingSWE() {
      return NoLsn2TaughtAssistingSWE;
  }
  
  public Integer getNoPersonsTaughtLesson2ByVol() {
      return NoPersonsTaughtLesson2ByVol;
  }
  
  public Integer getNoLsn3TaughtAsPrimaryInstr() {
      return NoLsn3TaughtAsPrimaryInstr;
  }
  
  public Integer getNoLsn3TaughtAssistingSWE() {
      return NoLsn3TaughtAssistingSWE;
  }
  
  public Integer getNoPersonsTaughtLesson3ByVol() {
      return NoPersonsTaughtLesson3ByVol;
  }
  
  public Integer getNoLsn4TaughtAsPrimaryInstr() {
      return NoLsn4TaughtAsPrimaryInstr;
  }
  
  public Integer getNoLsn4TaughtAssistingSWE() {
      return NoLsn4TaughtAssistingSWE;
  }
  
  public Integer getNoPersonsTaughtLesson4ByVol() {
      return NoPersonsTaughtLesson4ByVol;
  }
  
  public Integer getNoPersonsTaughtByVol() {
      return NoPersonsTaughtByVol;
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
  
  private VolunteerMonthlySummary(String id, String Namebwe, String Namevol, Temporal.Date date, Integer NoWaterSampleTaken, Integer NoLsn1TaughtAsPrimaryInstr, Integer NoLsn1TaughtAssistingSWE, Integer NoPersonsTaughtLesson1ByVol, Integer NoLsn2TaughtAsPrimaryInstr, Integer NoLsn2TaughtAssistingSWE, Integer NoPersonsTaughtLesson2ByVol, Integer NoLsn3TaughtAsPrimaryInstr, Integer NoLsn3TaughtAssistingSWE, Integer NoPersonsTaughtLesson3ByVol, Integer NoLsn4TaughtAsPrimaryInstr, Integer NoLsn4TaughtAssistingSWE, Integer NoPersonsTaughtLesson4ByVol, Integer NoPersonsTaughtByVol, Integer Completed, String Lat, String Lng) {
    this.id = id;
    this.Namebwe = Namebwe;
    this.Namevol = Namevol;
    this.date = date;
    this.NoWaterSampleTaken = NoWaterSampleTaken;
    this.NoLsn1TaughtAsPrimaryInstr = NoLsn1TaughtAsPrimaryInstr;
    this.NoLsn1TaughtAssistingSWE = NoLsn1TaughtAssistingSWE;
    this.NoPersonsTaughtLesson1ByVol = NoPersonsTaughtLesson1ByVol;
    this.NoLsn2TaughtAsPrimaryInstr = NoLsn2TaughtAsPrimaryInstr;
    this.NoLsn2TaughtAssistingSWE = NoLsn2TaughtAssistingSWE;
    this.NoPersonsTaughtLesson2ByVol = NoPersonsTaughtLesson2ByVol;
    this.NoLsn3TaughtAsPrimaryInstr = NoLsn3TaughtAsPrimaryInstr;
    this.NoLsn3TaughtAssistingSWE = NoLsn3TaughtAssistingSWE;
    this.NoPersonsTaughtLesson3ByVol = NoPersonsTaughtLesson3ByVol;
    this.NoLsn4TaughtAsPrimaryInstr = NoLsn4TaughtAsPrimaryInstr;
    this.NoLsn4TaughtAssistingSWE = NoLsn4TaughtAssistingSWE;
    this.NoPersonsTaughtLesson4ByVol = NoPersonsTaughtLesson4ByVol;
    this.NoPersonsTaughtByVol = NoPersonsTaughtByVol;
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
              ObjectsCompat.equals(getNoLsn1TaughtAsPrimaryInstr(), volunteerMonthlySummary.getNoLsn1TaughtAsPrimaryInstr()) &&
              ObjectsCompat.equals(getNoLsn1TaughtAssistingSWE(), volunteerMonthlySummary.getNoLsn1TaughtAssistingSWE()) &&
              ObjectsCompat.equals(getNoPersonsTaughtLesson1ByVol(), volunteerMonthlySummary.getNoPersonsTaughtLesson1ByVol()) &&
              ObjectsCompat.equals(getNoLsn2TaughtAsPrimaryInstr(), volunteerMonthlySummary.getNoLsn2TaughtAsPrimaryInstr()) &&
              ObjectsCompat.equals(getNoLsn2TaughtAssistingSWE(), volunteerMonthlySummary.getNoLsn2TaughtAssistingSWE()) &&
              ObjectsCompat.equals(getNoPersonsTaughtLesson2ByVol(), volunteerMonthlySummary.getNoPersonsTaughtLesson2ByVol()) &&
              ObjectsCompat.equals(getNoLsn3TaughtAsPrimaryInstr(), volunteerMonthlySummary.getNoLsn3TaughtAsPrimaryInstr()) &&
              ObjectsCompat.equals(getNoLsn3TaughtAssistingSWE(), volunteerMonthlySummary.getNoLsn3TaughtAssistingSWE()) &&
              ObjectsCompat.equals(getNoPersonsTaughtLesson3ByVol(), volunteerMonthlySummary.getNoPersonsTaughtLesson3ByVol()) &&
              ObjectsCompat.equals(getNoLsn4TaughtAsPrimaryInstr(), volunteerMonthlySummary.getNoLsn4TaughtAsPrimaryInstr()) &&
              ObjectsCompat.equals(getNoLsn4TaughtAssistingSWE(), volunteerMonthlySummary.getNoLsn4TaughtAssistingSWE()) &&
              ObjectsCompat.equals(getNoPersonsTaughtLesson4ByVol(), volunteerMonthlySummary.getNoPersonsTaughtLesson4ByVol()) &&
              ObjectsCompat.equals(getNoPersonsTaughtByVol(), volunteerMonthlySummary.getNoPersonsTaughtByVol()) &&
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
      .append(getNoLsn1TaughtAsPrimaryInstr())
      .append(getNoLsn1TaughtAssistingSWE())
      .append(getNoPersonsTaughtLesson1ByVol())
      .append(getNoLsn2TaughtAsPrimaryInstr())
      .append(getNoLsn2TaughtAssistingSWE())
      .append(getNoPersonsTaughtLesson2ByVol())
      .append(getNoLsn3TaughtAsPrimaryInstr())
      .append(getNoLsn3TaughtAssistingSWE())
      .append(getNoPersonsTaughtLesson3ByVol())
      .append(getNoLsn4TaughtAsPrimaryInstr())
      .append(getNoLsn4TaughtAssistingSWE())
      .append(getNoPersonsTaughtLesson4ByVol())
      .append(getNoPersonsTaughtByVol())
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
      .append("NoLsn1TaughtAsPrimaryInstr=" + String.valueOf(getNoLsn1TaughtAsPrimaryInstr()) + ", ")
      .append("NoLsn1TaughtAssistingSWE=" + String.valueOf(getNoLsn1TaughtAssistingSWE()) + ", ")
      .append("NoPersonsTaughtLesson1ByVol=" + String.valueOf(getNoPersonsTaughtLesson1ByVol()) + ", ")
      .append("NoLsn2TaughtAsPrimaryInstr=" + String.valueOf(getNoLsn2TaughtAsPrimaryInstr()) + ", ")
      .append("NoLsn2TaughtAssistingSWE=" + String.valueOf(getNoLsn2TaughtAssistingSWE()) + ", ")
      .append("NoPersonsTaughtLesson2ByVol=" + String.valueOf(getNoPersonsTaughtLesson2ByVol()) + ", ")
      .append("NoLsn3TaughtAsPrimaryInstr=" + String.valueOf(getNoLsn3TaughtAsPrimaryInstr()) + ", ")
      .append("NoLsn3TaughtAssistingSWE=" + String.valueOf(getNoLsn3TaughtAssistingSWE()) + ", ")
      .append("NoPersonsTaughtLesson3ByVol=" + String.valueOf(getNoPersonsTaughtLesson3ByVol()) + ", ")
      .append("NoLsn4TaughtAsPrimaryInstr=" + String.valueOf(getNoLsn4TaughtAsPrimaryInstr()) + ", ")
      .append("NoLsn4TaughtAssistingSWE=" + String.valueOf(getNoLsn4TaughtAssistingSWE()) + ", ")
      .append("NoPersonsTaughtLesson4ByVol=" + String.valueOf(getNoPersonsTaughtLesson4ByVol()) + ", ")
      .append("NoPersonsTaughtByVol=" + String.valueOf(getNoPersonsTaughtByVol()) + ", ")
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
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      Namebwe,
      Namevol,
      date,
      NoWaterSampleTaken,
      NoLsn1TaughtAsPrimaryInstr,
      NoLsn1TaughtAssistingSWE,
      NoPersonsTaughtLesson1ByVol,
      NoLsn2TaughtAsPrimaryInstr,
      NoLsn2TaughtAssistingSWE,
      NoPersonsTaughtLesson2ByVol,
      NoLsn3TaughtAsPrimaryInstr,
      NoLsn3TaughtAssistingSWE,
      NoPersonsTaughtLesson3ByVol,
      NoLsn4TaughtAsPrimaryInstr,
      NoLsn4TaughtAssistingSWE,
      NoPersonsTaughtLesson4ByVol,
      NoPersonsTaughtByVol,
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
    NoLsn1TaughtAsPrimaryInstrStep noWaterSampleTaken(Integer noWaterSampleTaken);
  }
  

  public interface NoLsn1TaughtAsPrimaryInstrStep {
    NoLsn1TaughtAssistingSweStep noLsn1TaughtAsPrimaryInstr(Integer noLsn1TaughtAsPrimaryInstr);
  }
  

  public interface NoLsn1TaughtAssistingSweStep {
    NoPersonsTaughtLesson1ByVolStep noLsn1TaughtAssistingSwe(Integer noLsn1TaughtAssistingSwe);
  }
  

  public interface NoPersonsTaughtLesson1ByVolStep {
    NoLsn2TaughtAsPrimaryInstrStep noPersonsTaughtLesson1ByVol(Integer noPersonsTaughtLesson1ByVol);
  }
  

  public interface NoLsn2TaughtAsPrimaryInstrStep {
    NoLsn2TaughtAssistingSweStep noLsn2TaughtAsPrimaryInstr(Integer noLsn2TaughtAsPrimaryInstr);
  }
  

  public interface NoLsn2TaughtAssistingSweStep {
    NoPersonsTaughtLesson2ByVolStep noLsn2TaughtAssistingSwe(Integer noLsn2TaughtAssistingSwe);
  }
  

  public interface NoPersonsTaughtLesson2ByVolStep {
    NoLsn3TaughtAsPrimaryInstrStep noPersonsTaughtLesson2ByVol(Integer noPersonsTaughtLesson2ByVol);
  }
  

  public interface NoLsn3TaughtAsPrimaryInstrStep {
    NoLsn3TaughtAssistingSweStep noLsn3TaughtAsPrimaryInstr(Integer noLsn3TaughtAsPrimaryInstr);
  }
  

  public interface NoLsn3TaughtAssistingSweStep {
    NoPersonsTaughtLesson3ByVolStep noLsn3TaughtAssistingSwe(Integer noLsn3TaughtAssistingSwe);
  }
  

  public interface NoPersonsTaughtLesson3ByVolStep {
    NoLsn4TaughtAsPrimaryInstrStep noPersonsTaughtLesson3ByVol(Integer noPersonsTaughtLesson3ByVol);
  }
  

  public interface NoLsn4TaughtAsPrimaryInstrStep {
    NoLsn4TaughtAssistingSweStep noLsn4TaughtAsPrimaryInstr(Integer noLsn4TaughtAsPrimaryInstr);
  }
  

  public interface NoLsn4TaughtAssistingSweStep {
    NoPersonsTaughtLesson4ByVolStep noLsn4TaughtAssistingSwe(Integer noLsn4TaughtAssistingSwe);
  }
  

  public interface NoPersonsTaughtLesson4ByVolStep {
    NoPersonsTaughtByVolStep noPersonsTaughtLesson4ByVol(Integer noPersonsTaughtLesson4ByVol);
  }
  

  public interface NoPersonsTaughtByVolStep {
    CompletedStep noPersonsTaughtByVol(Integer noPersonsTaughtByVol);
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
  

  public static class Builder implements NamebweStep, NamevolStep, NoWaterSampleTakenStep, NoLsn1TaughtAsPrimaryInstrStep, NoLsn1TaughtAssistingSweStep, NoPersonsTaughtLesson1ByVolStep, NoLsn2TaughtAsPrimaryInstrStep, NoLsn2TaughtAssistingSweStep, NoPersonsTaughtLesson2ByVolStep, NoLsn3TaughtAsPrimaryInstrStep, NoLsn3TaughtAssistingSweStep, NoPersonsTaughtLesson3ByVolStep, NoLsn4TaughtAsPrimaryInstrStep, NoLsn4TaughtAssistingSweStep, NoPersonsTaughtLesson4ByVolStep, NoPersonsTaughtByVolStep, CompletedStep, LatStep, LngStep, BuildStep {
    private String id;
    private String Namebwe;
    private String Namevol;
    private Integer NoWaterSampleTaken;
    private Integer NoLsn1TaughtAsPrimaryInstr;
    private Integer NoLsn1TaughtAssistingSWE;
    private Integer NoPersonsTaughtLesson1ByVol;
    private Integer NoLsn2TaughtAsPrimaryInstr;
    private Integer NoLsn2TaughtAssistingSWE;
    private Integer NoPersonsTaughtLesson2ByVol;
    private Integer NoLsn3TaughtAsPrimaryInstr;
    private Integer NoLsn3TaughtAssistingSWE;
    private Integer NoPersonsTaughtLesson3ByVol;
    private Integer NoLsn4TaughtAsPrimaryInstr;
    private Integer NoLsn4TaughtAssistingSWE;
    private Integer NoPersonsTaughtLesson4ByVol;
    private Integer NoPersonsTaughtByVol;
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
          NoLsn1TaughtAsPrimaryInstr,
          NoLsn1TaughtAssistingSWE,
          NoPersonsTaughtLesson1ByVol,
          NoLsn2TaughtAsPrimaryInstr,
          NoLsn2TaughtAssistingSWE,
          NoPersonsTaughtLesson2ByVol,
          NoLsn3TaughtAsPrimaryInstr,
          NoLsn3TaughtAssistingSWE,
          NoPersonsTaughtLesson3ByVol,
          NoLsn4TaughtAsPrimaryInstr,
          NoLsn4TaughtAssistingSWE,
          NoPersonsTaughtLesson4ByVol,
          NoPersonsTaughtByVol,
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
     public NoLsn1TaughtAsPrimaryInstrStep noWaterSampleTaken(Integer noWaterSampleTaken) {
        Objects.requireNonNull(noWaterSampleTaken);
        this.NoWaterSampleTaken = noWaterSampleTaken;
        return this;
    }
    
    @Override
     public NoLsn1TaughtAssistingSweStep noLsn1TaughtAsPrimaryInstr(Integer noLsn1TaughtAsPrimaryInstr) {
        Objects.requireNonNull(noLsn1TaughtAsPrimaryInstr);
        this.NoLsn1TaughtAsPrimaryInstr = noLsn1TaughtAsPrimaryInstr;
        return this;
    }
    
    @Override
     public NoPersonsTaughtLesson1ByVolStep noLsn1TaughtAssistingSwe(Integer noLsn1TaughtAssistingSwe) {
        Objects.requireNonNull(noLsn1TaughtAssistingSwe);
        this.NoLsn1TaughtAssistingSWE = noLsn1TaughtAssistingSwe;
        return this;
    }
    
    @Override
     public NoLsn2TaughtAsPrimaryInstrStep noPersonsTaughtLesson1ByVol(Integer noPersonsTaughtLesson1ByVol) {
        Objects.requireNonNull(noPersonsTaughtLesson1ByVol);
        this.NoPersonsTaughtLesson1ByVol = noPersonsTaughtLesson1ByVol;
        return this;
    }
    
    @Override
     public NoLsn2TaughtAssistingSweStep noLsn2TaughtAsPrimaryInstr(Integer noLsn2TaughtAsPrimaryInstr) {
        Objects.requireNonNull(noLsn2TaughtAsPrimaryInstr);
        this.NoLsn2TaughtAsPrimaryInstr = noLsn2TaughtAsPrimaryInstr;
        return this;
    }
    
    @Override
     public NoPersonsTaughtLesson2ByVolStep noLsn2TaughtAssistingSwe(Integer noLsn2TaughtAssistingSwe) {
        Objects.requireNonNull(noLsn2TaughtAssistingSwe);
        this.NoLsn2TaughtAssistingSWE = noLsn2TaughtAssistingSwe;
        return this;
    }
    
    @Override
     public NoLsn3TaughtAsPrimaryInstrStep noPersonsTaughtLesson2ByVol(Integer noPersonsTaughtLesson2ByVol) {
        Objects.requireNonNull(noPersonsTaughtLesson2ByVol);
        this.NoPersonsTaughtLesson2ByVol = noPersonsTaughtLesson2ByVol;
        return this;
    }
    
    @Override
     public NoLsn3TaughtAssistingSweStep noLsn3TaughtAsPrimaryInstr(Integer noLsn3TaughtAsPrimaryInstr) {
        Objects.requireNonNull(noLsn3TaughtAsPrimaryInstr);
        this.NoLsn3TaughtAsPrimaryInstr = noLsn3TaughtAsPrimaryInstr;
        return this;
    }
    
    @Override
     public NoPersonsTaughtLesson3ByVolStep noLsn3TaughtAssistingSwe(Integer noLsn3TaughtAssistingSwe) {
        Objects.requireNonNull(noLsn3TaughtAssistingSwe);
        this.NoLsn3TaughtAssistingSWE = noLsn3TaughtAssistingSwe;
        return this;
    }
    
    @Override
     public NoLsn4TaughtAsPrimaryInstrStep noPersonsTaughtLesson3ByVol(Integer noPersonsTaughtLesson3ByVol) {
        Objects.requireNonNull(noPersonsTaughtLesson3ByVol);
        this.NoPersonsTaughtLesson3ByVol = noPersonsTaughtLesson3ByVol;
        return this;
    }
    
    @Override
     public NoLsn4TaughtAssistingSweStep noLsn4TaughtAsPrimaryInstr(Integer noLsn4TaughtAsPrimaryInstr) {
        Objects.requireNonNull(noLsn4TaughtAsPrimaryInstr);
        this.NoLsn4TaughtAsPrimaryInstr = noLsn4TaughtAsPrimaryInstr;
        return this;
    }
    
    @Override
     public NoPersonsTaughtLesson4ByVolStep noLsn4TaughtAssistingSwe(Integer noLsn4TaughtAssistingSwe) {
        Objects.requireNonNull(noLsn4TaughtAssistingSwe);
        this.NoLsn4TaughtAssistingSWE = noLsn4TaughtAssistingSwe;
        return this;
    }
    
    @Override
     public NoPersonsTaughtByVolStep noPersonsTaughtLesson4ByVol(Integer noPersonsTaughtLesson4ByVol) {
        Objects.requireNonNull(noPersonsTaughtLesson4ByVol);
        this.NoPersonsTaughtLesson4ByVol = noPersonsTaughtLesson4ByVol;
        return this;
    }
    
    @Override
     public CompletedStep noPersonsTaughtByVol(Integer noPersonsTaughtByVol) {
        Objects.requireNonNull(noPersonsTaughtByVol);
        this.NoPersonsTaughtByVol = noPersonsTaughtByVol;
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
    private CopyOfBuilder(String id, String namebwe, String namevol, Temporal.Date date, Integer noWaterSampleTaken, Integer noLsn1TaughtAsPrimaryInstr, Integer noLsn1TaughtAssistingSwe, Integer noPersonsTaughtLesson1ByVol, Integer noLsn2TaughtAsPrimaryInstr, Integer noLsn2TaughtAssistingSwe, Integer noPersonsTaughtLesson2ByVol, Integer noLsn3TaughtAsPrimaryInstr, Integer noLsn3TaughtAssistingSwe, Integer noPersonsTaughtLesson3ByVol, Integer noLsn4TaughtAsPrimaryInstr, Integer noLsn4TaughtAssistingSwe, Integer noPersonsTaughtLesson4ByVol, Integer noPersonsTaughtByVol, Integer completed, String lat, String lng) {
      super.id(id);
      super.namebwe(namebwe)
        .namevol(namevol)
        .noWaterSampleTaken(noWaterSampleTaken)
        .noLsn1TaughtAsPrimaryInstr(noLsn1TaughtAsPrimaryInstr)
        .noLsn1TaughtAssistingSwe(noLsn1TaughtAssistingSwe)
        .noPersonsTaughtLesson1ByVol(noPersonsTaughtLesson1ByVol)
        .noLsn2TaughtAsPrimaryInstr(noLsn2TaughtAsPrimaryInstr)
        .noLsn2TaughtAssistingSwe(noLsn2TaughtAssistingSwe)
        .noPersonsTaughtLesson2ByVol(noPersonsTaughtLesson2ByVol)
        .noLsn3TaughtAsPrimaryInstr(noLsn3TaughtAsPrimaryInstr)
        .noLsn3TaughtAssistingSwe(noLsn3TaughtAssistingSwe)
        .noPersonsTaughtLesson3ByVol(noPersonsTaughtLesson3ByVol)
        .noLsn4TaughtAsPrimaryInstr(noLsn4TaughtAsPrimaryInstr)
        .noLsn4TaughtAssistingSwe(noLsn4TaughtAssistingSwe)
        .noPersonsTaughtLesson4ByVol(noPersonsTaughtLesson4ByVol)
        .noPersonsTaughtByVol(noPersonsTaughtByVol)
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
     public CopyOfBuilder noLsn1TaughtAsPrimaryInstr(Integer noLsn1TaughtAsPrimaryInstr) {
      return (CopyOfBuilder) super.noLsn1TaughtAsPrimaryInstr(noLsn1TaughtAsPrimaryInstr);
    }
    
    @Override
     public CopyOfBuilder noLsn1TaughtAssistingSwe(Integer noLsn1TaughtAssistingSwe) {
      return (CopyOfBuilder) super.noLsn1TaughtAssistingSwe(noLsn1TaughtAssistingSwe);
    }
    
    @Override
     public CopyOfBuilder noPersonsTaughtLesson1ByVol(Integer noPersonsTaughtLesson1ByVol) {
      return (CopyOfBuilder) super.noPersonsTaughtLesson1ByVol(noPersonsTaughtLesson1ByVol);
    }
    
    @Override
     public CopyOfBuilder noLsn2TaughtAsPrimaryInstr(Integer noLsn2TaughtAsPrimaryInstr) {
      return (CopyOfBuilder) super.noLsn2TaughtAsPrimaryInstr(noLsn2TaughtAsPrimaryInstr);
    }
    
    @Override
     public CopyOfBuilder noLsn2TaughtAssistingSwe(Integer noLsn2TaughtAssistingSwe) {
      return (CopyOfBuilder) super.noLsn2TaughtAssistingSwe(noLsn2TaughtAssistingSwe);
    }
    
    @Override
     public CopyOfBuilder noPersonsTaughtLesson2ByVol(Integer noPersonsTaughtLesson2ByVol) {
      return (CopyOfBuilder) super.noPersonsTaughtLesson2ByVol(noPersonsTaughtLesson2ByVol);
    }
    
    @Override
     public CopyOfBuilder noLsn3TaughtAsPrimaryInstr(Integer noLsn3TaughtAsPrimaryInstr) {
      return (CopyOfBuilder) super.noLsn3TaughtAsPrimaryInstr(noLsn3TaughtAsPrimaryInstr);
    }
    
    @Override
     public CopyOfBuilder noLsn3TaughtAssistingSwe(Integer noLsn3TaughtAssistingSwe) {
      return (CopyOfBuilder) super.noLsn3TaughtAssistingSwe(noLsn3TaughtAssistingSwe);
    }
    
    @Override
     public CopyOfBuilder noPersonsTaughtLesson3ByVol(Integer noPersonsTaughtLesson3ByVol) {
      return (CopyOfBuilder) super.noPersonsTaughtLesson3ByVol(noPersonsTaughtLesson3ByVol);
    }
    
    @Override
     public CopyOfBuilder noLsn4TaughtAsPrimaryInstr(Integer noLsn4TaughtAsPrimaryInstr) {
      return (CopyOfBuilder) super.noLsn4TaughtAsPrimaryInstr(noLsn4TaughtAsPrimaryInstr);
    }
    
    @Override
     public CopyOfBuilder noLsn4TaughtAssistingSwe(Integer noLsn4TaughtAssistingSwe) {
      return (CopyOfBuilder) super.noLsn4TaughtAssistingSwe(noLsn4TaughtAssistingSwe);
    }
    
    @Override
     public CopyOfBuilder noPersonsTaughtLesson4ByVol(Integer noPersonsTaughtLesson4ByVol) {
      return (CopyOfBuilder) super.noPersonsTaughtLesson4ByVol(noPersonsTaughtLesson4ByVol);
    }
    
    @Override
     public CopyOfBuilder noPersonsTaughtByVol(Integer noPersonsTaughtByVol) {
      return (CopyOfBuilder) super.noPersonsTaughtByVol(noPersonsTaughtByVol);
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
