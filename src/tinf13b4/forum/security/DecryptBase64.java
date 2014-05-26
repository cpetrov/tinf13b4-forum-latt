package tinf13b4.forum.security;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

public class DecryptBase64 
{
	private ArrayList<String> data;
	
	public DecryptBase64() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<String> DecryptBase64(final String filepath) 
	{	
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
			data = new ArrayList<String>();
			while(reader.ready())
			{
				data.add(reader.readLine());
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		int i = 0;
		for(String a : data)
		{
			data.set(i, decrypt(a));
			i++;
		}
		/*
		 * Position 0: Hostname
		 * Position 1: Port
		 * Position 2: Databasename
		 * Position 3: Username
		 * Position 4: Password
		 */
		return data;
	}
	
	public String decrypt(String s){
        return new String(DatatypeConverter.parseBase64Binary(s));
	}
}
