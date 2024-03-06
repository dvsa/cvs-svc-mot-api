package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MsvaFailCertificateData {
    @JsonProperty("serialNumber")
    private String serialNo;
    @JsonProperty("vehicleZNumber")
    private String vehicleZNumber;
    @JsonProperty("Type")
    private String Type;
    @JsonProperty("vin")
    private String vin;
    @JsonProperty("make")
    private String make;
    @JsonProperty("model")
    private String model;
    @JsonProperty("date")
    private String date;
    @JsonProperty("retestDate")
    private String retestDate;
    @JsonProperty("station")
    private String station;
    @JsonProperty("testerName")
    private String testerName;
    @JsonProperty("additionalDefects")
    private AdditionalDefect[] additionalDefects;
    @JsonProperty("requiredStandards")
    private RequiredStandard[] requiredStandards;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getVehicleZNumber() {
        return vehicleZNumber;
    }

    public void setVehicleZNumber(String vehicleZNumber) {
        this.vehicleZNumber = vehicleZNumber;
    }

    public String getType() {
        return Type;
    }

    public void setType(String testType) {
        this.Type = testType;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRetestDate() {
        return retestDate;
    }

    public void setRetestDate(String retestDate) {
        this.retestDate = retestDate;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTesterName() {
        return testerName;
    }

    public void setTesterName(String testerName) {
        this.testerName = testerName;
    }

    public AdditionalDefect[] getAdditionalDefects() {
        return additionalDefects;
    }

    public void setAdditionalDefects(AdditionalDefect[] additionalDefects) {
        this.additionalDefects = additionalDefects;
    }

    public RequiredStandard[] getRequiredStandards() {
        return requiredStandards;
    }

    public void setRequiredStandards(RequiredStandard[] requiredStandards) {
        this.requiredStandards = requiredStandards;
    }
}
