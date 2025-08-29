package pt.ulisboa.tecnico.rnl.dei.tms.workflow.domain;

import java.util.List;

import jakarta.persistence.*;

import lombok.Data;
import pt.ulisboa.tecnico.rnl.dei.tms.person.domain.Person;
import pt.ulisboa.tecnico.rnl.dei.tms.workflow.dto.WorkflowDto;

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

    @ManyToMany
    @JoinTable(
        name = "workflow_professors",
        joinColumns = @JoinColumn(name = "workflow_id"),
        inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> professors;


    @ManyToOne
    @JoinColumn(name = "student")
    private Person student;

    @ManyToOne
    @JoinColumn(name = "juri_president")
    private Person juriPresident;
    
    @Column(name = "defense_id", nullable = true)
    private Long defenseId;

    public Workflow() {}

    public Workflow(WorkflowStatus status, List<Person> professors, Person student) {
        this.status = status;
        this.professors = professors;
        this.student = student;
    }

    public Workflow(WorkflowDto workflowDto) {
        this.status = WorkflowStatus.valueOf(workflowDto.status().toUpperCase());
        this.professors = workflowDto.professors();
        this.student = workflowDto.student();
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
