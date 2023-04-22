package com.example.earthquake_monitor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquake_monitor.databinding.EqListItemBinding;

//la clase que se extiende ListAdapter solicita el objeto que se va a adaptar y un view holder
//que se encarga de reciclar las vistas, es decir, reusa las views para no crear mas de las
//necesarias

public class EqAdapter extends ListAdapter<Earthquake, EqAdapter.EqViewHolder> {

    private Context context;
    //se debe usar un callback para hacer la diferenciación
    public static final DiffUtil.ItemCallback<Earthquake> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Earthquake>() {
                @Override
                public boolean areItemsTheSame(@NonNull Earthquake oldItem,
                                               @NonNull Earthquake newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Earthquake oldItem,
                                                  @NonNull Earthquake newItem) {
                    return oldItem.equals(newItem);
                }
            };
    protected EqAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;

    }
    OnItemClickListener onItemClickListener;
    interface OnItemClickListener{
        void onItemClick(Earthquake earthquake);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    //se ejecuta este metodo por cada elemento de la lista
    //inflate significa transformar un layout en una view
    @NonNull
    @Override
    public EqAdapter.EqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //en el metodo inflate se recibe el layout a transformar en view
        EqListItemBinding binding = EqListItemBinding.inflate(LayoutInflater.from(parent.getContext()));

        //despues de haber creado el view, se crea el ViewHolder
        return new EqViewHolder(binding);
    }
    //se ejecuta este metodo por cada elemento de la lista, este metodo es el que se encarga de
    //decirle a EqViewHolder que datos debe pintar en la lista
    @Override
    public void onBindViewHolder(@NonNull EqAdapter.EqViewHolder holder, int position) {
        Earthquake earthquake = getItem(position);
        //se mandara a pintar la informacion de la tarjeta
        holder.bind(earthquake);
    }

    //esta es la clase que hara el reuso
    class EqViewHolder extends RecyclerView.ViewHolder{
        private final EqListItemBinding dataBinding;
        public EqViewHolder(@NonNull EqListItemBinding binding) {
            super(binding.getRoot());
            dataBinding = binding;

        }
        public void bind( Earthquake earthquake)
        {
            //dataBinding.magnitudeText.setText( String.valueOf(earthquake.getMagnitud()) );
            dataBinding.magnitudeText.setText( context.getString(R.string.magnitude_format, earthquake.getMagnitud())  );
            dataBinding.placeText.setText(earthquake.getPlace());
            dataBinding.getRoot().setOnClickListener( v-> {
                onItemClickListener.onItemClick(earthquake);
            });
            dataBinding.executePendingBindings();
        }
    }

}