package com.riders.giddy;

import com.riders.giddy.api.v1.models.GiddyPath;
import com.riders.giddy.api.v1.models.GiddyPoint;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SessionRouteSaveTest extends BaseTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

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
        int sizePathOne = pathOne.getPath().size();
        int sizePathTwo = pathTwo.getPath().size();
        assert sizePathOne != sizePathTwo;
        logger.info("Test finished: resulted path sizes are " + String.valueOf(sizePathOne) + " " + String.valueOf(sizePathTwo));
    }

    @Test
    public void testRouteComputingIsDeterministic() {
        //Given
        GiddyPoint start = new GiddyPoint(46.7540808, 23.5849463);
        GiddyPoint finish = new GiddyPoint(46.7649881, 23.619064);
        float lowerBound = 0.2f;

        float[] gaugeScore = {1, 1, 1, 1, 1, 1};

        //When
        GiddyPath pathOne = router.computeRoute(start, finish, gaugeScore, lowerBound);
        GiddyPath pathTwo = router.computeRoute(start, finish, gaugeScore, lowerBound);

        //Then
        int sizePathOne = pathOne.getPath().size();
        int sizePathTwo = pathTwo.getPath().size();
        assert sizePathOne == sizePathTwo;
        logger.info("Test finished: resulted path sizes are " + String.valueOf(sizePathOne) + " " + String.valueOf(sizePathTwo));
    }


}
