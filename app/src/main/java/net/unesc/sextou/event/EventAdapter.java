package net.unesc.sextou.event;

import android.view.View;
import android.widget.TextView;

import net.unesc.sextou.R;
import net.unesc.sextou.adapter.GenericAdapter;

import java.util.List;

public class EventAdapter extends GenericAdapter<Event> {
    public EventAdapter(List<Event> dataset) {
        super(dataset, new ViewHolder(), R.layout.event_card_adapter);
    }

    static class ViewHolder implements Holder<Event> {
        private TextView mTitle;
        private TextView mContent;

        @Override
        public void configure(View view) {
            mTitle = view.findViewById(R.id.title);
            mContent = view.findViewById(R.id.content);
        }

        @Override
        public void set(Event event) {
            mTitle.setText(event.getTitle());
            mContent.setText(event.getContent());
        }
    }
}
