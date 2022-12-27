#include<iostream>
#include<limits.h>
using namespace std;
class Heap
{
    private : int *arr;
    public : int length;
    private : int maxlength;

    public :  Heap(int maxlength)
    {
        this-> maxlength = maxlength;
        this->length = 0;
        arr = new int[this->maxlength + 1];
        arr[0] = INT8_MAX;
    }
    private : int parent(int pos) { return (pos-1)/2; }
    private : int leftChild(int pos) { return (2 * pos)+1; }
    private : int rightChild(int pos)
    {
        return (2 * pos) + 2;
    }
    private : bool isLeaf(int pos)
    {
        if (pos > (length / 2) && pos < length) {
            return true;
        }
        return false;
    }

    private : void swap(int fpos, int spos)
    {
        int tmp;
        tmp = arr[fpos];
        arr[fpos] = arr[spos];
        arr[spos] = tmp;
    }
    public : void deleteKey()
{
    int n = this->length;
    int lastElement = arr[n - 1];
    arr[0] = lastElement;
    n = n - 1;
    length--;
    heapify(0);
}

    private : void heapify(int pos)
    {
        int large = pos;
        if( leftChild(pos)<length && arr[leftChild(pos)]>arr[large] )
            large = leftChild(pos);
        if( rightChild(pos)<length && arr[rightChild(pos)]>arr[large] )
            large = rightChild(pos);
        if( large!=pos ){
            swap(pos,large);
            heapify(large);
        }
    }

    public : int size()
    {
        return this->length;
    }
    public :  void insert(int element)
    {
        arr[length++] = element;
        int current = length-1;
        while (current!=0 and arr[current] > arr[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }
    int getMax()
    {
        if(this->length==0)
        return -1;
        return arr[0];
    }
};
void heapsort(vector<int> &v){
	Heap h(v.size());
	while(!v.empty()){
		h.insert(v[v.size()-1]);
		v.pop_back();
	}
    int s = h.size();
	while(s>0){
		v.push_back(h.getMax());
		h.deleteKey();
        s--;
	}
}