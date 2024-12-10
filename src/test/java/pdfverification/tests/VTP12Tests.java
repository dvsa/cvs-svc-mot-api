package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.AbandonedCertificate;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;

import java.io.FileOutputStream;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

public class VTP12Tests {

    private static final String DYNAMIC_TITLE_SECTION = "Examination (VTP12)";
    private static final String REGULATION_LINE1 = "Regulation 13 of the Motor Vehicles (Tests) ";
    private static final String REGULATION_LINE2 = "Regulations 1981 as amended";
    private static final String VEHICLE_TYPE_TEXT_LINE = "In respect of the public service vehicle with registration number / chassis serial number :";
    private static final String VIN = "P O I U Y T R E W Q 0 1 2 3 0 1 0 9 5 6 7 8 9 1";
    private static final String REASONS_FOR_REFUSAL_LINE1 = "Reason 1 exists VTP12";
    private static final String REASONS_FOR_REFUSAL_LINE2 = "Reason 1 exists VTP12";
    private static final String ROLLING_FOOTER_LEFT = "VTP12 (DVSA0453)";
    private static final String ROLLING_FOOTER_RIGHT = "Date (Feb 2024)";
    private static final String ADDITIONAL_COMMENTS = "additional comments VTP12";
    private static final String ROLLING_HEADER_LEFT = "VTP12";
    private static final String ROLLING_HEADER_RIGHT = "Acceptance of a Public Services Vehicle for Examination";
    private static final String PRINT_NAME = "fake tester";
    private static final String LOCATION = "fake12312312";
    private static final String LOCATION_NUMBER = "fake12312312";
    private static final String DATE_OF_THE_TEST = "01.02.2024";
    private static final String SECTION_TEXT = "having been submitted for an examination under Section 45 of the Road Traffic Act 1998, it is";


    private HtmlGenerator htmlGenerator;
    private PDFGenerationService pdfGenerationService;
    private AbandonedCertificate testCertificate;
    private PDFParser pdfParser;

    private PdfReader pdfReader;
    private byte[] pdfData;

    public VTP12Tests() {
        this.testCertificate = CvsCertificateTestDataProvider.getVTP12();
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();
    }

    @Before
    public void setup() throws Exception {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
        pdfReader = pdfParser.readPdf(pdfData);
    }

    @Test
    public void verifyTitle() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("testVTP112.pdf")) { fos.write(pdfData); }
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(DYNAMIC_TITLE_SECTION));
    }

    @Test
    public void verifyRegulationText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(REGULATION_LINE1));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(REGULATION_LINE2));
    }

    @Test
    public void verifyVehicleTypeText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(VEHICLE_TYPE_TEXT_LINE));
    }

    @Test
    public void verifyVINText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(VIN));
    }

    @Test
    public void verifySectionText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(SECTION_TEXT));
    }

    @Test
    public void verifyReasonsForRefusal() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(REASONS_FOR_REFUSAL_LINE1));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(REASONS_FOR_REFUSAL_LINE2));
    }

    @Test
    public void verifyRollingFooterLeftText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(ROLLING_FOOTER_LEFT));
    }

    @Test
    public void verifyRollingFooterRightText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(ROLLING_FOOTER_RIGHT));
    }

    @Test
    public void verifyAdditionalComments() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(ADDITIONAL_COMMENTS));
    }

    @Test
    public void verifyRollingHeaderLeftText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(ROLLING_HEADER_LEFT));
    }

    @Test
    public void verifyRollingHeaderRightText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(ROLLING_HEADER_RIGHT));
    }

    @Test
    public void verifyPrintName() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(PRINT_NAME));
    }

    @Test
    public void verifyLocation() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(LOCATION));
    }

    @Test
    public void verifyLocationNumber() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(LOCATION_NUMBER));
    }

    @Test
    public void verifyDate() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(DATE_OF_THE_TEST));
    }
}
