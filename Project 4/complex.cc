#include <iostream>
#include <string>

class complex {
    private:
        double real;
        double imaginary;

    public:
        // a + bi => 0 + 0i
        complex() : real(0), imaginary(0) {}

        // a + bi
        complex(double r, double i) : real(r), imaginary(i) {}

        // a + 0i
        complex(double r) : real(r), imaginary(0) {}

        //Complex x Complex
        complex operator+(const complex& other) const {
            return complex(real + other.real, imaginary + other.imaginary);
        }

        complex operator-(const complex& other) const {
            return complex(real - other.real, imaginary - other.imaginary);
        }

        complex operator*(const complex& other) const {
            return complex(real * other.real - imaginary * other.imaginary, real * other.imaginary + imaginary * other.real);
        }

        complex operator/(const complex& other) const {
            return complex((real * other.real + imaginary * other.imaginary) / (other.real * other.real + other.imaginary * other.imaginary), (imaginary * other.real - real * other.imaginary) / (other.real * other.real + other.imaginary * other.imaginary));
        }

        //Complex x double
        complex operator+(double other) const {
            return complex(real + other, imaginary);
        }

        complex operator-(double other) const {
            return complex(real - other, imaginary);
        }

        complex operator*(double other) const {
            return complex(real * other, imaginary * other);
        }

        complex operator/(double other) const {
            return complex(real / other, imaginary / other);
        }

        //double x Complex
        friend complex operator+(double otherD, const complex& otherC) {
            return complex(otherD + otherC.real, otherC.imaginary);
        }

        friend complex operator-(double otherD, const complex& otherC) {
            return complex(otherD - otherC.real, otherC.imaginary);
        }

        friend complex operator*(double otherD, const complex& otherC) {
            return complex(otherD * otherC.real, otherD * otherC.imaginary);
        }

        friend complex operator/(double otherD, const complex& otherC) {
            return complex(otherD / otherC.real, otherD / otherC.imaginary);
        }

        friend std::ostream& operator<<(std::ostream& os, const complex& c) {
            os << c.real << " + " << c.imaginary << "i";
            return os;
        }
};

int main() {
    complex a(1, 2);
    complex b(3, 4);

    double d = 5;

    //Complex x Complex
    std::cout << a << " + " << b << " = " << a + b << std::endl;
    std::cout << a << " - " << b << " = " << a - b << std::endl;
    std::cout << a << " * " << b << " = " << a * b << std::endl;
    std::cout << a << " / " << b << " = " << a / b << std::endl;

    //Complex x double
    std::cout << a << " + " << d << " = " << a + d << std::endl;
    std::cout << a << " - " << d << " = " << a - d << std::endl;
    std::cout << a << " * " << d << " = " << a * d << std::endl;
    std::cout << a << " / " << d << " = " << a / d << std::endl;

    //double x Complex
    std::cout << d << " + " << a << " = " << d + a << std::endl;
    std::cout << d << " - " << a << " = " << d - a << std::endl;
    std::cout << d << " * " << a << " = " << d * a << std::endl;
    std::cout << d << " / " << a << " = " << d / a << std::endl;


    return 0;
}