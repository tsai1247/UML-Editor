package Shapes;



public class Shapes extends Component
{
    public double x, y, width, height;

    public Shapes(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.strokeWidth = 4;
    }

    public int getX()
    {
        return (int) this.x;
    }
    public int getY()
    {
        return (int) this.y;
    }

    public Pair<Double, Double> center()
    {
        return new Pair<Double, Double>(this.x + this.width/2, this.y + this.height/2);
    }
}