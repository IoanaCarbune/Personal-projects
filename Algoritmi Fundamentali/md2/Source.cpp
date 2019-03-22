#include <iostream>
#include "Profiler.h"
#include <algorithm>
using namespace std;

Profiler p("p");


typedef struct node
{
	int parent;
	int rang;
}Node;

typedef struct edge
{
	int x;
	int y;
	int cost;
}Edge;

typedef struct graph
{
	int V;//cardinal varfuri/muchii
	int E;
	Edge* edge;
}Graph;


void make_set(Node s[], int i, int *op);
int find_set(Node *s, int i, int *op);
void union_set(int a, int b, Node *s, int *op);
Graph *create_graph(int V, int E);
void generate_edges(int V, int E, Graph * graph);
void write_graph(Graph *graph, int V, int E);
void kruskal(Graph *graph, int V, int E, Node *v, int *op);
//void compare(Graph *graph);
void demo();
int main()
{
	srand(time(NULL));
	demo();
	int op = 0;
	for (int n = 100; n <= 10000; n += 100) {
		//cout << n;
		op = 0;
		Node *v = (Node*)calloc((n+1), sizeof(Node));
		Graph * graph = create_graph(n, 4*n);
		generate_edges(n, 4*n, graph);
		
		kruskal(graph, n, 4*n, v, &op);
		p.countOperation("Operatii", n,op);
	}

	p.showReport();
	getchar();
	getchar();

}

void demo()
{  int op;
	Node *v = (Node*)calloc(6, sizeof(Node));
	for (int i = 0; i < 6; i++)
		make_set(v, i,&op);
	int parent;
	
	union_set(3, 1, v, &op);
	parent = find_set(v, 3,&op);
	cout << parent << " ";

	union_set(4, 2, v, &op);
	parent = find_set(v, 2, &op);
	cout << parent << " ";

	union_set(3, 2, v, &op);
	parent = find_set(v, 3, &op);
	cout << parent << " ";

	union_set(0, 2, v, &op);
	parent = find_set(v, 2, &op);
	cout << parent << " ";

	union_set(2, 5, v, &op);
	parent = find_set(v, 5,&op);
	cout << parent << " ";

}
void make_set(Node s[], int i, int *op)
{
	s[i].parent = i;
	s[i].rang = 0;
	(*op) += 2;

}
int find_set(Node *s, int i, int *op)
{
	(*op)++;
	if (s[i].parent != i)
	{
		s[i].parent = find_set(s, s[i].parent, op);
	}

	return s[i].parent;
}
void union_set(int a, int b, Node *s, int *op)
{
	int x, y;
	x = find_set(s, a, &(*op));
	y = find_set(s, b, &(*op));
	(*op)++;
	if (s[x].rang > s[y].rang)
	{
		s[y].parent = x;
	}
	else
	{
		s[x].parent = y;
		if (s[x].rang == s[y].rang)
		{
			s[y].rang++;
		}
	}

}
Graph *create_graph(int V, int E)
{
	Graph *graph = new Graph();
	graph->V = V;
	graph->E = E;
	graph->edge = (Edge*)calloc((graph->E + 1), sizeof(Edge));

	return graph;
}
void generate_edges(int V, int E, Graph *graph)
{
	int random = 1;
	
		for (int j = 1; j <= V - 1; j++)
		{

			
			int x = j;
			int y = j+1;
			random = rand() % 10 + random;
				graph->edge[j].x = x;
				graph->edge[j].y = y;
				graph->edge[j].cost = random;
			

		}
		
		for (int j = V; j <= E; j++)
		{

			int x = rand() % V;
			int y = rand() % V;
			if (x != y) {
				random = rand() % 10 + random;
				graph->edge[j].x = x;
				graph->edge[j].y = y;
				graph->edge[j].cost = random;
			}
			else
			{
				j--;
				continue;
			}
			for (int k = 0; k < j; k++)
			{
				if ((graph->edge[j].x == graph->edge[k].x && graph->edge[j].y == graph->edge[k].y) ||
					(graph->edge[j].x == graph->edge[k].y && graph->edge[j].y == graph->edge[k].x))
					j--;
			}
		}

	
		//write_graph(graph, graph->V, graph->E);
}
void write_graph(Graph *graph, int V, int E)
{
	cout << "\nThe graph is\n";
	for (int i = 0; i < V; i++)
	{
		int count = 0;
		cout << "\n\t" << i + 1 << "-> { ";
		for (int j = 0; j < E; j++)
		{
			if (graph->edge[j].x == i + 1)
			{
				cout << graph->edge[j].y << " costul " << graph->edge[j].cost<< " ";
				count++;
			}
			else if (graph->edge[j].y == i + 1)
			{
				cout << graph->edge[j].x << "   costul " << graph->edge[j].cost;
				count++;
			}
		}
		cout << " }";

	}
}

//void compare(Graph *graph)
//{
//	for (int i = 1; i <= graph->E; i++)
//	{
//		for (int j = i + 1; j <= graph->E; j++)
//		{
//			cout << graph->edge[i].cost << "compare to  " << graph->edge[j].cost << endl;
//			if (graph->edge[i].cost > graph->edge[j].cost)
//				swap(graph->edge[i], graph->edge[j]);
//		}
//	}
//}

void kruskal(Graph *graph, int V, int E, Node *v, int *op)
{
	int nr = 0;
	for (int i = 0; i < V; i++)
	{
		make_set(v, i, &(*op));
	
	}

	//compare(graph);

	for (int i = 0; i <= E-1 && nr < V-1 ; i++)
	{
		int x = find_set(v, graph->edge[i].x, &(*op));
		int y = find_set(v, graph->edge[i].y, &(*op));
		
		if ( x != y)
		{
			nr++;
			union_set(graph->edge[i].x, graph->edge[i].y, v, &(*op));
		}
	}

}