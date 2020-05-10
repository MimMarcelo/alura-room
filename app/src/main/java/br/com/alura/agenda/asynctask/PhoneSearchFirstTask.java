package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import br.com.alura.agenda.database.dao.PhoneDao;
import br.com.alura.agenda.model.Phone;

public class PhoneSearchFirstTask extends AsyncTask<Void, Void, Phone> {
    private final PhoneDao dao;
    private final TextView txt;
    private final int studentId;

    public PhoneSearchFirstTask(PhoneDao dao, TextView txt, int studentId) {
        this.dao = dao;
        this.txt = txt;
        this.studentId = studentId;
    }

    @Override
    protected Phone doInBackground(Void... voids) {
        return dao.getFirstPhone(studentId);
    }

    @Override
    protected void onPostExecute(Phone phone) {
        super.onPostExecute(phone);
        txt.setText(phone.getNumber());
    }
}
