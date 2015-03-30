package com.example;

import java.util.Comparator;

/**
 * Created by joe-work on 3/15/15.
 */
public class SingleMatch implements Comparable<SingleMatch>{

    public int profileId;
    public double score;

    public SingleMatch(final int profileId, final double score){
        this.profileId = profileId;
        this.score = score;
    }

    public int compareTo(SingleMatch compareMatch) {

        double compareQuantity = compareMatch.score;

        //descending order
        double diff = compareQuantity - this.score;
        if(diff < 0) return -1;
        if(diff > 0) return 1;
        return 0;

    }
}
