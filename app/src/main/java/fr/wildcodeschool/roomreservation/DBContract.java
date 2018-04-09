package fr.wildcodeschool.roomreservation;

import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by wilder on 09/04/18.
 */

public class DBContract implements BaseColumns {

    public static class PersonEntry implements BaseColumns {
        public static final String TABLE_NAME = "Person";
        public static final String COLUMN_NAME_LASTNAME = "lastname";
        public static final String COLUMN_NAME_FIRSTNAME = "firstname";
    }
    public static class RoomEntry implements BaseColumns {
        public static final String TABLE_ROOM = "Reservation";
        public static final String COLUMN_NAME_ROOM ="Room";
    }

}


