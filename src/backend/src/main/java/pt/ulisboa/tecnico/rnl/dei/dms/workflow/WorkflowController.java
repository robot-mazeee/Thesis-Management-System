package pt.ulisboa.tecnico.rnl.dei.dms.workflow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pt.ulisboa.tecnico.rnl.dei.dms.person.dto.PersonDto;
import pt.ulisboa.tecnico.rnl.dei.dms.workflow.dto.WorkflowDto;
import pt.ulisboa.tecnico.rnl.dei.dms.workflow.service.WorkflowService;

@RestController
public class WorkflowController {
	 
	@Autowired
	private WorkflowService workflowService;

	// create 
	@PostMapping("/workflows")
	public WorkflowDto createWorkflow(@RequestBody WorkflowDto workflowDto) {
		return workflowService.createWorkflow(workflowDto);
	}

	// get all workflows
	@GetMapping("/workflows")
	public List<WorkflowDto> getWorkflows() {
		return workflowService.getWorkflows();
	}

	// get single workflow
	@GetMapping("/workflows/{id}")
	public WorkflowDto getWorkflow(long id) {
		return workflowService.getWorkflow(id);
	}

	@GetMapping("/student-workflow/{studentId}")
	public WorkflowDto getWorkflowByStudent(Long studentId) {
		return workflowService.getWorkflowByStudent(studentId);
	}

	// update workflow status
	@PutMapping("/workflows/{id}")
	public WorkflowDto updateWorkflow(@PathVariable long id, @RequestBody WorkflowDto workflow) {
		System.out.println(id);
		return workflowService.updateWorkflow(id, workflow);
	}

	@GetMapping("/workflows/juri-proposals") 
	public List<WorkflowDto> getJuriProposals() {
		return workflowService.getJuriProposals(); 
	}

	@GetMapping("/workflows/juri-proposals/approved") 
	public List<WorkflowDto> getApprovedJuriProposals() {
		return workflowService.getApprovedJuriProposals(); 
	}

	// get workflow juri
	@GetMapping("/workflows/{id}/teachers")
	public List<PersonDto> getJuri(@PathVariable long id) {
		return workflowService.getJuri(id);
	}

	// get juri president
	@GetMapping("/workflows/{id}/juri-president")
	public PersonDto getJuriPresident(@PathVariable long id) {
		return workflowService.getJuriPresident(id);
	}

	// update/assign juri president
	@PutMapping("/workflows/{id}/juri-president")
	public WorkflowDto assignJuriPresident(@PathVariable long id, @RequestBody WorkflowDto workflow) {
		return workflowService.assignJuriPresident(id, workflow);
	}

	@GetMapping("/workflows/juri-president-selected")
	public List<WorkflowDto> getJuriPresidentSelectedWorkflows() {
		return workflowService.getJuriPresidentSelectedWorkflows();
	}

	// // workflows submetidos no fenix
	@GetMapping("/workflows/signed-documents")
	public List<WorkflowDto> getSignedDocuments() {
		return workflowService.getSignedDocuments();
	}

	// submissão de um workflow no fenix
	@GetMapping("/workflows/submissions")
	public List<WorkflowDto> getSubmissions() {
		return workflowService.getSubmissions();
	}

}