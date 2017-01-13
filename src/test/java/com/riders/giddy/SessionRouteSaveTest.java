package com.riders.giddy;

import com.riders.giddy.api.v1.models.GiddyPath;
import com.riders.giddy.api.v1.models.GiddyPoint;

import org.junit.Test;


public class SessionRouteSaveTest extends BaseTest {

    @Test
    public void givenSameStartAndFinishPoints_whenComputedRoutesForDifferentGaugeScores_thenDifferentPathsAreResulted() {
        //Given
        GiddyPoint start = new GiddyPoint(46.7540808, 23.5849463);
        GiddyPoint finish = new GiddyPoint(46.7649881, 23.619064);
        float lowerBound = 0.2f;

        float[] gaugeScore = {1, 1, 1, 1, 1, 1};
        float[] differentGaugeScore = {-1, -1, -1, -1, -1, -1};

        //When
        GiddyPath pathOne = router.computeRoute(start, finish, gaugeScore, lowerBound);
        GiddyPath pathTwo = router.computeRoute(start, finish, differentGaugeScore, lowerBound);

        //Then
        assert pathOne.getPath().size() != pathTwo.getPath().size();
    }


}
