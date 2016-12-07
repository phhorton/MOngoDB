import org.bson.Document;

import java.util.List;

/**
 * Created by Philip on 12/6/2016.
 */
public interface IMongoDB {

    Document createDocument(String name, int num);

    String addDocument(Document doc);

    List<String> addMultipleDocuments(List<Document> list);

    long countDocuments();

    Document queryForDocument(String name);

    List<Document> queryDocuments(List<String> names);

    void printDocument(Document doc);

    void printAllDocuments();
}
