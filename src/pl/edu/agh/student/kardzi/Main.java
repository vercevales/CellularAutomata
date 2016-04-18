package pl.edu.agh.student.kardzi;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Rule rule90 = new Rule();
        rule90.addRule(1, 1, 1, 0)
                .addRule(1, 1, 0, 1)
                .addRule(1, 0, 1, 0)
                .addRule(1, 0, 0, 1)
                .addRule(0, 1, 1, 1)
                .addRule(0, 1, 0, 0)
                .addRule(0, 0, 1, 1)
                .addRule(0, 0, 0, 0);
    }
}
