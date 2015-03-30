package com.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class OkCupidSort {

    private static String PATH = "/Users/joe-work/Documents/CloverChallenge2/clovertest/src/main/java/com/example/input.json";

    public static void main(String[] args) {

        Gson gson = new Gson();
        String inputString = null;
        try {
            inputString = readFile(PATH, StandardCharsets.UTF_8);
        } catch (IOException e){
            e.printStackTrace();
        }

//        System.out.println(inputString);

        if(inputString!=null) {
            Profiles profiles = gson.fromJson(inputString, Profiles.class);

            Results results = calculateResults(profiles);
            String outputString = gson.toJson(results);
            System.out.println(outputString); //this is working!

            try {
                PrintWriter writer = new PrintWriter("/Users/joe-work/Documents/CloverChallenge2/clovertest/src/main/java/com/example/output.json", "UTF-8");
                writer.println(outputString);
                writer.close();
            } catch (Throwable e){
                e.printStackTrace();
            }


        }
    }


    private static  Results calculateResults(final Profiles profiles){
        Results results = new Results();

        int length = profiles.profiles.size();

        //go through the list of all people
        for(int i = 0; i<length; i++){
            //for each one, match against everyone else
            Profile singleProfileA  = profiles.profiles.get(i);
            Result singleResult = new Result(singleProfileA.id);

            for(int j=0;j<length; j++){
                //BUT don't include themselves
                if(j!=i){
                    Profile singleProfileB = profiles.profiles.get(j);//the other person's profile
                    double trueMatchScore = OkCupidMathUtils.trueMatchScore(singleProfileA, singleProfileB);
                    SingleMatch match = new SingleMatch(singleProfileB.id, trueMatchScore);
                    singleResult.addMatch(match);
                }
            }

            singleResult.trimResults();

            results.results.add(singleResult);
        }

        return results;
    }


    public static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }


}
