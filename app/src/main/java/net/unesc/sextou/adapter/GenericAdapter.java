package net.unesc.sextou.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericAdapter<T> extends RecyclerView.Adapter<GenericAdapter.ViewHolder<T>> {
    final private List<T> dataset;
    final private Map<Integer, View> viewByPosition = new HashMap<>();
    final private int view;
    final private Configure<T> configure;

    public GenericAdapter(List<T> dataset, int view, Configure<T> configure) {
        this.dataset = dataset;
        this.view = view;
        this.configure = configure;
    }

    @NonNull
    @Override
    public ViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Holder<T> holder = configure.config();
        holder.setViewByPosition(viewByPosition);
        return new ViewHolder<>(holder, LayoutInflater.from(parent.getContext())
                .inflate(view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenericAdapter.ViewHolder<T> holder, int position) {
        viewByPosition.put(position, holder.view);
        holder.set(dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static abstract class Holder<T> {
        private Map<Integer, View> viewByPosition = new HashMap<>();
        protected abstract void configure(View view);
        protected abstract void set(T news);

        public View getView(Integer position) {
            return viewByPosition.get(position);
        }

        public void setViewByPosition(Map<Integer, View> viewByPosition) {
            this.viewByPosition = viewByPosition;
        }
    }

    public interface Configure<J> {
        Holder<J> config();
    }

    static class ViewHolder<T> extends RecyclerView.ViewHolder {
        private final Holder<T> holder;
        private final View view;

        private ViewHolder(Holder<T> holder, View v) {
            super(v);
            this.holder = holder;
            this.view = v;
            holder.configure(v);
        }

        void set(T news) {
            holder.set(news);
        }
    }
}
