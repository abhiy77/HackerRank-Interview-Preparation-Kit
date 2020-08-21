#include <bits/stdc++.h>

using namespace std;

vector<string> split_string(string);

typedef struct plant{
    int pesticide,days;
}plant;


// Complete the poisonousPlants function below.
int poisonousPlants(vector<int> p,int n) {
    for(int i=0;i<n;i++){
        p[i] = -p[i];
    }
    stack<plant> s;
    int maxi = 0;

    for(int i=0;i<n;i++){
        if(s.empty()){
            s.push({p[i],0});
        }
        else{
            plant val = s.top();
            if(p[i] < val.pesticide){
                s.push({p[i],1});
                maxi = max(maxi,1);
            }
            else{
                plant temp = s.top();
                int maxDays = temp.days;
                while(!s.empty() && temp.pesticide <= p[i]){
                    s.pop();
                    if(s.empty())break;
                    maxDays = max(maxDays,temp.days);
                    temp = s.top();
                }
                if(s.empty()){
                    s.push({p[i],0});
                }
                else{
                    s.push({p[i],maxDays+1});
                    maxi = max(maxi,maxDays+1);
                }
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

    string p_temp_temp;
    getline(cin, p_temp_temp);

    vector<string> p_temp = split_string(p_temp_temp);

    vector<int> p(n);

    for (int i = 0; i < n; i++) {
        int p_item = stoi(p_temp[i]);

        p[i] = p_item;
    }

    int result = poisonousPlants(p,n);

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
