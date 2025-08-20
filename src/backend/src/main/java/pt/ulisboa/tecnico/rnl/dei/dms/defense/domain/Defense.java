package pt.ulisboa.tecnico.rnl.dei.dms.defense.domain;

import pt.ulisboa.tecnico.rnl.dei.dms.defense.dto.DefenseDto;
import pt.ulisboa.tecnico.rnl.dei.dms.person.domain.Person;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "defences")
public class Defense {
    @Id
	@GeneratedValue
	private Long id;
 
    @Column(name = "workflow_status")
	@Enumerated(EnumType.STRING)
    private DefenseStatus defenseStatus;

    @Column(name = "date")
    private String date;

    @Column(name = "grade")
    private long grade;

    @ManyToOne
    @JoinColumn(name = "student")
    private Person student;

    public Defense() {}

    public Defense(DefenseStatus status) {
        this.defenseStatus = status;
    }

    public Defense(DefenseDto defenseDto) {
        this.defenseStatus = DefenseStatus.valueOf(defenseDto.defenseStatus().toUpperCase());
        this.date = defenseDto.date();
        this.grade = defenseDto.grade();
        this.student = defenseDto.student();
    }

    public void setDefenseStatus(DefenseStatus status) {
        this.defenseStatus = status;
    }
}