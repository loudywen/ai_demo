
package com.devon.demo.main.model.sapdetailerror;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Errordetail implements Serializable
{

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("propertyref")
    @Expose
    private String propertyref;
    @SerializedName("severity")
    @Expose
    private String severity;
    @SerializedName("target")
    @Expose
    private String target;
    private final static long serialVersionUID = -6920546483145068815L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPropertyref() {
        return propertyref;
    }

    public void setPropertyref(String propertyref) {
        this.propertyref = propertyref;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(code).append(message).append(propertyref).append(severity).append(target).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Errordetail) == false) {
            return false;
        }
        Errordetail rhs = ((Errordetail) other);
        return new EqualsBuilder().append(code, rhs.code).append(message, rhs.message).append(propertyref, rhs.propertyref).append(severity, rhs.severity).append(target, rhs.target).isEquals();
    }

}
