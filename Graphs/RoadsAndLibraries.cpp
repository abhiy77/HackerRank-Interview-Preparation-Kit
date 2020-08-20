#include <bits/stdc++.h>

using namespace std;

vector<string> split_string(string);
bool visited[100005];
vector<int> adj[100005];
long nodes;

void dfs(int n){
    nodes++;
    visited[n] = true;
    for(auto i = 0; i<adj[n].size();i++){
        int val = adj[n][i];
        if(!visited[val]){
            dfs(val);
        }
    }
}

// Complete the roadsAndLibraries function below.
long roadsAndLibraries(int n, int c_lib, int c_road, vector<vector<int>> cities) {
    long cost = 0;
    for(int i=0;i<cities.size();i++){
        int a = cities[i][0];
        int b = cities[i][1];

        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    for(int i=1;i<=n;i++){
        if(!visited[i]){
            nodes = 0;
            dfs(i);

            cost += c_lib;
            if(c_lib > c_road){
                cost += ((nodes-1)*c_road);
            }
            else{
                cost += ((nodes-1)*c_lib);
            }
        }
    }

    for(int i=0;i<=n;i++){
        adj[i].clear();
        visited[i] = false;
    }
    return cost;

}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    int q;
    cin >> q;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    for (int q_itr = 0; q_itr < q; q_itr++) {
        string nmC_libC_road_temp;
        getline(cin, nmC_libC_road_temp);

        vector<string> nmC_libC_road = split_string(nmC_libC_road_temp);

        int n = stoi(nmC_libC_road[0]);

        int m = stoi(nmC_libC_road[1]);

        int c_lib = stoi(nmC_libC_road[2]);

        int c_road = stoi(nmC_libC_road[3]);

        vector<vector<int>> cities(m);
        for (int i = 0; i < m; i++) {
            cities[i].resize(2);

            for (int j = 0; j < 2; j++) {
                cin >> cities[i][j];
            }

            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }

        long result = roadsAndLibraries(n, c_lib, c_road, cities);

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
