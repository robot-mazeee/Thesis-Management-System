package pt.ulisboa.tecnico.rnl.dei.dms.defense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.ulisboa.tecnico.rnl.dei.dms.defense.domain.Defense;
import pt.ulisboa.tecnico.rnl.dei.dms.defense.repository.DefenseRepository;
import pt.ulisboa.tecnico.rnl.dei.dms.exceptions.DEIException;
import pt.ulisboa.tecnico.rnl.dei.dms.exceptions.ErrorMessage;
import pt.ulisboa.tecnico.rnl.dei.dms.defense.dto.DefenseDto;
import pt.ulisboa.tecnico.rnl.dei.dms.person.domain.Person;

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

        defense = defenseRepository.save(defense);

        return new DefenseDto(defense.getId(), defense.getDefenseStatus().toString(), defense.getDate(), defense.getGrade(), defense.getStudent());
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

    // @Transactional 
    // public DefenseDto updateDefense(long id, DefenseDto defenseDto) {
    //     fetchDefenseOrThrow(id);
    //     Defense defense = new Defense(defenseDto);
    //     defense.setId(id);
    //     return new DefenseDto(defenseRepository.save(defense));
    // }

    // @Transactional
    // public defenseDto scheduleDefense(long id, DefenseDto defenseDto) {}

    @Transactional
    public DefenseDto gradeDefense(long id, DefenseDto defenseDto) {
        Defense defense = fetchDefenseOrThrow(id);
        defense.setGrade(defenseDto.grade());
        return new DefenseDto(defenseRepository.save(defense));
    }
}