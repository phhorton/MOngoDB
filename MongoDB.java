import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Philip on 12/5/2016.
 */
public class MongoDB implements IMongoDB {

    private MongoCollection<Document> collection;

    public MongoDB() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("myDB");
        collection = database.getCollection("test");
    }

    @Override
    public Document createDocument(String name, int x) {
        return new Document("name", name).append("number", x);
    }

    @Override
    public String addDocument(Document doc) {
        collection.insertOne(doc);
        return null;
    }

    @Override
    public List<String> addMultipleDocuments(List<Document> list) {
        collection.insertMany(list);
        return null;
    }

    @Override
    public long countDocuments() {
        return collection.count();
    }

    @Override
    public Document queryForDocument(String name) {
        return collection.find(eq("name", name)).first();
    }

    @Override
    public List<Document> queryDocuments(List<String> names) {
        List<Document> list = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            list.add(cursor.next());
        }
        return list;
    }

    @Override
    public void printDocument(Document doc) {
        System.out.println(doc.toJson());
    }

    @Override
    public void printAllDocuments() {
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson());
        }
    }


}
