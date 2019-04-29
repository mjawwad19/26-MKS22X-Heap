import java.util.*;
public class MyHeap{
  /*swaps two nodes!*/
  private static void swapN(int[] d, int i1, int i2) {
    //System.out.println("Swapping " + d[i2] + " with " + d[i1]);
    int tStore = d[i2];
    d[i2] = d[i1];
    d[i1] = tStore;
  }

  /*
  size  is the number of elements in the data array.
  push the element at index i downward into the correct position. This will swap with the larger of the child nodes provided thatchild is larger. This stops when a leaf is reached, or neither child is larger. [ should be O(logn) ]
  precondition: index is between 0 and size-1 inclusive
  precondition: size is between 0 and data.length-1 inclusive.
  */
  private static void pushDown(int[]data,int size,int index){
    if (size % 2 == 0 && 2*index+1 == size -1) {
      if (data[index] < data[2*index+1]) {
        swapN(data, index, 2*index +1);
        return;
      //simplest case would be when child of index is a leaf and yet is greater than index.
      }
    }
    //okay if index has two available kids (whom aren't leaves), yet is DEFINITELY less than both of it's children , THEN check for which is the greatest child to pushdown to!
    else if (2*index+2 < size && (data[index] < data[2*index+1]
                              &&  data[index] < data[2*index+2])) {
         //left child is greater than both index parent and right sibling
         if (data[2*index + 1] > data[2*index+2]) {
           swapN(data, index, 2*index +1);
           pushDown(data, size, 2*index +1);
         }
         else {
           //right child is greather than both index parent and left sibling
           swapN(data, index, 2*index + 2);
           pushDown(data, size, 2*index +2);
         }
       }
     }

  /*
  push the element at index i up into the correct position. This will swap it with the parent node until the parent node is larger or the root is reached. [ should be O(logn) ]
  precondition: index is between 0 and data.length-1 inclusive.
  */
  private static void pushUp(int[]data,int index){
    // basically the reverse of pushdown --locate the parent!
    if (index == 0 || data[(index-1)/2] > data[index]) return;
    //when you are at the root of the tree you kinda have to stop.
    //also you cannot push up since this is a max heap --> if the parent is already greater than it's children, then it is already in the right place!
    else {
      swapN(data, index, (index-1)/2);
      pushUp(data, (index-1)/2);
    }
    //otherwise the parent IS in the wrong place and needs to be swapped with it's child. Continue evaluating from there!
  }

  /*
  convert the array into a valid heap. [ should be O(n) ]
  */
  public static void heapify(int[] d){
    for (int i = d.length -1; i >= 0; i--) {
      //System.out.println("currently at index: " + i);
      pushDown(d,d.length, i);
      //basically push the element to the right place, then change index.
      //ystem.out.println(Arrays.toString(d));
    }
  }

  /*
  sort the array [ should be O(nlogn) ] :
  converting it into a heap
  removing the largest value n-1 times (remove places at end of the sub-array).
  */
  public static void heapsort(int[] d){
    heapify(d);
    for (int i = d.length-1; i > 0; i--) {
      swapN(d, 0, i);
      pushDown(d, i, 0);
    }
  }

  public static void main(String[] args) {
    int[] a = {12,33,5,2,7,52,16};
    heapify(a);
    System.out.println(Arrays.toString(a));
    heapsort(a);
    System.out.println(Arrays.toString(a));
  }
}
