package com.example.kkiri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private View btnSend;
    private EditText editText;
    boolean isMine = true;
    private List<ChatMessage> chatMessages;
    private ArrayAdapter<ChatMessage> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatMessages = new ArrayList<>(); // 메세지 내용을 담을 arrayList

        listView = (ListView) findViewById(R.id.list_msg); // 메세지가 나타날 리스트
        btnSend = findViewById(R.id.btn_chat_send); // Send 버튼
        editText = (EditText) findViewById(R.id.msg_type); // 메시지 입력 창

        //set ListView adapter first
        adapter = new MessageAdapter(this, R.layout.item_chat_left, chatMessages);
        listView.setAdapter(adapter);

        //send 버튼을 누르면~
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")) { // 빈칸을 입력하면~
                    Toast.makeText(MainActivity.this, "내용을 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else { // 내용을 입력하면~
                    ChatMessage chatMessage = new ChatMessage(editText.getText().toString(), isMine); // ArrayList에 저장 & 자신이 보냈다는 true값
                    chatMessages.add(chatMessage);
                    adapter.notifyDataSetChanged(); // 업데이트
                    editText.setText(""); // 전송 후 전송칸 초기화
                    if (isMine) { // 누가 보낸메시지인지에 따라서 메시지 출력 위치 변경
                        isMine = false;
                    } else {
                        isMine = true;
                    }
                }
            }
        });
    }
}
