package uk.gov.dvsa.model.cvs;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.AbandonedData;
import uk.gov.dvsa.model.cvs.certificateData.Signature;

public abstract class AbandonedCertificate extends Document {
    @JsonProperty("ABANDONED_DATA")
    protected AbandonedData data;

    @JsonProperty("Signature")
    private Signature signature;

    public String getDocumentType() {
        return this.getDocumentName().split("/")[1];
    }

    protected String regulationText;

    protected String vehicleTypeText;

    protected String titleTextIncludingRollingHeaders;
    public String getTitleTextIncludingRollingHeaders() {
        return this.titleTextIncludingRollingHeaders;
    }

    protected String sectionTextRef;

    protected String testNumber;

    public AbandonedData getData() {
        return data;
    }

    public Signature getSignature() {
        return signature;
    }

    public String getRegulationText() { return this.regulationText; }

    public String getVehicleTypeText() { return this.vehicleTypeText; }

    public String getSectionTextRef() { return this.sectionTextRef; }

    public String getTestNumber() { return testNumber; }

    public Document setData(AbandonedData data) {
        this.data = data;
        return this;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }
}
