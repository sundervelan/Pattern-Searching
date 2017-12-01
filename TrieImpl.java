import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TrieImpl {

	private static final int R = 256; 
	private Node root = new Node();
	private static ArrayList<String> words= new ArrayList<String>();
	
	public class Node
	{
		private Object value;
        private Node[] next = new Node[R];	
	}
	
	public void  put(String text,int val)
	{
		root = putText(root,text,val,0);
		
	}
	
	public Node putText(Node node,String text,int val, int d)
	{
	if (node==null)
		node = new Node();
		
	if(d == text.length())
	{ node.value = val;
	return node;}
	
	char a= text.charAt(d);
	//System.out.println(val);
	//System.out.println(text);
	int ascii = (int) a;
	System.out.println(ascii);
	if(ascii>255)
		a = 255;
	 node.next[a]= putText(node.next[a],text,val, d+1);
	return node;
	
	}
	
	public Object get(String text)
	{
		Node result = getText(root,text,0);
		
		if(result != null)
		{
			return result.value;
		}
		else 
		{
			return result.value;
		}
		
	}
	
	public Node getText(Node node,String text, int d)
	{
		
		
        if(node ==null)
		return node;
        
		if(d == text.length())
		{ return node;}
		
		
		char a= text.charAt(d);
		int ascii = (int) a;
		System.out.println(ascii);
		if(ascii>255)
			a = 255;
		Node result =  getText(node.next[a],text, d+1);
		
		return result;
			
	}
	
	public boolean contains(String text)
	{
		Object result = get(text);
		if(result != null)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public Object longestprefix(String text)
	{
		 if (text == null) throw new IllegalArgumentException("argument to longestPrefixOf() is null");
		 
		int length = longestprefix(root,text,0,-1);
		
		if(length ==-1)
		return null;
			else
				return (text.substring(0, length));
			
		
		
	}
	
	public int longestprefix(Node node,String text, int d,int length)
	{
	
		 if(node ==null)
				return length;
		 if(node.value != null)
		   length =d;
		 if(d == text.length())
			{ length =d;
			return length;}
			
			
			char a= text.charAt(d);
			int ascii = (int) a;
			System.out.println(ascii);
			if(ascii>255)
				a = 255;
			
			return longestprefix(node.next[a],text, d+1,length);
			
			 
	}
	
	
	public static void main(String[] args) {
		TrieImpl trie = new TrieImpl();

		try {
			File file = new File("E:\\Interview program workout\\Pattern Searchng\\src\\test.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] Res = line.split("[\\p{Punct}\\s]+");
				for (String s:Res){
				    System.out.println(s);
				    words.add(s);
				}
				
			}
			fileReader.close();
								
		
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		// code for get
		for (int i=0; i<words.size(); i++){
			trie.put( words.get(i),i);
		}
		
		// code for put
		
		for (int i=0; i<words.size(); i++){
			int result = (int) trie.get( words.get(i));
			System.out.println(result);
		}

		//code for trie contains
		System.out.println(trie.contains("so"));
		
		//code for longestprefix
		System.out.println(trie.longestprefix("interviewersdfgdf"));
			
		
		
	}

}
