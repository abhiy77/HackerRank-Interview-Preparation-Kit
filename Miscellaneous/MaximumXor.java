#include <bits/stdc++.h>

using namespace std;

struct node{
    int val;
    node* child[2];
};

void createTrie(node* trie,vector<int> arr);
void find(node* trie,int query,int idx);
void insert(node* trie,int val,int idx);

vector<string> split_string(string);



int ans;

// Complete the maxXor function below.
vector<int> maxXor(vector<int> arr, vector<int> queries) {
    node* trie = new node();
    createTrie(trie,arr);
    vector<int> result;
    int len_query = queries.size();
    for(int i=0;i<len_query;i++){
        ans = 0;
        find(trie,queries[i],31);
        result.push_back(ans ^ queries[i]);
    }
    return result;
}

void find(node* trie,int query,int idx){
    if(idx < 0){
        return;
    }
    int d = (query >> idx) & 1;
    d ^= 1;

    if(!trie->child[d]){
        d ^= 1;
    }
    ans = (ans << 1) | d;
    find(trie->child[d],query,idx-1);

}

void createTrie(node* trie,vector<int> arr){
    int len_arr = arr.size();
    for(int i=0;i<len_arr;i++){
        insert(trie,arr[i],31);
    }
}

void insert(node* trie,int val,int idx){
    if(idx < 0){
        return;
    }
    int d = (val >> idx)&1;
    if(!trie->child[d]){
        trie->child[d] = new node();
    }
    insert(trie->child[d],val,idx-1);
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    int n;
    cin >> n;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    string arr_temp_temp;
    getline(cin, arr_temp_temp);

    vector<string> arr_temp = split_string(arr_temp_temp);

    vector<int> arr(n);

    for (int i = 0; i < n; i++) {
        int arr_item = stoi(arr_temp[i]);

        arr[i] = arr_item;
    }

    int m;
    cin >> m;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    vector<int> queries(m);

    for (int i = 0; i < m; i++) {
        int queries_item;
        cin >> queries_item;
        cin.ignore(numeric_limits<streamsize>::max(), '\n');

        queries[i] = queries_item;
    }

    vector<int> result = maxXor(arr, queries);

    for (int i = 0; i < result.size(); i++) {
        fout << result[i];

        if (i != result.size() - 1) {
            fout << "\n";
        }
    }

    fout << "\n";

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
