#include <bits/stdc++.h>

using namespace std;

int maxi = INT_MIN;
bool visited[10][10];
int cnt = 0;
// Complete the maxRegion function below.

void dfs(vector<vector<int>> grid,int i,int j,int n,int m){
    cnt+=1;
    maxi = max(maxi,cnt);
    visited[i][j] = true;

    if(i-1 >= 0 && j-1 >= 0 && grid[i-1][j-1] == 1 && !visited[i-1][j-1]){
        dfs(grid,i-1,j-1,n,m);
    }
    if(i-1 >= 0  && grid[i-1][j] == 1 && !visited[i-1][j]){
        dfs(grid,i-1,j,n,m);
    }
    if(i-1 >= 0 && j+1 < m && grid[i-1][j+1] == 1 && !visited[i-1][j+1]){
        dfs(grid,i-1,j+1,n,m);
    }
    if(j+1 < m && grid[i][j+1] == 1 && !visited[i][j+1]){
        dfs(grid,i,j+1,n,m);
    }
    if(i+1 < n && j+1 < m && grid[i+1][j+1] == 1 && !visited[i+1][j+1]){
        dfs(grid,i+1,j+1,n,m);
    }
    if(i+1 < n &&  grid[i+1][j] == 1 && !visited[i+1][j]){
        dfs(grid,i+1,j,n,m);
    }
    if(i+1 < n && j-1 >=0 && grid[i+1][j-1] == 1 && !visited[i+1][j-1]){
        dfs(grid,i+1,j-1,n,m);
    }
    if(j-1 >=0 && grid[i][j-1] == 1 && !visited[i][j-1]){
        dfs(grid,i,j-1,n,m);
    }
}

int maxRegion(vector<vector<int>> grid,int n,int m) {
    
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            cnt = 0;
            if(grid[i][j] == 1 && !visited[i][j]){
                dfs(grid,i,j,n,m);
            }
        }
    }  
    return maxi;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    int n;
    cin >> n;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    int m;
    cin >> m;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    vector<vector<int>> grid(n);
    for (int i = 0; i < n; i++) {
        grid[i].resize(m);

        for (int j = 0; j < m; j++) {
            cin >> grid[i][j];
        }

        cin.ignore(numeric_limits<streamsize>::max(), '\n');
    }

    int res = maxRegion(grid,n,m);

    fout << res << "\n";

    fout.close();

    return 0;
}
