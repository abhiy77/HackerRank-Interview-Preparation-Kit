#include <bits/stdc++.h>

using namespace std;

vector<string> split_string(string);

int calSum(long long int num){
    if(num <= 9){
        return num;
    }
    long long int sum = 0;
    long long int m = 0;
    while(num != 0){
        m = num%10;
        sum += m;
        num /=10;
    }
    return calSum(sum);
}

// Complete the superDigit function below.
int superDigit(string n, int k) {
    long long int ans = 0;

    for(auto i=0;i<n.length();i++){
        ans += n[i]-'0';
    }
    return calSum(ans*k);
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    string nk_temp;
    getline(cin, nk_temp);

    vector<string> nk = split_string(nk_temp);

    string n = nk[0];

    int k = stoi(nk[1]);

    int result = superDigit(n, k);

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
