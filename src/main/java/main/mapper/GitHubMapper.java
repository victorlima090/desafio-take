package main.mapper;

import main.dto.ResponseDTO;
import main.dto.TakeDTO;

public class GitHubMapper {

    public static TakeDTO githubToTakeDtoMapper(ResponseDTO githubReponse){
        TakeDTO takeDTO = new TakeDTO();
        takeDTO.setRepoTitle(githubReponse.getFull_name());
        takeDTO.setRepoDescription(githubReponse.getDescription());
        takeDTO.setImageUrl(githubReponse.getOwner().avatar_url);

        return takeDTO;
    }
}
