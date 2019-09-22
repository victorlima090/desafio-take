package main.Controller;

import main.api.GitHubApi;
import main.dto.ResponseDTO;
import main.dto.TakeDTO;
import main.mapper.GitHubMapper;
import main.service.GitHubApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TakeBotController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TakeBotController.class);

    @GetMapping(value = "/repos/{userName}")
    public List<TakeDTO> getTakeOlderRepos(@PathVariable("userName") String userName,
                                           @RequestParam(value = "sort", required = false) String sort,
                                           @RequestParam(value = "direction", required = false) String direction,
                                           @RequestParam(value = "page", required = false) Integer page,
                                           @RequestParam(value = "perPage", required = false) Integer perPage,
                                           @RequestParam(value = "language", required = false) String language,
                                           HttpServletResponse response) throws Exception {


        try {
            GitHubApi gitHubApiService = GitHubApiService.createService(GitHubApi.class);
            Response<List<ResponseDTO>> githubAPiResponse = gitHubApiService.getGithubReposByUsername(userName,
                    sort, direction, page, perPage, language)
                    .execute();
            if (githubAPiResponse.code() == 200) {
                List<ResponseDTO> responseDTOList = githubAPiResponse.body();
                return responseDTOList.stream().map(responseDTO -> GitHubMapper.githubToTakeDtoMapper(responseDTO)).collect(Collectors.toList());
            } else {
                LOGGER.error("Status Code {} and message {}", githubAPiResponse.code(), githubAPiResponse.message());
                response.sendError(githubAPiResponse.code(), githubAPiResponse.message());
                return null;
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            response.sendError(500, ex.getMessage());
            return null;
        }
    }
}
