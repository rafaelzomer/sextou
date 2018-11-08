package net.unesc.sextou.event;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
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
            private CardView mCard;
            private ImageView mCheck;

            @Override
            public void configure(View view) {
                mTitle = view.findViewById(R.id.title);
                mIcon = view.findViewById(R.id.icon);
                mCard = view.findViewById(R.id.card);
                mCheck = view.findViewById(R.id.check);
            }

            @Override
            public void set(EventType event) {
                mTitle.setText(event.getTitle());
                mIcon.setImageResource(event.getIcon());

                mCheck.setVisibility(event.isChecked() ? View.VISIBLE : View.INVISIBLE);


                mCard.setOnClickListener(viewCard -> {
                    event.getListener().onClick(viewCard);
                    for(int i = 0; i < dataset.size(); i++){
                        EventType eventType = dataset.get(i);
                        getView(i).findViewById(R.id.check).setVisibility(View.INVISIBLE);
                        eventType.setChecked(false);
                    }
                    event.setChecked(true);
                    mCheck.setVisibility(View.VISIBLE);
                });
            }
        });
    }
}
