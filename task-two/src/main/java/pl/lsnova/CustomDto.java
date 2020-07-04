package pl.lsnova;

public class CustomDto {
    private final int firstValue;
    private final int secondValue;

    public CustomDto(int firstValue, int secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public int getFirstValue() {
        return firstValue;
    }

    public int getSecondValue() {
        return secondValue;
    }

    @Override
    public String toString() {
        return firstValue > secondValue ? (secondValue + " " + firstValue) : (firstValue + " " +  secondValue);
    }
}
