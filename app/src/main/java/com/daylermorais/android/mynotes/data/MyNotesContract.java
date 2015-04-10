package com.daylermorais.android.mynotes.data;

import android.provider.BaseColumns;

public class MyNotesContract {

    // Inner class that defines the table contents of the subject table
    public static final class   SubjectEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "subject";

        // The subject description
        public static final String COLUMN_SUBJECT_DESCRIPTION = "subject_description";

    }

}
