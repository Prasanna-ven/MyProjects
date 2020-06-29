import os
import sys
""" this is a test python script """
print('Hello World!')
print("Number of args : ", len(sys.argv))
print("Argument list", str(sys.argv))

a, b, c = 2, 5, 'Prasanna'
lst = [190751, 51786, 10612382, 'pv20681', 'Prasanna Vedantha Desikan']
tup = (190751, 51786, 10612382, 'pv20681', 'Prasanna Vedantha Desikan')
maps = {'Name': 'Prasanna', 'Empno': 10612382, 'Mobile': 9790932969}
print("a =", float(a))
print("b =", complex(b))
print("c =", repr(c))
# print("c =", eval('Prasanna'))
print("C[1:5]", c[1:5])
print("10/5=", 10/5)
print("5 ** 10=", 5**10)
print("10%5 = ", 10 % 5)
print("10//5 = ", 10//5)
print(2*100/2)
print(maps)
print(maps.keys())
print(maps.values())
print(maps['Name'])
# for i in 'Prasanna':
#    print(i)
# print((-5-1)*3)
# print((-18/3)+1)

# if 2 == 2:
#    print("True")
# else:
#    print("False")
# print("condition is over")
v_cwd = os.getcwd()
print(v_cwd)
print(sys.platform)
