
package com.devon.demo.main.model.sapdetailerror;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Message implements Serializable {

  @SerializedName("lang")
  @Expose
  private String lang;
  @SerializedName("value")
  @Expose
  private String value;
  private final static long serialVersionUID = 8365207965812671847L;

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(lang).append(value).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof Message) == false) {
      return false;
    }
    Message rhs = ((Message) other);
    return new EqualsBuilder().append(lang, rhs.lang).append(value, rhs.value).isEquals();
  }

}
