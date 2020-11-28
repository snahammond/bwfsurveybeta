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

/** This is an auto generated class representing the Family type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Families")
public final class Family implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAMEBWE = field("namebwe");
  public static final QueryField SURVEY_ID = field("surveyId");
  public static final QueryField COUNTRY = field("country");
  public static final QueryField COMMUNITY = field("community");
  public static final QueryField DATE = field("date");
  public static final QueryField HEAD_HOUSEHOLD_NAME = field("headHouseholdName");
  public static final QueryField HEAD_HOUSEHOLD_SEX = field("headHouseholdSex");
  public static final QueryField HEAD_HOUSEHOLD_MARITAL_STATUS = field("headHouseholdMaritalStatus");
  public static final QueryField HEAD_HOUSEHOLD_AGE = field("headHouseholdAge");
  public static final QueryField HEAD_HOUSEHOLD_OCCUPATION = field("headHouseholdOccupation");
  public static final QueryField HEAD_HOUSEHOLD_EDUCATION_LEVEL = field("headHouseholdEducationLevel");
  public static final QueryField INTERVIEWEE = field("interviewee");
  public static final QueryField NUMBER_PEOPLE_HOUSEHOLD = field("numberPeopleHousehold");
  public static final QueryField NUMBER_MALE_HOUSEHOLD1 = field("numberMaleHousehold1");
  public static final QueryField NUMBER_FEMALE_HOUSEHOLD1 = field("numberFemaleHousehold1");
  public static final QueryField NUMBER_MALE_HOUSEHOLD5 = field("numberMaleHousehold5");
  public static final QueryField NUMBER_FEMALE_HOUSEHOLD5 = field("numberFemaleHousehold5");
  public static final QueryField NUMBER_MALE_HOUSEHOLD12 = field("numberMaleHousehold12");
  public static final QueryField NUMBER_FEMALE_HOUSEHOLD12 = field("numberFemaleHousehold12");
  public static final QueryField NUMBER_MALE_HOUSEHOLD17 = field("numberMaleHousehold17");
  public static final QueryField NUMBER_FEMALE_HOUSEHOLD17 = field("numberFemaleHousehold17");
  public static final QueryField NUMBER_MALE_HOUSEHOLD18 = field("numberMaleHousehold18");
  public static final QueryField NUMBER_FEMALE_HOUSEHOLD18 = field("numberFemaleHousehold18");
  public static final QueryField REASON_CHILDREN_NOT_IN_SCHOOL = field("reasonChildrenNotInSchool");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String namebwe;
  private final @ModelField(targetType="Int", isRequired = true) Integer surveyId;
  private final @ModelField(targetType="String", isRequired = true) String country;
  private final @ModelField(targetType="String", isRequired = true) String community;
  private final @ModelField(targetType="AWSDate") Temporal.Date date;
  private final @ModelField(targetType="String", isRequired = true) String headHouseholdName;
  private final @ModelField(targetType="Sex", isRequired = true) Sex headHouseholdSex;
  private final @ModelField(targetType="MaritalStatus", isRequired = true) MaritalStatus headHouseholdMaritalStatus;
  private final @ModelField(targetType="Int", isRequired = true) Integer headHouseholdAge;
  private final @ModelField(targetType="Occupation", isRequired = true) Occupation headHouseholdOccupation;
  private final @ModelField(targetType="EducationLevel", isRequired = true) EducationLevel headHouseholdEducationLevel;
  private final @ModelField(targetType="Interviewee", isRequired = true) Interviewee interviewee;
  private final @ModelField(targetType="Int", isRequired = true) Integer numberPeopleHousehold;
  private final @ModelField(targetType="Int", isRequired = true) Integer numberMaleHousehold1;
  private final @ModelField(targetType="Int", isRequired = true) Integer numberFemaleHousehold1;
  private final @ModelField(targetType="Int", isRequired = true) Integer numberMaleHousehold5;
  private final @ModelField(targetType="Int", isRequired = true) Integer numberFemaleHousehold5;
  private final @ModelField(targetType="Int", isRequired = true) Integer numberMaleHousehold12;
  private final @ModelField(targetType="Int", isRequired = true) Integer numberFemaleHousehold12;
  private final @ModelField(targetType="Int", isRequired = true) Integer numberMaleHousehold17;
  private final @ModelField(targetType="Int", isRequired = true) Integer numberFemaleHousehold17;
  private final @ModelField(targetType="Int", isRequired = true) Integer numberMaleHousehold18;
  private final @ModelField(targetType="Int", isRequired = true) Integer numberFemaleHousehold18;
  private final @ModelField(targetType="NoSchoolReason", isRequired = true) NoSchoolReason reasonChildrenNotInSchool;
  public String getId() {
      return id;
  }
  
  public String getNamebwe() {
      return namebwe;
  }
  
  public Integer getSurveyId() {
      return surveyId;
  }
  
  public String getCountry() {
      return country;
  }
  
  public String getCommunity() {
      return community;
  }
  
  public Temporal.Date getDate() {
      return date;
  }
  
  public String getHeadHouseholdName() {
      return headHouseholdName;
  }
  
  public Sex getHeadHouseholdSex() {
      return headHouseholdSex;
  }
  
  public MaritalStatus getHeadHouseholdMaritalStatus() {
      return headHouseholdMaritalStatus;
  }
  
  public Integer getHeadHouseholdAge() {
      return headHouseholdAge;
  }
  
  public Occupation getHeadHouseholdOccupation() {
      return headHouseholdOccupation;
  }
  
  public EducationLevel getHeadHouseholdEducationLevel() {
      return headHouseholdEducationLevel;
  }
  
  public Interviewee getInterviewee() {
      return interviewee;
  }
  
  public Integer getNumberPeopleHousehold() {
      return numberPeopleHousehold;
  }
  
  public Integer getNumberMaleHousehold1() {
      return numberMaleHousehold1;
  }
  
  public Integer getNumberFemaleHousehold1() {
      return numberFemaleHousehold1;
  }
  
  public Integer getNumberMaleHousehold5() {
      return numberMaleHousehold5;
  }
  
  public Integer getNumberFemaleHousehold5() {
      return numberFemaleHousehold5;
  }
  
  public Integer getNumberMaleHousehold12() {
      return numberMaleHousehold12;
  }
  
  public Integer getNumberFemaleHousehold12() {
      return numberFemaleHousehold12;
  }
  
  public Integer getNumberMaleHousehold17() {
      return numberMaleHousehold17;
  }
  
  public Integer getNumberFemaleHousehold17() {
      return numberFemaleHousehold17;
  }
  
  public Integer getNumberMaleHousehold18() {
      return numberMaleHousehold18;
  }
  
  public Integer getNumberFemaleHousehold18() {
      return numberFemaleHousehold18;
  }
  
  public NoSchoolReason getReasonChildrenNotInSchool() {
      return reasonChildrenNotInSchool;
  }
  
  private Family(String id, String namebwe, Integer surveyId, String country, String community, Temporal.Date date, String headHouseholdName, Sex headHouseholdSex, MaritalStatus headHouseholdMaritalStatus, Integer headHouseholdAge, Occupation headHouseholdOccupation, EducationLevel headHouseholdEducationLevel, Interviewee interviewee, Integer numberPeopleHousehold, Integer numberMaleHousehold1, Integer numberFemaleHousehold1, Integer numberMaleHousehold5, Integer numberFemaleHousehold5, Integer numberMaleHousehold12, Integer numberFemaleHousehold12, Integer numberMaleHousehold17, Integer numberFemaleHousehold17, Integer numberMaleHousehold18, Integer numberFemaleHousehold18, NoSchoolReason reasonChildrenNotInSchool) {
    this.id = id;
    this.namebwe = namebwe;
    this.surveyId = surveyId;
    this.country = country;
    this.community = community;
    this.date = date;
    this.headHouseholdName = headHouseholdName;
    this.headHouseholdSex = headHouseholdSex;
    this.headHouseholdMaritalStatus = headHouseholdMaritalStatus;
    this.headHouseholdAge = headHouseholdAge;
    this.headHouseholdOccupation = headHouseholdOccupation;
    this.headHouseholdEducationLevel = headHouseholdEducationLevel;
    this.interviewee = interviewee;
    this.numberPeopleHousehold = numberPeopleHousehold;
    this.numberMaleHousehold1 = numberMaleHousehold1;
    this.numberFemaleHousehold1 = numberFemaleHousehold1;
    this.numberMaleHousehold5 = numberMaleHousehold5;
    this.numberFemaleHousehold5 = numberFemaleHousehold5;
    this.numberMaleHousehold12 = numberMaleHousehold12;
    this.numberFemaleHousehold12 = numberFemaleHousehold12;
    this.numberMaleHousehold17 = numberMaleHousehold17;
    this.numberFemaleHousehold17 = numberFemaleHousehold17;
    this.numberMaleHousehold18 = numberMaleHousehold18;
    this.numberFemaleHousehold18 = numberFemaleHousehold18;
    this.reasonChildrenNotInSchool = reasonChildrenNotInSchool;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Family family = (Family) obj;
      return ObjectsCompat.equals(getId(), family.getId()) &&
              ObjectsCompat.equals(getNamebwe(), family.getNamebwe()) &&
              ObjectsCompat.equals(getSurveyId(), family.getSurveyId()) &&
              ObjectsCompat.equals(getCountry(), family.getCountry()) &&
              ObjectsCompat.equals(getCommunity(), family.getCommunity()) &&
              ObjectsCompat.equals(getDate(), family.getDate()) &&
              ObjectsCompat.equals(getHeadHouseholdName(), family.getHeadHouseholdName()) &&
              ObjectsCompat.equals(getHeadHouseholdSex(), family.getHeadHouseholdSex()) &&
              ObjectsCompat.equals(getHeadHouseholdMaritalStatus(), family.getHeadHouseholdMaritalStatus()) &&
              ObjectsCompat.equals(getHeadHouseholdAge(), family.getHeadHouseholdAge()) &&
              ObjectsCompat.equals(getHeadHouseholdOccupation(), family.getHeadHouseholdOccupation()) &&
              ObjectsCompat.equals(getHeadHouseholdEducationLevel(), family.getHeadHouseholdEducationLevel()) &&
              ObjectsCompat.equals(getInterviewee(), family.getInterviewee()) &&
              ObjectsCompat.equals(getNumberPeopleHousehold(), family.getNumberPeopleHousehold()) &&
              ObjectsCompat.equals(getNumberMaleHousehold1(), family.getNumberMaleHousehold1()) &&
              ObjectsCompat.equals(getNumberFemaleHousehold1(), family.getNumberFemaleHousehold1()) &&
              ObjectsCompat.equals(getNumberMaleHousehold5(), family.getNumberMaleHousehold5()) &&
              ObjectsCompat.equals(getNumberFemaleHousehold5(), family.getNumberFemaleHousehold5()) &&
              ObjectsCompat.equals(getNumberMaleHousehold12(), family.getNumberMaleHousehold12()) &&
              ObjectsCompat.equals(getNumberFemaleHousehold12(), family.getNumberFemaleHousehold12()) &&
              ObjectsCompat.equals(getNumberMaleHousehold17(), family.getNumberMaleHousehold17()) &&
              ObjectsCompat.equals(getNumberFemaleHousehold17(), family.getNumberFemaleHousehold17()) &&
              ObjectsCompat.equals(getNumberMaleHousehold18(), family.getNumberMaleHousehold18()) &&
              ObjectsCompat.equals(getNumberFemaleHousehold18(), family.getNumberFemaleHousehold18()) &&
              ObjectsCompat.equals(getReasonChildrenNotInSchool(), family.getReasonChildrenNotInSchool());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getNamebwe())
      .append(getSurveyId())
      .append(getCountry())
      .append(getCommunity())
      .append(getDate())
      .append(getHeadHouseholdName())
      .append(getHeadHouseholdSex())
      .append(getHeadHouseholdMaritalStatus())
      .append(getHeadHouseholdAge())
      .append(getHeadHouseholdOccupation())
      .append(getHeadHouseholdEducationLevel())
      .append(getInterviewee())
      .append(getNumberPeopleHousehold())
      .append(getNumberMaleHousehold1())
      .append(getNumberFemaleHousehold1())
      .append(getNumberMaleHousehold5())
      .append(getNumberFemaleHousehold5())
      .append(getNumberMaleHousehold12())
      .append(getNumberFemaleHousehold12())
      .append(getNumberMaleHousehold17())
      .append(getNumberFemaleHousehold17())
      .append(getNumberMaleHousehold18())
      .append(getNumberFemaleHousehold18())
      .append(getReasonChildrenNotInSchool())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Family {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("namebwe=" + String.valueOf(getNamebwe()) + ", ")
      .append("surveyId=" + String.valueOf(getSurveyId()) + ", ")
      .append("country=" + String.valueOf(getCountry()) + ", ")
      .append("community=" + String.valueOf(getCommunity()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("headHouseholdName=" + String.valueOf(getHeadHouseholdName()) + ", ")
      .append("headHouseholdSex=" + String.valueOf(getHeadHouseholdSex()) + ", ")
      .append("headHouseholdMaritalStatus=" + String.valueOf(getHeadHouseholdMaritalStatus()) + ", ")
      .append("headHouseholdAge=" + String.valueOf(getHeadHouseholdAge()) + ", ")
      .append("headHouseholdOccupation=" + String.valueOf(getHeadHouseholdOccupation()) + ", ")
      .append("headHouseholdEducationLevel=" + String.valueOf(getHeadHouseholdEducationLevel()) + ", ")
      .append("interviewee=" + String.valueOf(getInterviewee()) + ", ")
      .append("numberPeopleHousehold=" + String.valueOf(getNumberPeopleHousehold()) + ", ")
      .append("numberMaleHousehold1=" + String.valueOf(getNumberMaleHousehold1()) + ", ")
      .append("numberFemaleHousehold1=" + String.valueOf(getNumberFemaleHousehold1()) + ", ")
      .append("numberMaleHousehold5=" + String.valueOf(getNumberMaleHousehold5()) + ", ")
      .append("numberFemaleHousehold5=" + String.valueOf(getNumberFemaleHousehold5()) + ", ")
      .append("numberMaleHousehold12=" + String.valueOf(getNumberMaleHousehold12()) + ", ")
      .append("numberFemaleHousehold12=" + String.valueOf(getNumberFemaleHousehold12()) + ", ")
      .append("numberMaleHousehold17=" + String.valueOf(getNumberMaleHousehold17()) + ", ")
      .append("numberFemaleHousehold17=" + String.valueOf(getNumberFemaleHousehold17()) + ", ")
      .append("numberMaleHousehold18=" + String.valueOf(getNumberMaleHousehold18()) + ", ")
      .append("numberFemaleHousehold18=" + String.valueOf(getNumberFemaleHousehold18()) + ", ")
      .append("reasonChildrenNotInSchool=" + String.valueOf(getReasonChildrenNotInSchool()))
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
  public static Family justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Family(
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
      namebwe,
      surveyId,
      country,
      community,
      date,
      headHouseholdName,
      headHouseholdSex,
      headHouseholdMaritalStatus,
      headHouseholdAge,
      headHouseholdOccupation,
      headHouseholdEducationLevel,
      interviewee,
      numberPeopleHousehold,
      numberMaleHousehold1,
      numberFemaleHousehold1,
      numberMaleHousehold5,
      numberFemaleHousehold5,
      numberMaleHousehold12,
      numberFemaleHousehold12,
      numberMaleHousehold17,
      numberFemaleHousehold17,
      numberMaleHousehold18,
      numberFemaleHousehold18,
      reasonChildrenNotInSchool);
  }
  public interface NamebweStep {
    SurveyIdStep namebwe(String namebwe);
  }
  

  public interface SurveyIdStep {
    CountryStep surveyId(Integer surveyId);
  }
  

  public interface CountryStep {
    CommunityStep country(String country);
  }
  

  public interface CommunityStep {
    HeadHouseholdNameStep community(String community);
  }
  

  public interface HeadHouseholdNameStep {
    HeadHouseholdSexStep headHouseholdName(String headHouseholdName);
  }
  

  public interface HeadHouseholdSexStep {
    HeadHouseholdMaritalStatusStep headHouseholdSex(Sex headHouseholdSex);
  }
  

  public interface HeadHouseholdMaritalStatusStep {
    HeadHouseholdAgeStep headHouseholdMaritalStatus(MaritalStatus headHouseholdMaritalStatus);
  }
  

  public interface HeadHouseholdAgeStep {
    HeadHouseholdOccupationStep headHouseholdAge(Integer headHouseholdAge);
  }
  

  public interface HeadHouseholdOccupationStep {
    HeadHouseholdEducationLevelStep headHouseholdOccupation(Occupation headHouseholdOccupation);
  }
  

  public interface HeadHouseholdEducationLevelStep {
    IntervieweeStep headHouseholdEducationLevel(EducationLevel headHouseholdEducationLevel);
  }
  

  public interface IntervieweeStep {
    NumberPeopleHouseholdStep interviewee(Interviewee interviewee);
  }
  

  public interface NumberPeopleHouseholdStep {
    NumberMaleHousehold1Step numberPeopleHousehold(Integer numberPeopleHousehold);
  }
  

  public interface NumberMaleHousehold1Step {
    NumberFemaleHousehold1Step numberMaleHousehold1(Integer numberMaleHousehold1);
  }
  

  public interface NumberFemaleHousehold1Step {
    NumberMaleHousehold5Step numberFemaleHousehold1(Integer numberFemaleHousehold1);
  }
  

  public interface NumberMaleHousehold5Step {
    NumberFemaleHousehold5Step numberMaleHousehold5(Integer numberMaleHousehold5);
  }
  

  public interface NumberFemaleHousehold5Step {
    NumberMaleHousehold12Step numberFemaleHousehold5(Integer numberFemaleHousehold5);
  }
  

  public interface NumberMaleHousehold12Step {
    NumberFemaleHousehold12Step numberMaleHousehold12(Integer numberMaleHousehold12);
  }
  

  public interface NumberFemaleHousehold12Step {
    NumberMaleHousehold17Step numberFemaleHousehold12(Integer numberFemaleHousehold12);
  }
  

  public interface NumberMaleHousehold17Step {
    NumberFemaleHousehold17Step numberMaleHousehold17(Integer numberMaleHousehold17);
  }
  

  public interface NumberFemaleHousehold17Step {
    NumberMaleHousehold18Step numberFemaleHousehold17(Integer numberFemaleHousehold17);
  }
  

  public interface NumberMaleHousehold18Step {
    NumberFemaleHousehold18Step numberMaleHousehold18(Integer numberMaleHousehold18);
  }
  

  public interface NumberFemaleHousehold18Step {
    ReasonChildrenNotInSchoolStep numberFemaleHousehold18(Integer numberFemaleHousehold18);
  }
  

  public interface ReasonChildrenNotInSchoolStep {
    BuildStep reasonChildrenNotInSchool(NoSchoolReason reasonChildrenNotInSchool);
  }
  

  public interface BuildStep {
    Family build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep date(Temporal.Date date);
  }
  

  public static class Builder implements NamebweStep, SurveyIdStep, CountryStep, CommunityStep, HeadHouseholdNameStep, HeadHouseholdSexStep, HeadHouseholdMaritalStatusStep, HeadHouseholdAgeStep, HeadHouseholdOccupationStep, HeadHouseholdEducationLevelStep, IntervieweeStep, NumberPeopleHouseholdStep, NumberMaleHousehold1Step, NumberFemaleHousehold1Step, NumberMaleHousehold5Step, NumberFemaleHousehold5Step, NumberMaleHousehold12Step, NumberFemaleHousehold12Step, NumberMaleHousehold17Step, NumberFemaleHousehold17Step, NumberMaleHousehold18Step, NumberFemaleHousehold18Step, ReasonChildrenNotInSchoolStep, BuildStep {
    private String id;
    private String namebwe;
    private Integer surveyId;
    private String country;
    private String community;
    private String headHouseholdName;
    private Sex headHouseholdSex;
    private MaritalStatus headHouseholdMaritalStatus;
    private Integer headHouseholdAge;
    private Occupation headHouseholdOccupation;
    private EducationLevel headHouseholdEducationLevel;
    private Interviewee interviewee;
    private Integer numberPeopleHousehold;
    private Integer numberMaleHousehold1;
    private Integer numberFemaleHousehold1;
    private Integer numberMaleHousehold5;
    private Integer numberFemaleHousehold5;
    private Integer numberMaleHousehold12;
    private Integer numberFemaleHousehold12;
    private Integer numberMaleHousehold17;
    private Integer numberFemaleHousehold17;
    private Integer numberMaleHousehold18;
    private Integer numberFemaleHousehold18;
    private NoSchoolReason reasonChildrenNotInSchool;
    private Temporal.Date date;
    @Override
     public Family build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Family(
          id,
          namebwe,
          surveyId,
          country,
          community,
          date,
          headHouseholdName,
          headHouseholdSex,
          headHouseholdMaritalStatus,
          headHouseholdAge,
          headHouseholdOccupation,
          headHouseholdEducationLevel,
          interviewee,
          numberPeopleHousehold,
          numberMaleHousehold1,
          numberFemaleHousehold1,
          numberMaleHousehold5,
          numberFemaleHousehold5,
          numberMaleHousehold12,
          numberFemaleHousehold12,
          numberMaleHousehold17,
          numberFemaleHousehold17,
          numberMaleHousehold18,
          numberFemaleHousehold18,
          reasonChildrenNotInSchool);
    }
    
    @Override
     public SurveyIdStep namebwe(String namebwe) {
        Objects.requireNonNull(namebwe);
        this.namebwe = namebwe;
        return this;
    }
    
    @Override
     public CountryStep surveyId(Integer surveyId) {
        Objects.requireNonNull(surveyId);
        this.surveyId = surveyId;
        return this;
    }
    
    @Override
     public CommunityStep country(String country) {
        Objects.requireNonNull(country);
        this.country = country;
        return this;
    }
    
    @Override
     public HeadHouseholdNameStep community(String community) {
        Objects.requireNonNull(community);
        this.community = community;
        return this;
    }
    
    @Override
     public HeadHouseholdSexStep headHouseholdName(String headHouseholdName) {
        Objects.requireNonNull(headHouseholdName);
        this.headHouseholdName = headHouseholdName;
        return this;
    }
    
    @Override
     public HeadHouseholdMaritalStatusStep headHouseholdSex(Sex headHouseholdSex) {
        Objects.requireNonNull(headHouseholdSex);
        this.headHouseholdSex = headHouseholdSex;
        return this;
    }
    
    @Override
     public HeadHouseholdAgeStep headHouseholdMaritalStatus(MaritalStatus headHouseholdMaritalStatus) {
        Objects.requireNonNull(headHouseholdMaritalStatus);
        this.headHouseholdMaritalStatus = headHouseholdMaritalStatus;
        return this;
    }
    
    @Override
     public HeadHouseholdOccupationStep headHouseholdAge(Integer headHouseholdAge) {
        Objects.requireNonNull(headHouseholdAge);
        this.headHouseholdAge = headHouseholdAge;
        return this;
    }
    
    @Override
     public HeadHouseholdEducationLevelStep headHouseholdOccupation(Occupation headHouseholdOccupation) {
        Objects.requireNonNull(headHouseholdOccupation);
        this.headHouseholdOccupation = headHouseholdOccupation;
        return this;
    }
    
    @Override
     public IntervieweeStep headHouseholdEducationLevel(EducationLevel headHouseholdEducationLevel) {
        Objects.requireNonNull(headHouseholdEducationLevel);
        this.headHouseholdEducationLevel = headHouseholdEducationLevel;
        return this;
    }
    
    @Override
     public NumberPeopleHouseholdStep interviewee(Interviewee interviewee) {
        Objects.requireNonNull(interviewee);
        this.interviewee = interviewee;
        return this;
    }
    
    @Override
     public NumberMaleHousehold1Step numberPeopleHousehold(Integer numberPeopleHousehold) {
        Objects.requireNonNull(numberPeopleHousehold);
        this.numberPeopleHousehold = numberPeopleHousehold;
        return this;
    }
    
    @Override
     public NumberFemaleHousehold1Step numberMaleHousehold1(Integer numberMaleHousehold1) {
        Objects.requireNonNull(numberMaleHousehold1);
        this.numberMaleHousehold1 = numberMaleHousehold1;
        return this;
    }
    
    @Override
     public NumberMaleHousehold5Step numberFemaleHousehold1(Integer numberFemaleHousehold1) {
        Objects.requireNonNull(numberFemaleHousehold1);
        this.numberFemaleHousehold1 = numberFemaleHousehold1;
        return this;
    }
    
    @Override
     public NumberFemaleHousehold5Step numberMaleHousehold5(Integer numberMaleHousehold5) {
        Objects.requireNonNull(numberMaleHousehold5);
        this.numberMaleHousehold5 = numberMaleHousehold5;
        return this;
    }
    
    @Override
     public NumberMaleHousehold12Step numberFemaleHousehold5(Integer numberFemaleHousehold5) {
        Objects.requireNonNull(numberFemaleHousehold5);
        this.numberFemaleHousehold5 = numberFemaleHousehold5;
        return this;
    }
    
    @Override
     public NumberFemaleHousehold12Step numberMaleHousehold12(Integer numberMaleHousehold12) {
        Objects.requireNonNull(numberMaleHousehold12);
        this.numberMaleHousehold12 = numberMaleHousehold12;
        return this;
    }
    
    @Override
     public NumberMaleHousehold17Step numberFemaleHousehold12(Integer numberFemaleHousehold12) {
        Objects.requireNonNull(numberFemaleHousehold12);
        this.numberFemaleHousehold12 = numberFemaleHousehold12;
        return this;
    }
    
    @Override
     public NumberFemaleHousehold17Step numberMaleHousehold17(Integer numberMaleHousehold17) {
        Objects.requireNonNull(numberMaleHousehold17);
        this.numberMaleHousehold17 = numberMaleHousehold17;
        return this;
    }
    
    @Override
     public NumberMaleHousehold18Step numberFemaleHousehold17(Integer numberFemaleHousehold17) {
        Objects.requireNonNull(numberFemaleHousehold17);
        this.numberFemaleHousehold17 = numberFemaleHousehold17;
        return this;
    }
    
    @Override
     public NumberFemaleHousehold18Step numberMaleHousehold18(Integer numberMaleHousehold18) {
        Objects.requireNonNull(numberMaleHousehold18);
        this.numberMaleHousehold18 = numberMaleHousehold18;
        return this;
    }
    
    @Override
     public ReasonChildrenNotInSchoolStep numberFemaleHousehold18(Integer numberFemaleHousehold18) {
        Objects.requireNonNull(numberFemaleHousehold18);
        this.numberFemaleHousehold18 = numberFemaleHousehold18;
        return this;
    }
    
    @Override
     public BuildStep reasonChildrenNotInSchool(NoSchoolReason reasonChildrenNotInSchool) {
        Objects.requireNonNull(reasonChildrenNotInSchool);
        this.reasonChildrenNotInSchool = reasonChildrenNotInSchool;
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
    private CopyOfBuilder(String id, String namebwe, Integer surveyId, String country, String community, Temporal.Date date, String headHouseholdName, Sex headHouseholdSex, MaritalStatus headHouseholdMaritalStatus, Integer headHouseholdAge, Occupation headHouseholdOccupation, EducationLevel headHouseholdEducationLevel, Interviewee interviewee, Integer numberPeopleHousehold, Integer numberMaleHousehold1, Integer numberFemaleHousehold1, Integer numberMaleHousehold5, Integer numberFemaleHousehold5, Integer numberMaleHousehold12, Integer numberFemaleHousehold12, Integer numberMaleHousehold17, Integer numberFemaleHousehold17, Integer numberMaleHousehold18, Integer numberFemaleHousehold18, NoSchoolReason reasonChildrenNotInSchool) {
      super.id(id);
      super.namebwe(namebwe)
        .surveyId(surveyId)
        .country(country)
        .community(community)
        .headHouseholdName(headHouseholdName)
        .headHouseholdSex(headHouseholdSex)
        .headHouseholdMaritalStatus(headHouseholdMaritalStatus)
        .headHouseholdAge(headHouseholdAge)
        .headHouseholdOccupation(headHouseholdOccupation)
        .headHouseholdEducationLevel(headHouseholdEducationLevel)
        .interviewee(interviewee)
        .numberPeopleHousehold(numberPeopleHousehold)
        .numberMaleHousehold1(numberMaleHousehold1)
        .numberFemaleHousehold1(numberFemaleHousehold1)
        .numberMaleHousehold5(numberMaleHousehold5)
        .numberFemaleHousehold5(numberFemaleHousehold5)
        .numberMaleHousehold12(numberMaleHousehold12)
        .numberFemaleHousehold12(numberFemaleHousehold12)
        .numberMaleHousehold17(numberMaleHousehold17)
        .numberFemaleHousehold17(numberFemaleHousehold17)
        .numberMaleHousehold18(numberMaleHousehold18)
        .numberFemaleHousehold18(numberFemaleHousehold18)
        .reasonChildrenNotInSchool(reasonChildrenNotInSchool)
        .date(date);
    }
    
    @Override
     public CopyOfBuilder namebwe(String namebwe) {
      return (CopyOfBuilder) super.namebwe(namebwe);
    }
    
    @Override
     public CopyOfBuilder surveyId(Integer surveyId) {
      return (CopyOfBuilder) super.surveyId(surveyId);
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
     public CopyOfBuilder headHouseholdSex(Sex headHouseholdSex) {
      return (CopyOfBuilder) super.headHouseholdSex(headHouseholdSex);
    }
    
    @Override
     public CopyOfBuilder headHouseholdMaritalStatus(MaritalStatus headHouseholdMaritalStatus) {
      return (CopyOfBuilder) super.headHouseholdMaritalStatus(headHouseholdMaritalStatus);
    }
    
    @Override
     public CopyOfBuilder headHouseholdAge(Integer headHouseholdAge) {
      return (CopyOfBuilder) super.headHouseholdAge(headHouseholdAge);
    }
    
    @Override
     public CopyOfBuilder headHouseholdOccupation(Occupation headHouseholdOccupation) {
      return (CopyOfBuilder) super.headHouseholdOccupation(headHouseholdOccupation);
    }
    
    @Override
     public CopyOfBuilder headHouseholdEducationLevel(EducationLevel headHouseholdEducationLevel) {
      return (CopyOfBuilder) super.headHouseholdEducationLevel(headHouseholdEducationLevel);
    }
    
    @Override
     public CopyOfBuilder interviewee(Interviewee interviewee) {
      return (CopyOfBuilder) super.interviewee(interviewee);
    }
    
    @Override
     public CopyOfBuilder numberPeopleHousehold(Integer numberPeopleHousehold) {
      return (CopyOfBuilder) super.numberPeopleHousehold(numberPeopleHousehold);
    }
    
    @Override
     public CopyOfBuilder numberMaleHousehold1(Integer numberMaleHousehold1) {
      return (CopyOfBuilder) super.numberMaleHousehold1(numberMaleHousehold1);
    }
    
    @Override
     public CopyOfBuilder numberFemaleHousehold1(Integer numberFemaleHousehold1) {
      return (CopyOfBuilder) super.numberFemaleHousehold1(numberFemaleHousehold1);
    }
    
    @Override
     public CopyOfBuilder numberMaleHousehold5(Integer numberMaleHousehold5) {
      return (CopyOfBuilder) super.numberMaleHousehold5(numberMaleHousehold5);
    }
    
    @Override
     public CopyOfBuilder numberFemaleHousehold5(Integer numberFemaleHousehold5) {
      return (CopyOfBuilder) super.numberFemaleHousehold5(numberFemaleHousehold5);
    }
    
    @Override
     public CopyOfBuilder numberMaleHousehold12(Integer numberMaleHousehold12) {
      return (CopyOfBuilder) super.numberMaleHousehold12(numberMaleHousehold12);
    }
    
    @Override
     public CopyOfBuilder numberFemaleHousehold12(Integer numberFemaleHousehold12) {
      return (CopyOfBuilder) super.numberFemaleHousehold12(numberFemaleHousehold12);
    }
    
    @Override
     public CopyOfBuilder numberMaleHousehold17(Integer numberMaleHousehold17) {
      return (CopyOfBuilder) super.numberMaleHousehold17(numberMaleHousehold17);
    }
    
    @Override
     public CopyOfBuilder numberFemaleHousehold17(Integer numberFemaleHousehold17) {
      return (CopyOfBuilder) super.numberFemaleHousehold17(numberFemaleHousehold17);
    }
    
    @Override
     public CopyOfBuilder numberMaleHousehold18(Integer numberMaleHousehold18) {
      return (CopyOfBuilder) super.numberMaleHousehold18(numberMaleHousehold18);
    }
    
    @Override
     public CopyOfBuilder numberFemaleHousehold18(Integer numberFemaleHousehold18) {
      return (CopyOfBuilder) super.numberFemaleHousehold18(numberFemaleHousehold18);
    }
    
    @Override
     public CopyOfBuilder reasonChildrenNotInSchool(NoSchoolReason reasonChildrenNotInSchool) {
      return (CopyOfBuilder) super.reasonChildrenNotInSchool(reasonChildrenNotInSchool);
    }
    
    @Override
     public CopyOfBuilder date(Temporal.Date date) {
      return (CopyOfBuilder) super.date(date);
    }
  }
  
}
