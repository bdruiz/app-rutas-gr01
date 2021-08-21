package com.example.apprutasmintic.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apprutasmintic.model.repository.AttendanceRepository;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

import static com.example.apprutasmintic.R.*;

public class AsistenciaAdapter extends RecyclerView.Adapter<AsistenciaAdapter.ViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    private List<AsistenciaInfo> data;
    AttendanceRepository attendanceRepository = new AttendanceRepository();

    public AsistenciaAdapter(List<AsistenciaInfo> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout.lista_asistencia2, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsistenciaAdapter.ViewHolder holder, int position) {
        holder.setInfo(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvjornada_mañana, tvjornada_tarde, tvfecha;
        ImageView imgiconoasisma, imgiconoasista;
        private AsistenciaInfo info;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
/*
            tvjornada
                    = itemView.findViewById(id.tvfecha);
            tvfecha = itemView.findViewById(id.tvjornadaMañana);
            imgiconoasis = itemView.findViewById(id.imgicono_asistencia_mañana);
*/
            tvjornada_mañana = itemView.findViewById(id.tvjornadaMañana);
            tvjornada_tarde = itemView.findViewById(id.tvjornadaTarde);
            tvfecha = itemView.findViewById(id.tvFechaAsistencia);
            imgiconoasisma = itemView.findViewById(id.imgicono_asistencia_mañana);
            imgiconoasista = itemView.findViewById(id.imgicono_asistencia_tarde);
        }

        public void setInfo(AsistenciaInfo info) {
            this.info = info;
            tvfecha.setText(attendanceRepository.UnixToStr(info.getDate()));

            // tvjornada_mañana.setText(info.getAttendad_Morning().toString());
            tvjornada_mañana.setText("Mañana");
            if (info.getAttendad_Morning() == null) {
                imgiconoasisma.setImageResource(drawable.outline_remove_24);
                tvjornada_mañana.setText("");
            } else if (info.getAttendad_Morning()) {
                imgiconoasisma.setImageResource(drawable.check);
            } else {
                imgiconoasisma.setImageResource(drawable.absent);
            }
            if (info.getAttendad_Afternoon() == null) {
                imgiconoasista.setImageResource(drawable.outline_remove_24);
                tvjornada_tarde.setText("");
            } else if (info.getAttendad_Afternoon()) {
                imgiconoasista.setImageResource(drawable.check);

            } else {
                imgiconoasista.setImageResource(drawable.absent);
            }

            //imgiconoasis.setImageResource(drawable.outline_check_24);

/*
            tvjornada.setText(info.getTrip());
            tvfecha.setText(""+info.getFecha());
            if (info.isAttendad() == null){
                tvjornada.setText("----");

            } else if (info.isAttendad()){
                tvjornada.setText("++++");

            }
*/
            // if (info.isAttendad()) {

            //  imgiconoasis.setImageDrawable();
            // } else {
            // imgiconoasis.setImageDrawable();
            // }


        }


    }
}