package kodlama.io.devs.devs.business.abstracts;

import java.util.List;

import kodlama.io.devs.devs.business.requests.CreateProgrammingLanguageRequest;
import kodlama.io.devs.devs.business.requests.UpdateProgrammingLanguageRequest;
import kodlama.io.devs.devs.business.responses.GetProgrammingLanguageResponse;

public interface ProgrammingLanguageService {
    void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception;
    void delete(int id);
    void update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) throws Exception;
    List<GetProgrammingLanguageResponse> getAll();
    GetProgrammingLanguageResponse getById(int id) throws Exception;
}
