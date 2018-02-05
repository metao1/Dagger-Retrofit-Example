package com.metao.flix.app;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.metao.flix.R;
import com.metao.flix.adpter.DepartureAdapter;
import com.metao.flix.di.ApplicationComponent;
import com.metao.flix.di.DaggerDepartureComponent;
import com.metao.flix.di.DepartureModule;
import com.metao.flix.models.Departure;
import com.metao.flix.models.Response;
import com.metao.flix.mvp.DeparturePresenter;
import com.metao.flix.mvp.DepartureView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DepartureView {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Inject
    public DeparturePresenter departuresPresenter;

    @BindView(R.id.departures_recycle_view)
    RecyclerView departureRecycleView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private DepartureAdapter departureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        initToolbar();
        departureRecycleView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        departureAdapter = new DepartureAdapter(this);
        departureRecycleView.setNestedScrollingEnabled(false);
        departureRecycleView.setAdapter(departureAdapter);
        createFlixComponent();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (departureAdapter != null) {
            List<Departure> departureList = departureAdapter.getDepartureList();
            outState.putParcelableArray("departures", departureList.toArray(new Departure[]{}));
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Departure[] departures = (Departure[]) savedInstanceState.getParcelableArray("departures");
        assert departures != null;
        List<Departure> departureList = new ArrayList<>(Arrays.asList(departures));
        stateChanged(new ArrayList<>(departureList));
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void initToolbar() {
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void createFlixComponent() {
        DaggerDepartureComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .departureModule(new DepartureModule(getApp(), this))
                .build()
                .inject(this);
    }

    protected Application getApp() {
        return getApplication();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((Flixpplication) getApplication()).getApplicationComponent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (departureAdapter != null) {
            List<Departure> departureList = departureAdapter.getDepartureList();
            if (departureList != null && departureList.size() == 0) {
                departuresPresenter.getDepartures();
            }
        }
    }

    @Override
    public void onDeparturesLoad() {
        Toast.makeText(this, "Departures loaded completely", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoading() {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNextDeparturesLoad(Response response) {
        if (response != null && response.timetable != null && response.timetable.departures != null) {
            stateChanged(response.timetable.departures);
        }
    }

    private void stateChanged(ArrayList<Departure> departures) {
        this.departureAdapter.setDepartureList(departures);
    }

    @Override
    public void onErrorLoadingDepartures(Throwable e) {
        Log.d(TAG, "onErrorLoadingDepartures: " + e.getMessage());
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
