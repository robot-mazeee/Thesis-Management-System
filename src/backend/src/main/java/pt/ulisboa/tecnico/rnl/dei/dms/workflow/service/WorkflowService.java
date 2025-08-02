package pt.ulisboa.tecnico.rnl.dei.dms.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.ulisboa.tecnico.rnl.dei.dms.workflow.domain.Workflow;
import pt.ulisboa.tecnico.rnl.dei.dms.workflow.domain.WorkflowStatus;
import pt.ulisboa.tecnico.rnl.dei.dms.workflow.repository.WorkflowRepository;
import pt.ulisboa.tecnico.rnl.dei.dms.workflow.dto.WorkflowDto;
import pt.ulisboa.tecnico.rnl.dei.dms.exceptions.DEIException;
import pt.ulisboa.tecnico.rnl.dei.dms.exceptions.ErrorMessage;
import pt.ulisboa.tecnico.rnl.dei.dms.person.domain.Person;
import pt.ulisboa.tecnico.rnl.dei.dms.person.dto.PersonDto;

@Service
@Transactional
public class WorkflowService {

	@Autowired 
	private WorkflowRepository workflowRepository;

    private Workflow fetchWorkflowOrThrow(long id) {
		return workflowRepository.findById(id)
				.orElseThrow(() -> new DEIException(ErrorMessage.NO_SUCH_WORKFLOW, Long.toString(id)));
	}

    private Workflow fetchWorkflowByStudent(long id) {
		return workflowRepository.findByStudentId(id).orElse(null);
	}
    
	@Transactional
    public WorkflowDto createWorkflow(WorkflowDto workflowDto) {
        Workflow workflow = new Workflow();
        workflow.setWorkflowStatus(WorkflowStatus.valueOf(workflowDto.workflowStatus()));

        List<Person> professors = workflowDto.professors().stream().toList();
        workflow.setProfessors(professors);
        Long studentId = workflowDto.studentId();
        workflow.setStudentId(studentId);
        workflow.setJuriPresident(null);

        workflow = workflowRepository.save(workflow);

        return new WorkflowDto(workflow.getId(), workflow.getWorkflowStatus().toString(), workflow.getProfessors(), workflow.getStudentId(), workflow.getJuriPresident());
    }
	
	@Transactional
	public List<WorkflowDto> getWorkflows() {
        return workflowRepository.findAll().stream()
            .map(WorkflowDto::new)
            .toList();
	}

    @Transactional
    public WorkflowDto getWorkflow(long id) {
        return new WorkflowDto(fetchWorkflowOrThrow(id));
    }

    @Transactional
    public WorkflowDto getWorkflowByStudent(long studentId) {
        var workflow = fetchWorkflowByStudent(studentId);
        return (workflow != null) ? new WorkflowDto(workflow) : null;
    }

    @Transactional 
    public WorkflowDto updateWorkflow(long id, WorkflowDto workflowDto) {
        fetchWorkflowOrThrow(id);
        Workflow workflow = new Workflow(workflowDto);
        workflow.setId(id); 
        return new WorkflowDto(workflowRepository.save(workflow));
    }

    public List<WorkflowDto> getWorkflowsByStatus(WorkflowStatus status) {
        return workflowRepository.findByWorkflowStatus(status).stream()
				.map(WorkflowDto::new)
				.toList();
    }

    @Transactional
    public List<WorkflowDto> getJuriProposals() {
        return getWorkflowsByStatus(WorkflowStatus.JURI_PROPOSAL_SUBMITTED);
    }

    @Transactional
    public List<WorkflowDto> getApprovedJuriProposals() {
        return getWorkflowsByStatus(WorkflowStatus.APPROVED_BY_SC);
    }

    @Transactional
    public List<PersonDto> getJuri(long id) {
        Workflow workflow = fetchWorkflowOrThrow(id);
        return workflow.getProfessors().stream()
                .map(PersonDto::new)
                .toList();
    }

    @Transactional
    public PersonDto getJuriPresident(long id) {
        Workflow workflow = fetchWorkflowOrThrow(id);
        Person juriPresident = workflow.getJuriPresident();
        if (juriPresident == null) {
            throw new DEIException(ErrorMessage.NO_SUCH_PERSON, "Juri president not assigned for workflow ID: " + id);
        }
        return new PersonDto(juriPresident);
    }

    @Transactional
    public WorkflowDto assignJuriPresident(long id, WorkflowDto workflowDto) {
        Workflow workflow = fetchWorkflowOrThrow(id);
        Person juriPresident = workflowDto.juriPresident();
        workflow.setJuriPresident(juriPresident);
        workflow.setWorkflowStatus(WorkflowStatus.JURI_PRESIDENT_ATTRIBUTED);
        return new WorkflowDto(workflowRepository.save(workflow));
    }

    @Transactional
    public List<WorkflowDto> getJuriPresidentSelectedWorkflows() {
        return getWorkflowsByStatus(WorkflowStatus.JURI_PRESIDENT_ATTRIBUTED);
    }

    @Transactional
    public List<WorkflowDto> getSubmissions() {
        return getWorkflowsByStatus(WorkflowStatus.SUBMITTED_TO_FENIX);
    }

    @Transactional
    public List<WorkflowDto> getSignedDocuments() {
        return getWorkflowsByStatus(WorkflowStatus.DOCUMENT_SIGNED);
    }
}
