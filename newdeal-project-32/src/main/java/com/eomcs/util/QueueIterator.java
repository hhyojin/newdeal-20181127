package com.eomcs.util;

public class QueueIterator<E> implements Iterator<E>{
  
  Queue<E> queue;  
  int size; ////스택은 po할 때마다 줄어드니까 사이즈 갖고 있어야 해
  int count; //현재까지 꺼낸 개수
  
  public QueueIterator(Queue<E> queue) {
    this.queue = queue;
    this.size = queue.size();
  }

  @Override
  public boolean hasNext() {
    return this.count < this.size;
  }

  @Override
  public E next() {
    this.count++;
    return queue.poll();
  }

}
