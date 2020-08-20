#include <bits/stdc++.h>

using namespace std;

int dp[100001];
// Complete the stepPerms function below.

int stepCount(int n){
    if(n == 0)return 1;
    if(n < 0)return 0;
    if(dp[n] != -1)return dp[n];
    dp[n] = (stepCount(n-1) + stepCount(n-2) + stepCount(n-3));
    return dp[n];
}

int stepPerms(int n) {
   memset(dp,-1,sizeof dp);
   return stepCount(n);
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    int s;
    cin >> s;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    for (int s_itr = 0; s_itr < s; s_itr++) {
        int n;
        cin >> n;
        cin.ignore(numeric_limits<streamsize>::max(), '\n');

        int res = stepPerms(n);

        fout << res << "\n";
    }

    fout.close();

    return 0;
}
