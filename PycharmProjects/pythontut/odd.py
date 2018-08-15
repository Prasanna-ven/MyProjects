import datetime
import os
import sys
import time

print(datetime.date.today())
print(time.strftime('%A %H : %M'))
print(os.getcwd())
print(sys.platform)
print(sys.version)
print(os.getenv('PATH'))
odds = [1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59]

right_this_minute = datetime.datetime.today().minute
right_this_hour = datetime.datetime.today().hour
right_this_day = datetime.datetime.today().day
right_this_month = datetime.datetime.today().month
right_this_year = datetime.datetime.today().year
right_this = datetime.datetime.today().date().isoformat()
if right_this_minute in odds:
    print('This minute looks odd')
else:
    print('Not an odd minute')
if right_this_hour in odds:
    print('This is an odd hour')
else:
    print('this is not an odd hour')
if right_this_day in odds:
    print('This is an odd day')
else:
    print('this is not an odd day')
if right_this_month in odds:
    print('This is an odd month')
else:
    print('this is not an odd month')
if right_this_year%2 in odds:
    print('This is an odd year')
else:
    print('this is not an odd year')
print(right_this)