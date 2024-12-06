package uk.gov.dvsa.model.cvs;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.AbandonedData;

public class  AbandonedCertificate extends Document {
    @JsonProperty("DATA")
    protected AbandonedData data;

    public String getDocumentType() {
        return this.getDocumentName().split("/")[1];
    }

    protected String regulationText;
    public String getRegulationText() { return this.regulationText; }

    protected String vehicleTypeText;
    public String getVehicleTypeText() { return this.vehicleTypeText; }

    protected String titleTextIncludingRollingHeaders;
    public String getTitleTextIncludingRollingHeaders() {
        return this.titleTextIncludingRollingHeaders;
    }


    public AbandonedData getData() {
        return data;
    }

    public Document setData(AbandonedData data) {
        this.data = data;
        return this;
    }
}
