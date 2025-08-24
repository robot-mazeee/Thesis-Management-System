package pt.ulisboa.tecnico.rnl.dei.tms.defense.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.ulisboa.tecnico.rnl.dei.tms.defense.domain.Defense;
import pt.ulisboa.tecnico.rnl.dei.tms.defense.domain.DefenseStatus;


@Repository
@Transactional
public interface DefenseRepository extends JpaRepository<Defense, Long> {
    public List<Defense> findByStatus(DefenseStatus status);
    Optional<Defense> findByStudentId(Long studentId);
}