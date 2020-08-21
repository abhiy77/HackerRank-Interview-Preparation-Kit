#include <bits/stdc++.h>

using namespace std;

// Complete the isBalanced function below.
string isBalanced(string s) {

    stack<char> brackets;
    for(unsigned int i=0;i<s.length();i++){
        if(s[i] == '{' || s[i] == '[' || s[i] == '('){
            brackets.push(s[i]);
        }
        else if(brackets.empty()){
            return "NO";
        }
        else if(s[i] == '}'){
            if(brackets.top() != '{'){
                return "NO";
            }
            else brackets.pop();
        }
        else if(s[i] == ']'){
            if(brackets.top() != '['){
                return "NO";
            }
            else brackets.pop();
        }
        else if(s[i] == ')'){
            if(brackets.top() != '('){
                return "NO";
            }
            else brackets.pop();
        }
    }
    return (brackets.empty() ? "YES" : "NO");
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    int t;
    cin >> t;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    for (int t_itr = 0; t_itr < t; t_itr++) {
        string s;
        getline(cin, s);

        string result = isBalanced(s);

        fout << result << "\n";
    }

    fout.close();

    return 0;
}
