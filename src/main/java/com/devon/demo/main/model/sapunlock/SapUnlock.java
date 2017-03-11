
package com.devon.demo.main.model.sapunlock;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SapUnlock implements Serializable {

  @SerializedName("d")
  @Expose
  private D d;
  private final static long serialVersionUID = -3599318084513357361L;

  public D getD() {
    return d;
  }

  public void setD(D d) {
    this.d = d;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(d).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof SapUnlock) == false) {
      return false;
    }
    SapUnlock rhs = ((SapUnlock) other);
    return new EqualsBuilder().append(d, rhs.d).isEquals();
  }

}
