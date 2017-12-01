import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;





public class SuffixTrie {
	
	private static final int R = 256; 
	private static ArrayList<String> words= new ArrayList<String>();
	private Node root = new Node();
	
	public class Node
	{
		
        private Node[] next = new Node[R];
        private List<indexNode> value;
        
        Node()
        {
        	value = new LinkedList<indexNode>();
        	for(int i=0; i< R; i++)
        	{
        		next[i] = null;
        	}
        }
	}
	
	class indexNode {
		public int x; 
		public int y;
		public int getX() {
		    return x;
		}
		public void setX(int x) {
		    this.x = x;
		}
		public int getY() {
		    return y;
		}
		public void setY(int y) {
		    this.y = y;
		}
		public indexNode(int x, int y) {
		    super();
		    this.x = x;
		    this.y = y;
		}
	}
	public void insert(String text, int lineNo, int wordNo)
	{
		for(int i=0; i< text.length(); i++)
    	{
    		String temp = text.substring(i, text.length());
    		
    		root = insertPut(root,temp,lineNo,wordNo,0);
    	}
	}
	
	public Node insertPut(Node node,String text,int lineNo,int wordNo, int d)
	{
	if (node==null)
		node = new Node();
		
	if(d>0)
	{node.value.add(new indexNode(lineNo,wordNo) );}
	
	if(d == text.length())
	{ 
		
		return node;
	}
		
	char a= text.charAt(d);
	int ascii = (int) a;
	System.out.println(ascii);
	if(ascii>255)
		a = 255;
	 node.next[a]= insertPut(node.next[a],text,lineNo,wordNo,d+1);
	return node;
	
	}
	
	
	
	public void get(String text)
	{
		Node result = getText(root,text,0);
		
		if(result != null)
		{
			
			List resultList = new LinkedList<indexNode>();
			
			resultList = result.value;
			System.out.println("Match found at :");
			 for (int i = 0; i < resultList.size(); i++) {
				 indexNode a = (indexNode) resultList.get(i);
		            System.out.println("Line no: "+ a.x +" Word No:"+ a.y);
		        }
			
			//return result.value;
		}
		else 
		{
			 System.out.println("No match found");
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SuffixTrie trie = new SuffixTrie();
		
		try {
			File file = new File("E:\\Interview program workout\\Pattern Searchng\\src\\suffixTrieTest.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line;
			int lineNo = 1;
			while ((line = bufferedReader.readLine()) != null) {
				String[] Res = line.split("[\\p{Punct}\\s]+");
				int wordNo=0;
				for (String s:Res){
				    System.out.println(s);
				    wordNo++;
				    trie.insert(s,lineNo,wordNo);
				}
				lineNo++;
			}
			fileReader.close();
								
		
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// code for get
				
					trie.get("cat");
				
	
		
	}

}
