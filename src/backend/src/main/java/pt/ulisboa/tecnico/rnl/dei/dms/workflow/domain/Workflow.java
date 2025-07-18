package pt.ulisboa.tecnico.rnl.dei.dms.workflow.domain;

import pt.ulisboa.tecnico.rnl.dei.dms.workflow.dto.WorkflowDto;
import pt.ulisboa.tecnico.rnl.dei.dms.person.domain.Person;

import java.util.List;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "workflows")
public class Workflow {
    @Id
	@GeneratedValue
	private Long id;
 
    @Column(name = "workflow_status")
	@Enumerated(EnumType.STRING)
    private WorkflowStatus workflowStatus;

    @OneToMany
    @JoinColumn(name = "workflow_teachers")
    private List<Person> teachers;

    private Long studentId;

    @ManyToOne
    @JoinColumn(name = "juri_president")
    private Person juriPresident;

    public Workflow() {}

    public Workflow(WorkflowStatus status, List<Person> teachers, Long studentId) {
        this.workflowStatus = status;
        this.teachers = teachers;
        this.studentId = studentId;
    }

    public Workflow(WorkflowDto workflowDto) {
        this.workflowStatus = WorkflowStatus.valueOf(workflowDto.workflowStatus().toUpperCase());
        this.teachers = workflowDto.teachers();
        this.studentId = workflowDto.studentId();
    }

    public void setWorkflowStatus(WorkflowStatus newStatus) {
        this.workflowStatus = newStatus;
    }

    public void setTeachers(List<Person> teachers) {
        this.teachers = teachers;
    }

    public WorkflowStatus getWorkflowStatus() {
        return this.workflowStatus;
    } 
}
