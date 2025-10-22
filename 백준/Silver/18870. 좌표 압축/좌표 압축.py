import sys
input = sys.stdin.readline

n=int(input())
nums=list(map(int,input().split()))
nums_set=sorted(list(set(nums)))

dic={nums_set[i]: i for i in range(len(nums_set))}

for num in nums:
    print(dic[num], end=' ')