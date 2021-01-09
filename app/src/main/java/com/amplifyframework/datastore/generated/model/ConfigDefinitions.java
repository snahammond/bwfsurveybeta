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

/** This is an auto generated class representing the ConfigDefinitions type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ConfigDefinitions", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class ConfigDefinitions implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField TYPE = field("type");
  public static final QueryField NAME = field("name");
  public static final QueryField VALUE = field("value");
  public static final QueryField DESC = field("desc");
  public static final QueryField CHILDNAME = field("childname");
  public static final QueryField CHILDVALUE = field("childvalue");
  public static final QueryField CHILDDESC = field("childdesc");
  public static final QueryField PARENTNAME = field("parentname");
  public static final QueryField PARENTVALUE = field("parentvalue");
  public static final QueryField PARENTDESC = field("parentdesc");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String type;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String", isRequired = true) String value;
  private final @ModelField(targetType="String", isRequired = true) String desc;
  private final @ModelField(targetType="String", isRequired = true) String childname;
  private final @ModelField(targetType="String", isRequired = true) String childvalue;
  private final @ModelField(targetType="String", isRequired = true) String childdesc;
  private final @ModelField(targetType="String", isRequired = true) String parentname;
  private final @ModelField(targetType="String", isRequired = true) String parentvalue;
  private final @ModelField(targetType="String", isRequired = true) String parentdesc;
  public String getId() {
      return id;
  }
  
  public String getType() {
      return type;
  }
  
  public String getName() {
      return name;
  }
  
  public String getValue() {
      return value;
  }
  
  public String getDesc() {
      return desc;
  }
  
  public String getChildname() {
      return childname;
  }
  
  public String getChildvalue() {
      return childvalue;
  }
  
  public String getChilddesc() {
      return childdesc;
  }
  
  public String getParentname() {
      return parentname;
  }
  
  public String getParentvalue() {
      return parentvalue;
  }
  
  public String getParentdesc() {
      return parentdesc;
  }
  
  private ConfigDefinitions(String id, String type, String name, String value, String desc, String childname, String childvalue, String childdesc, String parentname, String parentvalue, String parentdesc) {
    this.id = id;
    this.type = type;
    this.name = name;
    this.value = value;
    this.desc = desc;
    this.childname = childname;
    this.childvalue = childvalue;
    this.childdesc = childdesc;
    this.parentname = parentname;
    this.parentvalue = parentvalue;
    this.parentdesc = parentdesc;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ConfigDefinitions configDefinitions = (ConfigDefinitions) obj;
      return ObjectsCompat.equals(getId(), configDefinitions.getId()) &&
              ObjectsCompat.equals(getType(), configDefinitions.getType()) &&
              ObjectsCompat.equals(getName(), configDefinitions.getName()) &&
              ObjectsCompat.equals(getValue(), configDefinitions.getValue()) &&
              ObjectsCompat.equals(getDesc(), configDefinitions.getDesc()) &&
              ObjectsCompat.equals(getChildname(), configDefinitions.getChildname()) &&
              ObjectsCompat.equals(getChildvalue(), configDefinitions.getChildvalue()) &&
              ObjectsCompat.equals(getChilddesc(), configDefinitions.getChilddesc()) &&
              ObjectsCompat.equals(getParentname(), configDefinitions.getParentname()) &&
              ObjectsCompat.equals(getParentvalue(), configDefinitions.getParentvalue()) &&
              ObjectsCompat.equals(getParentdesc(), configDefinitions.getParentdesc());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getType())
      .append(getName())
      .append(getValue())
      .append(getDesc())
      .append(getChildname())
      .append(getChildvalue())
      .append(getChilddesc())
      .append(getParentname())
      .append(getParentvalue())
      .append(getParentdesc())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ConfigDefinitions {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("type=" + String.valueOf(getType()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("value=" + String.valueOf(getValue()) + ", ")
      .append("desc=" + String.valueOf(getDesc()) + ", ")
      .append("childname=" + String.valueOf(getChildname()) + ", ")
      .append("childvalue=" + String.valueOf(getChildvalue()) + ", ")
      .append("childdesc=" + String.valueOf(getChilddesc()) + ", ")
      .append("parentname=" + String.valueOf(getParentname()) + ", ")
      .append("parentvalue=" + String.valueOf(getParentvalue()) + ", ")
      .append("parentdesc=" + String.valueOf(getParentdesc()))
      .append("}")
      .toString();
  }
  
  public static TypeStep builder() {
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
  public static ConfigDefinitions justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new ConfigDefinitions(
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
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      type,
      name,
      value,
      desc,
      childname,
      childvalue,
      childdesc,
      parentname,
      parentvalue,
      parentdesc);
  }
  public interface TypeStep {
    NameStep type(String type);
  }
  

  public interface NameStep {
    ValueStep name(String name);
  }
  

  public interface ValueStep {
    DescStep value(String value);
  }
  

  public interface DescStep {
    ChildnameStep desc(String desc);
  }
  

  public interface ChildnameStep {
    ChildvalueStep childname(String childname);
  }
  

  public interface ChildvalueStep {
    ChilddescStep childvalue(String childvalue);
  }
  

  public interface ChilddescStep {
    ParentnameStep childdesc(String childdesc);
  }
  

  public interface ParentnameStep {
    ParentvalueStep parentname(String parentname);
  }
  

  public interface ParentvalueStep {
    ParentdescStep parentvalue(String parentvalue);
  }
  

  public interface ParentdescStep {
    BuildStep parentdesc(String parentdesc);
  }
  

  public interface BuildStep {
    ConfigDefinitions build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements TypeStep, NameStep, ValueStep, DescStep, ChildnameStep, ChildvalueStep, ChilddescStep, ParentnameStep, ParentvalueStep, ParentdescStep, BuildStep {
    private String id;
    private String type;
    private String name;
    private String value;
    private String desc;
    private String childname;
    private String childvalue;
    private String childdesc;
    private String parentname;
    private String parentvalue;
    private String parentdesc;
    @Override
     public ConfigDefinitions build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new ConfigDefinitions(
          id,
          type,
          name,
          value,
          desc,
          childname,
          childvalue,
          childdesc,
          parentname,
          parentvalue,
          parentdesc);
    }
    
    @Override
     public NameStep type(String type) {
        Objects.requireNonNull(type);
        this.type = type;
        return this;
    }
    
    @Override
     public ValueStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public DescStep value(String value) {
        Objects.requireNonNull(value);
        this.value = value;
        return this;
    }
    
    @Override
     public ChildnameStep desc(String desc) {
        Objects.requireNonNull(desc);
        this.desc = desc;
        return this;
    }
    
    @Override
     public ChildvalueStep childname(String childname) {
        Objects.requireNonNull(childname);
        this.childname = childname;
        return this;
    }
    
    @Override
     public ChilddescStep childvalue(String childvalue) {
        Objects.requireNonNull(childvalue);
        this.childvalue = childvalue;
        return this;
    }
    
    @Override
     public ParentnameStep childdesc(String childdesc) {
        Objects.requireNonNull(childdesc);
        this.childdesc = childdesc;
        return this;
    }
    
    @Override
     public ParentvalueStep parentname(String parentname) {
        Objects.requireNonNull(parentname);
        this.parentname = parentname;
        return this;
    }
    
    @Override
     public ParentdescStep parentvalue(String parentvalue) {
        Objects.requireNonNull(parentvalue);
        this.parentvalue = parentvalue;
        return this;
    }
    
    @Override
     public BuildStep parentdesc(String parentdesc) {
        Objects.requireNonNull(parentdesc);
        this.parentdesc = parentdesc;
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
    private CopyOfBuilder(String id, String type, String name, String value, String desc, String childname, String childvalue, String childdesc, String parentname, String parentvalue, String parentdesc) {
      super.id(id);
      super.type(type)
        .name(name)
        .value(value)
        .desc(desc)
        .childname(childname)
        .childvalue(childvalue)
        .childdesc(childdesc)
        .parentname(parentname)
        .parentvalue(parentvalue)
        .parentdesc(parentdesc);
    }
    
    @Override
     public CopyOfBuilder type(String type) {
      return (CopyOfBuilder) super.type(type);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder value(String value) {
      return (CopyOfBuilder) super.value(value);
    }
    
    @Override
     public CopyOfBuilder desc(String desc) {
      return (CopyOfBuilder) super.desc(desc);
    }
    
    @Override
     public CopyOfBuilder childname(String childname) {
      return (CopyOfBuilder) super.childname(childname);
    }
    
    @Override
     public CopyOfBuilder childvalue(String childvalue) {
      return (CopyOfBuilder) super.childvalue(childvalue);
    }
    
    @Override
     public CopyOfBuilder childdesc(String childdesc) {
      return (CopyOfBuilder) super.childdesc(childdesc);
    }
    
    @Override
     public CopyOfBuilder parentname(String parentname) {
      return (CopyOfBuilder) super.parentname(parentname);
    }
    
    @Override
     public CopyOfBuilder parentvalue(String parentvalue) {
      return (CopyOfBuilder) super.parentvalue(parentvalue);
    }
    
    @Override
     public CopyOfBuilder parentdesc(String parentdesc) {
      return (CopyOfBuilder) super.parentdesc(parentdesc);
    }
  }
  
}
