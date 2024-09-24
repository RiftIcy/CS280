class complex:
    def __init__(self, real = 0, imaginary = 0):
        self.real = real
        self.imaginary = imaginary
    
    def __add__(self, other):
        #Complex x Complex
        if isinstance(other, complex):
            return complex(self.real + other.real, self.imaginary + other.imaginary)
        #Complex x double
        elif isinstance(other, (int, float)):
            return complex(self.real + other, self.imaginary)
        else:
            raise TypeError
    #double x Complex
    def __radd__(self, other):
        return complex(other + self.real, self.imaginary)

    def __sub__(self, other):
        if isinstance(other, complex):
            return complex(self.real - other.real, self.imaginary - other.imaginary)
        elif isinstance(other, (int, float)):
            return complex(self.real - other, self.imaginary)
        else:
            raise TypeError
    
    def __rsub__(self, other):
        return complex(other - self.real, self.imaginary)
    

    def __mul__(self, other):
        if isinstance(other, complex):
            return complex(self.real * other.real - self.imaginary * other.imaginary, self.real * other.imaginary + self.imaginary * other.real)
        elif isinstance(other, (int, float)):
            return complex(self.real * other, self.imaginary * other)
        else:
            raise TypeError
    
    def __rmul__(self, other):
        return complex(other * self.real, other * self.imaginary)
    
    def __truediv__(self, other):
        if isinstance(other, complex):
            return complex((self.real * other.real + self.imaginary * other.imaginary) / (other.real ** 2  + other.imaginary ** 2), (self.imaginary * other.real - self.real * other.imaginary) / (other.real ** 2  + other.imaginary ** 2))
        elif isinstance(other, (int,float)):
            return complex(self.real / other, self.imaginary / other)
        else:
            raise TypeError
    
    def __rtruediv__(self, other):
        return complex(other / self.real, other / self.imaginary)

    def __str__(self):
        return f"{self.real} + {self.imaginary}i"
    
if __name__ == '__main__':

    a = complex(1, 2)
    b = complex(3, 4)

    d = 5

    #Complex x Complex
    print('%s + %s = %s' % (a, b, a + b))
    print('%s - %s = %s' % (a, b, a - b))
    print('%s * %s = %s' % (a, b, a * b))
    print('%s / %s = %s' % (a, b, a / b))

    #Complex x double
    print('%s + %d = %s' % (a, d, a + d))
    print('%s - %d = %s' % (a, d, a - d))
    print('%s * %d = %s' % (a, d, a * d))
    print('%s / %d = %s' % (a, d, a / d))

    #double x Complex
    print('%d + %s = %s' % (d, a, d + a))
    print('%d - %s = %s' % (d, a, d - a))
    print('%d * %s = %s' % (d, a, d * a))
    print('%d / %s = %s' % (d, a, d / a))
    

