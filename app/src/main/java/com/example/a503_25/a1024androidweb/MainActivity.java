package com.example.a503_25.a1024androidweb;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText id,passwd;
    LinearLayout linearLayout;
    Button button;
    ProgressDialog progressDialog;

    //스레드를 작업한 후 화면 갱신을 위한 객체
    //1개만 있으면 Message의 what을 구분해서 사용할 수 있기 때문에 바로 인스턴스 생성
    Handler handler = new Handler(){
      public void handleMessage(Message message){
          progressDialog.dismiss();


          if(message.what==1) {
            linearLayout.setBackgroundColor(Color.RED);
          }else {
            linearLayout.setBackgroundColor(Color.GRAY);
          }
      }
    };

    //비동기적으로 작업을 수행하기 위한 스레드 클래스
    // 스레드는 재사용이 안되기 때문에 필요할 때 인스턴스를 만들어서 사용하므로 클래스를 만들어서 사용합니다.
    class ThreaeEx extends Thread{
        public void run(){
            try{
                String addr = "http://192.168.0.235:8080/1024androidlogin/login?id=";
                String logid = id.getText().toString();
                String logpw = passwd.getText().toString();
                addr = addr + logid + "&pw=" + logpw;
                // 문자열 주소를 URL로 변경
                URL url = new URL(addr);
                //연결 객체 생성
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                //옵션 설정
                con.setUseCaches(false);// 캐시(로컹에 저장해두고 사용) 사용 여부
                con.setConnectTimeout(30000); // 접속을 시도하는 최대 시간
                //30초 동안 접속이 안되면 예외를 발생시킵니다.

                //문자열을 다운로드 받을 스트림 생성
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                //문자열 다운로드
                StringBuilder sb = new StringBuilder();
                while (true){
                    String line = br.readLine();
                    if(line == null){
                        break;
                    }else{
                        sb.append(line + "\n");
                    }
                }

                br.close();
                con.disconnect();

                //Log.e("다운로드 받은 데이터 : ",sb.toString());

                JSONObject object = new JSONObject(sb.toString());
                String x = object.getString("id");

                //파싱한 결과를 가지고 Message의 what을 달라해서 핸들러에게 전송
                Message msg = new Message();
                if(x.equals("null")){
                   // Log.e("로그인 여부 : ", "실패");
                    msg.what = 1;
                }else {
                    //Log.e("로그인 여부 : ", "성공");
                    msg.what = 2;
                }
                handler.sendMessage(msg);



            }catch (Exception e){
                Log.e("다운로드 실패:",e.getMessage());
            }
        }
    }

    //Activity가 만들어 질 때 호출되는 메소드
    //Activity가 실행될 때 무엇인가를 하고자 하는 경우는 onResume
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //layout 파일을 읽어서 메모리에 로드 한 후 화면 출력을 준비하는 메소드를 호출
        setContentView(R.layout.activity_main);

        id = (EditText)findViewById(R.id.id);
        passwd = (EditText)findViewById(R.id.passwd);
        button = (Button)findViewById(R.id.loginButton);
        linearLayout = (LinearLayout)findViewById(R.id.layout01);

        //버튼을 누르면 수행할 내용
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //진행 대화상자를 출력
                progressDialog = progressDialog.show(MainActivity.this,"로그인","로그인 처리 중");
                //스레드 만들어서 실행
                ThreaeEx th = new ThreaeEx();
                th.start();
            }
        });









    }
}
