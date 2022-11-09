package kodlama.io.devs.devs.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.devs.devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.devs.devs.business.requests.CreateProgrammingLanguageRequest;
import kodlama.io.devs.devs.business.requests.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.devs.business.responses.GetProgrammingLanguageResponse;

@RestController
@RequestMapping("/api/programminglanguages")
public class ProgrammingLanguageController {

	private ProgrammingLanguageService programmingLanguageService;

	public ProgrammingLanguageController(ProgrammingLanguageService programmingLanguageService) {
		this.programmingLanguageService = programmingLanguageService;
	}

	@GetMapping("/getall")
	public List<GetProgrammingLanguageResponse> getAll() {
		return programmingLanguageService.getAll();
	}

	@PostMapping("/add")
	public void add(@RequestBody CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception {
		programmingLanguageService.add(createProgrammingLanguageRequest);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		programmingLanguageService.delete(id);
	}

	@PutMapping("/update")
	public void update(@RequestBody UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest)
			throws Exception {
		programmingLanguageService.update(updateProgrammingLanguageRequest);
	}

	@GetMapping("/{id}")
	public GetProgrammingLanguageResponse getById(@PathVariable int id) throws Exception {
		return programmingLanguageService.getById(id);
	}

}
