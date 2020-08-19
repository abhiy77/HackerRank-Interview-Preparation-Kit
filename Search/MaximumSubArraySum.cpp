#include <bits/stdc++.h>

using namespace std;

vector<string> split_string(string);
typedef long long int ll;
// Complete the maximumSum function below.
ll maximumSum(vector<ll> a, long M) {
ll x,prefix=0,maxim=0,N=a.size();
    set<ll> S;
    S.insert(0);
    for(int i=1;i<=N;i++){
        x = a[i-1];
        prefix = (prefix + x)%M;
        maxim = max(maxim,prefix);
        set<ll>::iterator  it = S.lower_bound(prefix+1);
        if( it != S.end() ){
            maxim = max(maxim,prefix - (*it) + M );
        }
        S.insert(prefix);
    }
    return maxim;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    int q;
    cin >> q;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    for (int q_itr = 0; q_itr < q; q_itr++) {
        string nm_temp;
        getline(cin, nm_temp);

        vector<string> nm = split_string(nm_temp);

        int n = stoi(nm[0]);

        ll m = stol(nm[1]);

        string a_temp_temp;
        getline(cin, a_temp_temp);

        vector<string> a_temp = split_string(a_temp_temp);

        vector<ll> a(n);

        for (int i = 0; i < n; i++) {
            ll a_item = stol(a_temp[i]);

            a[i] = a_item;
        }

        ll result = maximumSum(a, m);

        fout << result << "\n";
    }

    fout.close();

    return 0;
}

vector<string> split_string(string input_string) {
    string::iterator new_end = unique(input_string.begin(), input_string.end(), [] (const char &x, const char &y) {
        return x == y and x == ' ';
    });

    input_string.erase(new_end, input_string.end());

    while (input_string[input_string.length() - 1] == ' ') {
        input_string.pop_back();
    }

    vector<string> splits;
    char delimiter = ' ';

    size_t i = 0;
    size_t pos = input_string.find(delimiter);

    while (pos != string::npos) {
        splits.push_back(input_string.substr(i, pos - i));

        i = pos + 1;
        pos = input_string.find(delimiter, i);
    }

    splits.push_back(input_string.substr(i, min(pos, input_string.length()) - i + 1));

    return splits;
}
