package com.devon.demo.main.model.sapdetailerror;

public class Errordetails {

    private String code;
    private String message;
    private String propertyref;
    private String severity;
    private String target;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setPropertyref(String propertyref) {
        this.propertyref = propertyref;
    }

    public String getPropertyref() {
        return propertyref;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSeverity() {
        return severity;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

}