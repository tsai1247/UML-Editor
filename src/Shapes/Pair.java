package Shapes;
public class Pair<T, R> {
    private T t;
    private R r;

    public Pair(T t, R r)
    {
        this.t = t;
        this.r = r;
    }

    public T first()
    {
        return t;
    }
    public R second()
    {
        return r;
    }
}
