#include<bits/stdc++.h>

string S;
int d[10010];
int n;
int c[26], w[26];

int main() {
    cin >> S;
    reverse(S.begin(), S.end());
    n = S.length();
    assert(n <= 10000);
    for(int i = 0; i < n; i++) d[i] = S[i] - 'a';
    for(int i = 0; i < n; i++) c[d[i]]++;
    for(int i = 0; i < 26; i++) assert(c[i] % 2 == 0);
    for(int i = 0; i < 26; i++) w[i] = c[i] / 2;
    vector<int> ret(n / 2);
    for(int i = 0; 2 * i < n; i++) {
        for(int c = 0; c < 26; c++) {
            ret[i] = c;
            int p = 0;
            int l = 0;
            for(int j = 0; j < n; j++) {
                if(ret[p] == d[j]) {
                    p++;
                    l = j;
                    if(p > i) break;
                }
            }
            if(p <= i) continue;
            int want[26] = {};
            for(int j = 0; j < 26; j++) want[j] = w[j];
            for(int j = 0; j <= i; j++) want[ret[j]]--;
            int have[26] = {};
            for(int j = l + 1; j < n; j++) have[d[j]]++;
            int ok = 1;
            for(int j = 0; j < 26; j++) if(want[j] < 0 || want[j] > have[j]) ok = 0;
            if(ok) break;
        }
    }
    string r;
    for(int i = 0; 2 * i < n; i++) r += char(ret[i] + 'a');
    cout << r << endl;
    return 0;
}
