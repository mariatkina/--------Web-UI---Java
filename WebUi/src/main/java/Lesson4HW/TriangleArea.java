package Lesson4HW;

public class TriangleArea {

    public boolean triangleArea(int a, int b, int c) {
        double p = (a + b + c);
        p = p / 2;
        double s = Math.sqrt((p * (p - a) * (p - b) * (p - c)));

        if (((a + b) <= c) || ((a + c) <= b) || ((b + c) <= a)){
            return false;
        } else if (a <=0 || b <=0 || c <=0) {
            return false;
        }
        return true;
    }
    public double triangleArWithExcptn (int a, int b, int c)throws TriangleSidesException{
        double p = (a + b + c);
        p = p / 2;
        double s = Math.sqrt((p * (p - a) * (p - b) * (p - c)));
        if (((a + b) <= c) || ((a + c) <= b) || ((b + c) <= a) || a <=0 || b <=0 || c <=0)
            throw new TriangleSidesException("Ошибка в значинии одной из сторон!");
        return s;
    }
}
