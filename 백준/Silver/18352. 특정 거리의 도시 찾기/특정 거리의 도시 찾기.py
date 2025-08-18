import sys
from collections import deque
input = sys.stdin.readline

n,m,k,x = map(int,input().split())

graph=[[] for _ in range(n+1)]
for i in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)

result = []
visited=[False]*(n+1)

def bfs(x):
    visited[x]=True
    queue = deque()
    queue.append((x,0))
    while queue:
        x, step = queue.popleft()
        if step == k :
            result.append(x)
        if step > k :
            break
        for next in graph[x]:
            if visited[next] == False :
                visited[next] = True
                queue.append((next,step+1))



bfs(x)
result.sort()
if result:
    for i in result:
        print(i)
else:
    print(-1)

