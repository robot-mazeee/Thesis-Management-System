package pt.ulisboa.tecnico.rnl.dei.dms.defense.dto;

import pt.ulisboa.tecnico.rnl.dei.dms.defense.domain.Defense;
import pt.ulisboa.tecnico.rnl.dei.dms.person.domain.Person;

public record DefenseDto(long id, String defenseStatus, String date, long grade, Person student) {
    public DefenseDto(Defense defense) {
        this(defense.getId(), defense.getDefenseStatus().toString(), defense.getDate(), defense.getGrade(), defense.getStudent());  
    }
}