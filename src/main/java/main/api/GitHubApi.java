package main.api;

import main.dto.ResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface GitHubApi {
    @GET("users/takenet/repos?sort=created&direction=asc&page=1&per_page=5&language=c#")
    public Call<List<ResponseDTO>> getMostOlderTakeCSharpRepos();
}
