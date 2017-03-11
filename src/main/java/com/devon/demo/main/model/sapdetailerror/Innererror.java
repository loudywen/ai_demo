
package com.devon.demo.main.model.sapdetailerror;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Innererror implements Serializable
{

    @SerializedName("application")
    @Expose
    private Application application;
    @SerializedName("transactionid")
    @Expose
    private String transactionid;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("Error_Resolution")
    @Expose
    private ErrorResolution errorResolution;
    @SerializedName("errordetails")
    @Expose
    private List<Errordetail> errordetails = null;
    private final static long serialVersionUID = 7018443577086141121L;

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ErrorResolution getErrorResolution() {
        return errorResolution;
    }

    public void setErrorResolution(ErrorResolution errorResolution) {
        this.errorResolution = errorResolution;
    }

    public List<Errordetail> getErrordetails() {
        return errordetails;
    }

    public void setErrordetails(List<Errordetail> errordetails) {
        this.errordetails = errordetails;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(application).append(transactionid).append(timestamp).append(errorResolution).append(errordetails).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Innererror) == false) {
            return false;
        }
        Innererror rhs = ((Innererror) other);
        return new EqualsBuilder().append(application, rhs.application).append(transactionid, rhs.transactionid).append(timestamp, rhs.timestamp).append(errorResolution, rhs.errorResolution).append(errordetails, rhs.errordetails).isEquals();
    }

}
