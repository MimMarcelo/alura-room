package br.com.alura.agenda.ui.holder;

import android.view.View;
import android.widget.TextView;

import br.com.alura.agenda.R;

public class TituloSubtituloHolder {
    public final TextView titulo;
    public final TextView subtitulo;

    public TituloSubtituloHolder(View view) {
        this.titulo = view.findViewById(R.id.txt_itemlist_titulo);
        this.subtitulo = view.findViewById(R.id.txt_itemlist_subtitulo);
    }
}
