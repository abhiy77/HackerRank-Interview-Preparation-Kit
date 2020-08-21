#include <bits/stdc++.h>

using namespace std;
bool finish = false;
vector<string> result;

void recurse(vector<string> crossword,vector<string> words,int idx){

    if(finish == true)return;
    int len = words.size();
    if(idx == len){
        
        finish = true;
        for(auto word : crossword){
            result.push_back(word);
        }
        return;
    }

    int i,j,p,q,k;
    for(i=0;i<10;i++){
        for(j=0;j<10;j++){
                if(crossword[i][j] == '+') continue;
                p = i, q = j;

                int curr_word_len = words[idx].length();
                for(k=0;k<curr_word_len && p+k < 10;k++){
                    if(crossword[p+k][q] != '-' && crossword[p+k][q] != words[idx][k]){
                        break;
                    }
                }

                if(k == curr_word_len){
                    vector<string> temp = crossword;
                    for(k=0;k<curr_word_len;k++){
                        crossword[p+k][q] = words[idx][k];
                    }
                    recurse(crossword,words,idx+1);
                    crossword = temp;
                }

                for(k = 0;k<curr_word_len && q+k < 10;k++){
                    if(crossword[p][q+k] != '-' && crossword[p][q+k] != words[idx][k]){
                        break;
                    }
                }

                if(k == curr_word_len){
                    vector<string> temp = crossword;
                    for(k=0;k<curr_word_len;k++){
                        crossword[p][q+k] = words[idx][k];
                    }
                    recurse(crossword,words,idx+1);
                    crossword = temp;
                }
        }
    }
}

// Complete the crosswordPuzzle function below.
vector<string> crosswordPuzzle(vector<string> crossword, string words) {
    
    vector<string> wordList;
    string s;

    for(auto x: words) {
        if(x==';') {
            wordList.push_back(s);
            s="";
        } else
            s+=x;
    }
    wordList.push_back(s);
    recurse(crossword,wordList,0);
    return result;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    vector<string> crossword(10);

    for (int i = 0; i < 10; i++) {
        string crossword_item;
        getline(cin, crossword_item);

        crossword[i] = crossword_item;
    }

    string words;
    getline(cin, words);

    vector<string> result = crosswordPuzzle(crossword, words);

    for (unsigned int i = 0; i < result.size(); i++) {
        fout << result[i];

        if (i != result.size() - 1) {
            fout << "\n";
        }
    }

    fout << "\n";

    fout.close();

    return 0;
}
