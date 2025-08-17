package pt.ulisboa.tecnico.rnl.dei.dms.file;
import pt.ulisboa.tecnico.rnl.dei.dms.workflow.domain.Workflow;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import pt.ulisboa.tecnico.rnl.dei.dms.person.domain.Person;

import java.io.ByteArrayOutputStream;
import java.util.List;
    
@Service
public class PdfService {

    public byte[] generateWorkflowPdf(Workflow workflow) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);

            document.open();

            document.add(new Paragraph("Workflow Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            document.add(new Paragraph(" ")); // spacer

            document.add(new Paragraph("ID: " + workflow.getId()));
            document.add(new Paragraph(
                "Student: " + workflow.getStudent().getName() + "(" + workflow.getStudent().getIstId() + ")"
            ));
            document.add(new Paragraph("Workflow Status: " + workflow.getStatus()));

            List<Person> juri = workflow.getProfessors();
            int numProfs = juri.size();
            String[] professors = new String[numProfs];

            for (int i = 0; i < numProfs; i++) {
                Person prof = juri.get(i);
                professors[i] = prof.getName() + "(" + prof.getIstId() + ")";
                if (workflow.getJuriPresident().getId() == prof.getId()) {
                    professors[i] += " - juri president";
                    String president = professors[i];
                    professors[i] = professors[0];
                    professors[0] = president;
                }
            }
            document.add(new Paragraph("Juri: " + professors));

            document.close();

            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}