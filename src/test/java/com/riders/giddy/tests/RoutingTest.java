package com.riders.giddy.tests;

import com.riders.giddy.api.v1.models.GiddyPath;
import com.riders.giddy.api.v1.models.GiddyPoint;
import com.riders.giddy.api.v1.services.GiddyRouter;
import com.riders.giddy.commons.persistence.store.GiddyScoreServiceI;
import com.riders.giddy.commons.persistence.store.entities.StatNames;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static com.riders.giddy.api.v1.services.GiddyScoreHelper.buildStatsMap;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class RoutingTest extends BaseTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Spy
    @Autowired
    @InjectMocks
    GiddyRouter giddyRouter;

    @Mock
    GiddyScoreServiceI scoreService = new MockedGraphStore();

    @Test
    public void givenSameStartAndFinishPoints_whenComputedRoutesForDifferentGaugeScores_thenDifferentPathsAreResulted() {

        //Given
        GiddyPoint start = new GiddyPoint(46.7540808, 23.5849463);
        GiddyPoint finish = new GiddyPoint(46.7649881, 23.619064);
        float lowerBound = 0.2f;

        Map<StatNames, Float> gaugeScore = buildStatsMap(1, 1, 1, 1, 1, 1);
        Map<StatNames, Float> differentGaugeScore = buildStatsMap(-1, -1, -1, -1, -1, -1);

        //When
        GiddyPath pathOne = giddyRouter.computeRoute(start, finish, gaugeScore, lowerBound);
        GiddyPath pathTwo = giddyRouter.computeRoute(start, finish, differentGaugeScore, lowerBound);

        //Then
        boolean different = false;

        //assertTrue(different);
        int sizePathOne = pathOne.getPath().size();
        int sizePathTwo = pathTwo.getPath().size();
        if (sizePathOne != sizePathTwo) {
            for (int i = 0; i < pathOne.getPath().size(); i++) {
                if (!(pathOne.getPath().get(i).equals(pathTwo.getPath().get(i)))) {
                    different = true;
                }
            }
            assertTrue(different);
        }
        logger.info("Test finished: resulted path sizes are " + String.valueOf(sizePathOne) + " " + String.valueOf(sizePathTwo));
    }

    boolean areEqual(GiddyPoint p1, GiddyPoint p2) {
        return (p1.getLat() == p2.getLat()) && (p1.getLon() == p2.getLon());
    }

    @Test
    public void testRouteComputingIsDeterministic() {
        //Given
        GiddyPoint start = new GiddyPoint(46.7540808, 23.5849463);
        GiddyPoint finish = new GiddyPoint(46.7649881, 23.619064);
        float lowerBound = 0.2f;

        Map<StatNames, Float> gaugeScore = buildStatsMap(1, 1, 1, 1, 1, 1);

        //When
        GiddyPath pathOne = giddyRouter.computeRoute(start, finish, gaugeScore, lowerBound);
        GiddyPath pathTwo = giddyRouter.computeRoute(start, finish, gaugeScore, lowerBound);

        //Then
        int sizePathOne = pathOne.getPath().size();
        int sizePathTwo = pathTwo.getPath().size();
        assert sizePathOne == sizePathTwo;
        logger.info("Test finished: resulted path sizes are " + String.valueOf(sizePathOne) + " " + String.valueOf(sizePathTwo));
    }

}
