package pt.ulisboa.tecnico.rnl.dei.dms.workflow.dto;

import pt.ulisboa.tecnico.rnl.dei.dms.person.domain.Person;
import pt.ulisboa.tecnico.rnl.dei.dms.workflow.domain.Workflow;

import java.util.List;

public record WorkflowDto(long id, String status, List<Person> professors, Person student, Person juriPresident) {
    public WorkflowDto(Workflow workflow) {
        this(workflow.getId(), workflow.getStatus().toString(), workflow.getProfessors(), workflow.getStudent(), workflow.getJuriPresident());  
    }
}