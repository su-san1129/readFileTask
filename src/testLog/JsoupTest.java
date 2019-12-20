package testLog;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {
	
	public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://techacademy.jp/").get();
        Elements courses = document.select(".top-bootcamp-courses div");
        for (Element course : courses) {
            System.out.println(course.text());
        }
    }
	
}
