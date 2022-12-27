#include <bits/stdc++.h>
using namespace std;

int main()
{
    int num_tree, num_friends;
    cin>>num_tree>>num_friends;
    int arr[num_tree];
    for(int i=0;i<num_tree;i++)
        cin>>arr[i];

    sort(arr, arr + num_tree, greater<int>());

    int c=1,price=0,x=0;

    if(num_friends>=num_tree)
    {
        for(int i=0;i<num_tree;i++)
            price+=arr[i];
    }
    else
    {
        for(int i=0;i<num_tree;i++)
        {
            price+=c*arr[i];
            x++;
            if(x%num_friends==0)
            {
                c++;
            }
        }
    }
    cout<<price;
    return 0;
}

