package kodlama.io.devs.devs.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.devs.devs.business.abstracts.ProgrammingLanguageService;
import kodlama.io.devs.devs.business.abstracts.SubTechnologyService;
import kodlama.io.devs.devs.business.requests.CreateSubTechnologyRequest;
import kodlama.io.devs.devs.business.requests.UpdateSubTechnologyRequest;
import kodlama.io.devs.devs.business.responses.GetProgrammingLanguageResponse;
import kodlama.io.devs.devs.business.responses.GetSubTechnologyResponse;
import kodlama.io.devs.devs.dataAccess.abstracts.SubTechnologyRepository;
import kodlama.io.devs.devs.entities.concretes.SubTechnology;

@Service
public class SubTechnologyManager implements SubTechnologyService {

	private SubTechnologyRepository subTechnologyRepository;
	private ProgrammingLanguageService programmingLanguageService;
	private ModelMapper modelMapper;

	@Autowired
	public SubTechnologyManager(SubTechnologyRepository subTechnologyRepository,
			ProgrammingLanguageService programmingLanguageService, ModelMapper modelMapper) {
		this.subTechnologyRepository = subTechnologyRepository;
		this.programmingLanguageService = programmingLanguageService;
		this.modelMapper = modelMapper;
	}

	@Override
	public void add(CreateSubTechnologyRequest createSubTechnologyRequest) throws Exception {
		GetProgrammingLanguageResponse getProgrammingLanguageResponse = programmingLanguageService
				.getById(createSubTechnologyRequest.getProgrammingLanguageId());

		SubTechnology subTechnology = modelMapper.map(createSubTechnologyRequest, SubTechnology.class);
		subTechnologyRepository.save(subTechnology);
	}

	@Override
	public void delete(int id) {
		subTechnologyRepository.deleteById(id);
	}

	@Override
	public void update(UpdateSubTechnologyRequest updateSubTechnologyRequest) throws Exception {
		GetProgrammingLanguageResponse getProgrammingLanguageResponse = programmingLanguageService
				.getById(updateSubTechnologyRequest.getProgrammingLanguageId());
		SubTechnology subTechnology = modelMapper.map(getProgrammingLanguageResponse, SubTechnology.class);
		subTechnologyRepository.save(subTechnology);
	}

	@Override
	public List<GetSubTechnologyResponse> getAll() {
		List<GetSubTechnologyResponse> getSubTechnologyResponses = subTechnologyRepository.findAll().stream()
				.map(s -> modelMapper.map(s, GetSubTechnologyResponse.class)).collect(Collectors.toList());
		return getSubTechnologyResponses;
	}

}
