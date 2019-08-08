package com.crux.bphcfreshers;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class MessInfoFragment extends Fragment {

    private TextToSpeech myTTS;
    private FloatingActionButton messInfoButton;
    private SpeechRecognizer mySpeechRec;
    Calendar calendar;
    SQLiteDatabase messDB;
    private int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messinfo, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        messInfoButton = view.findViewById(R.id.messInfoButton);

        messInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                mySpeechRec.startListening(intent);
            }
        });
        initializeTextToSpeech();
        initializeSpeechRec();
        createMessDB();
    }

    private void checkPermission() {

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {}
        else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.RECORD_AUDIO)) {
                createDialog("Permission Needed", "You need to enable microphone for the app to recognize your voice commands!", "OK", "CANCEL");
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
            }
        }
    }

    private void createDialog(String title, String message, String positiveText, String negativeText) {

        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
                    }
                })
                .setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_RECORD_AUDIO) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Permission Denied Try Again", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createMessDB() {

        try {

            messDB = getContext().openOrCreateDatabase("MessMenu", MODE_PRIVATE, null );
            messDB.execSQL("CREATE TABLE IF NOT EXISTS messmenudata (day INT(1), breakfast VARCHAR, lunch VARCHAR, snacks VARCHAR, dinner VARCHAR)");
//Sunday Menu
            messDB.execSQL("INSERT INTO messmenudata (day, breakfast, lunch, snacks, dinner) VALUES (1," +
                    "'Milk, Tea, Coffee, Sweet Corn, Masala Dosa, Sambhar, Chutney, Chole Bhature, Bread Jam'," +
                    "'Sprouts, Roti, Steamed Rice, Curd, Sambhar/Rasam, Papad/Fryums, Chutney, Lemon Rice, Punjabi Dal, Kadi Pakodi, Gajar Methi Matar'," +
                    "'Milk, Tea, Coffee, Poha with Chutney and Bhujia'," +
                    "'Salad Roti, Steamed Rice, Sambhar, Rasam Pudhina Chutney, Pickle veg biryani chicken biryani veg raita dal tadka shahi paneer ice cream')");
            //Monday Menu
            messDB.execSQL("INSERT INTO messmenudata (day, breakfast, lunch, snacks, dinner) VALUES (2," +
                    "'Milk, Tea, Coffee, Sprouts Wada Sambhar, Chutney, Puri Bhaji, Breadjam, Cornflakes'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Papad/Fryums, Chutney, Tomato Rice, Dal Makhani, Veg Sabzi, Aloo Methi'," +
                    "'Milk, Tea, Coffee Biscuits Samosa Wada Pav Pudhina Chutney'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Pickle, Jeera Rice, Dal Tadka, Rajma Masala, Mix Vegetables')");
            //Tuesday Menu
            messDB.execSQL("INSERT INTO messmenudata (day, breakfast, lunch, snacks, dinner) VALUES (3," +
                    "'Milk, Tea, Coffee, Pessaratu Upma Ginger Chutney, Gobhi paratha curd pickle bournvita'," +
                    "'Salad, Roti, Steamed Rice, Curd, Sambhar/Rasam, Papad/Fryums, Chutney Arhar Dal chole bhindi fry methi puri boondi raita'," +
                    "'Milk, Tea, Coffee Rusk Pasta Red White Alternative'," +
                    "'Salad, Roti, Steamed Rice, Curd, Sambhar/Rasam, Pickel dal makhani pudina rice veg kofta masala corn ')");
            //Wednesday Menu
            messDB.execSQL("INSERT INTO messmenudata (day, breakfast, lunch, snacks, dinner) VALUES (4," +
                    "'Milk, Tea, Coffee, sweet corn rava idli sambhar chutney stuffed kulcha curd bread butter boiled egg/banana'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Papad/Fryums, Chutney veg pulao jeera dal fry black chana masala corn palak gulab jamun'," +
                    "'Milk, Tea, Coffee pani puri or dahi bhalla papdi chaat alternate'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Pickle, sambhar/rasam kashmiri pulao dal tadka kadhai paneer butter chciken veg biryani boondi raita')");
            //Thursday Menu
            messDB.execSQL("INSERT INTO messmenudata (day, breakfast, lunch, snacks, dinner) VALUES (5," +
                    "'Milk, Tea, Coffee, Sprouts masala dosa sambhar chutney poha chocos'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Papad/Fryums, dal khichdi navratan dal aaloo baigan'," +
                    "'Milk, Tea, Coffee cut fruits idli sambhar/ wada sambhar'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Pickle, coriander rice toor dal veg handi dum aloo boondi')");
            //Friday Menu
            messDB.execSQL("INSERT INTO messmenudata (day, breakfast, lunch, snacks, dinner) VALUES (6," +
                    "'Milk, Tea, Coffee, sweet corn mysore bonda chutney mix paratha pudina chutney'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Papad/Fryums, Chutney jeera rice chana dal tadka rajma masala jeera aloo'," +
                    "'Milk, Tea, Coffee Rusk sandwich with pudina chutney / sevia bath with coconut chutney'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Pickle, dal makhani egg bhurji / egg curry aloo 65')");
            //Saturday Menu
            messDB.execSQL("INSERT INTO messmenudata (day, breakfast, lunch, snacks, dinner) VALUES (7," +
                    "'milk tea coffee sprouts pongal chutney sandwich pancakes boost'," +
                    "'salad roti steamed rice curd sambhar / rasam papad/fryums chutney onion garlic rice achari dal chole ajwain puri boondi raita'," +
                    "'milk tea coffee biscuits noodles / set dosa tomato chutney'," +
                    "'salad roti steamed rice curd sambhar / rasam beg fried rice navratan dal tadka manchurian black chana dry cut fruits')");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initializeSpeechRec() {
        if (SpeechRecognizer.isRecognitionAvailable(getContext())) {
            mySpeechRec = SpeechRecognizer.createSpeechRecognizer(getContext());
            mySpeechRec.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {
                }

                @Override
                public void onBeginningOfSpeech() {
                }

                @Override
                public void onRmsChanged(float rmsdB) {

                }

                @Override
                public void onBufferReceived(byte[] buffer) {

                }

                @Override
                public void onEndOfSpeech() {
                }

                @Override
                public void onError(int error) {

                }

                @Override
                public void onResults(Bundle results) {
                    List<String> resultsArr = results.getStringArrayList(
                            SpeechRecognizer.RESULTS_RECOGNITION
                    );
                    processResult(resultsArr.get(0));
                }

                @Override
                public void onPartialResults(Bundle partialResults) {

                }

                @Override
                public void onEvent(int eventType, Bundle params) {

                }
            });
        }
    }

    private void processResult(String command) {

        Calendar calendar = Calendar.getInstance();
        int curr_day = calendar.get(Calendar.DAY_OF_WEEK);

        Cursor c = messDB.rawQuery("SELECT * FROM messmenudata", null);

        int dayIndex = c.getColumnIndex("day");
        int breakfastIndex = c.getColumnIndex("breakfast");
        int lunchIndex = c.getColumnIndex("lunch");
        int snackIndex = c.getColumnIndex("snacks");
        int dinnerIndex = c.getColumnIndex("dinner");

        command = command.toLowerCase();

        Toast.makeText(getContext(), command, Toast.LENGTH_SHORT).show();
//        if (command == "today" || command == "today's") {
        if (command.indexOf("breakfast") != -1) {
            if (c.moveToFirst()) {
                do{
                    if (c.getInt(dayIndex) == (curr_day)) {
                        convertTextToSpeech(c.getString(breakfastIndex));
                    }
                } while (c.moveToNext());
            }
            c.close();
        } else if (command.indexOf("lunch") != -1) {
            if (c.moveToFirst()) {
                do{
                    if (c.getInt(dayIndex) == (curr_day)) {
                        convertTextToSpeech(c.getString(lunchIndex));
                    }
                } while (c.moveToNext());
            }
            c.close();
        } else if (command.indexOf("snacks") != -1) {
            if (c.moveToFirst()) {
                do{
                    if (c.getInt(dayIndex) == (curr_day)) {
                        convertTextToSpeech(c.getString(snackIndex));
                    }
                } while (c.moveToNext());
            }
            c.close();
        } else if (command.indexOf("dinner") != -1) {
            if (c.moveToFirst()) {
                do{
                    if (c.getInt(dayIndex) == (curr_day)) {
                        convertTextToSpeech(c.getString(dinnerIndex));
                    }
                } while (c.moveToNext());
            }
            c.close();
        } else {
            convertTextToSpeech("Wrong Input");
        }
//        }

    }

    public void initializeTextToSpeech() {
        myTTS = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (myTTS.getEngines().size() == 0) {
                    Toast.makeText(getContext(), "No TTS Engine Found", Toast.LENGTH_SHORT).show();
                } else {
                    myTTS.setLanguage(Locale.ENGLISH);
                    convertTextToSpeech("Which meal's menu would u like to know about today?");
                }
            }
        });
    }

    private void convertTextToSpeech(String text) {
        myTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null,null);
    }

    @Override
    public void onPause() {
        super.onPause();
        myTTS.shutdown();
//        messDB.execSQL("DROP TABLE IF EXISTS messmenudata");
    }
}