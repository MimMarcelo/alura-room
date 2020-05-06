package br.com.alura.agenda.ui.holder;

import android.view.View;
import android.widget.TextView;

import br.com.alura.agenda.R;

public class StudentsHolder {
    public final TextView txtFullName;
    public final TextView txtPhone;

    public StudentsHolder(View view) {
        this.txtFullName = view.findViewById(R.id.txt_student_full_name);
        this.txtPhone = view.findViewById(R.id.txt_student_phone);
    }
}
