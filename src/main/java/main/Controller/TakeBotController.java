package main.Controller;

import main.api.GitHubApi;
import main.dto.ResponseDTO;
import main.dto.TakeDTO;
import main.mapper.GitHubMapper;
import main.service.GitHubApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class TakeBotController {

    @RequestMapping("/take-repos")
    public List<TakeDTO> getTakeOlderRepos(){
        GitHubApi gitHubApiService =  GitHubApiService.createService(GitHubApi.class);
        try{
            Response<List<ResponseDTO>>response = gitHubApiService.getMostOlderTakeCSharpRepos().execute();
            if (response.code() == 200){
                List<ResponseDTO> responseDTOList = response.body();
                return responseDTOList.stream().map(responseDTO -> GitHubMapper.githubToTakeDtoMapper(responseDTO)).collect(Collectors.toList());
            }else{
                Collections.emptyList();
            }
            return Collections.emptyList();
        }catch (Exception ex){
            return Collections.emptyList();
        }
    }
}
