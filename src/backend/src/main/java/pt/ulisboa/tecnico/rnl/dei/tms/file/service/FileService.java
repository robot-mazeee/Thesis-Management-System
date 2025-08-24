package pt.ulisboa.tecnico.rnl.dei.tms.file.service;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import pt.ulisboa.tecnico.rnl.dei.tms.person.domain.Person;
import pt.ulisboa.tecnico.rnl.dei.tms.workflow.domain.Workflow;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class FileService {

    public byte[] generateWorkflowPdf(Workflow workflow) {
        if (workflow == null) {
            throw new IllegalArgumentException("Workflow cannot be null");
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);

            document.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(18);
            
            Paragraph p = new Paragraph("List of Users", font);
            p.setAlignment(Paragraph.ALIGN_CENTER);
            
            document.add(p);
            
            document.close();

    //         document.open();
    //         // Title
    //         document.add(new Paragraph("Workflow Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
    //         document.add(new Paragraph(" ")); // spacer

    //         // Basic workflow info
    //         document.add(new Paragraph("ID: " + workflow.getId()));
    //         if (workflow.getStudent() != null) {
    //             document.add(new Paragraph(
    //                     "Student: " + workflow.getStudent().getName() + " (" + workflow.getStudent().getIstId() + ")"
    //             ));
    //         } else {
    //             document.add(new Paragraph("Student: N/A"));
    //         }

    //         document.add(new Paragraph("Workflow Status: " + workflow.getStatus()));

    //         // Professors / Juri
    //         if (workflow.getProfessors() != null && !workflow.getProfessors().isEmpty()) {
    //             StringBuilder profList = new StringBuilder();
    //             for (Person prof : workflow.getProfessors()) {
    //                 String name = prof.getName() + " (" + prof.getIstId() + ")";
    //                 if (workflow.getJuriPresident() != null && prof.getId().equals(workflow.getJuriPresident().getId())) {
    //                     name += " - Juri President";
    //                 }
    //                 if (profList.length() > 0) profList.append(", ");
    //                 profList.append(name);
    //             }
    //             document.add(new Paragraph("Juri: " + profList.toString()));
    //         } else {
    //             document.add(new Paragraph("Juri: None"));
    //         }

    //         document.close();

            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}
