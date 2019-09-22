package main.api;

import main.dto.ResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface GitHubApi {
    @GET("users/{userName}/repos")
    Call<List<ResponseDTO>> getGithubReposByUsername(@Path ("userName") String userName, @Query("sort")String sort,
                                                     @Query("direction")String direction,
                                                     @Query("page")Integer page, @Query("per_page")Integer perPage,
                                                     @Query("language")String language);

}
