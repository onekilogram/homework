package test;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JavaFile {

	public static void main(String[] args) {

		try {

			// read file content from file

			StringBuffer sb = new StringBuffer("");

			FileReader reader = new FileReader("c://colors.ascii.bak");

			BufferedReader br = new BufferedReader(reader);

			String str = null;
			HashMap<Integer, Integer>  hashMap = new HashMap<Integer,Integer>();
			while ((str = br.readLine()) != null) {

				//sb.append(str + "/n");
				String []strings = str.split("\\s+");
				
				if(hashMap.get(strings.length)==null){
					hashMap.put(strings.length, 1);
				}
				else{
					hashMap.put(strings.length, hashMap.get(strings.length)+1);
				}
				//System.out.println(str);

			}
			System.out.println(hashMap.size());
			for(Map.Entry<Integer, Integer>  map:hashMap.entrySet()){
				System.out.println(map.getKey()+":"+map.getValue());
			}
			br.close();

		/*	reader.close();

			// write string to file

			FileWriter writer = new FileWriter("c://test2.txt");

			BufferedWriter bw = new BufferedWriter(writer);

			bw.write(sb.toString());

			bw.close();

			writer.close();*/

		}

		catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		catch (IOException e) {

			e.printStackTrace();

		}

	}

}