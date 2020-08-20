/* Hidden stub code will pass a root argument to the function below. Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.  

The Node struct is defined as follows:
	struct Node {
		int data;
		Node* left;
		Node* right;
	}
*/


    bool checkBST(Node* root,int mini,int maxi){
        if(!root){
            return true;
        }
        if(root->data < mini || root->data > maxi){
            return false;
        }
        return ((checkBST(root->left,mini,root->data-1)) && (checkBST(root->right,root->data+1,maxi)));
    }

	bool checkBST(Node* root) {
		return checkBST(root,0,10000);
	}
