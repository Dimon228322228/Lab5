package Parser;

import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Parser {
    File file = new File("C:\\Users\\Пользовотель\\IdeaProjects\\Laba5\\Test.xml");
    FileInputStream fis;

    {
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Document doc = Jsoup.parse(fis, null, "", Parser.xmlParser());

}
