package com.example.medicalcenterapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicalcenterapp.R
import com.example.medicalcenterapp.adapters.RoomsAdapter
import com.example.medicalcenterapp.utilities.AppController
import com.pusher.chatkit.AndroidChatkitDependencies
import com.pusher.chatkit.ChatListeners
import com.pusher.chatkit.ChatManager
import com.pusher.chatkit.ChatkitTokenProvider
import com.pusher.chatkit.rooms.Room
import com.pusher.util.Result
import kotlinx.android.synthetic.main.activity_rooms_list.*

class RoomsListActivity : AppCompatActivity() {
    private val adapter = RoomsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms_list)
        supportActionBar!!.title = "List of Specialists"
        initRecyclerView()
        initChatManager()
    }

    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this@RoomsListActivity)
        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recycler_view.adapter = adapter
    }

    private fun initChatManager() {
        val username = intent.getStringExtra("extra")
        val chatManager = ChatManager(
            instanceLocator = "v1:us1:87b7072f-7203-4cf8-8c66-ae5faf2261e4",
            userId = username,
            dependencies = AndroidChatkitDependencies(
                tokenProvider = ChatkitTokenProvider(
                    endpoint = "http://10.0.2.2:3000/auth",
                    userId = username
                )
            )
        )
        chatManager.connect(listeners = ChatListeners(
            onErrorOccurred = { },
            onAddedToRoom = { },
            onRemovedFromRoom = { },
            onCurrentUserReceived = { },
            onNewReadCursor = { },
            onRoomDeleted = { },
            onRoomUpdated = { },
            onPresenceChanged = { u, n, p -> },
            onUserJoinedRoom = { u, r -> },
            onUserLeftRoom = { u, r -> },
            onUserStartedTyping = { u, r -> },
            onUserStoppedTyping = { u, r -> }
        )) { result ->
            when (result) {
                is Result.Success -> {
                    // We have connected!
                    val currentUser = result.value
                    AppController.currentUser = currentUser
                    val userJoinedRooms = ArrayList<Room>(currentUser.rooms)
                    for (i in 0 until userJoinedRooms.size) {
                        adapter.addRoom(userJoinedRooms[i])
                    }

                    currentUser.getJoinableRooms { result ->
                        when (result) {
                            is Result.Success -> {
                                // Do something with List<Room>
                                val rooms = result.value
                                runOnUiThread {
                                    for (i in 0 until rooms.size) {
                                        adapter.addRoom(rooms[i])
                                    }
                                }
                            }
                        }
                    }

                    adapter.setInterface(object : RoomsAdapter.RoomClickedInterface {
                        override fun roomSelected(room: Room) {
                            if (room.memberUserIds.contains(currentUser.id)) {
                                // user already belongs to this room
                                roomJoined(room)
                            } else {
                                currentUser.joinRoom(
                                    roomId = room.id,
                                    callback = { result ->
                                        when (result) {
                                            is Result.Success -> {
                                                // Joined the room!
                                                roomJoined(result.value)
                                            }
                                            is Result.Failure -> {
                                                Log.d("TAG", result.error.toString())
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    })
                }

                is Result.Failure -> {
                    // Failure
                    Log.d("TAG", result.error.toString())
                }
            }
        }
    }

    private fun roomJoined(room: Room) {
        val intent = Intent(this@RoomsListActivity, ChatRoomActivity::class.java)
        intent.putExtra("room_id", room.id)
        intent.putExtra("room_name", room.name)
        startActivity(intent)
    }
}