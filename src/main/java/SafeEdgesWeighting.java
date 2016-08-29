import com.graphhopper.routing.util.AbstractAdjustedWeighting;
import com.graphhopper.routing.util.Weighting;
import com.graphhopper.util.EdgeIteratorState;
import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;

import java.util.Set;

/**
 * Created by rik on 8/16/16.
 */
public class SafeEdgesWeighting extends AbstractAdjustedWeighting
{
    // contains the edge IDs of the already visited edges
    protected final TIntSet visitedEdges = new TIntHashSet();

    private double edgePenaltyFactor = 5.0;

    public SafeEdgesWeighting(Weighting superWeighting)
    {
        super(superWeighting);
    }



    public SafeEdgesWeighting setEdgePenaltyFactor(double edgePenaltyFactor )
    {
        this.edgePenaltyFactor = edgePenaltyFactor;
        return this;
    }

    /**
     * This method adds the specified path to this weighting which should be penalized in the
     * calcWeight method.
     * @param edges
     */
    public void addEdges(Set<Integer> edges )
    {
       // TODO
    }

    @Override
    public double getMinWeight( double distance )
    {
        // TODO
        return 0;
    }

    @Override
    public double calcWeight( EdgeIteratorState edgeState, boolean reverse, int prevOrNextEdgeId )
    {
        // TODO
        return 0;
    }

    @Override
    public String getName()
    {
        return "safe_edges";
    }
}