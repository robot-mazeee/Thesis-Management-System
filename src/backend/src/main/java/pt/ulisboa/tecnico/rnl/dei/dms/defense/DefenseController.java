package pt.ulisboa.tecnico.rnl.dei.dms.defense;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pt.ulisboa.tecnico.rnl.dei.dms.defense.dto.DefenseDto;
import pt.ulisboa.tecnico.rnl.dei.dms.defense.service.DefenseService;

@RestController
public class DefenseController {
	 
	@Autowired
	private DefenseService defenseService;

	@PostMapping("/defenses")
	public DefenseDto createDefense(@RequestBody DefenseDto defenseDto) {
		return defenseService.createDefense(defenseDto);
	}

	@GetMapping("/defenses")
	public List<DefenseDto> getDefenses() {
		return defenseService.getDefenses();
	}

	// update workflow status
	// @PutMapping("/defenses/{id}")
	// public DefenseDto updateDefense(@PathVariable long id, @RequestBody DefenseDto defense) {
	// 	return defenseService.updateDefense(id, defense);
	// }

	@PutMapping("/defenses/{id}/grade")
	public DefenseDto gradeDefense(@PathVariable long id, @RequestBody DefenseDto defense) {
		return defenseService.gradeDefense(id, defense);
	}

}