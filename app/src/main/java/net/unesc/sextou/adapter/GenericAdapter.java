package net.unesc.sextou.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GenericAdapter<T> extends RecyclerView.Adapter<GenericAdapter.ViewHolder<T>> {
    final private List<T> dataset;
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
        return new ViewHolder<>(configure.config(), LayoutInflater.from(parent.getContext())
                .inflate(view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenericAdapter.ViewHolder<T> holder, int position) {
        holder.set(dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public interface Holder<T> {
        void configure(View view);

        void set(T news);
    }

    public interface Configure<J> {
        Holder<J> config();
    }

    static class ViewHolder<T> extends RecyclerView.ViewHolder {
        private Holder<T> holder;

        private ViewHolder(Holder<T> holder, View v) {
            super(v);
            holder.configure(v);
            this.holder = holder;
        }

        void set(T news) {
            holder.set(news);
        }
    }
}
