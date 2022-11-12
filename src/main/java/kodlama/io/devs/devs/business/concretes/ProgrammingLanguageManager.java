package kodlama.io.devs.devs.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.devs.devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.devs.devs.business.requests.CreateProgrammingLanguageRequest;
import kodlama.io.devs.devs.business.requests.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.devs.business.responses.GetProgrammingLanguageResponse;
import kodlama.io.devs.devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import kodlama.io.devs.devs.entities.concretes.ProgrammingLanguage;

@Service
public class ProgrammingLanguageManager implements ProgrammingLanguageService {

	private ProgrammingLanguageRepository programmingLanguageRepository;
	private ModelMapper modelMapper;

	@Autowired
	public ProgrammingLanguageManager(ProgrammingLanguageRepository programmingLanguageRepository,
			ModelMapper modelMapper) {
		this.programmingLanguageRepository = programmingLanguageRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception {
		if (createProgrammingLanguageRequest.getName().isEmpty()
				|| createProgrammingLanguageRequest.getName().isBlank()) {
			throw new Exception("Name can not be null");
		} else if (programmingLanguageRepository.existsByName(createProgrammingLanguageRequest.getName())) {
			throw new Exception(createProgrammingLanguageRequest.getName() + " already exists");
		} else {
			ProgrammingLanguage programmingLanguage = modelMapper.map(createProgrammingLanguageRequest,
					ProgrammingLanguage.class);
			programmingLanguageRepository.save(programmingLanguage);
		}
	}

	@Override
	public void delete(int id) {
		programmingLanguageRepository.deleteById(id);
	}

	@Override
	public void update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) throws Exception {
		if (updateProgrammingLanguageRequest.getName().isEmpty()
				|| updateProgrammingLanguageRequest.getName().isBlank()) {
			throw new Exception("Name can not be null");
		} else if (programmingLanguageRepository.existsByName(updateProgrammingLanguageRequest.getName())) {
			throw new Exception(updateProgrammingLanguageRequest.getName() + " already exists");
		} else {
			ProgrammingLanguage programmingLanguage = programmingLanguageRepository
					.findById(updateProgrammingLanguageRequest.getId())
					.orElseThrow(() -> new Exception("Id does not exists"));
			programmingLanguage = modelMapper.map(updateProgrammingLanguageRequest, ProgrammingLanguage.class);
			programmingLanguageRepository.save(programmingLanguage);
		}
	}

	@Override
	public List<GetProgrammingLanguageResponse> getAll() {
		List<GetProgrammingLanguageResponse> getProgrammingLanguageResponses = programmingLanguageRepository.findAll()
				.stream().map(s -> modelMapper.map(s, GetProgrammingLanguageResponse.class))
				.collect(Collectors.toList());
		return getProgrammingLanguageResponses;
	}

	@Override
	public GetProgrammingLanguageResponse getById(int id) throws Exception {
		ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findById(id)
				.orElseThrow(() -> new Exception("Id does not exists"));
		GetProgrammingLanguageResponse getProgrammingLanguageResponse = modelMapper.map(programmingLanguage,
				GetProgrammingLanguageResponse.class);
		return getProgrammingLanguageResponse;
	}

}
