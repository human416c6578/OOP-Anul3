package Database;

public class Entry {
    String Author;
    String Title;
    Integer Stock;

    public Entry(String Author, String Title, Integer Stock) {
        this.Author = Author;
        this.Title = Title;
        this.Stock = Stock;
    }

    public String getAuthor() {
        return this.Author;
    }

    public String getTitle() {
        return this.Title;
    }

    public Integer getStock() {
        return this.Stock;
    }

    public boolean equals(Entry entry) {
        return entry.getAuthor().contentEquals(this.Author)  && entry.getTitle().contentEquals(this.Title);
    }

    public int compareTo(Entry other) {
        int authorComparison = this.Author.compareTo(other.Author);

        if (authorComparison != 0) {
            return authorComparison;
        }
        
        return this.Title.compareTo(other.Title);
    }
}
