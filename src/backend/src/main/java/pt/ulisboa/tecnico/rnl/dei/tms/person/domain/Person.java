package pt.ulisboa.tecnico.rnl.dei.tms.person.domain;


import jakarta.persistence.*;

import lombok.Data;
import pt.ulisboa.tecnico.rnl.dei.tms.person.dto.PersonDto;

@Data
@Entity
@Table(name = "people") 
public class Person {

	public enum PersonType {
		COORDINATOR, STAFF, STUDENT, PROFESSOR, ADMIN
	}

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "ist_id", nullable = false, unique = true)
	private String istId;

	@Column(name = "email", nullable = false, unique = true)
    private String email;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
    private PersonType type;

	protected Person() {
	}

	public Person(String name, String istId, String email, PersonType type) {
		this.name = name;
		this.istId = istId;
		this.email = email;
		this.type = type;
	}

	public Person(PersonDto personDto) {
		this(personDto.name(), personDto.istId(), personDto.email(),
				PersonType.valueOf(personDto.type().toUpperCase()));
		System.out.println("PersonDto: " + personDto);
		System.out.println("PersonType: " + personDto.type());
	}
}
