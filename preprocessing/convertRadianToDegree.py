
import math
import pandas as pd

def degree(x):
    pi=math.pi
    degree=(x * 180)/pi
    return degree

df = pd.read_csv("/Users/vincent/Learning-Data/cs226/project/data/poi.csv")
print(df.head)
# vincent: only extract latitude and longitude and save to a new csv
df1 = df[['latitude_radian', 'longitude_radian']]
df1.to_csv('extract.csv',index= False)

df2 = pd.read_csv("/Users/vincent/PycharmProjects/untitled/extract.csv")
print(df2.head)

# vincent: convert radian to degree
for i in range(0, df2['latitude_radian'].size):
    print(i)
    df2['latitude_radian'][i] = degree(df2['latitude_radian'][i])
    df2['longitude_radian'][i] = degree(df2['longitude_radian'][i])

df2.to_csv("processed.csv", index=False)
