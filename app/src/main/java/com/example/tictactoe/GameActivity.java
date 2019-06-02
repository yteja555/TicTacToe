package com.example.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    public boolean uCanStartGame;
    public String matchStatus;
    public Button first;
    public Button second;
    public int yfmFourthPosition;
    public int yfmThirdPosition;
    public int yfmSecondPosition;
    public int yfmfirstPosition;
    public boolean specialCase = false;
    public boolean yfmPlayerfirstPositionSide = false;
    public boolean yfmPlayerfirstPositionMiddle = false;
    public boolean yfmPlayerMoveisMiddleAndSideCorner = false;
    public boolean yfmPlayerfirstPositionOppositeCorner = false;
    public boolean yfmPlayerfirstPositionSideCorner = false;
    public boolean ysmPlayerFirstMoveIsCorner = false;
    public boolean ysmPlayerFirstMoveIsSide = false;
    public boolean ysmPlayerFirstMoveIsMiddle = false;
    public boolean ysmPlayerMoveIsCornerAndOppositeCorner = false;
    public boolean ysmPlayerMoveIsCornerAndSideCorner = false;
    public boolean ysmPlayerMoveIsCornerAndSide = false;
    public boolean ysmPlayerMoveIsMiddleAndOppositeCorner = false;
    public boolean ysmPlayerMoveIsMiddleAndSideCorner = false;
    public boolean ysmPlayerMoveIsMiddleAndNearSide = false;
    public boolean ysmPlayerMoveIsMiddleAndNearSideAndSideCorner = false;
    public boolean ysmPlayerMoveIsMiddleAndNearSideAndOppositeCorner = false;
    public boolean ysmPlayerMoveIsMiddleAndNearSideAndSide = false;
    public boolean ysmPlayerMoveIsMiddleAndFarSide = false;
    public boolean ysmPlayerMoveisSideandNearSide = false;
    public boolean ysmPlayerMoveisSideandFarSide = false;
    public boolean ysmPlayerMoveisSideandNearCorner = false;
    public boolean ysmPlayerMoveisSideandFarCorner = false;
    public int yfmPlayerfirstposition;
    public int yfmPlayerSecondPosition;
    public int yfmPlayerThirdPosition;
    public int ysmFourthPosition;
    public int ysmThirdPosition;
    public int ysmSecondPosition;
    public int ysmfirstPosition;
    public int ysmPlayerfirstposition;
    public int ysmPlayerSecondPosition;
    public int ysmPlayerThirdPosition;
    public int ysmPlayerFourthPosition;
    public ArrayList<Integer> remainingCorners;
    public boolean yfmneedToTrackFirstMoveofPlayer = false;
    public boolean yfmneedToTrackSecondMoveofPlayer = false;
    public boolean yfmNeedToTrackThirdMoveofPlayer = false;
    public boolean ysmneedToTrackFirstMoveofPlayer = true;
    public boolean ysmneedToTrackSecondMoveofPlayer = false;
    public boolean ysmNeedToTrackThirdMoveofPlayer = false;
    public boolean ysmNeedToTrackFourthMoveofPlayer = false;
    public boolean yuvaFirstMove = false;
    public ArrayList<Integer> remainingSides;
    public int winner;
    public final int[] corners = {0, 2, 6, 8};

    public final int[] sides = {1, 3, 5, 7};

    public final int middlePosition = 4;

    public final int[] idOfPositions = {R.id.position_0, R.id.position_1, R.id.position_2
            , R.id.position_3, R.id.position_4, R.id.position_5
            , R.id.position_6, R.id.position_7, R.id.position_8};
    public ArrayList<Integer> emptyPositions;
    public boolean gameEnded = false;
    public int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    public TextView player1Name;
    public TextView player2Name;

    public int presentGameMode = 0;
    public boolean gameStarted = false;

    public ImageView swapImage;

    public int yuvaSymbolId;
    public int yourSymbolId;

    public ImageView yourSymbol;
    public ImageView yuvaSymbol;

    public ImageView player1Symbol;
    public int player1SymbolId;

    public int player2SymbolId;

    public ImageView player2Symbol;

    public int activePlayer;

    public int[] positionStatus = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent fromMainActivity = getIntent();

        presentGameMode = fromMainActivity.getIntExtra(getString(R.string.key_for_saving_mode), 0);
