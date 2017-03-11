
package com.devon.demo.main.model.sapdetailerror;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Application implements Serializable
{

    @SerializedName("component_id")
    @Expose
    private String componentId;
    @SerializedName("service_namespace")
    @Expose
    private String serviceNamespace;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("service_version")
    @Expose
    private String serviceVersion;
    private final static long serialVersionUID = 4335836201781279114L;

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getServiceNamespace() {
        return serviceNamespace;
    }

    public void setServiceNamespace(String serviceNamespace) {
        this.serviceNamespace = serviceNamespace;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(componentId).append(serviceNamespace).append(serviceId).append(serviceVersion).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Application) == false) {
            return false;
        }
        Application rhs = ((Application) other);
        return new EqualsBuilder().append(componentId, rhs.componentId).append(serviceNamespace, rhs.serviceNamespace).append(serviceId, rhs.serviceId).append(serviceVersion, rhs.serviceVersion).isEquals();
    }

}
