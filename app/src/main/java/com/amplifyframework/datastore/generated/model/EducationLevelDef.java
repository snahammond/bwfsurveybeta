package com.amplifyframework.datastore.generated.model;


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

/** This is an auto generated class representing the EducationLevelDef type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "EducationLevelDefs")
public final class EducationLevelDef implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField FIELD_NAME = field("fieldName");
  public static final QueryField FIELD_DESCRIPTION = field("fieldDescription");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String fieldName;
  private final @ModelField(targetType="String", isRequired = true) String fieldDescription;
  public String getId() {
      return id;
  }
  
  public String getFieldName() {
      return fieldName;
  }
  
  public String getFieldDescription() {
      return fieldDescription;
  }
  
  private EducationLevelDef(String id, String fieldName, String fieldDescription) {
    this.id = id;
    this.fieldName = fieldName;
    this.fieldDescription = fieldDescription;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      EducationLevelDef educationLevelDef = (EducationLevelDef) obj;
      return ObjectsCompat.equals(getId(), educationLevelDef.getId()) &&
              ObjectsCompat.equals(getFieldName(), educationLevelDef.getFieldName()) &&
              ObjectsCompat.equals(getFieldDescription(), educationLevelDef.getFieldDescription());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getFieldName())
      .append(getFieldDescription())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("EducationLevelDef {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("fieldName=" + String.valueOf(getFieldName()) + ", ")
      .append("fieldDescription=" + String.valueOf(getFieldDescription()))
      .append("}")
      .toString();
  }
  
  public static FieldNameStep builder() {
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
  public static EducationLevelDef justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new EducationLevelDef(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      fieldName,
      fieldDescription);
  }
  public interface FieldNameStep {
    FieldDescriptionStep fieldName(String fieldName);
  }
  

  public interface FieldDescriptionStep {
    BuildStep fieldDescription(String fieldDescription);
  }
  

  public interface BuildStep {
    EducationLevelDef build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements FieldNameStep, FieldDescriptionStep, BuildStep {
    private String id;
    private String fieldName;
    private String fieldDescription;
    @Override
     public EducationLevelDef build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new EducationLevelDef(
          id,
          fieldName,
          fieldDescription);
    }
    
    @Override
     public FieldDescriptionStep fieldName(String fieldName) {
        Objects.requireNonNull(fieldName);
        this.fieldName = fieldName;
        return this;
    }
    
    @Override
     public BuildStep fieldDescription(String fieldDescription) {
        Objects.requireNonNull(fieldDescription);
        this.fieldDescription = fieldDescription;
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
    private CopyOfBuilder(String id, String fieldName, String fieldDescription) {
      super.id(id);
      super.fieldName(fieldName)
        .fieldDescription(fieldDescription);
    }
    
    @Override
     public CopyOfBuilder fieldName(String fieldName) {
      return (CopyOfBuilder) super.fieldName(fieldName);
    }
    
    @Override
     public CopyOfBuilder fieldDescription(String fieldDescription) {
      return (CopyOfBuilder) super.fieldDescription(fieldDescription);
    }
  }
  
}