//        Toast.makeText(this,"present game mode "+presentGameMode,Toast.LENGTH_LONG).show();
        if (presentGameMode == 1) {
            initializeFriendViews();
            initializeFriendSymbols();
            setFriendModeListeners();

        } else if (presentGameMode == 2) {
            initializeYuvaModeViews();
            initializeYuvaModeSymbols();
            setYuvaModeListeners();
        }

    }


    public void makeAMove(View view) {

        if (uCanStartGame) {


        gameStarted = true;
        swapImage.setVisibility(View.GONE);
        ImageView block = (ImageView) view;
        int tag = Integer.parseInt(block.getTag().toString());
        Log.v("pressed position is", " " + tag);
        Log.v("pressed  status is", " " + positionStatus[tag]);
        if (positionStatus[tag] == 0) {
            if (presentGameMode == 1) {
                if (positionStatus[tag] == 0 && (!gameEnded)) {
                    Log.v("active player is ", " " + activePlayer);

                    if (activePlayer == 1) {
                        block.setImageResource(player1SymbolId);
                        activePlayer = 2;
                        positionStatus[tag] = 1;
                    } else {
                        block.setImageResource(player2SymbolId);
                        activePlayer = 1;
                        positionStatus[tag] = 2;
                    }

                }
                winner = checkPlayer1AndPlayer2();
                if (winner != 0) {
                    gameEnded = true;
                    if (presentGameMode == 1) {
                        if (winner == 1) {
                            matchStatus = "Player 1\n                    Winner";
                        } else {
                            matchStatus = "Player 2\n                    Winner";
                        }
//                Toast.makeText(this, "player " + winner, Toast.LENGTH_LONG).show();
                        showGameOverDialog();
                    }
                } else {
                    if (checkForDraw()) {
                        winner = 0;
                        matchStatus = "Draw";
//                    Toast.makeText(this,"match drawn",Toast.LENGTH_SHORT).show();
                        showGameOverDialog();
                    }
                }
            } else {
                Log.v("in yuva mode", "player chance");
                if (positionStatus[tag] == 0 && (!gameEnded)) {
//                Log.v("position is","empty");
//                Log.v("active player is "," "+activePlayer);

                    block.setImageResource(yourSymbolId);
                    activePlayer = 2;
                    positionStatus[tag] = 1;
                    if (ysmneedToTrackFirstMoveofPlayer) {
                        ysmPlayerfirstposition = tag;
                    }
                    if (ysmneedToTrackSecondMoveofPlayer) {
                        ysmPlayerSecondPosition = tag;
                    }
                    if (ysmNeedToTrackThirdMoveofPlayer) {
                        ysmPlayerThirdPosition = tag;
                    }
                    if (ysmNeedToTrackFourthMoveofPlayer) {
                        ysmPlayerFourthPosition = tag;
                    }

                    if (yfmneedToTrackFirstMoveofPlayer) {
                        yfmPlayerfirstposition = tag;
                        Log.v("yfm player position", "one " + tag);
//                    Toast.makeText(this,"player first position="+yfmPlayerfirstposition,Toast.LENGTH_SHORT).show();
                    }
                    if (yfmneedToTrackSecondMoveofPlayer) {
                        yfmPlayerSecondPosition = tag;
                        Log.v("yfm player position", "two " + tag);
//                    Toast.makeText(this,"player second position="+yfmPlayerSecondPosition,Toast.LENGTH_SHORT).show();
                    }
                    if (yfmNeedToTrackThirdMoveofPlayer) {
                        yfmPlayerThirdPosition = tag;
                        Log.v("yfm player position", "three " + tag);
//                    Toast.makeText(this,"player second position="+yfmPlayerSecondPosition,Toast.LENGTH_SHORT).show();
                    }

                }
                winner = checkPlayer1AndPlayer2();
                if (winner == 1) {
                    Log.v("checking for win", "after player move");
                    gameEnded = true;
                    matchStatus = "You \n                  Winner";
//                Toast.makeText(this,"you won the game",Toast.LENGTH_SHORT).show();
                    showGameOverDialog();
                } else {
                    Log.v("checking for draw", "after player move");
                    if (checkForDraw()) {
                        winner = 0;
                        matchStatus = "Draw";
//                    Toast.makeText(this,"match drawn",Toast.LENGTH_SHORT).show();
                        showGameOverDialog();
                    } else {
                        try {
                            yuvaMakeNextMove(yuvaSymbolId);
                        } catch (Exception e) {
                            Toast.makeText(this, " " + e, Toast.LENGTH_SHORT).show();
                        }
                        winner = checkPlayer1AndPlayer2();
                        if (winner == 2) {
                            matchStatus = "Yuva \n                      Winner";
                            Log.v("checking for draw", "after yuva move");
                            gameEnded = true;
//                        Toast.makeText(this,"Yuva won the game",Toast.LENGTH_SHORT).show();
                            showGameOverDialog();
                        } else {
                            Log.v("checking for draw", "after yuva move");
                            if (checkForDraw()) {
                                winner = 0;
                                matchStatus = "Draw";
//                            Toast.makeText(this,"match drawn",Toast.LENGTH_SHORT).show();
                                showGameOverDialog();
                            }
                        }
                    }
                }
            }
        }
    }

}

    public void initializeFriendSymbols()
    {
        player1SymbolId=R.drawable.ic_o_symbol;
        player2SymbolId=R.drawable.ic_x_symbol;
        activePlayer=1;
        first.setText("Player 1");
        second.setText("Player 2");
        player1Symbol.setImageResource(player1SymbolId);
        player2Symbol.setImageResource(player2SymbolId);
//        Random random = new Random();
//        int first=random.nextInt(10);
//        Log.v("random number is "," "+first);
//        if(first>5)
//        {
//            player1SymbolId=R.drawable.ic_o_symbol;
//            player2SymbolId=R.drawable.ic_x_symbol;
//            activePlayer=1;
//        }
//        else
//        {
//            player2SymbolId=R.drawable.ic_o_symbol;
//            player1SymbolId=R.drawable.ic_x_symbol;
//            activePlayer=2;
//
//        }
//        player1Symbol.setImageResource(player1SymbolId);
//        player2Symbol.setImageResource(player2SymbolId);
    }
    public void initializeYuvaModeSymbols()
    {
        player1Name.setText(getString(R.string.you));
        player2Name.setText(getString(R.string.yuva));
        yourSymbolId=R.drawable.ic_o_symbol;
        yuvaSymbolId=R.drawable.ic_x_symbol;
        activePlayer=1;
        first.setText("You");
        second.setText("Yuva");
        yourSymbol.setImageResource(yourSymbolId);
        yuvaSymbol.setImageResource(yuvaSymbolId);
    }
    public void initializeFriendViews()
    {
        swapImage=(ImageView)findViewById(R.id.swapImage);
        player1Symbol=(ImageView)findViewById(R.id.player_1_symbol);
        player2Symbol=(ImageView)findViewById(R.id.player_2_symbol);
        first=(Button)findViewById(R.id.first);
        second=(Button)findViewById(R.id.second);
    }
    public void initializeYuvaModeViews()
    {
        first=(Button)findViewById(R.id.first);
        second=(Button)findViewById(R.id.second);
        player1Name=findViewById(R.id.player_1_label);
        player2Name=findViewById(R.id.player_2_label);
        yourSymbol=findViewById(R.id.player_1_symbol);
        yuvaSymbol=findViewById(R.id.player_2_symbol);
        swapImage=findViewById(R.id.swapImage);
    }
    public void swapFriends()
    {

        if(player1SymbolId==R.drawable.ic_o_symbol)
        {
            swapImage.animate().rotationBy(180).setDuration(100);
            player1SymbolId=R.drawable.ic_x_symbol;
            player2SymbolId=R.drawable.ic_o_symbol;
        }
        else
        {
            swapImage.setImageResource(R.drawable.swap_symbols);
            swapImage.animate().rotationBy(180).setDuration(100);
            player1SymbolId=R.drawable.ic_o_symbol;
            player2SymbolId=R.drawable.ic_x_symbol;
        }
        player1Symbol.setImageResource(player1SymbolId);
        player2Symbol.setImageResource(player2SymbolId);

    }
    public void swapYuvaAndPlayer()
    {
        if(yourSymbolId==R.drawable.ic_o_symbol)
        {
            swapImage.animate().rotationBy(180).setDuration(100);
            yourSymbolId=R.drawable.ic_x_symbol;
            yuvaSymbolId=R.drawable.ic_o_symbol;
            Log.v("in yuva mode ","your symbol id changed from o to x");
        }
        else
        {
            swapImage.setImageResource(R.drawable.swap_symbols);
            swapImage.animate().rotationBy(180).setDuration(100);
            yourSymbolId=R.drawable.ic_o_symbol;
            yuvaSymbolId=R.drawable.ic_x_symbol;
            Log.v("in yuva mode ","your symbol id changed from o to x");
        }
        yourSymbol.setImageResource(yourSymbolId);
        yuvaSymbol.setImageResource(yuvaSymbolId);
        Log.v("in yuva mode ","your symbol successfully swapped");

    }
    public void setFriendModeListeners()
    {
        // swap button listeners
        swapImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.v("game started "," "+gameStarted);
                if(!gameStarted) {
                    swapFriends();
                }
            }
        });
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer=1;
                uCanStartGame=true;
                LinearLayout linearLayout=(LinearLayout)findViewById(R.id.dialog);
                linearLayout.setVisibility(View.GONE);

            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer=2;
                uCanStartGame=true;
                LinearLayout linearLayout=(LinearLayout)findViewById(R.id.dialog);
                linearLayout.setVisibility(View.GONE);
            }
        });
    }
    public void setYuvaModeListeners()
    {
        swapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!gameStarted)
                {
                    Log.v("in yuva mode ","before swapping");
                    swapYuvaAndPlayer();
                    Log.v("in yuva mode ","after swapping");
                }

            }
        });
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer=1;
                yuvaFirstMove=false;
                uCanStartGame=true;
                LinearLayout linearLayout=(LinearLayout)findViewById(R.id.dialog);
                linearLayout.setVisibility(View.GONE);

            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer=2;
                uCanStartGame=true;
                yuvaMakeYourFirstMove();
                LinearLayout linearLayout=(LinearLayout)findViewById(R.id.dialog);
                linearLayout.setVisibility(View.GONE);
            }
        });
    }
    public int checkPlayer1AndPlayer2()
    {
        int winner=0;
        for(int[] i:winningPositions)
        {

            if((positionStatus[i[0]]==positionStatus[i[1]])&&(positionStatus[i[1]]==positionStatus[i[2]])&&(positionStatus[i[0]]!=0))
            {
                setGameOverAnimation(i[0],i[1],i[2]);
                winner=positionStatus[i[0]];

            }

        }
        return winner;
    }
    public void yuvaMove(int position,int playerSymbolId)
    {
        if(positionStatus[position]==0 && (!gameEnded))
        {
            ImageView block = (ImageView)findViewById(idOfPositions[position]);
            block.setImageResource(playerSymbolId);
            positionStatus[position]=2;
            activePlayer=1;
        }
    }
    public void yuvaMakeNextMove(int yuvaSymbolId)
    {
        if(yuvaFirstMove)
        {
            Log.v("need track move","out side");
            if(yfmneedToTrackFirstMoveofPlayer)
            {
                Log.v("need track first move","start");
                yfmneedToTrackSecondMoveofPlayer =true;
                if(isSideblock(yfmPlayerfirstposition))
                {
                    yfmSecondPosition=middlePosition;
                    yuvaMove(middlePosition,yuvaSymbolId);
                    yfmPlayerfirstPositionSide=true;

                }
                else if(isMiddle(yfmPlayerfirstposition))
                {
                    yfmSecondPosition=getOppositeCorner(yfmfirstPosition);
                    yuvaMove(yfmSecondPosition,yuvaSymbolId);
                    yfmPlayerfirstPositionMiddle=true;
                }
                else if(isSideCorner(yfmPlayerfirstposition))
                {
                    yfmSecondPosition=getOppositeCorner(yfmfirstPosition);
                    yuvaMove(yfmSecondPosition,yuvaSymbolId);
                    yfmPlayerfirstPositionSideCorner=true;
                }
                else if(isOppositeCorner(yfmPlayerfirstposition))
                {
                    yfmSecondPosition=getSideCorner(yfmPlayerfirstposition);
                    yuvaMove(yfmSecondPosition,yuvaSymbolId);
                    yfmPlayerfirstPositionOppositeCorner=true;
                }
                yfmneedToTrackFirstMoveofPlayer =false;
            }
            else if(yfmneedToTrackSecondMoveofPlayer)
            {
                yfmNeedToTrackThirdMoveofPlayer=true;
                Log.v("need track second move","start");
                if(yfmPlayerfirstPositionSide)
                {
                    Log.v("need track second move","side");
                    ArrayList<Integer> sideCorners;
                    int opposite=getOppositeCorner(yfmfirstPosition);
                    if(positionStatus[opposite]==0)
                    {
                        yfmThirdPosition=opposite;
                        yuvaMove(opposite,yuvaSymbolId);
                    }
                    else
                    {
                        if(isNearOfCorner(yfmPlayerfirstposition, yfmfirstPosition))
                        {
                            sideCorners= getEmptySideCorners(opposite);
                            if(!isNearOfCorner(yfmPlayerfirstposition,sideCorners.get(0)))
                            {
                                yfmThirdPosition=sideCorners.get(0);
                                yuvaMove(sideCorners.get(0),yuvaSymbolId);
                            }
                            else
                            {
                                yfmThirdPosition=sideCorners.get(1);
                                yuvaMove(sideCorners.get(1),yuvaSymbolId);
                            }
                        }
                        else
                        {
                            sideCorners= getEmptySideCorners(opposite);
                            if(isNearOfCorner(yfmPlayerfirstposition,sideCorners.get(0)))
                            {
                                yfmThirdPosition=sideCorners.get(0);
                                yuvaMove(sideCorners.get(0),yuvaSymbolId);
                            }
                            else
                            {
                                yfmThirdPosition=sideCorners.get(1);
                                yuvaMove(sideCorners.get(1),yuvaSymbolId);
                            }
                        }
                    }
                }
                else if(yfmPlayerfirstPositionMiddle)
                {
                    Log.v("need track second move","middle");
                    if(isCornerBlock(yfmPlayerSecondPosition))
                    {
                        yfmThirdPosition=getOppositeCorner(yfmPlayerSecondPosition);
                        yuvaMove(yfmThirdPosition,yuvaSymbolId);
                        yfmPlayerMoveisMiddleAndSideCorner=true;
                    }
                    else
                    {
                        yfmThirdPosition= getStraightBlockForSide(yfmPlayerSecondPosition);
                        yuvaMove(yfmThirdPosition,yuvaSymbolId);
                    }
                }
                else if (yfmPlayerfirstPositionSideCorner)
                {
                    Log.v("need track second move","side corner");
//                    Toast.makeText(this,"outside 1"+ yfmPlayerfirstposition,Toast.LENGTH_SHORT).show();
                    if(yfmPlayerSecondPosition !=middlePosition)
                    {
                        Log.v("need track second move","side corner 1");
                        yfmThirdPosition=middlePosition;
                        yuvaMove(yfmThirdPosition,yuvaSymbolId);

                    }
                    else
                    {
                        Log.v("hahaha","here");
//                        Toast.makeText(this,"outside"+ yfmPlayerfirstposition,Toast.LENGTH_SHORT).show();
                        yfmThirdPosition=getOppositeCorner(yfmPlayerfirstposition);
                        yuvaMove(yfmThirdPosition,yuvaSymbolId);
                    }
                    Log.v("need track second move","afterSide corner");

                }
                else if(yfmPlayerfirstPositionOppositeCorner)
                {
                    if(yfmPlayerSecondPosition==getInbetween(yfmfirstPosition,yfmSecondPosition))
                    {
//                        Toast.makeText(this,"inside",Toast.LENGTH_SHORT).show();
                        yfmThirdPosition=getOppositeCorner(yfmSecondPosition);
                        yuvaMove(yfmThirdPosition,yuvaSymbolId);
                    }
                    else
                    {
                        yfmThirdPosition=getInbetween(yfmSecondPosition, yfmfirstPosition);
                        yuvaMove(yfmThirdPosition,yuvaSymbolId);
                    }

                }
                yfmneedToTrackSecondMoveofPlayer=false;
            }
            else if(yfmNeedToTrackThirdMoveofPlayer)
            {
                if(yfmPlayerfirstPositionSide)
                {
                    if(yfmPlayerThirdPosition==getInbetween(yfmfirstPosition,yfmThirdPosition))
                    {
                        ArrayList<Integer> emptyCorners=getEmptyCorners();
                        yfmFourthPosition=emptyCorners.get(0);
                        yuvaMove(yfmFourthPosition,yuvaSymbolId);
                    }
                    else
                    {
                        yfmFourthPosition=getInbetween(yfmfirstPosition,yfmThirdPosition);
                        yuvaMove(yfmFourthPosition,yuvaSymbolId);
                    }
                }
                else if(yfmPlayerfirstPositionMiddle)
                {
                    if(yfmPlayerMoveisMiddleAndSideCorner)
                    {
                       int middle1=getInbetween(yfmfirstPosition,yfmThirdPosition);
                       if(middle1==yfmPlayerThirdPosition)
                       {
                           yfmFourthPosition=getInbetween(yfmSecondPosition,yfmThirdPosition);
                           yuvaMove(yfmFourthPosition,yuvaSymbolId);
                       }
                       else
                       {
                           yfmFourthPosition=getInbetween(yfmfirstPosition,yfmThirdPosition);
                           yuvaMove(yfmFourthPosition,yuvaSymbolId);
                       }
                    }
                    else
                    {
                        if(isCornerBlock(yfmPlayerThirdPosition)) {
                            ArrayList<Integer> emptyCorners=getEmptyCorners();
                            yfmFourthPosition=emptyCorners.get(0);
                            yuvaMove(yfmFourthPosition,yuvaSymbolId);
                        }
                        else
                        {
                            ArrayList<Integer> emptyCorners=getEmptyCorners();
                            yfmFourthPosition=emptyCorners.get(0);
//                            Log.v("remaining corners"," "+emptyCorners.get(0)+" "+emptyCorners.get(1));
//                            Log.v("yfmthird="," "+yfmThirdPosition);
                            if(yfmThirdPosition==getInbetween(yfmSecondPosition,yfmFourthPosition))
                            {
                                Log.v("is near of","first");
                                yuvaMove(yfmFourthPosition, yuvaSymbolId);
                            }
                            else
                            {
                                Log.v("is near of","second");
                                yfmFourthPosition=emptyCorners.get(1);
                                yuvaMove(yfmFourthPosition, yuvaSymbolId);
                            }



                        }

                    }

                }
                else if(yfmPlayerfirstPositionOppositeCorner||yfmPlayerfirstPositionSideCorner)
                {
                    if(yfmPlayerThirdPosition==getInbetween(yfmfirstPosition,yfmThirdPosition))
                    {
                        yfmFourthPosition=getInbetween(yfmSecondPosition,yfmThirdPosition);
                        yuvaMove(yfmFourthPosition,yuvaSymbolId);
                    }
                    else
                    {
                        yfmFourthPosition=getInbetween(yfmfirstPosition,yfmThirdPosition);
                        yuvaMove(yfmFourthPosition,yuvaSymbolId);
                    }
                }
                yfmNeedToTrackThirdMoveofPlayer=false;
            }
            else {
                getEmptyPositions();
                Random random = new Random();
                int randomNumber = emptyPositions.get(random.nextInt(emptyPositions.size()));
                yuvaMove(randomNumber, yuvaSymbolId);
            }
        }
        else
        {
            if(ysmneedToTrackFirstMoveofPlayer)
            {
                ysmneedToTrackSecondMoveofPlayer=true;
                if(isCornerBlock(ysmPlayerfirstposition))
                {
                    ysmfirstPosition=middlePosition;
                    yuvaMove(ysmfirstPosition,yuvaSymbolId);
                    ysmPlayerFirstMoveIsCorner=true;
                    Log.v(" ysm corner","1st player corner");
                }
                else if(isSideblock(ysmPlayerfirstposition))
                {
                    ysmfirstPosition=middlePosition;
                    yuvaMove(ysmfirstPosition,yuvaSymbolId);
                    ysmPlayerFirstMoveIsSide=true;
                    Log.v(" ysm side","1st player side");
                }
                else if(ysmPlayerfirstposition==middlePosition)
                {
                    Random random= new Random();
                    ArrayList<Integer> emptyCorners=getEmptyCorners();
                    ysmfirstPosition=emptyCorners.get(random.nextInt(emptyCorners.size()));
                    yuvaMove(ysmfirstPosition,yuvaSymbolId);
                    ysmPlayerFirstMoveIsMiddle=true;
                    Log.v(" ysm middle","1st player middle");
                }
                ysmneedToTrackFirstMoveofPlayer=false;
            }
            else if(ysmneedToTrackSecondMoveofPlayer)
            {
                ysmNeedToTrackThirdMoveofPlayer=true;
                Log.v(" analyzing ","second move");
                if(ysmPlayerFirstMoveIsCorner)
                {
                    Log.v(" ysm corner","1st player corner");
                    if(ysmPlayerSecondPosition==getOppositeCorner(ysmPlayerfirstposition))
                    {
                        Random random = new Random();
                        ArrayList<Integer> emptySides = getEmptySideBlocks();
                        ysmSecondPosition = emptySides.get(random.nextInt(emptySides.size()));
                        yuvaMove(ysmSecondPosition, yuvaSymbolId);
                        ysmPlayerMoveIsCornerAndOppositeCorner=true;
                        Log.v(" ysm corner","1st player opposite corner");
                    }
                    else if(isSideCorner(ysmPlayerfirstposition,ysmPlayerSecondPosition))
                    {
                        ysmSecondPosition=getInbetween(ysmPlayerfirstposition,ysmPlayerSecondPosition);
                        yuvaMove(ysmSecondPosition,yuvaSymbolId);
                        ysmPlayerMoveIsCornerAndSideCorner=true;
                        Log.v(" ysm corner","1st player sideCorner");
                    }
                    else
                    {
                        for(int i:getEmptyCorners())
                        {
                            if(isNearOfCorner(ysmPlayerSecondPosition,i)&&(positionStatus[getOppositeCorner(i)]==0))
                            {
                                ysmSecondPosition=i;
                                break;
                            }
                        }
                        yuvaMove(ysmSecondPosition,yuvaSymbolId);
                        ysmPlayerMoveIsCornerAndSide=true;
                        Log.v(" ysm corner","1st player side");
                    }

                }
                else if(ysmPlayerFirstMoveIsMiddle)
                {
                    if(isOppositeCorners(ysmfirstPosition,ysmPlayerSecondPosition))
                    {
                        Random random= new Random();
                        ArrayList<Integer> emptyCorners=getEmptyCorners();
                        ysmSecondPosition=emptyCorners.get(random.nextInt(emptyCorners.size()));
                        yuvaMove(ysmSecondPosition,yuvaSymbolId);
                        ysmPlayerMoveIsMiddleAndOppositeCorner=true;
                        Log.v(" ysm middle","1st player Opposite corner");
                    }
                    else if(isSideCorner(ysmfirstPosition,ysmPlayerSecondPosition))
                    {
                        ysmSecondPosition=getOppositeCorner(ysmPlayerSecondPosition);
                        yuvaMove(ysmSecondPosition,yuvaSymbolId);
                        ysmPlayerMoveIsMiddleAndSideCorner=true;
                        Log.v(" ysm middle","1st player side corner");
                    }
                    else if(!isNearOfCorner(ysmPlayerSecondPosition,ysmfirstPosition))
                    {
                        ysmSecondPosition=getStraightBlockForSide(ysmPlayerSecondPosition);
                        yuvaMove(ysmSecondPosition,yuvaSymbolId);
                        ysmPlayerMoveIsMiddleAndFarSide=true;
                        Log.v(" ysm middle","1st player far side ");
                    }
                    else
                    {
                        ysmSecondPosition=getStraightBlockForSide(ysmPlayerSecondPosition);
                        yuvaMove(ysmSecondPosition,yuvaSymbolId);
                        ysmPlayerMoveIsMiddleAndNearSide=true;
                        Log.v(" ysm middle","1st player near side ");
                    }
                }
                else if(ysmPlayerFirstMoveIsSide)
                {
                    if(isNearOfCorner(ysmPlayerfirstposition,ysmPlayerSecondPosition))
                    {
                        ysmPlayerMoveisSideandNearCorner=true;
                        for(int i:
                                getEmptyCorners())
                        {
                            if(isNearOfCorner(ysmPlayerfirstposition,i))
                            {
                                ysmSecondPosition=i;
                                yuvaMove(ysmSecondPosition,yuvaSymbolId);
                                break;
                            }
                        }
                    }
                    else if(isSideblock(ysmPlayerSecondPosition)&&(ysmPlayerSecondPosition==getStraightBlockForSide(ysmPlayerfirstposition)))
                    {
                        Random random = new Random();
                        ArrayList<Integer> emptyCorners=getEmptyCorners();
                        ysmSecondPosition=emptyCorners.get(random.nextInt(4));
                        yuvaMove(ysmSecondPosition,yuvaSymbolId);
                        ysmPlayerMoveisSideandFarSide=true;
                    }
                    else if(isSideblock(ysmPlayerSecondPosition)&&(ysmPlayerSecondPosition!=getStraightBlockForSide(ysmPlayerfirstposition)))
                    {
                        ArrayList<Integer> boxes=new ArrayList<>();
                        int first=ysmPlayerfirstposition;
                        int second=ysmPlayerSecondPosition;
                        for(int i:corners)
                        {
                            if(((isNearOfCorner(first,i))&&(!isNearOfCorner(second,i)))||(((isNearOfCorner(second,i))&&(!isNearOfCorner(first,i)))))
                            {
                                boxes.add(i);
                            }
                        }
                        Random random= new Random();
                        ysmSecondPosition=boxes.get(random.nextInt(2));
                        yuvaMove(ysmSecondPosition,yuvaSymbolId);
                        ysmPlayerMoveisSideandNearSide=true;
                    }
                    else
                    {
                        ysmPlayerMoveisSideandFarCorner=true;
                        for(int i:getEmptyCorners())
                        {
                            if(isNearOfCorner(ysmPlayerfirstposition,i)&&(positionStatus[getOppositeCorner(i)]==0))
                            {
                                ysmSecondPosition=i;
                                yuvaMove(ysmSecondPosition,yuvaSymbolId);
                                break;
                            }
                        }
                    }
                }
                ysmneedToTrackSecondMoveofPlayer=false;
            }
            else if(ysmNeedToTrackThirdMoveofPlayer)
            {
                ysmNeedToTrackFourthMoveofPlayer=true;
                Log.v("analyzing","third move");
                if(ysmPlayerFirstMoveIsCorner)
                {
                    Log.v(" ysm corner","1st player corner");
                    if(ysmPlayerMoveIsCornerAndOppositeCorner)
                    {
                        int straightSide = getStraightBlockForSide(ysmSecondPosition);
                        if (ysmPlayerThirdPosition != straightSide) {
                            ysmThirdPosition = straightSide;
                            yuvaMove(ysmThirdPosition, yuvaSymbolId);
                        } else {
                            Log.v("player position", "not straight to my second position");
                            ArrayList<Integer> emptyCorners = getEmptyCorners();
                            ysmThirdPosition = emptyCorners.get(0);
                            Log.v("first corner", " " + ysmThirdPosition);
                            int inBetween1And3=getInbetween(ysmThirdPosition, ysmPlayerfirstposition);
                            int inBetween1And2=getInbetween(ysmThirdPosition, ysmPlayerSecondPosition);
                            if ((ysmPlayerThirdPosition==inBetween1And2)||(ysmPlayerThirdPosition==inBetween1And3)) {
                                Log.v("this corner is", "is acceptable");
                                yuvaMove(ysmThirdPosition, yuvaSymbolId);
                            } else {
                                Log.v("this corner is", "is  not " + "acceptable");
                                ysmThirdPosition = emptyCorners.get(1);
                                yuvaMove(ysmThirdPosition, yuvaSymbolId);
                            }
                        }
                        Log.v(" ysm corner","1st player oppositeCorner");
                    }
                    else if(ysmPlayerMoveIsCornerAndSideCorner)
                    {
                        Log.v(" ysm corner","1st player side corner");
                        int straight=getStraightBlockForSide(ysmSecondPosition);
                        Log.v(" ysm corner","straight is "+straight);
                        if(ysmPlayerThirdPosition!=straight)
                        {
                            ysmThirdPosition=straight;
                            Log.v(" ysm corner","straight is empty");
                            yuvaMove(ysmThirdPosition, yuvaSymbolId);
                        }
                        else
                        {
                            Random random=new Random();
                            ArrayList<Integer> emptySides=getEmptySideBlocks();
                            ysmThirdPosition=emptySides.get(random.nextInt(2));
                            yuvaMove(ysmThirdPosition,yuvaSymbolId);
                            Log.v(" ysm corner","straight is not empty");
                        }
                    }
                    else if(ysmPlayerMoveIsCornerAndSide)
                    {
                        if(ysmPlayerThirdPosition==getOppositeCorner(ysmSecondPosition)) {
                            ysmThirdPosition = getInbetween(ysmPlayerfirstposition, ysmPlayerThirdPosition);
                            yuvaMove(ysmThirdPosition, yuvaSymbolId);
                        }
                        else
                            {
                                ysmThirdPosition=getOppositeCorner(ysmSecondPosition);
                                yuvaMove(ysmThirdPosition,yuvaSymbolId);
                            }
                    }
                }
                else if(ysmPlayerFirstMoveIsMiddle)
                {
                    if(ysmPlayerMoveIsMiddleAndOppositeCorner)
                    {
                        if(ysmPlayerThirdPosition!=getInbetween(ysmfirstPosition,ysmSecondPosition))
                        {
                            ysmThirdPosition=getInbetween(ysmfirstPosition,ysmSecondPosition);
                            yuvaMove(ysmThirdPosition,yuvaSymbolId);
                        }
                        else
                        {
                            ysmThirdPosition=getStraightBlockForSide(ysmPlayerThirdPosition);
                            yuvaMove(ysmThirdPosition,yuvaSymbolId);
                        }
                    }
                    else if(ysmPlayerMoveIsMiddleAndSideCorner)
                    {
                        int middle=getInbetween(ysmfirstPosition,ysmSecondPosition);
                        if(positionStatus[middle]==0)
                        {
                            ysmThirdPosition=middle;
                            yuvaMove(ysmThirdPosition,yuvaSymbolId);
                        }
                        else
                        {
                            ysmThirdPosition=getStraightBlockForSide(ysmPlayerThirdPosition);
                            yuvaMove(ysmThirdPosition,yuvaSymbolId);
                        }
                    }
                    else if(ysmPlayerMoveIsMiddleAndFarSide)
                    {
                        if(ysmSecondPosition==getInbetween(ysmfirstPosition,ysmPlayerThirdPosition))
                        {
                            int corner=getCross(ysmPlayerThirdPosition,middlePosition);
                            ysmThirdPosition=corner;
                            yuvaMove(ysmThirdPosition,yuvaSymbolId);
                        }
                        else
                        {
                            for(int i:getEmptyCorners())
                            {
                                if(ysmSecondPosition==getInbetween(ysmfirstPosition,i))
                                {
                                    ysmThirdPosition=i;
                                    yuvaMove(ysmThirdPosition,yuvaSymbolId);
                                    break;
                                }
                            }
                        }

                    }
                    else if(ysmPlayerMoveIsMiddleAndNearSide)
                    {
                        if(ysmPlayerThirdPosition==getOppositeCorner(ysmfirstPosition))
                        {
                            ArrayList<Integer> emptyCorners=getEmptyCorners();
                            ysmThirdPosition=emptyCorners.get(0);
                            if(isNearOfCorner(ysmSecondPosition,ysmThirdPosition))
                            {
                                yuvaMove(ysmThirdPosition, yuvaSymbolId);
                            }
                            else
                            {
                                ysmThirdPosition=emptyCorners.get(1);
                                yuvaMove(ysmThirdPosition,yuvaSymbolId);
                            }
                            ysmPlayerMoveIsMiddleAndNearSideAndOppositeCorner=true;
                        }
                        else if(isSideblock(ysmPlayerThirdPosition))
                        {
                            ysmThirdPosition=getStraightBlockForSide(ysmPlayerThirdPosition);
                            yuvaMove(ysmThirdPosition,yuvaSymbolId);
                            ysmPlayerMoveIsMiddleAndNearSideAndSide=true;
                        }
                        else
                        {
                            ysmThirdPosition=getOppositeCorner(ysmPlayerThirdPosition);
                            yuvaMove(ysmThirdPosition,yuvaSymbolId);
                            ysmPlayerMoveIsMiddleAndNearSideAndSideCorner=true;
                            if (positionStatus[getInbetween(ysmfirstPosition, ysmThirdPosition)] == 0) {
                                specialCase=true;
                            }
                        }
                    }
                }
                else if(ysmPlayerFirstMoveIsSide)
                {
                    if(ysmPlayerMoveisSideandNearCorner)
                    {
                        if(ysmPlayerThirdPosition!=getOppositeCorner(ysmSecondPosition))
                        {
                            ysmThirdPosition=getOppositeCorner(ysmSecondPosition);
                            yuvaMove(ysmThirdPosition,yuvaSymbolId);
                        }
                        else
                        {
                            ysmThirdPosition=getInbetween(ysmPlayerSecondPosition,ysmPlayerThirdPosition);
                            yuvaMove(ysmThirdPosition,yuvaSymbolId);
                        }
                    }
                    else if(ysmPlayerMoveisSideandFarCorner)
                    {
                        if(ysmPlayerThirdPosition!=getOppositeCorner(ysmSecondPosition))
                        {
                            ysmThirdPosition=getOppositeCorner(ysmSecondPosition);
                            yuvaMove(ysmThirdPosition,yuvaSymbolId);
                        }
                        else
                        {
                            ysmThirdPosition=getInbetween(ysmPlayerThirdPosition,ysmPlayerSecondPosition);
                            yuvaMove(ysmThirdPosition,yuvaSymbolId);
                        }
                    }
                    else if(ysmPlayerMoveisSideandNearSide)
                    {
                        if(ysmPlayerThirdPosition!=getOppositeCorner(ysmSecondPosition))
                        {
                            ysmThirdPosition=getOppositeCorner(ysmSecondPosition);
                            yuvaMove(ysmThirdPosition,yuvaSymbolId);
                        }
                        else
                        {
                            ArrayList<Integer> emptyConers=getEmptyCorners();
                            int corner=emptyConers.get(0);
                            if(isNearOfCorner(ysmPlayerfirstposition,corner)||isNearOfCorner(ysmPlayerSecondPosition,corner))
                            {
                                ysmThirdPosition=corner;
                                yuvaMove(ysmThirdPosition,yuvaSymbolId);
                            }
                            else
                            {
                                ysmThirdPosition=emptyConers.get(1);
                                yuvaMove(ysmThirdPosition,yuvaSymbolId);
                            }
                        }
                    }
                    else if(ysmPlayerMoveisSideandFarSide)
                    {
                        if(ysmPlayerThirdPosition!=getOppositeCorner(ysmSecondPosition))
                        {
                            ysmThirdPosition=getOppositeCorner(ysmSecondPosition);
                            yuvaMove(ysmThirdPosition,yuvaSymbolId);
                        }
                        else
                        {
                            ArrayList<Integer> emptyCorners=getEmptyCorners();
                            int corner=emptyCorners.get(0);
                            if((ysmPlayerfirstposition==getInbetween(corner,ysmPlayerThirdPosition))||(ysmPlayerSecondPosition==getInbetween(corner,ysmPlayerThirdPosition)))
                            {
                                ysmThirdPosition=corner;
                                yuvaMove(ysmThirdPosition,yuvaSymbolId);
                            }
                            else
                            {
                                ysmThirdPosition=emptyCorners.get(1);
                                yuvaMove(ysmThirdPosition,yuvaSymbolId);
                            }
                        }
                    }
                }
                ysmNeedToTrackThirdMoveofPlayer=false;
            }
            else if(ysmNeedToTrackFourthMoveofPlayer)
            {
                if(ysmPlayerFirstMoveIsCorner)
                {
                    if(ysmPlayerMoveIsCornerAndOppositeCorner)
                    {
                        int corner = getOppositeCorner(ysmThirdPosition);
                        if (ysmPlayerFourthPosition != corner) {
                            ysmFourthPosition = corner;
                            yuvaMove(ysmFourthPosition, yuvaSymbolId);
                        } else {
                            ysmFourthPosition = getInbetween(ysmPlayerfirstposition, ysmPlayerFourthPosition);
                            if (positionStatus[ysmFourthPosition] == 0) {
                                yuvaMove(ysmFourthPosition, yuvaSymbolId);
                            } else {
                                ysmFourthPosition = getInbetween(ysmPlayerSecondPosition, ysmPlayerFourthPosition);
                                yuvaMove(ysmFourthPosition, yuvaSymbolId);
                            }
                        }
                    }
                    else if(ysmPlayerMoveIsCornerAndSideCorner)
                    {
                        ysmFourthPosition=getStraightBlockForSide(ysmThirdPosition);
                        Log.v("straight block is empty"," "+ysmFourthPosition);
                        if(ysmPlayerFourthPosition!=ysmFourthPosition)
                        {
                            yuvaMove(ysmFourthPosition,yuvaSymbolId);
                        }
                        else
                        {
                            ArrayList<Integer> emptyCorners=getEmptyCorners();
                            if(isNearOfCorner(ysmPlayerFourthPosition,emptyCorners.get(0)))
                            {
                                ysmFourthPosition=emptyCorners.get(0);
                                yuvaMove(ysmFourthPosition,yuvaSymbolId);
                            }
                            else
                            {
                                ysmFourthPosition=emptyCorners.get(1);
                                yuvaMove(ysmFourthPosition,yuvaSymbolId);
                            }
                        }
                    }
                    else if(ysmPlayerMoveIsCornerAndSide)
                    {
                        if(positionStatus[getStraightBlockForSide(ysmThirdPosition)]==0)
                        {
                            ysmFourthPosition=getStraightBlockForSide(ysmThirdPosition);
                            yuvaMove(ysmFourthPosition,yuvaSymbolId);
                        }
                        else
                        {
                            ArrayList<Integer> emptyCorners=getEmptyCorners();
                            if(emptyCorners.size()>0)
                            {
                                ysmFourthPosition=emptyCorners.get(0);
                                yuvaMove(ysmFourthPosition,yuvaSymbolId);
                            }
                            else
                            {
                                ysmFourthPosition=getInbetween(ysmPlayerThirdPosition,ysmPlayerFourthPosition);
                                yuvaMove(ysmFourthPosition,yuvaSymbolId);
                            }
                        }

                    }

                }
                if(ysmPlayerFirstMoveIsMiddle)
                {
                    if(ysmPlayerMoveIsMiddleAndOppositeCorner||ysmPlayerMoveIsMiddleAndSideCorner)
                    {
                        ArrayList<Integer> emptySides=getEmptySideBlocks();
                        ysmFourthPosition=emptySides.get(0);
                        yuvaMove(ysmFourthPosition,yuvaSymbolId);
                    }
                    else if(ysmPlayerMoveIsMiddleAndFarSide)
                    {
                        int middle=getInbetween(ysmfirstPosition,ysmThirdPosition);

                        if(middle!=ysmPlayerFourthPosition)
                        {
                            ysmFourthPosition=middle;
                            yuvaMove(ysmFourthPosition,yuvaSymbolId);
                        }
                        else
                        {
                            ysmFourthPosition=getStraightBlockForSide(ysmPlayerFourthPosition);
                            yuvaMove(ysmFourthPosition,yuvaSymbolId);
                        }
                    }
                    else if(ysmPlayerMoveIsMiddleAndNearSide)
                    {
                        if (ysmPlayerMoveIsMiddleAndNearSideAndSideCorner)
                        {
                            if(specialCase)
                            {
                                Log.v("special case","true");
                                if (positionStatus[getInbetween(ysmfirstPosition, ysmThirdPosition)] == 0)
                                {
                                    ysmFourthPosition = getInbetween(ysmfirstPosition, ysmThirdPosition);
                                    yuvaMove(ysmFourthPosition, yuvaSymbolId);
                                }
                                else
                                {
                                    ArrayList<Integer> emptyCorners = getEmptyCorners();
                                    ysmFourthPosition = emptyCorners.get(0);
                                    yuvaMove(ysmFourthPosition, yuvaSymbolId);
                                }
                            }
                            else
                            {
                                Log.v("special case","false");
                                ArrayList<Integer> emptySides = getEmptySideBlocks();
                                Random random = new Random();
                                ysmFourthPosition=emptySides.get(random.nextInt(emptySides.size()));
                                yuvaMove(ysmFourthPosition,yuvaSymbolId);
                            }

                        }
                        else if (ysmPlayerMoveIsMiddleAndNearSideAndSide)
                        {
                            for(int i:corners)
                            {
                                if((isNearOfCorner(ysmSecondPosition,i))&&(isNearOfCorner(ysmThirdPosition,i)))
                                {
                                    if((positionStatus[getOppositeCorner(i)]==0))
                                    {
                                        if(positionStatus[i]==0)
                                        {
                                            Log.v("that corner is "+i,"empty ");
                                            ysmFourthPosition=i;
                                            yuvaMove(ysmFourthPosition,yuvaSymbolId);
                                        }
                                        else
                                        {
                                            ysmFourthPosition=getOppositeCorner(ysmPlayerFourthPosition);
                                            yuvaMove(ysmFourthPosition,yuvaSymbolId);
                                        }
                                    }
                                    else
                                    {
                                        if(positionStatus[i]==0)
                                        {
                                            Log.v("that corner is "+i,"empty ");
                                            ysmFourthPosition=getOppositeCorner(ysmPlayerFourthPosition);
                                            yuvaMove(ysmFourthPosition,yuvaSymbolId);
                                        }
                                        else
                                        {
                                            Log.v("that corner is "+i,"empty ");
                                            Random random = new Random();
                                            ArrayList<Integer> emptyCorners=getEmptyCorners();
                                            ysmfirstPosition=emptyCorners.get(random.nextInt(emptyCorners.size()));
                                        }
                                    }

                                    break;
                                }
                            }
                        }
                        else if(ysmPlayerMoveIsMiddleAndNearSideAndOppositeCorner)
                        {
                            if(positionStatus[getInbetween(ysmThirdPosition,ysmfirstPosition)]==0)
                            {
                                ysmFourthPosition=getInbetween(ysmThirdPosition,ysmfirstPosition);
                                yuvaMove(ysmFourthPosition,yuvaSymbolId);
                            }
                            else
                            {
                                ysmFourthPosition=getStraightBlockForSide(ysmPlayerFourthPosition);
                                yuvaMove(ysmFourthPosition,yuvaSymbolId);
                            }

                        }
                    }

                }
                else if(ysmPlayerFirstMoveIsSide)
                {
                    if(ysmPlayerMoveisSideandNearCorner)
                    {
                        if(ysmPlayerFourthPosition!=getStraightBlockForSide(ysmThirdPosition))
                        {
                            ysmFourthPosition=getStraightBlockForSide(ysmThirdPosition);
                            yuvaMove(ysmFourthPosition,yuvaSymbolId);
                        }
                        else
                        {
                            getEmptyPositions();
                            Random random = new Random();
                            int randomNumber = emptyPositions.get(random.nextInt(emptyPositions.size()));
                            yuvaMove(randomNumber, yuvaSymbolId);
                        }
                    }
                    else if(ysmPlayerMoveisSideandFarCorner)
                    {
                        if(isCornerBlock(ysmPlayerFourthPosition))
                        {
                            ArrayList<Integer> emptySides=getEmptySideBlocks();
                            int side=emptySides.get(0);
                            if(isNearOfCorner(side,ysmPlayerFourthPosition))
                            {
                                ysmFourthPosition=side;
                                yuvaMove(ysmFourthPosition,yuvaSymbolId);
                            }
                            else
                            {
                                ysmFourthPosition=emptySides.get(1);
                                yuvaMove(ysmFourthPosition,yuvaSymbolId);
                            }
                        }
                        else
                        {
                            ArrayList<Integer> emptyCorners=getEmptyCorners();
                            ysmFourthPosition=emptyCorners.get(0);
                            yuvaMove(ysmFourthPosition,yuvaSymbolId);
                        }
                    }
                    else if(ysmPlayerMoveisSideandNearSide)
                    {
                        if(!isCornerBlock(ysmPlayerFourthPosition))
                        {
                            ArrayList<Integer> emptyCorers=getEmptyCorners();
                            ysmFourthPosition= emptyCorers.get(0);
                            yuvaMove(ysmFourthPosition,yuvaSymbolId);
                        }
                        else
                        {
                            Random random= new Random();
                            getEmptyPositions();
                            ysmFourthPosition=emptyPositions.get(random.nextInt(emptyPositions.size()));
                            yuvaMove(ysmFourthPosition,yuvaSymbolId);
                        }
                    }
                    else if(ysmPlayerMoveisSideandFarSide)
                    {
                        if(!isCornerBlock(ysmPlayerFourthPosition))
                        {
                            ArrayList<Integer> emptyCorners=getEmptyCorners();
                            ysmFourthPosition=emptyCorners.get(0);
                            yuvaMove(ysmFourthPosition,yuvaSymbolId);
                        }
                        else
                        {
                            ysmFourthPosition=getInbetween(ysmSecondPosition,ysmThirdPosition);
                            yuvaMove(ysmFourthPosition,yuvaSymbolId);
                        }
                    }
                }
                ysmNeedToTrackFourthMoveofPlayer=false;
            }
            else
                {
                getEmptyPositions();
                Random random = new Random();
                int randomNumber = emptyPositions.get(random.nextInt(emptyPositions.size()));
                yuvaMove(randomNumber, yuvaSymbolId);
            }
        }

    }

    private int getCross(int x, int y) {
        if(((x==0)&&(y==4))||((x==4)&&(y==0)))
        {
            return 8;
        }
        else if(((x==2)&&(y==4))||((x==4)&&(y==2)))
        {
            return 6;
        }
        else if(((x==6)&&(y==4))||((x==4)&&(y==6)))
        {
            return 2;
        }
        else if(((x==4)&&(y==8))||((x==8)&&(y==4)))
        {
            return 0;
        }
        else
            return -1;
    }

    private ArrayList<Integer> getEmptySideBlocks()
    {
        ArrayList<Integer> emptySideBlocks= new ArrayList<>();
        for(int i:sides)
        {
            if(positionStatus[i]==0)
            {
                emptySideBlocks.add(i);
            }
        }
        return emptySideBlocks;
    }

    private ArrayList<Integer> getEmptyCorners()
    {
        ArrayList<Integer> emptyCorners=new ArrayList<>();
        for(int i:corners)
        {
            if(positionStatus[i]==0)
            {
                emptyCorners.add(i);
            }
        }
        return emptyCorners;
    }

    private int getStraightBlockForSide(int position)
    {
        if(position==1)
            return 7;
        else if(position==3)
            return 5;
        else if(position==7)
            return 1;
        else if(position==5)
            return 3;
        else
            return -1;
    }

    private boolean isNearOfCorner(int position,int comparator)
    {
        if(comparator==0&&(position==1||position==3))
        {
            return true;
        }
        else if(comparator==2&&(position==1||position==5))
        {
            return true;
        }
        else if(comparator==6&&(position==7||position==3))
        {
            return true;
        }
        else if(comparator==8&&(position==5||position==7))
        {
            return true;
        }
        else
            return false;
    }

    private ArrayList<Integer> getEmptySideCorners(int position)
    {
        ArrayList<Integer> sideCorner=new ArrayList<>();
        if(position==0||position==8) {
            sideCorner.add(2);
            sideCorner.add(6);
        }
        else
        {
            sideCorner.add(0);
            sideCorner.add(8);
        }
        return sideCorner;

    }

    public void getEmptyPositions()
    {
        emptyPositions=new ArrayList<>();
        for(int i=0;i<9;i++)
        {
            if(positionStatus[i]==0)
            {
                emptyPositions.add(i);
            }
        }
    }
    public void getRemainingCorners()
    {
        remainingCorners=new ArrayList<Integer>();
        for(int i:corners)
        {
            if(positionStatus[i]==0)
            {
                remainingCorners.add(i);
            }
        }
    }
    public void getRemainingSides()
    {
        remainingSides=new ArrayList<Integer>();
        for(int i:sides)
        {
            if(positionStatus[i]==0)
            {
                remainingSides.add(i);
            }
        }
    }
    public boolean checkForDraw()
    {
        for(int i:positionStatus)
        {
            if(i==0)
            {
                return false;
            }
        }
        return true;
    }
    public void yuvaMakeYourFirstMove()
    {
        Random random= new Random();

        int randomNumber=random.nextInt(4);
        yfmfirstPosition =corners[randomNumber];
        yuvaMove(yfmfirstPosition,yuvaSymbolId);
        yuvaFirstMove=true;
        yfmneedToTrackFirstMoveofPlayer =true;
    }
    public boolean isSideblock(int position)
    {
        for(int i:sides)
        {
            if(position==i)
            {
                return true;
            }
        }
        return false;
    }
    public boolean isCornerBlock(int position)
    {
        for(int i:corners)
        {
            if(position==i)
            {
                return true;
            }
        }
        return false;
    }
    public boolean isMiddle(int position)
    {
        if(position==4)
            return true;
        else
            return false;
    }
    public boolean isOppositeCorner(int position)
    {
        if(position==0&& yfmfirstPosition ==8)
            return true;
        else if(position==2&& yfmfirstPosition ==6)
            return true;
        else if(position==6&& yfmfirstPosition ==2)
            return true;
        else if(position==8&& yfmfirstPosition ==0)
            return true;
        else
            return false;
    }
    public boolean isOppositeCorners(int x,int y)
    {
        if(x==0&& y ==8)
            return true;
        else if(x==2&& y ==6)
            return true;
        else if(x==6&& y ==2)
            return true;
        else if(x==8&& y ==0)
            return true;
        else
            return false;
    }
    public boolean isSideCorner(int position)
    {
        if((position==0||position==8)&&(yfmfirstPosition ==2|| yfmfirstPosition ==6))
            return true;
        else if((position==2||position==6)&&(yfmfirstPosition ==0|| yfmfirstPosition ==8))
            return true;
        else
            return false;
    }
    public boolean isSideCorner(int x,int y)
    {
        if((x==0||x==8)&&(y ==2|| y ==6))
            return true;
        else if((x==2||x==6)&&(y ==0|| y ==8))
            return true;
        else
            return false;
    }
    public int getOppositeCorner(int position)
    {
        if(position==0)
            return 8;
        else if(position==2)
            return 6;
        else if(position==6)
            return 2;
        else if(position==8)
            return 0;
        return -1;
    }
    public int getSideCorner(int position)
    {
        ArrayList<Integer> sideCorner=new ArrayList<>();
        if(position==0||position==8) {
            sideCorner.add(2);
            sideCorner.add(6);
        }
        else
        {
            sideCorner.add(0);
            sideCorner.add(8);
        }
        Random random = new Random();
        return sideCorner.get(random.nextInt(2));
    }
    public int getInbetween(int x,int y)
    {
        if((x==0&&y==2)||(x==2&&y==0))
            return 1;
        else if((x==3&&y==5)||(x==5&&y==3))
            return 4;
        else if((x==6&&y==8)||(x==8&&y==6))
            return 7;
        else if((x==0&&y==6)||(x==6&&y==0))
            return 3;
        else if((x==1&&y==7)||(x==7&&y==1))
            return 4;
        else if((x==2&&y==8)||(x==8&&y==2))
            return 5;
        else if((x==0&&y==8)||(x==8&&y==0))
            return 4;
        else if((x==2&&y==6)||(x==6&&y==2))
            return 4;
        else
            return -1;
    }
    public void showGameOverDialog()
    {
        int time;
        if(winner==0)
        {
            time=0;
        }
        else
        {
            time=1000;
        }
        CountDownTimer countDownTimer = new CountDownTimer(time,time) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish()
            {
                AlertDialog.Builder builder= new AlertDialog.Builder(GameActivity.this);
                builder.setMessage(""+getString(R.string.game_over)+"\n"+"                 "+matchStatus);
                builder.setNegativeButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setPositiveButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        resetGame();
                    }
                });
                Resources resources = getResources();
                Drawable negativeIcon= ResourcesCompat.getDrawable(resources,R.drawable.ic_home_red,null);
                Drawable positiveIcon=ResourcesCompat.getDrawable(resources,R.drawable.ic_replay_blue,null);
                builder.setPositiveButtonIcon(positiveIcon);
                builder.setNegativeButtonIcon(negativeIcon);
                AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(false);
                alertDialog.show();
            }
        }.start();


    }
    public void resetGame()
    {
//        Intent intent=new Intent(this,GameActivity.class);
//        intent.putExtra("match_status",winner);
//        intent.putExtra("mode",presentGameMode);
//        startActivity(intent);
//        Log.v("match","playAgain");
        Intent intent =getIntent();
        finish();
        startActivity(intent);
    }
    public void setGameOverAnimation(int a,int b,int c)
    {

        ImageView one=(ImageView)findViewById(idOfPositions[a]);
        ImageView two=(ImageView)findViewById(idOfPositions[b]);
        ImageView three=(ImageView)findViewById(idOfPositions[c]);

        one.animate().translationXBy(-1500).translationYBy(-1500).setDuration(2000);
        two.animate().translationXBy(-1500).translationYBy(-1500).setDuration(2000);
        three.animate().translationXBy(-1500).translationYBy(-1500).setDuration(2000);
    }
    public void showGameNotEnded()
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(GameActivity.this);
        builder.setMessage("Game not Completed");
        builder.setNegativeButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                goHome();
            }
        });
        builder.setNeutralButton("restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetGame();
            }
        });
        builder.setPositiveButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if(dialog!=null)
                {
                    dialog.dismiss();
                }
            }
        });
        Resources resources = getResources();
        Drawable negativeIcon= ResourcesCompat.getDrawable(resources,R.drawable.ic_home_red,null);
        Drawable positiveIcon=ResourcesCompat.getDrawable(resources,R.drawable.ic_play,null);
        Drawable neutralIcon=ResourcesCompat.getDrawable(resources,R.drawable.ic_replay_blue,null);
        builder.setPositiveButtonIcon(positiveIcon);
        builder.setNegativeButtonIcon(negativeIcon);
        builder.setNeutralButtonIcon(neutralIcon);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed()
    {
//        Toast.makeText(this," "+gameEnded,Toast.LENGTH_SHORT).show();
        if(gameEnded)
        {
//            goHome();
            finish();
        }
        else
        {
            showGameNotEnded();
        }
    }
    public void goHome()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId()==android.R.id.home)
        {
            showGameNotEnded();
            return true;
        }
        else
        {
            return
                    super.onOptionsItemSelected(item);
        }
    }
}
