package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.VTP30W;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static htmlverification.framework.component.DefectSummaryComponent.*;
import static org.junit.Assert.assertTrue;

public class VTP30WTests {

    private static final int FIRST_WELSH_PAGE_NUMBER = 3;
    private static final int FIRST_ENGLISH_PAGE_NUMBER = 1;
    private static final String ISSUER_SIGNATURE = "Issuer signature";
    private static final String ISSUED_BY_DVSA = "Issued by DVSA";
    private static final String ISSUER_SIGNATURE_WELSH = "Llofnod y cyhoeddwr";
    private static final String ISSUED_BY_DVSA_WELSH = "Cyhoeddwyd gan ASGC";

    private PDFGenerationService pdfGenerationService;
    private HtmlGenerator htmlGenerator;
    private PDFParser pdfParser;
    private VTP30W testCertificate;
    private PdfReader pdfReader;
    private byte[] pdfData;

    public VTP30WTests() {
        this.testCertificate = CvsCertificateTestDataProvider.getVtp30w();

        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();
    }

    @Before
    public void setup() throws IOException {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
        pdfReader = pdfParser.readPdf(pdfData);
    }

   @Test
    public void verifyWelshTitleIsOnFirstPage() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains("Gwrthodiad tystysgrif prawf MOT"));
    }

    @Test
    public void verifyEnglishTitleIsOnThirdPage() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_ENGLISH_PAGE_NUMBER).contains("Refusal of MOT test certificate"));
    }

    @Test
    public void verifyWelshRfrs() throws Exception {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(MINOR_DEFECTS_HEADER_TEXT_WELSH));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(ADVISORIES_HEADER_TEXT_WELSH));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(MAJOR_DEFECTS_HEADER_TEXT_WELSH));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(DANGEROUS_DEFECTS_HEADER_PARTIAL_TEXT_WELSH));
    }
}
