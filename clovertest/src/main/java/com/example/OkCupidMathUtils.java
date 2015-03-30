package com.example;

import java.util.List;

/**
 * Created by joe-work on 3/15/15.
 */
public class OkCupidMathUtils {

    private static final int[] IMPORTANCE_POINTS = new int[]{0, 1, 10, 50, 250};

    public static double trueMatchScore(final Profile profileA, final Profile profileB) {
        //get the set they share
        List<QuestionResponse> answersA = profileA.answers;
        List<QuestionResponse> answersB = profileB.answers;

        double weightedSumOfAcceptedA = 0;
        double weightedSumOfAnswersA = 0;

        double weightedSumOfAcceptedB = 0;
        double weightedSumOfAnswersB = 0;

        int setSize = 0;

        for (int i = 0; i < answersA.size(); i++) {
            for (int j = 0; j < answersB.size(); j++) {

                final QuestionResponse singleAnswerFromA = answersA.get(i);
                final QuestionResponse singleAnswerFromB = answersB.get(j);

                //this will run through all the Shared answers
                if (singleAnswerFromA.questionId == singleAnswerFromB.questionId) {
                    setSize++;

                    //do the match for A
                    weightedSumOfAcceptedA += IMPORTANCE_POINTS[singleAnswerFromA.importance];

                    //add if is an answer they accept
                    for (int k : singleAnswerFromA.acceptableAnswers) {
                        if (k == singleAnswerFromB.answer) {
                            weightedSumOfAnswersA += IMPORTANCE_POINTS[singleAnswerFromA.importance];
                            break;
                        }
                    }

                    //do the match for B
                    weightedSumOfAcceptedB += IMPORTANCE_POINTS[singleAnswerFromB.importance];

                    //add if is an answer they accept
                    for (int k : singleAnswerFromB.acceptableAnswers) {
                        if (k == singleAnswerFromA.answer) {
                            weightedSumOfAnswersB += IMPORTANCE_POINTS[singleAnswerFromB.importance];
                            break;
                        }
                    }
                }
            }

        }

        double matchScoreA = weightedSumOfAnswersA / weightedSumOfAcceptedA;
        double matchScoreB = weightedSumOfAnswersB / weightedSumOfAcceptedB;

        double calcMatchScore = Math.sqrt(matchScoreA * matchScoreB);

        return calcMatchScore - 1 / setSize;
    }
}
