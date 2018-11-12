package de.maierilya.jbossoutreach.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.maierilya.jbossoutreach.ContributorsActivity;
import de.maierilya.jbossoutreach.R;
import de.maierilya.jbossoutreach.entities.Repository;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<Repository> repositories;
    private Context context;

    public MainAdapter(List<Repository> repositories, Context context) {
        this.repositories = repositories;
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MainViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_main, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int i) {
        Repository r = repositories.get(i);
        mainViewHolder.name.setText(r.getName());
        mainViewHolder.description.setText(r.getDescription());
        mainViewHolder.language.setText(r.getLanguage());
        mainViewHolder.stars.setText(String.valueOf(r.getStars()));
        mainViewHolder.forks.setText(String.valueOf(r.getForks()));
        mainViewHolder.watchers.setText(String.valueOf(r.getWatchers()));
        mainViewHolder.setRepo(r.getName());
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public void updateData(List<Repository> r) {
        repositories = r;
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        private TextView name, description, language, stars, forks, watchers;
        private String repo;

        MainViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_repo_name_main);
            description = itemView.findViewById(R.id.tv_repo_description_main);
            language = itemView.findViewById(R.id.tv_repo_language_main);
            stars = itemView.findViewById(R.id.tv_repo_stars_main);
            forks = itemView.findViewById(R.id.tv_repo_forks_main);
            watchers = itemView.findViewById(R.id.tv_repo_watchers_main);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, ContributorsActivity.class);
                    i.putExtra("repo", repo);
                    context.startActivity(i);
                }
            });
        }

        private void setRepo(String repo) {
            this.repo = repo;
        }

    }

}
