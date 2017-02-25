package com.devon.demo.main.model.sapdetailerror;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Innererror {

    private Application application;
    private String transactionid;
    private String timestamp;
    @JsonProperty("Error_Resolution")
    private ErrorResolution errorResolution;
    private List<Errordetails> errordetails;

    public void setApplication(Application application) {
        this.application = application;
    }

    public Application getApplication() {
        return application;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setErrorResolution(ErrorResolution errorResolution) {
        this.errorResolution = errorResolution;
    }

    public ErrorResolution getErrorResolution() {
        return errorResolution;
    }

    public void setErrordetails(List<Errordetails> errordetails) {
        this.errordetails = errordetails;
    }

    public List<Errordetails> getErrordetails() {
        return errordetails;
    }

}