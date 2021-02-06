package com.kh.c_collection.a_list.simple;

import java.util.Iterator;

import com.kh.c_collection.a_list.simple.node.Node;

public class SimpleLinkedList<E> implements Iterable<E>{
	private Node<E> head; //첫 노드의 주소를 저장
	private Node<E> moveLink;// 노드간 이동용 레퍼런스
	private int nodeCount; //노드의 갯수 저장
	
	public SimpleLinkedList() {
		// TODO Auto-generated constructor stub
	}

	//1. size()
	public int size() {
		return nodeCount;
	}
	
	//2. add(E e)
	public void add(E data) {
		Node node = new Node(data);
		//head가 초기화 되지 않았고 node의 갯수가 0이라면
		//첫번째 노드라면!
		if(head == null && nodeCount == 0) {
			head = node;
		}else {
			moveLink = head;
			//제일 마자막 노드를 moveLink에 저장
			for(int i = 0; i < nodeCount - 1; i++){
				//moveLink에 moveLink에 담겨있던 노드의 다음 노드를 저장
				moveLink = moveLink.getLink();
			}
			
			//마지막 노드의 link에 새 노드의 주소를 저장
			moveLink.setLink(node);
		}
		nodeCount++;
	}
	
	//3. get(E e)
	public E get(int index) {
		moveLink = head;
		for(int i = 0; i < index; i++) {
			moveLink = moveLink.getLink();
		}
		
		return moveLink.getData();
	}
	
	//4. set(int idx, E data)
	//지정된 인덱스의 데이터를 수정하고, 수정되기 전 데이터를 반환
	public E set(int index, E data) {
		E res = null;
		//0번 인덱스의 값을 수정할 경우 >> Node를 찾을 필요가 없다.
		if(index == 0) {
			//수정 되기 전 수정할 데이터 저장
			res = head.getData();
			head.setData(data);
		}else {
			moveLink = head;
			for(int i = 0; i < index; i++) {
				//수정해야하는 노드를 moveLink에 저장
				moveLink = moveLink.getLink();
			}
			//moveLink의 다음 노드에 담겨있는 Data(수정 되기 전 데이터)를 res에 저장
			res = moveLink.getData();
			//수정완료			
			moveLink.setData(data);
		}
	
		//수정하기 전 데이터 반환
		return res;
	}
	
	//5. remove(int idx)
	public E remove(int index) {
		E res = null;
		
		if(index == 0) {
			//head에 head의 다음 노드를 저장
			//삭제 되기 전, 삭제 될 데이터를 res에 저장
			res = head.getData();
			head = head.getLink();
		}else {
			moveLink = head;
			//삭제할 노드의 앞 노드를 moveLink에 저장
			for(int i = 0; i < index-1; i++) {
				moveLink = moveLink.getLink();
			}
			
			//삭제 되기 전, 삭제 될 데이터를 res에 저장
			res = moveLink.getLink().getData();
			
			//moveLink의 다다음 노드를 moveLink의 link에 저장
			//moveLink의 다음 노드는 참조하고 있는 레퍼런스가 존재하지 않게 되면서
			//가비지컬렉터에의해 메모리에서 내려가게 된다.
			moveLink.setLink(moveLink.getLink().getLink());
		}
		
		nodeCount--;
		return res;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<E>() {
			private int iterIdx;
			private Node<E> befor;

			@Override
			public boolean hasNext() {
				if(iterIdx < nodeCount) {
					return true;
				}
				return false;
			}

			@Override
			public E next() {
				//next()가 처음 호출이면
				if(iterIdx == 0) {
					iterIdx++;
					//befor에 첫 노드를 담고
					befor = head;
					//첫 노드의 데이터를 반환
					return head.getData();
				}else {
					//직전에 반환한 노드의 다음 노드를 befor에 담고
					befor = befor.getLink();
					iterIdx++;
					//그 노드의 데이터를 반환
					return befor.getData();
				}
			}
		};
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
