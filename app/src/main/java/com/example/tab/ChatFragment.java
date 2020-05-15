package com.example.tab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment implements View.OnClickListener {
    EditText el;
    TextView gor;
    Button button;
    RecyclerView recyclerView;
    List<ResponseMessage> responseMessageList;
    List<ResponseMessage> dateList;
    MessageAdapter messageAdapter;
    View v;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_chat, container, false);
        el = (EditText) v.findViewById(R.id.editText);
        button = (Button) v.findViewById(R.id.button);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        responseMessageList = new ArrayList<>();

        messageAdapter = new MessageAdapter(responseMessageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(messageAdapter);
        button.setOnClickListener(this);
        Thread myThread = new Thread(new ChatFragment.MyServerThread());
        myThread.start();
        return v;
    }

    @Override
    public void onClick(View v) {

        MessageSender messagesender = new MessageSender();
        messagesender.execute(el.getText().toString());
        SimpleDateFormat sdf=new SimpleDateFormat("MMM d , HH:mm");
        String zaman=sdf.format(new Date());
        ResponseMessage messages2 = new ResponseMessage(el.getText().toString(), true,zaman);
        responseMessageList.add(messages2);
        messageAdapter.notifyDataSetChanged();//listede degisiklik old ekranı yenilemek icin
        el.setText("");
        if (!isvisible()) {
            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
        }

    }

    public boolean isvisible() { //mesajlar altta kalmasın hep ekranda gözüksün diye
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int positionOfLastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
        int itemCount = recyclerView.getAdapter().getItemCount();
        return (positionOfLastVisibleItem >= itemCount);
    }

    class MyServerThread implements Runnable {
        Socket s;
        ServerSocket ss;
        InputStreamReader isr;
        BufferedReader bufferedReader;
        Handler h = new Handler();
        String message;

        @Override
        public void run() {
            try {
                ss = new ServerSocket(MainActivity.port);
                while (true) {
                    s = ss.accept();
                    isr = new InputStreamReader(s.getInputStream());
                    bufferedReader = new BufferedReader(isr);
                    message = bufferedReader.readLine();


                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            if (message.equals("Zil")) {
                                // Toast.makeText(getApplicationContext(),"Doorbell is ringing!!",Toast.LENGTH_SHORT).show();

                            } else {
                                SimpleDateFormat sdf2=new SimpleDateFormat("MMM d , HH:mm");
                                String zaman2=sdf2.format(new Date());
                                ResponseMessage messages = new ResponseMessage(message, false,zaman2);
                                responseMessageList.add(messages);
                                messageAdapter.notifyDataSetChanged();
                                if (!isvisible()) { //mesajlar
                                    recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                                }


                            }
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
