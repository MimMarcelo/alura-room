package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.database.dao.PhoneDao;
import br.com.alura.agenda.model.Phone;

public class PhoneSearchFirstTask extends AsyncTask<Void, Void, Phone> {
    private final PhoneDao dao;
    private final PhoneLoadListener phoneLoadListener;
    private final int studentId;

    public PhoneSearchFirstTask(PhoneDao dao, int studentId, PhoneLoadListener phoneLoadListener) {
        this.dao = dao;
        this.studentId = studentId;
        this.phoneLoadListener = phoneLoadListener;
    }

    @Override
    protected Phone doInBackground(Void... voids) {
        return dao.getFirstPhone(studentId);
    }

    @Override
    protected void onPostExecute(Phone phone) {
        super.onPostExecute(phone);
        phoneLoadListener.loadPhone(phone);
    }

    public interface PhoneLoadListener{
        void loadPhone(Phone phone);
    }
}
