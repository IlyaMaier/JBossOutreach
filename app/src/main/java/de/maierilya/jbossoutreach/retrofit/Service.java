package de.maierilya.jbossoutreach.retrofit;

import java.util.List;

import de.maierilya.jbossoutreach.entities.Contributor;
import de.maierilya.jbossoutreach.entities.Repository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {

    @GET("orgs/JBossOutreach/repos")
    Call<List<Repository>> getRepos();

    @GET("repos/JBossOutreach/{repository}/contributors")
    Call<List<Contributor>> getContributors(@Path("repository") String repository);

}
