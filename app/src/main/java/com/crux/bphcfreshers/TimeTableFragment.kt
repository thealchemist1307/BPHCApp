package com.crux.bphcfreshers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class TimeTableFragment : Fragment() {
    private var timetableList: ListView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_timetable, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timetableList = view.findViewById(R.id.timetableList)
        val title = resources.getStringArray(R.array.titleList)
        val description = resources.getStringArray(R.array.descriptionList)
        val listAdapter = ListAdapter(context, title, description)
        timetableList?.setAdapter(listAdapter)
        timetableList?.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    val intent = Intent(context, timetableView::class.java)
                    startActivity(intent)
                }
                1 -> {
                    val intent = Intent(context, Subject::class.java)
                    startActivity(intent)
                }
            }
        })
    }

    inner class ListAdapter(context: Context?, private val titleArray: Array<String>, private val descArray: Array<String>) : BaseAdapter() {
        private val context: Context? = null
        private val layoutInflater: LayoutInflater
        private var title: TextView? = null
        private var description: TextView? = null
        private var imageView: ImageView? = null
        override fun getCount(): Int {
            return titleArray.size
        }

        override fun getItem(position: Int): Any {
            return titleArray[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
            var convertView = convertView
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.fragment_timetable_card, null)
            }
            title = convertView.findViewById(R.id.cardTitle)
            description = convertView.findViewById(R.id.cardDesc)
            imageView = convertView.findViewById(R.id.cardImg)
            title?.setText(titleArray[position])
            description?.setText(descArray[position])
            if (titleArray[position].equals("TimeTable", ignoreCase = true)) {
                imageView?.setImageResource(R.drawable.timetable)
            } else if (titleArray[position].equals("Subjects", ignoreCase = true)) {
                imageView?.setImageResource(R.drawable.subjects)
            }
            return convertView
        }

        init {
            layoutInflater = LayoutInflater.from(context)
        }
    }
}