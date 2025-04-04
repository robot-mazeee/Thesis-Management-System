package pt.ulisboa.tecnico.rnl.dei.dms.workflow.dto;

import pt.ulisboa.tecnico.rnl.dei.dms.person.domain.Person;
import pt.ulisboa.tecnico.rnl.dei.dms.workflow.domain.Workflow;

import java.util.List;

public record WorkflowDto(long id, String workflowStatus, List<Person> teachers, Person student, Person juriPresident) {

    public WorkflowDto(Workflow workflow) {
        this(workflow.getId(), workflow.getWorkflowStatus().toString(), workflow.getTeachers(), workflow.getStudent(), workflow.getJuriPresident());  
    }
}