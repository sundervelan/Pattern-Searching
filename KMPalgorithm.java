import java.util.Arrays;

public class KMPalgorithm {
	
	//Program to implement KMP algorithm

	int F[];
	public void FailureFunction(String pattern)
	{
		int length = pattern.length();
		F = new int[length];
		F[0] =0;
		int i=1;
		int j=0;
		while(i<length)
		{
			if(pattern.charAt(j) == pattern.charAt(i) )
			{
				
				j=j+1;
				F[i] = j;
				i=i+1;
			}
			else if(j>0)
			{
					j = F[j-1];
			
			}
			else
			{
				F[i] = 0;
				i=i+1;
			}
			
		}
				
	}
	
	public void KMPfinding(String pattern,String text)
	{
		int i=0;
		int j=0;
		int textLength = text.length();
		int patternLength = pattern.length();
		System.out.println(Arrays.toString(F));	
		while (i<textLength)
		{
			if(text.charAt(i) == pattern.charAt(j))
			{
				int index = i;
					i=i+1;
					j=j+1;
					
					if(j == patternLength)
					{
						System.out.println("Position is at " + index);
						j= F[j-1];
						
						int k = index;
						while(k>index-patternLength)
						{System.out.println("Char " + text.charAt(k));
						k--;
						}
					}
				}
			else 
				{
				if (j>0)
				{
					j= F[j-1];
				}
			
				else
				{
					i=i+1;	
				}
		}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String pattern = "abcabc";
		String text = "sunabcabcabcder sudeabcabcabcrgald sudevel sunder suder";
		
		KMPalgorithm obj = new KMPalgorithm();
		obj.FailureFunction(pattern);
		obj.KMPfinding(pattern, text);
	}
	
	
	
	

}
