package net.unesc.sextou.event;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.unesc.sextou.AppActivity;
import net.unesc.sextou.R;
import net.unesc.sextou.activity.DefaultActivity;

import java.util.ArrayList;
import java.util.List;

public class EventRegisterActivity extends DefaultActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_register_activity);
        recyclerView = findViewById(R.id.events_type);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(EventRegisterActivity.this, 2));
        List<EventType> eventList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            eventList.add(new EventType("Fotografia " + i));
        }
        adapter = new EventTypeAdapter(eventList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public Class getPreviousActivity() {
        return AppActivity.class;
    }
}

