package com.amplifyframework.datastore.generated.model;


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

/** This is an auto generated class representing the HouseholdAttendingMeeting type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "HouseholdAttendingMeetings", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class HouseholdAttendingMeeting implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAMEBWE = field("Namebwe");
  public static final QueryField HEAD_HOUSEHOLD_NAME = field("HeadHouseholdName");
  public static final QueryField HEAD_HOUSEHOLD_PHONE_NUMBER = field("HeadHouseholdPhoneNumber");
  public static final QueryField NUMBER_OF_ADULTS = field("NumberOfAdults");
  public static final QueryField COMMITED_TO_USE_AQUATABS = field("CommitedToUseAquatabs");
  public static final QueryField NUMBER_OF_AQUA_TABS_RECEIVED = field("NumberOfAquaTabsReceived");
  public static final QueryField MEETING_ID = field("MeetingID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Namebwe;
  private final @ModelField(targetType="String", isRequired = true) String HeadHouseholdName;
  private final @ModelField(targetType="String", isRequired = true) String HeadHouseholdPhoneNumber;
  private final @ModelField(targetType="Int", isRequired = true) Integer NumberOfAdults;
  private final @ModelField(targetType="String", isRequired = true) String CommitedToUseAquatabs;
  private final @ModelField(targetType="Int", isRequired = true) Integer NumberOfAquaTabsReceived;
  private final @ModelField(targetType="String", isRequired = true) String MeetingID;
  public String getId() {
      return id;
  }
  
  public String getNamebwe() {
      return Namebwe;
  }
  
  public String getHeadHouseholdName() {
      return HeadHouseholdName;
  }
  
  public String getHeadHouseholdPhoneNumber() {
      return HeadHouseholdPhoneNumber;
  }
  
  public Integer getNumberOfAdults() {
      return NumberOfAdults;
  }
  
  public String getCommitedToUseAquatabs() {
      return CommitedToUseAquatabs;
  }
  
  public Integer getNumberOfAquaTabsReceived() {
      return NumberOfAquaTabsReceived;
  }
  
  public String getMeetingId() {
      return MeetingID;
  }
  
  private HouseholdAttendingMeeting(String id, String Namebwe, String HeadHouseholdName, String HeadHouseholdPhoneNumber, Integer NumberOfAdults, String CommitedToUseAquatabs, Integer NumberOfAquaTabsReceived, String MeetingID) {
    this.id = id;
    this.Namebwe = Namebwe;
    this.HeadHouseholdName = HeadHouseholdName;
    this.HeadHouseholdPhoneNumber = HeadHouseholdPhoneNumber;
    this.NumberOfAdults = NumberOfAdults;
    this.CommitedToUseAquatabs = CommitedToUseAquatabs;
    this.NumberOfAquaTabsReceived = NumberOfAquaTabsReceived;
    this.MeetingID = MeetingID;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      HouseholdAttendingMeeting householdAttendingMeeting = (HouseholdAttendingMeeting) obj;
      return ObjectsCompat.equals(getId(), householdAttendingMeeting.getId()) &&
              ObjectsCompat.equals(getNamebwe(), householdAttendingMeeting.getNamebwe()) &&
              ObjectsCompat.equals(getHeadHouseholdName(), householdAttendingMeeting.getHeadHouseholdName()) &&
              ObjectsCompat.equals(getHeadHouseholdPhoneNumber(), householdAttendingMeeting.getHeadHouseholdPhoneNumber()) &&
              ObjectsCompat.equals(getNumberOfAdults(), householdAttendingMeeting.getNumberOfAdults()) &&
              ObjectsCompat.equals(getCommitedToUseAquatabs(), householdAttendingMeeting.getCommitedToUseAquatabs()) &&
              ObjectsCompat.equals(getNumberOfAquaTabsReceived(), householdAttendingMeeting.getNumberOfAquaTabsReceived()) &&
              ObjectsCompat.equals(getMeetingId(), householdAttendingMeeting.getMeetingId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getNamebwe())
      .append(getHeadHouseholdName())
      .append(getHeadHouseholdPhoneNumber())
      .append(getNumberOfAdults())
      .append(getCommitedToUseAquatabs())
      .append(getNumberOfAquaTabsReceived())
      .append(getMeetingId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("HouseholdAttendingMeeting {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Namebwe=" + String.valueOf(getNamebwe()) + ", ")
      .append("HeadHouseholdName=" + String.valueOf(getHeadHouseholdName()) + ", ")
      .append("HeadHouseholdPhoneNumber=" + String.valueOf(getHeadHouseholdPhoneNumber()) + ", ")
      .append("NumberOfAdults=" + String.valueOf(getNumberOfAdults()) + ", ")
      .append("CommitedToUseAquatabs=" + String.valueOf(getCommitedToUseAquatabs()) + ", ")
      .append("NumberOfAquaTabsReceived=" + String.valueOf(getNumberOfAquaTabsReceived()) + ", ")
      .append("MeetingID=" + String.valueOf(getMeetingId()))
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
  public static HouseholdAttendingMeeting justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new HouseholdAttendingMeeting(
      id,
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
      HeadHouseholdName,
      HeadHouseholdPhoneNumber,
      NumberOfAdults,
      CommitedToUseAquatabs,
      NumberOfAquaTabsReceived,
      MeetingID);
  }
  public interface NamebweStep {
    HeadHouseholdNameStep namebwe(String namebwe);
  }
  

  public interface HeadHouseholdNameStep {
    HeadHouseholdPhoneNumberStep headHouseholdName(String headHouseholdName);
  }
  

  public interface HeadHouseholdPhoneNumberStep {
    NumberOfAdultsStep headHouseholdPhoneNumber(String headHouseholdPhoneNumber);
  }
  

  public interface NumberOfAdultsStep {
    CommitedToUseAquatabsStep numberOfAdults(Integer numberOfAdults);
  }
  

  public interface CommitedToUseAquatabsStep {
    NumberOfAquaTabsReceivedStep commitedToUseAquatabs(String commitedToUseAquatabs);
  }
  

  public interface NumberOfAquaTabsReceivedStep {
    MeetingIdStep numberOfAquaTabsReceived(Integer numberOfAquaTabsReceived);
  }
  

  public interface MeetingIdStep {
    BuildStep meetingId(String meetingId);
  }
  

  public interface BuildStep {
    HouseholdAttendingMeeting build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements NamebweStep, HeadHouseholdNameStep, HeadHouseholdPhoneNumberStep, NumberOfAdultsStep, CommitedToUseAquatabsStep, NumberOfAquaTabsReceivedStep, MeetingIdStep, BuildStep {
    private String id;
    private String Namebwe;
    private String HeadHouseholdName;
    private String HeadHouseholdPhoneNumber;
    private Integer NumberOfAdults;
    private String CommitedToUseAquatabs;
    private Integer NumberOfAquaTabsReceived;
    private String MeetingID;
    @Override
     public HouseholdAttendingMeeting build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new HouseholdAttendingMeeting(
          id,
          Namebwe,
          HeadHouseholdName,
          HeadHouseholdPhoneNumber,
          NumberOfAdults,
          CommitedToUseAquatabs,
          NumberOfAquaTabsReceived,
          MeetingID);
    }
    
    @Override
     public HeadHouseholdNameStep namebwe(String namebwe) {
        Objects.requireNonNull(namebwe);
        this.Namebwe = namebwe;
        return this;
    }
    
    @Override
     public HeadHouseholdPhoneNumberStep headHouseholdName(String headHouseholdName) {
        Objects.requireNonNull(headHouseholdName);
        this.HeadHouseholdName = headHouseholdName;
        return this;
    }
    
    @Override
     public NumberOfAdultsStep headHouseholdPhoneNumber(String headHouseholdPhoneNumber) {
        Objects.requireNonNull(headHouseholdPhoneNumber);
        this.HeadHouseholdPhoneNumber = headHouseholdPhoneNumber;
        return this;
    }
    
    @Override
     public CommitedToUseAquatabsStep numberOfAdults(Integer numberOfAdults) {
        Objects.requireNonNull(numberOfAdults);
        this.NumberOfAdults = numberOfAdults;
        return this;
    }
    
    @Override
     public NumberOfAquaTabsReceivedStep commitedToUseAquatabs(String commitedToUseAquatabs) {
        Objects.requireNonNull(commitedToUseAquatabs);
        this.CommitedToUseAquatabs = commitedToUseAquatabs;
        return this;
    }
    
    @Override
     public MeetingIdStep numberOfAquaTabsReceived(Integer numberOfAquaTabsReceived) {
        Objects.requireNonNull(numberOfAquaTabsReceived);
        this.NumberOfAquaTabsReceived = numberOfAquaTabsReceived;
        return this;
    }
    
    @Override
     public BuildStep meetingId(String meetingId) {
        Objects.requireNonNull(meetingId);
        this.MeetingID = meetingId;
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
    private CopyOfBuilder(String id, String namebwe, String headHouseholdName, String headHouseholdPhoneNumber, Integer numberOfAdults, String commitedToUseAquatabs, Integer numberOfAquaTabsReceived, String meetingId) {
      super.id(id);
      super.namebwe(namebwe)
        .headHouseholdName(headHouseholdName)
        .headHouseholdPhoneNumber(headHouseholdPhoneNumber)
        .numberOfAdults(numberOfAdults)
        .commitedToUseAquatabs(commitedToUseAquatabs)
        .numberOfAquaTabsReceived(numberOfAquaTabsReceived)
        .meetingId(meetingId);
    }
    
    @Override
     public CopyOfBuilder namebwe(String namebwe) {
      return (CopyOfBuilder) super.namebwe(namebwe);
    }
    
    @Override
     public CopyOfBuilder headHouseholdName(String headHouseholdName) {
      return (CopyOfBuilder) super.headHouseholdName(headHouseholdName);
    }
    
    @Override
     public CopyOfBuilder headHouseholdPhoneNumber(String headHouseholdPhoneNumber) {
      return (CopyOfBuilder) super.headHouseholdPhoneNumber(headHouseholdPhoneNumber);
    }
    
    @Override
     public CopyOfBuilder numberOfAdults(Integer numberOfAdults) {
      return (CopyOfBuilder) super.numberOfAdults(numberOfAdults);
    }
    
    @Override
     public CopyOfBuilder commitedToUseAquatabs(String commitedToUseAquatabs) {
      return (CopyOfBuilder) super.commitedToUseAquatabs(commitedToUseAquatabs);
    }
    
    @Override
     public CopyOfBuilder numberOfAquaTabsReceived(Integer numberOfAquaTabsReceived) {
      return (CopyOfBuilder) super.numberOfAquaTabsReceived(numberOfAquaTabsReceived);
    }
    
    @Override
     public CopyOfBuilder meetingId(String meetingId) {
      return (CopyOfBuilder) super.meetingId(meetingId);
    }
  }
  
}
