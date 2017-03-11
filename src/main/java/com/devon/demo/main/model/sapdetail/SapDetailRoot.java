
package com.devon.demo.main.model.sapdetail;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SapDetailRoot implements Serializable
{

    @SerializedName("d")
    @Expose
    private D d;
    private final static long serialVersionUID = -4365372316183452987L;

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
        if ((other instanceof SapDetailRoot) == false) {
            return false;
        }
        SapDetailRoot rhs = ((SapDetailRoot) other);
        return new EqualsBuilder().append(d, rhs.d).isEquals();
    }

}
