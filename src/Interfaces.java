Interface 1: Kernel Interface

void removeBookFromShelf(String genre, String title)
  Removes the book with genre and title from the two dimensonal hash-table and adds it to the stack of listOfBooksInProgress
  Parameters:
  genre - the genre of the book
  title - the title of the book
  Updates:
  Map<String,Map<String,String>>[][] hashTable;
  Stack<Map<String,String>> listOfBooksInProgress
  int numberOfBooksInProgress
  Requires:
  this contains book with corresponding genre and title
  Ensures:
  this does not contain book with corresponding title and genre

void addBookToShelf(String genre, String title, String author)
  Adds book to shelf with genre, title, author
  Parameters:
  genre - the genre of the book
  title - the title of the book
  author - the author of the book
  Updates:
   Map<String,Map<String,String>>[][] hashTable;
  Requires:
  this.hashTable does not already contain the book
  Ensures:
  this.hashTable contains book with genre, title author

boolean shelfContainsBook(String genre, String title)
  Returns whether or not the book shelf contains the book with genre, title
  Parameters:
  genre - genre of the book 
  title - title of the book
  Returns:
  true if the book is in the shelf, false if it is not
  Ensures:
  true is returned if the book with corresponding genre and title is in this, and false otherwise

String removeFromListOfBooksInProgress(String genre, String title)
  Removes the book from the stack of books in progress with the corresponding genre and title
  Parameters:
  genre - genre of the book 
  title - title of the book
  Returns:
  the title of the book that was removed from the stack
  Ensures:
  this.listOfBooksInProgress does not contains book with corresponding genre and title
  Requires:
  The stack contains the book with the genre and title
  Updates:
  this.booksInProgress


Interface 2: Secondary Interface

void updateNumberOfBooksToRead(int newNumber)
  Updates the number of books that the user plans on reading
  Parameters:
  newNumber - the new number of books to read
  Requires: 
  newNumber >= 0
  Ensures:
  this.numberOfBooksToRead = newNumber
  Updates:
  this.numberOfBooksToRead

void updateNumberOfBooksDNF(String genre, String title)
  Updates the number of books that the user has quit
  Parameters:
  genre: the genre of the book
  title: the title of the book
  Requires: 
  this.listOfBooksInProgress contains book with corresponding genre and title
  Ensures:
  this.listOfBooksInProgress does not contains book with corresponding genre and title
  this.numberOfBooksDNF = #this.numberOfBooksDNF + 1
  Updates:
  this.listOfBooksInProgress
  this.numberOfBooksDNF

void updateNumberOfBooksCompleted(String genre, String title)
  Updates the number of books that the user has completed
  Parameters:
  genre: the genre of the book
  title: the title of the book
  Requires: 
  this.listOfBooksInProgress contains book with corresponding genre and title
  Ensures:
  this.listOfBooksInProgress does not contains book with corresponding genre and title
  this.numberOfBooksCompleted = #this.numberOfBooksCompleted + 1
  Updates:
  this.listOfBooksInProgress
  this.numberOfBooksCompleted

int goalReached()
  Returns the number of books left to complete to reach the goal
  Returns:
  int value that is greater than or equal to 0
  Requires: 
  this.numberOfBooksToRead >= this.numberOfBooksInProgress
  Ensures:
  Value returned >= 0

String recommedNextBookToRead()
  Returns the title of the book the user should read based on the genre of the last book they read
  Returns:
  A string title
  Requires: 
  this.genreOfLastBookRead != null
  Ensures:
  The title of the book returned has the same genre as the last book the user read

List<Map<String,String>> booksWrittenByAuthor(String author)
  Returns a list of the books in the shelf that are written by the corresponding author
  Returns:
  A list of maps containing the book title and the genre
  Ensures:
  this.hashTable contains the books in the returned list



