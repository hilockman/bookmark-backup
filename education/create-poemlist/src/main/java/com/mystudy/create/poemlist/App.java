package com.mystudy.create.poemlist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.crypto.AlgorithmMethod;

/**
 * Hello world!
 *
 */
public class App 
{

	public static List<PoemItem> readPoems(InputStream inputStream) throws IOException {
		List<PoemItem> strs = new ArrayList<PoemItem>();
		
		
	    BufferedReader fr = new BufferedReader(new InputStreamReader(inputStream));
		String str;
		Pattern pattern=Pattern.compile("\\[|\\]|\\s|:");
		while ((str = fr.readLine()) != null) {
			if (!str.contains("["))
					continue;
			
			
			String[] segs = pattern.split(str);
			if (segs.length == 0)
				continue;
			
			int i = 0;
			PoemItem item = new PoemItem();
			for (String s : segs) {
				if (s.isEmpty() )
					continue;
				if (i > 1)
					break;
				
				if (i == 0) {
					item.setTitle(s);
					i++;
				} else {
					item.setAuthor(s);
					i++;
				}
			}
			if (i > 1) {
			strs.add(item);
			}
		}
		
		return strs;
	}
	
	public static void writeLine(BufferedWriter w) throws IOException {
		writeLine(w, "");
	}
	
	public static void writeLine(BufferedWriter w, String line) throws IOException {
		w.write(line+'\n');
	}
	
	public static void writeTableLine(BufferedWriter w, String[] segs) throws IOException {
		writeLine(w, "| "+String.join(" | ", segs).trim()+" |");

	}
	
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );
        String fileName = "poemlist.txt";
        InputStream stream = App.class.getClassLoader().getResourceAsStream(fileName);
        String outPath = "export";
        File outDir = new File(outPath);
        
        if (!outDir.exists()) {
        	outDir.mkdirs();
        }
        
        InputStream source = App.class.getClassLoader().getResourceAsStream("md.template");
  
        BufferedReader r = new BufferedReader(new InputStreamReader(source));
        String line = null;
        
        BufferedWriter w  = new BufferedWriter(new FileWriter(outPath+"/poemlist.md"));
        while ((line  = r.readLine()) != null) {
        	w.append(line);
        	w.newLine();
        }
        
        if (stream == null) {
        	System.out.println("Fail to find "+fileName);
        	return;
        }
        //List<PoemItem> strs = readPoems("C:\\Users\\wangheng\\Desktop\\新建文件夹\\poemlist.txt");
        List<PoemItem> strs = readPoems(stream);
        Map<String, List<PoemItem>> author2poems = new HashMap<>();
        for (PoemItem str : strs) {
        	System.out.println(str);
        	List<PoemItem> poems = author2poems.get(str.getAuthor());
        	if (poems == null) {
        		poems = new ArrayList<>();
        		author2poems.put(str.getAuthor(), poems);
        	}
        	
        	poems.add(str);
        }
        System.out.println("count = "+ strs.size());
        
        //write title table
        writeLine(w, "## 按作品");
        writeLine(w, " ");
        int colSum = 3;
        
        int maxRowCount = 0;
        String [] list = new String[colSum];
        Arrays.fill(list, "目录");
        writeTableLine(w, list);
        Arrays.fill(list, "--");
        writeTableLine(w, list);
        
        int left = strs.size() %  colSum;
        if (left > 0) {
        	maxRowCount = (strs.size() - left) / (colSum - 1);
        } else {
        	maxRowCount = strs.size() / colSum;
        }
        
        //writeTableLine(w, new String[]{"目录1", "目录2"});
        String [] ss1 = new String[colSum];
        int count = 0;
        for (int i = 0; i < maxRowCount; i++) {
        	for (int j = 0; j < colSum; j++) {
        		int offset = i * colSum + j;   
            	if (offset >= strs.size()) {
            		ss1[j] = "";
            		
            	} else {
            		PoemItem poem = strs.get(offset);
            		ss1[j] = String.format("%d [%s][%s]", ++count, poem.getTitle(), poem.getTitle());
            	}
        	}
        	writeTableLine(w, ss1);    	

        }

 
        writeLine(w);

        
        //write author table //
        writeLine(w, "## 按作者");
        writeLine(w, " ");
        
        writeTableLine(w, new String[]{"作者", "作品"});
        writeTableLine(w, new String[]{"--", "--"});
        List<String> authors = new ArrayList<>(author2poems.keySet());
        Collections.sort(authors);
        for (String author : authors) {
        	List<PoemItem> items = author2poems.get(author);
        	List<String> ss = new ArrayList<>();
        	for (PoemItem item : items) {
        		ss.add(String.format("[%s][%s]", item.getTitle(), item.getTitle()));
        	}
        	writeTableLine(w, new String[]{author, String.join(", ", ss)});
        }
        
        
        //write references
        writeLine(w, " ");
        String url = "https://baike.baidu.com/item";
        for (PoemItem poem : strs) {
        	line = String.format("[%s]:%s/%s",poem.getTitle(),url,poem.getTitle());
        	System.out.println(line);
        	
        	writeLine(w, line);
          }
               
        w.flush();
        w.close();
        
    }
}
