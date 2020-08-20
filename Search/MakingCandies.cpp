#include <bits/stdc++.h>

using namespace std;

vector<string> split_string(string);

typedef long long int ll;

bool check(ll machines, ll workers, ll price, ll target, ll rounds) {
    if (machines >= (target+workers-1)/workers) return true;
    ll cur = machines*workers;
    rounds--;
    if (rounds == 0) return false;
    while (1) {
        ll rem = target - cur;
        ll rnds = (rem + machines*workers - 1) / (machines*workers);
        if (rnds <= rounds) return true;
        if (cur < price) {
          rem = price - cur;
          rnds = (rem + machines*workers - 1) / (machines*workers);
          rounds -= rnds;
          if (rounds < 1) return false;
          cur += rnds * machines * workers;
        }
        cur -= price;
        if (machines > workers) {
          workers++;
        } else {
          machines++;
        }
    }
    return false;
}


// Complete the minimumPasses function below.
ll minimumPasses(ll m, ll w, ll p, ll n) {
     ll a = 1, b = 1000000000000LL;
    while (a < b) {
        ll mid = (a + b) >> 1;
        if (check(m, w, p, n, mid)) {
          b = mid;
        } else {
          a = mid + 1;
        }
    }
    return a;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    string mwpn_temp;
    getline(cin, mwpn_temp);

    vector<string> mwpn = split_string(mwpn_temp);

    long m = stol(mwpn[0]);

    long w = stol(mwpn[1]);

    long p = stol(mwpn[2]);

    long n = stol(mwpn[3]);

    ll result = minimumPasses(m, w, p, n);

    fout << result << "\n";

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
