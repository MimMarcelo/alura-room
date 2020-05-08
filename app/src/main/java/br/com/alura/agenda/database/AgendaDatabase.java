package br.com.alura.agenda.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.agenda.database.converter.CalendarConverter;
import br.com.alura.agenda.database.converter.PhoneTypeConverter;
import br.com.alura.agenda.database.dao.PhoneDao;
import br.com.alura.agenda.database.dao.StudentDao;
import br.com.alura.agenda.model.Phone;
import br.com.alura.agenda.model.Student;

@Database(
        entities = {
                Student.class,
                Phone.class
        },
        version = 6,
        exportSchema = false
)
@TypeConverters({CalendarConverter.class, PhoneTypeConverter.class})
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String DB_NAME = "agenda.db";

    private static AgendaDatabase instance;

    public abstract StudentDao getStudentDao();

    public abstract PhoneDao getPhoneDao();

    public static AgendaDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context, AgendaDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(Migrations.MIGRATIONS)
                    .build();
        }
        return instance;
    }

    interface Migrations{
        Migration MIGRATION_1_2 = new Migration(1, 2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE Student ADD COLUMN 'lastName' TEXT");
            }
        };
        Migration MIGRATION_2_3 = new Migration(2, 3) {
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
        };
        Migration MIGRATION_3_4 = new Migration(3, 4) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE Student ADD COLUMN 'createdAt' INTEGER");
            }
        };

        Migration MIGRATION_4_5 = new Migration(4, 5) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE Student ADD COLUMN 'cellPhone' TEXT");
            }
        };

        Migration MIGRATION_5_6 = new Migration(5, 6) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `StudentTemp` (" +
                        "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "`firstName` TEXT, " +
                        "`lastName` TEXT, " +
                        "`email` TEXT, " +
                        "`createdAt` INTEGER" +
                        ")"
                );

                database.execSQL("INSERT INTO StudentTemp (id, firstName, lastName, email, createdAt) " +
                        "SELECT id, firstName, lastName, email, createdAt FROM Student");

                database.execSQL("CREATE TABLE IF NOT EXISTS `Phone` (" +
                        "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "`studentId` INTEGER NOT NULL, " +
                        "`number` TEXT, " +
                        "`type` TEXT" +
                        ")"
                );

                database.execSQL("INSERT INTO Phone (number, studentId, type) " +
                        "SELECT phone, id, 'PHONE' FROM Student");

                database.execSQL("DROP TABLE Student");
                database.execSQL("ALTER TABLE StudentTemp RENAME TO Student");
            }
        };

        Migration[] MIGRATIONS = {
                MIGRATION_1_2,
                MIGRATION_2_3,
                MIGRATION_3_4,
                MIGRATION_4_5,
                MIGRATION_5_6
        };
    }
}
