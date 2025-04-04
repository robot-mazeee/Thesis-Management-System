package pt.ulisboa.tecnico.rnl.dei.dms.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.ulisboa.tecnico.rnl.dei.dms.workflow.domain.Workflow;
import pt.ulisboa.tecnico.rnl.dei.dms.workflow.domain.WorkflowStatus;

import java.util.List;

@Repository
@Transactional
public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
    public List<Workflow> findByWorkflowStatus(WorkflowStatus status);
}
