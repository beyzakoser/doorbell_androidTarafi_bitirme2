package com.example.tab;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment implements View.OnClickListener {
    ImageView imageView;
    public ImageView image;
    ToggleButton toggleButton;
    public byte[] buff;
    Button buton;
    View v;



    public CameraFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_camera, container, false);
        image = (ImageView) v.findViewById(R.id.img);
        image.setImageResource(R.drawable.camera);
        toggleButton = (ToggleButton) v.findViewById(R.id.toggleBtn);
        toggleButton.setOnClickListener(this);
        return v;

    }

    @Override
    public void onClick(View v) {
        if (toggleButton.isChecked()) {
//stream baslangic
            MessageSender messagesender = new MessageSender();
            messagesender.execute("kamera");

            Thread myThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        final DatagramSocket clientsocket = new DatagramSocket(MainActivity.port);
                        byte[] receivedata = new byte[92024];

                        while (true) {
                            DatagramPacket recv_packet = new DatagramPacket(receivedata, receivedata.length);
                            clientsocket.receive(recv_packet);
                            buff = recv_packet.getData();
                            if (buff.length != 0) { //resim alınabildiyse devam
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Bitmap bmp = BitmapFactory.decodeByteArray(buff, 0, buff.length);
                                        image.setImageBitmap(bmp);
                                        if (toggleButton.isChecked() == false) { //kapat a basıldıysa threada i interrupt etmeye çalıştım
                                            MessageSender messagesender2 = new MessageSender();
                                            messagesender2.execute("kapat");
                                            Thread.interrupted();
                                            clientsocket.close();
                                            image.setImageDrawable(getResources().getDrawable(R.drawable.camera));//resim alımını kapat

                                        }

                                    }
                                });
                            } else { //resim alınamadıysa //bu kısım olmadı
                                Log.i("message:", "buffer bos");
                                Thread.interrupted();
                                clientsocket.close();
                                toggleButton.setVisibility(View.GONE);

                            }
                        }
                    } catch (SocketException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            myThread.start();
//stream bitis

        }
    }
}


