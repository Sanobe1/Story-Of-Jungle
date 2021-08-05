package com.storyofjungle.palm;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String LOG_TAG = GameActivity.class.getSimpleName();
    private TextView scoreTxt;
    private final Context context = GameActivity.this;
    private int Score = 0;
    private int Last = 0;
    private int Fail = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GridLayout gameCardsGridLayout = findViewById(R.id.gameCardsGridLayout);
        scoreTxt = findViewById(R.id.scoreTxt);

        Card[] allCards = new Card[16];

        for (int j=1; j<=16; j++){
            allCards[j - 1] = new Card(context,j);
            allCards[j - 1].setOnClickListener(this);
        }
        for (int j=0; j<16; j++){
            int random = (int) (Math.random() * 16);
            Card randomCard = allCards[random];
            allCards[random] = allCards[j];
            allCards[j] = randomCard;
        }
        for (int j=0; j<16; j++){
            gameCardsGridLayout.addView(allCards[j]);
            gameCardsGridLayout.setColumnCount(4);
            gameCardsGridLayout.setRowCount(4);
        }

    }

    @Override
    public void onClick(View view) {
        final Card selectedCard = (Card) view;
        selectedCard.flip();
        if (Last > 0){
            final Card card2 = findViewById(Last);
            if (card2.getFrontRes() == selectedCard.getFrontRes() && card2.getId() != selectedCard.getId()){
                Button firsImageView = findViewById(card2.getId());
                firsImageView.setClickable(false);
                Button secondImageView = findViewById(selectedCard.getId());
                secondImageView.setClickable(false);
                card2.setFlippable(false);
                selectedCard.setFlippable(false);
                Score++;
                scoreTxt.setText(String.valueOf(Score));
                Log.i(LOG_TAG,"score" + Score);

            } else {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        card2.flip();
                        selectedCard.flip();
                    }
                },1000);
                Fail++;
            }
            Last = 0;
        } else {
            Last = selectedCard.getId();
        }
    }

    static void shuffleArray(int[] CARD_IMAGE_ARRAY){
        Random random = new Random();
        for (int i = CARD_IMAGE_ARRAY.length - 1 ; i>0 ; i--){
            int index = random.nextInt(i+1);
            if ( i== index){
                i++;
            } else {
                int a = CARD_IMAGE_ARRAY[index];
                CARD_IMAGE_ARRAY[index] = CARD_IMAGE_ARRAY[i];
                CARD_IMAGE_ARRAY[i] = a;
            }
        }
    }

    public void onClickToMenu(View view) {
        startActivity(new Intent(this, MenuActivity.class));
    }
}
