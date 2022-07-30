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

/** This is an auto generated class representing the SWEMonthlySchoolSummary type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "SWEMonthlySchoolSummaries", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class SWEMonthlySchoolSummary implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAMEBWE = field("Namebwe");
  public static final QueryField DATE = field("date");
  public static final QueryField SWE_POSITION = field("SWEPosition");
  public static final QueryField COUNTRY = field("Country");
  public static final QueryField COMMUNITY = field("Community");
  public static final QueryField SCHOOL = field("School");
  public static final QueryField NO_TABLET_USED_AT_DRINKING_STATION1 = field("NoTabletUsedAtDrinkingStation1");
  public static final QueryField NO_TABLET_USED_AT_DRINKING_STATION2 = field("NoTabletUsedAtDrinkingStation2");
  public static final QueryField NO_TABLET_USED_AT_DRINKING_STATION3 = field("NoTabletUsedAtDrinkingStation3");
  public static final QueryField NO_STUDENTS_TAUGHT_SAFE_WATER_PRINCIPLES = field("NoStudentsTaughtSafeWaterPrinciples");
  public static final QueryField NO_ENROLLED_IN_SCHOOL_HEALTH_CLUB = field("NoEnrolledInSchoolHealthClub");
  public static final QueryField NO_HEALTH_CLUB_MEETING_HELD = field("NoHealthClubMeetingHeld");
  public static final QueryField NO_HEALTH_CLUB_LESSONS_TAUGHT = field("NoHealthClubLessonsTaught");
  public static final QueryField DO_HEALTH_CLUB_MANAGE_STATIONS = field("DoHealthClubManageStations");
  public static final QueryField NO_HEALTH_CLUB_CLEAN_UP_PROJECT = field("NoHealthClubCleanUpProject");
  public static final QueryField COMPLETED = field("Completed");
  public static final QueryField LAT = field("Lat");
  public static final QueryField LNG = field("Lng");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Namebwe;
  private final @ModelField(targetType="AWSDate") Temporal.Date date;
  private final @ModelField(targetType="String", isRequired = true) String SWEPosition;
  private final @ModelField(targetType="String", isRequired = true) String Country;
  private final @ModelField(targetType="String", isRequired = true) String Community;
  private final @ModelField(targetType="String", isRequired = true) String School;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoTabletUsedAtDrinkingStation1;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoTabletUsedAtDrinkingStation2;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoTabletUsedAtDrinkingStation3;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoStudentsTaughtSafeWaterPrinciples;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoEnrolledInSchoolHealthClub;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHealthClubMeetingHeld;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHealthClubLessonsTaught;
  private final @ModelField(targetType="String", isRequired = true) String DoHealthClubManageStations;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHealthClubCleanUpProject;
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
  
  public String getCountry() {
      return Country;
  }
  
  public String getCommunity() {
      return Community;
  }
  
  public String getSchool() {
      return School;
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
  
  public Integer getNoStudentsTaughtSafeWaterPrinciples() {
      return NoStudentsTaughtSafeWaterPrinciples;
  }
  
  public Integer getNoEnrolledInSchoolHealthClub() {
      return NoEnrolledInSchoolHealthClub;
  }
  
  public Integer getNoHealthClubMeetingHeld() {
      return NoHealthClubMeetingHeld;
  }
  
  public Integer getNoHealthClubLessonsTaught() {
      return NoHealthClubLessonsTaught;
  }
  
  public String getDoHealthClubManageStations() {
      return DoHealthClubManageStations;
  }
  
  public Integer getNoHealthClubCleanUpProject() {
      return NoHealthClubCleanUpProject;
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
  
  private SWEMonthlySchoolSummary(String id, String Namebwe, Temporal.Date date, String SWEPosition, String Country, String Community, String School, Integer NoTabletUsedAtDrinkingStation1, Integer NoTabletUsedAtDrinkingStation2, Integer NoTabletUsedAtDrinkingStation3, Integer NoStudentsTaughtSafeWaterPrinciples, Integer NoEnrolledInSchoolHealthClub, Integer NoHealthClubMeetingHeld, Integer NoHealthClubLessonsTaught, String DoHealthClubManageStations, Integer NoHealthClubCleanUpProject, Integer Completed, String Lat, String Lng) {
    this.id = id;
    this.Namebwe = Namebwe;
    this.date = date;
    this.SWEPosition = SWEPosition;
    this.Country = Country;
    this.Community = Community;
    this.School = School;
    this.NoTabletUsedAtDrinkingStation1 = NoTabletUsedAtDrinkingStation1;
    this.NoTabletUsedAtDrinkingStation2 = NoTabletUsedAtDrinkingStation2;
    this.NoTabletUsedAtDrinkingStation3 = NoTabletUsedAtDrinkingStation3;
    this.NoStudentsTaughtSafeWaterPrinciples = NoStudentsTaughtSafeWaterPrinciples;
    this.NoEnrolledInSchoolHealthClub = NoEnrolledInSchoolHealthClub;
    this.NoHealthClubMeetingHeld = NoHealthClubMeetingHeld;
    this.NoHealthClubLessonsTaught = NoHealthClubLessonsTaught;
    this.DoHealthClubManageStations = DoHealthClubManageStations;
    this.NoHealthClubCleanUpProject = NoHealthClubCleanUpProject;
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
      SWEMonthlySchoolSummary sweMonthlySchoolSummary = (SWEMonthlySchoolSummary) obj;
      return ObjectsCompat.equals(getId(), sweMonthlySchoolSummary.getId()) &&
              ObjectsCompat.equals(getNamebwe(), sweMonthlySchoolSummary.getNamebwe()) &&
              ObjectsCompat.equals(getDate(), sweMonthlySchoolSummary.getDate()) &&
              ObjectsCompat.equals(getSwePosition(), sweMonthlySchoolSummary.getSwePosition()) &&
              ObjectsCompat.equals(getCountry(), sweMonthlySchoolSummary.getCountry()) &&
              ObjectsCompat.equals(getCommunity(), sweMonthlySchoolSummary.getCommunity()) &&
              ObjectsCompat.equals(getSchool(), sweMonthlySchoolSummary.getSchool()) &&
              ObjectsCompat.equals(getNoTabletUsedAtDrinkingStation1(), sweMonthlySchoolSummary.getNoTabletUsedAtDrinkingStation1()) &&
              ObjectsCompat.equals(getNoTabletUsedAtDrinkingStation2(), sweMonthlySchoolSummary.getNoTabletUsedAtDrinkingStation2()) &&
              ObjectsCompat.equals(getNoTabletUsedAtDrinkingStation3(), sweMonthlySchoolSummary.getNoTabletUsedAtDrinkingStation3()) &&
              ObjectsCompat.equals(getNoStudentsTaughtSafeWaterPrinciples(), sweMonthlySchoolSummary.getNoStudentsTaughtSafeWaterPrinciples()) &&
              ObjectsCompat.equals(getNoEnrolledInSchoolHealthClub(), sweMonthlySchoolSummary.getNoEnrolledInSchoolHealthClub()) &&
              ObjectsCompat.equals(getNoHealthClubMeetingHeld(), sweMonthlySchoolSummary.getNoHealthClubMeetingHeld()) &&
              ObjectsCompat.equals(getNoHealthClubLessonsTaught(), sweMonthlySchoolSummary.getNoHealthClubLessonsTaught()) &&
              ObjectsCompat.equals(getDoHealthClubManageStations(), sweMonthlySchoolSummary.getDoHealthClubManageStations()) &&
              ObjectsCompat.equals(getNoHealthClubCleanUpProject(), sweMonthlySchoolSummary.getNoHealthClubCleanUpProject()) &&
              ObjectsCompat.equals(getCompleted(), sweMonthlySchoolSummary.getCompleted()) &&
              ObjectsCompat.equals(getLat(), sweMonthlySchoolSummary.getLat()) &&
              ObjectsCompat.equals(getLng(), sweMonthlySchoolSummary.getLng());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getNamebwe())
      .append(getDate())
      .append(getSwePosition())
      .append(getCountry())
      .append(getCommunity())
      .append(getSchool())
      .append(getNoTabletUsedAtDrinkingStation1())
      .append(getNoTabletUsedAtDrinkingStation2())
      .append(getNoTabletUsedAtDrinkingStation3())
      .append(getNoStudentsTaughtSafeWaterPrinciples())
      .append(getNoEnrolledInSchoolHealthClub())
      .append(getNoHealthClubMeetingHeld())
      .append(getNoHealthClubLessonsTaught())
      .append(getDoHealthClubManageStations())
      .append(getNoHealthClubCleanUpProject())
      .append(getCompleted())
      .append(getLat())
      .append(getLng())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("SWEMonthlySchoolSummary {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Namebwe=" + String.valueOf(getNamebwe()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("SWEPosition=" + String.valueOf(getSwePosition()) + ", ")
      .append("Country=" + String.valueOf(getCountry()) + ", ")
      .append("Community=" + String.valueOf(getCommunity()) + ", ")
      .append("School=" + String.valueOf(getSchool()) + ", ")
      .append("NoTabletUsedAtDrinkingStation1=" + String.valueOf(getNoTabletUsedAtDrinkingStation1()) + ", ")
      .append("NoTabletUsedAtDrinkingStation2=" + String.valueOf(getNoTabletUsedAtDrinkingStation2()) + ", ")
      .append("NoTabletUsedAtDrinkingStation3=" + String.valueOf(getNoTabletUsedAtDrinkingStation3()) + ", ")
      .append("NoStudentsTaughtSafeWaterPrinciples=" + String.valueOf(getNoStudentsTaughtSafeWaterPrinciples()) + ", ")
      .append("NoEnrolledInSchoolHealthClub=" + String.valueOf(getNoEnrolledInSchoolHealthClub()) + ", ")
      .append("NoHealthClubMeetingHeld=" + String.valueOf(getNoHealthClubMeetingHeld()) + ", ")
      .append("NoHealthClubLessonsTaught=" + String.valueOf(getNoHealthClubLessonsTaught()) + ", ")
      .append("DoHealthClubManageStations=" + String.valueOf(getDoHealthClubManageStations()) + ", ")
      .append("NoHealthClubCleanUpProject=" + String.valueOf(getNoHealthClubCleanUpProject()) + ", ")
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
  public static SWEMonthlySchoolSummary justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new SWEMonthlySchoolSummary(
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
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      Namebwe,
      date,
      SWEPosition,
      Country,
      Community,
      School,
      NoTabletUsedAtDrinkingStation1,
      NoTabletUsedAtDrinkingStation2,
      NoTabletUsedAtDrinkingStation3,
      NoStudentsTaughtSafeWaterPrinciples,
      NoEnrolledInSchoolHealthClub,
      NoHealthClubMeetingHeld,
      NoHealthClubLessonsTaught,
      DoHealthClubManageStations,
      NoHealthClubCleanUpProject,
      Completed,
      Lat,
      Lng);
  }
  public interface NamebweStep {
    SwePositionStep namebwe(String namebwe);
  }
  

  public interface SwePositionStep {
    CountryStep swePosition(String swePosition);
  }
  

  public interface CountryStep {
    CommunityStep country(String country);
  }
  

  public interface CommunityStep {
    SchoolStep community(String community);
  }
  

  public interface SchoolStep {
    NoTabletUsedAtDrinkingStation1Step school(String school);
  }
  

  public interface NoTabletUsedAtDrinkingStation1Step {
    NoTabletUsedAtDrinkingStation2Step noTabletUsedAtDrinkingStation1(Integer noTabletUsedAtDrinkingStation1);
  }
  

  public interface NoTabletUsedAtDrinkingStation2Step {
    NoTabletUsedAtDrinkingStation3Step noTabletUsedAtDrinkingStation2(Integer noTabletUsedAtDrinkingStation2);
  }
  

  public interface NoTabletUsedAtDrinkingStation3Step {
    NoStudentsTaughtSafeWaterPrinciplesStep noTabletUsedAtDrinkingStation3(Integer noTabletUsedAtDrinkingStation3);
  }
  

  public interface NoStudentsTaughtSafeWaterPrinciplesStep {
    NoEnrolledInSchoolHealthClubStep noStudentsTaughtSafeWaterPrinciples(Integer noStudentsTaughtSafeWaterPrinciples);
  }
  

  public interface NoEnrolledInSchoolHealthClubStep {
    NoHealthClubMeetingHeldStep noEnrolledInSchoolHealthClub(Integer noEnrolledInSchoolHealthClub);
  }
  

  public interface NoHealthClubMeetingHeldStep {
    NoHealthClubLessonsTaughtStep noHealthClubMeetingHeld(Integer noHealthClubMeetingHeld);
  }
  

  public interface NoHealthClubLessonsTaughtStep {
    DoHealthClubManageStationsStep noHealthClubLessonsTaught(Integer noHealthClubLessonsTaught);
  }
  

  public interface DoHealthClubManageStationsStep {
    NoHealthClubCleanUpProjectStep doHealthClubManageStations(String doHealthClubManageStations);
  }
  

  public interface NoHealthClubCleanUpProjectStep {
    CompletedStep noHealthClubCleanUpProject(Integer noHealthClubCleanUpProject);
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
    SWEMonthlySchoolSummary build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep date(Temporal.Date date);
  }
  

  public static class Builder implements NamebweStep, SwePositionStep, CountryStep, CommunityStep, SchoolStep, NoTabletUsedAtDrinkingStation1Step, NoTabletUsedAtDrinkingStation2Step, NoTabletUsedAtDrinkingStation3Step, NoStudentsTaughtSafeWaterPrinciplesStep, NoEnrolledInSchoolHealthClubStep, NoHealthClubMeetingHeldStep, NoHealthClubLessonsTaughtStep, DoHealthClubManageStationsStep, NoHealthClubCleanUpProjectStep, CompletedStep, LatStep, LngStep, BuildStep {
    private String id;
    private String Namebwe;
    private String SWEPosition;
    private String Country;
    private String Community;
    private String School;
    private Integer NoTabletUsedAtDrinkingStation1;
    private Integer NoTabletUsedAtDrinkingStation2;
    private Integer NoTabletUsedAtDrinkingStation3;
    private Integer NoStudentsTaughtSafeWaterPrinciples;
    private Integer NoEnrolledInSchoolHealthClub;
    private Integer NoHealthClubMeetingHeld;
    private Integer NoHealthClubLessonsTaught;
    private String DoHealthClubManageStations;
    private Integer NoHealthClubCleanUpProject;
    private Integer Completed;
    private String Lat;
    private String Lng;
    private Temporal.Date date;
    @Override
     public SWEMonthlySchoolSummary build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new SWEMonthlySchoolSummary(
          id,
          Namebwe,
          date,
          SWEPosition,
          Country,
          Community,
          School,
          NoTabletUsedAtDrinkingStation1,
          NoTabletUsedAtDrinkingStation2,
          NoTabletUsedAtDrinkingStation3,
          NoStudentsTaughtSafeWaterPrinciples,
          NoEnrolledInSchoolHealthClub,
          NoHealthClubMeetingHeld,
          NoHealthClubLessonsTaught,
          DoHealthClubManageStations,
          NoHealthClubCleanUpProject,
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
     public CountryStep swePosition(String swePosition) {
        Objects.requireNonNull(swePosition);
        this.SWEPosition = swePosition;
        return this;
    }
    
    @Override
     public CommunityStep country(String country) {
        Objects.requireNonNull(country);
        this.Country = country;
        return this;
    }
    
    @Override
     public SchoolStep community(String community) {
        Objects.requireNonNull(community);
        this.Community = community;
        return this;
    }
    
    @Override
     public NoTabletUsedAtDrinkingStation1Step school(String school) {
        Objects.requireNonNull(school);
        this.School = school;
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
     public NoStudentsTaughtSafeWaterPrinciplesStep noTabletUsedAtDrinkingStation3(Integer noTabletUsedAtDrinkingStation3) {
        Objects.requireNonNull(noTabletUsedAtDrinkingStation3);
        this.NoTabletUsedAtDrinkingStation3 = noTabletUsedAtDrinkingStation3;
        return this;
    }
    
    @Override
     public NoEnrolledInSchoolHealthClubStep noStudentsTaughtSafeWaterPrinciples(Integer noStudentsTaughtSafeWaterPrinciples) {
        Objects.requireNonNull(noStudentsTaughtSafeWaterPrinciples);
        this.NoStudentsTaughtSafeWaterPrinciples = noStudentsTaughtSafeWaterPrinciples;
        return this;
    }
    
    @Override
     public NoHealthClubMeetingHeldStep noEnrolledInSchoolHealthClub(Integer noEnrolledInSchoolHealthClub) {
        Objects.requireNonNull(noEnrolledInSchoolHealthClub);
        this.NoEnrolledInSchoolHealthClub = noEnrolledInSchoolHealthClub;
        return this;
    }
    
    @Override
     public NoHealthClubLessonsTaughtStep noHealthClubMeetingHeld(Integer noHealthClubMeetingHeld) {
        Objects.requireNonNull(noHealthClubMeetingHeld);
        this.NoHealthClubMeetingHeld = noHealthClubMeetingHeld;
        return this;
    }
    
    @Override
     public DoHealthClubManageStationsStep noHealthClubLessonsTaught(Integer noHealthClubLessonsTaught) {
        Objects.requireNonNull(noHealthClubLessonsTaught);
        this.NoHealthClubLessonsTaught = noHealthClubLessonsTaught;
        return this;
    }
    
    @Override
     public NoHealthClubCleanUpProjectStep doHealthClubManageStations(String doHealthClubManageStations) {
        Objects.requireNonNull(doHealthClubManageStations);
        this.DoHealthClubManageStations = doHealthClubManageStations;
        return this;
    }
    
    @Override
     public CompletedStep noHealthClubCleanUpProject(Integer noHealthClubCleanUpProject) {
        Objects.requireNonNull(noHealthClubCleanUpProject);
        this.NoHealthClubCleanUpProject = noHealthClubCleanUpProject;
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
    private CopyOfBuilder(String id, String namebwe, Temporal.Date date, String swePosition, String country, String community, String school, Integer noTabletUsedAtDrinkingStation1, Integer noTabletUsedAtDrinkingStation2, Integer noTabletUsedAtDrinkingStation3, Integer noStudentsTaughtSafeWaterPrinciples, Integer noEnrolledInSchoolHealthClub, Integer noHealthClubMeetingHeld, Integer noHealthClubLessonsTaught, String doHealthClubManageStations, Integer noHealthClubCleanUpProject, Integer completed, String lat, String lng) {
      super.id(id);
      super.namebwe(namebwe)
        .swePosition(swePosition)
        .country(country)
        .community(community)
        .school(school)
        .noTabletUsedAtDrinkingStation1(noTabletUsedAtDrinkingStation1)
        .noTabletUsedAtDrinkingStation2(noTabletUsedAtDrinkingStation2)
        .noTabletUsedAtDrinkingStation3(noTabletUsedAtDrinkingStation3)
        .noStudentsTaughtSafeWaterPrinciples(noStudentsTaughtSafeWaterPrinciples)
        .noEnrolledInSchoolHealthClub(noEnrolledInSchoolHealthClub)
        .noHealthClubMeetingHeld(noHealthClubMeetingHeld)
        .noHealthClubLessonsTaught(noHealthClubLessonsTaught)
        .doHealthClubManageStations(doHealthClubManageStations)
        .noHealthClubCleanUpProject(noHealthClubCleanUpProject)
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
     public CopyOfBuilder country(String country) {
      return (CopyOfBuilder) super.country(country);
    }
    
    @Override
     public CopyOfBuilder community(String community) {
      return (CopyOfBuilder) super.community(community);
    }
    
    @Override
     public CopyOfBuilder school(String school) {
      return (CopyOfBuilder) super.school(school);
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
     public CopyOfBuilder noStudentsTaughtSafeWaterPrinciples(Integer noStudentsTaughtSafeWaterPrinciples) {
      return (CopyOfBuilder) super.noStudentsTaughtSafeWaterPrinciples(noStudentsTaughtSafeWaterPrinciples);
    }
    
    @Override
     public CopyOfBuilder noEnrolledInSchoolHealthClub(Integer noEnrolledInSchoolHealthClub) {
      return (CopyOfBuilder) super.noEnrolledInSchoolHealthClub(noEnrolledInSchoolHealthClub);
    }
    
    @Override
     public CopyOfBuilder noHealthClubMeetingHeld(Integer noHealthClubMeetingHeld) {
      return (CopyOfBuilder) super.noHealthClubMeetingHeld(noHealthClubMeetingHeld);
    }
    
    @Override
     public CopyOfBuilder noHealthClubLessonsTaught(Integer noHealthClubLessonsTaught) {
      return (CopyOfBuilder) super.noHealthClubLessonsTaught(noHealthClubLessonsTaught);
    }
    
    @Override
     public CopyOfBuilder doHealthClubManageStations(String doHealthClubManageStations) {
      return (CopyOfBuilder) super.doHealthClubManageStations(doHealthClubManageStations);
    }
    
    @Override
     public CopyOfBuilder noHealthClubCleanUpProject(Integer noHealthClubCleanUpProject) {
      return (CopyOfBuilder) super.noHealthClubCleanUpProject(noHealthClubCleanUpProject);
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
