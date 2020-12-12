package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the SWEMonthlySummary type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "SWEMonthlySummaries")
public final class SWEMonthlySummary implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAMEBWE = field("Namebwe");
  public static final QueryField DATE = field("date");
  public static final QueryField SWE_POSITION = field("SWEPosition");
  public static final QueryField NO_WATER_SAMPLE_TAKEN = field("NoWaterSampleTaken");
  public static final QueryField NO_SURVEYS_COMPLETED = field("NoSurveysCompleted");
  public static final QueryField NO_LSN1_TAUGHT = field("NoLsn1Taught");
  public static final QueryField NO_LSN2_TAUGHT = field("NoLsn2Taught");
  public static final QueryField NO_LSN3_TAUGHT = field("NoLsn3Taught");
  public static final QueryField NO_LSN4_TAUGHT = field("NoLsn4Taught");
  public static final QueryField NO_PERSONS_TAUGHT = field("NoPersonsTaught");
  public static final QueryField NO_CHLORINE_LIQUID_TABS_DISTRIBUTED = field("NoChlorineLiquid_TabsDistributed");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Namebwe;
  private final @ModelField(targetType="AWSDate") Temporal.Date date;
  private final @ModelField(targetType="String", isRequired = true) String SWEPosition;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoWaterSampleTaken;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoSurveysCompleted;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn1Taught;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn2Taught;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn3Taught;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoLsn4Taught;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoPersonsTaught;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoChlorineLiquid_TabsDistributed;
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
  
  public Integer getNoLsn1Taught() {
      return NoLsn1Taught;
  }
  
  public Integer getNoLsn2Taught() {
      return NoLsn2Taught;
  }
  
  public Integer getNoLsn3Taught() {
      return NoLsn3Taught;
  }
  
  public Integer getNoLsn4Taught() {
      return NoLsn4Taught;
  }
  
  public Integer getNoPersonsTaught() {
      return NoPersonsTaught;
  }
  
  public Integer getNoChlorineLiquidTabsDistributed() {
      return NoChlorineLiquid_TabsDistributed;
  }
  
  private SWEMonthlySummary(String id, String Namebwe, Temporal.Date date, String SWEPosition, Integer NoWaterSampleTaken, Integer NoSurveysCompleted, Integer NoLsn1Taught, Integer NoLsn2Taught, Integer NoLsn3Taught, Integer NoLsn4Taught, Integer NoPersonsTaught, Integer NoChlorineLiquid_TabsDistributed) {
    this.id = id;
    this.Namebwe = Namebwe;
    this.date = date;
    this.SWEPosition = SWEPosition;
    this.NoWaterSampleTaken = NoWaterSampleTaken;
    this.NoSurveysCompleted = NoSurveysCompleted;
    this.NoLsn1Taught = NoLsn1Taught;
    this.NoLsn2Taught = NoLsn2Taught;
    this.NoLsn3Taught = NoLsn3Taught;
    this.NoLsn4Taught = NoLsn4Taught;
    this.NoPersonsTaught = NoPersonsTaught;
    this.NoChlorineLiquid_TabsDistributed = NoChlorineLiquid_TabsDistributed;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      SWEMonthlySummary sweMonthlySummary = (SWEMonthlySummary) obj;
      return ObjectsCompat.equals(getId(), sweMonthlySummary.getId()) &&
              ObjectsCompat.equals(getNamebwe(), sweMonthlySummary.getNamebwe()) &&
              ObjectsCompat.equals(getDate(), sweMonthlySummary.getDate()) &&
              ObjectsCompat.equals(getSwePosition(), sweMonthlySummary.getSwePosition()) &&
              ObjectsCompat.equals(getNoWaterSampleTaken(), sweMonthlySummary.getNoWaterSampleTaken()) &&
              ObjectsCompat.equals(getNoSurveysCompleted(), sweMonthlySummary.getNoSurveysCompleted()) &&
              ObjectsCompat.equals(getNoLsn1Taught(), sweMonthlySummary.getNoLsn1Taught()) &&
              ObjectsCompat.equals(getNoLsn2Taught(), sweMonthlySummary.getNoLsn2Taught()) &&
              ObjectsCompat.equals(getNoLsn3Taught(), sweMonthlySummary.getNoLsn3Taught()) &&
              ObjectsCompat.equals(getNoLsn4Taught(), sweMonthlySummary.getNoLsn4Taught()) &&
              ObjectsCompat.equals(getNoPersonsTaught(), sweMonthlySummary.getNoPersonsTaught()) &&
              ObjectsCompat.equals(getNoChlorineLiquidTabsDistributed(), sweMonthlySummary.getNoChlorineLiquidTabsDistributed());
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
      .append(getNoLsn1Taught())
      .append(getNoLsn2Taught())
      .append(getNoLsn3Taught())
      .append(getNoLsn4Taught())
      .append(getNoPersonsTaught())
      .append(getNoChlorineLiquidTabsDistributed())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("SWEMonthlySummary {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Namebwe=" + String.valueOf(getNamebwe()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("SWEPosition=" + String.valueOf(getSwePosition()) + ", ")
      .append("NoWaterSampleTaken=" + String.valueOf(getNoWaterSampleTaken()) + ", ")
      .append("NoSurveysCompleted=" + String.valueOf(getNoSurveysCompleted()) + ", ")
      .append("NoLsn1Taught=" + String.valueOf(getNoLsn1Taught()) + ", ")
      .append("NoLsn2Taught=" + String.valueOf(getNoLsn2Taught()) + ", ")
      .append("NoLsn3Taught=" + String.valueOf(getNoLsn3Taught()) + ", ")
      .append("NoLsn4Taught=" + String.valueOf(getNoLsn4Taught()) + ", ")
      .append("NoPersonsTaught=" + String.valueOf(getNoPersonsTaught()) + ", ")
      .append("NoChlorineLiquid_TabsDistributed=" + String.valueOf(getNoChlorineLiquidTabsDistributed()))
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
  public static SWEMonthlySummary justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new SWEMonthlySummary(
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
      NoLsn1Taught,
      NoLsn2Taught,
      NoLsn3Taught,
      NoLsn4Taught,
      NoPersonsTaught,
      NoChlorineLiquid_TabsDistributed);
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
    NoLsn1TaughtStep noSurveysCompleted(Integer noSurveysCompleted);
  }
  

  public interface NoLsn1TaughtStep {
    NoLsn2TaughtStep noLsn1Taught(Integer noLsn1Taught);
  }
  

  public interface NoLsn2TaughtStep {
    NoLsn3TaughtStep noLsn2Taught(Integer noLsn2Taught);
  }
  

  public interface NoLsn3TaughtStep {
    NoLsn4TaughtStep noLsn3Taught(Integer noLsn3Taught);
  }
  

  public interface NoLsn4TaughtStep {
    NoPersonsTaughtStep noLsn4Taught(Integer noLsn4Taught);
  }
  

  public interface NoPersonsTaughtStep {
    NoChlorineLiquidTabsDistributedStep noPersonsTaught(Integer noPersonsTaught);
  }
  

  public interface NoChlorineLiquidTabsDistributedStep {
    BuildStep noChlorineLiquidTabsDistributed(Integer noChlorineLiquidTabsDistributed);
  }
  

  public interface BuildStep {
    SWEMonthlySummary build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep date(Temporal.Date date);
  }
  

  public static class Builder implements NamebweStep, SwePositionStep, NoWaterSampleTakenStep, NoSurveysCompletedStep, NoLsn1TaughtStep, NoLsn2TaughtStep, NoLsn3TaughtStep, NoLsn4TaughtStep, NoPersonsTaughtStep, NoChlorineLiquidTabsDistributedStep, BuildStep {
    private String id;
    private String Namebwe;
    private String SWEPosition;
    private Integer NoWaterSampleTaken;
    private Integer NoSurveysCompleted;
    private Integer NoLsn1Taught;
    private Integer NoLsn2Taught;
    private Integer NoLsn3Taught;
    private Integer NoLsn4Taught;
    private Integer NoPersonsTaught;
    private Integer NoChlorineLiquid_TabsDistributed;
    private Temporal.Date date;
    @Override
     public SWEMonthlySummary build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new SWEMonthlySummary(
          id,
          Namebwe,
          date,
          SWEPosition,
          NoWaterSampleTaken,
          NoSurveysCompleted,
          NoLsn1Taught,
          NoLsn2Taught,
          NoLsn3Taught,
          NoLsn4Taught,
          NoPersonsTaught,
          NoChlorineLiquid_TabsDistributed);
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
     public NoLsn1TaughtStep noSurveysCompleted(Integer noSurveysCompleted) {
        Objects.requireNonNull(noSurveysCompleted);
        this.NoSurveysCompleted = noSurveysCompleted;
        return this;
    }
    
    @Override
     public NoLsn2TaughtStep noLsn1Taught(Integer noLsn1Taught) {
        Objects.requireNonNull(noLsn1Taught);
        this.NoLsn1Taught = noLsn1Taught;
        return this;
    }
    
    @Override
     public NoLsn3TaughtStep noLsn2Taught(Integer noLsn2Taught) {
        Objects.requireNonNull(noLsn2Taught);
        this.NoLsn2Taught = noLsn2Taught;
        return this;
    }
    
    @Override
     public NoLsn4TaughtStep noLsn3Taught(Integer noLsn3Taught) {
        Objects.requireNonNull(noLsn3Taught);
        this.NoLsn3Taught = noLsn3Taught;
        return this;
    }
    
    @Override
     public NoPersonsTaughtStep noLsn4Taught(Integer noLsn4Taught) {
        Objects.requireNonNull(noLsn4Taught);
        this.NoLsn4Taught = noLsn4Taught;
        return this;
    }
    
    @Override
     public NoChlorineLiquidTabsDistributedStep noPersonsTaught(Integer noPersonsTaught) {
        Objects.requireNonNull(noPersonsTaught);
        this.NoPersonsTaught = noPersonsTaught;
        return this;
    }
    
    @Override
     public BuildStep noChlorineLiquidTabsDistributed(Integer noChlorineLiquidTabsDistributed) {
        Objects.requireNonNull(noChlorineLiquidTabsDistributed);
        this.NoChlorineLiquid_TabsDistributed = noChlorineLiquidTabsDistributed;
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
    private CopyOfBuilder(String id, String namebwe, Temporal.Date date, String swePosition, Integer noWaterSampleTaken, Integer noSurveysCompleted, Integer noLsn1Taught, Integer noLsn2Taught, Integer noLsn3Taught, Integer noLsn4Taught, Integer noPersonsTaught, Integer noChlorineLiquidTabsDistributed) {
      super.id(id);
      super.namebwe(namebwe)
        .swePosition(swePosition)
        .noWaterSampleTaken(noWaterSampleTaken)
        .noSurveysCompleted(noSurveysCompleted)
        .noLsn1Taught(noLsn1Taught)
        .noLsn2Taught(noLsn2Taught)
        .noLsn3Taught(noLsn3Taught)
        .noLsn4Taught(noLsn4Taught)
        .noPersonsTaught(noPersonsTaught)
        .noChlorineLiquidTabsDistributed(noChlorineLiquidTabsDistributed)
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
     public CopyOfBuilder noLsn1Taught(Integer noLsn1Taught) {
      return (CopyOfBuilder) super.noLsn1Taught(noLsn1Taught);
    }
    
    @Override
     public CopyOfBuilder noLsn2Taught(Integer noLsn2Taught) {
      return (CopyOfBuilder) super.noLsn2Taught(noLsn2Taught);
    }
    
    @Override
     public CopyOfBuilder noLsn3Taught(Integer noLsn3Taught) {
      return (CopyOfBuilder) super.noLsn3Taught(noLsn3Taught);
    }
    
    @Override
     public CopyOfBuilder noLsn4Taught(Integer noLsn4Taught) {
      return (CopyOfBuilder) super.noLsn4Taught(noLsn4Taught);
    }
    
    @Override
     public CopyOfBuilder noPersonsTaught(Integer noPersonsTaught) {
      return (CopyOfBuilder) super.noPersonsTaught(noPersonsTaught);
    }
    
    @Override
     public CopyOfBuilder noChlorineLiquidTabsDistributed(Integer noChlorineLiquidTabsDistributed) {
      return (CopyOfBuilder) super.noChlorineLiquidTabsDistributed(noChlorineLiquidTabsDistributed);
    }
    
    @Override
     public CopyOfBuilder date(Temporal.Date date) {
      return (CopyOfBuilder) super.date(date);
    }
  }
  
}