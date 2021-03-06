
package com.devon.demo.main.model.sapdetailerror;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.lang.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SapDetailError implements Serializable {

  @SerializedName("error")
  @Expose
  private Error error;
  private final static long serialVersionUID = 3523936707585624053L;

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(error).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof SapDetailError) == false) {
      return false;
    }
    SapDetailError rhs = ((SapDetailError) other);
    return new EqualsBuilder().append(error, rhs.error).isEquals();
  }

}
