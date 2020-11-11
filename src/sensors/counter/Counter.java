package sensors.counter;

public interface Counter {
    int getCount();
    double getRate();
    void setCount(int count);
    void reset();
}
