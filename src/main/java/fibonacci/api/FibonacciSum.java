package fibonacci.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FibonacciSum {
    private String memberCount;
    private String sequence;
    private String total;
    private String invalidInput;

    public FibonacciSum() {
        // Jackson deserialization
    }

    public FibonacciSum(String memberCount, String sequence, String total) {
        this.memberCount = memberCount;
        this.sequence = sequence;
        this.total = total;
    }

    public FibonacciSum(String invalidInput) {
        this.invalidInput = invalidInput;
    }

    @JsonProperty
    public String getMemberCount() {
        return memberCount;
    }

    @JsonProperty
    public String getSequence() { return sequence; }

    @JsonProperty
    public String getTotal() { return total; }

    @JsonProperty
    public String getInvalidInput() { return invalidInput; }
}
