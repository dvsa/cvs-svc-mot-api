package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.CvsMotCertificate;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class HgvPRSBilingualTests {
    private static final String CERT_NAME = "MOT test certificate (HGV)";
    private static final String CERT_NAME_FAIL = "Refusal of MOT test certificate";
    private static final String CERT_NAME_WELSH = "Tystysgrif prawf MOT (HGV)";
    private static final String CERT_NAME_FAIL_WELSH = "Gwrthod tystysgrif prawf MOT";

    private HtmlGenerator htmlGenerator;
    private PDFGenerationService pdfGenerationService;
    private CvsMotCertificate testCertificate;
    private PDFParser pdfParser;
    private PdfReader pdfReader;
    private byte[] pdfData;

    public HgvPRSBilingualTests() {
        this.testCertificate = CvsCertificateTestDataProvider.getCvsHgvPRSBilingual();
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
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(CERT_NAME));
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(CERT_NAME_FAIL));
        assertTrue(pdfParser.getRawText(pdfReader, 3).contains(CERT_NAME_WELSH));
        assertTrue(pdfParser.getRawText(pdfReader, 4).contains(CERT_NAME_FAIL_WELSH));
    }

    @Test
    public void verifySinglePageWithInvalidXMLCharacter() throws Exception {
        this.testCertificate = CvsCertificateTestDataProvider.getCvsHgvPRSBilingualHavingInvalidXMLCharacter();
        pdfParser.getRawText(pdfReader, 1);
    }
}
