package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class AbandonedData {
    @JsonProperty("ChassisNumber")
    private String chassisNumber;

    @JsonProperty("RegistrationNumber")
    private String registrationNumber;

    @JsonProperty("ReasonsForRefusal")
    private String[] reasonsForRefusal;

    @JsonProperty("TestStationName")
    private String testStationName;

    //print name
    @JsonProperty("IssuersName")
    private String issuersName;

    @JsonProperty("AdditionalComments")
    private String additionalComments;

    @JsonProperty("TestStationPNumber")
    private String testStationPNumber;

    @JsonProperty("DateOfTheTest")
    private String dateOfTheTest;

    public AbandonedData() {
    }

    public AbandonedData(String chassisNumber,
                         String registrationNumber,
                         String[] reasonsForRefusal,
                         String dateOfTheTest,
                         String testStationName,
                         String testStationPNumber,
                         String issuersName,
                         String additionalComments) {
        this.chassisNumber = chassisNumber;
        this.registrationNumber = registrationNumber;
        this.reasonsForRefusal = reasonsForRefusal;
        this.dateOfTheTest = dateOfTheTest;
        this.testStationName = testStationName;
        this.testStationPNumber = testStationPNumber;
        this.issuersName = issuersName;
        this.additionalComments = additionalComments;
    }

    public String[] getChassisNumber() {
        return chassisNumber.split("");
    }

    public String[] getReasonsForRefusal() {
        return reasonsForRefusal;
    }

    public String getDateOfTheTest() {
        return dateOfTheTest;
    }

    public String getDateOfTheTestYearMonth() {
        String[] dateString = dateOfTheTest.split("\\.");
        String month = Month.of(Integer.parseInt(dateString[1])).
                getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        return month +" "+  Integer.parseInt(dateString[2]);
    }

    public String getTestStationName() {
        return testStationName;
    }

    public String getTestStationPNumber() {
        return testStationPNumber;
    }

    public String getIssuersName() {
        return issuersName;
    }

    public String getAdditionalComments() {
        return additionalComments;
    }

}
