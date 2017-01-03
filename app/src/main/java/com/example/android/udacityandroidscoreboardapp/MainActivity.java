package com.example.android.udacityandroidscoreboardapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.android.udacityandroidscoreboardapp.R.string.points;

public class MainActivity extends AppCompatActivity {
    // Tracks the points for player A
    int pointsPlayerA = 0;

    // Tracks the points for player B
    int pointsPlayerB = 0;

    // Tracks the games for player A
    int gamesPlayerA = 0;

    // Tracks the games for player B
    int gamesPlayerB = 0;

    String winnerPlayerA = "Winner of the set: Player A";
    String winnerPlayerB = "Winner of the set: Player B";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * Increase the score for Player A by 15 pts until 30 pts and then by 10 pts until 40pts.
     */
    public void addPointsForPlayerA(View v) {
        if (pointsPlayerA == 30) {
            pointsPlayerA = pointsPlayerA + 10;
        } else if (pointsPlayerA == 40) {
            resetScore();
            addGamesForPlayerA();
        } else {
            pointsPlayerA = pointsPlayerA + 15;
        }
        displayForPlayerA(pointsPlayerA);
        displayGamesForPlayerA(gamesPlayerA);
    }

    /**
     * Add games for Player A until 6 games.
     */
    public void addGamesForPlayerA() {
        if (gamesPlayerA == 5 & pointsPlayerA == 0) {
            gamesPlayerA = 6;
            pointsPlayerB =0;
            resetScore();
            winnerDisplay(winnerPlayerA);

        } else {
            gamesPlayerA = gamesPlayerA + 1;
        }
        displayForPlayerB(pointsPlayerB);
    }

    /**
     * Increase the score for Player B by 15 pts until 30 pts and then by 10 pts until 40pts.
     */
    public void addPointsForPlayerB(View v) {
        if (pointsPlayerB == 30) {
            pointsPlayerB = pointsPlayerB + 10;
        } else if (pointsPlayerB == 40) {
            resetScore();
            addGamesForPlayerB();
        } else {
            pointsPlayerB = pointsPlayerB + 15;
        }

        displayForPlayerB(pointsPlayerB);
        displayGamesForPlayerB(gamesPlayerB);
    }

    /**
     * Add games for Player B until 6 games.
     */
    public void addGamesForPlayerB() {
        if (gamesPlayerB == 5 & pointsPlayerB == 0) {
            gamesPlayerB = 6;
            pointsPlayerA =0;
            winnerDisplay(winnerPlayerB);
        } else {
            gamesPlayerB = gamesPlayerB + 1;
        }

        displayForPlayerA(pointsPlayerA);
    }

    // Reset Points
    private int resetScore() {
        pointsPlayerA = 0;
        pointsPlayerB = 0;
        return pointsPlayerA & pointsPlayerB;
    }

    // Reset All
    public void resetAll(View v) {
        pointsPlayerA = 0;
        pointsPlayerB = 0;
        gamesPlayerA = 0;
        gamesPlayerB = 0;
        displayForPlayerA(pointsPlayerA);
        displayForPlayerB(pointsPlayerB);
        displayGamesForPlayerA(gamesPlayerA);
        displayGamesForPlayerB(gamesPlayerB);
        winnerDisplay("");
    }

    /**
     * Displays the given Points for Player A.
     */
    public void displayForPlayerA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_a_points);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given Points for Player B.
     */
    public void displayForPlayerB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_b_points);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the Games won for Player A.
     */
    public void displayGamesForPlayerA(int games) {
        TextView scoreView = (TextView) findViewById(R.id.player_a_games);
        scoreView.setText(String.valueOf(games));
    }

    /**
     * Displays the Games won for Player B.
     */
    public void displayGamesForPlayerB(int games) {
        TextView scoreView = (TextView) findViewById(R.id.player_b_games);
        scoreView.setText(String.valueOf(games));
    }

    /**
     * Displays the Winner of a set message.
     */
    public void winnerDisplay(String score) {
        TextView scoreView = (TextView) findViewById(R.id.winnertextView);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
