package pt.ulisboa.tecnico.rnl.dei.dms.defense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.ulisboa.tecnico.rnl.dei.dms.defense.domain.Defense;


@Repository
@Transactional
public interface DefenseRepository extends JpaRepository<Defense, Long> {

}