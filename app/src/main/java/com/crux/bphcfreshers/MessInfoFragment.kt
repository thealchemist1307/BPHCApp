package com.crux.bphcfreshers

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MessInfoFragment : Fragment() {
    private var myTTS: TextToSpeech? = null
    private var messInfoButton: FloatingActionButton? = null
    private var mySpeechRec: SpeechRecognizer? = null
    var calendar: Calendar? = null
    var messDB: SQLiteDatabase? = null
    private val MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_messinfo, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messInfoButton = view.findViewById(R.id.messInfoButton)
        messInfoButton?.setOnClickListener(View.OnClickListener {
            checkPermission()
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
            mySpeechRec!!.startListening(intent)
        })
        initializeTextToSpeech()
        initializeSpeechRec()
        createMessDB()
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(context!!,
                        Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity!!, Manifest.permission.RECORD_AUDIO)) {
                createDialog("Permission Needed", "You need to enable microphone for the app to recognize your voice commands!", "OK", "CANCEL")
            } else {
                ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.RECORD_AUDIO), MY_PERMISSIONS_REQUEST_RECORD_AUDIO)
            }
        }
    }

    private fun createDialog(title: String, message: String, positiveText: String, negativeText: String) {
        AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveText) { dialog, which -> ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.RECORD_AUDIO), MY_PERMISSIONS_REQUEST_RECORD_AUDIO) }
                .setNegativeButton(negativeText) { dialog, which -> dialog.dismiss() }
                .create().show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == MY_PERMISSIONS_REQUEST_RECORD_AUDIO) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Permission Denied Try Again", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createMessDB() {
        try {
            messDB = context!!.openOrCreateDatabase("MessMenu", Context.MODE_PRIVATE, null)
            messDB?.execSQL("CREATE TABLE IF NOT EXISTS messmenudata (day INT(1), breakfast VARCHAR, lunch VARCHAR, snacks VARCHAR, dinner VARCHAR)")
            //Sunday Menu
            messDB?.execSQL("INSERT INTO messmenudata (day, breakfast, lunch, snacks, dinner) VALUES (1," +
                    "'Milk, Tea, Coffee, Sweet Corn, Masala Dosa, Sambhar, Chutney, Chole Bhature, Bread Jam'," +
                    "'Sprouts, Roti, Steamed Rice, Curd, Sambhar/Rasam, Papad/Fryums, Chutney, Lemon Rice, Punjabi Dal, Kadi Pakodi, Gajar Methi Matar'," +
                    "'Milk, Tea, Coffee, Poha with Chutney and Bhujia'," +
                    "'Salad Roti, Steamed Rice, Sambhar, Rasam Pudhina Chutney, Pickle veg biryani chicken biryani veg raita dal tadka shahi paneer ice cream')")
            //Monday Menu
            messDB?.execSQL("INSERT INTO messmenudata (day, breakfast, lunch, snacks, dinner) VALUES (2," +
                    "'Milk, Tea, Coffee, Sprouts Wada Sambhar, Chutney, Puri Bhaji, Breadjam, Cornflakes'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Papad/Fryums, Chutney, Tomato Rice, Dal Makhani, Veg Sabzi, Aloo Methi'," +
                    "'Milk, Tea, Coffee Biscuits Samosa Wada Pav Pudhina Chutney'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Pickle, Jeera Rice, Dal Tadka, Rajma Masala, Mix Vegetables')")
            //Tuesday Menu
            messDB?.execSQL("INSERT INTO messmenudata (day, breakfast, lunch, snacks, dinner) VALUES (3," +
                    "'Milk, Tea, Coffee, Pessaratu Upma Ginger Chutney, Gobhi paratha curd pickle bournvita'," +
                    "'Salad, Roti, Steamed Rice, Curd, Sambhar/Rasam, Papad/Fryums, Chutney Arhar Dal chole bhindi fry methi puri boondi raita'," +
                    "'Milk, Tea, Coffee Rusk Pasta Red White Alternative'," +
                    "'Salad, Roti, Steamed Rice, Curd, Sambhar/Rasam, Pickel dal makhani pudina rice veg kofta masala corn ')")
            //Wednesday Menu
            messDB?.execSQL("INSERT INTO messmenudata (day, breakfast, lunch, snacks, dinner) VALUES (4," +
                    "'Milk, Tea, Coffee, sweet corn rava idli sambhar chutney stuffed kulcha curd bread butter boiled egg/banana'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Papad/Fryums, Chutney veg pulao jeera dal fry black chana masala corn palak gulab jamun'," +
                    "'Milk, Tea, Coffee pani puri or dahi bhalla papdi chaat alternate'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Pickle, sambhar/rasam kashmiri pulao dal tadka kadhai paneer butter chciken veg biryani boondi raita')")
            //Thursday Menu
            messDB?.execSQL("INSERT INTO messmenudata (day, breakfast, lunch, snacks, dinner) VALUES (5," +
                    "'Milk, Tea, Coffee, Sprouts masala dosa sambhar chutney poha chocos'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Papad/Fryums, dal khichdi navratan dal aaloo baigan'," +
                    "'Milk, Tea, Coffee cut fruits idli sambhar/ wada sambhar'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Pickle, coriander rice toor dal veg handi dum aloo boondi')")
            //Friday Menu
            messDB?.execSQL("INSERT INTO messmenudata (day, breakfast, lunch, snacks, dinner) VALUES (6," +
                    "'Milk, Tea, Coffee, sweet corn mysore bonda chutney mix paratha pudina chutney'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Papad/Fryums, Chutney jeera rice chana dal tadka rajma masala jeera aloo'," +
                    "'Milk, Tea, Coffee Rusk sandwich with pudina chutney / sevia bath with coconut chutney'," +
                    "'Salad, Roti, Steamed Rice, Curd Sambhar/Rasam, Pickle, dal makhani egg bhurji / egg curry aloo 65')")
            //Saturday Menu
            messDB?.execSQL("INSERT INTO messmenudata (day, breakfast, lunch, snacks, dinner) VALUES (7," +
                    "'milk tea coffee sprouts pongal chutney sandwich pancakes boost'," +
                    "'salad roti steamed rice curd sambhar / rasam papad/fryums chutney onion garlic rice achari dal chole ajwain puri boondi raita'," +
                    "'milk tea coffee biscuits noodles / set dosa tomato chutney'," +
                    "'salad roti steamed rice curd sambhar / rasam beg fried rice navratan dal tadka manchurian black chana dry cut fruits')")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initializeSpeechRec() {
        if (SpeechRecognizer.isRecognitionAvailable(context)) {
            mySpeechRec = SpeechRecognizer.createSpeechRecognizer(context)
            mySpeechRec?.setRecognitionListener(object : RecognitionListener {
                override fun onReadyForSpeech(params: Bundle) {}
                override fun onBeginningOfSpeech() {}
                override fun onRmsChanged(rmsdB: Float) {}
                override fun onBufferReceived(buffer: ByteArray) {}
                override fun onEndOfSpeech() {}
                override fun onError(error: Int) {}
                override fun onResults(results: Bundle) {
                    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS") val resultsArr: List<String> = results.getStringArrayList(
                            SpeechRecognizer.RESULTS_RECOGNITION
                    )
                    processResult(resultsArr[0])
                }

                override fun onPartialResults(partialResults: Bundle) {}
                override fun onEvent(eventType: Int, params: Bundle) {}
            })
        }
    }

    private fun processResult(command: String) {
        @Suppress("NAME_SHADOWING") var command = command
        val calendar = Calendar.getInstance()
        val curr_day = calendar[Calendar.DAY_OF_WEEK]
        val c = messDB!!.rawQuery("SELECT * FROM messmenudata", null)
        val dayIndex = c.getColumnIndex("day")
        val breakfastIndex = c.getColumnIndex("breakfast")
        val lunchIndex = c.getColumnIndex("lunch")
        val snackIndex = c.getColumnIndex("snacks")
        val dinnerIndex = c.getColumnIndex("dinner")
        command = command.toLowerCase()
        Toast.makeText(context, command, Toast.LENGTH_SHORT).show()
        //        if (command == "today" || command == "today's") {
        if (command.indexOf("breakfast") != -1) {
            if (c.moveToFirst()) {
                do {
                    if (c.getInt(dayIndex) == curr_day) {
                        convertTextToSpeech(c.getString(breakfastIndex))
                    }
                } while (c.moveToNext())
            }
            c.close()
        } else if (command.indexOf("lunch") != -1) {
            if (c.moveToFirst()) {
                do {
                    if (c.getInt(dayIndex) == curr_day) {
                        convertTextToSpeech(c.getString(lunchIndex))
                    }
                } while (c.moveToNext())
            }
            c.close()
        } else if (command.indexOf("snacks") != -1) {
            if (c.moveToFirst()) {
                do {
                    if (c.getInt(dayIndex) == curr_day) {
                        convertTextToSpeech(c.getString(snackIndex))
                    }
                } while (c.moveToNext())
            }
            c.close()
        } else if (command.indexOf("dinner") != -1) {
            if (c.moveToFirst()) {
                do {
                    if (c.getInt(dayIndex) == curr_day) {
                        convertTextToSpeech(c.getString(dinnerIndex))
                    }
                } while (c.moveToNext())
            }
            c.close()
        } else {
            convertTextToSpeech("Wrong Input")
        }
        //        }
    }

    fun initializeTextToSpeech() {
        myTTS = TextToSpeech(context, OnInitListener {
            if (myTTS!!.engines.size == 0) {
                Toast.makeText(context, "No TTS Engine Found", Toast.LENGTH_SHORT).show()
            } else {
                myTTS!!.language = Locale.ENGLISH
                convertTextToSpeech("Which meal's menu would u like to know about today?")
            }
        })
    }

    private fun convertTextToSpeech(text: String) {
        myTTS!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onPause() {
        super.onPause()
        myTTS!!.shutdown()
        //        messDB.execSQL("DROP TABLE IF EXISTS messmenudata");
    }
}