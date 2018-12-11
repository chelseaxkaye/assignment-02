import java.util.*;
import java.io.*;

public class Hashtable {

	//inner class hashnode
	public class HashNode {
		public String key;
		public String value;
		public HashNode next;
		public HashNode(String k, String v) {
			key = k;
			value = v;
			next = null;
		}
	}

	private int num_bucks;
	private ArrayList<HashNode> buckets;
	private int size;

	public Hashtable() {
		num_bucks = 20000;
		size = 0;
		buckets = new ArrayList<HashNode>(num_bucks);
		for (int i = 0; i < num_bucks; i++)
			buckets.add(null);
	}

	public int getBucket(String k) {
		int code = Math.abs(k.hashCode());
		return code % num_bucks;
	}

	public String get(String k) {
		int id = getBucket(k);
		HashNode node = buckets.get(id);
		while (node != null) {
			if (node.key.equals(k))
				return node.value;
			node = node.next;
		}
		return null;
	}

	public boolean containsKey(String k) {
		return get(k) != null;
	}

	public void put(String k, String v) {
		int id = getBucket(k);
		HashNode head = new HashNode(k, v);
		head.next = buckets.get(id);
		buckets.set(id, head);
		size++;
	}

	public String remove(String k) {
		int id = getBucket(k);
		HashNode node = buckets.get(id);
		HashNode prev = null;
		while (node != null) {
			if (node.key.equals(k))
				break;
			prev = node;
			node = node.next;
		}
		if (node == null)
			return null;
		size--;
		if (prev == null)
			buckets.set(id, node.next);
		else
			prev.next = node.next;
		return node.value;
	}
}