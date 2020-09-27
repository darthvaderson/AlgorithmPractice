package kakao_blind_2020;

import java.util.ArrayList;
import java.util.Arrays;

/* TRIE �ڷ� ���� Ȱ�� ver.1
reference : https://gusdnd852.tistory.com/174
*/
class TrieNode{

	TrieNode[] next = new TrieNode[26];   // 26�� ���ĺ� ����
	boolean isLastChar;   // ������ ����ǥ�� (�ܾ��� ������ ǥ��)
	int count;
	
	void insert(String string) {
		char[] ch = string.toCharArray();
		System.out.println(string);
		char cur = ch[0];
		count += 1;
		if(cur=='\0') {

			isLastChar = true;
		}
		else {
			int idx = char2indx(cur);
			if(next[idx]==null) {
				next[idx] = new TrieNode();
			}

			string = string.substring(1);

			next[idx].insert(string);
		}

	}
	void insertReverse(String string) {
		char[] ch = string.toCharArray();
		System.out.println(string);
		char cur = ch[ch.length-1];
		count += 1;
		if(cur=='\0') {

			isLastChar = true;
		}
		else {
			int idx = char2indx(cur);
			if(next[idx]==null) {
				next[idx] = new TrieNode();
			}

			string = string.substring(0,ch.length-1);

			next[idx].insertReverse(string);
		}
		
		
	}
//	Boolean find(String string) {
//		char[] ch = string.toCharArray();
//		char cur = ch[0];
//
//		if(cur == '\0') return isLastChar;
//		else {
//			int idx = char2indx(cur);
//
//			if(next[idx] == null) return null;
//			string = string.substring(1);
//
//			return next[idx].find(string);
//		}
//
//	}
	int count(String string) {
		char[] ch = string.toCharArray();
		char cur = ch[0];
		System.out.println(cur);
		if(cur == '\0' && isLastChar) return count;
		else if(cur == '\0' && !isLastChar) return 0;
		else {
			if(cur != '?') {
				int idx = char2indx(cur);
				System.out.println(cur);
				if(next[idx] == null) return 0;
				string = string.substring(1);
				return next[idx].count(string);
			}
			else {
				return count;
			}
		}
	}
	int char2indx(char c) {
		if(c<'a') {
			c += 'a'-'A';
		}
		return c-'a';
	}
}

class Trie{

	TrieNode root = new TrieNode();

	void insert(String key) {
		root.insert(key + '\0');
	}
	void insertReverse(String key) {
		root.insertReverse('\0'+key);
	}

//	void find(String key) {
//		Boolean result = root.find(key + '\0');
//
//		if (result == null)
//			System.out.println("���� ����");
//		else if (result)
//			System.out.println("���� ����");
//		else
//			System.out.println("���� ������ ���� �ƴ�");
//	}
	int count(String key) {
		ArrayList<Integer> k = new ArrayList<>();
		return root.count(key + '\0');
	
	}
	


}
public class Problem04_revised {

	static String words[] = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
	static String queries[] = {"fro??", "????o", "fr???", "fro???", "pro?"};
	public static void main(String[] args) {
		Trie tries[] = new Trie[10001];
		Trie reverse_tries[] = new Trie[10001];
		for(int i = 1 ; i <= 10000 ; i++) {
			tries[i] = new Trie();
			reverse_tries[i] = new Trie();
		}
		for(String word : words) {
			tries[word.length()].insert(word);
			reverse_tries[word.length()].insertReverse(word);
		}
		int answer[] = new int[queries.length];
		int i = 0;
		for(String query : queries) {
			if(query.charAt(0) == '?') {
				char cur[] = new char[query.length()];
				for(int j = 0 ; j < query.length() ; j++) {
					cur[j] = query.charAt(query.length()-1-j);
				}
				String reverse = new String(cur);
				answer[i++] = reverse_tries[query.length()].count(reverse);
			}else {
				answer[i++] = tries[query.length()].count(query);
			}
		}
		System.out.println(Arrays.toString(answer));
		
	}

}
