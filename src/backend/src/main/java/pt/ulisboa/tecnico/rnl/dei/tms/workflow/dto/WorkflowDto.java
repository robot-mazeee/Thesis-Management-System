package pt.ulisboa.tecnico.rnl.dei.tms.workflow.dto;

import java.util.List;

import pt.ulisboa.tecnico.rnl.dei.tms.person.domain.Person;
import pt.ulisboa.tecnico.rnl.dei.tms.workflow.domain.Workflow;

public record WorkflowDto(long id, String status, List<Person> professors, Person student, Person juriPresident) {
    public WorkflowDto(Workflow workflow) {
        this(workflow.getId(), workflow.getStatus().toString(), workflow.getProfessors(), workflow.getStudent(), workflow.getJuriPresident());  
    }
}