
package com.devon.demo.main.model.sapdetailerror;

public class Error {

    private String code;
    private Message message;
    private Innererror innererror;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setInnererror(Innererror innererror) {
        this.innererror = innererror;
    }

    public Innererror getInnererror() {
        return innererror;
    }

}