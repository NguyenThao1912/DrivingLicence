package com.example.drivinglicense.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.drivinglicense.Utils.Constants;
import com.example.drivinglicense.global.AppGlobal;
import com.example.drivinglicense.model.Licence;
import com.example.drivinglicense.model.NoticeBoard;
import com.example.drivinglicense.model.NoticeBoardType;
import com.example.drivinglicense.model.Question;
import com.example.drivinglicense.model.Tip;
import com.example.drivinglicense.model.TipCategory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static final String TAG = "DBManager";
    public static DBManager instance;
    private final Context context;
    private SQLiteDatabase database = null;
    private String fullPath = null;

    public DBManager(Context context) {
        this.context = context;
        String path = Environment.getDataDirectory().getPath() +
                File.separator + "data" +
                File.separator + context.getPackageName() +
                File.separator + "db";
        new File(path).mkdir();
        fullPath = path + File.separator + object.DB_NAME;
        try {
            copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DBManager getInstance(Context context) {
        if (instance == null) {
            instance = new DBManager(context);
        }
        return instance;
    }

    private void copyDataBase() throws IOException {
        InputStream input = context.getAssets().open(object.DB_NAME);
        if (new File(fullPath).exists()) {
            return;
        }
        FileOutputStream out = new FileOutputStream(fullPath);
        byte[] b = new byte[1024];
        int le = input.read(b);
        while (le >= 0) {
            out.write(b, 0, le);
            le = input.read(b);
        }
        input.close();
        out.close();
        Log.d("", "\npath" + fullPath + "\n");
    }

    private void openDatabase() {
        if (database == null || !database.isOpen()) {
            database = SQLiteDatabase.openDatabase(
                    fullPath, null,
                    SQLiteDatabase.OPEN_READWRITE
            );
        }
    }

    private void closeDatabase() {
        if (database != null && database.isOpen()) {
            database.close();
            database = null;
        }
    }

    //get 600 question
    public void getAllQuestion() {
        openDatabase();
        Cursor cursor = database.query("ZQUESTION", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int stt = cursor.getInt(0);
            String question = cursor.getString(1);
            String pathImg = cursor.getString(2);
            String option1 = cursor.getString(3);
            String option2 = cursor.getString(4);
            String option3 = cursor.getString(5);
            String option4 = cursor.getString(6);
            String dapAn = cursor.getString(7);
            Log.d("", "Câu hỏi " + stt + ": " + question + "\n");
            Log.d("", "\tA:" + option1 + ":\n");
            Log.d("", "\tB:" + option2 + ":\n");
            Log.d("", "\tC:" + option3 + ":\n");
            Log.d("", "\tD:" + option4 + ":\n");
            Log.d("", "\tĐáp án:" + dapAn + ":\n");
        }
        closeDatabase();
    }

    //Get all licence data by licence id
    public List<Licence> getLicenceData(int id) {
        openDatabase();
        List<Licence> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + Constants.TABLE_LICENSE + " WHERE Z_PK = " + id, null);
        while (cursor.moveToNext()) {
            int licence_id = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_LICENCE[0]));
            int licence_ent = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_LICENCE[1]));
            int licence_opt = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_LICENCE[2]));
            int licence_correct_ques = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_LICENCE[3]));
            int licence_numOf_ques = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_LICENCE[4]));
            int licence_numOf_test = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_LICENCE[5]));
            float licence_duration = cursor.getFloat(cursor.getColumnIndex(Constants.COLUMN_LICENCE[6]));
            String licence_content = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_LICENCE[7]));
            String licence_desc = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_LICENCE[8]));
            String licence_name = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_LICENCE[9]));

            Licence licence = new Licence(licence_id, licence_ent, licence_opt, licence_correct_ques, licence_numOf_ques, licence_numOf_test,
                    licence_duration, licence_content, licence_desc, licence_name);

            list.add(licence);
        }
        closeDatabase();
        return list;
    }

    public List<Question> getListQuestion_By_TestIndex_AND_LicenceName(int test_index, String licence_name) {
        String Query = "Select " +
                " ZQUESTION.Z_PK," +
                " ZQUESTION.ZQUESTIONCONTENT," +
                " ZQUESTION.ZIMAGE," +
                " ZQUESTION.ZOPTION1," +
                " ZQUESTION.ZOPTION2," +
                " ZQUESTION.ZOPTION3," +
                " ZQUESTION.ZOPTION4," +
                " ZQUESTION.ZANSWERS," +
                " ZQUESTION.ZANSWERDESC," +
                " ZQUESTION.ZQUESTIONDIE," +
                " ZQUESTION.ZLEARNED," +
                " ZQUESTION.ZMARKED," +
                " ZQUESTION.ZWRONG " +
                " FROM " + Constants.TABLE_TEST_QUEST +
                " JOIN " + Constants.TABLE_TEST + " on ZTESTQUEST.TESTID = ZTEST.IDTEST " +
                " JOIN " + Constants.TABLE_QUESTION + " on ZQUESTION.Z_PK = ZTESTQUEST.ZQUESTIONID " +
                " WHERE ZTEST.CLASS_LICENSE = ? and ZTEST.NAME_TEST = ?";
        String[] args = {licence_name, String.valueOf(test_index)};
        List<Question> questions = new ArrayList<>();
        try {
            openDatabase();
            Cursor cursor = database.rawQuery(Query, args);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int question_id = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_QUESTION[0]));
                    String question_content = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_QUESTION[1]));
                    String question_image = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_QUESTION[2]));
                    String question_option_1 = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_QUESTION[3]));
                    String question_option_2 = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_QUESTION[4]));
                    String question_option_3 = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_QUESTION[5]));
                    String question_option_4 = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_QUESTION[6]));
                    String question_answer_content = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_QUESTION[7]));
                    int question_answer_index = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_QUESTION[8]));
                    int question_die = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_QUESTION[20]));
                    int question_learned = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_QUESTION[10]));
                    int question_marked = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_QUESTION[11]));
                    int question_wrong = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_QUESTION[12]));
                    cursor.moveToNext();
                    Question myquestion = new Question(question_id,
                            question_content,
                            question_image,
                            question_option_1,
                            question_option_2,
                            question_option_3,
                            question_option_4,
                            question_answer_content,
                            question_answer_index,
                            question_die,
                            question_learned,
                            question_marked,
                            question_wrong);
                    questions.add(myquestion);
                }
                closeDatabase();
            }
            return questions;
        } catch (Exception e) {
            Log.d("", "" + e.getMessage());
            return questions;
        }
    }

    //TODO lấy danh sách loại biển báo
    public List<NoticeBoardType> getListNoticeBoardType(){
        List<NoticeBoardType> list = new ArrayList<>();

        openDatabase();
        Cursor cursor = database.query("TABLE_NOTICE_BOARD_TYPE", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            NoticeBoardType noticeBoardType = new NoticeBoardType(
                    cursor.getInt(0),
                    cursor.getString(1)
            );

            list.add(noticeBoardType);
        }
        closeDatabase();

        return list;
    }

    //TODO lấy danh sách tất cả các biển báo
    public List<NoticeBoard> getListNoticeBoard(){
        List<NoticeBoard> list = new ArrayList<>();
        openDatabase();
        Cursor cursor = database.query("TABLE_NOTICE_BOARD", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            NoticeBoard noticeBoard = new NoticeBoard(
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(4),
                    cursor.getString(5)
            );

            list.add(noticeBoard);
        }
        closeDatabase();

        return list;
    }

    //TODO lấy danh sách mẹo
    public List<Tip> getListTip(){
        List<Tip> list = new ArrayList<>();

        openDatabase();
        Cursor cursor = database.query("ZTIP", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Tip tip = new Tip(
                    cursor.getInt(3),
                    cursor.getString(4)
            );

            list.add(tip);
        }
        closeDatabase();

        return list;
    }

    //TODO lấy danh sách thể loại mẹo
    public List<TipCategory> getListTipCategory(){
        List<TipCategory> list = new ArrayList<>();

        openDatabase();
        Cursor cursor = database.query("ZTIPCATEGORY", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            TipCategory tipCategory = new TipCategory(
                    cursor.getInt(3),
                    cursor.getInt(0),
                    cursor.getString(5)
            );

            list.add(tipCategory);
        }
        closeDatabase();

        return list;
    }
    public Licence get_Licence_By_ID(int ID)
    {
        try
        {
            Licence licence = new Licence();
            openDatabase();
            Cursor cursor = database.query(Constants.TABLE_LICENSE, null, "Z_PK = ?", new String[]{String.valueOf(ID)}, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                    licence.setZ_PK(ID);
                    licence.setZ_ENT(cursor.getInt(cursor.getColumnIndex("Z_ENT")));
                    licence.setZ_OPT(cursor.getInt(cursor.getColumnIndex("Z_OPT")));
                    licence.setZ_NUMBER_OF_CORRECT_QUESTION(cursor.getInt(cursor.getColumnIndex("ZNUMBEROFCORRECTQUESTION")));
                    licence.setZ_NUMBER_OF_QUESTION(cursor.getInt(cursor.getColumnIndex("ZNUMBEROFQUESTION")));
                    licence.setZ_NUMBER_OF_TEST(cursor.getInt(cursor.getColumnIndex("ZNUMBEROFTEST")));
                    licence.setZDURATION(cursor.getFloat(cursor.getColumnIndex("ZDURATION")));
                    licence.setZCONTENT(cursor.getString(cursor.getColumnIndex("ZCONTENT")));
                    licence.setZDESC(cursor.getString(cursor.getColumnIndex("ZDESC")));
                    licence.setZNAME(cursor.getString(cursor.getColumnIndex("ZNAME")));
            }
            closeDatabase();
            return licence;
        }
        catch (Exception e)
        {
            Log.d(TAG, "get_Licence_By_ID: " + e.getMessage());
            return null;
        }
    }
    interface object {
        String DB_NAME = "600question.db";
    }

}