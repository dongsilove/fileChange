package fileChange;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileChange {

	public static void main(String[] args) {
		BufferedReader br;
		BufferedWriter bw;
		String result="";
		try {
			br = new BufferedReader(new FileReader("D:\\Google 드라이브\\10 데이터표준화\\상수도용어.txt"));
			bw = new BufferedWriter(new FileWriter("D:\\Google 드라이브\\10 데이터표준화\\상수도용어2.txt"));

			String wordNm = "";
			String wordDesc =  "";
			while((result = br.readLine()) != null) {
				int spcBrc = result.indexOf("(");
				if (spcBrc > -1) { // "(" 가 있으면 "(" 이전으로 잘라서 wordNm을 만든다.
					wordNm = result.substring(0, spcBrc);
					wordNm = wordNm.replaceAll(" ", "");
					result = result.substring(spcBrc);

					bw.write(wordNm + ", , ");
					
					Pattern pattern = Pattern.compile("(?<=\\().*(?=\\))");
					Matcher matcher = pattern.matcher(result);
					if (matcher.find()) {
						bw.write(matcher.group(0) + ", ");  // 괄호안 data 출력
						//System.out.println("wordNm - " + wordNm );
						//System.out.println(matcher.group(0));
					} else {
						bw.write( ", , ");
						//System.out.print("wordNm - " + wordNm );
					}
					
					int semiIdx = result.indexOf(")");
					if (semiIdx > -1) {
						result = result.substring(semiIdx);
						result = result.replace(" ", "");
						result = result.replace(":", "");
						result = result.replace(" ", "");
						wordDesc = result;
					} else {
						System.out.println("wordNm - " + wordNm + " " +result);
					}
				
				} else { // 공백으로 잘라서 wordNm을 만든다.
					int spcIdx = result.indexOf(" ");
					if (spcIdx > -1) {
						wordNm = result.substring(0, spcIdx);
						bw.write(wordNm + ", , , , ");

						result = result.substring(spcIdx);
						wordDesc = result.replace(" ", "");
					}	
					
				}
				

				bw.write(wordDesc + "\r\n");
				bw.flush();
			}
			bw.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
		}

	}

}
