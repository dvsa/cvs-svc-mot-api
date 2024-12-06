package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbandonedData {
    @JsonProperty("Vin")
    private String vin;

    @JsonProperty("ReasonsForAbandonment")
    private String[] reasonsForAbandonment;

    // might generate time in different part of the code
    @JsonProperty("DatePopulated")
    private String datePopulated;

    @JsonProperty("Location")
    private String location;

    @JsonProperty("TestersName")
    private String testersName;

    @JsonProperty("TestNumber")
    private String testNumber;

    @JsonProperty("Signature")
    private String signature;

    @JsonProperty("documentName")
    private String documentName;

    @JsonProperty("AdditionalComments")
    private String additionalComments;

    public AbandonedData() {
    }

    public AbandonedData(String vin,
                         String[] reasonsForAbandonment,
                         String datePopulated,
                         String location,
                         String testersName,
                         String testNumber,
                         String documentName,
                         String additionalComments) {
        this.vin = vin;
        this.reasonsForAbandonment = reasonsForAbandonment;
        this.datePopulated = datePopulated;
        this.location = location;
        this.testersName = testersName;
        this.testNumber = testNumber;
        this.documentName = documentName;
        this.additionalComments = additionalComments;
    }

    public AbandonedData(String vin,
                         String[] reasonsForAbandonment,
                         String datePopulated,
                         String location,
                         String testersName,
                         String testNumber,
                         String signature,
                         String documentName,
                         String additionalComments) {
        this.vin = vin;
        this.reasonsForAbandonment = reasonsForAbandonment;
        this.datePopulated = datePopulated;
        this.location = location;
        this.testersName = testersName;
        this.signature = signature;
        this.testNumber = testNumber;
        this.documentName = documentName;
        this.additionalComments = additionalComments;
    }

    public String[] getVin() {
        return vin.split("");
    }

    public String[] getReasonsForAbandonment() {
        return reasonsForAbandonment;
    }

    public String getDatePopulated() {
        return datePopulated;
    }

    public String getLocation() {
        return location;
    }

    public String getTestersName() {
        return testersName;
    }

    public String getTestNumber() {
        return testNumber;
    }

    public String getSignature() {
        return signature;
    }

    public String getAdditionalComments() {
        return additionalComments;
    }

}
