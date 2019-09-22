package main.Controller;

import main.api.GitHubApi;
import main.dto.ResponseDTO;
import main.dto.TakeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import main.mapper.GitHubMapper;
import main.service.GitHubApiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TakeBotController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TakeBotController.class);

    @RequestMapping("/take-repos")
    public List<TakeDTO> getTakeOlderRepos(){
        GitHubApi gitHubApiService =  GitHubApiService.createService(GitHubApi.class);
        try{
            Response<List<ResponseDTO>>response = gitHubApiService.getMostOlderTakeCSharpRepos().execute();
            if (response.code() == 200){
                List<ResponseDTO> responseDTOList = response.body();
                return responseDTOList.stream().map(responseDTO -> GitHubMapper.githubToTakeDtoMapper(responseDTO)).collect(Collectors.toList());
            }else{
                LOGGER.error("Status Code {} and message",response.code() , response.message());
                Collections.emptyList();
            }
            return Collections.emptyList();
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return Collections.emptyList();
        }
    }
}
