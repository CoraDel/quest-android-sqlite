package fr.wildcodeschool.roomreservation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class PersonListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);

        ArrayList<PersonModel> personModels = loadPersonsFromDB();

        PersonAdapter adapter = new PersonAdapter(this, 0, personModels);
        ListView lvListPerson = findViewById(R.id.list_person);
        lvListPerson.setAdapter(adapter);
    }

    private ArrayList<PersonModel> loadPersonsFromDB() {
        ArrayList<PersonModel> personModels = new ArrayList<>();

        // Tu initialises l'accès à la base de données :
        DbHelper mDbHelper = new DbHelper(PersonListActivity.this);
        // Cette fois, tu ouvres un accès en lecture :
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //Tu délimites dans une "projection" les colonnes de la table que tu vas récupérer :

        String[] projection = {
                DBContract.PersonEntry._ID,
                DBContract.PersonEntry.COLUMN_NAME_FIRSTNAME,
                DBContract.PersonEntry.COLUMN_NAME_LASTNAME
        };
        // Ici, c'est la requête SQL qui va sélectionner tous les résultats de la table, en fonction des colonnes de la "projection". Les résultats sont stockés dans un Cursor :
        Cursor cursor = db.query(
                DBContract.PersonEntry.TABLE_NAME,
                projection,
                null, null, null, null, null
        );
        /*
        Toutes les valeurs à null sont des paramètres qui pourraient être utile en d'autres circonstances.
        Tu peux voir à quoi ils servent dans le lien en ressource. Tu peux, par exemple, trier tes
        résultats ou les filtrer, comme tu le ferais avec une requête SQL classique.
         */
        /*
        Ici tu parcours chaque ligne de la table stockée dans le Cursor, et pour chacunes
        d'entres-elles tu récupères les valeurs des colonnes dont tu as besoin.
        Enfin, tu créés une instance de la classe PersonModel, pour ensuite l'ajouter à la
        liste qui sera plus tard envoyée à l'Adapter :
         */
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.PersonEntry._ID));
            String firstname = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.PersonEntry.COLUMN_NAME_FIRSTNAME));
            String lastname = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.PersonEntry.COLUMN_NAME_LASTNAME));

            PersonModel personModel = new PersonModel(id, firstname, lastname);
            personModels.add(personModel);
        }
        // Enfin, une fois le Cursor parcouru, on le ferme :
        cursor.close();

        return personModels;
    }
}
