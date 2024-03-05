package htmlverification.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.MsvaPageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.MSVA30;
import uk.gov.dvsa.model.cvs.certificateData.AdditionalDefect;
import uk.gov.dvsa.model.cvs.certificateData.RequiredStandard;
import uk.gov.dvsa.service.HtmlGenerator;

import static org.junit.Assert.assertEquals;

public class MSVA30Test {
    protected HtmlGenerator htmlGenerator;
    protected MSVA30 testCertificate;
    protected MsvaPageObject msvaPageObject;

    public MSVA30Test() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws JsonProcessingException {
        testCertificate = CvsCertificateTestDataProvider.getMSVA30();
        String certHtmlIva30 = htmlGenerator.generate(testCertificate).get(0);
        msvaPageObject = new MsvaPageObject(certHtmlIva30);
    }

    @Test
    public void verifySerialNumber() {
        String serialNumber = msvaPageObject.getSerialNumber();
        assertEquals("Serial Number: ".concat(testCertificate.getMsvaData().getSerialNo()), serialNumber);
    }

    @Test
    public void verifyVehicleZNumber() {
        String vehicleZNumber = msvaPageObject.getVehicleZNumber();
        assertEquals("Vehicle 'Z' No.: ".concat(testCertificate.getMsvaData().getVehicleZNo()), vehicleZNumber);
    }

    @Test
    public void verifyTestType() {
        String htmlTestType = msvaPageObject.getTestType();
        String testType = testCertificate.getMsvaData().getTestType();
        assertEquals("Type: ".concat(testType), htmlTestType);
    }

    @Test
    public void verifyMakeModel() {
        String makeModel = msvaPageObject.getMakeModel();
        String htmlMake = testCertificate.getMsvaData().getMake();
        String htmlModel = testCertificate.getMsvaData().getModel();
        assertEquals("Make/Model: ".concat(htmlMake + "/" + htmlModel), makeModel);
    }

    @Test
    public void verifyVin() {
        String vin = msvaPageObject.getVin();
        assertEquals("VIN/Chassis No: ".concat(testCertificate.getMsvaData().getVin()), vin);
    }

    @Test
    public void verifyDate() {
        String date = msvaPageObject.getDate();
        assertEquals("Date: ".concat(testCertificate.getMsvaData().getDate()), date);
    }

    @Test
    public void verifyRetestDate() {
        String retestDate = msvaPageObject.getRetestDate();
        assertEquals("Retest required by: ".concat(testCertificate.getMsvaData().getRetestDate()), retestDate);
    }

    @Test
    public void verifyTesterName() {
        String testerName = msvaPageObject.getTesterName();
        assertEquals("Name: ".concat(testCertificate.getMsvaData().getTesterName()), testerName);
    }

    @Test
    public void verifyStation() {
        String station = msvaPageObject.getStation();
        assertEquals("Station: ".concat(testCertificate.getMsvaData().getStation()), station);
    }

    @Test
    public void verifyRequiredStandards() {
        String requiredStandards = msvaPageObject.getRequiredStandards();
        RequiredStandard[] requiredStandardsArray = testCertificate.getMsvaData().getRequiredStandards();

        String sectionNo = requiredStandardsArray[0].getSectionNumber();
        String description = requiredStandardsArray[0].getSectionDescription();
        int rsNo = requiredStandardsArray[0].getRsNumber();
        String rs = requiredStandardsArray[0].getRequiredStandard();
        String addNotes = requiredStandardsArray[0].getAdditionalNotes();

        assertEquals(sectionNo + " - " + description + " RS" + rsNo + ": " + rs + " Additional Information: " + addNotes, requiredStandards);
    }

    @Test
    public void verifyAdditionalDefectsWhenNotApplicable() {
        String additionalDefects = msvaPageObject.getAdditionalDefects();
        AdditionalDefect[] additionalDefectsArray = testCertificate.getMsvaData().getAdditionalDefects();

        String defectName = additionalDefectsArray[0].getDefectName();
        String defectNotes = additionalDefectsArray[0].getDefectNotes();

        assertEquals(defectName + " " + defectNotes, additionalDefects);
    }
}
