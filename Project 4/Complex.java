public class Complex {
    private double real;
    private double imaginary;
    public Complex() {
        //a + bi => 0 + 0i
        this(0, 0);
    }

    public Complex(double r, double i) {
        //a + bi
        this.real = r;
        this.imaginary = i;
    }

    public Complex(double r) {
        //a + 0i
        this(r, 0);
    }

    //Complex x Complex
    public Complex add(Complex other) {
        //(a + bi) + (c + di) => (a + c) + (b + d)i
        return new Complex(real + other.real, imaginary + other.imaginary);
    }

    public Complex sub(Complex other) {
        //(a + bi) - (c + di) => (a + c) - (b + d)i
        return new Complex(real - other.real, imaginary - other.imaginary);
    }

    public Complex mul(Complex other) {
        //(a + bi) * (c + di) => (ac - bd) + (ad + bc)i
        return new Complex(real * other.real - imaginary * other.imaginary, real * other.imaginary + imaginary * other.real);
    }

    public Complex div(Complex other) {
        //(a + bi) / (c + di) => (ac - bd) + (bc + ad)i / (c^2 + d^2) 
        //=> (ac - bd)/(c^2 + d^2) + (bc - ad)i/(c^2 + d^2)
        return new Complex((real * other.real + imaginary * other.imaginary) / (other.real * other.real + other.imaginary * other.imaginary), (imaginary * other.real - real * other.imaginary) / (other.real * other.real + other.imaginary * other.imaginary));
    }

    //Complex x double <--> double x Complex
    //This language has no friend function so can only choose 1

    public Complex add(double other) {
        //(a + bi) + c => (a + c) + bi
        return new Complex(real + other, imaginary);
    }

    public Complex sub(double other) {
        ////(a + bi) - c => (a - c) + bi
        return new Complex(real - other, imaginary);
    }

    public Complex mul(double other) {
        //(a + bi) * c => (ac) + (bc)i
        return new Complex(real * other, imaginary * other);
    }

    public Complex div(double other) {
        //(a + bi) / c => a / c + (b / c)i
        return new Complex(real / other, imaginary / other);
    }



    public String toString() {
        return real + " + " + imaginary + "i";
    }

    public static void main(String[] args) {
        Complex a = new Complex(1, 2);
        Complex b = new Complex(3, 4);

        double d = 5;

        //Complex x Complex
        System.out.println(a + " + " + b + " = " + a.add(b));
        System.out.println(a + " - " + b + " = " + a.sub(b));
        System.out.println(a + " * " + b + " = " + a.mul(b));
        System.out.println(a + " / " + b + " = " + a.div(b));

        //Complex x double
        System.out.println(a + " + " + d + " = " + a.add(d));
        System.out.println(a + " - " + d + " = " + a.sub(d));
        System.out.println(a + " * " + d + " = " + a.mul(d));
        System.out.println(a + " / " + d + " = " + a.div(d));

        //double x Complex does not work because Java doesnt use operator overloading
    }
}
