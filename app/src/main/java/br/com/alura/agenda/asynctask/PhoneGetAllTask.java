package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.PhoneDao;
import br.com.alura.agenda.model.Phone;

public class PhoneGetAllTask extends AsyncTask<Void, Void, List<Phone>> {
    private final PhoneDao phoneDao;
    private final int studentId;
    private LoadPhonesListener loadPhonesListener;

    public PhoneGetAllTask(PhoneDao phoneDao, int studentId, LoadPhonesListener loadPhonesListener) {
        this.phoneDao = phoneDao;
        this.studentId = studentId;
        this.loadPhonesListener = loadPhonesListener;
    }

    @Override
    protected List<Phone> doInBackground(Void... voids) {
        return phoneDao.all(studentId);
    }

    @Override
    protected void onPostExecute(List<Phone> phones) {
        super.onPostExecute(phones);
        loadPhonesListener.loadPhones(phones);
    }

    public interface LoadPhonesListener{
        void loadPhones(List<Phone> phones);
    }
}
