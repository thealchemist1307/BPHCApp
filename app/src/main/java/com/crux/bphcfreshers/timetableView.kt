package com.crux.bphcfreshers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.crux.bphcfreshers.timetableView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class timetableView : AppCompatActivity() {
    private var mStorageRef: StorageReference? = null
    var Filename = "timetabledata.csv"
    private val mTimeTableDBRef: StorageReference? = null
    var file: Uri? = null
    var buttonsArray = ArrayList<ArrayList<Button>>(6)
    //    private void exportTheDB() throws IOException {
//
//        Cursor c = timeTableDB.rawQuery("SELECT * FROM timetabledata", null);
//
//        int courseIndex = c.getColumnIndex("course");
//        int classroomIndex = c.getColumnIndex("classroom");
//        int dayIndex = c.getColumnIndex("day");
//        int hourIndex = c.getColumnIndex("hour");
//        FileOutputStream out= openFileOutput(Filename, Context.MODE_PRIVATE);
//
//        if (c.moveToFirst()) {
//            do {
//                String entry = c.getString(courseIndex) + "," + c.getString(classroomIndex) + "," + c.getInt(dayIndex) + "," + c.getInt(hourIndex) + "\n";
//                try {
//                    out.write(entry.getBytes());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            } while (c.moveToNext());
//        }
//        c.close();
//        out.close();
//        uploadFile();
//    }
    fun createButtonsArray() {
        buttonsArray.add(ArrayList(9))
        buttonsArray[0].add(findViewById<View>(R.id.ttv_addButton1_1) as Button)
        buttonsArray[0].add(findViewById<View>(R.id.ttv_addButton1_2) as Button)
        buttonsArray[0].add(findViewById<View>(R.id.ttv_addButton1_3) as Button)
        buttonsArray[0].add(findViewById<View>(R.id.ttv_addButton1_4) as Button)
        buttonsArray[0].add(findViewById<View>(R.id.ttv_addButton1_5) as Button)
        buttonsArray[0].add(findViewById<View>(R.id.ttv_addButton1_6) as Button)
        buttonsArray[0].add(findViewById<View>(R.id.ttv_addButton1_7) as Button)
        buttonsArray[0].add(findViewById<View>(R.id.ttv_addButton1_8) as Button)
        buttonsArray[0].add(findViewById<View>(R.id.ttv_addButton1_9) as Button)
        buttonsArray[0].add(findViewById<View>(R.id.ttv_addButton1_10) as Button)
        buttonsArray.add(ArrayList(9))
        buttonsArray[1].add(findViewById<View>(R.id.ttv_addButton2_1) as Button)
        buttonsArray[1].add(findViewById<View>(R.id.ttv_addButton2_2) as Button)
        buttonsArray[1].add(findViewById<View>(R.id.ttv_addButton2_3) as Button)
        buttonsArray[1].add(findViewById<View>(R.id.ttv_addButton2_4) as Button)
        buttonsArray[1].add(findViewById<View>(R.id.ttv_addButton2_5) as Button)
        buttonsArray[1].add(findViewById<View>(R.id.ttv_addButton2_6) as Button)
        buttonsArray[1].add(findViewById<View>(R.id.ttv_addButton2_7) as Button)
        buttonsArray[1].add(findViewById<View>(R.id.ttv_addButton2_8) as Button)
        buttonsArray[1].add(findViewById<View>(R.id.ttv_addButton2_9) as Button)
        buttonsArray[1].add(findViewById<View>(R.id.ttv_addButton2_10) as Button)
        buttonsArray.add(ArrayList(9))
        buttonsArray[2].add(findViewById<View>(R.id.ttv_addButton3_1) as Button)
        buttonsArray[2].add(findViewById<View>(R.id.ttv_addButton3_2) as Button)
        buttonsArray[2].add(findViewById<View>(R.id.ttv_addButton3_3) as Button)
        buttonsArray[2].add(findViewById<View>(R.id.ttv_addButton3_4) as Button)
        buttonsArray[2].add(findViewById<View>(R.id.ttv_addButton3_5) as Button)
        buttonsArray[2].add(findViewById<View>(R.id.ttv_addButton3_6) as Button)
        buttonsArray[2].add(findViewById<View>(R.id.ttv_addButton3_7) as Button)
        buttonsArray[2].add(findViewById<View>(R.id.ttv_addButton3_8) as Button)
        buttonsArray[2].add(findViewById<View>(R.id.ttv_addButton3_9) as Button)
        buttonsArray[2].add(findViewById<View>(R.id.ttv_addButton3_10) as Button)
        buttonsArray.add(ArrayList(9))
        buttonsArray[3].add(findViewById<View>(R.id.ttv_addButton4_1) as Button)
        buttonsArray[3].add(findViewById<View>(R.id.ttv_addButton4_2) as Button)
        buttonsArray[3].add(findViewById<View>(R.id.ttv_addButton4_3) as Button)
        buttonsArray[3].add(findViewById<View>(R.id.ttv_addButton4_4) as Button)
        buttonsArray[3].add(findViewById<View>(R.id.ttv_addButton4_5) as Button)
        buttonsArray[3].add(findViewById<View>(R.id.ttv_addButton4_6) as Button)
        buttonsArray[3].add(findViewById<View>(R.id.ttv_addButton4_7) as Button)
        buttonsArray[3].add(findViewById<View>(R.id.ttv_addButton4_8) as Button)
        buttonsArray[3].add(findViewById<View>(R.id.ttv_addButton4_9) as Button)
        buttonsArray[3].add(findViewById<View>(R.id.ttv_addButton4_10) as Button)
        buttonsArray.add(ArrayList(9))
        buttonsArray[4].add(findViewById<View>(R.id.ttv_addButton5_1) as Button)
        buttonsArray[4].add(findViewById<View>(R.id.ttv_addButton5_2) as Button)
        buttonsArray[4].add(findViewById<View>(R.id.ttv_addButton5_3) as Button)
        buttonsArray[4].add(findViewById<View>(R.id.ttv_addButton5_4) as Button)
        buttonsArray[4].add(findViewById<View>(R.id.ttv_addButton5_5) as Button)
        buttonsArray[4].add(findViewById<View>(R.id.ttv_addButton5_6) as Button)
        buttonsArray[4].add(findViewById<View>(R.id.ttv_addButton5_7) as Button)
        buttonsArray[4].add(findViewById<View>(R.id.ttv_addButton5_8) as Button)
        buttonsArray[4].add(findViewById<View>(R.id.ttv_addButton5_9) as Button)
        buttonsArray[4].add(findViewById<View>(R.id.ttv_addButton5_10) as Button)
        buttonsArray.add(ArrayList(9))
        buttonsArray[5].add(findViewById<View>(R.id.ttv_addButton6_1) as Button)
        buttonsArray[5].add(findViewById<View>(R.id.ttv_addButton6_2) as Button)
        buttonsArray[5].add(findViewById<View>(R.id.ttv_addButton6_3) as Button)
        buttonsArray[5].add(findViewById<View>(R.id.ttv_addButton6_4) as Button)
        buttonsArray[5].add(findViewById<View>(R.id.ttv_addButton6_5) as Button)
        buttonsArray[5].add(findViewById<View>(R.id.ttv_addButton6_6) as Button)
        buttonsArray[5].add(findViewById<View>(R.id.ttv_addButton6_7) as Button)
        buttonsArray[5].add(findViewById<View>(R.id.ttv_addButton6_8) as Button)
        buttonsArray[5].add(findViewById<View>(R.id.ttv_addButton6_9) as Button)
        buttonsArray[5].add(findViewById<View>(R.id.ttv_addButton6_10) as Button)
    }

    fun createAlert(tempButton: Button, myDB: SQLiteDatabase?, day: Int, hour: Int) {
        val input = arrayOfNulls<EditText>(2)
        val textfields = arrayOfNulls<TextView>(2)
        val input_Text = arrayOfNulls<String>(2)
        val builder = AlertDialog.Builder(this@timetableView)
        val linearLayout = LinearLayout(this@timetableView)
        linearLayout.orientation = LinearLayout.VERTICAL
        val sublinearLayout1 = LinearLayout(this@timetableView)
        sublinearLayout1.orientation = LinearLayout.HORIZONTAL
        textfields[0] = TextView(applicationContext)
        textfields[0]!!.text = "Course:"
        textfields[0]!!.textSize = 20f
        textfields[0]!!.setPadding(10, 10, 20, 10)
        input[0] = EditText(applicationContext)
        input[0]!!.width = 500
        sublinearLayout1.addView(textfields[0])
        sublinearLayout1.addView(input[0])
        val sublinearLayout2 = LinearLayout(this@timetableView)
        sublinearLayout2.orientation = LinearLayout.HORIZONTAL
        textfields[1] = TextView(applicationContext)
        textfields[1]!!.text = "Class:"
        textfields[1]!!.textSize = 20f
        textfields[1]!!.setPadding(10, 10, 20, 10)
        input[1] = EditText(applicationContext)
        input[1]!!.width = 500
        sublinearLayout2.addView(textfields[1])
        sublinearLayout2.addView(input[1])
        linearLayout.addView(sublinearLayout1)
        linearLayout.addView(sublinearLayout2)
        builder.setView(linearLayout)
        builder.setTitle("Enter Data")
        builder.setIcon(R.drawable.ic_info_outline_black_24dp)
        builder.setMessage("Please Fill Course Name, Class No.")
        builder.setPositiveButton("ENTER") { dialog, which ->
            input_Text[0] = input[0]!!.text.toString()
            input_Text[1] = input[1]!!.text.toString()
            val finalText = input_Text[0].toString() + "\n" + input_Text[1]
            tempButton.text = finalText
            tempButton.textSize = 12f
            tempButton.animate().alpha(1f).duration = 100
            myDB!!.execSQL("INSERT INTO timetabledata (course, classroom, day, hour) VALUES ('" + input_Text[0] + "','" + input_Text[1] + "'," + day + "," + hour + ")")
        }
        builder.setNegativeButton("DELETE") { dialog, which ->
            dialog.dismiss()
            tempButton.animate().alpha(0.1f).duration = 100
            tempButton.text = "ADD"
            myDB!!.execSQL("DELETE FROM timetabledata WHERE day = '$day' AND hour = '$hour'")
        }
        builder.create().show()
    }

    fun createDB() {
        Log.i("created db", "Created")
        try {
            timeTableDB = this@timetableView.openOrCreateDatabase("TimeTable", Context.MODE_PRIVATE, null)
            timeTableDB?.execSQL("CREATE TABLE IF NOT EXISTS timetabledata (course VARCHAR, classroom VARCHAR, day INT(2), hour INT(2))")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable_view)
        mStorageRef = FirebaseStorage.getInstance().reference
        Log.i("Start works", "Working")
        createDB()
        createButtonsArray()
        for (i in 0..5) {
            for (j in 0..9) {
                Log.i("Main Loop Works", "Working")
                val tempButton = buttonsArray[i][j]
                val c = timeTableDB!!.rawQuery("SELECT * FROM timetabledata", null)
                val courseIndex = c.getColumnIndex("course")
                val classroomIndex = c.getColumnIndex("classroom")
                val dayIndex = c.getColumnIndex("day")
                val hourIndex = c.getColumnIndex("hour")
                if (c.moveToFirst()) {
                    do {
                        if (i == c.getInt(dayIndex) && j == c.getInt(hourIndex)) {
                            Log.i("If works", "Working")
                            val finalText = c.getString(courseIndex) + "\n" + c.getString(classroomIndex)
                            tempButton.text = finalText
                            tempButton.textSize = 12f
                            tempButton.animate().alpha(1f).duration = 100
                        } else {
                            Log.i("Else works", "Working")
                            tempButton.setOnClickListener { createAlert(tempButton, timeTableDB, i, j) }
                        }
                    } while (c.moveToNext())
                }
                c.close()
                Log.i("Else works", "Working")
                tempButton.setOnClickListener { createAlert(tempButton, timeTableDB, i, j) }
            }
            var sharedPreferences: SharedPreferences
            val switch1 = findViewById<Switch>(R.id.switch1)
            val rand = Random()
            sharedPreferences = getSharedPreferences("timetableView", Context.MODE_PRIVATE)
            val preferences = sharedPreferences.edit()
            switch1.isChecked = sharedPreferences.getBoolean("isChecked", false)
            switch1.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    preferences.putBoolean("isChecked", true)
                    val c = timeTableDB!!.rawQuery("SELECT * FROM timetabledata", null)
                    //                        int courseIndex = c.getColumnIndex("course");
//                        int classroomIndex = c.getColumnIndex("classroom");
                    val dayIndex = c.getColumnIndex("day")
                    val hourIndex = c.getColumnIndex("hour")
                    if (c.moveToFirst()) {
                        do {
                            val random = rand.nextInt()
                            val day = c.getInt(dayIndex)
                            val hour = c.getInt(hourIndex)
                            TimeSet((day + 2) % 7, 7 + hour, random)
                        } while (c.moveToNext())
                    }
                    c.close()
                } else {
                    preferences.putBoolean("isChecked", false)
                }
                preferences.commit()
            }
            //    @Override
//    protected void onStop() {
//        super.onStop();
//
//        try {
//            exportTheDB();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//    public void uploadFile() {
//
//        if (file != null) {
//
//            StorageReference riversRef = mStorageRef.child("database/timetabledata.csv");
//            file = Uri.fromFile();
//
//            riversRef.putFile(file)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception exception) {
//                            // Handle unsuccessful uploads
//                            // ...
//                        }
//                    });
//        } else {
//            Toast.makeText(timetableView.this, "File not found", Toast.LENGTH_SHORT).show();
//        }
//    }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.timetable_reset_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.reset -> {
                Log.i("sdfsd", "fdsdf")
                timeTableDB!!.execSQL("DROP TABLE IF EXISTS timetabledata")
                val blank_entry = ""
                var out: FileOutputStream? = null
                try {
                    out = openFileOutput(Filename, Context.MODE_PRIVATE)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
                try {
                    out!!.write(blank_entry.toByteArray())
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                var i = 0
                while (i < 6) {
                    var j = 0
                    while (j < 10) {
                        val resetButton = buttonsArray[i][j]
                        resetButton.animate().alpha(0.1f).duration = 10
                        resetButton.text = "ADD"
                        j++
                    }
                    i++
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun TimeSet(day: Int, hours: Int, i: Int) {
        val c = Calendar.getInstance()
        c[Calendar.DAY_OF_WEEK] = day
        c[Calendar.HOUR_OF_DAY] = hours
        c[Calendar.MINUTE] = 45
        c[Calendar.SECOND] = 0
        startAlarm(c, i)
    }

    fun startAlarm(cal: Calendar, i: Int) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this@timetableView, AlertActivity::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, i, intent, 0)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.timeInMillis, pendingIntent)
    }

    companion object {
        var timeTableDB: SQLiteDatabase? = null
    }
}