package fibonacci.core;

import java.util.ArrayList;
import java.util.List;

public class FibonacciGenerator {

    public int getNumber() {
        return number;
    }

    public final int number;
    public FibonacciGenerator(int number){
        this.number = number;
        generateSum();
    }

    public List<Integer> sequence = new ArrayList<>();

    public int total;

    public String invalidInput = "";

    public List<Integer> getSequence() {
        return sequence;
    }

    public void setSequence(List<Integer> sequence) {
        this.sequence = sequence;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setInvalidInput(String invalidInput) {this.invalidInput = invalidInput;}

    public String getInvalidInput() {return invalidInput;}

    public void generateSum() {
        int total = 0;
        int n1=0,n2=1,n3,i;
           for (i = 0; i < this.number; ++i) {
                n3 = n1 + n2;
                sequence.add(n3);
                total = total + n3;
                n1 = n2;
                n2 = n3;
            }
            this.setSequence(sequence);
            this.setTotal(total);
        }
}
