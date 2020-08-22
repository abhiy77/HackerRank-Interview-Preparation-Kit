#include<bits/stdc++.h>
using namespace std;

vector<int> adj[100000];
class Graph {
    public:
        int n;
        
        Graph(int n) {
            this->n = n;
        }
    
        void add_edge(int u, int v) {
            adj[u].push_back(v);
            adj[v].push_back(u);
        }
    
        vector<int> shortest_reach(int start) {
            bool vis[n+1];
            memset(vis,false,sizeof(vis));
            int dist[n+1];
            memset(dist,0,sizeof(dist));
            list<int>q;
            q.push_back(start);
            vis[start]=true;
            dist[start]=0;
            while(!q.empty())
            {
                int u=q.front();
                q.pop_front();
                for(auto i=0;i<adj[u].size();i++)
                {
                    int v= adj[u][i];
                    if(!vis[v])
                    {
                        vis[v]=true;
                        dist[v]=dist[u]+6;
                        q.push_back(v);
                    }
                }
            }
            vector<int> result;
            for(auto i=0;i<n;i++)
            {
                if(i!=start)
                {
                    if(dist[i]==0)
                    result.push_back(-1);
                    else
                    result.push_back(dist[i]);
                }
                else result.push_back(0);
            }
           for(int i=0;i<n;i++){
               adj[i].clear();
           }
           return result; 
        }
    
};

int main() {
    int queries;
    cin >> queries;
        
    for (int t = 0; t < queries; t++) {
      
		int n, m;
        cin >> n;
        // Create a graph of size n where each edge weight is 6: 
        Graph graph(n);
        cin >> m;
        // read and set edges
        for (int i = 0; i < m; i++) {
            int u, v;
            cin >> u >> v;
            u--, v--;
            // add each edge to the graph
            graph.add_edge(u, v);
        }
		int startId;
        cin >> startId;
        startId--;
        // Find shortest reach from node s
        vector<int> distances = graph.shortest_reach(startId);

        for (int i = 0; i < distances.size(); i++) {
            if (i != startId) {
                cout << distances[i] << " ";
            }
        }
        cout << endl;
    }
    
    return 0;
}
