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

/** This is an auto generated class representing the FamilyDef type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "FamilyDefs")
public final class FamilyDef implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField FIELD_NAME = field("fieldName");
  public static final QueryField FIELD_QUESTION_TEXT = field("fieldQuestionText");
  public static final QueryField FIELD_ANSWER_TYPE = field("fieldAnswerType");
  public static final QueryField FIELD_ANSWER_TYPE_NAME = field("fieldAnswerTypeName");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String fieldName;
  private final @ModelField(targetType="String", isRequired = true) String fieldQuestionText;
  private final @ModelField(targetType="AnswerType", isRequired = true) AnswerType fieldAnswerType;
  private final @ModelField(targetType="String", isRequired = true) String fieldAnswerTypeName;
  public String getId() {
      return id;
  }
  
  public String getFieldName() {
      return fieldName;
  }
  
  public String getFieldQuestionText() {
      return fieldQuestionText;
  }
  
  public AnswerType getFieldAnswerType() {
      return fieldAnswerType;
  }
  
  public String getFieldAnswerTypeName() {
      return fieldAnswerTypeName;
  }
  
  private FamilyDef(String id, String fieldName, String fieldQuestionText, AnswerType fieldAnswerType, String fieldAnswerTypeName) {
    this.id = id;
    this.fieldName = fieldName;
    this.fieldQuestionText = fieldQuestionText;
    this.fieldAnswerType = fieldAnswerType;
    this.fieldAnswerTypeName = fieldAnswerTypeName;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      FamilyDef familyDef = (FamilyDef) obj;
      return ObjectsCompat.equals(getId(), familyDef.getId()) &&
              ObjectsCompat.equals(getFieldName(), familyDef.getFieldName()) &&
              ObjectsCompat.equals(getFieldQuestionText(), familyDef.getFieldQuestionText()) &&
              ObjectsCompat.equals(getFieldAnswerType(), familyDef.getFieldAnswerType()) &&
              ObjectsCompat.equals(getFieldAnswerTypeName(), familyDef.getFieldAnswerTypeName());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getFieldName())
      .append(getFieldQuestionText())
      .append(getFieldAnswerType())
      .append(getFieldAnswerTypeName())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("FamilyDef {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("fieldName=" + String.valueOf(getFieldName()) + ", ")
      .append("fieldQuestionText=" + String.valueOf(getFieldQuestionText()) + ", ")
      .append("fieldAnswerType=" + String.valueOf(getFieldAnswerType()) + ", ")
      .append("fieldAnswerTypeName=" + String.valueOf(getFieldAnswerTypeName()))
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
  public static FamilyDef justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new FamilyDef(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      fieldName,
      fieldQuestionText,
      fieldAnswerType,
      fieldAnswerTypeName);
  }
  public interface FieldNameStep {
    FieldQuestionTextStep fieldName(String fieldName);
  }
  

  public interface FieldQuestionTextStep {
    FieldAnswerTypeStep fieldQuestionText(String fieldQuestionText);
  }
  

  public interface FieldAnswerTypeStep {
    FieldAnswerTypeNameStep fieldAnswerType(AnswerType fieldAnswerType);
  }
  

  public interface FieldAnswerTypeNameStep {
    BuildStep fieldAnswerTypeName(String fieldAnswerTypeName);
  }
  

  public interface BuildStep {
    FamilyDef build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements FieldNameStep, FieldQuestionTextStep, FieldAnswerTypeStep, FieldAnswerTypeNameStep, BuildStep {
    private String id;
    private String fieldName;
    private String fieldQuestionText;
    private AnswerType fieldAnswerType;
    private String fieldAnswerTypeName;
    @Override
     public FamilyDef build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new FamilyDef(
          id,
          fieldName,
          fieldQuestionText,
          fieldAnswerType,
          fieldAnswerTypeName);
    }
    
    @Override
     public FieldQuestionTextStep fieldName(String fieldName) {
        Objects.requireNonNull(fieldName);
        this.fieldName = fieldName;
        return this;
    }
    
    @Override
     public FieldAnswerTypeStep fieldQuestionText(String fieldQuestionText) {
        Objects.requireNonNull(fieldQuestionText);
        this.fieldQuestionText = fieldQuestionText;
        return this;
    }
    
    @Override
     public FieldAnswerTypeNameStep fieldAnswerType(AnswerType fieldAnswerType) {
        Objects.requireNonNull(fieldAnswerType);
        this.fieldAnswerType = fieldAnswerType;
        return this;
    }
    
    @Override
     public BuildStep fieldAnswerTypeName(String fieldAnswerTypeName) {
        Objects.requireNonNull(fieldAnswerTypeName);
        this.fieldAnswerTypeName = fieldAnswerTypeName;
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
    private CopyOfBuilder(String id, String fieldName, String fieldQuestionText, AnswerType fieldAnswerType, String fieldAnswerTypeName) {
      super.id(id);
      super.fieldName(fieldName)
        .fieldQuestionText(fieldQuestionText)
        .fieldAnswerType(fieldAnswerType)
        .fieldAnswerTypeName(fieldAnswerTypeName);
    }
    
    @Override
     public CopyOfBuilder fieldName(String fieldName) {
      return (CopyOfBuilder) super.fieldName(fieldName);
    }
    
    @Override
     public CopyOfBuilder fieldQuestionText(String fieldQuestionText) {
      return (CopyOfBuilder) super.fieldQuestionText(fieldQuestionText);
    }
    
    @Override
     public CopyOfBuilder fieldAnswerType(AnswerType fieldAnswerType) {
      return (CopyOfBuilder) super.fieldAnswerType(fieldAnswerType);
    }
    
    @Override
     public CopyOfBuilder fieldAnswerTypeName(String fieldAnswerTypeName) {
      return (CopyOfBuilder) super.fieldAnswerTypeName(fieldAnswerTypeName);
    }
  }
  
}
