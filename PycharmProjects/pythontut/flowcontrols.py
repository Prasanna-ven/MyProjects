s1 = 'Prasanna'
n1 = int(input())
n2 = 5
for i in range(0, len(s1)):
    print(s1[i])

if(n1 > 90):
    print('Grade A')
elif(n1 > 70 and n1 < 90):
    print('Grade B')
elif(n1 > 60 and n1 < 70):
    print('Grade C')
elif(n1 > 50 and n1 < 60):
    print('Grade D')
else:
    print('Better Luck Next Time')

while(n2 >0):
    print('n2 : ', n2)
    n2 -= 1
print('end while')