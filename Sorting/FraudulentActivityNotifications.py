#!/bin/python3

import math
import os
import random
import re
import sys

dic = {}
# Complete the activityNotifications function below.
def activityNotifications(arr, d):
    
    ans = 0
    for i in range(0, n):
        val = arr[i]
        
        if i>=d:
            med=find(d/2)
            
            if d%2==0:
                ret = find(d/2+1)
                if val >=med + ret:
                    ans = ans + 1
            else:
                if val>=med*2:
                    ans = ans + 1

        if val not in dic: dic[val] = 0
        dic[val] = dic[val] + 1
        
        #print i,dic
        if i>=d:
            prev = arr[i-d]
            dic[prev] = dic[prev]-1

    return ans;

def find(idx):
    s = 0
    for i in range(0, 200):
        freq = 0
        if i in dic:
            freq = dic[i]
        s = s + freq
        if s>=idx:
            return i

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nd = input().split()

    n = int(nd[0])

    d = int(nd[1])

    expenditure = list(map(int, input().rstrip().split()))

    result = activityNotifications(expenditure, d)

    fptr.write(str(result) + '\n')

    fptr.close()
