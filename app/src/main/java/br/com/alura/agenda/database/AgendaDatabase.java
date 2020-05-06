package br.com.alura.agenda.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.agenda.database.dao.StudentDao;
import br.com.alura.agenda.model.Student;

@Database(
        entities = {
                Student.class
        },
        version = 3,
        exportSchema = false
)
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String DB_NAME = "agenda.db";
    private static AgendaDatabase instance;

    public abstract StudentDao getStudentDao();

    public static AgendaDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context, AgendaDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(
                            new Migration(1, 2) {
                                @Override
                                public void migrate(@NonNull SupportSQLiteDatabase database) {
                                    database.execSQL("ALTER TABLE Student ADD COLUMN 'lastName' TEXT");
                                }
                            },
                            new Migration(2, 3) {
                                @Override
                                public void migrate(@NonNull SupportSQLiteDatabase database) {
                                    //Criar uma tabela temporária com a estrutura desejada
                                    database.execSQL("CREATE TABLE IF NOT EXISTS `StudentTemp` (" +
                                            "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                            "`firstName` TEXT, " +
                                            "`lastName` TEXT, " +
                                            "`phone` TEXT, " +
                                            "`email` TEXT)"
                                    );

                                    //Copiar os dados da tabela antiga para a tabela nova
                                    database.execSQL("INSERT INTO StudentTemp (id, firstName, lastName, phone, email) " +
                                            "SELECT id, name, lastName, phone, email FROM Student");

                                    //Remover tabela antiga
                                    database.execSQL("DROP TABLE Student");

                                    //Renomear tabela nova
                                    database.execSQL("ALTER TABLE StudentTemp RENAME TO Student");
                                }
                            }
                    )
                    .build();
        }
        return instance;
    }
}