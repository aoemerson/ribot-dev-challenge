package io.github.aoemerson.riapidevchallenge.model;


public class Name {

    String first;
    String last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (last != null ? last.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        if (first != null ? !first.equals(name.first) : name.first != null) return false;
        return last != null ? last.equals(name.last) : name.last == null;

    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }


}
