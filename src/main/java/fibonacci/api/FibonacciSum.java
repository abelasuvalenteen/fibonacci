package fibonacci.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FibonacciSum {
    private String memberCount;
    private String sequence;
    private String total;

    public FibonacciSum() {
        // Jackson deserialization
    }

    public FibonacciSum(String memberCount, String sequence, String total) {
        this.memberCount = memberCount;
        this.sequence = sequence;
        this.total = total;
    }

    @JsonProperty
    public String getMemberCount() {
        return memberCount;
    }

    @JsonProperty
    public String getSequence() { return sequence; }

    @JsonProperty
    public String getTotal() { return total; }
}
