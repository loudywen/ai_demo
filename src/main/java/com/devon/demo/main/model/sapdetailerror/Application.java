package com.devon.demo.main.model.sapdetailerror;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Application {

    @JsonProperty("component_id")
    private String componentId;
    @JsonProperty("service_namespace")
    private String serviceNamespace;
    @JsonProperty("service_id")
    private String serviceId;
    @JsonProperty("service_version")
    private String serviceVersion;
    public void setComponentId(String componentId) {
         this.componentId = componentId;
     }
     public String getComponentId() {
         return componentId;
     }

    public void setServiceNamespace(String serviceNamespace) {
         this.serviceNamespace = serviceNamespace;
     }
     public String getServiceNamespace() {
         return serviceNamespace;
     }

    public void setServiceId(String serviceId) {
         this.serviceId = serviceId;
     }
     public String getServiceId() {
         return serviceId;
     }

    public void setServiceVersion(String serviceVersion) {
         this.serviceVersion = serviceVersion;
     }
     public String getServiceVersion() {
         return serviceVersion;
     }

}