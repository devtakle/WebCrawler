/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.io.IOException;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *
 * @author devtakle
 */
public class WebCrawler  {
    //set maximum depth
    private static final int MAX_DEPTH = 3;
    private final HashSet<String> links;
    //set maximum links to be parsed
    private static final int MAX_LINKS = 50;
    
    public WebCrawler()  {
        links = new HashSet<>();
    }
    
    public void getLinks(String url, int depth) throws IOException {
        if(!links.contains(url) && depth <= MAX_DEPTH && links.size() <= MAX_LINKS) {
            //print urls visited 
            System.out.println(">> Depth: " + depth + " [" + url + "]");
            links.add(url);
            Document doc = Jsoup.connect(url).get();
            Elements pageLinks = doc.select("a[href]");
            depth++;
            for(Element page : pageLinks) {
                //recursive calls for depth-first search
                getLinks(page.attr("abs:href"), depth);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new WebCrawler().getLinks("http://www.uncc.edu/", 0);
    }

   
    
}
