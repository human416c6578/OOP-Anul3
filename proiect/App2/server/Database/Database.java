package Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Database {
    String FilePath;
    ArrayList<Entry> Entries;

    public Database(String Path) {
        this.FilePath = Path;
        this.Entries = new ArrayList<Entry>();
        loadDatabase();

    }
    
    void loadDatabase() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.FilePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String title = line.trim();
                String author = reader.readLine().trim();
                int stock = Integer.parseInt(reader.readLine().trim());

                Entry entry = new Entry(author, title, stock);
                Entries.add(entry);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public Entry getEntry(String Author, String Title) {
        Entry search = new Entry(Author, Title, null);
        for(Entry entry : Entries) {
            if(entry.equals(search))
                return entry;
        }

        return search;
    }

    public void printEntries(){
        for(Entry entry : this.Entries) {
            System.out.println(entry.getAuthor() + ":" + entry.getTitle() + ":" + entry.getStock());
        }
    }
}
