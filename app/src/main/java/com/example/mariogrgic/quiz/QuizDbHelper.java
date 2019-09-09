package com.example.mariogrgic.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mariogrgic.quiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="SportQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context){
        if (instance == null){
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       this.db = db;

       final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " + CategoriesTable.TABLE_NAME + "( " + CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
       CategoriesTable.COLUMN_NAME + " TEXT " + ")";

       final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
               QuestionTable.TABLE_NAME + " ( " +
               QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
               QuestionTable.COLUMN_QUESTION + " TEXT, " +
               QuestionTable.COLUMN_OPTION1 + " TEXT, " +
               QuestionTable.COLUMN_OPTION2 + " TEXT, " +
               QuestionTable.COLUMN_OPTION3 + " TEXT, " +
               QuestionTable.COLUMN_ANSWER_NR + " INTEGER, " +
               QuestionTable.COLUMN_DIFFICULTY + " TEXT, " +
               QuestionTable.COLUMN_CATEGORY_ID + " INTEGER, " +
               "FOREIGN KEY(" + QuestionTable.COLUMN_CATEGORY_ID + ") REFERENCES " + CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
               ")";

       db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
       db.execSQL(SQL_CREATE_QUESTION_TABLE);
       fillCategoriesTable();
       fillQuestionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
           onCreate(db);
    }



    private void fillCategoriesTable(){
        Category c1 = new Category("NBA");
        addCategory(c1);
        Category c2 = new Category("UFC");
        addCategory(c2);
        Category c3 = new Category("Football");
        addCategory(c3);

    }

    private void addCategory(Category category){
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionTable() {
        Question q1 = new Question("What is the name of the famous player who wore number 23 in Chicago Bulls:","Scottie Pippen","Michael Jordan","Toni Kukoc",2,Question.DIFFICULTY_EASY, Category.NBA);
        addQuestion(q1);
        Question q2 = new Question("Which NBA team has won the most titles:","Los Angeles Lakers","Chicago Bulls","Boston Celtics",3,Question.DIFFICULTY_EASY, Category.NBA);
        addQuestion(q2);
        Question q3 = new Question("Drazen Petrovic played for the Portland Trail Blazers and which other team:","Miami Heat","Orlando Magic","New Jersey Nets",3,Question.DIFFICULTY_EASY, Category.NBA);
        addQuestion(q3);
        Question q4 = new Question("How many team are in the NBA:","10","20","30",3,Question.DIFFICULTY_EASY, Category.NBA);
        addQuestion(q4);
        Question q5 = new Question("NBA stand for: ","National Basketball Association","National Baseball Association","Never Basketball Association",1,Question.DIFFICULTY_EASY, Category.NBA);
        addQuestion(q5);
        Question q6 = new Question("Who is nba champion 2018:","Golden State Warriors","Houston Rockets","Philadelphia 76ers",1,Question.DIFFICULTY_MEDIUM, Category.NBA);
        addQuestion(q6);
        Question q7 = new Question("Who is the first european player to win the regular season MVP:","Giannis Antetokounmpo","Dirk Nowitzki","Tony Parker",2,Question.DIFFICULTY_MEDIUM, Category.NBA);
        addQuestion(q7);
        Question q8 = new Question("Dino Rada played for this nba team:","Atlanta Hawks","Boston Celtics","Memphis Grizzlies",2,Question.DIFFICULTY_MEDIUM, Category.NBA);
        addQuestion(q8);
        Question q9 = new Question("Kobe bryant wore this two numbers in his hole career:","8 and 24","3 and 30","9 and 23",1,Question.DIFFICULTY_MEDIUM, Category.NBA);
        addQuestion(q9);
        Question q10 = new Question("How many finals MVPs does Lebron James have:","0","2","3",3,Question.DIFFICULTY_MEDIUM, Category.NBA);
        addQuestion(q10);
        Question q11 = new Question("In what year was Darko Milicic drafted and when was he picked:","3 pick 2000.","1 pick 2010","2 pick 2003",3,Question.DIFFICULTY_HARD, Category.NBA);
        addQuestion(q11);
        Question q12 = new Question("Magic Johnson and Larry Bird have played in how many finals against each other:","3","4","5",1,Question.DIFFICULTY_HARD, Category.NBA);
        addQuestion(q12);
        Question q13 = new Question("Out of those teams which one has reached the conference final:","Los Angeles Clippers","Memphis Grizzlies","New Orleans Pelicans",2,Question.DIFFICULTY_HARD, Category.NBA);
        addQuestion(q13);
        Question q14 = new Question("Lebron James won his first title against this team:","San Antonio Spurs","Dallas Mavericks","Oklahoma City Thunder",3,Question.DIFFICULTY_HARD, Category.NBA);
        addQuestion(q14);
        Question q15 = new Question("What player is called the 'three peat killer':","Kawhi Leonard","Shaquille O'Neal","Tim Duncan",1,Question.DIFFICULTY_HARD, Category.NBA);
        addQuestion(q15);
        Question q16 = new Question("Who is the most accomplished MMA fighter from Croatia:","Mirko Cro Cop","Igor Pokrajac","Vaso Bakocevic",1,Question.DIFFICULTY_EASY, Category.UFC);
        addQuestion(q16);
        Question q17 = new Question("UFC stand for:","Unlimited Fighting Championship","Ultimate Fire Championship","Ultimate Fighting Championship",3,Question.DIFFICULTY_EASY, Category.UFC);
        addQuestion(q17);
        Question q18 = new Question("Jon Jones is the champion of this division:","Heavyweight","Light Heavyweight","Middleweight",2,Question.DIFFICULTY_EASY, Category.UFC);
        addQuestion(q18);
        Question q19 = new Question("How many times has Stipe Miocic won the UFC title:","1","2","3",2,Question.DIFFICULTY_EASY, Category.UFC);
        addQuestion(q19);
        Question q20 = new Question("Before UFC the most famous MMA organizations which was located in Japan was:","K1","Pride","Dream",2,Question.DIFFICULTY_EASY, Category.UFC);
        addQuestion(q20);
        Question q21 = new Question("Who is the undefeated UFC lightweight champion:","Khabib Nurmagomedov","Tony Ferguson","Dustin Poirier",1,Question.DIFFICULTY_MEDIUM, Category.UFC);
        addQuestion(q21);
        Question q22 = new Question("Who is the first simultaneous two weight UFC champion:","Randy Couture","Conor McGregor","Ronda Rousey",2,Question.DIFFICULTY_MEDIUM, Category.UFC);
        addQuestion(q22);
        Question q23 = new Question("What city is 'the capital city of fighting':","New York","Los Angeles","Las Vegas",3,Question.DIFFICULTY_MEDIUM, Category.UFC);
        addQuestion(q23);
        Question q24 = new Question("Who is the president of the UFC:","Dana White","Lorenzo Fertitta","Nobody",1,Question.DIFFICULTY_MEDIUM, Category.UFC);
        addQuestion(q24);
        Question q25 = new Question("What is the record for most successful title defenses in UFC history:","6","10","11",3,Question.DIFFICULTY_HARD, Category.UFC);
        addQuestion(q25);
        Question q26 = new Question("Which of the following MMA legends never fought in UFC:","Alistair Overeem","Brock Lesnar","Fedor Emelianenko",3,Question.DIFFICULTY_MEDIUM, Category.UFC);
        addQuestion(q26);
        Question q27 = new Question("Which of the following fighter never lost a rematch:","Mirko Cro Cop","Frank Mir","Chuck Liddell",1,Question.DIFFICULTY_HARD, Category.UFC);
        addQuestion(q27);
        Question q28 = new Question("First UFC tournament was won by:","Ken Shamrock","Royce Gracie","Frank Shamrock",2,Question.DIFFICULTY_HARD, Category.UFC);
        addQuestion(q28);
        Question q29 = new Question("UFC 151 was cancelled because this fighter was injured a week before the match:","Michael Bisping","Dan Henderson","BJ Penn",2,Question.DIFFICULTY_HARD, Category.UFC);
        addQuestion(q29);
        Question q30 = new Question("UFC was sold in 2017 for:","100 million dollars","1.5 billion dollars","4.2 billion dollars",3,Question.DIFFICULTY_HARD, Category.UFC);
        addQuestion(q30);
        Question q31 = new Question("How many world cup has Brazil won:","3","4","5",3,Question.DIFFICULTY_EASY, Category.Football);
        addQuestion(q31);
        Question q32 = new Question("What is the name of the famous football club from Split Croatia","Dinamo","Hajduk","Slaven",2,Question.DIFFICULTY_EASY, Category.Football);
        addQuestion(q32);
        Question q33 = new Question("What is the name of the most prestigious club competition:","Champion League","Europa League","UEFA league",1,Question.DIFFICULTY_EASY, Category.Football);
        addQuestion(q33);
        Question q34 = new Question("How many times has Real Madrid won the Champions League:","8","13","20",2,Question.DIFFICULTY_EASY, Category.Football);
        addQuestion(q34);
        Question q35 = new Question("Who won the 2019 ballon d'or award:","Luka Modric","Lionel Messi","Cristiano Ronaldo",1,Question.DIFFICULTY_EASY, Category.Football);
        addQuestion(q35);
        Question q36 = new Question("Cristiano Ronaldo has won this much ballon d'or awards:","2","3","5",3,Question.DIFFICULTY_MEDIUM, Category.Football);
        addQuestion(q36);
        Question q37 = new Question("Who has Croatia beat in the round of 16 in World Cup 2018:","Argentina","Russia","Denmark",3,Question.DIFFICULTY_MEDIUM, Category.Football);
        addQuestion(q37);
        Question q38 = new Question("Who has scored most goals in World Cup history:","Miroslav Klose","Ronaldo","Diego Maradona",1,Question.DIFFICULTY_MEDIUM, Category.Football);
        addQuestion(q38);
        Question q39 = new Question("Which country host the first World Cup and which year was it:","1900. England","1915. USA","1930. Uruguay",3,Question.DIFFICULTY_MEDIUM, Category.Football);
        addQuestion(q39);
        Question q40 = new Question("Which of the following club did Zlatan Ibrahimovic not played for:","Ajax","Bayern Munich","Inter",2,Question.DIFFICULTY_MEDIUM, Category.Football);
        addQuestion(q40);
        Question q41 = new Question("Bayern won the Champions League in 2013 against this club in the finals:","Chelsea","Borussia Dortmund","Juventus",2,Question.DIFFICULTY_HARD, Category.Football);
        addQuestion(q41);
        Question q42 = new Question("Which Croatian player score the decisive penalty twice in world cup 2018:","Ivan Rakitic","Luka Modric","Ivan Perisic",1,Question.DIFFICULTY_HARD, Category.Football);
        addQuestion(q42);
        Question q43 = new Question("In which year did Dinamo Zagreb win the european cup:","1950","1956","1967",3,Question.DIFFICULTY_HARD, Category.Football);
        addQuestion(q43);
        Question q44 = new Question("Liverpool didn't won the English league for how many years:","29","35","40",1,Question.DIFFICULTY_HARD, Category.Football);
        addQuestion(q44);
        Question q45 = new Question("Who is the winner of the first Euro Cup:","France","Soviet Union","Italy",2,Question.DIFFICULTY_HARD, Category.Football);
        addQuestion(q45);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionTable.TABLE_NAME,null, cv);
    }

    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null );

        if(c.moveToFirst()){
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            }while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }while(c.moveToNext());
        }
      c.close();
        return questionList;

    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionTable.COLUMN_CATEGORY_ID + " = ? " + " AND " + QuestionTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }while(c.moveToNext());
        }
        c.close();
        return questionList;

    }
}
