package net.unesc.sextou.event;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.unesc.sextou.R;
import net.unesc.sextou.adapter.GenericAdapter;

import java.util.List;

public class EventTypeAdapter extends GenericAdapter<EventType> {
    public EventTypeAdapter(List<EventType> dataset) {
        super(dataset, R.layout.event_type_adapter, () -> new Holder<EventType>() {
            private TextView mTitle;
            private ImageView mIcon;

            @Override
            public void configure(View view) {
                mTitle = view.findViewById(R.id.title);
                mIcon = view.findViewById(R.id.icon);
            }

            @Override
            public void set(EventType event) {

                mTitle.setText(event.getTitle());
                mIcon.setImageResource(event.getIcon());
            }
        });
    }
}