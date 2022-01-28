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

/** This is an auto generated class representing the Meeting type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Meetings", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Meeting implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField COUNTRY = field("Country");
  public static final QueryField COMMUNITY = field("Community");
  public static final QueryField NAMEBWE = field("Namebwe");
  public static final QueryField DATE = field("date");
  public static final QueryField NAMEVOL = field("Namevol");
  public static final QueryField DISCUSSIONS_TAUGHT = field("DiscussionsTaught");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Country;
  private final @ModelField(targetType="String", isRequired = true) String Community;
  private final @ModelField(targetType="String", isRequired = true) String Namebwe;
  private final @ModelField(targetType="AWSDate") Temporal.Date date;
  private final @ModelField(targetType="String", isRequired = true) String Namevol;
  private final @ModelField(targetType="String", isRequired = true) String DiscussionsTaught;
  public String getId() {
      return id;
  }
  
  public String getCountry() {
      return Country;
  }
  
  public String getCommunity() {
      return Community;
  }
  
  public String getNamebwe() {
      return Namebwe;
  }
  
  public Temporal.Date getDate() {
      return date;
  }
  
  public String getNamevol() {
      return Namevol;
  }
  
  public String getDiscussionsTaught() {
      return DiscussionsTaught;
  }
  
  private Meeting(String id, String Country, String Community, String Namebwe, Temporal.Date date, String Namevol, String DiscussionsTaught) {
    this.id = id;
    this.Country = Country;
    this.Community = Community;
    this.Namebwe = Namebwe;
    this.date = date;
    this.Namevol = Namevol;
    this.DiscussionsTaught = DiscussionsTaught;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Meeting meeting = (Meeting) obj;
      return ObjectsCompat.equals(getId(), meeting.getId()) &&
              ObjectsCompat.equals(getCountry(), meeting.getCountry()) &&
              ObjectsCompat.equals(getCommunity(), meeting.getCommunity()) &&
              ObjectsCompat.equals(getNamebwe(), meeting.getNamebwe()) &&
              ObjectsCompat.equals(getDate(), meeting.getDate()) &&
              ObjectsCompat.equals(getNamevol(), meeting.getNamevol()) &&
              ObjectsCompat.equals(getDiscussionsTaught(), meeting.getDiscussionsTaught());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCountry())
      .append(getCommunity())
      .append(getNamebwe())
      .append(getDate())
      .append(getNamevol())
      .append(getDiscussionsTaught())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Meeting {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Country=" + String.valueOf(getCountry()) + ", ")
      .append("Community=" + String.valueOf(getCommunity()) + ", ")
      .append("Namebwe=" + String.valueOf(getNamebwe()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("Namevol=" + String.valueOf(getNamevol()) + ", ")
      .append("DiscussionsTaught=" + String.valueOf(getDiscussionsTaught()))
      .append("}")
      .toString();
  }
  
  public static CountryStep builder() {
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
  public static Meeting justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Meeting(
      id,
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
      Country,
      Community,
      Namebwe,
      date,
      Namevol,
      DiscussionsTaught);
  }
  public interface CountryStep {
    CommunityStep country(String country);
  }
  

  public interface CommunityStep {
    NamebweStep community(String community);
  }
  

  public interface NamebweStep {
    NamevolStep namebwe(String namebwe);
  }
  

  public interface NamevolStep {
    DiscussionsTaughtStep namevol(String namevol);
  }
  

  public interface DiscussionsTaughtStep {
    BuildStep discussionsTaught(String discussionsTaught);
  }
  

  public interface BuildStep {
    Meeting build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep date(Temporal.Date date);
  }
  

  public static class Builder implements CountryStep, CommunityStep, NamebweStep, NamevolStep, DiscussionsTaughtStep, BuildStep {
    private String id;
    private String Country;
    private String Community;
    private String Namebwe;
    private String Namevol;
    private String DiscussionsTaught;
    private Temporal.Date date;
    @Override
     public Meeting build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Meeting(
          id,
          Country,
          Community,
          Namebwe,
          date,
          Namevol,
          DiscussionsTaught);
    }
    
    @Override
     public CommunityStep country(String country) {
        Objects.requireNonNull(country);
        this.Country = country;
        return this;
    }
    
    @Override
     public NamebweStep community(String community) {
        Objects.requireNonNull(community);
        this.Community = community;
        return this;
    }
    
    @Override
     public NamevolStep namebwe(String namebwe) {
        Objects.requireNonNull(namebwe);
        this.Namebwe = namebwe;
        return this;
    }
    
    @Override
     public DiscussionsTaughtStep namevol(String namevol) {
        Objects.requireNonNull(namevol);
        this.Namevol = namevol;
        return this;
    }
    
    @Override
     public BuildStep discussionsTaught(String discussionsTaught) {
        Objects.requireNonNull(discussionsTaught);
        this.DiscussionsTaught = discussionsTaught;
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
    private CopyOfBuilder(String id, String country, String community, String namebwe, Temporal.Date date, String namevol, String discussionsTaught) {
      super.id(id);
      super.country(country)
        .community(community)
        .namebwe(namebwe)
        .namevol(namevol)
        .discussionsTaught(discussionsTaught)
        .date(date);
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
     public CopyOfBuilder namebwe(String namebwe) {
      return (CopyOfBuilder) super.namebwe(namebwe);
    }
    
    @Override
     public CopyOfBuilder namevol(String namevol) {
      return (CopyOfBuilder) super.namevol(namevol);
    }
    
    @Override
     public CopyOfBuilder discussionsTaught(String discussionsTaught) {
      return (CopyOfBuilder) super.discussionsTaught(discussionsTaught);
    }
    
    @Override
     public CopyOfBuilder date(Temporal.Date date) {
      return (CopyOfBuilder) super.date(date);
    }
  }
  
}
