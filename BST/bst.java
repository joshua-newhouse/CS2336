import java.util.concurrent.atomic.AtomicReference;

/* bst class:  This class implements a binary search tree of nodes sorted	*
 *	by keyword.  Each node has a linked list of records that are associated	*
 *	with the node's keyword.								*/
class bst {
	Node root;

	private class Node {
		String keyword;	//Keyword associated with all records in list
		Record record;	//First record in linked list of records
		int size;		//Number of records with keyword
		Node l;		//Left child node
		Node r;		//Right child node
		Node p;		//Parent node

		private Node(String k, Record r) {
			this.keyword = new String(k);
			this.record = r;
			this.size = 1;
			this.l = this.r = this.p = null;
		}

		/*Adds record r to the front of the linked list contained by Node */
		public void update(Record r){
			r.next = this.record;
			this.record = r;
			this.size++;
		}

		public void copy(Node source) {
			this.keyword = new String(source.keyword);
			this.record = source.record;
			this.size = source.size;
		}
	}

	public bst() {
		this.root = null;
	}

	/* insert:  Searches the binary tree for a node with the input keyword.	*
	 *	Creates a new record from the input FileData fd				*
	 *												*
	 *	If a node with keyword already exists in the tree then the record	*
	 *		is added to that node's list						*
	 *												*
	 *	If a node with keyword does not exist then one is created with	*
	 *		the record and inserted into the proper place in the tree	*/
	public void insert(String keyword, FileData fd) {
		if(keyword == null) {
			System.err.println("Error: attempting to add null keyword");
			return;
		}

		Record recordToAdd = new Record(fd.id, fd.author, fd.title, null);
		AtomicReference<Node> target = new AtomicReference();
		if(this.search(keyword, target))		//Node with keyword found
			target.get().update(recordToAdd);
		else
			this.addNode(target.get(), new Node(keyword, recordToAdd));
	}

	/*addNode:  Adds child to the left node of parent if child < parent	*
	 *												*
	 *	Adds child to the right node of parent if child > parent		*
	 *												*
	 *	If parent == null then the tree is empty and so the tree root is	*
	 *		set to child.								*/
	private void addNode(Node parent, Node child) {
		if(parent == null)
			this.root = child;			//Add root node
		else if(parent.keyword.compareTo(child.keyword) < 0)
			parent.r = child;				//Add right child node
		else
			parent.l = child;				//Add left child node
		child.p = parent;
	}

	/*search:  returns the result of calling search(Node, String, Atomic...)*
	 *	with the root node as the first actual argument				*/
	private boolean search(String keyword, AtomicReference<Node> target) {
		return this.search(this.root, keyword, target);
	}

	/*search:  performs a recursive search of the binary tree beginning at	*
	 *	current and looking for a node containing keyword.			*
	 *												*
	 *	If a node containing keyword is found the function returns true	*
	 *		and target is set to the node containing the keyword.		*
	 *												*
	 *	If a node containing keyword is not found the function returns	*
	 *		false and target is set to the node that would be the		*
	 *		parent of the node with keyword.  A new node with keyword	*
	 *		can then be added to target using addNode(...)			*/
	private boolean
	search(Node current, String keyword, AtomicReference<Node> target) {
		if(keyword == null) {
			System.err.println("Error: search for null keyword");
			return false;
		}

		if(current == null) {	//Empty tree
			target.set(current);
			return false;
		}

		int cmp = keyword.compareTo(current.keyword);
		if(cmp == 0) {		//Found matching keyword
			target.set(current);
			return true;
		}
		else if(cmp < 0) {	//Keyword is before current node
			if(current.l != null) {		//Move to left child and search
				return search(current.l, keyword, target);
			}
			else {		//Keyword not found
				target.set(current);
				return false;
			}
		}
		else {			//Keyword is after current node
			if(current.r != null) {		//Move to right child and search
				return search(current.r, keyword, target);
			}
			else {		//Keyword not found
				target.set(current);
				return false;
			}
		}
	}

	/*contains: Searches tree for a node with keyword.				*
	 *	Returns true if keyword found, false otherwise				*/
	public boolean contains(String keyword) {
		AtomicReference<Node> unused = new AtomicReference();
		return this.search(keyword, unused);
	}

	/*get_redords:  Searches tree for a node with keyword.			*
	 *	If found returns the first record in the node's record list.	*
	 *	Otherwise returns null.								*/
	public Record get_records(String keyword) {
		AtomicReference<Node> target = new AtomicReference();
		return this.search(keyword, target) ? target.get().record : null;
	}

	/* delete:  Searches the tree for keyword.  If found it deletes the node*
	 * 	from the tree.  If not found nothing happens.				*/
	public void delete(String keyword) {
		AtomicReference<Node> target = new AtomicReference();
		if(this.search(keyword, target)) {		//Keyword found
			Node nodeToDelete = target.get();

			//Perform deletion per algorithm
			if(nodeToDelete.l != null) {		//Node has left child
				Node rightmost = max(nodeToDelete.l);
				nodeToDelete.copy(rightmost);

				int cmp = rightmost.keyword.compareTo(
									rightmost.p.keyword);
				if(cmp < 0)
					rightmost.p.l = rightmost.l;
				else if(cmp > 0)
					rightmost.p.r = rightmost.l;
				else
					nodeToDelete.l = null;

				if(rightmost.l != null)
					rightmost.l.p = rightmost.p;

				rightmost = null;
			}
			else if(nodeToDelete.r != null) {	//Node has right child
				if(nodeToDelete == this.root)
					this.root = nodeToDelete.r;
				else if(nodeToDelete.keyword.compareTo(
								nodeToDelete.p.keyword) < 0)
					nodeToDelete.p.l = nodeToDelete.r;
				else
					nodeToDelete.p.r = nodeToDelete.r;

				nodeToDelete.r.p = nodeToDelete.p;
				nodeToDelete = null;
			}
			else {					//Node is a leaf
				if(nodeToDelete == this.root)
					this.root = null;
				else if(nodeToDelete.keyword.compareTo(
								nodeToDelete.p.keyword) < 0)
					nodeToDelete.p.l = null;
				else
					nodeToDelete.p.r = null;

				nodeToDelete = null;
			}
		}
	}

	/*max:  uses recursion to find and return the maximal (rightmost) node	*
	 *	of the subtree									*/
	private Node max(Node subtreeRoot) {
		return subtreeRoot.r != null ? max(subtreeRoot.r) : subtreeRoot;
	}

	public void print() {
		print(root);
	}

	private void print(Node t) {
		if(t != null) {
			print(t.l);
			System.out.println(t.keyword);
			Record r = t.record;
			while(r != null) {
				System.out.printf("\t%s\n",r.title);
				r = r.next;
			}
			print(t.r);
		}
	}
}
