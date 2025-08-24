package pt.ulisboa.tecnico.rnl.dei.tms.person;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pt.ulisboa.tecnico.rnl.dei.tms.person.dto.PersonDto;
import pt.ulisboa.tecnico.rnl.dei.tms.person.service.PersonService;

@RestController
public class PersonController {
	@Autowired
	private PersonService personService;

	@GetMapping("/people")
	public List<PersonDto> getPeople() {
		return personService.getPeople();
	}
	
	@GetMapping("/students")
	public List<PersonDto> getStudents() {
		return personService.getStudents();
	}

	@GetMapping("/professors")
	public List<PersonDto> getProfessors() {
		return personService.getProfessors();
	}

	@PostMapping("/people")
	public PersonDto createPerson(@RequestBody PersonDto personDto) {
		return personService.createPerson(personDto);
	}

	@GetMapping("/people/{id}")
	public PersonDto getPerson(@PathVariable long id) {
		return personService.getPerson(id);
	}

	@PutMapping("/people/{id}")
	public PersonDto updatePerson(@PathVariable long id, @RequestBody PersonDto personDto) {
		System.out.println("updating");
		return personService.updatePerson(id, personDto);
	}

	@DeleteMapping("/people/{id}")
	public void deletePerson(@PathVariable long id) {
		personService.deletePerson(id);
	}
}