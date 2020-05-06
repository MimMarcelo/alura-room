package br.com.alura.agenda;

import android.app.Application;

@SuppressWarnings("WeakerAccess")
public class AgendaApplication extends Application {

    /*
    Essa classe só é executada uma vez
    Portanto, caso seja necessário executar algum procedimento
    apenas uma vez, esse é o lugar.

    - Tela de Splash
    - Carregar o DAO

    Tudo deve ser chamado dentro do on-Create
     */
    @SuppressWarnings("EmptyMethod")
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
