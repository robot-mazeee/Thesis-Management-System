package pt.ulisboa.tecnico.rnl.dei.dms.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.ulisboa.tecnico.rnl.dei.dms.workflow.domain.Workflow;
import pt.ulisboa.tecnico.rnl.dei.dms.workflow.domain.WorkflowStatus;

import java.util.List;
import java.util.Optional;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
    public List<Workflow> findByWorkflowStatus(WorkflowStatus status);
    Optional<Workflow> findByStudentId(Long studentId);
}
