package pt.ulisboa.tecnico.rnl.dei.dms.defense.domain;

import pt.ulisboa.tecnico.rnl.dei.dms.defense.dto.DefenseDto;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "defences")
public class Defense {
    @Id
	@GeneratedValue
	private Long id;
 
    @Column(name = "workflow_status", nullable = false)
	@Enumerated(EnumType.STRING)
    private DefenseStatus defenseStatus;

    @Column(name = "date")
    private String date;

    @Column(name = "grade")
    private long grade;

    public Defense() {}

    public Defense(DefenseStatus status) {
        this.defenseStatus = status;
    }

    public Defense(DefenseDto defenseDto) {
        this.defenseStatus = DefenseStatus.valueOf(defenseDto.defenseStatus().toUpperCase());
        this.date = defenseDto.date();
        this.grade = defenseDto.grade();
    }

    public void setDefenseStatus(DefenseStatus status) {
        this.defenseStatus = status;
    }
}