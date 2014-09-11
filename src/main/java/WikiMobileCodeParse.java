import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xkorey on 2014/9/11.
 */
public class WikiMobileCodeParse {

//    String url="https://en.wikipedia.org/wiki/Mobile_country_code#A";

    String url="http://mcclist.com/mobile-network-codes-country-codes.asp";
    File input = new File("g:/code.htm");
//    File output = new File("g:/db_code.txt");
    File output = new File("g:/db_ops.txt");

    public void listCode() throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements names = doc.select("h3");
        Elements tabs = doc.select(".dataLists");
        for(Element e:names){
            System.out.println(e.id());
        }
//        System.out.println(doc);
    }

    public void listCodeTmpFile() throws IOException {
        Document doc = Jsoup.parse(input, "UTF-8");
        FileWriter fileWriter = new FileWriter(output,true);
        Elements names = doc.select("h3");
        Elements tabs = doc.select(".dataLists");
        List<String> tmp = new ArrayList();
        for(Element e:names){
            tmp.add(e.child(0).text());
        }
        int j=0;
        for(Element t:tabs){
            Element tbs = t.child(0);
            int i=0;
            for(Element tr:tbs.children()){
//                Element tr = tb.child(0);
                if(i==0){
                    i++;
                    continue;
                }else{
                    String t4=tr.child(4).html().replaceAll("&nbsp;", "");
                    if(t4.equals("Operational")){
                        fileWriter.write(makeOpsField(tr));
                    }
                }
                }
                 j++;
            }
            fileWriter.flush();
            fileWriter.close();
        }

        String makeField(String name,Element tr){
            String t0=tr.child(0).html().replaceAll("&nbsp;", "");
            String t1=tr.child(1).html().replaceAll("&nbsp;", "");
            String t2=tr.child(2).html().replaceAll("&nbsp;", "");
            String t3=tr.child(3).html().replaceAll("&nbsp;", "");
            String t4=tr.child(4).html().replaceAll("&nbsp;", "");
            return name+"\t"+t0+
                    "\t"+
                    (t1.length()>1?(t1):("0"+t1))
                    +"\t"+
                    (t2.equals("")?"null":t2)
                    +"\t"+
                    (t3.equals("")?"null":t3)
                    +"\t"+
                    t4
                    +"\t"+System.lineSeparator();
        }

        String makeOpsField(Element tr){
            String t0=tr.child(0).html().replaceAll("&nbsp;", "");
            String t1=tr.child(1).html().replaceAll("&nbsp;", "");
            String t2=tr.child(2).html().replaceAll("&nbsp;", "");
            String t3=tr.child(3).html().replaceAll("&nbsp;", "");
            return t0+
                    (t1.length()>1?(t1):("0"+t1))
                    +"\t"+
                    (t3.equals("")?t2:(t2+"-"+t3))
                    +"\t"+System.lineSeparator();
        }
    }
