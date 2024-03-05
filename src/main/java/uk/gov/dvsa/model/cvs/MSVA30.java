package uk.gov.dvsa.model.cvs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.MsvaFailCertificateData;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MSVA30 extends Document {
    private String id;
    private MsvaFailCertificateData msvaData;
    public MSVA30 setId(String id) {
        this.id = id;
        return this;
    }
    public String getId(){
        return this.id;
    }
    public void setMsvaData(MsvaFailCertificateData msvaData){
        this.msvaData = msvaData;

    }
    public MsvaFailCertificateData getMsvaData() {
        return this.msvaData;
    }
}

