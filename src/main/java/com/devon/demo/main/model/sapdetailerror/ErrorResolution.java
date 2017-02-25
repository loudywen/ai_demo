package com.devon.demo.main.model.sapdetailerror;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResolution {

    @JsonProperty("SAP_Transaction")
    private String sapTransaction;
    @JsonProperty("SAP_Note")
    private String sapNote;
    public void setSapTransaction(String sapTransaction) {
         this.sapTransaction = sapTransaction;
     }
     public String getSapTransaction() {
         return sapTransaction;
     }

    public void setSapNote(String sapNote) {
         this.sapNote = sapNote;
     }
     public String getSapNote() {
         return sapNote;
     }

}