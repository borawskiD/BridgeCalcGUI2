package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDeck {
    public String[] run(){
        String suits[] = {"H", "D", "C", "S"};
        String cards[] = {"2", "3", "4", "5", "6", "7", "8", "9", "X", "J", "Q", "K", "A" };
        Random random = new Random();
        List<String> fullDeck = new ArrayList<>();
        List<String> deck = new ArrayList<>();
        for(int i = 0; i<4; i++){
            for (String card : cards) {
                fullDeck.add(suits[i] +"" + card);
            }
        }
        for(int i = 0; i < 13; i++){
            int index = random.nextInt(fullDeck.size()-1);
            deck.add(fullDeck.get(index));
            fullDeck.remove(fullDeck.get(index));
        }
        System.out.println(deck);
        return makeSequence(deck);
    }
    public String[] makeSequence(List<String> deck){
        StringBuilder S = new StringBuilder("S");
        StringBuilder H = new StringBuilder("H");
        StringBuilder D = new StringBuilder("D");
        StringBuilder C = new StringBuilder("C");
        for (String s : deck) {
            if(s.charAt(0) == 'S'){
                S.append(s.charAt(1));
            } else if(s.charAt(0) == 'H'){
                H.append(s.charAt(1));
            } else if(s.charAt(0) == 'D'){
                D.append(s.charAt(1));
            } else if(s.charAt(0) == 'C'){
                C.append(s.charAt(1));
            }
        }
        String s = S.toString();
        String h = H.toString();
        String d = D.toString();
        String c = C.toString();
        String [] output = new String[]{s,h,d,c};
        return output;
    }
}
