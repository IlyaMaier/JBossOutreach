package de.maierilya.jbossoutreach;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.maierilya.jbossoutreach.adapters.ContributorsAdapter;
import de.maierilya.jbossoutreach.entities.Contributor;
import de.maierilya.jbossoutreach.retrofit.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContributorsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContributorsAdapter adapter;
    private List<Contributor> contributors;
    private ProgressBar progressBar;
    private String repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributors);
        repo = getIntent().getStringExtra("repo");
        setTitle(repo);
        initView();
        initRV();
        initRetrofit();
    }

    private void initView() {
        RelativeLayout relativeLayout = findViewById(R.id.frame_contributors);
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        relativeLayout.addView(progressBar, params);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void initRV() {
        contributors = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_contributors);
        adapter = new ContributorsAdapter(contributors, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Service service = retrofit.create(Service.class);
        service.getContributors(repo).enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(@NonNull Call<List<Contributor>> call, @NonNull Response<List<Contributor>> response) {
                contributors.addAll(response.body());
                adapter.updateContributors(contributors);
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<List<Contributor>> call, @NonNull Throwable t) {
                Toast.makeText(ContributorsActivity.this, "Error downloading repositories list!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
