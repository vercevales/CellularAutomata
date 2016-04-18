package pl.edu.agh.student.kardzi;

/**
 * Created by kdziegie on 2016-04-18.
 */
public class Change {
    private boolean[] before;
    private boolean after;

    public Change(int b1, int b2, int b3, int a) {
        before = new boolean[3];
        before[0] = (b1==1) ? true : false;
        before[1] = (b2==1) ? true : false;
        before[2] = (b3==1) ? true : false;
        after = (a==1) ? true : false;
    }

    public Change(boolean[] before, boolean after) {
        this.before = before;
        this.after = after;
    }

    public Change(boolean b1, boolean b2, boolean b3, boolean a) {
        before[0] = b1;
        before[1] = b2;
        before[2] = b3;
        after = a;
    }

    public boolean getAfter() {
        return after;
    }

    public void setAfter(boolean after) {
        this.after = after;
    }

    public boolean[] getBefore() {
        return before;
    }

    public void setBefore(boolean[] before) {
        this.before = before;
    }
}
