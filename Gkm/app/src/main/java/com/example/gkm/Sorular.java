package com.example.gkm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Sorular extends AppCompatActivity {

    //xml'deki tanimlamalari yapiyoruz.
    private Button answerButton;
    private RadioGroup radioGroup;
    private RadioButton a_option, b_option, c_option, d_option, e_option, rb_selected;
    private TextView questionText;
    private TextView scoreText;
    private  TextView gameState;

    private int scoreNum=0;

    private int currentQuestionIndex;

    // Olusturgumuz Questions sinifindan ArrayList tanimliyoruz.
    private ArrayList<Questions> questionsArrayList;

    //Firebase tanimlamasi yapiyoruz.
    // bunun icin grandle dosyasina implementation 'com.google.firebase:firebase-database:18.0.0' eklemeyi unutmayin.
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorular);

        // asagida id atamalarini yaptigimiz fonksiyondur.
        Init();

        // Firebase veritabaninda questions adinda bir alan tanimliyoruz.
        databaseReference = FirebaseDatabase.getInstance().getReference("questions");

        // ArrayListimizi tanimliyoruz.
        questionsArrayList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    Questions questions = postSnapshot.getValue(Questions.class);
                    questionsArrayList.add(questions);
                }

                // currentQuestionIndex=0;
                // asagida tanimladigimiz fonksiyondur.
                displayQuestion(currentQuestionIndex);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // cevap butonuna tikladigimizda olacaklari belirtiyoruz.
        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(radioGroup.getCheckedRadioButtonId()!=-1)
                {
                    // asagida tanimladigimiz bir fonksiyondur.
                    controlAnswers();

                } else {
                    Toast.makeText(getApplicationContext(),"Lütfen bir seçim yapınız!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    // yukarida cagirdigimiz id'leri atadigimiz fonksiyondur.
    private void Init() {
        answerButton = (Button)findViewById(R.id.answerButton);
        questionText = (TextView)findViewById(R.id.questionText);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        a_option = (RadioButton)findViewById(R.id.a_option);
        b_option = (RadioButton)findViewById(R.id.b_option);
        c_option = (RadioButton)findViewById(R.id.c_option);
        d_option = (RadioButton)findViewById(R.id.d_option);
        e_option = (RadioButton)findViewById(R.id.e_option);
        scoreText = (TextView)findViewById(R.id.scoreText);
        gameState = (TextView)findViewById(R.id.gameState);
        radioGroup.clearCheck();

    }


    private void controlAnswers() {


        if(answerCheck()){

            scoreNum = scoreNum + 5;
            scoreText.setText("PUANINIZ: "+ " " + String.valueOf(scoreNum));
          Toast.makeText(getApplicationContext(),"Tebrikler, doğru cevap!",Toast.LENGTH_SHORT).show();

        }else{

           Toast.makeText(getApplicationContext(),"Üzgünüm, yanlış cevap!",Toast.LENGTH_SHORT).show();

        }

        goOn();

    }

    private void goOn() {

        currentQuestionIndex = (currentQuestionIndex + 1)%questionsArrayList.size();

        if(currentQuestionIndex==0){

            gameState.setVisibility(View.VISIBLE);
            gameState.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Sorular.this, Sonuc.class);
                    startActivity(intent);
                }
            });
            radioGroup.clearCheck();
            answerButton.setEnabled(false);
            return;
        }

        displayQuestion(currentQuestionIndex);

    }

    private boolean answerCheck() {

        String answer = "";
        int id = radioGroup.getCheckedRadioButtonId();
        rb_selected = (RadioButton)findViewById(id);
        if(rb_selected == a_option){
            answer = "a";
        }
        if(rb_selected == b_option){
            answer = "b";
        }
        if(rb_selected == c_option){
            answer = "c";
        }
        if(rb_selected == d_option){
            answer = "d";
        }
        if(rb_selected == e_option){
            answer = "e";
        }

        return questionsArrayList.get(currentQuestionIndex).isCorrectAnswer(answer);
    }

    private void displayQuestion(int pos){

        radioGroup.clearCheck();
        questionText.setText(questionsArrayList.get(pos).getQuestionText());
        a_option.setText(questionsArrayList.get(pos).getChoise_a());
        b_option.setText(questionsArrayList.get(pos).getChoise_b());
        c_option.setText(questionsArrayList.get(pos).getChoise_c());
        d_option.setText(questionsArrayList.get(pos).getChoise_d());
        e_option.setText(questionsArrayList.get(pos).getChoise_e());

    }


}
