package com.metao.flix.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.metao.flix.R;
import com.metao.flix.models.Departure;
import com.metao.flix.utils.FlixUtils.FlixUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mehrdad A.Karami on 3/26/28.
 */
public class DepartureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = DepartureViewHolder.class.getSimpleName();
    private List<Departure> departureList = new ArrayList<>();
    private final LayoutInflater inflater;

    public DepartureAdapter(Activity activity) {
        inflater = LayoutInflater.from(activity.getBaseContext());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View departureView = inflater.inflate(R.layout.departure_item, parent, false);
        return new DepartureViewHolder(departureView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DepartureViewHolder departureViewHolder = (DepartureViewHolder) holder;
        if (departureList != null && departureList.get(position) != null) {
            departureViewHolder.bind(departureList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return this.departureList.size();
    }

    public List<Departure> getDepartureList(){
        return this.departureList;
    }

    public void setDepartureList(ArrayList<Departure> departuresList) {
        this.departureList.addAll(departuresList);
        notifyDataSetChanged();
    }

    class DepartureViewHolder extends RecyclerView.ViewHolder {

        private View departureView;
        private TextView departureNameTextView, departureLineCodeTextView, departureTimeTextView;

        DepartureViewHolder(View itemView) {
            super(itemView);
            this.departureView = itemView;
        }

        void bind(Departure departure) {
            if (departure != null && departure.datetime != null
                    && departure.datetime.timestamp != null) {
                String time = FlixUtils.timestampToTime(departure.datetime.timestamp);
                departureNameTextView = this.departureView.findViewById(R.id.departure_name);
                departureLineCodeTextView = this.departureView.findViewById(R.id.departure_line_code);
                departureTimeTextView = this.departureView.findViewById(R.id.departure_time);
                departureNameTextView.setText(departure.direction);
                departureTimeTextView.setText(time);
                departureLineCodeTextView.setText(departure.line_code);
            }
        }
    }
}
