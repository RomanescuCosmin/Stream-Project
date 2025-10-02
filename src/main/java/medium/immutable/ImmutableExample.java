package medium.immutable;

import java.util.*;

public final class ImmutableExample {

    private final String stringValue;
    private final int intValue;
    private final List<String> stringList;
    private final Date dateValue;

    public ImmutableExample(String stringValue, int intValue, List<String> stringList, Date dateValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;

        // creating a deep copy  of the list and date to ensure immutability
        this.stringList = new ArrayList<>(stringList);
        this.dateValue = new Date(dateValue.getTime());
    }


    public String getStringValue() {
        return stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public List<String> getStringList() {
        return List.copyOf(stringList);
    }

    public Date getDateValue() {
        return new Date(dateValue.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if( this == o) return true;
        if(!(o instanceof ImmutableExample example)) return false;

        return this.intValue == example.intValue &&
                Objects.equals(this.stringValue, example.stringValue) &&
                Objects.equals(this.stringList, example.stringList) &&
                Objects.equals(this.dateValue, example.dateValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringValue, intValue, stringList, dateValue);
    }

    public static void main(String[] args) {
        List<String> initialList = new ArrayList<>();
        initialList.add("one");
        initialList.add("two");

        Date currentDate = new Date();
        ImmutableExample immutableExample = new ImmutableExample("Immutable String", 10, initialList, currentDate);

        // accesing value
        System.out.println("String: " + immutableExample.getStringValue());
        System.out.println(" int : " + immutableExample.getIntValue());
        System.out.println("String list: " + immutableExample.getStringList());
        System.out.println("Current date: " + immutableExample.getDateValue());

        initialList.add("three");
        currentDate.setTime(System.currentTimeMillis() + 1000);
        System.out.println("String: " + immutableExample.getStringList());
        System.out.println("Set time: " + immutableExample.getDateValue());
    }

}
