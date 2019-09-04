package com.example.medicalcenterapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pusher.chatkit.rooms.Room

class RoomsAdapter : RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {

    private var list = ArrayList<Room>()
    lateinit var roomClickedInterface: RoomClickedInterface


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.roomName.text = list[position].name
    }


    fun addRoom(room: Room) {
        list.add(room)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun setInterface(roomClickedInterface: RoomClickedInterface) {
        this.roomClickedInterface = roomClickedInterface
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(p0: View?) {
            roomClickedInterface.roomSelected(list[adapterPosition])
        }

        var roomName: TextView = itemView.findViewById(android.R.id.text1)

        init {
            itemView.setOnClickListener(this)
        }
    }

    interface RoomClickedInterface {
        fun roomSelected(room: Room)
    }
}