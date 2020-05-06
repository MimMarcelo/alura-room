package br.com.alura.agenda;

import android.app.Application;

@SuppressWarnings("WeakerAccess")
public class AgendaApplication extends Application {

    /*
    Essa classe só é executada uma vez (Sempre que a aplicação é iniciada)
    Portanto, caso seja necessário executar algum procedimento
    apenas uma vez, esse é o lugar.

    - Tela de Splash

    Essa classe deve ser citada no Manifest na TAG <application> adicione o parâmetro:
        android:name=".AgendaApplication"

    Tudo deve ser chamado dentro do on-Create
     */
    @SuppressWarnings("EmptyMethod")
    @Override
    public void onCreate() {
        super.onCreate();
//        AgendaDatabase db = Room.databaseBuilder(this, AgendaDatabase.class, M.database.db_name)
//                .allowMainThreadQueries()
//                .build();
//        StudentDao dao = db.getStudentDao();
//        dao.insert(new Student("Marcelo Júnior", "(84) 9.8898-2980", "rokermarcelo@gmail.com"));
//        dao.insert(new Student("Ana Carolina", "(84) 9.8854-4419", "carol.mattiuci@gmail.com"));
    }
}
