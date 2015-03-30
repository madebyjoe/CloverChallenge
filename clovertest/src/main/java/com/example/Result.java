package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by joe-work on 3/15/15.
 */
public class Result {

    public int profileId;
    public List<SingleMatch> matches = new ArrayList<SingleMatch>(); //this is sorted

    public Result(final int id) {
        this.profileId = id;
    }

    //should be sorted
    public void addMatch(final SingleMatch match) {
        matches.add(match);
        Collections.sort(matches);
    }

    public void trimResults(){
        int k = matches.size();
        if ( k > 10 )
            matches.subList(10, k).clear();
    }
}
