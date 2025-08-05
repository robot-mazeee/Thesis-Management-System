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
    private WorkflowStatus status;

    @OneToMany
    @JoinColumn(name = "workflow_professors")
    private List<Person> professors;

    private Long studentId;

    @ManyToOne
    @JoinColumn(name = "juri_president")
    private Person juriPresident;

    public Workflow() {}

    public Workflow(WorkflowStatus status, List<Person> professors, Long studentId) {
        this.status = status;
        this.professors = professors;
        this.studentId = studentId;
    }

    public Workflow(WorkflowDto workflowDto) {
        this.status = WorkflowStatus.valueOf(workflowDto.status().toUpperCase());
        this.professors = workflowDto.professors();
        this.studentId = workflowDto.studentId();
    }

    public void setStatus(WorkflowStatus newStatus) {
        this.status = newStatus;
    }

    public void setProfessors(List<Person> professors) {
        this.professors = professors;
    }

    public WorkflowStatus getStatus() {
        return this.status;
    } 
}
