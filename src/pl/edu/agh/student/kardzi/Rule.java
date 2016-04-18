package pl.edu.agh.student.kardzi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdziegie on 2016-04-18.
 */
public class Rule {
    private List<Change> changes;

    Rule(){
        changes = new ArrayList<Change>();
    }

    public Rule addRule(int b1, int b2, int b3, int a){
        changes.add(new Change( b1, b2,  b3,  a));
        return this;
    }

    public Rule addRule(boolean b1, boolean b2, boolean b3, boolean a){
        changes.add(new Change( b1, b2,  b3,  a));
        return this;
    }

    public List<Change> getChanges() {
        return changes;
    }
}
