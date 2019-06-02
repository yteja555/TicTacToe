package com.example.tictactoe.modes;

import android.content.Context;
import android.util.Log;

import com.example.tictactoe.GameActivity;
import com.example.tictactoe.R;

import java.util.Random;

public class FriendMode {
    Context context;

    public FriendMode(Context context) {
        this.context = context;
    }

//    public static void initializeFriendSymbols() {
//        Random random = new Random();
//        int first = random.nextInt(10);
//        Log.v("random number is ", " " + first);
//        if (first > 5) {
//            GameActivity.player1SymbolId = R.drawable.ic_o_symbol;
//            GameActivity.player2SymbolId = R.drawable.ic_x_symbol;
//            GameActivity.activePlayer = 1;
//        } else {
//            GameActivity.player2SymbolId = R.drawable.ic_o_symbol;
//            GameActivity.player1SymbolId = R.drawable.ic_x_symbol;
//            GameActivity.activePlayer = 2;
//
//        }
//    }
}
