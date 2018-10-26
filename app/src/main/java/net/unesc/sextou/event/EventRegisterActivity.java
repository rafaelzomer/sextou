package net.unesc.sextou.event;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        getSupportActionBar().setTitle("Novo Evento");
        recyclerView = findViewById(R.id.events_type);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(EventRegisterActivity.this, 2));
        List<EventType> eventList = new ArrayList<>();

        eventList.add(new EventType("15 Anos", R.drawable.ic_15th_birthday));
        eventList.add(new EventType("Aniversários", R.drawable.ic_aniversario));
        eventList.add(new EventType("Casamentos", R.drawable.ic_casamento));
        eventList.add(new EventType("Churrasco", R.drawable.ic_churrasco));
        eventList.add(new EventType("Empresarial", R.drawable.ic_empresarial));
        eventList.add(new EventType("Festa em geral", R.drawable.ic_festa_geral));
        eventList.add(new EventType("Festa infantil", R.drawable.ic_festa_infantil));
        eventList.add(new EventType("Formaturas", R.drawable.ic_formatura));
        eventList.add(new EventType("Sextou", R.drawable.ic_sextou));

        adapter = new EventTypeAdapter(eventList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public Class getPreviousActivity() {
        return AppActivity.class;
    }
}

