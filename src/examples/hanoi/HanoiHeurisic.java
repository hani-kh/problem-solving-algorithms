package examples.hanoi;

import core.HeuristicFunction;
import core.State;
//This heuristic takes in consideration the tablet size and adds some weight and hence more required moves
//for the large tablet compared with smaller ones..
//the weight is:
// 1^2 for small tablet
// 2^2 for medium tablet
//3^2 for Large tablet..
//we multiply the weight with the power 2 of tablet position..
//so in goal state we'll have 1^2*2 + 2^2 * 2 + 3^2 * 2 = 1*2 + 4*2+ + 9*2 = 2+8+18 = 28;
//we need the goal heuristic to be equals to zero so the h will be: 28 - previous formula.
//=> h = 28 - SEGMA(i^2 *a(i)) i is the tablet, a(i) is the position of tablet i.
public class HanoiHeurisic implements HeuristicFunction {
    @Override
    public double getH(State s) {
        HanoiState t = (HanoiState)s;
        double h = 28;
        for(int i=0;i<t.tablets.length;i++){
            h-= Math.pow(i+1,2)*t.tablets[i];
        }
        return h;
    }
}