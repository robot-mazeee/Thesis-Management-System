package pt.ulisboa.tecnico.rnl.dei.tms.defense.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.ulisboa.tecnico.rnl.dei.tms.defense.domain.Defense;
import pt.ulisboa.tecnico.rnl.dei.tms.defense.domain.DefenseStatus;
import pt.ulisboa.tecnico.rnl.dei.tms.defense.dto.DefenseDto;
import pt.ulisboa.tecnico.rnl.dei.tms.defense.repository.DefenseRepository;
import pt.ulisboa.tecnico.rnl.dei.tms.exceptions.DEIException;
import pt.ulisboa.tecnico.rnl.dei.tms.exceptions.ErrorMessage;
import pt.ulisboa.tecnico.rnl.dei.tms.person.domain.Person;

@Service
@Transactional
public class DefenseService {

	@Autowired 
	private DefenseRepository defenseRepository;

    private Defense fetchDefenseOrThrow(long id) {
		return defenseRepository.findById(id)
				.orElseThrow(() -> new DEIException(ErrorMessage.NO_SUCH_WORKFLOW, Long.toString(id)));
	}

    private Defense fetchDefenseByStudent(long id) {
		return defenseRepository.findByStudentId(id).orElse(null);
	}

	@Transactional
    public DefenseDto createDefense(DefenseDto defenseDto) {
        Defense defense = new Defense();
        Person student = defenseDto.student();
        defense.setStudent(student);
        
        LocalDate defenseDate = defenseDto.date();
        LocalDate currentDate = LocalDate.now();

        System.out.println("defense date: " + defenseDate + " current date: " + currentDate);

        DefenseStatus status;
        if (defenseDate.isAfter(currentDate)) {
            status = DefenseStatus.DEFENSE_SCHEDULED;
        } else {
            status = DefenseStatus.IN_REVISION;
        }

        defense.setDate(defenseDate);
        defense.setDefenseStatus(status);

        defense = defenseRepository.save(defense);

        return new DefenseDto(defense.getId(), defense.getStatus().toString(), defense.getDate(), defense.getGrade(), defense.getStudent());
    }

	@Transactional
	public List<DefenseDto> getDefenses() {
        return defenseRepository.findAll().stream()
            .map(DefenseDto::new)
            .toList();
	}

    @Transactional
    public DefenseDto getDefense(long id) {
        return new DefenseDto(fetchDefenseOrThrow(id));
    }

    @Transactional
    public DefenseDto getDefenseByStudent(long studentId) {
        var defense = fetchDefenseByStudent(studentId);
        return (defense != null) ? new DefenseDto(defense) : null;
    }

    @Transactional
    public DefenseDto gradeDefense(long id, DefenseDto defenseDto) {
        Defense defense = fetchDefenseOrThrow(id);
        defense.setGrade(defenseDto.grade());
        defense.setStatus(DefenseStatus.SUBMITTED_TO_FENIX);
        return new DefenseDto(defenseRepository.save(defense));
    }
}