package fr.wildcodeschool.roomreservation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PersonCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_create);

        Button btCreate = findViewById(R.id.button_create_person);
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText etFirstname = findViewById(R.id.edit_firstname);
                EditText etLastname = findViewById(R.id.edit_lastname);

                String firstname = etFirstname.getText().toString();
                String lastname = etLastname.getText().toString();
                // Nous initialisons notre accès à la base de données :

                DbHelper mDbHelper = new DbHelper(PersonCreateActivity.this);
                //Nous ouvrons un accès en écriture :
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                // Une "carte" vide de notre objet à insérer est créé :
                ContentValues person = new ContentValues();
                // Ici sont associées les valeurs aux colonnes de la table :
                person.put(DBContract.PersonEntry.COLUMN_NAME_FIRSTNAME, firstname);
                person.put(DBContract.PersonEntry.COLUMN_NAME_LASTNAME, lastname);
                // Une fois remplie, la "carte" de la personne est insérée dans la table correspondante. Un identifiant unique est alors retourné :
                long newPersonId = db.insert(DBContract.PersonEntry.TABLE_NAME, null, person);
                // Le Toast nous permet juste de vérifier que le processus c'est bien déroulé.
                Toast.makeText(PersonCreateActivity.this, String.valueOf(newPersonId), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PersonCreateActivity.this, MainActivity.class);
                PersonCreateActivity.this.startActivity(intent);
            }
        });
    }

    private void addPersonToDB(String firstname, String lastname) {
        // TODO : add person into database

    }
}
