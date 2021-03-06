package de.maierilya.jbossoutreach.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import de.maierilya.jbossoutreach.R;
import de.maierilya.jbossoutreach.entities.Contributor;

public class ContributorsAdapter extends RecyclerView.Adapter<ContributorsAdapter.ContributorsViewHolder> {

    private List<Contributor> contributors;
    private Context context;

    public ContributorsAdapter(List<Contributor> contributors, Context context) {
        this.contributors = contributors;
        this.context = context;
    }

    @NonNull
    @Override
    public ContributorsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ContributorsViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_contributors, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContributorsViewHolder contributorsViewHolder, int i) {
        Contributor contributor = contributors.get(i);
        contributorsViewHolder.name.setText(contributor.getName());
        Glide.with(context).load(contributor.getPhoto()).into(contributorsViewHolder.photo);
    }

    @Override
    public int getItemCount() {
        return contributors.size();
    }

    public  void updateContributors(List<Contributor> c) {
        contributors = c;
    }

    class ContributorsViewHolder extends RecyclerView.ViewHolder {

        CircularImageView photo;
        TextView name;

        ContributorsViewHolder(@NonNull View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.photo);
            name = itemView.findViewById(R.id.tv_name);
        }
    }

}
