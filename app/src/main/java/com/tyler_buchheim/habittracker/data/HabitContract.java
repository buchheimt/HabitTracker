package com.tyler_buchheim.habittracker.data;

import android.provider.BaseColumns;

public final class HabitContract {

    private HabitContract() {}

    public static final class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habits";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABIT_NAME = "name";
        public final static String COLUMN_HABIT_COMMENT = "comment";
        public final static String COLUMN_HABIT_COUNT = "count";
    }

}
