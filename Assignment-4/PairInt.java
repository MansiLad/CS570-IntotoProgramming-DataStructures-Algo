package Maze;

public class PairInt {
    private int x;
    private int y;
    public PairInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Object p) {
        PairInt pint = (PairInt)p;
        if(this.x==pint.x && this.y==pint.y){
            return true;
        }
        else{
            return false;
        }
    }

    public String toString() {
        return "[" + String.valueOf(x) + "," + String.valueOf(y) +"]";
    }

    public PairInt copy() {
        PairInt solution = new PairInt(x, y);
        return solution;
    }
}
