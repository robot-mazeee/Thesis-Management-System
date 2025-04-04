package pt.ulisboa.tecnico.rnl.dei.dms.defense.dto;

import pt.ulisboa.tecnico.rnl.dei.dms.defense.domain.Defense;

public record DefenseDto(long id, String defenseStatus, String date, long grade) {
    public DefenseDto(Defense defense) {
        this(defense.getId(), defense.getDefenseStatus().toString(), defense.getDate(), defense.getGrade());  
    }
}