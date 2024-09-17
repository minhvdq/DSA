package DataStructs;

import java.util.EmptyStackException;

public class Stack <E> {

    private E[] items;
    private int top;

    // public Stack( int capacity ) {
    //     items = new E[capacity];
    // }

    public boolean isEmpty() {
        return top == 0;
    }

    public boolean isFull( ) {
        return top == items.length;
    }

    public boolean push( E item ){
        if( isFull() ) {
            return false;
        }
        items[top] = item;
        top ++;
        return true;
    }

    public E pop( ) {
        if( isEmpty() ) {
            throw new EmptyStackException();
        }
        return items[--top];
    }
    public static void main( String[] args ) {
        int a = 1;
        int[] arr = {1, 2, 3};
        System.out.println( arr[--a] );
        System.out.println(a);
    }
}
