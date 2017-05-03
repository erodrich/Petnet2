package pe.edu.upc.petnet2.adapters;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.petnet2.PetnetApp;
import pe.edu.upc.petnet2.R;
import pe.edu.upc.petnet2.activities.AboutPetActivity;
import pe.edu.upc.petnet2.activities.AboutResultActivity;
import pe.edu.upc.petnet2.models.Result;

/**
 * Created by Eric on 3/05/17.
 */

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {

    private List<Result> results = new ArrayList<>();
    @Override
    public ResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_result, parent, false);
        ResultsAdapter.ViewHolder viewHolder = new ResultsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResultsAdapter.ViewHolder holder, final int position) {
        holder.nameResultTextView.setText(results.get(position).getName());
        holder.titleResultTextView.setText(results.get(position).getTitle());
        holder.contentResultTextView.setText(results.get(position).getContent());
        holder.resultCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PetnetApp.getInstance().setCurrentResult(results.get(position));
                v.getContext().startActivity(new Intent(
                        v.getContext(), AboutResultActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return getResults().size();
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView resultCardView;
        TextView nameResultTextView, titleResultTextView, contentResultTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            resultCardView = (CardView) itemView.findViewById(R.id.resultCardView);
            nameResultTextView = (TextView) itemView.findViewById(R.id.nameResultTextView);
            titleResultTextView = (TextView) itemView.findViewById(R.id.titleResultTextView);
            contentResultTextView = (TextView) itemView.findViewById(R.id.contentResultTextView);
        }
    }
}
